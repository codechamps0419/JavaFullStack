package trees.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Check that each left-subtree value is smaller and each right-subtree value is
 * greater.
 *
 */
public class ValidateBinarySearchTree {
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
	 * APPROACH 1 (PRIMARY): Iterative inorder traversal Complexity ~ TIME : O(n),
	 * SPACE : O(h)
	 */
	static boolean isValidBST(Node root) {
		Deque<Node> stack = new ArrayDeque<>();
		Node current = root;
		long previous = Long.MIN_VALUE;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			if (current.value <= previous)
				return false;
			previous = current.value;
			current = current.right;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Valid BST: " + isValidBST(tree()));
	}
}

/*
 * Output : Valid BST: true
 */
