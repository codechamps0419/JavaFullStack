/* Day 34 -  06/03/2026 */

package arrays.twopointer;

/**
 * Author: CodeChamps0419
 *
 * Container with Most Water
 *
 * APPROACH: Two Pointer (greedy — always move the shorter line)
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class ContainerWithMostWater {

    public static int maxWater(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) left++;  // move shorter line inward
            else right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Heights   : " + java.util.Arrays.toString(height));
        System.out.println("Max Water : " + maxWater(height)); // 49
    }
}

/*

Output:

Heights   : [1, 8, 6, 2, 5, 4, 8, 3, 7]
Max Water : 49

*/






