package backtrackingdp;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return every subset of a list of distinct integers.
 *
 */
public class GenerateAllSubsets {

	/*
	 * APPROACH 1 (PRIMARY): Iterative subset doubling Complexity ~ TIME : O(n *
	 * 2^n), SPACE : O(n * 2^n) output
	 */
	static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		for (int value : nums) {
			int existing = result.size();
			for (int i = 0; i < existing; i++) {
				List<Integer> copy = new ArrayList<>(result.get(i));
				copy.add(value);
				result.add(copy);
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Subsets: " + subsets(new int[] { 1, 2 }));
	}
}

/*
 * Output : Subsets: [[], [1], [2], [1, 2]]
 */
