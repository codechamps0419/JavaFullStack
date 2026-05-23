/* Day 37 -  09/03/2026 */

package arrays.twopointer;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Rearrange Positive and Negative Numbers
 * All negatives on left, all positives on right — relative order not preserved.
 *
 */
public class RearrangePosNeg {

    /*
     * APPROACH: Two Pointer (Partition — like QuickSort pivot)
     * Complexity ~ TIME : O(n), SPACE : O(1)
     * Note: relative order of elements is NOT preserved
     */
    public static void rearrange(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            while (left < right && arr[left] < 0)  left++;  // find positive on left side
            while (left < right && arr[right] >= 0) right--; // find negative on right side
            if (left < right) {
                int temp = arr[left]; arr[left] = arr[right]; arr[right] = temp;
                left++; right--;
            }
        }
    }

    /*
     * APPROACH: Stream (order preserved)
     * Complexity ~ TIME : O(n), SPACE : O(n) as new array is created
     * Note: relative order IS preserved
     */
    public static int[] rearrangeUsingStream(int[] arr) {
        int[] negatives = Arrays.stream(arr).filter(n -> n < 0).toArray();
        int[] positives = Arrays.stream(arr).filter(n -> n >= 0).toArray();
        return java.util.stream.IntStream.concat(
                Arrays.stream(negatives),
                Arrays.stream(positives)
        ).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {-3, 5, -2, 8, -1, 4};

        // Solution 1
        System.out.println("Before: " + Arrays.toString(arr));
        rearrange(arr);
        System.out.println("After (S1): " + Arrays.toString(arr)); // negatives left, positives right

        // Solution 2
        int[] arr2 = {-3, 5, -2, 8, -1, 4};
        System.out.println("After (S2): " + Arrays.toString(rearrangeUsingStream(arr2))); // [-3,-2,-1,5,8,4]
    }
}

/*
Output:

Before: [-3, 5, -2, 8, -1, 4]
After (S1): [-3, -1, -2, 8, 5, 4]
After (S2): [-3, -2, -1, 5, 8, 4]

*/







