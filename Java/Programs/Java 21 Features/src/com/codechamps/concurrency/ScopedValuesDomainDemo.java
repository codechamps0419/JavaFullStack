/*
@author CodeChamps0419

Demonstrates Java 21 Scoped Values vs ThreadLocal Context Propagation:
1. Highlights legacy ThreadLocal usage with mutable state and manual cleanup requirements.
2. Demonstrates modern Java 21 ScopedValue usage offering immutability, thread safety, and automatic scope cleanup.

* NOTE:
* StructuredTaskScope is a preview API in Java 21.
* Compile and run with --enable-preview using JDK 21.
* 
* Structured Concurrency was introduced as a preview API in Java 21 through JEP 453. 
* It provides a structured way to manage related concurrent tasks, including their lifecycle, cancellation and
* exception handling. 
* We can use it by enabling preview features during compilation and execution. However, since preview APIs 
* may change between Java releases, I would avoid introducing them into critical production systems 
* without evaluating compatibility and organizational policies.
* For production applications running Java 21, I would generally prefer stable APIs such as Virtual Threads 
* with ExecutorService or CompletableFuture, depending on the requirement.
*/

package com.codechamps.concurrency;

public class ScopedValuesDomainDemo {

    // ==========================================
    // 1. LEGACY APPROACH: ThreadLocal Context
    // ==========================================
    public static class LegacyRequestContext {
        private static final ThreadLocal<String> currentUserThreadLocal = new ThreadLocal<>();

        public static void setUser(String user) {
            currentUserThreadLocal.set(user);
        }

        public static String getUser() {
            return currentUserThreadLocal.get();
        }

        public static void clear() {
            currentUserThreadLocal.remove(); // Required manually to avoid severe memory leaks!
        }
    }

    // ==========================================
    // 2. MODERN APPROACH: Java 21 ScopedValue
    // ==========================================
    // ScopedValues are immutable, bound to a specific execution scope, and automatically cleared.
    public static final ScopedValue<String> CURRENT_USER_SCOPE = ScopedValue.newInstance();

    public static void processOrderWithScopedValue() {
        // Automatically reads from the active scope established by ScopedValue.where(...)
        String user = CURRENT_USER_SCOPE.get();
        System.out.println("   -> [ScopedValue] Successfully accessed context for user: " + user);
    }

    public static void main(String[] args) {
        System.out.println("--- Java 21 Scoped Values vs ThreadLocal Demo ---\n");

        // --- OLD WAY (ThreadLocal) ---
        System.out.println("1. Legacy ThreadLocal Execution:");
        try {
            LegacyRequestContext.setUser("Hrushikesh (Platform Thread Context)");
            System.out.println("   -> [ThreadLocal] Current User: " + LegacyRequestContext.getUser());
        } finally {
            // If developer forgets this cleanup block, thread pools retain references causing memory leaks
            LegacyRequestContext.clear();
            System.out.println("   -> [ThreadLocal] Cleaned up manually.");
        }
        System.out.println();

        // --- NEW WAY (Java 21 Scoped Values) ---
        System.out.println("2. Modern Java 21 ScopedValue Execution:");
        
        // ScopedValue.where(...).run(...) binds the value immutably for the duration of the lambda block
        ScopedValue.where(CURRENT_USER_SCOPE, "Hrushikesh (Virtual Thread / Scoped Context)").run(() -> {
            processOrderWithScopedValue();
        });

        // Outside the block, the scope is gone. Attempting to call CURRENT_USER_SCOPE.get() here would throw NoSuchElementException.
        System.out.println("   -> [ScopedValue] Scope exited cleanly. No manual remove() required!");
    }
}

/*
Output:

--- Java 21 Scoped Values vs ThreadLocal Demo ---

1. Legacy ThreadLocal Execution:
   -> [ThreadLocal] Current User: Hrushikesh (Platform Thread Context)
   -> [ThreadLocal] Cleaned up manually.

2. Modern Java 21 ScopedValue Execution:
   -> [ScopedValue] Successfully accessed context for user: Hrushikesh (Virtual Thread / Scoped Context)
   -> [ScopedValue] Scope exited cleanly. No manual remove() required!

*/