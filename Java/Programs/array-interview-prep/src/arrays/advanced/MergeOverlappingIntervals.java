/* Day 5 -  05/02/2026 */

package arrays.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: CodeChamps0419
 *
 * Merge Overlapping Intervals
 *
 * APPROACH: Sort by start time + greedy merge
 * TIME  : O(n log n)
 * SPACE : O(n) for output
 */
public class MergeOverlappingIntervals {

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort by start
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval); // no overlap → add as new interval
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]); // merge
            }
        }
        return merged.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("Input:");
        for (int[] i : intervals) System.out.println(Arrays.toString(i));
        int[][] result = merge(intervals);
        System.out.println("Merged:");
        for (int[] i : result) System.out.println(Arrays.toString(i));
        // [1,6], [8,10], [15,18]
    }
}

/*
Output:
	
Input:
[1, 3]
[2, 6]
[8, 10]
[15, 18]
Merged:
[1, 6]
[8, 10]
[15, 18]

*/






