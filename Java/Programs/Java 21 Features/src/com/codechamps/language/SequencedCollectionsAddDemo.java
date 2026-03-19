/*
@author CodeChamps0419

Demonstrates Java 21 Sequenced Collections (JEP 431) Insertion / Add Methods:
1. SequencedCollection add methods (`addFirst`, `addLast`) on Lists and LinkedHashSets.
2. SequencedMap put methods (`putFirst`, `putLast`) on LinkedHashMaps.
3. Accessing elements using `.getFirst()`, `.getLast()`, and viewing collections in reverse via `.reversed()`.
*/

package com.codechamps.language;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

public class SequencedCollectionsAddDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 21 Sequenced Collections Add/Put Methods Demo (JEP 431) ---\n");

        // 1. SequencedCollection addFirst() and addLast() using ArrayList
        System.out.println("1. ArrayList addFirst() and addLast():");
        SequencedCollection<String> fruitList = new ArrayList<>();
        fruitList.add("banana");
        
        // Add uniformly to the beginning and end
        fruitList.addFirst("apple");
        fruitList.addLast("cherry");
        
        System.out.println("   - List after additions: " + fruitList);
        System.out.println("   - First Element: " + fruitList.getFirst());
        System.out.println("   - Last Element: " + fruitList.getLast());
        System.out.println();

        // 2. SequencedSet addFirst() and addLast() using LinkedHashSet
        System.out.println("2. LinkedHashSet addFirst() and addLast():");
        SequencedSet<String> uniqueTags = new LinkedHashSet<>();
        uniqueTags.add("backend");
        
        uniqueTags.addFirst("java-21");
        uniqueTags.addLast("cloud");
        
        System.out.println("   - LinkedHashSet after additions: " + uniqueTags);
        System.out.println("   - First Tag: " + uniqueTags.getFirst());
        System.out.println("   - Last Tag: " + uniqueTags.getLast());
        
        // Reversed view demonstration
        SequencedSet<String> reversedTags = uniqueTags.reversed();
        System.out.println("   - Reversed View: " + reversedTags);
        System.out.println();

        // 3. SequencedMap putFirst() and putLast() using LinkedHashMap
        System.out.println("3. LinkedHashMap putFirst() and putLast():");
        SequencedMap<Integer, String> statusMap = new LinkedHashMap<>();
        statusMap.put(200, "OK");
        
        // Insert or move entries to the first and last position in encounter order
        statusMap.putFirst(100, "Continue");
        statusMap.putLast(500, "Internal Server Error");
        
        System.out.println("   - SequencedMap entries: " + statusMap);
        System.out.println("   - First Entry: " + statusMap.firstEntry());
        System.out.println("   - Last Entry: " + statusMap.lastEntry());
        
        // Reversed map view
        SequencedMap<Integer, String> reversedMap = statusMap.reversed();
        System.out.println("   - Reversed Map View: " + reversedMap);
    }
}

/*
Output:

--- Java 21 Sequenced Collections Add/Put Methods Demo (JEP 431) ---

1. ArrayList addFirst() and addLast():
   - List after additions: [apple, banana, cherry]
   - First Element: apple
   - Last Element: cherry

2. LinkedHashSet addFirst() and addLast():
   - LinkedHashSet after additions: [java-21, backend, cloud]
   - First Tag: java-21
   - Last Tag: cloud
   - Reversed View: [cloud, backend, java-21]

3. LinkedHashMap putFirst() and putLast():
   - SequencedMap entries: {100=Continue, 200=OK, 500=Internal Server Error}
   - First Entry: 100=Continue
   - Last Entry: 500=Internal Server Error
   - Reversed Map View: {500=Internal Server Error, 200=OK, 100=Continue}

*/