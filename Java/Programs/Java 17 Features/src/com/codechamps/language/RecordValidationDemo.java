/*
@author CodeChamps0419

Demonstrates Java 17 Records - Modern Data Classes:
1. Defines an immutable record with a compact constructor for input validation.
2. Demonstrates derived properties, instance methods, and canonical mapping.
*/

package com.codechamps.language;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class RecordValidationDemo {

    // 1. DEFINE A RECORD WITH COMPACT CONSTRUCTOR VALIDATION
    public record OrderRequest(
        String orderId,
        String customerId,
        BigDecimal amount,
        LocalDateTime createdAt
    ) {
        // Compact Constructor for validation (no parameter declarations needed)
        public OrderRequest {
            Objects.requireNonNull(orderId, "Order ID cannot be null");
            Objects.requireNonNull(customerId, "Customer ID cannot be null");
            Objects.requireNonNull(amount, "Amount cannot be null");
            
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be positive and greater than zero.");
            }
        }

        // Derived property / business method
        public boolean isHighValueOrder() {
            return amount.compareTo(new BigDecimal("1000.00")) > 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 17 Records Demonstration ---\n");

        // 2. INSTANTIATING AND USING THE RECORD
        OrderRequest order = new OrderRequest(
            "ORD-9988",
            "CUST-101",
            new BigDecimal("1250.50"),
            LocalDateTime.now()
        );

        System.out.println("1. Record Generated toString():");
        System.out.println("   " + order);
        System.out.println();

        System.out.println("2. Accessor Methods (Note lack of 'get' prefix):");
        System.out.println("   - Order ID:    " + order.orderId());
        System.out.println("   - Customer ID: " + order.customerId());
        System.out.println("   - Amount:      " + order.amount());
        System.out.println("   - Created At:  " + order.createdAt());
        System.out.println();

        System.out.println("3. Custom Business Method Execution:");
        System.out.println("   - Is High Value Order (> 1000)? " + order.isHighValueOrder());
        System.out.println();

        // 3. VALIDATION DEMO
        System.out.println("4. Compact Constructor Validation Test:");
        try {
            // Attempting to pass an invalid amount will trigger the compact constructor check
            new OrderRequest("ORD-BAD", "CUST-102", new BigDecimal("-50.00"), LocalDateTime.now());
        } catch (IllegalArgumentException e) {
            System.out.println("   - Caught expected validation error: " + e.getMessage());
        }
    }
}