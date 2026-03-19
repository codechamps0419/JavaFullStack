package heap;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Maintain median under repeated integer insertion.
 *
 */
public class MedianFromDataStream {

	/*
	 * APPROACH 1 (PRIMARY): Max heap for lower half and min heap for upper half
	 * Complexity ~ TIME : O(log n) add, O(1) median, SPACE : O(n)
	 */
	static class MedianFinder {
		PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> upper = new PriorityQueue<>();

		void add(int value) {
			lower.offer(value);
			upper.offer(lower.poll());
			if (upper.size() > lower.size())
				lower.offer(upper.poll());
		}

		double median() {
			if (lower.isEmpty())
				throw new NoSuchElementException("No values");
			return lower.size() > upper.size() ? lower.peek() : ((long) lower.peek() + upper.peek()) / 2.0;
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		MedianFinder finder = new MedianFinder();
		finder.add(1);
		finder.add(2);
		System.out.println("Median: " + finder.median());
		finder.add(3);
		System.out.println("Median: " + finder.median());
	}
}

/*
 * Output : Median: 1.5 Median: 2.0
 */
