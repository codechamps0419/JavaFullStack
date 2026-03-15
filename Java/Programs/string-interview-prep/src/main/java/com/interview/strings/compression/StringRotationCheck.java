/* Day 16 -  16/02/2026 */

package com.interview.strings.compression;

/**
 * @author CodeChamps0419
 *
 * Check if a String is a Rotation of Another
 *
 * LOGIC (Concatenation Trick):
 * - If s2 is a rotation of s1, then s2 must appear as a substring in s1+s1.
 * - First check lengths are equal (necessary condition).
 *
 * TIME:  O(n)
 * SPACE: O(n) — for the concatenated string
 */
public class StringRotationCheck {

    public static boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        return (s1 + s1).contains(s2);
    }

    public static void main(String[] args) {
        System.out.println(isRotation("abcde", "cdeab")); // true
        System.out.println(isRotation("abcde", "abced")); // false
        System.out.println(isRotation("hello", "llohe")); // true
        System.out.println(isRotation("abc", "abcd"));    // false
    }
}


