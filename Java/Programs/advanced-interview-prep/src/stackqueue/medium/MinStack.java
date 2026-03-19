package stackqueue.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Support push, pop, top and minimum lookup in constant time.
 *
 */
public class MinStack {

	/*
	 * APPROACH 1 (PRIMARY): Stack of values paired with running minimum Complexity
	 * ~ TIME : O(1) per operation, SPACE : O(n)
	 */
	static class MinStackImpl {
		record Entry(int value, int min) {
		}

		Deque<Entry> stack = new ArrayDeque<>();

		void push(int value) {
			int min = stack.isEmpty() ? value : Math.min(value, stack.peek().min());
			stack.push(new Entry(value, min));
		}

		int pop() {
			return stack.pop().value();
		}

		int top() {
			return stack.peek().value();
		}

		int min() {
			return stack.peek().min();
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		MinStackImpl stack = new MinStackImpl();
		stack.push(5);
		stack.push(2);
		stack.push(4);
		System.out.println("Min: " + stack.min());
		stack.pop();
		stack.pop();
		System.out.println("Min after pops: " + stack.min());
	}
}

/*
 * Output : Min: 2 Min after pops: 5
 */
