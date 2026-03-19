package trees.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find the number of tree levels from root to deepest leaf.
 *
 */
public class MaximumDepthBinaryTree {
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
	 * APPROACH 1 (PRIMARY): Iterative breadth-first search Complexity ~ TIME :
	 * O(n), SPACE : O(w), w = max level width
	 */
	static int maxDepth(Node root) {
		if (root == null)
			return 0;
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);
		int depth = 0;
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				Node current = queue.remove();
				if (current.left != null)
					queue.add(current.left);
				if (current.right != null)
					queue.add(current.right);
			}
			depth++;
		}
		return depth;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Depth: " + maxDepth(tree()));
	}
}

/*
 * Output : Depth: 3
 */
