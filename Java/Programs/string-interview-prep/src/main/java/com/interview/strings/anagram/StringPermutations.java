/* Day 13 -  13/02/2026 */

package com.interview.strings.anagram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author CodeChamps0419
 *
 * Generate All Permutations of a String
 *
 * LOGIC:
 * - Use backtracking: swap current index with every index ahead of it.
 * - Recurse with index+1, then swap back (backtrack) to restore state.
 * - For duplicates: use a HashSet at each recursion level to skip already-used chars.
 *
 * TIME:  O(n * n!) — n! permutations, each takes O(n) to build
 * SPACE: O(n)     — recursion stack depth
 */
public class StringPermutations {

    public static List<String> permutations(String s) {
        List<String> result = new ArrayList<>();
        permute(s.toCharArray(), 0, result);
        return result;
    }

    private static void permute(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            if (used.add(chars[i])) {           // skip duplicate chars at same level
                swap(chars, index, i);
                permute(chars, index + 1, result);
                swap(chars, index, i);          // backtrack
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(permutations("abc"));  // [abc, acb, bac, bca, cab, cba]
        System.out.println(permutations("aab").size()); // 3 unique (no duplicates)
        System.out.println(permutations("aab"));
    }
}


