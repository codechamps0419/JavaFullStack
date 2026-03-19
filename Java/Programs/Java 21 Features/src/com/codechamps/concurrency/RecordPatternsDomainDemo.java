/*
@author CodeChamps0419

Demonstrates Java 21 Record Patterns & Destructuring (JEP 440 - Standardized Feature):
- Evolution Note: Introduced as a brand-new feature in Java 19/20 (Previews: JEP 405/432) 
  and finalized/standardized as a permanent production-ready language feature in Java 21 LTS (JEP 440).
1. Unpacking record components directly within instanceof type checks.
2. Exhaustive switch expression matching over sealed interfaces using record patterns.
3. Nested record patterns for deep object graph inspection (e.g., Order -> Customer -> Address).
*/

package com.codechamps.language;

public class RecordPatternsDomainDemo {

    // 1. Sealed Interface & Records for Switch Pattern Matching
    public sealed interface ApiResponse permits Success, Error {}
    public record Success(int statusCode, String body) implements ApiResponse {}
    public record Error(int statusCode, String message) implements ApiResponse {}

    // 2. Nested Records for Deep Destructuring
    public record Address(String street, String city) {}
    public record Customer(String name, Address address) {}
    public record Order(String id, Customer customer, double amount) {}

    public static String processApiResponse(ApiResponse response) {
        return switch (response) {
            // Destructuring directly in switch cases
            case Success(int code, String body) -> "Success [" + code + "]: " + body;
            case Error(int code, String message) -> "Error [" + code + "]: " + message;
        };
    }

    public static String extractOrderDetails(Object obj) {
        // Nested record pattern matching with var and explicit types
        if (obj instanceof Order(var id, Customer(var name, Address(var street, String city)), var amount)) {
            return "Order " + id + " for " + name + " shipped to " + city + " (Total: $" + amount + ")";
        }
        return "Not a valid nested Order structure";
    }

    public static void main(String[] args) {
        System.out.println("--- Java 21 Record Patterns Demo (JEP 440) ---\n");

        // --- PART A: Simple instanceof Record Pattern ---
        System.out.println("A. Simple instanceof Record Pattern:");
        Object pointObj = new Point(10, 20);
        if (pointObj instanceof Point(int x, int y)) {
            System.out.println("   -> Destructured Point: x = " + x + ", y = " + y + ", Sum = " + (x + y));
        }
        System.out.println();

        // --- PART B: Sealed Interface & Switch Destructuring ---
        System.out.println("B. Sealed Interface Switch Pattern Matching:");
        ApiResponse successRes = new Success(200, "User profile loaded successfully.");
        ApiResponse errorRes = new Error(404, "Requested resource not found.");

        System.out.println("   -> " + processApiResponse(successRes));
        System.out.println("   -> " + processApiResponse(errorRes));
        System.out.println();

        // --- PART C: Nested Record Patterns ---
        System.out.println("C. Nested Record Patterns (Deep Unpacking):");
        Address address = new Address("MG Road", "Pune");
        Customer customer = new Customer("Hrushikesh", address);
        Order order = new Order("ORD-778899", customer, 1499.50);

        String result = extractOrderDetails(order);
        System.out.println("   -> " + result);
    }

    public record Point(int x, int y) {}
}

/*
Output:

--- Java 21 Record Patterns Demo (JEP 440) ---

A. Simple instanceof Record Pattern:
   -> Destructured Point: x = 10, y = 20, Sum = 30

B. Sealed Interface Switch Pattern Matching:
   -> Success [200]: User profile loaded successfully.
   -> Error [404]: Requested resource not found.

C. Nested Record Patterns (Deep Unpacking):
   -> Order ORD-778899 for Hrushikesh shipped to Pune (Total: $1499.5)

*/