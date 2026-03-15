/* Day 1 -  01/02/2026 */

package arrays.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: CodeChamps0419
 *
 * Find All Duplicates in an Array (Without Extra Space)
 * Array contains integers in range [1, n], each appearing once or twice.
 *
 * APPROACH: Index marking — negate visited index as a marker
 * TIME  : O(n)
 * SPACE : O(1) excluding output
 */
public class FindAllDuplicates {

    public static List<Integer> findDuplicates(int[] arr) {
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            int idx = Math.abs(num) - 1; // map value to index (1-indexed → 0-indexed)
            if (arr[idx] < 0) result.add(Math.abs(num)); // already visited → duplicate
            else arr[idx] = -arr[idx];                   // mark as visited
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Array     : " + java.util.Arrays.toString(arr));
        System.out.println("Duplicates: " + findDuplicates(arr)); // [2, 3]
    }
}

/*

Output:

Array     : [4, 3, 2, 7, 8, 2, 3, 1]
Duplicates: [2, 3]

*/






