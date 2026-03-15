/* Day 28 -  28/02/2026 */

package com.interview.strings.fundamentals;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * @author CodeChamps0419
 *
 * Valid Parentheses
 *
 * APPROACH 1: Stack
 * - Push opening brackets onto stack.
 * - On closing bracket: check if top of stack is matching opening bracket.
 * - At end: stack must be empty.
 * TIME  : O(n)
 * SPACE : O(n)
 *
 * APPROACH 2: Counter (only for single bracket type)
 * - Works only when input has just '(' and ')'.
 * - Increment for '(', decrement for ')'.
 * - If counter goes negative → invalid. At end must be 0.
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class ValidParentheses {

    /*
     * APPROACH: Stack (handles all bracket types)
     * Complexity ~ TIME : O(n), SPACE : O(n)
     */
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    /*
     * APPROACH: Counter (only for single bracket type '(' and ')')
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static boolean isValidSingleType(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false; // more closing than opening
        }
        return count == 0;
    }

    public static void main(String[] args) {
        // Solution 1
        System.out.println(isValid("()"));        // true
        System.out.println(isValid("()[]{}"));    // true
        System.out.println(isValid("(]"));        // false
        System.out.println(isValid("([)]"));      // false
        System.out.println(isValid("{[]}"));      // true
        System.out.println(isValid(""));          // true

        // Solution 2
        System.out.println(isValidSingleType("(())")); // true
        System.out.println(isValidSingleType("(()"));  // false
        System.out.println(isValidSingleType(")("));   // false
    }
}

/*
Output:

true
true
false
false
true
true
true
false
false

*/


