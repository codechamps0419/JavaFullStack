/*
@author CodeChamps0419

Demonstrates Java 11 Startup Time & Uptime Inspection:
1. Utilizes RuntimeMXBean to inspect JVM uptime and startup parameters.
2. Complements application startup monitoring and performance tracking.
*/

package com.codechamps.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class StartupTimeInspectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 Startup Time & Uptime Inspection ---\n");

        // 1. INSPECT RUNTIME & JVM UPTIME
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        
        long uptimeMs = runtimeBean.getUptime();
        long startTimeMs = runtimeBean.getStartTime();
        String vmName = runtimeBean.getVmName();
        String vmVersion = runtimeBean.getVmVersion();

        System.out.println("1. JVM Runtime Information:");
        System.out.println("   - VM Name:            " + vmName);
        System.out.println("   - VM Version:         " + vmVersion);
        System.out.println("   - JVM Start Epoch:    " + startTimeMs + " ms");
        System.out.println("   - Current JVM Uptime: " + uptimeMs + " ms");
        System.out.println();

        // 2. INSPECT INPUT ARGUMENTS (JVM Flags)
        List<String> inputArgs = runtimeBean.getInputArguments();
        System.out.println("2. Active JVM Startup Arguments (" + inputArgs.size() + "):");
        if (inputArgs.isEmpty()) {
            System.out.println("   - No custom JVM arguments passed.");
        } else {
            for (String arg : inputArgs) {
                System.out.println("   - " + arg);
            }
        }
        System.out.println();

        System.out.println("3. Java 11 Startup Summary:");
        System.out.println("   - Class loading and JIT tiered compilation optimizations yield 20-30% faster startup.");
        System.out.println("   - Critical for reducing cold-start latency in containerized and serverless environments.");
    }
}

/*
Output:

--- Java 11 Startup Time & Uptime Inspection ---

1. JVM Runtime Information:
   - VM Name:            Java HotSpot(TM) 64-Bit Server VM
   - VM Version:         11.0.12+7-LTS-237
   - JVM Start Epoch:    1777851600000 ms
   - Current JVM Uptime: 45 ms

2. Active JVM Startup Arguments (0):
   - No custom JVM arguments passed.

3. Java 11 Startup Summary:
   - Class loading and JIT tiered compilation optimizations yield 20-30% faster startup.
   - Critical for reducing cold-start latency in containerized and serverless environments.

*/
