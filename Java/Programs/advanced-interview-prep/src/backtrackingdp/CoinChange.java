package backtrackingdp;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Minimum number of unlimited coins to reach a target or -1 if impossible.
 *
 */
public class CoinChange {

	/*
	 * APPROACH 1 (PRIMARY): Bottom-up dynamic programming Complexity ~ TIME :
	 * O(amount * number of coins), SPACE : O(amount)
	 */
	static int minCoins(int[] coins, int amount) {
		if (amount < 0)
			return -1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;
		for (int sum = 1; sum <= amount; sum++) {
			for (int coin : coins) {
				if (coin <= 0)
					throw new IllegalArgumentException("Positive coins only");
				if (coin <= sum)
					dp[sum] = Math.min(dp[sum], dp[sum - coin] + 1);
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Minimum coins: " + minCoins(new int[] { 1, 2, 5 }, 11));
	}
}

/*
 * Output : Minimum coins: 3
 */
