package concurrency;

/**
 * Author: CodeChamps0419
 *
 * Use two threads to print odd and even numbers in order.
 *
 */
public class OddEvenTwoThreads {

	/*
	 * APPROACH 1 (PRIMARY): wait/notify coordination with shared monitor Complexity
	 * ~ TIME : O(n), SPACE : O(1)
	 */
	static class Printer {
		private int next = 1;
		private final int limit;

		Printer(int limit) {
			this.limit = limit;
		}

		synchronized void print(boolean odd) {
			while (next <= limit) {
				while (next <= limit && ((next % 2 == 1) != odd)) {
					try {
						wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						return;
					}
				}
				if (next > limit)
					break;
				System.out.print(next + " ");
				next++;
				notifyAll();
			}
			notifyAll();
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Printer printer = new Printer(10);
		Thread odd = new Thread(() -> printer.print(true));
		Thread even = new Thread(() -> printer.print(false));
		odd.start();
		even.start();
		odd.join();
		even.join();
		System.out.println();
	}
}

/*
 * Output : 1 2 3 4 5 6 7 8 9 10
 */
