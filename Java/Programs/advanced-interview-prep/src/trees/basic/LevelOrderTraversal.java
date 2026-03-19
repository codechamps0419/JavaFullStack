package trees.basic;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Return node values grouped by level.
 *
 */
public class LevelOrderTraversal {
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
	 * APPROACH 1 (PRIMARY): Queue-based BFS Complexity ~ TIME : O(n), SPACE : O(w)
	 */
	static List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> levels = new ArrayList<>();
		if (root == null)
			return levels;
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> level = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				Node node = queue.remove();
				level.add(node.value);
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
			}
			levels.add(level);
		}
		return levels;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Levels: " + levelOrder(tree()));
	}
}

/*
 * Output : Levels: [[4], [2, 6], [1, 3, 5, 7]]
 */
