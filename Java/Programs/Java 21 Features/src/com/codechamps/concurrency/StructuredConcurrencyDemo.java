/*
@author CodeChamps0419

Demonstrates Java 21 Structured Concurrency (StructuredTaskScope):
1. Using ShutdownOnFailure policy to handle parallel subtasks where any failure cancels remaining tasks.
2. Using ShutdownOnSuccess policy to race concurrent tasks (e.g., fallback gateways) and return the first successful result.
3. Showcases clean scoping, error propagation, and resource safety without thread leaks.
*/

package com.codechamps.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyDemo {

    public record ProductDetails(String productId, String title, double price, boolean inStock) {}

    // 1. SHUTDOWN-ON-FAILURE DEMO: Parallel Aggregation with fail-fast cancellation
    public static ProductDetails fetchProductDetailsConcurrently(String productId) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            // Fork subtasks for parallel execution
            Future<String> titleTask = scope.fork(() -> {
                Thread.sleep(40); // Simulate product catalog service
                return "MacBook Pro M3 Max";
            });

            Future<Double> priceTask = scope.fork(() -> {
                Thread.sleep(30); // Simulate pricing microservice
                return 2499.99;
            });

            Future<Boolean> stockTask = scope.fork(() -> {
                Thread.sleep(20); // Simulate inventory database check
                return true;
            });

            // Wait for all subtasks to finish, throwing if any fail
            scope.join();
            scope.throwIfFailed(ex -> new RuntimeException("Subtask failed during aggregation", ex));

            return new ProductDetails(
                productId,
                titleTask.resultNow(),
                priceTask.resultNow(),
                stockTask.resultNow()
            );

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to fetch product details for: " + productId, e);
        }
    }

    // 2. SHUTDOWN-ON-SUCCESS DEMO: Racing multiple payment gateways for fastest response
    public static String processPaymentWithGatewayRace(String orderId) {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {

            scope.fork(() -> {
                Thread.sleep(60); // Gateway A (Slower)
                return "Processed via Gateway-A (Stripe)";
            });

            scope.fork(() -> {
                Thread.sleep(25); // Gateway B (Faster)
                return "Processed via Gateway-B (PayPal)";
            });

            scope.fork(() -> {
                Thread.sleep(100); // Gateway C (Slowest)
                return "Processed via Gateway-C (Adyen)";
            });

            scope.join();

            // Returns the result of the first successful subtask and cancels the rest
            return scope.result();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("All payment gateways failed to respond", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 21 Structured Concurrency Demo ---\n");

        // --- TEST 1: ShutdownOnFailure ---
        System.out.println("1. Parallel Product Details Aggregation (ShutdownOnFailure):");
        ProductDetails details = fetchProductDetailsConcurrently("PROD-8899");
        System.out.println("   - Aggregated Product Result:");
        System.out.println("     * Product ID: " + details.productId());
        System.out.println("     * Title:      " + details.title());
        System.out.println("     * Price:      $" + details.price());
        System.out.println("     * In Stock:   " + details.inStock());
        System.out.println();

        // --- TEST 2: ShutdownOnSuccess ---
        System.out.println("2. Gateway Racing (ShutdownOnSuccess):");
        String paymentResult = processPaymentWithGatewayRace("ORD-554433");
        System.out.println("   - Fastest Gateway Winner Result: " + paymentResult);
    }
}

/*
Output:

--- Java 21 Structured Concurrency Demo ---

1. Parallel Product Details Aggregation (ShutdownOnFailure):
   - Aggregated Product Result:
     * Product ID: PROD-8899
     * Title:      MacBook Pro M3 Max
     * Price:      $2499.99
     * In Stock:   true

2. Gateway Racing (ShutdownOnSuccess):
   - Fastest Gateway Winner Result: Processed via Gateway-B (PayPal)

*/