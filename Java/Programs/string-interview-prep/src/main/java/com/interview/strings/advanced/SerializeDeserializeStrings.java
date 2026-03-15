/* Day 7 -  07/02/2026 */

package com.interview.strings.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeChamps0419
 *
 * Serialize and Deserialize a List of Strings
 *
 * LOGIC (Length-Prefix Encoding):
 * - Encode: for each string, prepend its length + '#'.
 *   Format: "4#word3#foo0#"
 * - Decode: read digits until '#', parse as length, read exactly that many chars.
 *
 * TIME:  O(n) encode, O(n) decode
 * SPACE: O(n)
 */
public class SerializeDeserializeStrings {

    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) sb.append(s.length()).append('#').append(s);
        return sb.toString();
    }

    public static List<String> decode(String encoded) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < encoded.length()) {
            int j = encoded.indexOf('#', i);
            int len = Integer.parseInt(encoded.substring(i, j));
            result.add(encoded.substring(j + 1, j + 1 + len));
            i = j + 1 + len;
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> original = List.of("hello", "world", "", "foo#bar", "42");
        String encoded = encode(original);
        System.out.println("Encoded: " + encoded);
        List<String> decoded = decode(encoded);
        System.out.println("Decoded: " + decoded);
        System.out.println("Match:   " + original.equals(decoded)); // true

        List<String> empty = List.of();
        System.out.println(decode(encode(empty))); // []

        List<String> single = List.of("only");
        System.out.println(decode(encode(single))); // [only]
    }
}


