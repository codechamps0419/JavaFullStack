package streams;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Flatten a list of integer lists into one sequence.
 *
 */
public class FlattenNestedLists {

	/*
	 * APPROACH 1 (PRIMARY): Stream.flatMap Complexity ~ TIME : O(total elements),
	 * SPACE : O(total elements) output
	 */
	static List<Integer> flatten(List<List<Integer>> nested) {
		return nested.stream().flatMap(List::stream).toList();
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Flattened: " + flatten(List.of(List.of(1, 2), List.of(3), List.of(4, 5))));
	}
}

/*
 * Output : Flattened: [1, 2, 3, 4, 5]
 */
