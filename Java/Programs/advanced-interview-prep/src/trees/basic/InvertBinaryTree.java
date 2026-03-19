package trees.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Swap left/right children at every node.
 *
 */
public class InvertBinaryTree {
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
	 * APPROACH 1 (PRIMARY): Iterative breadth-first traversal Complexity ~ TIME :
	 * O(n), SPACE : O(w)
	 */
	static Node invert(Node root) {
		if (root == null)
			return null;
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			Node tmp = node.left;
			node.left = node.right;
			node.right = tmp;
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);
		}
		return root;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node root = invert(tree());
		System.out.println("New root children: " + root.left.value + ", " + root.right.value);
	}
}

/*
 * Output : New root children: 6, 2
 */
