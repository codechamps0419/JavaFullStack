/* Day 34 -  06/03/2026 */

package com.interview.strings.searching;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeChamps0419
 *
 * Z-Algorithm — Pattern Search
 *
 * PROBLEM: Find all occurrences of pattern in text.
 *
 * Z-ARRAY:
 * - Z[i] = length of longest substring starting at i that is also a prefix of the string.
 * - Z[0] is undefined (or set to string length).
 *
 * APPROACH: Concatenate pattern + '$' + text → build Z-array.
 * - Wherever Z[i] == pattern.length() → pattern found at index i - (m+1).
 * - '$' separator ensures Z values don't cross the boundary.
 * TIME  : O(n + m)
 * SPACE : O(n + m) — for concatenated string and Z-array
 */
public class ZAlgorithm {

    /*
     * APPROACH: Z-Algorithm
     * Complexity ~ TIME : O(n + m), SPACE : O(n + m)
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        String concat = pattern + "$" + text;
        int[] z = buildZArray(concat);
        int m = pattern.length();

        for (int i = m + 1; i < concat.length(); i++) {
            if (z[i] == m)
                result.add(i - m - 1); // index in original text
        }
        return result;
    }

    private static int[] buildZArray(String s) {
        int n = s.length();
        int[] z = new int[n];
        int left = 0, right = 0;

        for (int i = 1; i < n; i++) {
            if (i < right)
                z[i] = Math.min(right - i, z[i - left]);

            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i]))
                z[i]++;

            if (i + z[i] > right) {
                left = i;
                right = i + z[i];
            }
        }
        return z;
    }

    public static void main(String[] args) {
        System.out.println(search("aabaacaadaabaaba", "aaba")); // [0, 9, 12]
        System.out.println(search("abcabc", "abc"));            // [0, 3]
        System.out.println(search("hello", "xyz"));             // []
        System.out.println(search("aaaaaa", "aa"));             // [0, 1, 2, 3, 4]
    }
}

/*
Output:

[0, 9, 12]
[0, 3]
[]
[0, 1, 2, 3, 4]

*/


