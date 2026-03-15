/* Day 26 -  26/02/2026 */

package com.interview.strings.fundamentals;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author CodeChamps0419
 *
 * Remove Duplicate Characters (Preserve Order)
 *
 * LOGIC:
 * - Use a LinkedHashSet which maintains insertion order and ignores duplicates.
 * - Iterate each character; add to set (duplicates auto-ignored).
 * - Build result string from the set.
 *
 * TIME:  O(n)
 * SPACE: O(k) — k = unique characters
 */
public class RemoveDuplicates {

    public static String removeDuplicates(String s) {
        Set<Character> seen = new LinkedHashSet<>();
        for (char c : s.toCharArray()) seen.add(c);
        StringBuilder sb = new StringBuilder();
        seen.forEach(sb::append);
        return sb.toString();
    }

    // Stream alternative
    public static String removeDuplicatesStream(String s) {
        return s.chars()
                .distinct()
                .collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append)
                .toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("programming"));       // progamin
        System.out.println(removeDuplicates("aabbcc"));            // abc
        System.out.println(removeDuplicatesStream("hello world")); // helo wrd
    }
}


