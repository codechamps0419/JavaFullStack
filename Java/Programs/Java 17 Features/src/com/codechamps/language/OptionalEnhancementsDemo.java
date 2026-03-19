/*
@author CodeChamps0419

Demonstrates Java 17 Optional Enhancements:
1. ifPresentOrElse() for fluent dual-branch handling of present and absent values.
2. or() for building clean fallback chains (e.g., Cache -> Database -> Default).
3. stream() for seamless integration of Optionals into Stream pipelines.
*/

package com.codechamps.language;

import java.util.List;
import java.util.Optional;

public class OptionalEnhancementsDemo {

    // Simulating tiered data sources
    public static Optional<String> getFromCache(String key) {
        return Optional.empty(); // Simulate cache miss
    }

    public static Optional<String> getFromDatabase(String key) {
        return Optional.of("Data-From-Database-For-" + key); // Simulate DB hit
    }

    public static void main(String[] args) {
        System.out.println("--- Java 17 Optional Enhancements Demo ---\n");

        // 1. ifPresentOrElse() DEMO
        System.out.println("1. ifPresentOrElse() Demonstration:");
        Optional<String> foundUser = Optional.of("Hrushikesh");
        Optional<String> missingUser = Optional.empty();

        foundUser.ifPresentOrElse(
            user -> System.out.println("   - Success: Found user -> " + user),
            () -> System.out.println("   - Warning: User not found!")
        );

        missingUser.ifPresentOrElse(
            user -> System.out.println("   - Success: Found user -> " + user),
            () -> System.out.println("   - Warning: User not found!")
        );
        System.out.println();

        // 2. or() FALLBACK CHAIN DEMO
        System.out.println("2. or() Fallback Chain Demonstration:");
        String searchKey = "Order-1001";
        
        String result = getFromCache(searchKey)
                .or(() -> getFromDatabase(searchKey))
                .or(() -> Optional.of("Default-Fallback-Data"))
                .get();

        System.out.println("   - Resolved Data via Fallback Chain: " + result);
        System.out.println();

        // 3. Optional.stream() INTEGRATION DEMO
        System.out.println("3. Optional.stream() Pipeline Integration:");
        List<Optional<String>> optionalList = List.of(
            Optional.of("Java 17"),
            Optional.empty(),
            Optional.of("Spring Boot 3"),
            Optional.empty(),
            Optional.of("Microservices")
        );

        List<String> flattenedValues = optionalList.stream()
                .flatMap(Optional::stream) // Converts Optional to Stream (drops empties automatically)
                .map(String::toUpperCase)
                .toList();

        System.out.println("   - Flattened and Filtered Values: " + flattenedValues);
    }
}

/*
Output:

--- Java 17 Optional Enhancements Demo ---

1. ifPresentOrElse() Demonstration:
   - Success: Found user -> Hrushikesh
   - Warning: User not found!

2. or() Fallback Chain Demonstration:
   - Resolved Data via Fallback Chain: Data-From-Database-For-Order-1001

3. Optional.stream() Pipeline Integration:
   - Flattened and Filtered Values: [JAVA 17, SPRING BOOT 3, MICROSERVICES]

*/