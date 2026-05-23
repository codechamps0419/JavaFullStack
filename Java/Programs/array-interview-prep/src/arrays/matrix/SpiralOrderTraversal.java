/* Day 23 -  23/02/2026 */

package arrays.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: CodeChamps0419
 *
 * Spiral Order Traversal of a Matrix
 *
 * APPROACH: Layer-by-layer boundary shrinking
 * TIME  : O(m*n)
 * SPACE : O(1) excluding output
 */
public class SpiralOrderTraversal {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++)  result.add(matrix[top][i]);    // left → right
            top++;
            for (int i = top; i <= bottom; i++)  result.add(matrix[i][right]);  // top → bottom
            right--;
            if (top <= bottom)
                for (int i = right; i >= left; i--) result.add(matrix[bottom][i]); // right → left
            bottom--;
            if (left <= right)
                for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);   // bottom → top
            left++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Spiral: " + spiralOrder(matrix)); 
    }
}

/*

Output:

Spiral: [1, 2, 3, 6, 9, 8, 7, 4, 5]

*/








