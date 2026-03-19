package concurrency;

/**
 * Author: CodeChamps0419
 *
 * Create one lazily initialized object safely across threads.
 *
 */
public class ThreadSafeSingleton {

	/*
	 * APPROACH 1 (PRIMARY): Initialization-on-demand holder idiom Complexity ~ TIME
	 * : O(1) access, SPACE : O(1)
	 */
	static final class Singleton {
		private Singleton() {
		}

		private static class Holder {
			static final Singleton INSTANCE = new Singleton();
		}

		static Singleton getInstance() {
			return Holder.INSTANCE;
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Same instance: " + (Singleton.getInstance() == Singleton.getInstance()));
	}
}

/*
 * Output : Same instance: true
 */
