/* Day 33 -  05/03/2026 */

package arrays.subarray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: CodeChamps0419
 *
 * Find Sub-array with Given Sum 
 *
 * Interview Line - If the array contains only positive numbers, the optimal approach is Sliding Window 
 * else Prefix Sum with HashMap will be optimal approach which covers both positive and negative elements
 */
public class SubarrayWithGivenSum {

	/*
	 * APPROACH: Sliding Window (Works only for positive numbers)
	 * TIME  : O(n)
	 * SPACE : O(1)
	 */
    public static int[] findSubarray(int[] arr, int target) {
        int start = 0, currentSum = 0;
        for (int end = 0; end < arr.length; end++) {
            currentSum += arr[end];
            while (currentSum > target && start < end) {
                currentSum -= arr[start++]; // shrink window from left
            }
            if (currentSum == target) return new int[]{start, end};
        }
        return new int[]{-1, -1}; // no sub-array found
    }

    /*
	 * APPROACH: Prefix Sum with HashMap (Works for both positive and negative numbers)
	 * TIME  : O(n)
	 * SPACE : O(n) as in worst case we need to store all prefix values in HashMap 
	 */
    public static int[] findSubarrayWithGivenSumPosOrNeg(int[] arr, int target) {
        Map<Integer, Integer> prefixMap = new HashMap<>();

        int currentSum = 0;

        // Important: handles case when sub-array starts from index 0
        prefixMap.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            // If (currentSum - target) exists, sub-array found
            if (prefixMap.containsKey(currentSum - target)) {
                int start = prefixMap.get(currentSum - target) + 1;
                int end = i;
                return new int[]{start, end};
            }

            // Store prefix sum only first time
            prefixMap.putIfAbsent(currentSum, i);
        }

        return new int[]{-1, -1}; // no sub-array found
    }
    
    public static void main(String[] args) {
    	
        int[] arr = {1, 4, 20, 3, 10, 5};
        int target = 33;
        System.out.println("Input Array (Positive Numbers Only)  : " + java.util.Arrays.toString(arr) + ", Target Sum : " + target);
        
        int[] result = findSubarray(arr, target);
        if (result[0] != -1) {
            System.out.println("Sub-array with target sum found from index " + result[0] + " to " + result[1]);
            System.out.println(java.util.Arrays.toString(Arrays.copyOfRange(arr, result[0], result[1]+1)));
        } else {
            System.out.println("No subarray found");
        }
        
        int[] posNegArr = {10, 2, -2, -20, 10};
        int targetValue = -10;
        System.out.println("\nInput Array (Positive & Negative Numbers) : " + java.util.Arrays.toString(posNegArr) + ", Target Sum : " + targetValue);
        
        int[] resultArr = findSubarrayWithGivenSumPosOrNeg(posNegArr, targetValue);

        if (resultArr[0] != -1) {
        	System.out.println("Sub-array with target sum found from index " + resultArr[0] + " to " + resultArr[1]);
            System.out.println(java.util.Arrays.toString(Arrays.copyOfRange(posNegArr, resultArr[0], resultArr[1]+1)));
        } else {
            System.out.println("No subarray found");
        }
    }
}

/*
Output:

Input Array (Positive Numbers Only)  : [1, 4, 20, 3, 10, 5], Target Sum : 33
Sub-array with target sum found from index 2 to 4
[20, 3, 10]

Input Array (Positive & Negative Numbers) : [10, 2, -2, -20, 10], Target Sum : -10
Sub-array with target sum found from index 0 to 3
[10, 2, -2, -20]

*/






