/* Day 6 -  06/02/2026 */

package arrays.advanced;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Next Permutation
 *
 * APPROACH: Find pivot + swap + reverse suffix (in-place)
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class NextPermutation {

    public static void nextPermutation(int[] arr) {
        int n = arr.length, pivot = -1;

        // Step 1: Find rightmost index where arr[i] < arr[i+1]
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) { pivot = i; break; }
        }

        if (pivot == -1) { // already largest permutation → reverse to get smallest
            reverse(arr, 0, n - 1);
            return;
        }

        // Step 2: Find rightmost element greater than arr[pivot]
        for (int i = n - 1; i > pivot; i--) {
            if (arr[i] > arr[pivot]) { swap(arr, i, pivot); break; }
        }

        // Step 3: Reverse suffix after pivot
        reverse(arr, pivot + 1, n - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    private static void reverse(int[] arr, int i, int j) {
        while (i < j) { swap(arr, i++, j--); }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println("Before: " + Arrays.toString(arr));
        nextPermutation(arr);
        System.out.println("After : " + Arrays.toString(arr)); // [1,3,2]

        int[] arr2 = {3, 2, 1};
        nextPermutation(arr2);
        System.out.println("Next of [3,2,1]: " + Arrays.toString(arr2)); // [1,2,3]
    }
}

/*

Output:

Before: [1, 2, 3]
After : [1, 3, 2]
Next of [3,2,1]: [1, 2, 3]

*/







