package stackqueue.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Evaluate an arithmetic expression written in reverse Polish notation.
 *
 */
public class EvaluateReversePolishNotation {

	/*
	 * APPROACH 1 (PRIMARY): Iterative operand stack Complexity ~ TIME : O(n), SPACE
	 * : O(n)
	 */
	static int evaluate(String[] tokens) {
		Deque<Integer> stack = new ArrayDeque<>();
		for (String token : tokens) {
			if (Set.of("+", "-", "*", "/").contains(token)) {
				int b = stack.pop(), a = stack.pop();
				int value = switch (token) {
				case "+" -> a + b;
				case "-" -> a - b;
				case "*" -> a * b;
				default -> a / b;
				};
				stack.push(value);
			} else
				stack.push(Integer.parseInt(token));
		}
		if (stack.size() != 1)
			throw new IllegalArgumentException("Malformed expression");
		return stack.pop();
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Result: " + evaluate(new String[] { "2", "1", "+", "3", "*" }));
	}
}

/*
 * Output : Result: 9
 */
