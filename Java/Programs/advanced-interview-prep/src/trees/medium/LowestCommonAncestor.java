package trees.medium;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find the lowest common ancestor of two existing nodes in a binary tree.
 *
 */
public class LowestCommonAncestor {
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
	 * APPROACH 1 (PRIMARY): Iterative parent map + ancestor set Complexity ~ TIME :
	 * O(n), SPACE : O(n)
	 */
	static Node lowestCommonAncestor(Node root, Node p, Node q) {
		if (root == null || p == null || q == null)
			return null;
		Map<Node, Node> parent = new IdentityHashMap<>();
		Deque<Node> stack = new ArrayDeque<>();
		parent.put(root, null);
		stack.push(root);
		while (!stack.isEmpty() && (!parent.containsKey(p) || !parent.containsKey(q))) {
			Node node = stack.pop();
			if (node.left != null) {
				parent.put(node.left, node);
				stack.push(node.left);
			}
			if (node.right != null) {
				parent.put(node.right, node);
				stack.push(node.right);
			}
		}
		if (!parent.containsKey(p) || !parent.containsKey(q))
			return null;
		Set<Node> ancestors = Collections.newSetFromMap(new IdentityHashMap<>());
		while (p != null) {
			ancestors.add(p);
			p = parent.get(p);
		}
		while (!ancestors.contains(q))
			q = parent.get(q);
		return q;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node root = tree();
		System.out.println("LCA(1,3): " + lowestCommonAncestor(root, root.left.left, root.left.right).value);
	}
}

/*
 * Output : LCA(1,3): 2
 */
