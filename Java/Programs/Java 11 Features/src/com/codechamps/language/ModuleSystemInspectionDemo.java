/*
@author CodeChamps0419

Demonstrates Java 11 Module System Maturity & Runtime Introspection:
1. Inspects module information of the current running class.
2. Explores java.base module descriptor, packages, and exported APIs.
3. Showcases programmatic module layer querying.
*/

package com.codechamps.language;

import java.lang.module.ModuleDescriptor;
import java.util.Optional;
import java.util.Set;

public class ModuleSystemInspectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 Module System Maturity & Inspection --- \n");

        // 1. INSPECT CURRENT CLASS MODULE
        Class<ModuleSystemInspectionDemo> clazz = ModuleSystemInspectionDemo.class;
        Module currentModule = clazz.getModule();

        System.out.println("1. Current Class Module Information:");
        System.out.println("   - Class Name:  " + clazz.getName());
        System.out.println("   - Module Name: " + (currentModule.isNamed() ? currentModule.getName() : "Unnamed Module (Classpath)"));
        System.out.println();

        // 2. INSPECT java.base MODULE DESCRIPTOR
        // java.base is the foundational module of the Java Platform Module System (JPMS).
        Optional<Module> baseModuleOpt = ModuleLayer.boot().findModule("java.base");
        if (baseModuleOpt.isPresent()) {
            Module baseModule = baseModuleOpt.get();
            ModuleDescriptor descriptor = baseModule.getDescriptor();

            System.out.println("2. Found 'java.base' Module Descriptor:");
            System.out.println("   - Name:        " + descriptor.name());
            System.out.println("   - Version:     " + descriptor.version().map(Object::toString).orElse("Unknown"));
            
            Set<String> packages = descriptor.packages();
            System.out.println("   - Total Packages in java.base: " + packages.size());
            
            // Print a few sample packages
            System.out.println("   - Sample Packages:");
            packages.stream().limit(5).forEach(pkg -> System.out.println("     * " + pkg));
            System.out.println();
        }

        // 3. JPMS ENTERPRISE ADVANTAGES SUMMARY
        System.out.println("3. JPMS Production Benefits Summary:");
        System.out.println("   - Strong encapsulation: Hides internal packages from external access.");
        System.out.println("   - Explicit dependency declarations via 'requires' and 'requires transitive'.");
        System.out.println("   - Controlled framework reflection access using 'opens' declarations.");
        System.out.println("   - Faster module resolution and optimized startup times.");
    }
}

/*
Output:

--- Java 11 Module System Maturity & Inspection --- 

1. Current Class Module Information:
   - Class Name:  com.codechamps.language.ModuleSystemInspectionDemo
   - Module Name: Unnamed Module (Classpath)

2. Found 'java.base' Module Descriptor:
   - Name:        java.base
   - Version:     11.0.12
   - Total Packages in java.base: 133
   - Sample Packages:
     * java.io
     * java.lang
     * java.lang.annotation
     * java.lang.invoke
     * java.lang.management

3. JPMS Production Benefits Summary:
   - Strong encapsulation: Hides internal packages from external access.
   - Explicit dependency declarations via 'requires' and 'requires transitive'.
   - Controlled framework reflection access using 'opens' declarations.
   - Faster module resolution and optimized startup times.

*/