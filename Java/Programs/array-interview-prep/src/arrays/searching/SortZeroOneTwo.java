/* Day 28 -  28/02/2026 */

package arrays.searching;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Sort an Array of 0s, 1s, and 2s (Dutch National Flag Problem)
 *
 * APPROACH: Three Pointer (low, mid, high)
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class SortZeroOneTwo {

    public static void sort012(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                swap(arr, low++, mid++);
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high--);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 1, 0, 2, 1, 0};
        System.out.println("Before: " + Arrays.toString(arr));
        sort012(arr);
        System.out.println("After : " + Arrays.toString(arr)); // [0,0,0,1,1,1,2,2]
    }
}

/*

Output:

Before arr1: [1, 3, 5, 7]
Before arr2: [0, 2, 6, 8, 9]
After  arr1: [0, 1, 2, 3]
After  arr2: [5, 6, 7, 8, 9]

*/







