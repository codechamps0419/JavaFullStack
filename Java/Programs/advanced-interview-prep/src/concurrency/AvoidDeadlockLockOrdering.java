package concurrency;

/**
 * Author: CodeChamps0419
 *
 * Avoid deadlocks when two threads require two locks.
 *
 */
public class AvoidDeadlockLockOrdering {

	/*
	 * APPROACH 1 (PRIMARY): Global consistent lock acquisition order Complexity ~
	 * TIME : O(1) per pair of locks excluding contention, SPACE : O(1)
	 */
	static class Account {
		final int id;
		int balance;
		final Object lock = new Object();

		Account(int id, int balance) {
			this.id = id;
			this.balance = balance;
		}
	}

	static void transfer(Account from, Account to, int amount) {
		if (from == to)
			return;
		Account first = from.id < to.id ? from : to;
		Account second = from.id < to.id ? to : from;
		synchronized (first.lock) {
			synchronized (second.lock) {
				if (from.balance < amount)
					throw new IllegalArgumentException("Insufficient funds");
				from.balance -= amount;
				to.balance += amount;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Account a = new Account(1, 100), b = new Account(2, 100);
		transfer(a, b, 30);
		transfer(b, a, 10);
		System.out.println("Balances: " + a.balance + ", " + b.balance);
	}
}

/*
 * Output : Balances: 80, 120
 */
