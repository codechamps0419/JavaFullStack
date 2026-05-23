/* Day 19 -  19/02/2026 */

package com.interview.strings.conversion;

/**
 * @author CodeChamps0419
 *
 * String to Integer — myAtoi()
 *
 * LOGIC:
 * Step 1: Strip leading whitespace.
 * Step 2: Read optional sign ('+' or '-').
 * Step 3: Read digits until non-digit or end of string.
 * Step 4: Clamp result to [Integer.MIN_VALUE, Integer.MAX_VALUE] on overflow.
 *
 * TIME:  O(n)
 * SPACE: O(1)
 */
public class StringToInteger {

    public static int myAtoi(String s) {
        s = s.strip();
        if (s.isEmpty()) return 0;

        int sign = 1, i = 0;
        if (s.charAt(0) == '-') { sign = -1; i++; }
        else if (s.charAt(0) == '+') i++;

        long result = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i++) - '0');
            if (result * sign >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (result * sign <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int) (result * sign);
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));               // 42
        System.out.println(myAtoi("  -42"));            // -42
        System.out.println(myAtoi("4193 with words"));  // 4193
        System.out.println(myAtoi("words and 987"));    // 0
        System.out.println(myAtoi("99999999999"));      // 2147483647
        System.out.println(myAtoi("-99999999999"));     // -2147483648
    }
}


