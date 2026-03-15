/* Day 32 -  04/03/2026 */

package com.interview.strings.searching;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeChamps0419
 *
 * Find All Occurrences of Pattern (Rabin-Karp Algorithm)
 *
 * LOGIC (Rolling Hash):
 * - Compute hash of pattern and first window of text.
 * - Slide window: remove leftmost char hash, add new rightmost char hash.
 * - On hash match: verify character by character (to handle hash collisions).
 *
 * TIME:  O(n + m) average, O(n*m) worst case (many hash collisions)
 * SPACE: O(1)
 */
public class RabinKarpSearch {

    private static final int BASE = 31;
    private static final long MOD = 1_000_000_007L;

    public static List<Integer> findAllOccurrences(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m > n) return result;

        long h = 1, patHash = 0, winHash = 0;
        for (int i = 0; i < m - 1; i++) h = (h * BASE) % MOD;

        for (int i = 0; i < m; i++) {
            patHash = (patHash * BASE + pattern.charAt(i)) % MOD;
            winHash = (winHash * BASE + text.charAt(i)) % MOD;
        }

        for (int i = 0; i <= n - m; i++) {
            if (patHash == winHash && text.regionMatches(i, pattern, 0, m))
                result.add(i);
            if (i < n - m) {
                winHash = (BASE * (winHash - text.charAt(i) * h) + text.charAt(i + m)) % MOD;
                if (winHash < 0) winHash += MOD;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAllOccurrences("aabaacaadaabaaba", "aaba")); // [0, 9, 12]
        System.out.println(findAllOccurrences("abcabc", "abc"));            // [0, 3]
        System.out.println(findAllOccurrences("hello", "xyz"));             // []
    }
}


