package graphs;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Find minutes to rot all reachable fresh oranges, or -1.
 *
 */
public class RottingOranges {

	/*
	 * APPROACH 1 (PRIMARY): Multi-source breadth-first search Complexity ~ TIME :
	 * O(rows * cols), SPACE : O(rows * cols)
	 */
	static int minutesToRot(int[][] grid) {
		int rows = grid.length, cols = grid[0].length, fresh = 0, minutes = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 2)
					queue.add(new int[] { r, c });
				if (grid[r][c] == 1)
					fresh++;
			}
		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		while (fresh > 0 && !queue.isEmpty()) {
			int count = queue.size();
			for (int i = 0; i < count; i++) {
				int[] cell = queue.remove();
				for (int[] d : directions) {
					int r = cell[0] + d[0], c = cell[1] + d[1];
					if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
						grid[r][c] = 2;
						fresh--;
						queue.add(new int[] { r, c });
					}
				}
			}
			minutes++;
		}
		return fresh == 0 ? minutes : -1;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		System.out.println("Minutes: " + minutesToRot(grid));
	}
}

/*
 * Output : Minutes: 4
 */
