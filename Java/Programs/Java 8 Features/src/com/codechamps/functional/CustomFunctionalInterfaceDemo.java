package com.codechamps.functional;

/*
@author CodeChamps0419

Demonstrates custom functional interface.
Abstract Method present in Predicate : accept
*/

@FunctionalInterface
interface MathOperation {

    int calculate(int a, int b);

    default void printInfo() {
        System.out.println("Executing a math operation...");
    }

    static String printInterfaceInfo() {
        return "Interface: MathOperation, Use: Executing a math operation...";
    }
}

public class CustomFunctionalInterfaceDemo {
    public static void main(String[] args) {
        
        MathOperation addition = new MathOperation() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };
        
        addition.printInfo();
        System.out.println("Anonymous Class Result: " + addition.calculate(10, 5)); 
        
        MathOperation subtraction = (a, b) -> a - b;
        
        subtraction.printInfo();
        System.out.println("Lambda Result: " + subtraction.calculate(10, 5)); 

        int result = operate(20, 10, (a, b) -> a * b);
        System.out.println("Method Argument Result: " + result);     
    }

    public static int operate(int a, int b, MathOperation op) {
        return op.calculate(a, b);
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.functional.CustomFunctionalInterfaceDemo

Executing a math operation...
Anonymous Class Result: 15
Executing a math operation...
Lambda Result: 5
Method Argument Result: 200

*/
