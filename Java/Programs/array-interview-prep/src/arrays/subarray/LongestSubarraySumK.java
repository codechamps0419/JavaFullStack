/* Day 30 -  02/03/2026 */

package arrays.subarray;

import java.util.HashMap;

/**
 * Author: CodeChamps0419
 *
 * Longest Subarray with Sum = K (handles negatives too)
 *
 * APPROACH: Prefix Sum + HashMap
 * TIME  : O(n)
 * SPACE : O(n)
 */
public class LongestSubarraySumK {

    public static int longestSubarray(int[] arr, int k) {
        HashMap<Integer, Integer> prefixIndexMap = new HashMap<>();
        int prefixSum = 0, maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if (prefixSum == k) maxLen = i + 1; // whole subarray from 0 to i
            if (prefixIndexMap.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - prefixIndexMap.get(prefixSum - k));
            }
            prefixIndexMap.putIfAbsent(prefixSum, i); // store first occurrence only
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 2, 7, 1, 9};
        int k = 15;
        System.out.println("Array  : " + java.util.Arrays.toString(arr));
        System.out.println("k      : " + k);
        System.out.println("Max Len: " + longestSubarray(arr, k)); // 4 → [5,2,7,1]
    }
}

/*

Output:

Array  : [10, 5, 2, 7, 1, 9]
k      : 15
Max Len: 4

*/






