/*
@author CodeChamps0419

Demonstrates Java 11 Files API Improvements:
1. Files.readAllLines() - Reads all lines from a small file into a List<String>.
2. Files.lines() - Lazily streams lines from a file for memory-efficient large file processing.
3. Files.write() - Writes collections or strings directly to files.
*/

package com.codechamps.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FilesApiImprovementsDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 Files API Improvements Demonstrations ---\n");

        Path tempFilePath = Path.of("sample-config.txt");

        try {
            // 1. WRITING CONTENT USING Files.write()
            List<String> configLines = List.of(
                "# Application Configuration",
                "server.port=8080",
                "server.timeout=30",
                "database.url=jdbc:postgresql://localhost:5432/appdb"
            );

            Files.write(tempFilePath, configLines);
            System.out.println("1. File written successfully using Files.write().");

            // 2. READING ALL LINES USING Files.readAllLines() (Best for small files)
            List<String> linesList = Files.readAllLines(tempFilePath);
            System.out.println("\n2. Reading all lines into a List (Count: " + linesList.size() + "):");
            linesList.forEach(line -> System.out.println("   - " + line));

            // 3. STREAMING LINES USING Files.lines() (Best for large files with lazy evaluation)
            System.out.println("\n3. Processing lines lazily using Files.lines() stream:");
            try (Stream<String> stream = Files.lines(tempFilePath)) {
                stream.filter(line -> !line.isBlank())
                      .filter(line -> !line.startsWith("#"))
                      .map(String::strip)
                      .forEach(cleanLine -> System.out.println("   [Active Config] -> " + cleanLine));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Clean up temporary file
            try {
                if (Files.exists(tempFilePath)) {
                    Files.delete(tempFilePath);
                    System.out.println("\n4. Temporary sample file cleaned up.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
Output:

--- Java 11 Files API Improvements Demonstrations ---

1. File written successfully using Files.write().

2. Reading all lines into a List (Count: 4):
   - # Application Configuration
   - server.port=8080
   - server.timeout=30
   - database.url=jdbc:postgresql://localhost:5432/appdb

3. Processing lines lazily using Files.lines() stream:
   [Active Config] -> server.port=8080
   [Active Config] -> server.timeout=30
   [Active Config] -> database.url=jdbc:postgresql://localhost:5432/appdb

4. Temporary sample file cleaned up.

*/
