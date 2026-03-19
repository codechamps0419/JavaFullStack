package concurrency;

import java.util.concurrent.*;

/**
 * Author: CodeChamps0419
 *
 * Run two independent computations concurrently and combine results.
 *
 */
public class CombineCompletableFutures {

	/*
	 * APPROACH 1 (PRIMARY): CompletableFuture.supplyAsync + thenCombine Complexity
	 * ~ TIME : O(max(task times)) wall time ideally, SPACE : O(1) result; executor
	 * overhead
	 */
	static int combined() {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		try {
			CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> 20, pool);
			CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 22, pool);
			return a.thenCombine(b, Integer::sum).join();
		} finally {
			pool.shutdown();
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Combined: " + combined());
	}
}

/*
 * Output : Combined: 42
 */
