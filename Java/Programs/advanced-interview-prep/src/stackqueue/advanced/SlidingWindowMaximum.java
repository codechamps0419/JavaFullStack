package stackqueue.advanced;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return the maximum of every consecutive window of size k.
 *
 */
public class SlidingWindowMaximum {

	/*
	 * APPROACH 1 (PRIMARY): Monotonic deque of indices Complexity ~ TIME : O(n),
	 * SPACE : O(k)
	 */
	static int[] slidingMax(int[] nums, int k) {
		if (k < 1 || k > nums.length)
			throw new IllegalArgumentException("Invalid k");
		int[] answer = new int[nums.length - k + 1];
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < nums.length; i++) {
			while (!deque.isEmpty() && deque.peekFirst() <= i - k)
				deque.removeFirst();
			while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i])
				deque.removeLast();
			deque.addLast(i);
			if (i >= k - 1)
				answer[i - k + 1] = nums[deque.peekFirst()];
		}
		return answer;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println(Arrays.toString(slidingMax(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
	}
}

/*
 * Output : [3, 3, 5, 5, 6, 7]
 */
