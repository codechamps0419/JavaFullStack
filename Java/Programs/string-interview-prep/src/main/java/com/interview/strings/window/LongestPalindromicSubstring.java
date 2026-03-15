/* Day 36 -  08/03/2026 */

package com.interview.strings.window;

/**
 * @author CodeChamps0419
 *
 * Longest Palindromic Substring (Expand Around Center)
 *
 * LOGIC:
 * - For each character (and each gap between characters), try to expand outward.
 * - Two cases per center:
 *     1. Odd-length palindrome:  center at index i → expand(i, i)
 *     2. Even-length palindrome: center between i and i+1 → expand(i, i+1)
 * - Expand while left >= 0, right < n, and chars match.
 *
 * TIME:  O(n²)
 * SPACE: O(1)
 */
public class LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {
        if (s.length() < 2) return s;
        int start = 0, maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            int odd  = expand(s, i, i);
            int even = expand(s, i, i + 1);
            int len  = Math.max(odd, even);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));   // bab or aba
        System.out.println(longestPalindrome("cbbd"));    // bb
        System.out.println(longestPalindrome("racecar")); // racecar
        System.out.println(longestPalindrome("a"));       // a
    }
}


