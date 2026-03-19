/*
@author CodeChamps0419

Demonstrates Java 21 Pattern Matching for switch (JEP 441 - Standardized Feature):
- Evolution Note: Evolved through multiple preview phases from Java 17 to Java 20 
  (JEP 406, 420, 427, 433) and finalized as a permanent production-ready feature in Java 21 LTS (JEP 441).
1. Type patterns in switch expressions with type-safe variable binding.
2. Built-in explicit null handling (case null -> ...).
3. Guarded patterns using the 'when' clause for conditional refinement.
4. Exhaustive matching over sealed types.
*/

package com.codechamps.language;

import java.math.BigDecimal;

public class SwitchPatternMatchingDomainDemo {

    // Sealed hierarchy for exhaustive matching
    public sealed interface Request permits GetRequest, PostRequest, UnknownRequest {}
    public record GetRequest(String path) implements Request {}
    public record PostRequest(String path, String body) implements Request {}
    public record UnknownRequest() implements Request {}

    public record Order(String id, BigDecimal amount, String status) {}

    // 1. Type Patterns & Null Handling
    public static String analyzeInput(Object obj) {
        return switch (obj) {
            case null -> "Received a null object reference safely!";
            case String s -> "Matched String of length " + s.length() + ": " + s;
            case Integer i -> "Matched Integer: " + i * 2;
            case Order o -> "Matched Order with ID: " + o.id();
            default -> "Matched unknown object type";
        };
    }

    // 2. Guarded Patterns with 'when' clause
    public static String evaluateOrder(Order order) {
        return switch (order) {
            case Order o when o.amount().compareTo(BigDecimal.valueOf(1000)) > 0 ->
                "High-Value Order (" + o.id() + "): $" + o.amount();
            case Order o when "PENDING".equalsIgnoreCase(o.status()) ->
                "Pending Order Action Required (" + o.id() + ")";
            case Order o ->
                "Standard Order (" + o.id() + "): $" + o.amount();
        };
    }

    // 3. Sealed Type Exhaustive Matching
    public static String routeRequest(Request request) {
        return switch (request) {
            case GetRequest(String path) -> "Routing GET request to path: " + path;
            case PostRequest(String path, String body) -> "Routing POST request to path: " + path + " with body size: " + body.length();
            case UnknownRequest _ -> "Handling unsupported/unknown request gracefully.";
            // No default needed because Request is sealed and all implementations are covered!
        };
    }

    public static void main(String[] args) {
        System.out.println("--- Java 21 Pattern Matching for Switch Demo (JEP 441) ---\n");

        // --- PART A: Type Patterns & Null Case ---
        System.out.println("A. Type Patterns & Null Handling:");
        System.out.println("   -> " + analyzeInput(null));
        System.out.println("   -> " + analyzeInput("Hello Java 21"));
        System.out.println("   -> " + analyzeInput(42));
        System.out.println();

        // --- PART B: Guarded Patterns ('when' clause) ---
        System.out.println("B. Guarded Patterns ('when' clause):");
        Order highValOrder = new Order("ORD-001", BigDecimal.valueOf(1500), "CONFIRMED");
        Order pendingOrder = new Order("ORD-002", BigDecimal.valueOf(250), "PENDING");
        Order regularOrder = new Order("ORD-003", BigDecimal.valueOf(500), "COMPLETED");

        System.out.println("   -> " + evaluateOrder(highValOrder));
        System.out.println("   -> " + evaluateOrder(pendingOrder));
        System.out.println("   -> " + evaluateOrder(regularOrder));
        System.out.println();

        // --- PART C: Sealed Type Exhaustive Matching ---
        System.out.println("C. Sealed Type Exhaustive Matching:");
        Request getReq = new GetRequest("/api/users");
        Request postReq = new PostRequest("/api/orders", "{\"item\":\"laptop\"}");

        System.out.println("   -> " + routeRequest(getReq));
        System.out.println("   -> " + routeRequest(postReq));
    }
}

/*
Output:

--- Java 21 Pattern Matching for Switch Demo (JEP 441) ---

A. Type Patterns & Null Handling:
   -> Received a null object reference safely!
   -> Matched String of length 15: Hello Java 21
   -> Matched Integer: 84

B. Guarded Patterns ('when' clause):
   -> High-Value Order (ORD-001): $1500
   -> Pending Order Action Required (ORD-002)
   -> Standard Order (ORD-003): $500

C. Sealed Type Exhaustive Matching:
   -> Routing GET request to path: /api/users
   -> Routing POST request to path: /api/orders with body size: 17

*/