/*
@author CodeChamps0419

Demonstrates Java 17 Helpful NullPointerException (NPE) Messages (JEP 358 - Standardized & Enabled by Default in Java 17 LTS):
1. Shows how the JVM pinpoints the exact null reference in multi-level method/property invocation chains.
2. Demonstrates robust error handling capturing and reading enhanced NPE diagnostic details.
*/

package com.codechamps.language;

public class NullPointerExceptionEnhancementsDemo {

    public static class Profile {
        private String email;

        public Profile(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }

    public static class Customer {
        private String name;
        private Profile profile;

        public Customer(String name, Profile profile) {
            this.name = name;
            this.profile = profile;
        }

        public String getName() {
            return name;
        }

        public Profile getProfile() {
            return profile;
        }
    }

    public static class Order {
        private Customer customer;

        public Order(Customer customer) {
            this.customer = customer;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    // Method prone to NullPointerException across deep method chaining
    public static String getCustomerEmail(Order order) {
        // If order, customer, or profile is null, Java 17 JVM provides exact diagnostic details out-of-the-box
        return order.getCustomer().getProfile().getEmail().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println("--- Java 17 Helpful NullPointerException Demo (JEP 358) ---\n");

        // Case 1: Null Profile object scenario
        Order invalidOrder = new Order(new Customer("Hrushikesh", null));

        try {
            System.out.println("1. Executing method chain with missing profile (Java 17 Runtime)...");
            getCustomerEmail(invalidOrder);
        } catch (NullPointerException e) {
            System.out.println("   - Caught Expected NPE!");
            System.out.println("   - Enhanced Java 17 JVM Message: " + e.getMessage());
        }
        System.out.println();

        // Case 2: Completely null Customer scenario
        Order nullCustomerOrder = new Order(null);

        try {
            System.out.println("2. Executing method chain with missing customer (Java 17 Runtime)...");
            getCustomerEmail(nullCustomerOrder);
        } catch (NullPointerException e) {
            System.out.println("   - Caught Expected NPE!");
            System.out.println("   - Enhanced Java 17 JVM Message: " + e.getMessage());
        }
    }
}

/*
Output:

--- Java 17 Helpful NullPointerException Demo (JEP 358) ---

1. Executing method chain with missing profile (Java 17 Runtime)...
   - Caught Expected NPE!
   - Enhanced Java 17 JVM Message: Cannot invoke "com.codechamps.language.NullPointerExceptionEnhancementsDemo$Profile.getEmail()" because the return value of "com.codechamps.language.NullPointerExceptionEnhancementsDemo$Customer.getProfile()" is null

2. Executing method chain with missing customer (Java 17 Runtime)...
   - Caught Expected NPE!
   - Enhanced Java 17 JVM Message: Cannot invoke "com.codechamps.language.NullPointerExceptionEnhancementsDemo$Customer.getProfile()" because the return value of "com.codechamps.language.NullPointerExceptionEnhancementsDemo$Order.getCustomer()" is null

*/