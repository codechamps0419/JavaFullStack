package trees.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Print inorder, preorder, and postorder of a binary tree.
 *
 */
public class TreeTraversals {
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
	 * APPROACH 1 (PRIMARY): Iterative stack traversals Complexity ~ TIME : O(n)
	 * each, SPACE : O(h) to O(n)
	 */
	static List<Integer> inorder(Node root) {
		List<Integer> result = new ArrayList<>();
		Deque<Node> stack = new ArrayDeque<>();
		Node cur = root;
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			result.add(cur.value);
			cur = cur.right;
		}
		return result;
	}

	static List<Integer> preorder(Node root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;
		Deque<Node> stack = new ArrayDeque<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			result.add(node.value);
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
		return result;
	}

	static List<Integer> postorder(Node root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;
		Deque<Node> stack = new ArrayDeque<>();
		Deque<Node> out = new ArrayDeque<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			out.push(node);
			if (node.left != null)
				stack.push(node.left);
			if (node.right != null)
				stack.push(node.right);
		}
		while (!out.isEmpty())
			result.add(out.pop().value);
		return result;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node root = tree();
		System.out.println("Inorder  : " + inorder(root));
		System.out.println("Preorder : " + preorder(root));
		System.out.println("Postorder: " + postorder(root));
	}
}

/*
 * Output : Inorder : [1, 2, 3, 4, 5, 6, 7] Preorder : [4, 2, 1, 3, 6, 5, 7]
 * Postorder: [1, 3, 2, 5, 7, 6, 4]
 */
