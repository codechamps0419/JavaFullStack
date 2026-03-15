/* Day 30 -  02/03/2026 */
package com.interview.strings.searching;

/**
 * @author CodeChamps0419
 *
 * Longest Common Substring (Dynamic Programming)
 *
 * LOGIC:
 * - Build a 2D DP table where dp[i][j] = length of common substring ending at s1[i-1] and s2[j-1].
 * - If s1[i-1] == s2[j-1]: dp[i][j] = dp[i-1][j-1] + 1
 * - Else: dp[i][j] = 0 (substring must be contiguous, so reset)
 * - Track the maximum value and its ending index to extract the substring.
 *
 * TIME:  O(n * m)
 * SPACE: O(n * m)
 */
public class LongestCommonSubstring {

    public static String longestCommonSubstring(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        int maxLen = 0, endIndex = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return s1.substring(endIndex - maxLen, endIndex);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("abcdef", "zcdemf"));  // cde
        System.out.println(longestCommonSubstring("OldSite", "NewSite")); // Site
        System.out.println(longestCommonSubstring("abc", "xyz"));         // (empty)
    }
}


