package com.codechamps.functional;

/*
@author CodeChamps0419

Demonstrates core and specialized functions and operators that transform inputs 
into computed output results.
Abstract Method present in Predicate : apply
*/

import java.util.function.*;

public class FunctionDemo {
    public static void main(String[] args) {

        System.out.println("--- Function Interface Demonstrations ---");

        Function<String, Integer> function = (str) -> str.length();
        System.out.println("1. Function Length: " + function.apply("Hello"));

        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;
        System.out.println("2. BiFunction Sum: " + biFunction.apply(10, 20));

        UnaryOperator<String> unaryOperator = (str) -> str.toUpperCase();
        System.out.println("3. UnaryOperator UpperCase: " + unaryOperator.apply("java"));

        BinaryOperator<Integer> binaryOperator = (a, b) -> a * b;
        System.out.println("4. BinaryOperator Product: " + binaryOperator.apply(4, 5));

        IntUnaryOperator intUnary = (n) -> n * n;
        System.out.println("5. IntUnaryOperator Square: " + intUnary.applyAsInt(6));

        LongUnaryOperator longUnary = (n) -> n + 10L;
        System.out.println("6. LongUnaryOperator Add: " + longUnary.applyAsLong(100L));

        DoubleUnaryOperator doubleUnary = (d) -> d * 2.0;
        System.out.println("7. DoubleUnaryOperator Double: " + doubleUnary.applyAsDouble(12.5));

        IntBinaryOperator intBinary = (a, b) -> a - b;
        System.out.println("8. IntBinaryOperator Subtract: " + intBinary.applyAsInt(20, 5));

        LongBinaryOperator longBinary = (a, b) -> a / b;
        System.out.println("9. LongBinaryOperator Divide: " + longBinary.applyAsLong(100L, 2L));

        DoubleBinaryOperator doubleBinary = (a, b) -> a + b;
        System.out.println("10. DoubleBinaryOperator Add: " + doubleBinary.applyAsDouble(10.5, 5.5));

        IntFunction<String> intFunc = (n) -> "Value: " + n;
        System.out.println("11. IntFunction Result: " + intFunc.apply(42));

        LongFunction<String> longFunc = (n) -> "ID: " + n;
        System.out.println("12. LongFunction Result: " + longFunc.apply(9999L));

        DoubleFunction<String> doubleFunc = (d) -> "Price: $" + d;
        System.out.println("13. DoubleFunction Result: " + doubleFunc.apply(19.99));

        DoubleToIntFunction doubleToInt = (d) -> (int) d;
        System.out.println("14. DoubleToIntFunction: " + doubleToInt.applyAsInt(45.89));

        DoubleToLongFunction doubleToLong = (d) -> (long) d;
        System.out.println("15. DoubleToLongFunction: " + doubleToLong.applyAsLong(99.45));

        IntToLongFunction intToLong = (n) -> (long) n * 1000L;
        System.out.println("16. IntToLongFunction: " + intToLong.applyAsLong(5));

        IntToDoubleFunction intToDouble = (n) -> (double) n / 2;
        System.out.println("17. IntToDoubleFunction: " + intToDouble.applyAsDouble(7));

        LongToIntFunction longToInt = (n) -> (int) (n / 2);
        System.out.println("18. LongToIntFunction: " + longToInt.applyAsInt(100L));

        LongToDoubleFunction longToDouble = (n) -> (double) n + 0.5;
        System.out.println("19. LongToDoubleFunction: " + longToDouble.applyAsDouble(10L));

        ToIntFunction<String> toInt = (s) -> s.length();
        System.out.println("20. ToIntFunction Length: " + toInt.applyAsInt("Java"));

        ToLongFunction<String> toLong = (s) -> Long.parseLong(s);
        System.out.println("21. ToLongFunction Parse: " + toLong.applyAsLong("5000"));

        ToDoubleFunction<String> toDouble = (s) -> Double.parseDouble(s);
        System.out.println("22. ToDoubleFunction Parse: " + toDouble.applyAsDouble("99.9"));

        ToIntBiFunction<String, String> toIntBi = (s1, s2) -> s1.length() + s2.length();
        System.out.println("23. ToIntBiFunction Sum Length: " + toIntBi.applyAsInt("Hello", "World"));

        ToLongBiFunction<Integer, Integer> toLongBi = (a, b) -> (long) a * b;
        System.out.println("24. ToLongBiFunction Product: " + toLongBi.applyAsLong(100, 200));

        ToDoubleBiFunction<Double, Double> toDoubleBi = (a, b) -> a * b;
        System.out.println("25. ToDoubleBiFunction Product: " + toDoubleBi.applyAsDouble(2.5, 4.0));
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.functional.FunctionDemo

--- Function Interface Demonstrations ---

1. Function Length: 5
2. BiFunction Sum: 30
3. UnaryOperator UpperCase: JAVA
4. BinaryOperator Product: 20
5. IntUnaryOperator Square: 36
6. LongUnaryOperator Add: 110
7. DoubleUnaryOperator Double: 25.0
8. IntBinaryOperator Subtract: 15
9. LongBinaryOperator Divide: 50
10. DoubleBinaryOperator Add: 16.0
11. IntFunction Result: Value: 42
12. LongFunction Result: ID: 9999
13. DoubleFunction Result: Price: $19.99
14. DoubleToIntFunction: 45
15. DoubleToLongFunction: 99
16. IntToLongFunction: 5000
17. IntToDoubleFunction: 3.5
18. LongToIntFunction: 50
19. LongToDoubleFunction: 10.5
20. ToIntFunction Length: 4
21. ToLongFunction Parse: 5000
22. ToDoubleFunction Parse: 99.9
23. ToIntBiFunction Sum Length: 10
24. ToLongBiFunction Product: 20000
25. ToDoubleBiFunction Product: 10.0
*/
