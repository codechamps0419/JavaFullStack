/*
@author CodeChamps0419

Demonstrates Java 11 Predicate.not():
1. Using Predicate.not() with method references to cleanly filter out invalid or deleted items.
2. Combining multiple negations in Stream operations for enhanced readability.
*/

package com.codechamps.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateNotDemo {

    static class User {
        private final String name;
        private final boolean deleted;
        private final boolean blocked;

        User(String name, boolean deleted, boolean blocked) {
            this.name = name;
            this.deleted = deleted;
            this.blocked = blocked;
        }

        public String getName() {
            return name;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public boolean isBlocked() {
            return blocked;
        }

        @Override
        public String toString() {
            return "User{" + name + ", deleted=" + deleted + ", blocked=" + blocked + "}";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 11 Predicate.not() Demonstrations ---\n");

        List<String> rawNames = List.of("Hrushikesh", "   ", "Aprajita", "", "Anita", "\t");

        List<String> validNames = rawNames.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());

        System.out.println("1. Filtered Valid Names (ignoring blanks):");
        System.out.println("   - Result: " + validNames + "\n");

        List<User> users = List.of(
            new User("Rahul", false, false),
            new User("Aman", true, false),
            new User("Pooja", false, true),
            new User("Sneha", false, false)
        );

        List<User> activeUsers = users.stream()
                .filter(Predicate.not(User::isDeleted))
                .filter(Predicate.not(User::isBlocked))
                .collect(Collectors.toList());

        System.out.println("2. Filtered Active Users (not deleted & not blocked):");
        activeUsers.forEach(u -> System.out.println("   - " + u.getName()));
    }
}

/*
Output:

--- Java 11 Predicate.not() Demonstrations ---

1. Filtered Valid Names (ignoring blanks):
   - Result: [Hrushikesh, Aprajita, Anita]

2. Filtered Active Users (not deleted & not blocked):
   - Rahul
   - Sneha

*/
