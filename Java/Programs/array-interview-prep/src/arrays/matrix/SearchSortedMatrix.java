/* Day 21 -  21/02/2026 */

package arrays.matrix;

/**
 * Author: CodeChamps0419
 *
 * Search in a Row-wise and Column-wise Sorted Matrix
 *
 * APPROACH: Start from top-right corner — eliminate row or column each step
 * TIME  : O(m + n)
 * SPACE : O(1)
 */
public class SearchSortedMatrix {

    public static boolean search(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1; // start top-right
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--; // eliminate this column
            else row++;                                 // eliminate this row
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
        };
        System.out.println("Search 29: " + search(matrix, 29)); // true
        System.out.println("Search 100: " + search(matrix, 100)); // false
    }
}

/*

Output:

Search 29: true
Search 100: false

*/






