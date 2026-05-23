/* Day 29 -  01/03/2026 */

package com.interview.strings.searching;

/**
 * @author CodeChamps0419
 *
 * Longest Common Subsequence (LCS) — Dynamic Programming
 *
 * LOGIC:
 * - dp[i][j] = LCS length of s1[0..i-1] and s2[0..j-1].
 * - If s1[i-1] == s2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
 * - Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * - Backtrack through dp table to reconstruct the actual subsequence.
 *
 * TIME:  O(n * m)
 * SPACE: O(n * m)
 */
public class LongestCommonSubsequence {

    public static String lcs(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1)
                        ? dp[i - 1][j - 1] + 1
                        : Math.max(dp[i - 1][j], dp[i][j - 1]);

        StringBuilder sb = new StringBuilder();
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--; j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) i--;
            else j--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(lcs("abcde", "ace"));       // ace
        System.out.println(lcs("AGGTAB", "GXTXAYB"));  // GTAB
        System.out.println(lcs("abc", "abc"));          // abc
        System.out.println(lcs("abc", "def"));          // (empty)
    }
}


