/* Day 4 -  04/02/2026 */

package com.interview.strings.advanced;

/**
 * @author CodeChamps0419
 *
 * Interleaving Strings (Dynamic Programming)
 *
 * LOGIC:
 * - dp[i][j] = true if s3[0..i+j-1] is formed by interleaving s1[0..i-1] and s2[0..j-1].
 * - Transition:
 *     dp[i][j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1])
 *              OR (dp[i][j-1] && s2[j-1] == s3[i+j-1])
 *
 * TIME:  O(n * m)
 * SPACE: O(n * m)
 */
public class InterleavingStrings {

    public static boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();
        if (n + m != s3.length()) return false;

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++)
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for (int j = 1; j <= m; j++)
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                         || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));

        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac")); // true
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc")); // false
        System.out.println(isInterleave("", "", ""));                     // true
        System.out.println(isInterleave("ab", "bc", "abbc"));             // true
    }
}


