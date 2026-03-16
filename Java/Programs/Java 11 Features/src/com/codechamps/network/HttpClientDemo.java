/*
@author CodeChamps0419

Demonstrates Java 11 HttpClient API features:
1. Synchronous GET request using client.send()
2. Asynchronous GET request using client.sendAsync() with CompletableFuture
3. POST request with JSON body using HttpRequest.BodyPublishers.ofString()
*/

package com.codechamps.network;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HttpClientDemo {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public static void main(String[] args) {
        System.out.println("--- Java 11 HttpClient API Demonstrations ---\n");

        String targetUrl = "https://httpbin.org/get";
        String postUrl = "https://httpbin.org/post";

        try {
            System.out.println("1. Executing Synchronous GET Request...");
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(targetUrl))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("   - Status Code: " + response.statusCode());
            System.out.println("   - Response Body Length: " + response.body().length() + " characters\n");

            System.out.println("2. Executing Asynchronous GET Request...");
            CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(
                    getRequest, 
                    HttpResponse.BodyHandlers.ofString()
            );

            futureResponse.thenApply(HttpResponse::body)
                    .thenAccept(body -> System.out.println("   - Async Response Received! Length: " + body.length()))
                    .join();
            System.out.println();

            System.out.println("3. Executing POST Request with JSON Body...");
            String jsonPayload = "{\"name\":\"Hrushikesh\",\"role\":\"Java Full Stack Developer\"}";

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create(postUrl))
                    .header("Content-Type", "application/json")
                    .timeout(Duration.ofSeconds(5))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("   - POST Status Code: " + postResponse.statusCode());
            System.out.println("   - POST Response received successfully.");

        } catch (Exception e) {
            System.out.println("Execution note: Network calls require active internet access. Error: " + e.getMessage());
        }
    }
}

/*
Output:

--- Java 11 HttpClient API Demonstrations ---

1. Executing Synchronous GET Request...
   - Status Code: 200
   - Response Body Length: 312 characters

2. Executing Asynchronous GET Request...
   - Async Response Received! Length: 312

3. Executing POST Request with JSON Body...
   - POST Status Code: 200
   - POST Response received successfully.

*/
