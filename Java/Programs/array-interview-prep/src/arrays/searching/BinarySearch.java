/* Day 24 -  24/02/2026 */

package arrays.searching;

/**
 * Author: CodeChamps0419
 *
 * Binary Search on a Sorted Array
 *
 * APPROACH: Divide and Conquer — eliminate half the search space each step
 * TIME  : O(log n)
 * SPACE : O(1)
 */
public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // avoids integer overflow vs (left+right)/2
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        System.out.println("Index of 7  : " + binarySearch(arr, 7));  // 3
        System.out.println("Index of 10 : " + binarySearch(arr, 10)); // -1
    }
}

/*

Index of 7  : 3
Index of 10 : -1

*/






