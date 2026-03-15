package com.codechamps.language;

/*
@author CodeChamps0419

Demonstrates Java 8 Type Annotations using ElementType.TYPE_USE.
Classification: Major Improvement (expanding pre-Java 8 annotation restrictions).
What is TYPE_USE?: Before Java 8, annotations could only be placed on declarations (like classes, methods, and fields). 
TYPE_USE allows annotations anywhere a "type" is used in code—such as generic type arguments, object creations, 
casts, and implements clauses—enabling advanced static analysis tools to catch bugs (like null pointer exceptions) at compile time.
*/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class TypeAnnotationDemo {

    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface NonNull {}

    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface NonNegative {}

    public static void main(String[] args) {
        System.out.println("--- Java 8 Type Annotations Demonstrations ---\n");

        List<@NonNull String> names = new ArrayList<>();
        names.add("Java");
        names.add("Enterprise");
        System.out.println("1. List with annotated type arguments: " + names);

        Object obj = new @NonNull String("Type Use Annotation Example");
        System.out.println("2. Instantiated object with TYPE_USE: " + obj);

        String text = "Hello Java 8";
        String castedText = (@NonNull String) text;
        System.out.println("3. Casted text with TYPE_USE: " + castedText);

        @NonNegative int score = 100;
        System.out.println("4. Primitive variable with TYPE_USE annotation score: " + score);
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.language.TypeAnnotationDemo

--- Java 8 Type Annotations Demonstrations ---

1. List with annotated type arguments: [Java, Enterprise]
2. Instantiated object with TYPE_USE: Type Use Annotation Example
3. Casted text with TYPE_USE: Hello Java 8
4. Primitive variable with TYPE_USE annotation score: 100

*/
