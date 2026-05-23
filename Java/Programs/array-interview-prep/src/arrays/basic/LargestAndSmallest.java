/* Day 10 -  10/02/2026 */

package arrays.basic;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

/**
 * Author: CodeChamps0419
 *
 * Find the Largest and Smallest Element in an Array
 * 
 */
public class LargestAndSmallest {

	 /*
	 * APPROACH: Single Pass Linear Scan
	 * Complexity ~ TIME  : O(n), SPACE : O(1)
	 */
    public static int[] find(int[] arr) {
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        return new int[]{max, min};
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 7, 2, 9, 4};
        System.out.println("Array   : " + Arrays.toString(arr));
        
        //Solution 1
        int[] result = find(arr);
        System.out.println("Largest : " + result[0]);  // 9
        System.out.println("Smallest: " + result[1]);  // 1
        
        //Solution 2
        /*
    	 * APPROACH: Two-Pass Linear Scan (using Java 8 Streams)
    	 * Complexity ~ TIME  : O(n) [O(n) for max + O(n) for min = O(2n) = O(n) as Big-O ignores constants], 
    	 * SPACE : O(1)
    	 */
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();
        
        System.out.println("Largest : " + max);  // 9
        System.out.println("Smallest: " + min);  // 1
        
        
        //Solution 3 ~ Optimal Approach
        /*
    	 * APPROACH: Single-pass aggregation (using Java 8 Streams)
    	 * Complexity ~ TIME  : O(n) , 
    	 * SPACE : O(1)
    	 * FYI - IntSummaryStatistics have methods like getMin(), getMax(), getSum(), 
    	 * getAverage(), getCount() 
    	 */
        IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
        System.out.println("Largest : " + stats.getMax());  // 9
        System.out.println("Smallest: " + stats.getMin());  // 1
        
    }
}

/*
Output :

Array   : [3, 1, 7, 2, 9, 4]
Largest : 9
Smallest: 1
Largest : 9
Smallest: 1
Largest : 9
Smallest: 1

*/







