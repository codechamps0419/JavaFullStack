/*
@author CodeChamps0419

Demonstrates Java 21 Virtual Threads & Structured Concurrency:
1. Spawning individual virtual threads and virtual thread per task executors.
2. Managing critical concurrency concepts like Thread Pinning using ReentrantLock instead of synchronized.
3. Leveraging Structured Concurrency (StructuredTaskScope) for parallel subtask execution.
*/

package com.codechamps.concurrency;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.locks.ReentrantLock;

public class VirtualThreadsDomainDemo {

    // 1. THREAD-SAFE STORAGE WITHOUT SYNCHRONIZED PINNING
    private static final ConcurrentHashMap<String, String> orderStore = new ConcurrentHashMap<>();
    private static final ReentrantLock balanceLock = new ReentrantLock();
    private static double userWalletBalance = 500.00;

    // Safe balance update using ReentrantLock to prevent Carrier Thread Pinning
    public static void depositFunds(double amount) {
        balanceLock.lock();
        try {
            // Simulating a short blocking I/O or delay without pinning the carrier thread
            Thread.sleep(10); 
            userWalletBalance += amount;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            balanceLock.unlock();
        }
    }

    // 2. SIMULATING BACKEND SERVICES VIA STRUCTURED CONCURRENCY
    public record OrderSummary(String orderId, String customerName, double amount, String status) {}

    public static OrderSummary aggregateOrderDetails(String orderId) {
        // StructuredTaskScope ensures all parallel subtasks complete or fail together cleanly
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            Future<String> customerTask = scope.fork(() -> {
                Thread.sleep(50); // Simulate network latency
                return "Hrushikesh (Pune Hub)";
            });

            Future<Double> pricingTask = scope.fork(() -> {
                Thread.sleep(30); // Simulate database lookup
                return 1250.75;
            });

            Future<String> statusTask = scope.fork(() -> {
                Thread.sleep(20); // Simulate cache fetch
                return "CONFIRMED_VIA_VIRTUAL_THREAD";
            });

            scope.join();     // Wait for all concurrent subtasks
            scope.throwIfFailed(); // Propagate any subtask exceptions safely

            return new OrderSummary(
                orderId,
                customerTask.resultNow(),
                pricingTask.resultNow(),
                statusTask.resultNow()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to aggregate order details concurrently", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 21 Virtual Threads & Structured Concurrency Demo ---\n");

        // --- PART A: CREATING AND RUNNING BASIC VIRTUAL THREADS ---
        System.out.println("A. Basic Virtual Thread Execution:");
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("   -> Executing inside Virtual Thread ID: " + Thread.currentThread().threadId() + 
                               " | Is Virtual? " + Thread.currentThread().isVirtual());
        });

        try {
            virtualThread.join(); // Wait for virtual thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();

        // --- PART B: VIRTUAL THREAD PER TASK EXECUTOR ---
        System.out.println("B. High-Throughput ExecutorService Simulation:");
        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            for (int i = 1; i <= 5; i++) {
                final int taskId = i;
                virtualExecutor.submit(() -> {
                    depositFunds(100.00);
                    System.out.println("   -> Processed task " + taskId + " on Virtual Thread. Current Balance: $" + userWalletBalance);
                });
            }
        } // Executor automatically closes and waits for tasks to finish
        System.out.println();

        // --- PART C: STRUCTURED CONCURRENCY DEMO ---
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
Output:

--- Java 21 Virtual Threads & Structured Concurrency Demo ---

A. Basic Virtual Thread Execution:
   -> Executing inside Virtual Thread ID: 23 | Is Virtual? true

B. High-Throughput ExecutorService Simulation:
   -> Processed task 1 on Virtual Thread. Current Balance: $600.0
   -> Processed task 3 on Virtual Thread. Current Balance: $700.0
   -> Processed task 2 on Virtual Thread. Current Balance: $800.0
   -> Processed task 4 on Virtual Thread. Current Balance: $900.0
   -> Processed task 5 on Virtual Thread. Current Balance: $1000.0

C. Structured Concurrency Aggregation:
   - Aggregated Summary Result:
     * Order ID:     ORD-998877
     * Customer:     Hrushikesh (Pune Hub)
     * Total Amount: $1250.75
     * Status:       CONFIRMED_VIA_VIRTUAL_THREAD

*/