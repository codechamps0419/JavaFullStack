/* Day 14 -  14/02/2026 */

package com.interview.strings.compression;

/**
 * @author CodeChamps0419
 *
 * Decode a Run-Length Encoded String
 *
 * LOGIC:
 * - Iterate through the encoded string.
 * - When a letter is found: read ahead to collect all digit characters as count.
 * - Append the letter 'count' times to the result.
 * - If no digit follows a letter, append it once.
 *
 * TIME:  O(n + m) — n = encoded length, m = decoded length
 * SPACE: O(m)     — decoded output
 */
public class RunLengthDecoding {

    public static String decode(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);
            StringBuilder numStr = new StringBuilder();
            while (i < s.length() && Character.isDigit(s.charAt(i)))
                numStr.append(s.charAt(i++));
            int count = numStr.isEmpty() ? 1 : Integer.parseInt(numStr.toString());
            sb.append(String.valueOf(ch).repeat(count));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(decode("a3b3c2"));   // aaabbbcc
        System.out.println(decode("a2bc3d4"));  // aabcccdddd
        System.out.println(decode("a5"));       // aaaaa
        System.out.println(decode("abcd"));     // abcd
    }
}


