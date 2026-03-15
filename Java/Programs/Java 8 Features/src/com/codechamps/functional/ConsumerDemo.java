package com.codechamps.functional;

/*
@author CodeChamps0419

Demonstrates all variants of consumers that accept inputs to perform actions 
without returning any results.
Abstract Method present in Predicate : accept
*/

import java.util.function.*;

public class ConsumerDemo {

    public static void main(String[] args) {

        Consumer<String> consumer = (name) -> 
            System.out.println("1. Consumer: Hello, " + name);
        consumer.accept("Buddy");

        BiConsumer<String, Integer> biConsumer = (name, age) -> 
            System.out.println("2. BiConsumer: " + name + " is " + age + " years old");
        biConsumer.accept("AP", 27);

        IntConsumer intConsumer = (number) -> 
            System.out.println("3. IntConsumer Square: " + (number * number));
        intConsumer.accept(5);

        LongConsumer longConsumer = (number) -> 
            System.out.println("4. LongConsumer Value: " + (number * 10L));
        longConsumer.accept(100L);

        DoubleConsumer doubleConsumer = (number) -> 
            System.out.println("5. DoubleConsumer Half: " + (number / 2.0));
        doubleConsumer.accept(45.5);

        ObjIntConsumer<String> objIntConsumer = (item, count) -> 
            System.out.println("6. ObjIntConsumer: Item '" + item + "' has quantity " + count);
        objIntConsumer.accept("Jhumka", 20);

        ObjLongConsumer<String> objLongConsumer = (account, balance) -> 
            System.out.println("7. ObjLongConsumer: Account " + account + " balance is Rs. " + balance);
        objLongConsumer.accept("ACC-9988", 1000000L);

        ObjDoubleConsumer<String> objDoubleConsumer = (product, price) -> 
            System.out.println("8. ObjDoubleConsumer: Product " + product + " costs Rs. " + price);
        objDoubleConsumer.accept("Laptop", 68499.99);
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.functional.ConsumerDemo

1. Consumer: Hello, Buddy
2. BiConsumer: AP is 27 years old
3. IntConsumer Square: 25
4. LongConsumer Value: 1000
5. DoubleConsumer Half: 22.75
6. ObjIntConsumer: Item 'Jhumka' has quantity 20
7. ObjLongConsumer: Account ACC-9988 balance is Rs. 1000000
8. ObjDoubleConsumer: Product Laptop costs Rs. 68499.99

*/
