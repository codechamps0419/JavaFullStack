/* Day 17 -  17/02/2026 */

package arrays.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * Author: CodeChamps0419
 *
 * Reverse the elements of an array.
 *
 */
public class ReverseArray {

	/*
	 * APPROACH: Two Pointer
	 * Complexity ~ TIME  : O(n), SPACE : O(1)
	 */
    public static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    
    /*
	 * APPROACH: Stream
	 * Complexity ~ TIME  : O(n), SPACE : O(n) as new array of same size will be created
	 */
    public static int[] reverseUsingStream(int[] arr) {
        return IntStream.range(0, arr.length)
                .map(i -> arr[arr.length - 1 - i])
                .toArray();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        
        //Solution 1 ~ Optimal Approach
        System.out.println("Before: " + Arrays.toString(arr));
        reverse(arr);
        System.out.println("After : " + Arrays.toString(arr)); // [5, 4, 3, 2, 1]
        
        //Solution 2
        System.out.println("Before: " + Arrays.toString(arr));
        int [] revArr = reverseUsingStream(arr);
        System.out.println("After : " + Arrays.toString(revArr)); // [1, 2, 3, 4, 5]
        
        //Solution 3 - Inbuilt Method
        System.out.println("Before: " + Arrays.toString(arr));
        Collections.reverse(Arrays.asList(arr)); //reverse method supports list
        System.out.println("After : " + Arrays.toString(revArr)); // [1, 2, 3, 4, 5]
    }
}


/*
Output:

Before: [1, 2, 3, 4, 5]
After : [5, 4, 3, 2, 1]
Before: [5, 4, 3, 2, 1]
After : [1, 2, 3, 4, 5]
Before: [5, 4, 3, 2, 1]
After : [1, 2, 3, 4, 5]

*/







