/* Day 32 -  04/03/2026 */

package arrays.subarray;

/**
 * Author: CodeChamps0419
 *
 * Maximum Subarray Sum — Kadane's Algorithm
 *
 * APPROACH: Dynamic Programming (Kadane's) TIME : O(n) SPACE : O(1)
 */
public class MaxSubarraySum {

	public static int[] maxSubarraySumWithIndices(int[] arr) {

		// Initialize current sum and max sum with first element
		int currentSum = arr[0], maxSum = arr[0];

		// start → final subarray start index
		// end → final subarray end index
		int start = 0, end = 0;

		// tempStart → temporary start index when we restart subarray
		int tempStart = 0;

		// Traverse from second element as first element is initialized for currentSum
		for (int i = 1; i < arr.length; i++) {

			// Decide whether to:
			// 1. Start new subarray from current element
			// 2. Extend existing subarray
			if (arr[i] > currentSum + arr[i]) {
				// Starting new subarray is better
				currentSum = arr[i];

				// Mark this index as potential start
				tempStart = i;
			} else {
				// Extend previous subarray
				currentSum = currentSum + arr[i];
			}

			// Check if we found a new maximum sum
			if (currentSum > maxSum) {
				// Update max sum
				maxSum = currentSum;

				// Update final start and end indices
				start = tempStart; // confirmed start
				end = i; // current index is end
			}
		}

		return new int[] { maxSum, start, end }; // Return result: {maxSum, startIndex, endIndex}
	}

	public static void main(String[] args) {
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println("Array           : " + java.util.Arrays.toString(arr));
		int[] result = maxSubarraySumWithIndices(arr);
		System.out.println("Max Subarray Sum: " + result[0]);                                          // 6
		System.out.println("Start Index     : " + result[1]);                                          // 3
		System.out.println("End Index       : " + result[2]);                                          // 6
		System.out.println("Subarray        : " + java.util.Arrays.toString(
				java.util.Arrays.copyOfRange(arr, result[1], result[2] + 1)));                         // [4,-1,2,1]
	}
}

/*
Output:

Array           : [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Max Subarray Sum: 6
Start Index     : 3
End Index       : 6
Subarray        : [4, -1, 2, 1]

*/







