/* Day 22 -  22/02/2026 */

package com.interview.strings.fundamentals;

/**
 * @author CodeChamps0419
 *
 * Count and Say
 *
 * SEQUENCE:
 * n=1 → "1"
 * n=2 → "11"       (one 1)
 * n=3 → "21"       (two 1s)
 * n=4 → "1211"     (one 2, one 1)
 * n=5 → "111221"   (one 1, one 2, two 1s)
 *
 * APPROACH 1: Iterative — build each term from previous
 * - Start with "1", iterate n-1 times.
 * - For each term: scan consecutive same chars, append count + char.
 * TIME  : O(n * m) — m = length of nth term
 * SPACE : O(m)
 *
 * APPROACH 2: Recursive
 * - Base case: n==1 → return "1".
 * - Recursive case: generate term from countAndSay(n-1).
 * TIME  : O(n * m)
 * SPACE : O(n) — recursion stack
 */
public class CountAndSay {

    /*
     * APPROACH: Iterative
     * Complexity ~ TIME : O(n * m), SPACE : O(m)
     */
    public static String countAndSay(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int j = 0;
            while (j < result.length()) {
                char c = result.charAt(j);
                int count = 0;
                while (j < result.length() && result.charAt(j) == c) {
                    j++;
                    count++;
                }
                sb.append(count).append(c);
            }
            result = sb.toString();
        }
        return result;
    }

    /*
     * APPROACH: Recursive
     * Complexity ~ TIME : O(n * m), SPACE : O(n)
     */
    public static String countAndSayRecursive(int n) {
        if (n == 1) return "1";
        String prev = countAndSayRecursive(n - 1);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < prev.length()) {
            char c = prev.charAt(i);
            int count = 0;
            while (i < prev.length() && prev.charAt(i) == c) { i++; count++; }
            sb.append(count).append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Solution 1
        for (int i = 1; i <= 6; i++)
            System.out.println("n=" + i + " : " + countAndSay(i));

        System.out.println("--- Recursive ---");

        // Solution 2
        for (int i = 1; i <= 6; i++)
            System.out.println("n=" + i + " : " + countAndSayRecursive(i));
    }
}

/*
Output:

n=1 : 1
n=2 : 11
n=3 : 21
n=4 : 1211
n=5 : 111221
n=6 : 312211
--- Recursive ---
n=1 : 1
n=2 : 11
n=3 : 21
n=4 : 1211
n=5 : 111221
n=6 : 312211

*/


