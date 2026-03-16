/*
@author CodeChamps0419

Demonstrates how to inspect JVM Container Awareness settings at runtime:
1. Displays JVM Maximum Heap Memory (automatically scaled to container limits in Java 11+).
2. Displays Available Processors (respecting container CPU quotas).
3. Displays active JVM garbage collection and container support flags.
*/

package com.codechamps.jvm;

import java.lang.management.ManagementFactory;
import java.util.List;

public class ContainerAwarenessDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 JVM Container Awareness Diagnostic ---\n");

        // 1. MEMORY INSPECTION
        long maxMemoryBytes = Runtime.getRuntime().maxMemory();
        double maxMemoryMB = maxMemoryBytes / (1024.0 * 1024.0);
        double maxMemoryGB = maxMemoryMB / 1024.0;

        System.out.println("1. Memory Allocation Inspection:");
        System.out.printf("   - Max Heap Memory (Bytes): %d\n", maxMemoryBytes);
        System.out.printf("   - Max Heap Memory (MB):    %.2f MB\n", maxMemoryMB);
        System.out.printf("   - Max Heap Memory (GB):    %.2f GB\n", maxMemoryGB);
        System.out.println();

        // 2. CPU / PROCESSOR INSPECTION
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("2. CPU / Processor Inspection:");
        System.out.println("   - Available Processors (Detected by JVM): " + availableProcessors);
        System.out.println();

        // 3. JVM RUNTIME ARGUMENTS INSPECTION
        System.out.println("3. JVM Runtime Arguments & Flags:");
        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        if (inputArguments.isEmpty()) {
            System.out.println("   - No explicit JVM arguments passed (using defaults).");
        } else {
            inputArguments.forEach(arg -> System.out.println("   - " + arg));
        }
        
        System.out.println("\nTip: Run inside Docker with '-m 512m' to observe automatic heap scaling!");
    }
}

/*
Output (Example when run on a local machine vs inside a 512MB Docker container):

Local Machine (e.g., 16GB RAM, 8 Cores):
   - Max Heap Memory (MB):    4096.00 MB
   - Available Processors:    8

Docker Container (e.g., docker run -m 512m -it myapp):
   - Max Heap Memory (MB):    256.00 MB  (Automatically set to 50% of 512MB limit)
   - Available Processors:    1 (or as constrained by cgroups)
*/
