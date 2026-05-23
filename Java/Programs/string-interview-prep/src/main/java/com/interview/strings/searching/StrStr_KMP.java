/* Day 33 -  05/03/2026 */

package com.interview.strings.searching;

/**
 * @author CodeChamps0419
 *
 * Implement strStr() — Find First Occurrence of Substring (KMP Algorithm)
 *
 * LOGIC (KMP — Knuth-Morris-Pratt):
 * - Build an LPS (Longest Proper Prefix which is also Suffix) array for the pattern.
 *   LPS[i] = length of longest proper prefix of pattern[0..i] that is also a suffix.
 * - Use LPS to skip unnecessary comparisons when a mismatch occurs.
 * - On mismatch: don't restart from 0, jump to lps[j-1] position.
 * - On match (j == pattern.length): record index, then use lps[j-1] to continue.
 *
 * TIME:  O(n + m) — n = text length, m = pattern length
 * SPACE: O(m)     — LPS array
 */
public class StrStr_KMP {

    public static int strStr(String text, String pattern) {
        if (pattern.isEmpty()) return 0;
        int n = text.length(), m = pattern.length();
        int[] lps = buildLPS(pattern);
        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) { i++; j++; }
            if (j == m) return i - j;           // match found
            else if (i < n && text.charAt(i) != pattern.charAt(j))
                j = (j != 0) ? lps[j - 1] : 0; // use LPS to skip
            if (j == 0 && i < n && text.charAt(i) != pattern.charAt(j)) i++;
        }
        return -1;
    }

    private static int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0, i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));          // 2
        System.out.println(strStr("aaaaa", "bba"));         // -1
        System.out.println(strStr("mississippi", "issip")); // 4
        System.out.println(strStr("abcabc", "abc"));        // 0
    }
}


