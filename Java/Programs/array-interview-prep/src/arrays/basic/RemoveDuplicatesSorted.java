/* Day 16 -  16/02/2026 */

package arrays.basic;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Remove Duplicates from a Sorted Array
 *
 */
public class RemoveDuplicatesSorted {

	/*
	 * APPROACH: Two Pointer (slow-fast) 
	 * TIME : O(n) SPACE : O(1)
	 */
	public static int removeDuplicates(int[] arr) {
		if (arr.length == 0)
			return 0;
		int slow = 0;
		for (int fast = 1; fast < arr.length; fast++) {
			if (arr[fast] != arr[slow]) {
				slow++;
				arr[slow] = arr[fast];
			}
		}
		return slow + 1; // count of unique elements
	}
	
	/*
	 * APPROACH: Stream
	 * Complexity ~ TIME  : O(n), SPACE : O(n) as new array of same size will be created
	 */
	public static int[] removeDuplicatesUsingStream(int[] arr) {
	    return Arrays.stream(arr)
	                 .distinct()
	                 .toArray();
	}

	public static void main(String[] args) {
		int[] arr = { 1, 1, 2, 3, 3, 4, 5, 5 };
				
		//Solution 1
		System.out.println("Before Solution 1 : " + Arrays.toString(arr));
		int k = removeDuplicates(arr);
		System.out.println("Unique count      : " + k); // 5
		System.out.println("After Solution  1 : " + Arrays.toString(Arrays.copyOf(arr, k))); // [1,2,3,4,5]
	
		//Solution 2
		System.out.println("Before Solution 2 : " + Arrays.toString(arr));
		int []distntArr = removeDuplicatesUsingStream(arr);
		System.out.println("After  Solution 2 : " + Arrays.toString(distntArr));
		
	}
}

/*
Output:

Before Solution 1 : [1, 1, 2, 3, 3, 4, 5, 5]
Unique count      : 5
After Solution 1  : [1, 2, 3, 4, 5]
Before Solution 2 : [1, 2, 3, 4, 5, 4, 5, 5]
After  Solution 2 : [1, 2, 3, 4, 5]

*/






