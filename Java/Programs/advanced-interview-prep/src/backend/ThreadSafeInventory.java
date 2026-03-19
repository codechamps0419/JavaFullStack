package backend;

import java.util.concurrent.atomic.*;

/**
 * Author: CodeChamps0419
 *
 * Prevent overselling when multiple threads purchase limited inventory.
 *
 */
public class ThreadSafeInventory {

	/*
	 * APPROACH 1 (PRIMARY): AtomicInteger CAS update Complexity ~ TIME : O(1)
	 * expected per attempt, SPACE : O(1)
	 */
	static class Inventory {
		private final AtomicInteger stock;

		Inventory(int initial) {
			stock = new AtomicInteger(initial);
		}

		boolean purchase(int quantity) {
			if (quantity <= 0)
				throw new IllegalArgumentException();
			while (true) {
				int available = stock.get();
				if (available < quantity)
					return false;
				if (stock.compareAndSet(available, available - quantity))
					return true;
			}
		}

		int remaining() {
			return stock.get();
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Inventory inventory = new Inventory(2);
		System.out.println("Bought 1: " + inventory.purchase(1));
		System.out.println("Bought 2: " + inventory.purchase(2));
		System.out.println("Remaining: " + inventory.remaining());
	}
}

/*
 * Output : Bought 1: true Bought 2: false Remaining: 1
 */
