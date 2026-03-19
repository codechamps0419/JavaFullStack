package concurrency;

import java.util.*;
import java.util.concurrent.*;

/**
 * Author: CodeChamps0419
 *
 * Pass produced items safely from one thread to a consumer.
 *
 */
public class ProducerConsumerBlockingQueue {

	/*
	 * APPROACH 1 (PRIMARY): ArrayBlockingQueue with poison pill Complexity ~ TIME :
	 * O(n), SPACE : O(capacity)
	 */
	static List<Integer> runDemo() throws InterruptedException {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
		List<Integer> received = Collections.synchronizedList(new ArrayList<>());
		Thread producer = new Thread(() -> {
			try {
				for (int i = 1; i <= 5; i++)
					queue.put(i);
				queue.put(-1); // End-of-stream marker.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		});
		Thread consumer = new Thread(() -> {
			try {
				while (true) {
					int v = queue.take();
					if (v == -1)
						break;
					received.add(v);
				}
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		});
		producer.start();
		consumer.start();
		producer.join();
		consumer.join();
		return received;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Consumed: " + runDemo());
	}
}

/*
 * Output : Consumed: [1, 2, 3, 4, 5]
 */
