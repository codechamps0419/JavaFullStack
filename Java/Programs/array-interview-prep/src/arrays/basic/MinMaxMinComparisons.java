/* Day 13 -  13/02/2026 */

package arrays.basic;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Find Max and Min Using Minimum Comparisons (Tournament / Pair Comparison)
 * 
 */
public class MinMaxMinComparisons {

    /*
     * APPROACH: Pair Comparison (Tournament Method)
     * Complexity ~ TIME : O(n), SPACE : O(1)
     * Comparisons: ~3n/2 instead of 2(n-1)
     */
    public static int[] findMinMax(int[] arr) {
        int min, max;
        int i;

        // Step 1: initialize min and max from first pair
        if (arr.length % 2 == 0) {
            min = Math.min(arr[0], arr[1]);
            max = Math.max(arr[0], arr[1]);
            i = 2;
        } else {
            min = max = arr[0];
            i = 1;
        }

        // Step 2: process remaining elements in pairs
        while (i < arr.length - 1) {
            if (arr[i] < arr[i + 1]) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i + 1]);
            } else {
                min = Math.min(min, arr[i + 1]);
                max = Math.max(max, arr[i]);
            }
            i += 2;
        }
        return new int[]{min, max};
    }

    /*
     * APPROACH: Stream (IntSummaryStatistics)
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static int[] findMinMaxUsingStream(int[] arr) {
        java.util.IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
        return new int[]{stats.getMin(), stats.getMax()};
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 8, 2, 9, 4};

        // Solution 1
        System.out.println("Array      : " + Arrays.toString(arr));
        int[] result = findMinMax(arr);
        System.out.println("Min (S1)   : " + result[0]); // 1
        System.out.println("Max (S1)   : " + result[1]); // 9

        // Solution 2
        int[] result2 = findMinMaxUsingStream(arr);
        System.out.println("Min (S2)   : " + result2[0]); // 1
        System.out.println("Max (S2)   : " + result2[1]); // 9
    }
}

/*
Output:

Array      : [3, 5, 1, 8, 2, 9, 4]
Min (S1)   : 1
Max (S1)   : 9
Min (S2)   : 1
Max (S2)   : 9

*/







