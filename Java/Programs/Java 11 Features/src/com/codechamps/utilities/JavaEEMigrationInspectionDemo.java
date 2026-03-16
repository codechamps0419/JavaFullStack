/*
@author CodeChamps0419

Demonstrates Java 11 Java EE Module Removal & Jakarta EE Migration Inspection:
1. Programmatically checks for the availability of removed Java EE modules (JAXB, Common Annotations).
2. Verifies whether external Jakarta dependencies have been correctly added to the classpath in Java 11+.
*/

package com.codechamps.utilities;

public class JavaEEMigrationInspectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 Java EE / Jakarta EE Migration Inspection ---\n");

        // 1. Check for Jakarta JAXB (Replaces java.xml.bind)
        checkClassAvailability(
            "jakarta.xml.bind.JAXBContext",
            "Jakarta JAXB API (XML Binding)",
            "Add jakarta.xml.bind-api and jaxb-runtime dependencies to your pom.xml/build.gradle."
        );

        // 2. Check for Jakarta Common Annotations (Replaces java.xml.ws.annotation / javax.annotation)
        checkClassAvailability(
            "jakarta.annotation.PostConstruct",
            "Jakarta Annotations API (@PostConstruct / @PreDestroy)",
            "Add jakarta.annotation-api dependency."
        );

        // 3. Check for Jakarta RESTful Web Services API (JAX-RS)
        checkClassAvailability(
            "jakarta.ws.rs.Path",
            "Jakarta REST API",
            "Add jakarta.ws.rs-api dependency if building REST services."
        );
        
        System.out.println("\nMigration Summary for Java 11+:");
        System.out.println("   - Built-in javax.* EE modules were removed to slim down the JDK.");
        System.out.println("   - Spring Boot starters handle most of these automatically, but standalone apps require explicit Jakarta dependencies.");
    }

    private static void checkClassAvailability(String className, String displayName, String remediationTip) {
        try {
            Class.forName(className);
            System.out.println("[FOUND]    " + displayName + " is present on the classpath.");
        } catch (ClassNotFoundException e) {
            System.out.println("[MISSING]  " + displayName + " NOT found.");
            System.out.println("           -> Remedy: " + remediationTip);
        }
    }
}

/*
Output (Example when run with Jakarta dependencies present):

--- Java 11 Java EE / Jakarta EE Migration Inspection ---

[FOUND]    Jakarta JAXB API (XML Binding) is present on the classpath.
[FOUND]    Jakarta Annotations API (@PostConstruct / @PreDestroy) is present on the classpath.
[FOUND]    Jakarta REST API is present on the classpath.

Migration Summary for Java 11+:
   - Built-in javax.* EE modules were removed to slim down the JDK.
   - Spring Boot starters handle most of these automatically, but standalone apps require explicit Jakarta dependencies.

*/
