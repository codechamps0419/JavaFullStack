package com.codechamps.utilities;

/*
@author CodeChamps0419

Demonstrates methods of StringJoiner, String.join(), and Collectors.joining().
*/

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class StringJoinDemo {
    public static void main(String[] args) {
        System.out.println("--- Java String Joining Demonstrations ---\n");

        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        joiner.add("Java").add("Spring").add("Kafka");
        System.out.println("1. StringJoiner result: " + joiner);

        String csv = String.join(",", "A", "B", "C");
        System.out.println("2. String.join() result: " + csv);

        List<String> techList = Arrays.asList("Docker", "Kubernetes", "Jenkins");
        String streamJoined = techList.stream()
                .collect(Collectors.joining(" | ", "{", "}"));
        System.out.println("3. Collectors.joining() result: " + streamJoined);
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.utilities.StringJoinDemo

--- Java String Joining Demonstrations ---

1. StringJoiner result: [Java, Spring, Kafka]
2. String.join() result: A,B,C
3. Collectors.joining() result: {Docker | Kubernetes | Jenkins}

*/
