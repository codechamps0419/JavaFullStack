/* Day 37 -  09/03/2026 */

package com.interview.strings.window;

/**
 * @author CodeChamps0419
 *
 * Longest Repeating Character Replacement (LeetCode #424)
 *
 * PROBLEM: Given a string and integer k, find the length of the longest
 * substring where you can replace at most k characters to make all chars same.
 *
 * APPROACH: Sliding Window + Max Frequency Tracking
 * - Maintain a frequency array for the current window.
 * - Track maxFreq = highest frequency of any single char in window.
 * - Window is valid if: (windowSize - maxFreq) <= k
 *   i.e. chars to replace = total chars - most frequent char <= k
 * - If invalid: shrink from left.
 * TIME  : O(n)
 * SPACE : O(1) — fixed int[26] array
 */
public class LongestRepeatingCharReplacement {

    /*
     * APPROACH: Sliding Window
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0, maxFreq = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            // window size - most frequent char count = chars to replace
            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));   // 4 → replace both B or A
        System.out.println(characterReplacement("AABABBA", 1)); // 4 → "AABA" or "ABBA"
        System.out.println(characterReplacement("AAAA", 2));   // 4 → already all same
        System.out.println(characterReplacement("ABCDE", 1));  // 2
    }
}

/*
Output:

4
4
4
2

*/


