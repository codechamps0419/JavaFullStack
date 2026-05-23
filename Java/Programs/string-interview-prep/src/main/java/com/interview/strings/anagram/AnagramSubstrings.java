/* Day 12 -  12/02/2026 */

package com.interview.strings.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CodeChamps0419
 *
 * Find All Anagram Substrings (Sliding Window)
 *
 * LOGIC:
 * - Use a fixed-size sliding window of length = pattern.length().
 * - Maintain two frequency arrays: one for pattern, one for current window.
 * - Slide window right: add new char, remove leftmost char.
 * - If both frequency arrays match → anagram found at that index.
 * - Arrays.equals() on int[26] is O(26) = O(1).
 *
 * TIME:  O(n) — single pass with O(1) comparison
 * SPACE: O(1) — two fixed int[26] arrays
 */
public class AnagramSubstrings {

    public static List<Integer> findAnagrams(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (text.length() < pattern.length()) return result;

        int[] pFreq = new int[26];
        int[] wFreq = new int[26];
        int pLen = pattern.length();

        for (char c : pattern.toCharArray()) pFreq[c - 'a']++;
        for (int i = 0; i < pLen; i++) wFreq[text.charAt(i) - 'a']++;

        if (Arrays.equals(pFreq, wFreq)) result.add(0);

        for (int i = pLen; i < text.length(); i++) {
            wFreq[text.charAt(i) - 'a']++;                    // add new right char
            wFreq[text.charAt(i - pLen) - 'a']--;             // remove old left char
            if (Arrays.equals(pFreq, wFreq)) result.add(i - pLen + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc")); // [0, 6]
        System.out.println(findAnagrams("abab", "ab"));        // [0, 1, 2]
        System.out.println(findAnagrams("hello", "xyz"));      // []
    }
}


