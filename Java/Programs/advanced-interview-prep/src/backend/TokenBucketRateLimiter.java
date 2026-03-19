package backend;

/**
 * Author: CodeChamps0419
 *
 * Implement a per-process token-bucket limiter with refill over time.
 *
 */
public class TokenBucketRateLimiter {

	/*
	 * APPROACH 1 (PRIMARY): Synchronized token bucket using monotonic clock
	 * Complexity ~ TIME : O(1) per request, SPACE : O(1)
	 */
	static class TokenBucket {
		final double capacity, refillPerSecond;
		double tokens;
		long lastNanos;

		TokenBucket(int capacity, double refillPerSecond) {
			if (capacity <= 0 || refillPerSecond <= 0)
				throw new IllegalArgumentException();
			this.capacity = capacity;
			this.refillPerSecond = refillPerSecond;
			tokens = capacity;
			lastNanos = System.nanoTime();
		}

		synchronized boolean allow() {
			long now = System.nanoTime();
			double elapsed = Math.max(0, now - lastNanos) / 1_000_000_000.0;
			tokens = Math.min(capacity, tokens + elapsed * refillPerSecond);
			lastNanos = now;
			if (tokens < 1.0)
				return false;
			tokens -= 1.0;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		TokenBucket limiter = new TokenBucket(2, 1);
		System.out.println("Allowed: " + limiter.allow());
		System.out.println("Allowed: " + limiter.allow());
		System.out.println("Allowed: " + limiter.allow());
	}
}

/*
 * Output : Allowed: true Allowed: true Allowed: false
 */
