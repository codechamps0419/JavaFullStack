/* Day 15 -  15/02/2026 */

package arrays.basic;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Author: CodeChamps0419
 *
 * Move All Zeros to End of Array
 *
 */
public class MoveZerosToEnd {

    /*
     * APPROACH: Two Pointer
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static void moveZeros(int[] arr) {
        int slow = 0; // tracks position to place next non-zero
        for (int fast = 0; fast < arr.length; fast++) {
            if (arr[fast] != 0) {
                arr[slow] = arr[fast];
                slow++;
            }
        }
        // fill remaining positions with zeros
        while (slow < arr.length) {
            arr[slow] = 0;
            slow++;
        }
    }

    /*
     * APPROACH: Stream
     * Complexity ~ TIME : O(n), SPACE : O(n) as new array is created
     */
    public static int[] moveZerosUsingStream(int[] arr) {
        int[] nonZeros = Arrays.stream(arr).filter(n -> n != 0).toArray();
        int[] zeros    = Arrays.stream(arr).filter(n -> n == 0).toArray();
        return IntStream.concat(Arrays.stream(nonZeros), Arrays.stream(zeros)).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};

        // Solution 1
        System.out.println("Before: " + Arrays.toString(arr));
        moveZeros(arr);
        System.out.println("After : " + Arrays.toString(arr)); // [1, 3, 12, 0, 0]

        // Solution 2
        int[] arr2 = {0, 1, 0, 3, 12};
        System.out.println("Before: " + Arrays.toString(arr2));
        System.out.println("After : " + Arrays.toString(moveZerosUsingStream(arr2))); // [1, 3, 12, 0, 0]
    }
}

/*
Output:

Before: [0, 1, 0, 3, 12]
After : [1, 3, 12, 0, 0]
Before: [0, 1, 0, 3, 12]
After : [1, 3, 12, 0, 0]

*/







