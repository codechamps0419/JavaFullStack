package com.codechamps.utilities;

/*
@author CodeChamps0419

Demonstrates methods of the Optional class.
*/

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {

        System.out.println("--- Java Optional Class Demonstrations ---\n");

        Optional<String> emptyOpt = Optional.empty();
        System.out.println("1. Is emptyOpt present? " + emptyOpt.isPresent());

        Optional<String> ofOpt = Optional.of("Java Enterprise");
        System.out.println("2. Optional.of value: " + ofOpt.get());

        String nullableName = null;
        Optional<String> nullableOpt1 = Optional.ofNullable(nullableName);
        Optional<String> nullableOpt2 = Optional.ofNullable("Spring Boot");
        System.out.println("3A. ofNullable with null present? " + nullableOpt1.isPresent());
        System.out.println("3B. ofNullable with value present? " + nullableOpt2.get() + "\n");

        Optional<String> tech = Optional.of("Microservices");

        if (tech.isPresent()) {
            System.out.println("4A. isPresent check: " + tech.get());
        }

        System.out.println("4B. get() value: " + tech.get());

        tech.ifPresent(val -> System.out.println("4C. ifPresent action executed on: " + val));
        System.out.println();

        Optional<String> missingValue = Optional.empty();

        String defaultVal = missingValue.orElse("Default Technology");
        System.out.println("5A. orElse fallback: " + defaultVal);

        String lazyVal = missingValue.orElseGet(() -> "Generated Default Value");
        System.out.println("5B. orElseGet fallback: " + lazyVal);

        try {
            String result = missingValue.orElseThrow(() -> new IllegalArgumentException("Value not found!"));
        } catch (Exception e) {
            System.out.println("5C. orElseThrow caught exception: " + e.getMessage() + "\n");
        }

        Optional<String> framework = Optional.of("Hibernate Framework");

        Optional<Integer> lengthOpt = framework.map(String::length);
        System.out.println("6A. map() transformed length: " + lengthOpt.orElse(0));

        Optional<String> filteredOpt = framework.filter(f -> f.startsWith("H"));
        System.out.println("6B. filter() matched value: " + filteredOpt.orElse("No Match"));
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.utilities.OptionalDemo

--- Java Optional Class Demonstrations ---

1. Is emptyOpt present? false
2. Optional.of value: Java Enterprise
3A. ofNullable with null present? false
3B. ofNullable with value present? Spring Boot

4A. isPresent check: Microservices
4B. get() value: Microservices
4C. ifPresent action executed on: Microservices

5A. orElse fallback: Default Technology
5B. orElseGet fallback: Generated Default Value
5C. orElseThrow caught exception: Value not found!

6A. map() transformed length: 19
6B. filter() matched value: Hibernate Framework
*/
