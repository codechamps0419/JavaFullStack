package graphs;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Deep-copy an undirected graph with cycles.
 *
 */
public class CloneGraph {

	/*
	 * APPROACH 1 (PRIMARY): Iterative BFS with identity mapping Complexity ~ TIME :
	 * O(V + E), SPACE : O(V)
	 */
	static class Node {
		int value;
		List<Node> neighbors = new ArrayList<>();

		Node(int value) {
			this.value = value;
		}
	}

	static Node cloneGraph(Node start) {
		if (start == null)
			return null;
		Map<Node, Node> copies = new IdentityHashMap<>();
		Queue<Node> queue = new ArrayDeque<>();
		copies.put(start, new Node(start.value));
		queue.add(start);
		while (!queue.isEmpty()) {
			Node original = queue.remove();
			for (Node neighbor : original.neighbors) {
				if (!copies.containsKey(neighbor)) {
					copies.put(neighbor, new Node(neighbor.value));
					queue.add(neighbor);
				}
				copies.get(original).neighbors.add(copies.get(neighbor));
			}
		}
		return copies.get(start);
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		Node a = new Node(1), b = new Node(2);
		a.neighbors.add(b);
		b.neighbors.add(a);
		Node copy = cloneGraph(a);
		System.out.println("Cloned value: " + copy.value);
		System.out.println("Different object: " + (copy != a));
	}
}

/*
 * Output : Cloned value: 1 Different object: true
 */
