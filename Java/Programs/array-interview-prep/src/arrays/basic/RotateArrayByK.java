/* Day 18 -  18/02/2026 */

package arrays.basic;

import java.util.Arrays;

/**
 * Author: CodeChamps0419
 *
 * Rotate Array by K Positions (Left and Right)
 * 
 */
public class RotateArrayByK {

    /*
     * APPROACH: Reversal Algorithm — Right Rotate by k
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        k = k % n; // handle k > n
        reverse(arr, 0, n - 1);     // Step 1: reverse entire array
        reverse(arr, 0, k - 1);     // Step 2: reverse first k elements
        reverse(arr, k, n - 1);     // Step 3: reverse remaining n-k elements
    }

    /*
     * APPROACH: Reversal Algorithm — Left Rotate by k
     * Complexity ~ TIME : O(n), SPACE : O(1)
     */
    public static void rotateLeft(int[] arr, int k) {
        int n = arr.length;
        k = k % n; // handle k > n
        reverse(arr, 0, k - 1);     // Step 1: reverse first k elements
        reverse(arr, k, n - 1);     // Step 2: reverse remaining n-k elements
        reverse(arr, 0, n - 1);     // Step 3: reverse entire array
    }

    /*
     * APPROACH: Stream — Right Rotate by k (new array)
     * Complexity ~ TIME : O(n), SPACE : O(n)
     */
    public static int[] rotateRightUsingStream(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        return java.util.stream.IntStream.concat(
                Arrays.stream(arr, n - k, n),   // last k elements
                Arrays.stream(arr, 0, n - k)    // first n-k elements
        ).toArray();
    }

    private static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left]; arr[left] = arr[right]; arr[right] = temp;
            left++; right--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        // Solution 1 — Right Rotate
        System.out.println("Original    : " + Arrays.toString(arr));
        rotateRight(arr, 3);
        System.out.println("Right by 3  : " + Arrays.toString(arr)); // [5,6,7,1,2,3,4]

        // Solution 2 — Left Rotate
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        rotateLeft(arr2, 3);
        System.out.println("Left by 3   : " + Arrays.toString(arr2)); // [4,5,6,7,1,2,3]

        // Solution 3 — Stream Right Rotate
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Stream Right: " + Arrays.toString(rotateRightUsingStream(arr3, 3))); // [5,6,7,1,2,3,4]
    }
}

/*
Output:

Original    : [1, 2, 3, 4, 5, 6, 7]
Right by 3  : [5, 6, 7, 1, 2, 3, 4]
Left by 3   : [4, 5, 6, 7, 1, 2, 3]
Stream Right: [5, 6, 7, 1, 2, 3, 4]

*/







