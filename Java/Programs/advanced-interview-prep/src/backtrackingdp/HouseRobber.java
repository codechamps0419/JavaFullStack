package backtrackingdp;

/**
 * Author: CodeChamps0419
 *
 * Maximize non-adjacent house values without robbing neighbors.
 *
 */
public class HouseRobber {

	/*
	 * APPROACH 1 (PRIMARY): Constant-space bottom-up DP Complexity ~ TIME : O(n),
	 * SPACE : O(1)
	 */
	static int maxRobbery(int[] houses) {
		int previousTwo = 0, previousOne = 0;
		for (int cash : houses) {
			int current = Math.max(previousOne, previousTwo + cash);
			previousTwo = previousOne;
			previousOne = current;
		}
		return previousOne;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Maximum amount: " + maxRobbery(new int[] { 2, 7, 9, 3, 1 }));
	}
}

/*
 * Output : Maximum amount: 12
 */
