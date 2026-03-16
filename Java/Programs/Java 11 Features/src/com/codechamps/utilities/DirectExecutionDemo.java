/*
@author CodeChamps0419

Demonstrates Java 11 Packaged Execution:
When using a package declaration, the file must be compiled using javac and run using 
fully-qualified package naming from the root src directory.
*/

package com.codechamps.utilities;

import java.time.LocalDateTime;

public class DirectExecutionDemo {

    public static void main(String[] args) {
        System.out.println("--- Packaged Java Execution Demonstration ---\n");

        System.out.println("1. Execution Timestamp: " + LocalDateTime.now());
        System.out.println("2. Package Name:       " + DirectExecutionDemo.class.getPackage().getName());
        System.out.println("3. Arguments passed:    " + args.length);
        
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("   - Arg[" + i + "]: " + args[i]);
            }
        }

        System.out.println("\n4. Status: Successfully compiled and executed with package structure!");
    }
}

/*
Output:

--- Packaged Java Execution Demonstration ---

1. Execution Timestamp: 2026-11-01T23:42:00
2. Package Name:       com.codechamps.utilities
3. Arguments passed:    0

4. Status: Successfully compiled and executed with package structure!

*/
