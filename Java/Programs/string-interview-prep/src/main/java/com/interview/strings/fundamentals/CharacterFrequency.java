/* Day 21 -  21/02/2026 */

package com.interview.strings.fundamentals;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author CodeChamps0419
 *
 * Count Occurrences of Each Character
 *
 * LOGIC:
 * - Use a LinkedHashMap to preserve insertion order.
 * - Iterate each character, use merge() to increment count.
 * - Stream alternative: Collectors.groupingBy with counting().
 *
 * TIME:  O(n)
 * SPACE: O(k) — k = unique characters (max 26 for lowercase alpha)
 */
public class CharacterFrequency {

    public static Map<Character, Integer> charFrequency(String s) {
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for (char c : s.toCharArray())
            freq.merge(c, 1, Integer::sum);
        return freq;
    }

    // Stream-based alternative
    public static void charFrequencyStream(String s) {
        s.chars()
         .mapToObj(c -> (char) c)
         .collect(java.util.stream.Collectors.groupingBy(c -> c, LinkedHashMap::new, java.util.stream.Collectors.counting()))
         .forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    public static void main(String[] args) {
        System.out.println(charFrequency("aabbccdde")); // {a=2, b=2, c=2, d=2, e=1}
        System.out.println(charFrequency("hello"));     // {h=1, e=1, l=2, o=1}
        System.out.println("--- Stream ---");
        charFrequencyStream("interview");
    }
}


