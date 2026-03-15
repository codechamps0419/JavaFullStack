/* Day 19 -  19/02/2026 */

package arrays.basic;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Author: CodeChamps0419
 *
 * Find the Second Largest Element in an Array.
 *
 */
public class SecondLargest {

	/*
	 * APPROACH: Single Pass Linear Scan with 2 Variables
	 * Complexity ~ TIME  : O(n), SPACE : O(1)
	 */
    public static int findSecondLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) { //(num != first is for check & skip duplicate value
                second = num;
            }
        }
        return second; // returns MIN_VALUE if no second largest exists
    }
   
    /*
	 * APPROACH: Min Heap of size 2 (Top-K approach)
	 * Complexity ~ TIME  : O(n log 2) → O(n), SPACE : O(1) (since k=2)
	 */
    public static int findSecondLargestUsingStream(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Arrays.stream(arr).forEach(num -> {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll(); //remove element at top of priority queue when heap size grows beyond 2
            }
        });

        return pq.peek();
    }

    public static void main(String[] args) {
        int[] arr = {12, 35, 1, 10, 34, 1};
        System.out.println("Array          : " + Arrays.toString(arr));
        
        //Solution 1
        System.out.println("Second Largest : " + findSecondLargest(arr)); // 34
        
        //Solution 2
        System.out.println("Second Largest : " + findSecondLargestUsingStream(arr, 2)); // 34
    }
}

/*
Output :

Array          : [12, 35, 1, 10, 34, 1]
Second Largest : 34
Second Largest : 34

*/






