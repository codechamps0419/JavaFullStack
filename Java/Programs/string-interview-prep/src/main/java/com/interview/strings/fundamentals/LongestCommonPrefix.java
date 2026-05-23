/* Day 24 -  24/02/2026 */

package com.interview.strings.fundamentals;

/**
 * @author CodeChamps0419
 *
 * Longest Common Prefix
 *
 * APPROACH 1: Vertical Scan
 * - Take first string as reference.
 * - For each character position, check if all strings have same char at that position.
 * - Stop when mismatch found or any string runs out.
 * TIME  : O(n * m) — n = number of strings, m = length of shortest string
 * SPACE : O(1)
 *
 * APPROACH 2: Binary Search on prefix length
 * - Binary search on length 1 to minLen.
 * - For each mid, check if prefix of that length is common to all strings.
 * TIME  : O(n * m * log m)
 * SPACE : O(1)
 */
public class LongestCommonPrefix {

    /*
     * APPROACH: Vertical Scan
     * Complexity ~ TIME : O(n * m), SPACE : O(1)
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    /*
     * APPROACH: Binary Search on prefix length
     * Complexity ~ TIME : O(n * m * log m), SPACE : O(1)
     */
    public static String longestCommonPrefixBinarySearch(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        for (String s : strs) minLen = Math.min(minLen, s.length());

        int lo = 1, hi = minLen;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (isCommonPrefix(strs, mid)) lo = mid + 1;
            else hi = mid - 1;
        }
        return strs[0].substring(0, (lo + hi) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int len) {
        String prefix = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(prefix)) return false;
        return true;
    }

    public static void main(String[] args) {
        // Solution 1
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // fl
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));    // ""
        System.out.println(longestCommonPrefix(new String[]{"interview", "inter", "internal"})); // inter

        // Solution 2
        System.out.println(longestCommonPrefixBinarySearch(new String[]{"flower", "flow", "flight"})); // fl
        System.out.println(longestCommonPrefixBinarySearch(new String[]{"interview", "inter", "internal"})); // inter
    }
}

/*
Output:

fl
(empty)
inter
fl
inter

*/


