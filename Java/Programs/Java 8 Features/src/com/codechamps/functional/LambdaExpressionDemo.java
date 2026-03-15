package com.codechamps.functional;

/*
@author CodeChamps0419

Demonstrates Lambda Expression variants in Java 8.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Greeting {
    void sayHello();
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class LambdaExpressionDemo {
    public static void main(String[] args) {
        
        System.out.println("--- Lambda Expression Demonstrations ---\n");
        
        // 1. Lambda with Zero Arguments
        Greeting greeting = () -> System.out.println("1. Zero arguments: Hello, AP - from Lambda!");
        greeting.sayHello();

        // 2. Lambda with a Single Argument
        java.util.function.Consumer<String> consumer = name -> 
            System.out.println("2. Single argument: Welcome, " + name);
        consumer.accept("Buddy");

        // 3. Lambda with Multiple Arguments & Block Body
        Calculator addition = (int a, int b) -> {
            System.out.println("3. Multiple arguments block body execution:");
            return a + b;
        };
        System.out.println("   Result: " + addition.calculate(15, 25));

        // 4. Lambda Expressions in Collections
        List<String> techStack = new ArrayList<>(Arrays.asList("Java", "Spring Boot", "Python", "React", "Docker"));

        System.out.println("4A. Collection Iteration (forEach):");
        techStack.forEach(tech -> System.out.println("   - " + tech));

        techStack.removeIf(tech -> tech.startsWith("P"));
        System.out.println("4B. Collection Filtering (after removing items starting with 'P'):");
        techStack.forEach(tech -> System.out.println("   - " + tech));
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.functional.LambdaExpressionDemo

--- Lambda Expression Demonstrations ---

1. Zero arguments: Hello AP - from Lambda!
2. Single argument: Welcome, Buddy
3. Multiple arguments block body execution:
   Result: 40
4A. Collection Iteration (forEach):
   - Java
   - Spring Boot
   - Python
   - React
   - Docker
4B. Collection Filtering (after removing items starting with 'P'):
   - Java
   - Spring Boot
   - React
   - Docker

*/
