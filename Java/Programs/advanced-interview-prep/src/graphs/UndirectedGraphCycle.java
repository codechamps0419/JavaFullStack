package graphs;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Detect a cycle in an undirected graph adjacency list.
 *
 */
public class UndirectedGraphCycle {

	/*
	 * APPROACH 1 (PRIMARY): Iterative BFS with parent tracking Complexity ~ TIME :
	 * O(V + E), SPACE : O(V)
	 */
	record Visit(int node, int parent) {
	}

	static boolean hasCycle(List<List<Integer>> graph) {
		boolean[] visited = new boolean[graph.size()];
		for (int start = 0; start < graph.size(); start++) {
			if (visited[start])
				continue;
			Queue<Visit> queue = new ArrayDeque<>();
			visited[start] = true;
			queue.add(new Visit(start, -1));
			while (!queue.isEmpty()) {
				Visit v = queue.remove();
				for (int next : graph.get(v.node())) {
					if (!visited[next]) {
						visited[next] = true;
						queue.add(new Visit(next, v.node()));
					} else if (next != v.parent())
						return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		List<List<Integer>> graph = List.of(List.of(1, 2), List.of(0, 2), List.of(0, 1));
		System.out.println("Cycle: " + hasCycle(graph));
	}
}

/*
 * Output : Cycle: true
 */
