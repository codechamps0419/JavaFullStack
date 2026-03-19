package heap;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return the k most frequent integer values.
 *
 */
public class TopKFrequentElements {

	/*
	 * APPROACH 1 (PRIMARY): Frequency HashMap + min heap Complexity ~ TIME : O(n
	 * log k), SPACE : O(u + k)
	 */
	record Entry(int value, int count) {
	}

	static List<Integer> topK(int[] values, int k) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int value : values)
			freq.merge(value, 1, Integer::sum);
		if (k < 1 || k > freq.size())
			throw new IllegalArgumentException("Invalid k");
		PriorityQueue<Entry> heap = new PriorityQueue<>(
				Comparator.comparingInt(Entry::count).thenComparing(Entry::value));
		for (var e : freq.entrySet()) {
			heap.offer(new Entry(e.getKey(), e.getValue()));
			if (heap.size() > k)
				heap.poll();
		}
		List<Integer> result = new ArrayList<>();
		while (!heap.isEmpty())
			result.add(heap.poll().value());
		Collections.reverse(result);
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Top 2: " + topK(new int[] { 1, 1, 1, 2, 2, 3 }, 2));
	}
}

/*
 * Output : Top 2: [1, 2]
 */
