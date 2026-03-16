/*
@author CodeChamps0419

Demonstrates Java 11 Nest-based Access Control:
1. Direct private member access between outer and nested classes without synthetic bridge methods.
2. Runtime inspection of nest relationships using reflection (getNestHost, isNestmateOf, getNestMembers).
*/

package com.codechamps.language;

public class NestAccessDemo {

    private static final String SECRET_KEY = "Enterprise-Nest-Secret-2026";

    static class OrderValidatorNestMember {
        public void validateAndAccess() {
            System.out.println("   - Nested member accessed private field directly: " + SECRET_KEY);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 11 Nest-based Access Control Demonstration ---\n");

        Class<?> outerClass = NestAccessDemo.class;
        Class<?> nestedClass = OrderValidatorNestMember.class;

        System.out.println("1. Nest Host Inspection:");
        System.out.println("   - Outer Class Nest Host: " + outerClass.getNestHost().getName());
        System.out.println("   - Nested Class Nest Host: " + nestedClass.getNestHost().getName());
        System.out.println();

        System.out.println("2. Nestmate Validation:");
        System.out.println("   - Is nestedClass a nestmate of outerClass? " + nestedClass.isNestmateOf(outerClass));
        System.out.println();

        System.out.println("3. Executing Direct Private Access:");
        new OrderValidatorNestMember().validateAndAccess();
        System.out.println();

        System.out.println("4. Total Nest Members Count in Host: " + outerClass.getNestMembers().length);
    }
}

/*
Output:

--- Java 11 Nest-based Access Control Demonstration ---

1. Nest Host Inspection:
   - Outer Class Nest Host: com.codechamps.language.NestAccessDemo
   - Nested Class Nest Host: com.codechamps.language.NestAccessDemo

2. Nestmate Validation:
   - Is nestedClass a nestmate of outerClass? true

3. Executing Direct Private Access:
   - Nested member accessed private field directly: Enterprise-Nest-Secret-2026

4. Total Nest Members Count in Host: 2

*/
