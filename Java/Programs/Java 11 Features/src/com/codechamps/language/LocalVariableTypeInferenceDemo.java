/*
@author CodeChamps0419

Demonstrates Java 11 Local Variable Type Inference (var keyword):
1. Simple local variable type inference (primitive and object types).
2. Type inference with complex generic collections and Stream API pipelines.
3. Usage in enhanced for-loops and try-with-resources blocks.
*/

package com.codechamps.language;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocalVariableTypeInferenceDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 Local Variable Type Inference (var) Demonstrations ---\n");

        // 1. SIMPLE LOCAL VARIABLES
        var message = "Hello, Java 11 var!";
        var count = 42;
        var price = 99.99;
        var isActive = true;

        System.out.println("1. Simple Local Variables:");
        System.out.println("   - message type: String -> " + message);
        System.out.println("   - count type: int -> " + count);
        System.out.println("   - price type: double -> " + price);
        System.out.println("   - isActive type: boolean -> " + isActive);
        System.out.println();

        // 2. COMPLEX GENERICS & COLLECTIONS
        var techStack = List.of("Java", "Spring Boot", "Docker", "Kubernetes");
        var departmentMap = new HashMap<String, List<String>>();
        departmentMap.put("Engineering", new ArrayList<>(techStack));

        System.out.println("2. Complex Generics & Collections:");
        System.out.println("   - techStack: " + techStack);
        System.out.println("   - departmentMap: " + departmentMap);
        System.out.println();

        // 3. STREAM API OPERATIONS
        var filteredTech = techStack.stream()
                .filter(tech -> tech.length() > 4)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("3. Stream API Operations:");
        System.out.println("   - Filtered and uppercased tech: " + filteredTech);
        System.out.println();

        // 4. LOOPS (ENHANCED FOR-LOOP)
        System.out.println("4. Enhanced For-Loop Iteration:");
        for (var entry : departmentMap.entrySet()) {
            var dept = entry.getKey();
            var members = entry.getValue();
            System.out.println("   - Dept: " + dept + ", Members: " + members);
        }
        System.out.println();

        // 5. TRY-WITH-RESOURCES
        System.out.println("5. Try-With-Resources with var:");
        try (var reader = new BufferedReader(new StringReader("Line 1: var in try-with-resources\nLine 2: Clean syntax"))) {
            var line = reader.readLine();
            while (line != null) {
                System.out.println("   - Read -> " + line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
Output:

--- Java 11 Local Variable Type Inference (var) Demonstrations ---

1. Simple Local Variables:
   - message type: String -> Hello, Java 11 var!
   - count type: int -> 42
   - price type: double -> 99.99
   - isActive type: boolean -> true

2. Complex Generics & Collections:
   - techStack: [Java, Spring Boot, Docker, Kubernetes]
   - departmentMap: {Engineering=[Java, Spring Boot, Docker, Kubernetes]}

3. Stream API Operations:
   - Filtered and uppercased tech: [SPRING BOOT, DOCKER, KUBERNETES]

4. Enhanced For-Loop Iteration:
   - Dept: Engineering, Members: [Java, Spring Boot, Docker, Kubernetes]

5. Try-With-Resources with var:
   - Read -> Line 1: var in try-with-resources
   - Read -> Line 2: Clean syntax

*/
