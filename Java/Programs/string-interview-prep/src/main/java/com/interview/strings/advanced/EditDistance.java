/* Day 2 -  02/02/2026 */

package com.interview.strings.advanced;

/**
 * @author CodeChamps0419
 *
 * Edit Distance — Levenshtein Distance (Dynamic Programming)
 *
 * LOGIC:
 * - dp[i][j] = min edits to convert s1[0..i-1] to s2[0..j-1].
 * - Transition:
 *     Match:    dp[i][j] = dp[i-1][j-1]
 *     Mismatch: dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
 *
 * TIME:  O(n * m)
 * SPACE: O(n * m)
 */
public class EditDistance {

    public static int minDistance(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1)
                        ? dp[i - 1][j - 1]
                        : 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));

        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));    // 3
        System.out.println(minDistance("intention", "execution")); // 5
        System.out.println(minDistance("", "abc"));         // 3
        System.out.println(minDistance("abc", "abc"));      // 0
        System.out.println(minDistance("kitten", "sitting")); // 3
    }
}


