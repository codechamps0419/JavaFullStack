package backtrackingdp;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Partition a string into every sequence of palindrome substrings.
 *
 */
public class PalindromePartitioning {

	/*
	 * APPROACH 1 (PRIMARY): Iterative explicit DFS with precomputed palindrome
	 * table Complexity ~ TIME : O(n * 2^n) output-sensitive, SPACE : O(n^2 +
	 * output)
	 */
	record State(int start, List<String> parts) {
	}

	static List<List<String>> partitions(String s) {
		int n = s.length();
		boolean[][] palindrome = new boolean[n][n];
		for (int length = 1; length <= n; length++)
			for (int i = 0; i + length <= n; i++) {
				int j = i + length - 1;
				palindrome[i][j] = s.charAt(i) == s.charAt(j) && (length <= 2 || palindrome[i + 1][j - 1]);
			}
		List<List<String>> result = new ArrayList<>();
		Deque<State> stack = new ArrayDeque<>();
		stack.push(new State(0, new ArrayList<>()));
		while (!stack.isEmpty()) {
			State state = stack.pop();
			if (state.start() == n) {
				result.add(state.parts());
				continue;
			}
			for (int end = n - 1; end >= state.start(); end--) {
				if (!palindrome[state.start()][end])
					continue;
				List<String> next = new ArrayList<>(state.parts());
				next.add(s.substring(state.start(), end + 1));
				stack.push(new State(end + 1, next));
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Partitions: " + partitions("aab"));
	}
}

/*
 * Output : Partitions: [[a, a, b], [aa, b]]
 */
