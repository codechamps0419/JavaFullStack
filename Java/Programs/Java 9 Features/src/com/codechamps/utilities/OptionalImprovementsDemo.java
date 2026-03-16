/*
@author CodeChamps0419

Demonstrates Java 9 Optional Improvements:
1. ifPresentOrElse() - Handles both present and empty outcomes
2. or() - Provides lazy fallback to another Optional
3. stream() - Converts Optional into a zero-or-one-element Stream for stream pipeline integration
*/

package com.codechamps.utilities;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalImprovementsDemo {

    static class User {
        String id;
        String name;

        User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{id='" + id + "', name='" + name + "'}";
        }
    }

    // Mock data source handlers for fallback demonstration
    static Optional<User> findInCache(String id) {
        System.out.println("   [Cache Lookup] Checking cache for ID: " + id);
        return Optional.empty(); // Simulate cache miss
    }

    static Optional<User> findInDatabase(String id) {
        System.out.println("   [Database Lookup] Checking database for ID: " + id);
        return Optional.of(new User(id, "Rahul Sharma")); // Simulate database hit
    }

    static Optional<User> findUserMock(String id) {
        if ("U404".equals(id)) {
            return Optional.empty();
        }
        return Optional.of(new User(id, "User-" + id));
    }

    public static void main(String[] args) {
        System.out.println("--- Java 9 Optional Improvements Demonstrations ---\n");

        // 1. IFPRESENTORELSE() DEMO
        // Executes the consumer action if present, or the runnable action if empty.
        System.out.println("1. ifPresentOrElse() Demonstration:");
        Optional<String> activeUser = Optional.of("Appu");
        Optional<String> missingUser = Optional.empty();

        System.out.print("   - Present Optional: ");
        activeUser.ifPresentOrElse(
            name -> System.out.println("Welcome, " + name),
            () -> System.out.println("User not found")
        );

        System.out.print("   - Empty Optional: ");
        missingUser.ifPresentOrElse(
            name -> System.out.println("Welcome, " + name),
            () -> System.out.println("User not found")
        );
        System.out.println();

        // 2. OR() LAZY FALLBACK DEMO
        // Chains Optional lookups lazily. Database fallback only triggers if cache is empty.
        System.out.println("2. or() Lazy Fallback Chain Demonstration:");
        Optional<User> resolvedUser = findInCache("U101")
                .or(() -> findInDatabase("U101"));
        
        System.out.println("   - Resolved user result: " + resolvedUser.orElse(null) + "\n");

        // 3. STREAM() & FLATMAP DEMO
        // Converts Optionals to Streams to cleanly filter out empty results in bulk processing pipelines.
        System.out.println("3. stream() & flatMap() Demonstration:");
        List<String> userIds = List.of("U1", "U404", "U3");

        List<String> validUserNames = userIds.stream()
                .map(OptionalImprovementsDemo::findUserMock) // Stream<Optional<User>>
                .flatMap(Optional::stream)                  // Stream<User> (drops empty Optionals automatically)
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("   - Input IDs: " + userIds);
        System.out.println("   - Filtered and mapped names: " + validUserNames);
    }
}

/*
Output:

H:\Passionate Buddies\Java\Programs\Java 9 Features\src\com\codechamps\utilities>java OptionalImprovementsDemo

--- Java 9 Optional Improvements Demonstrations ---

1. ifPresentOrElse() Demonstration:
   - Present Optional: Welcome, Appu
   - Empty Optional: User not found

2. or() Lazy Fallback Chain Demonstration:
   [Cache Lookup] Checking cache for ID: U101
   [Database Lookup] Checking database for ID: U101
   - Resolved user result: User{id='U101', name='Rahul Sharma'}

3. stream() & flatMap() Demonstration:
   - Input IDs: [U1, U404, U3]
   - Filtered and mapped names: [User-U1, User-U3]

*/