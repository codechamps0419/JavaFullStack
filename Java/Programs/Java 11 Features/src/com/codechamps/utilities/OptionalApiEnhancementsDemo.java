/*
@author CodeChamps0419

Demonstrates Java 11 Optional API Enhancements:
1. isEmpty() - Checks if Optional is empty (cleaner inverse of isPresent())
2. ifPresentOrElse() - Executes action if present, or alternative runnable if empty
3. or() - Provides lazy fallback to an alternative Optional
4. stream() - Converts an Optional into a zero-or-one-element Stream for stream integration
*/

package com.codechamps.utilities;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalApiEnhancementsDemo {

    static class User {
        private final String id;
        private final String email;

        User(String id, String email) {
            this.id = id;
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 11 Optional API Enhancements Demonstrations ---\n");

        // 1. ISEMPTY() DEMO
        Optional<String> emptyOptional = Optional.empty();
        Optional<String> filledOptional = Optional.of("Production Config");

        System.out.println("1. isEmpty() Demonstration:");
        System.out.println("   - emptyOptional.isEmpty(): " + emptyOptional.isEmpty());
        System.out.println("   - filledOptional.isEmpty(): " + filledOptional.isEmpty());
        System.out.println();

        // 2. IFPRESENTORELSE() DEMO
        System.out.println("2. ifPresentOrElse() Demonstration:");
        System.out.print("   - Present case: ");
        filledOptional.ifPresentOrElse(
            val -> System.out.println("Found -> " + val),
            () -> System.out.println("Value not found")
        );

        System.out.print("   - Empty case: ");
        emptyOptional.ifPresentOrElse(
            val -> System.out.println("Found -> " + val),
            () -> System.out.println("Default handling triggered (Empty Optional)")
        );
        System.out.println();

        // 3. OR() FALLBACK DEMO
        System.out.println("3. or() Fallback Demonstration:");
        Optional<String> primaryConfig = Optional.empty();
        Optional<String> resolvedConfig = primaryConfig.or(() -> Optional.of("Fallback-Config-Default"));
        System.out.println("   - Resolved fallback config: " + resolvedConfig.orElse("None"));
        System.out.println();

        // 4. STREAM() & FLATMAP DEMO
        System.out.println("4. stream() Pipeline Integration Demonstration:");
        List<Optional<User>> userOptionals = List.of(
            Optional.of(new User("U1", "user1@example.com")),
            Optional.empty(),
            Optional.of(new User("U3", "user3@example.com"))
        );

        List<String> validEmails = userOptionals.stream()
                .flatMap(Optional::stream)
                .map(User::getEmail)
                .collect(Collectors.toList());

        System.out.println("   - Extracted valid emails: " + validEmails);
    }
}

/*
Output:

--- Java 11 Optional API Enhancements Demonstrations ---

1. isEmpty() Demonstration:
   - emptyOptional.isEmpty(): true
   - filledOptional.isEmpty(): false

2. ifPresentOrElse() Demonstration:
   - Present case: Found -> Production Config
   - Empty case: Default handling triggered (Empty Optional)

3. or() Fallback Demonstration:
   - Resolved fallback config: Fallback-Config-Default

4. stream() Pipeline Integration Demonstration:
   - Extracted valid emails: [user1@example.com, user3@example.com]

*/
