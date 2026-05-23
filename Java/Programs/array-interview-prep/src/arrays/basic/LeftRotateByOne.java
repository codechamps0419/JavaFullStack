/* Day 12 -  12/02/2026 */

package arrays.basic;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Left Rotate Array by One Position
 *
 */
public class LeftRotateByOne {

    /*
     * APPROACH: Single Pass — shift elements left
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static void leftRotateByOne(int[] arr) {
        int first = arr[0]; // store first element
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1]; // shift each element one position left
        }
        arr[arr.length - 1] = first; // place first element at end
    }

    /*
     * APPROACH: Stream
     * Complexity ~ TIME : O(n), SPACE : O(n) as new array is created
     */
    public static int[] leftRotateByOneUsingStream(int[] arr) {
        return java.util.stream.IntStream.concat(
                Arrays.stream(arr, 1, arr.length),  // elements from index 1 to end
                Arrays.stream(arr, 0, 1)            // first element moved to end
        ).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        // Solution 1
        System.out.println("Before: " + Arrays.toString(arr));
        leftRotateByOne(arr);
        System.out.println("After (S1): " + Arrays.toString(arr)); // [2, 3, 4, 5, 1]

        // Solution 2
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("After (S2): " + Arrays.toString(leftRotateByOneUsingStream(arr2))); // [2, 3, 4, 5, 1]
    }
}

/*
Output:

Before: [1, 2, 3, 4, 5]
After (S1): [2, 3, 4, 5, 1]
After (S2): [2, 3, 4, 5, 1]

*/







