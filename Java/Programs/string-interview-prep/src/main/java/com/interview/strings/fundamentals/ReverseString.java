/* Day 27 -  27/02/2026 */

package com.interview.strings.fundamentals;

/**
 * @author CodeChamps0419
 *
 * Reverse a String
 *
 * LOGIC:
 * - Use two-pointer technique: one at start, one at end.
 * - Swap characters moving inward until pointers meet.
 * - Works correctly for Unicode surrogate pairs using codePoints.
 *
 * TIME:  O(n)
 * SPACE: O(n) — char array copy (in-place swap on array)
 */
public class ReverseString {

    // Iterative — two pointer
    public static String reverse(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
        return new String(chars);
    }

    // Recursive
    public static String reverseRecursive(String s) {
        if (s.length() <= 1) return s;
        return reverseRecursive(s.substring(1)) + s.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(reverse("hello"));           // olleh
        System.out.println(reverse("Java"));            // avaJ
        System.out.println(reverseRecursive("world"));  // dlrow
        System.out.println(reverse(""));                // (empty)
        System.out.println(reverse("a"));               // a
    }
}


