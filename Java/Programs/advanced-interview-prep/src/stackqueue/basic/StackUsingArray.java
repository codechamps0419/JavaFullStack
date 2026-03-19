package stackqueue.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Implement push, pop and peek with an array.
 *
 */
public class StackUsingArray {

	/*
	 * APPROACH 1 (PRIMARY): Array-backed bounded stack Complexity ~ TIME : O(1) per
	 * operation, SPACE : O(capacity)
	 */
	static class IntStack {
		private final int[] data;
		private int size;

		IntStack(int capacity) {
			data = new int[capacity];
		}

		void push(int value) {
			if (size == data.length)
				throw new IllegalStateException("Stack full");
			data[size++] = value;
		}

		int pop() {
			if (size == 0)
				throw new NoSuchElementException("Stack empty");
			return data[--size];
		}

		int peek() {
			if (size == 0)
				throw new NoSuchElementException("Stack empty");
			return data[size - 1];
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		IntStack stack = new IntStack(3);
		stack.push(10);
		stack.push(20);
		System.out.println("Top: " + stack.peek());
		System.out.println("Pop: " + stack.pop());
	}
}

/*
 * Output : Top: 20 Pop: 20
 */
