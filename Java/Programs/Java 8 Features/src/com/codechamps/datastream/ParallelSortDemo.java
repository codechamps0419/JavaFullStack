package com.codechamps.datastream;

/*
@author CodeChamps0419

Demonstrates Arrays.parallelSort() feature introduced in Java 8.
*/

import java.util.Arrays;

public class ParallelSortDemo {
    public static void main(String[] args) {
        System.out.println("--- Java 8 Parallel Sort Demonstrations ---\n");

        int[] numbers = {58, 12, 85, 36, 91, 7, 43, 22, 65};
        System.out.println("1A. Original Array: " + Arrays.toString(numbers));

        Arrays.parallelSort(numbers);
        System.out.println("1B. Sorted Array (parallelSort): " + Arrays.toString(numbers) + "\n");

        String[] languages = {"Python", "Java", "C++", "Go", "JavaScript", "Rust"};
        System.out.println("2A. Original String Array: " + Arrays.toString(languages));

        Arrays.parallelSort(languages);
        System.out.println("2B. Sorted String Array (parallelSort): " + Arrays.toString(languages));
    }
}

/*
Output:

H:\Passionate Buddies\Java>java com.codechamps.datastream.ParallelSortDemo

--- Java 8 Parallel Sort Demonstrations ---

1A. Original Array: [58, 12, 85, 36, 91, 7, 43, 22, 65]
1B. Sorted Array (parallelSort): [7, 12, 22, 36, 43, 58, 65, 85, 91]

2A. Original String Array: [Python, Java, C++, Go, JavaScript, Rust]
2B. Sorted String Array (parallelSort): [C++, Go, Java, JavaScript, Python, Rust]

*/
