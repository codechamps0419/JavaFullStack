/* Day 25 -  25/02/2026 */

package com.interview.strings.fundamentals;

/**
 * @author CodeChamps0419
 *
 * Check if a String is a Palindrome
 *
 * LOGIC:
 * - Strip non-alphanumeric characters, convert to lowercase.
 * - Use two pointers from both ends, compare characters inward.
 * - If all match → palindrome.
 *
 * TIME:  O(n)
 * SPACE: O(1) — no extra data structure
 */
public class PalindromeCheck {

    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("racecar"));          // true
        System.out.println(isPalindrome("A man a plan a canal Panama")); // true
        System.out.println(isPalindrome("hello"));            // false
        System.out.println(isPalindrome(""));                 // true
        System.out.println(isPalindrome("No 'x' in Nixon"));  // true
    }
}


