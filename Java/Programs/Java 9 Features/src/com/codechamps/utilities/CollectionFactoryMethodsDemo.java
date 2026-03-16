/*
@author CodeChamps0419

Demonstrates Java 9 Collection Factory Methods:
1. List.of() - Creates an immutable list
2. Set.of() - Creates an immutable set (disallows duplicates)
3. Map.of() - Creates an immutable map (supports up to 10 entries)
4. Map.ofEntries() - Creates an immutable map for larger datasets using Map.entry()
*/

package com.codechamps.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactoryMethodsDemo {

    static class Product {
        String name;
        double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 9 Collection Factory Methods Demonstrations ---\n");

        // 1. LIST.OF() DEMO
        // Creates a compact, immutable list. Throws NullPointerException if any element is null.
        List<String> fruits = List.of("Apple", "Banana", "Cherry");
        System.out.println("1. List.of() result: " + fruits);

        // Demonstrating immutability exception safeguard
        try {
            fruits.add("Date");
        } catch (UnsupportedOperationException e) {
            System.out.println("   - Modification safeguard caught: " + e.getClass().getSimpleName());
        }
        System.out.println();

        // 2. SET.OF() DEMO
        // Creates an immutable set. Automatically rejects duplicate values with IllegalArgumentException.
        Set<String> roles = Set.of("ADMIN", "USER", "GUEST");
        System.out.println("2. Set.of() result: " + roles);
        System.out.println("   - Contains 'ADMIN'? " + roles.contains("ADMIN"));
        System.out.println();

        // 3. MAP.OF() DEMO
        // Concise map initialization for up to 10 key-value pairs
        Map<String, Integer> inventory = Map.of(
            "Apple", 50,
            "Banana", 30,
            "Cherry", 20
        );
        System.out.println("3. Map.of() result:");
        inventory.forEach((item, qty) -> System.out.println("   - " + item + ": " + qty));
        System.out.println();

        // 4. MAP.OFENTRIES() DEMO
        // Scalable alternative for maps containing more than 10 entries or complex objects
        Map<String, Product> catalog = Map.ofEntries(
            Map.entry("P1", new Product("Laptop", 999.99)),
            Map.entry("P2", new Product("Mouse", 29.99)),
            Map.entry("P3", new Product("Keyboard", 79.99))
        );

        System.out.println("4. Map.ofEntries() result with custom objects:");
        catalog.forEach((code, prod) -> System.out.println("   - Code [" + code + "]: " + prod));
    }
}

/*
Output:

H:\Passionate Buddies\Java\Programs\Java 9 Features\src\com\codechamps\utilities>java CollectionFactoryMethodsDemo

--- Java 9 Collection Factory Methods Demonstrations ---

1. List.of() result: [Apple, Banana, Cherry]
   - Modification safeguard caught: UnsupportedOperationException

2. Set.of() result: [GUEST, ADMIN, USER]
   - Contains 'ADMIN'? true

3. Map.of() result:
   - Apple: 50
   - Cherry: 20
   - Banana: 30

4. Map.ofEntries() result with custom objects:
   - Code [P1]: Laptop ($999.99)
   - Code [P2]: Mouse ($29.99)
   - Code [P3]: Keyboard ($79.99)

*/