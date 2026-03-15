/* Day 2 -  02/02/2026 */

package arrays.advanced;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Find the Duplicate Number in Array of N+1 Integers
 * Array contains integers in range [1, n], exactly one number appears twice.
 *
 */
public class FindDuplicate {

    /*
     * APPROACH: Floyd's Cycle Detection (Tortoise and Hare)
     * Complexity ~ TIME : O(n), SPACE : O(1)
     * Treats array as a linked list where arr[i] points to next node
     */
    public static int findDuplicate(int[] arr) {
        // Phase 1: detect cycle — find meeting point
        int slow = arr[0];
        int fast = arr[0];
        do {
            slow = arr[slow];           // move one step
            fast = arr[arr[fast]];      // move two steps
        } while (slow != fast);

        // Phase 2: find cycle entry — duplicate number
        slow = arr[0];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }

    /*
     * APPROACH: Negative Marking (modifies array)
     * Complexity ~ TIME : O(n), SPACE : O(1)
     * Uses index as hash — negates visited index as marker
     */
    public static int findDuplicateNegativeMarking(int[] arr) {
        int duplicate = -1;
        for (int i = 0; i < arr.length; i++) {
            int idx = Math.abs(arr[i]);
            if (arr[idx] < 0) { duplicate = idx; break; } // already visited → duplicate
            arr[idx] = -arr[idx];                          // mark as visited
        }
        // restore array
        for (int i = 0; i < arr.length; i++) arr[i] = Math.abs(arr[i]);
        return duplicate;
    }

    /*
     * APPROACH: Stream (sorting based)
     * Complexity ~ TIME : O(n log n), SPACE : O(n)
     */
    public static int findDuplicateUsingStream(int[] arr) {
        int[] sorted = Arrays.stream(arr).sorted().toArray();
        return java.util.stream.IntStream.range(0, sorted.length - 1)
                .filter(i -> sorted[i] == sorted[i + 1])
                .map(i -> sorted[i])
                .findFirst()
                .getAsInt();
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 2};

        System.out.println("Array        : " + Arrays.toString(arr));
        System.out.println("Duplicate (S1): " + findDuplicate(arr));                // 2
        System.out.println("Duplicate (S2): " + findDuplicateNegativeMarking(arr)); // 2
        System.out.println("Duplicate (S3): " + findDuplicateUsingStream(arr));     // 2
    }
}

/*
Output:

Array        : [1, 3, 4, 2, 2]
Duplicate (S1): 2
Duplicate (S2): 2
Duplicate (S3): 2

*/







