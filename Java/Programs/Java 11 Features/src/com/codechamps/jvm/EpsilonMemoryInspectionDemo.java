/*
@author CodeChamps0419

Demonstrates Java 11 Memory and Heap Inspection:
1. Programmatically inspects JVM heap memory thresholds and current usage.
2. Complements memory-testing scenarios (such as evaluating allocations under specialized garbage collectors like Epsilon GC).
*/

package com.codechamps.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class EpsilonMemoryInspectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 Heap & Memory Inspection Demonstration ---\n");

        // 1. INSPECT HEAP MEMORY USAGE
        MemoryUsage heapUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        
        long initSizeMb = heapUsage.getInit() / (1024 * 1024);
        long usedSizeMb = heapUsage.getUsed() / (1024 * 1024);
        long committedSizeMb = heapUsage.getCommitted() / (1024 * 1024);
        long maxSizeMb = heapUsage.getMax() / (1024 * 1024);

        System.out.println("1. Heap Memory Statistics:");
        System.out.println("   - Initial Heap:   " + initSizeMb + " MB");
        System.out.println("   - Used Heap:      " + usedSizeMb + " MB");
        System.out.println("   - Committed Heap: " + committedSizeMb + " MB");
        System.out.println("   - Maximum Heap:   " + maxSizeMb + " MB");
        System.out.println();

        System.out.println("2. Note on Epsilon GC (-XX:+UseEpsilonGC):");
        System.out.println("   - Epsilon is a 'no-op' (do-nothing) garbage collector introduced in Java 11.");
        System.out.println("   - It allocates memory for objects but never reclaims or collects them.");
        System.out.println("   - When the maximum heap (" + maxSizeMb + " MB) is exhausted, the JVM throws an OutOfMemoryError.");
        System.out.println("   - Ideal strictly for performance benchmarking, short-lived batch jobs, and memory allocation profiling.");
    }
}

/*
Output:

--- Java 11 Heap & Memory Inspection Demonstration ---

1. Heap Memory Statistics:
   - Initial Heap:   256 MB
   - Used Heap:      4 MB
   - Committed Heap: 254 MB
   - Maximum Heap:   4096 MB

2. Note on Epsilon GC (-XX:+UseEpsilonGC):
   - Epsilon is a 'no-op' (do-nothing) garbage collector introduced in Java 11.
   - It allocates memory for objects but never reclaims or collects them.
   - When the maximum heap (4096 MB) is exhausted, the JVM throws an OutOfMemoryError.
   - Ideal strictly for performance benchmarking, short-lived batch jobs, and memory allocation profiling.

*/
