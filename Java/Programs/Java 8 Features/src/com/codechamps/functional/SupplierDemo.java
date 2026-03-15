package com.codechamps.functional;

/*
@author CodeChamps0419

Demonstrates all variants of suppliers that generate values without accepting inputs.
Abstract Method present in Predicate : get
*/

import java.util.function.*;

public class SupplierDemo {
    public static void main(String[] args) {

        System.out.println("--- Supplier Demonstrations ---");

        Supplier<String> supplier = () -> "Hello from Supplier!";
        System.out.println("1. Supplier Result: " + supplier.get());

        BooleanSupplier booleanSupplier = () -> 10 > 5;
        System.out.println("2. BooleanSupplier Result: " + booleanSupplier.getAsBoolean());

        IntSupplier intSupplier = () -> (int) (Math.random() * 100);
        System.out.println("3. IntSupplier Random Number: " + intSupplier.getAsInt());

        LongSupplier longSupplier = () -> System.currentTimeMillis();
        System.out.println("4. LongSupplier Timestamp: " + longSupplier.getAsLong());

        DoubleSupplier doubleSupplier = () -> Math.PI;
        System.out.println("5. DoubleSupplier PI Value: " + doubleSupplier.getAsDouble());
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.functional.SupplierDemo

--- Supplier Demonstrations ---

1. Supplier Result: Hello from Supplier!
2. BooleanSupplier Result: true
3. IntSupplier Random Number: 42
4. LongSupplier Timestamp: 1716940800000
5. DoubleSupplier PI Value: 3.141592653589793

*/
