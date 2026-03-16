/*
@author CodeChamps0419

Demonstrates Java 11 JVM Runtime & Performance Inspection:
1. Inspects CompilationMXBean to check JIT compiler name and compilation time.
2. Inspects heap memory thresholds and active thread metrics.
*/

package com.codechamps.jvm;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;

public class JvmPerformanceInspectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 JVM Performance & Runtime Inspection ---\n");

        // 1. JIT COMPILATION INSPECTION
        CompilationMXBean compilationBean = ManagementFactory.getCompilationMXBean();
        if (compilationBean != null) {
            System.out.println("1. JIT Compiler Name: " + compilationBean.getName());
            boolean isTimeSupported = compilationBean.isCompilationTimeMonitoringSupported();
            System.out.println("   - Compilation time monitoring supported: " + isTimeSupported);
            if (isTimeSupported) {
                System.out.println("   - Total compilation time: " + compilationBean.getTotalCompilationTime() + " ms");
            }
        } else {
            System.out.println("1. JIT Compilation monitoring not available.");
        }
        System.out.println();

        // 2. MEMORY USAGE INSPECTION
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long heapUsedMb = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long heapMaxMb = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);
        System.out.println("2. Heap Memory Status:");
        System.out.println("   - Heap Used: " + heapUsedMb + " MB");
        System.out.println("   - Heap Max:  " + heapMaxMb + " MB");
        System.out.println();

        // 3. THREAD METRICS
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        System.out.println("3. Thread Metrics:");
        System.out.println("   - Active Thread Count:     " + threadBean.getThreadCount());
        System.out.println("   - Peak Thread Count:       " + threadBean.getPeakThreadCount());
        System.out.println("   - Daemon Thread Count:     " + threadBean.getDaemonThreadCount());
        System.out.println();

        System.out.println("4. Java 11 Performance Summary:");
        System.out.println("   - Enhanced C2 JIT inlining limits and loop optimizations.");
        System.out.println("   - Optimized string concatenation and compact strings handling.");
        System.out.println("   - Reduced lambda metafactory overhead.");
    }
}

/*
Output:

--- Java 11 JVM Performance & Runtime Inspection ---

1. JIT Compiler Name: hotspot
   - Compilation time monitoring supported: true
   - Total compilation time: 245 ms

2. Heap Memory Status:
   - Heap Used: 6 MB
   - Heap Max:  4096 MB

3. Thread Metrics:
   - Active Thread Count:     3
   - Peak Thread Count:       3
   - Daemon Thread Count:     2

4. Java 11 Performance Summary:
   - Enhanced C2 JIT inlining limits and loop optimizations.
   - Optimized string concatenation and compact strings handling.
   - Reduced lambda metafactory overhead.

*/
