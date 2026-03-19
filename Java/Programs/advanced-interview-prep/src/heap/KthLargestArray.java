package heap;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find kth largest (counting duplicates) in an unsorted array.
 *
 */
public class KthLargestArray {

	/*
	 * APPROACH 1 (PRIMARY): Min heap of size k Complexity ~ TIME : O(n log k),
	 * SPACE : O(k)
	 */
	static int kthLargest(int[] values, int k) {
		if (k < 1 || k > values.length)
			throw new IllegalArgumentException("Invalid k");
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int value : values) {
			heap.offer(value);
			if (heap.size() > k)
				heap.poll();
		}
		return heap.peek();
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("2nd largest: " + kthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
	}
}

/*
 * Output : 2nd largest: 5
 */
