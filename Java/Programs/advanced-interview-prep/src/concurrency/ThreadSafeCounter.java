package concurrency;

import java.util.*;
import java.util.concurrent.atomic.*;

/**
 * Author: CodeChamps0419
 *
 * Increment shared count safely across several threads.
 *
 */
public class ThreadSafeCounter {

	/*
	 * APPROACH 1 (PRIMARY): AtomicInteger compare-and-swap operations Complexity ~
	 * TIME : O(1) expected per increment, SPACE : O(1)
	 */
	static int count(int threads, int increments) throws InterruptedException {
		AtomicInteger counter = new AtomicInteger();
		List<Thread> workers = new ArrayList<>();
		for (int i = 0; i < threads; i++) {
			Thread t = new Thread(() -> {
				for (int j = 0; j < increments; j++)
					counter.incrementAndGet();
			});
			workers.add(t);
			t.start();
		}
		for (Thread t : workers)
			t.join();
		return counter.get();
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Count: " + count(4, 1000));
	}
}

/*
 * Output : Count: 4000
 */
