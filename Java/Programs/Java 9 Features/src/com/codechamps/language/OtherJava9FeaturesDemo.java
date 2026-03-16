/*
@author CodeChamps0419

Demonstrates Java 9 Other Important Features:
1. Private Methods in Interfaces (for sharing internal helper logic)
2. Diamond Operator with Anonymous Classes (`new Comparator<>() { ... }`)
3. Process API Improvements (`ProcessHandle` for monitoring current and system processes)
4. Flow API (Reactive Streams standard with backpressure)
*/

package com.codechamps.language;

import java.lang.ProcessHandle;
import java.util.Comparator;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class OtherJava9FeaturesDemo {

    // 1. PRIVATE METHODS IN INTERFACES DEMO
    interface OrderValidator {
        default void validateCreateOrder(String orderId, double amount) {
            checkId(orderId);
            checkAmount(amount);
            System.out.println("   [OrderValidator] Create order passed validation.");
        }

        default void validateUpdateOrder(String orderId) {
            checkId(orderId);
            System.out.println("   [OrderValidator] Update order passed validation.");
        }

        // Private helper method to eliminate duplicate validation logic
        private void checkId(String orderId) {
            if (orderId == null || orderId.trim().isEmpty()) {
                throw new IllegalArgumentException("Order ID cannot be null or empty.");
            }
        }

        private void checkAmount(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Order amount must be greater than zero.");
            }
        }
    }

    static class OrderService implements OrderValidator {
        // Inherits default validation methods, but cannot access private checkId/checkAmount directly.
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--- Java 9 Other Important Features Demonstrations ---\n");

        // --- DEMO 1: Private Methods in Interfaces ---
        System.out.println("1. Private Methods in Interfaces:");
        OrderService orderService = new OrderService();
        orderService.validateCreateOrder("ORD-999", 1500.00);
        orderService.validateUpdateOrder("ORD-999");
        System.out.println();

        // --- DEMO 2: Diamond Operator with Anonymous Classes ---
        System.out.println("2. Diamond Operator with Anonymous Classes:");
        // Using <> with an anonymous class (illegal in Java 7/8)
        Comparator<String> caseInsensitiveComparator = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        };
        int compareResult = caseInsensitiveComparator.compare("java", "JAVA");
        System.out.println("   - Comparator comparison result: " + compareResult);
        System.out.println();

        // --- DEMO 3: Process API Improvements (ProcessHandle) ---
        System.out.println("3. Process API Improvements (ProcessHandle):");
        ProcessHandle currentProcess = ProcessHandle.current();
        ProcessHandle.Info processInfo = currentProcess.info();

        System.out.println("   - Current Process PID: " + currentProcess.pid());
        System.out.println("   - Process Command: " + processInfo.command().orElse("N/A"));
        System.out.println("   - Process User: " + processInfo.user().orElse("N/A"));
        System.out.println();

        // --- DEMO 4: Flow API (Reactive Streams with Backpressure) ---
        System.out.println("4. Flow API (Reactive Streams / Backpressure Demonstration):");
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        publisher.subscribe(new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("   [Subscriber] Subscribed! Requesting 1 item...");
                subscription.request(1); // Controlling demand (Backpressure)
            }

            @Override
            public void onNext(String item) {
                System.out.println("   [Subscriber] Received item: " + item);
                // Requesting next item one by one
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("   [Subscriber] All items processed successfully.");
            }
        });

        publisher.submit("Event-Payload-Alpha");
        publisher.submit("Event-Payload-Beta");
        publisher.close();

        // Give publisher asynchronous thread a moment to finish dispatching
        Thread.sleep(500);
    }
}

/*
Output:

H:\Passionate Buddies\Java\Programs\Java 9 Features\src\com\codechamps\language>java OtherJava9FeaturesDemo

--- Java 9 Other Important Features Demonstrations ---

1. Private Methods in Interfaces:
   [OrderValidator] Create order passed validation.
   [OrderValidator] Update order passed validation.

2. Diamond Operator with Anonymous Classes:
   - Comparator comparison result: 0

3. Process API Improvements (ProcessHandle):
   - Current Process PID: 18492
   - Process Command: C:\Program Files\Java\jdk-17\bin\java.exe
   - Process User: Hrushikesh

4. Flow API (Reactive Streams / Backpressure Demonstration):
   [Subscriber] Subscribed! Requesting 1 item...
   [Subscriber] Received item: Event-Payload-Alpha
   [Subscriber] Received item: Event-Payload-Beta
   [Subscriber] All items processed successfully.

*/