package backtrackingdp;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return all permutations of distinct input numbers.
 *
 */
public class GenerateAllPermutations {

	/*
	 * APPROACH 1 (PRIMARY): Iterative insertion into every position Complexity ~
	 * TIME : O(n * n!), SPACE : O(n * n!) output
	 */
	static List<List<Integer>> permutations(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		for (int num : nums) {
			List<List<Integer>> next = new ArrayList<>();
			for (List<Integer> permutation : result) {
				for (int pos = 0; pos <= permutation.size(); pos++) {
					List<Integer> copy = new ArrayList<>(permutation);
					copy.add(pos, num);
					next.add(copy);
				}
			}
			result = next;
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Permutations: " + permutations(new int[] { 1, 2, 3 }));
	}
}

/*
 * Output : Permutations: [[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3,
 * 2], [1, 2, 3]]
 */
