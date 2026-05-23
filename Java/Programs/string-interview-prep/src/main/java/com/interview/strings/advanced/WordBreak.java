/* Day 10 -  10/02/2026 */

package com.interview.strings.advanced;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author CodeChamps0419
 *
 * Word Break Problem (Dynamic Programming)
 *
 * LOGIC:
 * - dp[i] = true if s[0..i-1] can be segmented using words in the dictionary.
 * - Base case: dp[0] = true (empty string is always valid).
 * - Transition: dp[i] = true if dp[j]==true AND s[j..i-1] is in dictionary.
 *
 * TIME:  O(n²)
 * SPACE: O(n)
 */
public class WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++)
            for (int j = 0; j < i; j++)
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode",  List.of("leet", "code")));       // true
        System.out.println(wordBreak("applepenapple", List.of("apple", "pen")));   // true
        System.out.println(wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"))); // false
        System.out.println(wordBreak("cars", List.of("car", "ca", "rs")));         // true
    }
}


