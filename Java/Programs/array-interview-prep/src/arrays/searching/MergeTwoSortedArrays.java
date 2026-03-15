/* Day 27 -  27/02/2026 */

package arrays.searching;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Merge Two Sorted Arrays Without Extra Space
 *
 * APPROACH: Gap Method (Shell Sort based)
 * TIME  : O((m+n) log(m+n))
 * SPACE : O(1)
 */
public class MergeTwoSortedArrays {

    public static void merge(int[] arr1, int[] arr2) {
        int m = arr1.length, n = arr2.length;
        int gap = (m + n + 1) / 2;

        while (gap > 0) {
            int i = 0, j = gap;
            while (j < m + n) {
                int left  = i < m ? arr1[i] : arr2[i - m];
                int right = j < m ? arr1[j] : arr2[j - m];
                if (left > right) {
                    if (i < m && j < m) { int t = arr1[i]; arr1[i] = arr1[j]; arr1[j] = t; }
                    else if (i < m)     { int t = arr1[i]; arr1[i] = arr2[j - m]; arr2[j - m] = t; }
                    else                { int t = arr2[i - m]; arr2[i - m] = arr2[j - m]; arr2[j - m] = t; }
                }
                i++; j++;
            }
            if (gap == 1) break;
            gap = (gap + 1) / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {0, 2, 6, 8, 9};
        System.out.println("Before arr1: " + Arrays.toString(arr1));
        System.out.println("Before arr2: " + Arrays.toString(arr2));
        merge(arr1, arr2);
        System.out.println("After  arr1: " + Arrays.toString(arr1)); // [0,1,2,3]
        System.out.println("After  arr2: " + Arrays.toString(arr2)); // [5,6,7,8,9]
    }
}

/*

Output:

Before arr1: [1, 3, 5, 7]
Before arr2: [0, 2, 6, 8, 9]
After  arr1: [0, 1, 2, 3]
After  arr2: [5, 6, 7, 8, 9]

*/






