/* Day 9 -  09/02/2026 */

package com.interview.strings.advanced;

/**
 * @author CodeChamps0419
 *
 * Wildcard Pattern Matching (Dynamic Programming)
 *
 * PATTERN RULES:
 * - '?' matches any single character.
 * - '*' matches any sequence of characters (including empty).
 *
 * LOGIC:
 * - dp[i][j] = true if pattern[0..j-1] matches text[0..i-1].
 * - If pattern[j-1] == '*': dp[i][j] = dp[i-1][j] OR dp[i][j-1]
 * - If pattern[j-1] == '?' or exact match: dp[i][j] = dp[i-1][j-1]
 *
 * TIME:  O(n * m)
 * SPACE: O(n * m)
 */
public class WildcardMatching {

    public static boolean isMatch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int j = 1; j <= m; j++)
            dp[0][j] = dp[0][j - 1] && pattern.charAt(j - 1) == '*';

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                char p = pattern.charAt(j - 1);
                if (p == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                else
                    dp[i][j] = dp[i - 1][j - 1] && (p == '?' || p == text.charAt(i - 1));
            }

        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));      // false
        System.out.println(isMatch("aa", "*"));      // true
        System.out.println(isMatch("cb", "?a"));     // false
        System.out.println(isMatch("adceb", "*a*b")); // true
        System.out.println(isMatch("acdcb", "a*c?b")); // false
        System.out.println(isMatch("", "*"));        // true
    }
}


