/* Day 20 -  20/02/2026 */

package arrays.matrix;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Rotate a Matrix 90 Degrees Clockwise In-Place
 *
 * APPROACH: Transpose + Reverse each row
 * TIME  : O(n²)
 * SPACE : O(1)
 */
public class RotateMatrix90 {

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        // Step 1: Transpose (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        // Step 2: Reverse each row
        for (int[] row : matrix)
            for (int left = 0, right = n - 1; left < right; left++, right--) {
                int temp = row[left]; row[left] = row[right]; row[right] = temp;
            }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Before:");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        rotate(matrix);
        System.out.println("After (90° clockwise):");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        // [7,4,1], [8,5,2], [9,6,3]
    }
}

/*
Output:

Before:
[1, 2, 3]
[4, 5, 6]
[7, 8, 9]
After (90° clockwise):
[7, 4, 1]
[8, 5, 2]
[9, 6, 3]

*/






