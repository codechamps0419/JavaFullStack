package com.codechamps.utilities;

/*
@author CodeChamps0419

Demonstrates methods of the Base64 API (Basic, URL, and MIME variants).
*/

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Demo {
    public static void main(String[] args) {
        System.out.println("--- Java Base64 API Demonstrations ---\n");

        String originalText = "hello";
        
        String encodedBasic = Base64.getEncoder()
                .encodeToString(originalText.getBytes(StandardCharsets.UTF_8));
        System.out.println("1A. Basic Encoded: " + encodedBasic);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedBasic);
        String decodedBasic = new String(decodedBytes, StandardCharsets.UTF_8);
        System.out.println("1B. Basic Decoded: " + decodedBasic + "\n");

        String urlString = "https://example.com/path?query=java8+stream";
        
        String encodedUrl = Base64.getUrlEncoder()
                .encodeToString(urlString.getBytes(StandardCharsets.UTF_8));
        System.out.println("2A. URL Encoded: " + encodedUrl);

        byte[] decodedUrlBytes = Base64.getUrlDecoder().decode(encodedUrl);
        String decodedUrl = new String(decodedUrlBytes, StandardCharsets.UTF_8);
        System.out.println("2B. URL Decoded: " + decodedUrl + "\n");

        String mimeContent = "Line 1: Sample MIME data content for email or large text blocks.\nLine 2: Continuing payload.";
        
        String encodedMime = Base64.getMimeEncoder()
                .encodeToString(mimeContent.getBytes(StandardCharsets.UTF_8));
        System.out.println("3A. MIME Encoded:\n" + encodedMime);

        byte[] decodedMimeBytes = Base64.getMimeDecoder().decode(encodedMime);
        String decodedMime = new String(decodedMimeBytes, StandardCharsets.UTF_8);
        System.out.println("3B. MIME Decoded:\n" + decodedMime);
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.utilities.Base64Demo

--- Java Base64 API Demonstrations ---

1A. Basic Encoded: aGVsbG8=
1B. Basic Decoded: hello

2A. URL Encoded: aHR0cHM6Ly9leGFtcGxlLmNvbS9wYXRoP3F1ZXJ5PWphdmE4K3N0cmVhbQ==
2B. URL Decoded: https://example.com/path?query=java8+stream

3A. MIME Encoded:
TGluZSAxOiBTYW1wbGUgTUlNRSBkYXRhIGNvbnRlbnQgZm9yIGVtYWlsIG9yIGxhcmdlIHRleHQg
YmxvY2tzLgpMaW5lIDI6IENvbnRpbnVpbmcgcGF5bG9hZC4=
3B. MIME Decoded:
Line 1: Sample MIME data content for email or large text blocks.
Line 2: Continuing payload.

*/
