package backtrackingdp;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find combinations of positive candidates that sum to target; reuse allowed.
 *
 */
public class CombinationSum {

	/*
	 * APPROACH 1 (PRIMARY): Iterative explicit DFS state stack Complexity ~ TIME :
	 * Output-sensitive, exponential worst case, SPACE : O(target/min + output size)
	 */
	record State(int index, int remaining, List<Integer> chosen) {
	}

	static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Deque<State> stack = new ArrayDeque<>();
		stack.push(new State(0, target, new ArrayList<>()));
		while (!stack.isEmpty()) {
			State state = stack.pop();
			if (state.remaining() == 0) {
				result.add(state.chosen());
				continue;
			}
			if (state.index() == candidates.length)
				continue;
			int value = candidates[state.index()];
			stack.push(new State(state.index() + 1, state.remaining(), state.chosen()));
			if (value <= state.remaining()) {
				List<Integer> copy = new ArrayList<>(state.chosen());
				copy.add(value);
				stack.push(new State(state.index(), state.remaining() - value, copy));
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Combinations: " + combinationSum(new int[] { 2, 3, 6, 7 }, 7));
	}
}

/*
 * Output : Combinations: [[2, 2, 3], [7]]
 */
