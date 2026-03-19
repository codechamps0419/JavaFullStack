/*
 * @author CodeChamps0419
 *
 * Demonstrates Java 21 Structured Concurrency (StructuredTaskScope):
 *
 * 1. ShutdownOnFailure:
 *    Executes independent subtasks concurrently.
 *    If one fails, unfinished subtasks are interrupted.
 *
 * 2. ShutdownOnSuccess:
 *    Returns the first successful result from concurrent subtasks.
 *
 * 3. Demonstrates structured task management, cancellation,
 *    exception propagation and resource cleanup.
 *
 * NOTE:
 * StructuredTaskScope is a preview API in Java 21.
 * Compile and run with --enable-preview using JDK 21.
 * 
 * javac --release 21 --enable-preview -d out StructuredConcurrencyDemo.java
 * java --enable-preview -cp out com.codechamps.concurrency.StructuredConcurrencyDemo
 */

package com.codechamps.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredConcurrencyDemo {

    public record ProductDetails(
            String productId,
            String title,
            double price,
            boolean inStock
    ) {
    }

    /*
     * APPROACH 1: ShutdownOnFailure
     *
     * Fetch product details concurrently from independent services.
     *
     * If any subtask fails, unfinished subtasks are interrupted.
     * The final result is constructed only when all subtasks succeed.
     */
    public static ProductDetails fetchProductDetailsConcurrently(String productId) {

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            // Task 1: Fetch product title.
            Subtask<String> titleTask = scope.fork(() -> {
                Thread.sleep(40);
                return "MacBook Pro M3 Max";
            });

            // Task 2: Fetch product price.
            Subtask<Double> priceTask = scope.fork(() -> {
                Thread.sleep(30);
                return 2499.99;
            });

            // Task 3: Check product availability.
            Subtask<Boolean> stockTask = scope.fork(() -> {
                Thread.sleep(20);
                return true;
            });

            // Wait for completion according to the scope's failure policy.
            scope.join();

            // Convert any subtask failure into a RuntimeException.
            scope.throwIfFailed(
                    ex -> new RuntimeException(
                            "Subtask failed during aggregation", ex
                    )
            );

            // Java 21 uses get() instead of resultNow().
            return new ProductDetails(
                    productId,
                    titleTask.get(),
                    priceTask.get(),
                    stockTask.get()
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Product aggregation interrupted for: " + productId, e
            );
        }
    }

    /*
     * APPROACH 2: ShutdownOnSuccess
     *
     * Execute several independent provider lookups concurrently.
     * Return the first successful response.
     *
     * NOTE:
     * This is a simulation. Never race actual payment charges because
     * multiple providers could charge the customer before cancellation.
     */
    public static String processPaymentWithGatewayRace(String orderId) {

        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {

            // Gateway A: Slower response.
            scope.fork(() -> {
                Thread.sleep(60);
                return "Processed via Gateway-A (Stripe)";
            });

            // Gateway B: Faster response.
            scope.fork(() -> {
                Thread.sleep(25);
                return "Processed via Gateway-B (PayPal)";
            });

            // Gateway C: Slowest response.
            scope.fork(() -> {
                Thread.sleep(100);
                return "Processed via Gateway-C (Adyen)";
            });

            // Wait until a task succeeds or all tasks fail.
            scope.join();

            // Return the first successful result.
            return scope.result();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Gateway lookup interrupted for order: " + orderId, e
            );

        } catch (ExecutionException e) {

            throw new RuntimeException(
                    "All gateway lookups failed for order: " + orderId, e
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("--- Java 21 Structured Concurrency Demo ---\n");

        // TEST 1: ShutdownOnFailure
        System.out.println(
                "1. Parallel Product Details Aggregation (ShutdownOnFailure):"
        );

        ProductDetails details =
                fetchProductDetailsConcurrently("PROD-8899");

        System.out.println("   - Aggregated Product Result:");
        System.out.println("     * Product ID: " + details.productId());
        System.out.println("     * Title:      " + details.title());
        System.out.println("     * Price:      $" + details.price());
        System.out.println("     * In Stock:   " + details.inStock());

        System.out.println();

        // TEST 2: ShutdownOnSuccess
        System.out.println("2. Gateway Racing (ShutdownOnSuccess):");

        String paymentResult =
                processPaymentWithGatewayRace("ORD-554433");

        System.out.println(
                "   - Fastest Gateway Winner Result: " + paymentResult
        );
    }
}

/*
Expected Output:

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