package stackqueue.advanced;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find the largest rectangle area in a histogram.
 *
 */
public class LargestRectangleHistogram {

	/*
	 * APPROACH 1 (PRIMARY): Monotonic increasing stack with sentinel Complexity ~
	 * TIME : O(n), SPACE : O(n)
	 */
	static int largestRectangle(int[] heights) {
		Deque<Integer> stack = new ArrayDeque<>();
		int max = 0;
		for (int i = 0; i <= heights.length; i++) {
			int height = i == heights.length ? 0 : heights[i];
			while (!stack.isEmpty() && heights[stack.peek()] > height) {
				int bar = heights[stack.pop()];
				int left = stack.isEmpty() ? -1 : stack.peek();
				int width = i - left - 1;
				max = Math.max(max, bar * width);
			}
			stack.push(i);
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Largest area: " + largestRectangle(new int[] { 2, 1, 5, 6, 2, 3 }));
	}
}

/*
 * Output : Largest area: 10
 */
