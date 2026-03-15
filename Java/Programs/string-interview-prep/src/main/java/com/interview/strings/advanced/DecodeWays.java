/* Day 1 -  01/02/2026 */

package com.interview.strings.advanced;

/**
 * @author CodeChamps0419
 *
 * Decode Ways (LeetCode #91)
 *
 * PROBLEM: A string of digits can be decoded where '1'→'A', '2'→'B', ..., '26'→'Z'.
 * Count the number of ways to decode a given digit string.
 *
 * APPROACH 1: Dynamic Programming (Bottom-Up)
 * - dp[i] = number of ways to decode s[0..i-1].
 * - Base: dp[0]=1 (empty string), dp[1]=1 if s[0]!='0' else 0.
 * - Transition:
 *     Single digit: if s[i-1] != '0' → dp[i] += dp[i-1]
 *     Two digits:   if s[i-2..i-1] in [10..26] → dp[i] += dp[i-2]
 * TIME  : O(n)
 * SPACE : O(n) — optimizable to O(1)
 *
 * APPROACH 2: O(1) Space DP
 * - Only need previous two dp values.
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class DecodeWays {

    /*
     * APPROACH: Bottom-Up DP
     * Complexity ~ TIME : O(n), SPACE : O(n)
     */
    public static int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // empty string
        dp[1] = 1; // single digit (already checked not '0')

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1)              dp[i] += dp[i - 1]; // valid single digit
            if (twoDigit >= 10 && twoDigit <= 26) dp[i] += dp[i - 2]; // valid two digit
        }
        return dp[n];
    }

    /*
     * APPROACH: O(1) Space DP
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static int numDecodingsOptimal(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int prev2 = 1, prev1 = 1;

        for (int i = 2; i <= s.length(); i++) {
            int curr = 0;
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1)                    curr += prev1;
            if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;

            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        // Solution 1
        System.out.println(numDecodings("12"));    // 2 → "AB" or "L"
        System.out.println(numDecodings("226"));   // 3 → "BZ","VF","BBF"
        System.out.println(numDecodings("06"));    // 0 → leading zero invalid
        System.out.println(numDecodings("11106")); // 2

        System.out.println("--- Optimal ---");

        // Solution 2
        System.out.println(numDecodingsOptimal("12"));    // 2
        System.out.println(numDecodingsOptimal("226"));   // 3
        System.out.println(numDecodingsOptimal("06"));    // 0
        System.out.println(numDecodingsOptimal("11106")); // 2
    }
}

/*
Output:

2
3
0
2
--- Optimal ---
2
3
0
2

*/


