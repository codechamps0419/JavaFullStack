package stackqueue.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Check whether brackets are correctly nested and balanced.
 *
 */
public class ValidParentheses {

	/*
	 * APPROACH 1 (PRIMARY): Iterative stack matching Complexity ~ TIME : O(n),
	 * SPACE : O(n)
	 */
	static boolean isValid(String text) {
		Deque<Character> stack = new ArrayDeque<>();
		for (char c : text.toCharArray()) {
			if (c == '(' || c == '[' || c == '{')
				stack.push(c);
			else if (c == ')' || c == ']' || c == '}') {
				if (stack.isEmpty())
					return false;
				char open = stack.pop();
				if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{'))
					return false;
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Valid: " + isValid("{[()]}"));
	}
}

/*
 * Output : Valid: true
 */
