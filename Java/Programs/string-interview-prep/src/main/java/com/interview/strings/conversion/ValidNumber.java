/* Day 20 -  20/02/2026 */

package com.interview.strings.conversion;

/**
 * @author CodeChamps0419
 *
 * Check if a String is a Valid Number
 *
 * LOGIC (State-based single pass):
 * - Track flags: seenDigit, seenDot, seenE (for scientific notation).
 * - '+'/'-' only valid at start or right after 'e'/'E'.
 * - '.' only valid once and not after 'e'/'E'.
 * - 'e'/'E' only valid once and must have digit before it.
 *
 * TIME:  O(n)
 * SPACE: O(1)
 */
public class ValidNumber {

    public static boolean isValidNumber(String s) {
        s = s.strip();
        boolean seenDigit = false, seenDot = false, seenE = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
            } else if (c == '.') {
                if (seenDot || seenE) return false;
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                if (seenE || !seenDigit) return false;
                seenE = true;
                seenDigit = false;
            } else {
                return false;
            }
        }
        return seenDigit;
    }

    public static void main(String[] args) {
        System.out.println(isValidNumber("2"));       // true
        System.out.println(isValidNumber("-3.14"));   // true
        System.out.println(isValidNumber("1e10"));    // true
        System.out.println(isValidNumber("2.5e-3"));  // true
        System.out.println(isValidNumber("abc"));     // false
        System.out.println(isValidNumber("1e"));      // false
        System.out.println(isValidNumber("1.2.3"));   // false
        System.out.println(isValidNumber("."));       // false
        System.out.println(isValidNumber("+.5"));     // true
    }
}


