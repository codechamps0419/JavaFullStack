package backend;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

/**
 * Author: CodeChamps0419
 *
 * Return original result when the same operation key is retried.
 *
 */
public class IdempotencyKeyManager {

	/*
	 * APPROACH 1 (PRIMARY): ConcurrentHashMap computeIfAbsent Complexity ~ TIME :
	 * O(1) expected per key, SPACE : O(number of unique keys)
	 */
	static class Manager {
		private final ConcurrentHashMap<String, String> responses = new ConcurrentHashMap<>();

		String execute(String key, Supplier<String> operation) {
			return responses.computeIfAbsent(key, k -> operation.get());
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Manager manager = new Manager();
		AtomicInteger calls = new AtomicInteger();
		Supplier<String> work = () -> "receipt-" + calls.incrementAndGet();
		System.out.println(manager.execute("order-1", work));
		System.out.println(manager.execute("order-1", work));
		System.out.println("Executions: " + calls.get());
	}
}

/*
 * Output : receipt-1 receipt-1 Executions: 1
 */
