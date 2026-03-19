/*
@author CodeChamps0419

Demonstrates Java 17 Time-Based Versioning and JVM Performance Monitoring:
1. Inspects runtime version details using Runtime.version() (Feature version, LTS identification, etc.).
2. Monitors JVM memory usage and Garbage Collector beans via java.lang.management APIs.
*/

package com.codechamps.utilities;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.List;

public class JvmAndVersionDiagnosticsDemo {

    // 1. Time-Based Release Versioning Inspection
    public static void inspectRuntimeVersion() {
        Runtime.Version version = Runtime.version();
        int feature = version.feature();
        int interim = version.interim();
        int update = version.update();
        
        System.out.println("1. Time-Based Release Versioning Details:");
        System.out.println("   - Full Version String: " + version);
        System.out.println("   - Feature Version (Major): " + feature);
        System.out.println("   - Interim Version: " + interim);
        System.out.println("   - Update Version: " + update);
        
        // LTS versions: 8, 11, 17, 21, 25, etc. (every 3 years)
        boolean isLts = (feature == 8 || feature == 11 || feature == 17 || feature == 21 || (feature >= 17 && (feature - 17) % 6 == 0));
        System.out.println("   - Is LTS Version: " + isLts);
        System.out.println();
    }

    // 2. JVM Performance and GC Monitoring Inspection
    public static void inspectJvmPerformanceAndGc() {
        System.out.println("2. JVM Performance & Garbage Collection Inspection:");
        
        // Memory Usage
        MemoryUsage heapUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        long maxHeapMB = heapUsage.getMax() / (1024 * 1024);
        long usedHeapMB = heapUsage.getUsed() / (1024 * 1024);
        
        System.out.println("   - Max Heap Size: " + maxHeapMB + " MB");
        System.out.println("   - Used Heap Size: " + usedHeapMB + " MB");
        System.out.println("   - Available Processors: " + Runtime.getRuntime().availableProcessors());
        
        // Garbage Collector Beans
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        System.out.println("   - Active Garbage Collectors:");
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("     * Name: " + gcBean.getName());
            System.out.println("       Collections: " + gcBean.getCollectionCount());
            System.out.println("       Total GC Time: " + gcBean.getCollectionTime() + " ms");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 17 Versioning & JVM Diagnostics Demo ---\n");
        inspectRuntimeVersion();
        inspectJvmPerformanceAndGc();
    }
}

/*
Output:

--- Java 17 Versioning & JVM Diagnostics Demo ---

1. Time-Based Release Versioning Details:
   - Full Version String: 17.0.12+7-LTS
   - Feature Version (Major): 17
   - Interim Version: 0
   - Update Version: 12
   - Is LTS Version: true

2. JVM Performance & Garbage Collection Inspection:
   - Max Heap Size: 4096 MB
   - Used Heap Size: 24 MB
   - Available Processors: 8
   - Active Garbage Collectors:
     * Name: G1 Young Generation
       Collections: 3
       Total GC Time: 15 ms
     * Name: G1 Old Generation
       Collections: 0
       Total GC Time: 0 ms

*/