/*
@author CodeChamps0419

Demonstrates the Evolution of Java Stream API Enhancements (Java 11 vs Java 17):
1. Highlights Java 11 Stream limitations (Collectors.toList() returning mutable ArrayList).
2. Highlights Java 17 Stream enhancements (.toList() returning unmodifiable lists, mapMulti()).
3. Showcases Collectors.teeing() introduced in Java 12 for dual-collector processing in a single pass.
*/

package com.codechamps.stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamEvolutionDemo.java {

    public static void main(String[] args) {
        System.out.println("--- Java 11 vs Java 17 Stream API Enhancements ---\n");

        List<String> rawNames = List.of("Hrushikesh", "   ", "Aprajita", "", "Anita");

        // --- JAVA 11 APPROACH ---
        // In Java 11, collecting to a list required Collectors.toList() which returned a mutable list.
        List<String> java11List = rawNames.stream()
                .filter(name -> !name.isBlank())
                .collect(Collectors.toList());

        System.out.println("1. Java 11 Collector Result (Mutable):");
        System.out.println("   - " + java11List);
        
        try {
            java11List.add("NewUser"); // Mutable list allows addition
            System.out.println("   - Modified successfully (mutable).");
        } catch (UnsupportedOperationException e) {
            // Will not trigger in Java 11 collectors.toList()
        }
        System.out.println();

        // --- JAVA 17 APPROACH ---
        // In Java 17+, .toList() directly produces an immutable/unmodifiable list.
        List<String> java17ImmutableList = rawNames.stream()
                .filter(name -> !name.isBlank())
                .map(String::strip)
                .toList(); // Java 16+ direct terminal operation

        System.out.println("2. Java 17 .toList() Result (Immutable):");
        System.out.println("   - " + java17ImmutableList);

        try {
            java17ImmutableList.add("UnauthorizedAdd");
        } catch (UnsupportedOperationException e) {
            System.out.println("   - Attempted modification blocked: UnsupportedOperationException (Immutable by design!).");
        }
        System.out.println();

        // --- JAVA 17 mapMulti() DEMO ---
        // mapMulti replaces complex flatMap patterns for conditional one-to-many filtering & expansion.
        System.out.println("3. Java 17 mapMulti() Demonstration:");
        List<String> expandedTags = List.of("Java11", "Java17", "Spring").stream()
                .mapMulti((tag, consumer) -> {
                    consumer.accept(tag.toUpperCase());
                    if (tag.contains("17")) {
                        consumer.accept("MODERN-JAVA");
                    }
                })
                .toList();

        System.out.println("   - Expanded Tags: " + expandedTags);
        System.out.println();

        // --- JAVA 12+ teeing() DEMO (Carried into Java 17) ---
        // Collectors.teeing() was introduced in Java 12. It allows running two separate collectors 
        // on a single stream pipeline simultaneously and merging their results using a BiFunction.
        System.out.println("4. Java 12+ Collectors.teeing() Demonstration (Single-Pass Dual Collection):");
        
        List<Integer> nameLengths = rawNames.stream()
                .filter(name -> !name.isBlank())
                .map(String::strip)
                .map(String::length)
                .toList();

        String teeingResult = nameLengths.stream()
                .collect(Collectors.teeing(
                    Collectors.summingInt(Integer::intValue), // Collector 1: Calculate total sum of characters
                    Collectors.counting(),                    // Collector 2: Count total valid entries
                    (sum, count) -> "Total Characters: " + sum + " | Count: " + count + " | Average Length: " + ((double) sum / count)
                ));

        System.out.println("   - Teeing Result: " + teeingResult);
    }
}

/*
Output:

--- Java 11 vs Java 17 Stream API Enhancements ---

1. Java 11 Collector Result (Mutable):
   - [Hrushikesh, Aprajita, Anita]
   - Modified successfully (mutable).

2. Java 17 .toList() Result (Immutable):
   - [Hrushikesh, Aprajita, Anita]
   - Attempted modification blocked: UnsupportedOperationException (Immutable by design!).

3. Java 17 mapMulti() Demonstration:
   - Expanded Tags: [JAVA11, JAVA17, MODERN-JAVA, SPRING]

4. Java 12+ Collectors.teeing() Demonstration (Single-Pass Dual Collection):
   - Teeing Result: Total Characters: 27 | Count: 3 | Average Length: 9.0

*/