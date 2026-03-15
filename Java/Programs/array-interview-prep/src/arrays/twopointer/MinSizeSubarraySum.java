/* Day 36 -  08/03/2026 */

package arrays.twopointer;

/**
 * Author: CodeChamps0419
 *
 * Minimum Size Subarray with Sum >= k
 *
 * APPROACH: Sliding Window
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class MinSizeSubarraySum {

    public static int minSubarrayLen(int[] arr, int target) {
        int left = 0, currentSum = 0, minLen = Integer.MAX_VALUE;
        for (int right = 0; right < arr.length; right++) {
            currentSum += arr[right];
            while (currentSum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                currentSum -= arr[left++]; // shrink window
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println("Array  : " + java.util.Arrays.toString(arr));
        System.out.println("Target : " + target);
        System.out.println("Min Len: " + minSubarrayLen(arr, target)); // 2 → [4,3]
    }
}

/*

Output:

Array  : [1, 2, 1, 2, 3]
k      : 2
Max Len: 4

*/






