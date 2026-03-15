/* Day 11 -  11/02/2026 */

package com.interview.strings.anagram;

/**
 * @author CodeChamps0419
 *
 * Check if Two Strings are Anagrams
 *
 * LOGIC (Frequency Array — optimal):
 * - Use an int[26] array for lowercase letters.
 * - Increment count for each char in s1, decrement for s2.
 * - If all counts are 0 at the end → anagrams.
 * - Avoids sorting overhead (O(n log n)) — runs in O(n).
 *
 * TIME:  O(n)
 * SPACE: O(1) — fixed size array of 26
 */
public class AnagramCheck {

    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int count : freq)
            if (count != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("listen", "silent")); // true
        System.out.println(isAnagram("hello", "world"));   // false
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));       // false
    }
}


