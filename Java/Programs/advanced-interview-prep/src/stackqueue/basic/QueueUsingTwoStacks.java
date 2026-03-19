package stackqueue.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Implement a FIFO queue using two LIFO stacks.
 *
 */
public class QueueUsingTwoStacks {

	/*
	 * APPROACH 1 (PRIMARY): Two stacks with amortized transfers Complexity ~ TIME :
	 * Amortized O(1) per operation, SPACE : O(n)
	 */
	static class TwoStackQueue {
		Deque<Integer> input = new ArrayDeque<>();
		Deque<Integer> output = new ArrayDeque<>();

		void offer(int value) {
			input.push(value);
		}

		int poll() {
			if (output.isEmpty()) {
				while (!input.isEmpty())
					output.push(input.pop());
			}
			if (output.isEmpty())
				throw new NoSuchElementException();
			return output.pop();
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		TwoStackQueue queue = new TwoStackQueue();
		queue.offer(10);
		queue.offer(20);
		queue.offer(30);
		System.out.println("Poll: " + queue.poll());
		System.out.println("Poll: " + queue.poll());
	}
}

/*
 * Output : Poll: 10 Poll: 20
 */
