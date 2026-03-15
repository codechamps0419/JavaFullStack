/* Day 26 -  26/02/2026 */

package arrays.searching;

import java.util.PriorityQueue;

/**
 * Author: CodeChamps0419
 *
 * Find Kth Largest and Kth Smallest Element
 *
 * APPROACH: Min-Heap of size k for Kth Largest
 *           Max-Heap of size k for Kth Smallest
 * TIME  : O(n log k)
 * SPACE : O(k)
 */
public class KthLargestSmallest {

    // Kth Largest — maintain a min-heap of size k
    public static int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll(); // remove smallest
        }
        return minHeap.peek(); // top of min-heap = kth largest
    }

    // Kth Smallest — maintain a max-heap of size k
    public static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) maxHeap.poll(); // remove largest
        }
        return maxHeap.peek(); // top of max-heap = kth smallest
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        System.out.println("Array       : " + java.util.Arrays.toString(arr));
        System.out.println("3rd Largest : " + kthLargest(arr, 3));  // 10
        System.out.println("3rd Smallest: " + kthSmallest(arr, 3)); // 7
    }
}

/*

Output:

Array       : [7, 10, 4, 3, 20, 15]
3rd Largest : 10
3rd Smallest: 7

*/







