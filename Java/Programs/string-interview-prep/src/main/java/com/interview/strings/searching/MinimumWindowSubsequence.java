/* Day 31 -  03/03/2026 */

package com.interview.strings.searching;

/**
 * @author CodeChamps0419
 *
 * Minimum Window Subsequence (LeetCode #727)
 *
 * PROBLEM: Find the minimum length substring of s1 such that s2 is a
 * subsequence of that substring. Unlike MinimumWindowSubstring, order matters.
 *
 * APPROACH: Two Pointer
 * - Forward pass: find end of window where s2 is a subsequence.
 * - Backward pass: shrink from end to find minimum start.
 * - Track minimum window found.
 * TIME  : O(n * m) — n = s1 length, m = s2 length
 * SPACE : O(1)
 */
public class MinimumWindowSubsequence {

    /*
     * APPROACH: Two Pointer (forward + backward pass)
     * Complexity ~ TIME : O(n * m), SPACE : O(1)
     */
    public static String minWindowSubsequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        String result = "";
        int i = 0;

        while (i < n) {
            // Forward pass: find end where s2 is a subsequence
            int j = 0;
            while (i < n && j < m) {
                if (s1.charAt(i) == s2.charAt(j)) j++;
                i++;
            }
            if (j < m) break; // s2 not found as subsequence

            // Backward pass: shrink from end to find minimum start
            int end = i;
            j = m - 1;
            while (j >= 0) {
                if (s1.charAt(i - 1) == s2.charAt(j)) j--;
                i--;
            }
            // window is s1[i..end-1]
            if (result.isEmpty() || end - i < result.length())
                result = s1.substring(i, end);

            i++; // move past current start to find next window
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minWindowSubsequence("abcdebdde", "bde")); // "bcde"
        System.out.println(minWindowSubsequence("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u")); // ""
        System.out.println(minWindowSubsequence("cnhczmccqouqadqtmjjzl", "mm")); // "mccqouqadqtm"
    }
}

/*
Output:

bcde
(empty)
mccqouqadqtm

*/


