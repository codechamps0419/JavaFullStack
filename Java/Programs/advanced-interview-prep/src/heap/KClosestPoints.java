package heap;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find k 2D points with shortest Euclidean distance from origin.
 *
 */
public class KClosestPoints {

	/*
	 * APPROACH 1 (PRIMARY): Max heap of size k on squared distance Complexity ~
	 * TIME : O(n log k), SPACE : O(k)
	 */
	static List<String> closest(int[][] points, int k) {
		if (k < 0 || k > points.length)
			throw new IllegalArgumentException("Invalid k");
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Long.compare(dist(b), dist(a)));
		for (int[] point : points) {
			heap.offer(point);
			if (heap.size() > k)
				heap.poll();
		}
		List<int[]> selected = new ArrayList<>(heap);
		selected.sort(Comparator.comparingLong(KClosestPoints::dist));
		List<String> result = new ArrayList<>();
		for (int[] p : selected)
			result.add(Arrays.toString(p));
		return result;
	}

	static long dist(int[] p) {
		return (long) p[0] * p[0] + (long) p[1] * p[1];
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Closest: " + closest(new int[][] { { 1, 3 }, { -2, 2 }, { 5, 8 } }, 1));
	}
}

/*
 * Output : Closest: [[-2, 2]]
 */
