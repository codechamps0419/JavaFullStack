package com.codechamps.datastream;

/*
@author CodeChamps0419

Demonstrates Java 8 Spliterator interface and its core methods.
Classification: New Feature (core traversal and splitting engine underlying parallel Streams).
What is a Spliterator?: "Split-iterator" combines traversal (like a traditional Iterator) with splitting capabilities. 
It breaks a data source into parts so that multiple threads can process elements in parallel (used extensively behind the scenes by Java 8 Streams).
*/

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorDemo {
    public static void main(String[] args) {
        System.out.println("--- Java 8 Spliterator Demonstrations ---\n");

        List<String> frameworks = Arrays.asList("Spring", "Hibernate", "Quarkus", "Micronaut", "Struts", "JSF");
        Spliterator<String> spliterator = frameworks.spliterator();

        System.out.println("1A. Estimated size: " + spliterator.estimateSize());
        System.out.println("1B. Characteristics code: " + spliterator.characteristics());
        System.out.println("   - Is ORDERED? " + spliterator.hasCharacteristics(Spliterator.ORDERED));
        System.out.println("   - Is SIZED? " + spliterator.hasCharacteristics(Spliterator.SIZED) + "\n");

        System.out.print("2. tryAdvance traversal (single element): ");
        boolean hasMore = spliterator.tryAdvance(item -> System.out.print(item));
        System.out.println("\n   - Was element processed? " + hasMore + "\n");

        List<String> techList = Arrays.asList("Java", "Python", "Go", "Rust", "C++", "JavaScript");
        Spliterator<String> originalSpliterator = techList.spliterator();
        
        Spliterator<String> splitPart = originalSpliterator.trySplit();

        System.out.println("3. After trySplit() execution:");
        if (splitPart != null) {
            System.out.print("   - Split-off part elements: ");
            splitPart.forEachRemaining(item -> System.out.print(item + " "));
            System.out.println();
        }
        
        System.out.print("   - Remaining original part elements: ");
        originalSpliterator.forEachRemaining(item -> System.out.print(item + " "));
        System.out.println();
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.datastream.SpliteratorDemo

--- Java 8 Spliterator Demonstrations ---

1A. Estimated size: 6
1B. Characteristics code: 16464
   - Is ORDERED? true
   - Is SIZED? true

2. tryAdvance traversal (single element): Spring
   - Was element processed? true

3. After trySplit() execution:
   - Split-off part elements: Java Python Go 
   - Remaining original part elements: Rust C++ JavaScript

*/
