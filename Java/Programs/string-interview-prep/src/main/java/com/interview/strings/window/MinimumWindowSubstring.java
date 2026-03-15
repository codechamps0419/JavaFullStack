/* Day 40 -  12/03/2026 */

package com.interview.strings.window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CodeChamps0419
 *
 * Minimum Window Substring
 *
 * LOGIC (Sliding Window with two frequency maps):
 * - Build a frequency map for all chars in pattern 't'.
 * - Expand window by moving 'right': when a needed char is found, decrement 'required'.
 * - When 'required' == 0 (all chars covered): try to shrink from 'left'.
 * - Repeat until right reaches end.
 *
 * TIME:  O(n + m)
 * SPACE: O(m)
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        int left = 0, right = 0, required = need.size();
        int minLen = Integer.MAX_VALUE, minStart = 0;
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right++);
            window.merge(c, 1, Integer::sum);
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) required--;

            while (required == 0) {
                if (right - left < minLen) { minLen = right - left; minStart = left; }
                char leftChar = s.charAt(left++);
                window.merge(leftChar, -1, Integer::sum);
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar))
                    required++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(minWindow("a", "a"));               // a
        System.out.println(minWindow("a", "aa"));              // (empty)
        System.out.println(minWindow("cabwefgewcwaefgcf", "cae")); // cwae
    }
}


