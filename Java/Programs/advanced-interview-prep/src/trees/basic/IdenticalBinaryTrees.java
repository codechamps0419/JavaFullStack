package trees.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Check whether two binary trees have identical shape and values.
 *
 */
public class IdenticalBinaryTrees {
	static class Node {
		int value;
		Node left, right;

		Node(int value) {
			this.value = value;
		}
	}

// Shared demo tree:         4
//                         /   \
//                        2     6
//                       / \   / \
//                      1   3 5   7
	static Node tree() {
		Node root = new Node(4);
		root.left = new Node(2);
		root.right = new Node(6);
		root.left.left = new Node(1);
		root.left.right = new Node(3);
		root.right.left = new Node(5);
		root.right.right = new Node(7);
		return root;
	}

	/*
	 * APPROACH 1 (PRIMARY): Iterative queue of node pairs Complexity ~ TIME : O(n),
	 * SPACE : O(w)
	 */
	record Pair(Node a, Node b) {
	}

	static boolean identical(Node a, Node b) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(a, b));
		while (!queue.isEmpty()) {
			Pair pair = queue.remove();
			Node left = pair.a(), right = pair.b();
			if (left == null && right == null)
				continue;
			if (left == null || right == null || left.value != right.value)
				return false;
			queue.add(new Pair(left.left, right.left));
			queue.add(new Pair(left.right, right.right));
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Identical: " + identical(tree(), tree()));
	}
}

/*
 * Output : Identical: true
 */
