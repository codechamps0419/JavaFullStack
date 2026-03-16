/*
@author CodeChamps0419

Demonstrates Java 11 Java Flight Recorder (JFR) Management Inspection:
1. Inspects FlightRecorder capabilities and verifies whether JFR is available.
2. Lists active JFR recording sessions and configurations programmatically.
*/

package com.codechamps.jvm;

import jdk.jfr.FlightRecorder;
import jdk.jfr.Recording;

public class JfrMonitoringInspectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 JFR Monitoring & Inspection Demonstration ---\n");

        try {
            boolean isAvailable = FlightRecorder.isAvailable();
            System.out.println("1. Is Java Flight Recorder available? " + isAvailable);

            if (isAvailable) {
                var activeRecordings = FlightRecorder.getFlightRecorder().getRecordings();
                System.out.println("2. Active JFR Recording Sessions Count: " + activeRecordings.size());

                if (activeRecordings.isEmpty()) {
                    System.out.println("   - No active programmatic recordings found.");
                    System.out.println("   - Tip: Start JFR via JVM flag: -XX:StartFlightRecording=duration=60s,filename=recording.jfr");
                } else {
                    for (Recording recording : activeRecordings) {
                        System.out.println("   - Recording Name: " + recording.getName());
                        System.out.println("     State:          " + recording.getState());
                        System.out.println("     Destination:    " + recording.getDestination());
                    }
                }
            } else {
                System.out.println("JFR is not available on this JVM runtime.");
            }

        } catch (UnsupportedOperationException e) {
            System.out.println("JFR operation not supported in this environment: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
Output:

--- Java 11 JFR Monitoring & Inspection Demonstration ---

1. Is Java Flight Recorder available? true
2. Active JFR Recording Sessions Count: 0
   - No active programmatic recordings found.
   - Tip: Start JFR via JVM flag: -XX:StartFlightRecording=duration=60s,filename=recording.jfr

*/
