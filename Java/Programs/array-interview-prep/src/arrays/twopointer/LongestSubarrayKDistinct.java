/* Day 35 -  07/03/2026 */

package arrays.twopointer;

import java.util.HashMap;

/**
 * Author: CodeChamps0419
 *
 * Longest Subarray with At Most K Distinct Elements
 *
 * APPROACH: Sliding Window + HashMap (frequency count)
 * TIME  : O(n)
 * SPACE : O(k)
 */
public class LongestSubarrayKDistinct {

    public static int longestSubarrayKDistinct(int[] arr, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int left = 0, maxLen = 0;
        for (int right = 0; right < arr.length; right++) {
            freq.put(arr[right], freq.getOrDefault(arr[right], 0) + 1);
            while (freq.size() > k) {
                freq.put(arr[left], freq.get(arr[left]) - 1);
                if (freq.get(arr[left]) == 0) freq.remove(arr[left]);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 3};
        int k = 2;
        System.out.println("Array  : " + java.util.Arrays.toString(arr));
        System.out.println("k      : " + k);
        System.out.println("Max Len: " + longestSubarrayKDistinct(arr, k)); // 4 → [1,2,1,2]
    }
}

/*

Output:

Array  : [1, 2, 1, 2, 3]
k      : 2
Max Len: 4

*/






