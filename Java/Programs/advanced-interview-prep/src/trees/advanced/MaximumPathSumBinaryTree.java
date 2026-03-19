package trees.advanced;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find maximum sum of any path in a binary tree (path may start/end anywhere).
 *
 */
public class MaximumPathSumBinaryTree {
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
	 * APPROACH 1 (PRIMARY): Iterative postorder dynamic programming Complexity ~
	 * TIME : O(n), SPACE : O(n)
	 */
	static int maxPathSum(Node root) {
		if (root == null)
			throw new IllegalArgumentException("Tree must not be empty");
		Deque<Node> stack = new ArrayDeque<>(), reverse = new ArrayDeque<>();
		Map<Node, Integer> gain = new IdentityHashMap<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node n = stack.pop();
			reverse.push(n);
			if (n.left != null)
				stack.push(n.left);
			if (n.right != null)
				stack.push(n.right);
		}
		int best = Integer.MIN_VALUE;
		while (!reverse.isEmpty()) {
			Node n = reverse.pop();
			int left = n.left == null ? 0 : Math.max(0, gain.get(n.left));
			int right = n.right == null ? 0 : Math.max(0, gain.get(n.right));
			best = Math.max(best, n.value + left + right);
			gain.put(n, n.value + Math.max(left, right));
		}
		return best;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Max path sum: " + maxPathSum(tree()));
	}
}

/*
 * Output : Max path sum: 22
 */
