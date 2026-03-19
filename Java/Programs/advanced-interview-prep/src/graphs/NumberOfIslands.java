package graphs;

import java.util.*;

/**
 * Author: CodeChamps0419
 *
 * Count connected 4-directional groups of 1 in a binary grid.
 *
 */
public class NumberOfIslands {

	/*
	 * APPROACH 1 (PRIMARY): Iterative BFS flood fill Complexity ~ TIME : O(rows *
	 * cols), SPACE : O(rows * cols) worst case
	 */
	static int countIslands(char[][] grid) {
		if (grid.length == 0)
			return 0;
		int rows = grid.length, cols = grid[0].length, islands = 0;
		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] != '1')
					continue;
				islands++;
				Queue<int[]> queue = new ArrayDeque<>();
				queue.add(new int[] { r, c });
				grid[r][c] = '0';
				while (!queue.isEmpty()) {
					int[] cell = queue.remove();
					for (int[] d : directions) {
						int nr = cell[0] + d[0], nc = cell[1] + d[1];
						if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1') {
							grid[nr][nc] = '0';
							queue.add(new int[] { nr, nc });
						}
					}
				}
			}
		return islands;
	}

	public static void main(String[] args) throws Exception {
		// Sample input and runnable verification.
		char[][] grid = { { '1', '1', '0' }, { '0', '1', '0' }, { '1', '0', '1' } };
		System.out.println("Islands: " + countIslands(grid));
	}
}

/*
 * Output : Islands: 3
 */
