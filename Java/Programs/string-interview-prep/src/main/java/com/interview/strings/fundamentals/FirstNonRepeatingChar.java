/* Day 23 -  23/02/2026 */

package com.interview.strings.fundamentals;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author CodeChamps0419
 *
 * First Non-Repeating Character
 *
 * LOGIC:
 * - Pass 1: Build a LinkedHashMap of character → frequency (preserves order).
 * - Pass 2: Iterate map entries, return first key with frequency == 1.
 * - LinkedHashMap ensures we check characters in their original appearance order.
 *
 * TIME:  O(n)
 * SPACE: O(k) — k = unique characters
 */
public class FirstNonRepeatingChar {

    public static char firstNonRepeating(String s) {
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : s.toCharArray()) freq.merge(c, 1, Integer::sum);
        return freq.entrySet().stream()
                   .filter(e -> e.getValue() == 1)
                   .map(Map.Entry::getKey)
                   .findFirst()
                   .orElse('_'); // '_' signals no unique char found
    }

    public static void main(String[] args) {
        System.out.println(firstNonRepeating("aabbcde"));    // c
        System.out.println(firstNonRepeating("leetcode"));   // l
        System.out.println(firstNonRepeating("aabb"));       // _
        System.out.println(firstNonRepeating("interview"));  // i
    }
}


