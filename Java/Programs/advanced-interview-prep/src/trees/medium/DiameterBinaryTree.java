package trees.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find the longest path between any two nodes, measured in edges.
 *
 */
public class DiameterBinaryTree {
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
	 * APPROACH 1 (PRIMARY): Iterative postorder + subtree heights Complexity ~ TIME
	 * : O(n), SPACE : O(n)
	 */
	static int diameter(Node root) {
		if (root == null)
			return 0;
		Map<Node, Integer> height = new IdentityHashMap<>();
		Deque<Node> todo = new ArrayDeque<>(), postorder = new ArrayDeque<>();
		todo.push(root);
		while (!todo.isEmpty()) {
			Node node = todo.pop();
			postorder.push(node);
			if (node.left != null)
				todo.push(node.left);
			if (node.right != null)
				todo.push(node.right);
		}
		int best = 0;
		while (!postorder.isEmpty()) {
			Node node = postorder.pop();
			int left = node.left == null ? 0 : height.get(node.left);
			int right = node.right == null ? 0 : height.get(node.right);
			best = Math.max(best, left + right);
			height.put(node, 1 + Math.max(left, right));
		}
		return best;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Diameter: " + diameter(tree()));
	}
}

/*
 * Output : Diameter: 4
 */
