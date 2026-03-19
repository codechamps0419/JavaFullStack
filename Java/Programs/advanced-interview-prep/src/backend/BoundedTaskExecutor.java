package backend;

import java.util.*;
import java.util.concurrent.*;

/**
 * Author: CodeChamps0419
 *
 * Run tasks using a limited worker count and bounded queue.
 *
 */
public class BoundedTaskExecutor {

	/*
	 * APPROACH 1 (PRIMARY): ThreadPoolExecutor with AbortPolicy Complexity ~ TIME :
	 * O(1) submission, task-dependent execution, SPACE : O(workers + queue
	 * capacity)
	 */
	static List<String> executeDemo() throws InterruptedException, ExecutionException {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
				new ThreadPoolExecutor.AbortPolicy());
		try {
			List<Future<String>> results = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				final int task = i;
				results.add(pool.submit(() -> "task-" + task));
			}
			List<String> output = new ArrayList<>();
			for (Future<String> result : results)
				output.add(result.get());
			return output;
		} finally {
			pool.shutdown();
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Completed: " + executeDemo());
	}
}

/*
 * Output : Completed: [task-1, task-2, task-3]
 */
