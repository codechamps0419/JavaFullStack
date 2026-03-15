/* Day 5 -  05/02/2026 */

package com.interview.strings.advanced;

/**
 * @author CodeChamps0419
 *
 * Longest Repeating Substring (Binary Search + Rolling Hash)
 *
 * LOGIC:
 * - Binary search on the answer length L (1 to n-1).
 * - For each candidate length L, use rolling hash to check if any substring
 *   of length L appears more than once.
 *
 * TIME:  O(n log n)
 * SPACE: O(n)
 */
public class LongestRepeatingSubstring {

    private static final long BASE = 31L;
    private static final long MOD  = 1_000_000_007L;

    public static String longestRepeatingSubstring(String s) {
        int lo = 1, hi = s.length() - 1, bestLen = 0, bestStart = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int start = search(s, mid);
            if (start != -1) { bestLen = mid; bestStart = start; lo = mid + 1; }
            else hi = mid - 1;
        }
        return s.substring(bestStart, bestStart + bestLen);
    }

    private static int search(String s, int len) {
        long hash = 0, h = 1;
        for (int i = 0; i < len - 1; i++) h = (h * BASE) % MOD;
        for (int i = 0; i < len; i++) hash = (hash * BASE + s.charAt(i)) % MOD;

        java.util.Map<Long, java.util.List<Integer>> seen = new java.util.HashMap<>();
        seen.computeIfAbsent(hash, k -> new java.util.ArrayList<>()).add(0);

        for (int i = 1; i <= s.length() - len; i++) {
            hash = (BASE * (hash - s.charAt(i - 1) * h) + s.charAt(i + len - 1)) % MOD;
            if (hash < 0) hash += MOD;
            java.util.List<Integer> positions = seen.get(hash);
            if (positions != null) {
                for (int pos : positions)
                    if (s.regionMatches(pos, s, i, len)) return i;
            }
            seen.computeIfAbsent(hash, k -> new java.util.ArrayList<>()).add(i);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(longestRepeatingSubstring("abcabc"));    // abc
        System.out.println(longestRepeatingSubstring("aabcaabdaab")); // aab
        System.out.println(longestRepeatingSubstring("aaaa"));      // aaa
        System.out.println(longestRepeatingSubstring("abcd"));      // (empty)
    }
}


