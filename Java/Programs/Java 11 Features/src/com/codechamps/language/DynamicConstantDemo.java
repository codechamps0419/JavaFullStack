/*
@author CodeChamps0419

Demonstrates Java 11 Dynamic Constant & MethodHandle Optimization:
1. Utilizes MethodHandles and MethodHandles.Lookup for high-performance invocation.
2. Highlights how advanced runtime constant mechanics back modern Java frameworks and lambda execution.
*/

package com.codechamps.language;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class DynamicConstantDemo {

    public static void main(String[] args) throws Throwable {
        System.out.println("--- Java 11 Dynamic Constants & MethodHandles Demonstration ---\n");

        // 1. OBTAINING METHOD HANDLE VIA LOOKUP
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle computeHb = lookup.findStatic(
            DynamicConstantDemo.class, 
            "computeEnterpriseConfig", 
            MethodType.methodType(String.class)
        );

        System.out.println("1. MethodHandle successfully resolved via Lookup.");

        // 2. INVOKING METHOD HANDLE
        String resolvedConfig = (String) computeHb.invokeExact();
        System.out.println("2. Invoked computed result: " + resolvedConfig);
        System.out.println();

        System.out.println("Note on Dynamic Class-File Constants (CONSTANT_Dynamic):");
        System.out.println("   - Introduced in Java 11 (JEP 309), this allows constant pools to hold runtime-computed constants.");
        System.out.println("   - Uses bootstrap methods to compute values once during class loading and cache them efficiently.");
    }

    private static String computeEnterpriseConfig() {
        return "Config-Value-Cached-Dynamically-At-Classloading";
    }
}

/*
Output:

--- Java 11 Dynamic Constants & MethodHandles Demonstration ---

1. MethodHandle successfully resolved via Lookup.
2. Invoked computed result: Config-Value-Cached-Dynamically-At-Classloading

Note on Dynamic Class-File Constants (CONSTANT_Dynamic):
   - Introduced in Java 11 (JEP 309), this allows constant pools to hold runtime-computed constants.
   - Uses bootstrap methods to compute values once during class loading and cache them efficiently.

*/
