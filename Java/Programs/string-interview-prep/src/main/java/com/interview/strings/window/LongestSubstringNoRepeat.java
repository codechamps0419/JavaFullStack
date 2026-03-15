/* Day 39 -  11/03/2026 */

package com.interview.strings.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CodeChamps0419
 *
 * Longest Substring Without Repeating Characters (Sliding Window)
 *
 * LOGIC:
 * - Use a HashMap to store character → last seen index.
 * - Maintain a 'left' pointer for the window's start.
 * - For each char at 'right':
 *     - If char was seen and its last index >= left: move left to lastIndex + 1.
 *     - Update char's latest index in map.
 *     - Update max window size: right - left + 1.
 *
 * TIME:  O(n)
 * SPACE: O(k) — k = charset size (max 128 for ASCII)
 */
public class LongestSubstringNoRepeat {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int max = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastIndex.containsKey(c) && lastIndex.get(c) >= left)
                left = lastIndex.get(c) + 1;
            lastIndex.put(c, right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3 (abc)
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // 1 (b)
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // 3 (wke)
        System.out.println(lengthOfLongestSubstring(""));         // 0
        System.out.println(lengthOfLongestSubstring("dvdf"));     // 3 (vdf)
    }
}


