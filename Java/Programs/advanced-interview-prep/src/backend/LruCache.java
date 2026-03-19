package backend;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Build an O(1) least-recently-used cache supporting get and put.
 *
 */
public class LruCache {

	/*
	 * APPROACH 1 (PRIMARY): LinkedHashMap access-order eviction Complexity ~ TIME :
	 * O(1) expected, SPACE : O(capacity)
	 */
	static class Lru extends LinkedHashMap<Integer, Integer> {
		private final int capacity;

		Lru(int capacity) {
			super(16, 0.75f, true);
			this.capacity = capacity;
		}

		@Override
		protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
			return size() > capacity;
		}

		int getValue(int key) {
			return getOrDefault(key, -1);
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Lru cache = new Lru(2);
		cache.put(1, 10);
		cache.put(2, 20);
		cache.getValue(1);
		cache.put(3, 30);
		System.out.println("Evicted key 2: " + cache.getValue(2));
	}
}

/*
 * Output : Evicted key 2: -1
 */
