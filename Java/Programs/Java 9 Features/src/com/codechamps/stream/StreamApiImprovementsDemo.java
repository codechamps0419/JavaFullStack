/*
@author CodeChamps0419

Demonstrates Java 9 Stream API Improvements:
1. takeWhile() - Takes elements while condition is true (stops at first false)
2. dropWhile() - Skips elements while condition is true (starts keeping from first false)
3. iterate() with predicate - Generates finite streams with seed, predicate, and step operator
4. ofNullable() - Safely constructs zero-or-one-element streams from nullable values
*/

package com.codechamps.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiImprovementsDemo {

    static class Order {
        String id;
        double amount;

        Order(String id, double amount) {
            this.id = id;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Order(" + id + ", $" + amount + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 9 Stream API Improvements Demonstrations ---\n");

        // 1. TAKEWHILE DEMO
        // Takes elements sequentially until the predicate evaluates to false
        List<Order> sortedOrders = Arrays.asList(
            new Order("O1", 100.0),
            new Order("O2", 200.0),
            new Order("O3", 300.0),
            new Order("O4", 450.0),
            new Order("O5", 500.0)
        );

        List<Order> budgetOrders = sortedOrders.stream()
                .takeWhile(order -> order.amount < 350.0)
                .collect(Collectors.toList());

        System.out.println("1. takeWhile() (Orders under $350):");
        System.out.println("   Result: " + budgetOrders + "\n");

        // 2. DROPWHILE DEMO
        // Drops elements sequentially until the predicate evaluates to false, keeping all remaining
        List<String> logFileLines = Arrays.asList(
            "# Config Version: 2.1",
            "# Environment: Production",
            "# Last Updated: 2026-03-31",
            "INFO: Application server booted successfully",
            "DEBUG: Connection pool initialized with 20 connections",
            "ERROR: Failed to establish database handshake"
        );

        List<String> operationalLogs = logFileLines.stream()
                .dropWhile(line -> line.startsWith("#"))
                .collect(Collectors.toList());

        System.out.println("2. dropWhile() (Skipping header comments):");
        operationalLogs.forEach(line -> System.out.println("   - " + line));
        System.out.println();

        // 3. ITERATE WITH PREDICATE DEMO
        // Finite stream generation using (seed, predicate, hasNext)
        System.out.println("3A. iterate() with Predicate (Numbers 0 to 9):");
        List<Integer> numbers = Stream.iterate(0, n -> n < 10, n -> n + 1)
                .collect(Collectors.toList());
        System.out.println("   Result: " + numbers);

        System.out.println("3B. iterate() with Predicate (Fibonacci under 100):");
        List<Integer> fibonacci = Stream.iterate(
                    new int[]{0, 1},
                    pair -> pair[0] < 100,
                    pair -> new int[]{pair[1], pair[0] + pair[1]}
                )
                .map(pair -> pair[0])
                .collect(Collectors.toList());
        System.out.println("   Result: " + fibonacci + "\n");

        // 4. OFNULLABLE DEMO
        // Wraps nullable objects safely into zero-or-one-element streams without explicit null checks
        System.out.println("4. ofNullable() (Null-safe configuration processing):");

        String nullConfig = null;
        String activeConfig = "database.url=jdbc:postgresql://localhost:5432/prod";

        long nullStreamCount = Stream.ofNullable(nullConfig).count();
        System.out.println("   - Null value stream element count: " + nullStreamCount);

        System.out.print("   - Non-null value stream output: ");
        Stream.ofNullable(activeConfig).forEach(System.out::println);

        // Practical array processing with ofNullable + flatMap
        String[] mixedConfigs = {"timeout=30", null, "retries=3", null, "debug=true"};
        List<String> validConfigs = Stream.of(mixedConfigs)
                .flatMap(Stream::ofNullable)
                .collect(Collectors.toList());

        System.out.println("   - Filtered non-null configurations: " + validConfigs);
    }
}

/*
Output:

H:\Passionate Buddies\Java\Programs\Java 9 Features\src\com\codechamps\language>java StreamApiImprovementsDemo

--- Java 9 Stream API Improvements Demonstrations ---

1. takeWhile() (Orders under $350):
   Result: [Order(O1, $100.0), Order(O2, $200.0), Order(O3, $300.0)]

2. dropWhile() (Skipping header comments):
   - INFO: Application server booted successfully
   - DEBUG: Connection pool initialized with 20 connections
   - ERROR: Failed to establish database handshake

3A. iterate() with Predicate (Numbers 0 to 9):
   Result: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

3B. iterate() with Predicate (Fibonacci under 100):
   Result: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]

4. ofNullable() (Null-safe configuration processing):
   - Null value stream element count: 0
   - Non-null value stream output: database.url=jdbc:postgresql://localhost:5432/prod
   - Filtered non-null configurations: [timeout=30, retries=3, debug=true]

*/