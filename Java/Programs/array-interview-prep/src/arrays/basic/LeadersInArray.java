/* Day 11 -  11/02/2026 */

package arrays.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: CodeChamps0419
 *
 * Find All Leaders in an Array
 * A leader is an element greater than all elements to its right.
 * Rightmost element is always a leader.
 * 
 */
public class LeadersInArray {

    /*
     * APPROACH: Right to Left Scan
     * Complexity ~ TIME : O(n), SPACE : O(1) excluding output
     */
    public static List<Integer> findLeaders(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int maxFromRight = arr[arr.length - 1];
        leaders.add(maxFromRight); // rightmost is always a leader

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] >= maxFromRight) {
                maxFromRight = arr[i];
                leaders.add(0, arr[i]); // add at front to maintain order
            }
        }
        return leaders;
    }

    /*
     * APPROACH: Stream
     * Complexity ~ TIME : O(n²), SPACE : O(1) excluding output
     */
    public static List<Integer> findLeadersUsingStream(int[] arr) {
        return IntStream.range(0, arr.length)
                .filter(i -> IntStream.range(i + 1, arr.length).allMatch(j -> arr[i] >= arr[j]))
                .mapToObj(i -> arr[i])
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] arr = {16, 17, 4, 3, 5, 2};

        // Solution 1
        System.out.println("Array  : " + Arrays.toString(arr));
        System.out.println("Leaders (S1): " + findLeaders(arr));           // [17, 5, 2]

        // Solution 2
        System.out.println("Leaders (S2): " + findLeadersUsingStream(arr)); // [17, 5, 2]
    }
}

/*
Output:

Array  : [16, 17, 4, 3, 5, 2]
Leaders (S1): [17, 5, 2]
Leaders (S2): [17, 5, 2]

*/







