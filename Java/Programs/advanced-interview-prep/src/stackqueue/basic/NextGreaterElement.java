package stackqueue.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * For every array value, return the next greater value to its right or -1.
 *
 */
public class NextGreaterElement {

	/*
	 * APPROACH 1 (PRIMARY): Monotonic decreasing stack of indices Complexity ~ TIME
	 * : O(n), SPACE : O(n)
	 */
	static int[] nextGreater(int[] values) {
		int[] answer = new int[values.length];
		Arrays.fill(answer, -1);
		Deque<Integer> indices = new ArrayDeque<>();
		for (int i = 0; i < values.length; i++) {
			while (!indices.isEmpty() && values[i] > values[indices.peek()]) {
				answer[indices.pop()] = values[i];
			}
			indices.push(i);
		}
		return answer;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println(Arrays.toString(nextGreater(new int[] { 4, 5, 2, 10, 8 })));
	}
}

/*
 * Output : [5, 10, 10, -1, -1]
 */
