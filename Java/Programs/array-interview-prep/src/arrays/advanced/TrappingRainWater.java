/* Day 7 -  07/02/2026 */

package arrays.advanced;

/**
 * Author: CodeChamps0419
 *
 * Trapping Rain Water
 *
 * APPROACH: Two Pointer (O(1) space)
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class TrappingRainWater {

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) leftMax = height[left];
                else water += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax) rightMax = height[right];
                else water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Height : " + java.util.Arrays.toString(height));
        System.out.println("Water  : " + trap(height)); // 6
    }
}

/*
Output:
	
Height : [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
Water  : 6

*/







