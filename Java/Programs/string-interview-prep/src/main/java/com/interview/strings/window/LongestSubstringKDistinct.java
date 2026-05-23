/* Day 38 -  10/03/2026 */

package com.interview.strings.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CodeChamps0419
 *
 * Longest Substring with At Most K Distinct Characters (LeetCode #340)
 *
 * APPROACH: Sliding Window + HashMap
 * - Maintain a frequency map of chars in current window.
 * - Expand right: add char to map.
 * - When distinct chars > k: shrink from left until distinct <= k.
 * - Track max window size.
 * TIME  : O(n)
 * SPACE : O(k) — map holds at most k+1 entries
 */
public class LongestSubstringKDistinct {

    /*
     * APPROACH: Sliding Window + HashMap
     * Complexity ~ TIME : O(n), SPACE : O(k)
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            while (freq.size() > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) freq.remove(leftChar);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));  // 3 → "ece"
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));     // 2 → "aa"
        System.out.println(lengthOfLongestSubstringKDistinct("abcadcacacaca", 3)); // 11
        System.out.println(lengthOfLongestSubstringKDistinct("abc", 0));    // 0
    }
}

/*
Output:

3
2
11
0

*/


