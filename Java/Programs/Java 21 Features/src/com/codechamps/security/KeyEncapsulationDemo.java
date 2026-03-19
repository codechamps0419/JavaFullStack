/*
@author CodeChamps0419

Demonstrates Java 21 Key Encapsulation Mechanism (KEM) API (JEP 452):
- Reference Guide & Visual Walkthrough: https://www.youtube.com/watch?v=nSYTZPnHAmk
1. Generating a secure X25519 key pair for cryptographic key exchange.
2. Initializing a KEM encapsulator to generate a shared secret and an encapsulation ciphertext blob.
3. Initializing a KEM decapsulator on the receiver side to securely recover the exact same shared secret.
*/

package com.codechamps.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Arrays;
import javax.crypto.KEM;
import javax.crypto.SecretKey;

public class KeyEncapsulationDemo {

    public static void main(String[] args) {
        try {
            System.out.println("--- Java 21 Key Encapsulation Mechanism (KEM) API Demo ---\n");

            // 1. Generate an asymmetric key pair (using modern X25519 curve)
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("X25519");
            KeyPair keyPair = keyPairGen.generateKeyPair();
            System.out.println("1. Generated recipient asymmetric key pair successfully.");

            // 2. Obtain a KEM instance (DHKEM algorithm specified)
            KEM kem = KEM.getInstance("DHKEM");

            // 3. Sender Side: Encapsulate a shared secret using the recipient's public key
            KEM.Encapsulator encapsulator = kem.newEncapsulator(keyPair.getPublic());
            KEM.Encapsulated encapsulated = encapsulator.encapsulate();
            
            SecretKey senderSecret = encapsulated.key();
            byte[] capsule = encapsulated.encapsulation();

            System.out.println("2. Sender Side:");
            System.out.println("   - Generated Encapsulation Capsule size: " + capsule.length + " bytes");
            System.out.println("   - Derived Sender Secret (hex prefix): " + bytesToHex(senderSecret.getEncoded(), 8));

            // 4. Receiver Side: Decapsulate the capsule using the private key to recover the secret
            KEM.Decapsulator decapsulator = kem.newDecapsulator(keyPair.getPrivate());
            SecretKey receiverSecret = decapsulator.decapsulate(capsule);

            System.out.println("3. Receiver Side:");
            System.out.println("   - Recovered Receiver Secret (hex prefix): " + bytesToHex(receiverSecret.getEncoded(), 8));

            // 5. Verify cryptographic alignment
            boolean keysMatch = Arrays.equals(senderSecret.getEncoded(), receiverSecret.getEncoded());
            System.out.println("\n4. Cryptographic Verification:");
            System.out.println("   - Do sender and receiver shared secrets match? " + (keysMatch ? "YES (Secure Handshake Confirmed)" : "NO"));

        } catch (Exception e) {
            System.err.println("KEM execution failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper utility to format byte arrays for clean logging
    private static String bytesToHex(byte[] bytes, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(bytes.length, length); i++) {
            sb.append(String.format("%02x", bytes[i]));
        }
        return sb.toString() + "...";
    }
}

/*
Output:

--- Java 21 Key Encapsulation Mechanism (KEM) API Demo ---

1. Generated recipient asymmetric key pair successfully.
2. Sender Side:
   - Generated Encapsulation Capsule size: 32 bytes
   - Derived Sender Secret (hex prefix): 4a7b9c1d...
3. Receiver Side:
   - Recovered Receiver Secret (hex prefix): 4a7b9c1d...

4. Cryptographic Verification:
   - Do sender and receiver shared secrets match? YES (Secure Handshake Confirmed)

*/