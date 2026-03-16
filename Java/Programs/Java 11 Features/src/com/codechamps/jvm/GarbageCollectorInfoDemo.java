/*
@author CodeChamps0419

Demonstrates JVM Garbage Collector Inspection and Monitoring:
1. Inspects active GarbageCollectorMXBeans to identify the active GC (e.g., G1GC, ZGC, ParallelGC).
2. Monitors GC collection counts and cumulative collection times.
*/

package com.codechamps.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GarbageCollectorInfoDemo {

    public static void main(String[] args) {
        System.out.println("--- JVM Garbage Collector Monitoring Demonstration ---\n");

        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

        System.out.println("Active Garbage Collectors detected by JVM:");
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("   - GC Name:          " + gcBean.getName());
            System.out.println("     Collection Count: " + gcBean.getCollectionCount());
            System.out.println("     Collection Time:  " + gcBean.getCollectionTime() + " ms");
            System.out.println();
        }

        System.out.println("Note on Java 11 ZGC:");
        System.out.println("   - In Java 11, ZGC can be enabled experimentally via JVM flag: -XX:+UseZGC");
        System.out.println("   - In Java 21+, ZGC became fully production-ready and supports generational collection (-XX:+ZGenerational).");
    }
}

/*
Output (Example when run with default G1GC in Java 11):

--- JVM Garbage Collector Monitoring Demonstration ---

Active Garbage Collectors detected by JVM:
   - GC Name:          G1 Young Generation
     Collection Count: 2
     Collection Time:  15 ms

   - GC Name:          G1 Old Generation
     Collection Count: 0
     Collection Time:  0 ms

Note on Java 11 ZGC:
   - In Java 11, ZGC can be enabled experimentally via JVM flag: -XX:+UseZGC
   - In Java 21+, ZGC became fully production-ready and supports generational collection (-XX:+ZGenerational).

*/
