package stackqueue.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Implement a fixed-size FIFO queue using a circular array.
 *
 */
public class CircularQueue {

	/*
	 * APPROACH 1 (PRIMARY): Modular front/rear indexing Complexity ~ TIME : O(1)
	 * per operation, SPACE : O(capacity)
	 */
	static class CircularIntQueue {
		int[] data;
		int front, size;

		CircularIntQueue(int capacity) {
			data = new int[capacity];
		}

		boolean offer(int value) {
			if (size == data.length)
				return false;
			data[(front + size) % data.length] = value;
			size++;
			return true;
		}

		int poll() {
			if (size == 0)
				throw new NoSuchElementException();
			int value = data[front];
			front = (front + 1) % data.length;
			size--;
			return value;
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		CircularIntQueue q = new CircularIntQueue(2);
		q.offer(10);
		q.offer(20);
		System.out.println("Poll: " + q.poll());
		q.offer(30);
		System.out.println("Poll: " + q.poll());
		System.out.println("Poll: " + q.poll());
	}
}

/*
 * Output : Poll: 10 Poll: 20 Poll: 30
 */
