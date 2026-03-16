/*
@author CodeChamps0419

Demonstrates Java 11 TLS 1.3 Support & Protocol Inspection:
1. Initializes SSLContext configured for standard TLS negotiation (preferring TLS 1.3).
2. Inspects supported and enabled protocols and cipher suites on SSLSockets.
*/

package com.codechamps.network;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.util.Arrays;

public class TlsProtocolInspectionDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 TLS 1.3 Protocol & Socket Inspection ---\n");

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            try (SSLSocket socket = (SSLSocket) socketFactory.createSocket()) {

                String[] supportedProtocols = socket.getSupportedProtocols();
                System.out.println("1. Supported Protocols by JVM:");
                for (String proto : supportedProtocols) {
                    System.out.println("   - " + proto);
                }
                System.out.println();

                String[] enabledProtocols = socket.getEnabledProtocols();
                System.out.println("2. Default Enabled Protocols (TLS 1.3 active by default):");
                for (String proto : enabledProtocols) {
                    System.out.println("   - " + proto);
                }
                System.out.println();

                boolean tls13Supported = Arrays.asList(enabledProtocols).contains("TLSv1.3");
                System.out.println("3. Is TLS 1.3 enabled and ready? " + tls13Supported);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
Output:

--- Java 11 TLS 1.3 Protocol & Socket Inspection ---

1. Supported Protocols by JVM:
   - TLSv1
   - TLSv1.1
   - TLSv1.2
   - TLSv1.3

2. Default Enabled Protocols (TLS 1.3 active by default):
   - TLSv1.3
   - TLSv1.2

3. Is TLS 1.3 enabled and ready? true

*/
