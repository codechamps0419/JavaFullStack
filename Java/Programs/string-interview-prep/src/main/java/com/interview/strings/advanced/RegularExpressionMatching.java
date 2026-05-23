/* Day 6 -  06/02/2026 */

package com.interview.strings.advanced;

/**
 * @author CodeChamps0419
 *
 * Regular Expression Matching (LeetCode #10)
 *
 * PATTERN RULES:
 * - '.' matches any single character.
 * - '*' matches zero or more of the preceding element.
 *
 * APPROACH 1: Dynamic Programming (Bottom-Up)
 * - dp[i][j] = true if pattern[0..j-1] matches text[0..i-1].
 * - Base: dp[0][0]=true; dp[0][j]=true if pattern[0..j-1] are all x* pairs.
 * - Transition:
 *     If pattern[j-1] == '*':
 *       dp[i][j] = dp[i][j-2]                              ← zero occurrences
 *                OR (dp[i-1][j] && match(text[i-1], pattern[j-2])) ← one+ occurrences
 *     Else:
 *       dp[i][j] = dp[i-1][j-1] && match(text[i-1], pattern[j-1])
 * TIME  : O(n * m)
 * SPACE : O(n * m)
 *
 * KEY DIFFERENCE from WildcardMatching:
 * - Here '*' means zero or more of PRECEDING element (e.g. "a*" = "", "a", "aa"...)
 * - In Wildcard, '*' matches any sequence independently
 */
public class RegularExpressionMatching {

    /*
     * APPROACH: Bottom-Up DP
     * Complexity ~ TIME : O(n * m), SPACE : O(n * m)
     */
    public static boolean isMatch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        // Handle patterns like a*, a*b*, a*b*c* that can match empty string
        for (int j = 2; j <= m; j++)
            if (pattern.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char p = pattern.charAt(j - 1);
                if (p == '*') {
                    // zero occurrences of preceding element
                    dp[i][j] = dp[i][j - 2];
                    // one or more occurrences — preceding element must match current text char
                    if (matches(text.charAt(i - 1), pattern.charAt(j - 2)))
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && matches(text.charAt(i - 1), p);
                }
            }
        }
        return dp[n][m];
    }

    private static boolean matches(char textChar, char patternChar) {
        return patternChar == '.' || patternChar == textChar;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));      // false
        System.out.println(isMatch("aa", "a*"));     // true  (a* = "aa")
        System.out.println(isMatch("ab", ".*"));     // true  (.* = any sequence)
        System.out.println(isMatch("aab", "c*a*b")); // true  (c*=empty, a*="aa", b="b")
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(isMatch("", "a*"));       // true  (a* = zero a's)
        System.out.println(isMatch("", ".*"));       // true
    }
}

/*
Output:

false
true
true
true
false
true
true

*/


