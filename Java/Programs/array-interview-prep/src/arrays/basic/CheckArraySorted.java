/* Day 8 -  08/02/2026 */

package arrays.basic;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Check if Array is Sorted (Ascending)
 * 
 */
public class CheckArraySorted {

    /*
     * APPROACH: Single Pass
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    /*
     * APPROACH: Stream
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static boolean isSortedUsingStream(int[] arr) {
        return java.util.stream.IntStream
                .range(0, arr.length - 1)
                .allMatch(i -> arr[i] <= arr[i + 1]);
    }

    public static void main(String[] args) {
        int[] sorted   = {1, 2, 3, 4, 5};
        int[] unsorted = {1, 3, 2, 4, 5};

        // Solution 1
        System.out.println("Array: " + Arrays.toString(sorted));
        System.out.println("Sorted (S1): " + isSorted(sorted));     // true
        System.out.println("Array: " + Arrays.toString(unsorted));
        System.out.println("Sorted (S1): " + isSorted(unsorted));   // false

        // Solution 2
        System.out.println("Sorted (S2): " + isSortedUsingStream(sorted));   // true
        System.out.println("Sorted (S2): " + isSortedUsingStream(unsorted)); // false
    }
}

/*
Output:

Array: [1, 2, 3, 4, 5]
Sorted (S1): true
Array: [1, 3, 2, 4, 5]
Sorted (S1): false
Sorted (S2): true
Sorted (S2): false

*/







