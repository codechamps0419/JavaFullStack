/* Day 25 -  25/02/2026 */

package arrays.searching;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Find First and Last Position of Target in Sorted Array
 *
 * APPROACH: Binary Search twice — once for first, once for last occurrence
 * TIME  : O(log n)
 * SPACE : O(1)
 */
public class FirstLastPosition {

    public static int[] searchRange(int[] arr, int target) {
        return new int[]{findFirst(arr, target), findLast(arr, target)};
    }

    private static int findFirst(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) { result = mid; right = mid - 1; } // keep searching left
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return result;
    }

    private static int findLast(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) { result = mid; left = mid + 1; } // keep searching right
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};
        System.out.println("Array  : " + Arrays.toString(arr));
        System.out.println("Range of 8 : " + Arrays.toString(searchRange(arr, 8)));  // [3, 4]
        System.out.println("Range of 6 : " + Arrays.toString(searchRange(arr, 6)));  // [-1, -1]
    }
}

/* 
Output:

Array  : [5, 7, 7, 8, 8, 10]
Range of 8 : [3, 4]
Range of 6 : [-1, -1]

*/






