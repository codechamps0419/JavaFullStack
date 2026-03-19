package backend;

import java.util.concurrent.*;

/**
 * Author: CodeChamps0419
 *
 * Create a key-value store that lazily expires entries after a TTL.
 *
 */
public class KeyValueStoreWithTtl {

	/*
	 * APPROACH 1 (PRIMARY): ConcurrentHashMap + expiration timestamp Complexity ~
	 * TIME : O(1) expected get/put, SPACE : O(number of entries)
	 */
	record Entry(String value, long expiresAtNanos) {
	}

	static class Store {
		final ConcurrentHashMap<String, Entry> data = new ConcurrentHashMap<>();

		void put(String key, String value, long ttlMillis) {
			if (ttlMillis <= 0)
				throw new IllegalArgumentException();
			data.put(key, new Entry(value, System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(ttlMillis)));
		}

		String get(String key) {
			Entry entry = data.get(key);
			if (entry == null)
				return null;
			if (System.nanoTime() - entry.expiresAtNanos() >= 0) {
				data.remove(key, entry);
				return null;
			}
			return entry.value();
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Store store = new Store();
		store.put("name", "Java", 10000);
		System.out.println("Value: " + store.get("name"));
		System.out.println("Missing: " + store.get("unknown"));
	}
}

/*
 * Output : Value: Java Missing: null
 */
