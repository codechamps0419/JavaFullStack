/* Day 3 -  03/02/2026 */

package arrays.advanced;

/**
 * Author: CodeChamps0419
 *
 * Jump Game — Can You Reach the Last Index?
 *
 * APPROACH: Greedy — track the farthest reachable index
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class JumpGame {

    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;           // current index is unreachable
            maxReach = Math.max(maxReach, i + nums[i]); // update farthest reachable
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Array1: " + java.util.Arrays.toString(nums1));
        System.out.println("Can jump: " + canJump(nums1)); // true

        System.out.println("Array2: " + java.util.Arrays.toString(nums2));
        System.out.println("Can jump: " + canJump(nums2)); // false
    }
}

/*

Output:

Array1: [2, 3, 1, 1, 4]
Can jump: true
Array2: [3, 2, 1, 0, 4]
Can jump: false

*/







