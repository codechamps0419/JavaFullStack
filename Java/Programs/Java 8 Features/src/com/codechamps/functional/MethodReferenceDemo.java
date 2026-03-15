package com.codechamps.functional;

/*
@author CodeChamps0419

Demonstrates Method References in Java 8.
*/

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.List;
import java.util.ArrayList;

public class MethodReferenceDemo {

    public static int addTen(int x) {
        return x + 10;
    }

    public String convertToLowerCase(String text) {
        return text.toLowerCase();
    }

    public static void main(String[] args) {
        
        System.out.println("--- Method Reference Demonstrations ---");

        // 1. Reference to a Static Method
        Function<Integer, Integer> staticRef1 = MethodReferenceDemo::addTen;
        System.out.println("1. Custom Static Method Ref: " + staticRef1.apply(5)); 

        Function<String, Integer> staticRef2 = Integer::parseInt;
        System.out.println("   Built-in Static Method Ref: " + staticRef2.apply("123")); 

        // 2. Reference to an Instance Method
        MethodReferenceDemo demoObj = new MethodReferenceDemo();

        Function<String, String> instanceRef1 = demoObj::convertToLowerCase;
        System.out.println("2A. Instance Method (Particular Object): " + instanceRef1.apply("JAVA")); 

        Function<String, String> instanceRef2 = String::toUpperCase;
        System.out.println("2B. Instance Method (Arbitrary Object): " + instanceRef2.apply("hello")); 

        // 3. Reference to a Constructor
        Supplier<List<String>> constructorRef = ArrayList::new;
        
        List<String> myList = constructorRef.get();
        myList.add("AP");
        System.out.println("3. Constructor Ref (List instantiated): " + !myList.isEmpty()); 
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.functional.MethodReferenceDemo

--- Method Reference Demonstrations ---

1. Custom Static Method Ref: 15
   Built-in Static Method Ref: 123
2A. Instance Method (Particular Object): java
2B. Instance Method (Arbitrary Object): HELLO
3. Constructor Ref (List instantiated): true

*/
