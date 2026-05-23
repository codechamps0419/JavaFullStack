/* Day 35 -  07/03/2026 */

package com.interview.strings.window;

/**
 * @author CodeChamps0419
 *
 * Count All Palindromic Substrings
 *
 * LOGIC:
 * - Same expand-around-center approach as LongestPalindromicSubstring.
 * - For each center (odd and even), count how many palindromes can be expanded.
 * - Each successful expansion = one palindromic substring.
 *
 * TIME:  O(n²)
 * SPACE: O(1)
 */
public class CountPalindromicSubstrings {

    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i);     // odd-length
            count += expand(s, i, i + 1); // even-length
        }
        return count;
    }

    private static int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));    // 3
        System.out.println(countSubstrings("aaa"));    // 6
        System.out.println(countSubstrings("racecar")); // 10
        System.out.println(countSubstrings("abba"));   // 6
    }
}


