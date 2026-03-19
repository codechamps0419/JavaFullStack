package graphs;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Determine whether all courses can be completed given prerequisite edges.
 *
 */
public class CourseSchedule {

	/*
	 * APPROACH 1 (PRIMARY): Kahn topological sort with indegrees Complexity ~ TIME
	 * : O(V + E), SPACE : O(V + E)
	 */
	static boolean canFinish(int courses, int[][] prerequisites) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < courses; i++)
			graph.add(new ArrayList<>());
		int[] indegree = new int[courses];
		for (int[] p : prerequisites) {
			graph.get(p[1]).add(p[0]);
			indegree[p[0]]++;
		}
		Queue<Integer> ready = new ArrayDeque<>();
		for (int i = 0; i < courses; i++)
			if (indegree[i] == 0)
				ready.add(i);
		int finished = 0;
		while (!ready.isEmpty()) {
			int course = ready.remove();
			finished++;
			for (int dependent : graph.get(course))
				if (--indegree[dependent] == 0)
					ready.add(dependent);
		}
		return finished == courses;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		System.out.println("Can finish: " + canFinish(2, new int[][] { { 1, 0 } }));
	}
}

/*
 * Output : Can finish: true
 */
