/* Day 31 -  03/03/2026 */

package arrays.subarray;

/**
 * Author: CodeChamps0419
 *
 * Maximum Product Subarray
 *
 * APPROACH: Track both max and min product at each step
 * TIME  : O(n)
 * SPACE : O(1)
 */
public class MaxProductSubarray {

    public static int maxProduct(int[] arr) {
        int maxProd = arr[0], minProd = arr[0], result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) {
                int temp = maxProd;  // swap: negative flips max and min
                maxProd = minProd;
                minProd = temp;
            }
            maxProd = Math.max(arr[i], maxProd * arr[i]);
            minProd = Math.min(arr[i], minProd * arr[i]);
            result = Math.max(result, maxProd);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, -2, 4};
        System.out.println("Array           : " + java.util.Arrays.toString(arr));
        System.out.println("Max Product     : " + maxProduct(arr)); // 6 → [2,3]

        int[] arr2 = {-2, 0, -1};
        System.out.println("Max Product arr2: " + maxProduct(arr2)); // 0
    }
}

/*

Output:

Array           : [2, 3, -2, 4]
Max Product     : 6
Max Product arr2: 0

*/






