/* Day 22 -  22/02/2026 */

package arrays.matrix;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Set Matrix Zeroes
 * If matrix[i][j] == 0, set entire row i and column j to 0.
 *
 * APPROACH: Use first row and first column as markers (O(1) space)
 * TIME  : O(m*n)
 * SPACE : O(1)
 */
public class SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false, firstColZero = false;

        // Check if first row/col originally have zeros
        for (int j = 0; j < n; j++) if (matrix[0][j] == 0) firstRowZero = true;
        for (int i = 0; i < m; i++) if (matrix[i][0] == 0) firstColZero = true;

        // Use first row and col as markers for rest of matrix
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0) { matrix[i][0] = 0; matrix[0][j] = 0; }

        // Zero out cells based on markers
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;

        // Zero out first row and col if needed
        if (firstRowZero) Arrays.fill(matrix[0], 0);
        if (firstColZero) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        System.out.println("Before:");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        setZeroes(matrix);
        System.out.println("After:");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        // [1,0,1], [0,0,0], [1,0,1]
    }
}

/*

Output:

Before:
[1, 1, 1]
[1, 0, 1]
[1, 1, 1]
After:
[1, 0, 1]
[0, 0, 0]
[1, 0, 1]

*/






