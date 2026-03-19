/*
 * @author CodeChamps0419
 *
 * Demonstrates Java 21 Virtual Threads and Structured Concurrency:
 * 1. Creating a virtual thread directly.
 * 2. Running independent tasks with a virtual-thread-per-task executor.
 * 3. Sharing order statuses using ConcurrentHashMap.
 * 4. Protecting a shared wallet balance with ReentrantLock.
 * 5. Combining independent service responses with ShutdownOnFailure.
 *
 * StructuredTaskScope is a preview API in Java 21.
 * Compile and run with --enable-preview using JDK 21.
 * 
 * use --enable-preview to enable preview APIs and run this program (If not then you will get errors)
 */

package com.codechamps.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.locks.ReentrantLock;

public class VirtualThreadsDomainDemo {

    // Safe for simultaneous map updates by different virtual threads.
    private static final ConcurrentHashMap<String, String> orderStore = new ConcurrentHashMap<>();

    // Protects reads and writes to the shared wallet balance.
    private static final ReentrantLock balanceLock = new ReentrantLock();
    private static double userWalletBalance = 500.00;

    /*
     * APPROACH: Explicit locking for a shared mutable value.
     * Returning the updated amount while holding the lock gives each
     * task a consistent balance to print.
     */
    public static double depositFunds(double amount) {
        balanceLock.lock();
        try {
            userWalletBalance += amount;
            return userWalletBalance;
        } finally {
            balanceLock.unlock();
        }
    }

    // Immutable response assembled from three independent operations.
    public record OrderSummary(String orderId, String customerName, double amount, String status) {
    }

    /*
     * APPROACH: ShutdownOnFailure with StructuredTaskScope.
     * All three operations must succeed to build the final response.
     * If one fails, the scope interrupts unfinished sibling tasks.
     */
    public static OrderSummary aggregateOrderDetails(String orderId) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            var customerTask = scope.fork(() -> {
                Thread.sleep(50); // Simulated customer-service call.
                return "Hrushikesh (Pune Hub)";
            });

            var pricingTask = scope.fork(() -> {
                Thread.sleep(30); // Simulated pricing-service call.
                return 1250.75;
            });

            var statusTask = scope.fork(() -> {
                Thread.sleep(20); // Simulated order-status lookup.
                return "CONFIRMED_VIA_VIRTUAL_THREAD";
            });

            scope.join();
            scope.throwIfFailed();

            // Java 21 StructuredTaskScope.Subtask uses get(), not resultNow().
            return new OrderSummary(
                    orderId,
                    customerTask.get(),
                    pricingTask.get(),
                    statusTask.get()
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Order aggregation interrupted: " + orderId, e);
        } catch (java.util.concurrent.ExecutionException e) {
            throw new RuntimeException("Order aggregation failed: " + orderId, e);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 21 Virtual Threads & Structured Concurrency Demo ---\n");

        // PART A: Start one virtual thread and wait for it to finish.
        System.out.println("A. Basic Virtual Thread Execution:");
        Thread virtualThread = Thread.ofVirtual().start(() ->
                System.out.println("   -> Thread ID: " + Thread.currentThread().threadId()
                        + " | Is Virtual? " + Thread.currentThread().isVirtual())
        );

        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for virtual thread", e);
        }
        System.out.println();

        // PART B: One virtual thread per task, with thread-safe order storage.
        System.out.println("B. High-Throughput ExecutorService Simulation:");
        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 1; i <= 5; i++) {
                final int taskId = i;

                virtualExecutor.submit(() -> {
                    double updatedBalance = depositFunds(100.00);

                    String orderId = "ORDER-" + taskId;
                    orderStore.put(orderId, "PROCESSED");
                    String orderStatus = orderStore.get(orderId);

                    System.out.println("   -> Task " + taskId
                            + " | Order: " + orderId
                            + " | Status: " + orderStatus
                            + " | Balance after deposit: $" + updatedBalance);
                });
            }
        } // close() waits for executor tasks to complete.
        System.out.println("   -> Total stored orders: " + orderStore.size());
        System.out.println();

        // PART C: Concurrently aggregate customer, price, and status.
        System.out.println("C. Structured Concurrency Aggregation:");
        OrderSummary summary = aggregateOrderDetails("ORD-998877");
        System.out.println("   - Aggregated Summary Result:");
        System.out.println("     * Order ID:     " + summary.orderId());
        System.out.println("     * Customer:     " + summary.customerName());
        System.out.println("     * Total Amount: $" + summary.amount());
        System.out.println("     * Status:       " + summary.status());
    }
}

/*
 * Sample output (thread IDs and Part B ordering may differ):
 *
 * A. Basic Virtual Thread Execution:
 *    -> Thread ID: 13 | Is Virtual? true
 *
 * B. High-Throughput ExecutorService Simulation:
 *    -> Task 1 | Order: ORDER-1 | Status: PROCESSED | Balance after deposit: $600.0
 *    -> Task 2 | Order: ORDER-2 | Status: PROCESSED | Balance after deposit: $700.0
 *    -> Task 3 | Order: ORDER-3 | Status: PROCESSED | Balance after deposit: $800.0
 *    -> Task 4 | Order: ORDER-4 | Status: PROCESSED | Balance after deposit: $900.0
 *    -> Task 5 | Order: ORDER-5 | Status: PROCESSED | Balance after deposit: $1000.0
 *    -> Total stored orders: 5
 *
 * C. Structured Concurrency Aggregation:
 *    - Aggregated Summary Result:
 *      * Order ID:     ORD-998877
 *      * Customer:     Hrushikesh (Pune Hub)
 *      * Total Amount: $1250.75
 *      * Status:       CONFIRMED_VIA_VIRTUAL_THREAD
 *
 * For real money, use BigDecimal or integer minor units instead of double.
 */



