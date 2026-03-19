/*
@author CodeChamps0419

Demonstrates Java 17 Switch Expressions:
1. Replaces verbose switch statements with concise expression semantics returning values directly.
2. Utilizes arrow syntax (->) to eliminate fall-through bugs and break statements.
3. Showcases multi-statement blocks using the 'yield' keyword.
*/

package com.codechamps.language;

public class SwitchExpressionsDemo {

    public enum OrderStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }

    // 1. SIMPLE SWITCH EXPRESSION USING ARROW SYNTAX (->)
    public static String getOrderStatusMessage(OrderStatus status) {
        return switch (status) {
            case PENDING    -> "Order is being processed";
            case CONFIRMED  -> "Order has been confirmed";
            case SHIPPED    -> "Order is on its way";
            case DELIVERED  -> "Order has been delivered";
            case CANCELLED  -> "Order has been cancelled";
        }; // Notice: No default branch needed because all enum values are covered (Exhaustiveness)
    }

    // 2. MULTI-STATEMENT SWITCH EXPRESSION USING yield
    public static double calculateShippingCost(String method, double weight) {
        return switch (method.toUpperCase()) {
            case "STANDARD" -> {
                System.out.println("   -> Applying standard base rate");
                yield 5.0 + (weight * 0.5);
            }
            case "EXPRESS" -> {
                System.out.println("   -> Applying express priority rate");
                yield 15.0 + (weight * 1.2);
            }
            case "OVERNIGHT" -> {
                System.out.println("   -> Applying overnight surcharge");
                yield 25.0 + (weight * 2.0);
            }
            default -> throw new IllegalArgumentException("Unknown shipping method: " + method);
        };
    }

    public static void main(String[] args) {
        System.out.println("--- Java 17 Switch Expressions Demo ---\n");

        // Test 1: Enum-based exhaustive switch expression
        System.out.println("1. Order Status Messages:");
        System.out.println("   - PENDING:   " + getOrderStatusMessage(OrderStatus.PENDING));
        System.out.println("   - SHIPPED:   " + getOrderStatusMessage(OrderStatus.SHIPPED));
        System.out.println();

        // Test 2: Multi-statement block with yield
        System.out.println("2. Shipping Cost Calculations:");
        double standardCost = calculateShippingCost("STANDARD", 4.0);
        System.out.println("   - Total Cost: $" + standardCost + "\n");

        double expressCost = calculateShippingCost("EXPRESS", 2.5);
        System.out.println("   - Total Cost: $" + expressCost);
    }
}

/*
Output:

--- Java 17 Switch Expressions Demo ---

1. Order Status Messages:
   - PENDING:   Order is being processed
   - SHIPPED:   Order is on its way

2. Shipping Cost Calculations:
   -> Applying standard base rate
   - Total Cost: $7.0

   -> Applying express priority rate
   - Total Cost: $18.0

*/