/* Day 4 -  04/02/2026 */

package arrays.advanced;

import java.util.HashSet;

/**
 * Author: CodeChamps0419
 *
 * Longest Consecutive Sequence in an Unsorted Array
 *
 * APPROACH: HashSet — only start counting from sequence beginnings
 * TIME  : O(n)
 * SPACE : O(n)
 */
public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);

        int maxLen = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) { // only start from sequence beginning
                int current = num, length = 1;
                while (set.contains(current + 1)) { current++; length++; }
                maxLen = Math.max(maxLen, length);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println("Array  : " + java.util.Arrays.toString(arr));
        System.out.println("Longest: " + longestConsecutive(arr)); // 4 → [1,2,3,4]
    }
}

/*

Output:

Array  : [100, 4, 200, 1, 3, 2]
Longest: 4

*/






