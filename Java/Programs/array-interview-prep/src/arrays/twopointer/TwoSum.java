/* Day 39 -  11/03/2026 */

package arrays.twopointer;

import java.util.HashMap;

/**
 * Author: CodeChamps0419
 *
 * Two Sum — Find Pair with Given Sum
 *
 * APPROACH: HashMap for O(1) lookup
 * TIME  : O(n)
 * SPACE : O(n)
 */
public class TwoSum {

    public static int[] twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); // value → index
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) return new int[]{map.get(complement), i};
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Array  : " + java.util.Arrays.toString(arr));
        System.out.println("Target : " + target);
        System.out.println("Indices: " + java.util.Arrays.toString(twoSum(arr, target))); // [0,1]
    }
}

/*
Output:

Array  : [2, 7, 11, 15]
Target : 9
Indices: [0, 1]

*/






