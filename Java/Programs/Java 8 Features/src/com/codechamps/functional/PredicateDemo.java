package com.codechamps.functional;

/*
@author CodeChamps0419

Demonstrates all varients of Predicates which is boolean-valued condition evaluators across object and primitive variants.
Abstract Method present in Predicate : test
*/

import java.util.function.*;

public class PredicateDemo {
    public static void main(String[] args) {

        System.out.println("--- Predicate Demonstrations ---\n");

        Predicate<String> predicate = (str) -> str.isEmpty();
        System.out.println("1. Predicate Is Empty? " + predicate.test(""));

        BiPredicate<String, String> biPredicate = (s1, s2) -> s1.equals(s2);
        System.out.println("2. BiPredicate Equals? " + biPredicate.test("Test", "Test"));

        IntPredicate intPredicate = (n) -> n % 2 == 0;
        System.out.println("3. IntPredicate Is Even (4)? " + intPredicate.test(4));

        LongPredicate longPredicate = (n) -> n > 1000L;
        System.out.println("4. LongPredicate Greater Than 1000? " + longPredicate.test(5000L));

        DoublePredicate doublePredicate = (d) -> d > 0.0;
        System.out.println("5. DoublePredicate Is Positive? " + doublePredicate.test(-2.5));
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.functional.PredicateDemo

--- Predicate Demonstrations ---

1. Predicate Is Empty? true
2. BiPredicate Equals? true
3. IntPredicate Is Even (4)? true
4. LongPredicate Greater Than 1000? true
5. DoublePredicate Is Positive? false

*/
