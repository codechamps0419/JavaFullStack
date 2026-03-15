/* Day 14 -  14/02/2026 */

package arrays.basic;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Author: CodeChamps0419
 *
 * Find the missing number in an array
 * 
 */
public class MissingNumber {

	/*
	 * APPROACH: Mathematical Formula Approach
	 * Complexity ~ TIME  : O(n), SPACE : O(1)
	 */
    public static int findMissing(int[] arr, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : arr) actualSum += num;
        return expectedSum - actualSum;
    }
    
    /*
	 * APPROACH: XOR Method
	 * Complexity ~ TIME  : O(n), SPACE : O(1)
	 */
    public static int findMissingUsingXOR(int[] arr, int n) {
        int xor1 = 0; // XOR of 1 to n
        int xor2 = 0; // XOR of array elements

        for (int i = 1; i <= n; i++) {
            xor1 ^= i;
        }

        for (int num : arr) {
            xor2 ^= num;
        }

        return xor1 ^ xor2;
    }
    

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6}; // missing 3, n=6
        
        //Solution 1 - Can exceeds the maximum value that a data type can store
        System.out.println("Missing Number: " + findMissing(arr, 6)); // 3
        
        //Solution 2
        System.out.println("Missing Number: " + findMissingUsingXOR(arr, 6)); // 3
        
        //Solution 3 - Can exceeds the maximum value that a data type can store
        /*
    	 * APPROACH: Stream
    	 * Complexity ~ TIME  : O(n), SPACE : O(1)
    	 */
        int n = arr.length + 1; // total numbers including missing
        int missing = IntStream.rangeClosed(1, n).sum() - Arrays.stream(arr).sum();
        System.out.println("Missing Number: " + missing); // 3
    }
}

/*
Output:

Missing Number: 3
Missing Number: 3
Missing Number: 3

*/







