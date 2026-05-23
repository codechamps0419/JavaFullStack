/* Day 15 -  15/02/2026 */

package com.interview.strings.compression;

/**
 * @author CodeChamps0419
 *
 * Run-Length Encoding (RLE Compression)
 *
 * LOGIC:
 * - Iterate through the string, count consecutive identical characters.
 * - When character changes (or end reached): append char + count to result.
 * - Only append count if > 1 (optional: always append for strict RLE).
 *
 * TIME:  O(n)
 * SPACE: O(n) — output string
 */
public class RunLengthEncoding {

    public static String encode(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(s.charAt(i - 1));
                if (count > 1) sb.append(count);
                count = 1;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(encode("aaabbbcc"));    // a3b3c2
        System.out.println(encode("aabcccdddd"));  // a2bc3d4
        System.out.println(encode("abcd"));        // abcd (no compression)
        System.out.println(encode("aaaaa"));       // a5
    }
}


