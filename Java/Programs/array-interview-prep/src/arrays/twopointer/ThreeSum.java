/* Day 38 -  10/03/2026 */

package arrays.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: CodeChamps0419
 *
 * Three Sum — Find All Triplets that Sum to Zero
 *
 * APPROACH: Sort + Two Pointer
 * TIME  : O(n²)
 * SPACE : O(1) excluding output
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue; // skip duplicates for i
            int left = i + 1, right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    while (left < right && arr[left] == arr[left + 1]) left++;   // skip duplicates
                    while (left < right && arr[right] == arr[right - 1]) right--; // skip duplicates
                    left++; right--;
                } else if (sum < 0) left++;
                else right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        System.out.println("Array  : " + Arrays.toString(arr));
        System.out.println("Triplets: " + threeSum(arr)); // [[-1,-1,2],[-1,0,1]]
    }
}

/*
Output:

Array  : [-1, 0, 1, 2, -1, -4]
Triplets: [[-1, -1, 2], [-1, 0, 1]]

*/






