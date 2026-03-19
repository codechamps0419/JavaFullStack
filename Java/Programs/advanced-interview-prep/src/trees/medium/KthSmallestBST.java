package trees.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return the kth smallest value in a binary search tree.
 *
 */
public class KthSmallestBST {
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
	 * APPROACH 1 (PRIMARY): Iterative inorder with counter Complexity ~ TIME : O(h
	 * + k), SPACE : O(h)
	 */
	static int kthSmallest(Node root, int k) {
		if (k <= 0)
			throw new IllegalArgumentException("k must be positive");
		Deque<Node> stack = new ArrayDeque<>();
		Node cur = root;
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			if (--k == 0)
				return cur.value;
			cur = cur.right;
		}
		throw new IllegalArgumentException("k exceeds node count");
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("3rd smallest: " + kthSmallest(tree(), 3));
	}
}

/*
 * Output : 3rd smallest: 3
 */
