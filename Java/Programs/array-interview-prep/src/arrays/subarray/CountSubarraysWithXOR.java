/* Day 29 -  01/03/2026 */

package arrays.subarray;

import java.util.HashMap;

/**
 * Author: CodeChamps0419
 *
 * Count Subarrays with Given XOR
 *
 * APPROACH: Prefix XOR + HashMap
 * TIME  : O(n)
 * SPACE : O(n)
 */
public class CountSubarraysWithXOR {

    public static int countSubarraysWithXOR(int[] arr, int k) {
        HashMap<Integer, Integer> prefixXorCount = new HashMap<>();
        prefixXorCount.put(0, 1); // empty subarray has XOR = 0
        int prefixXor = 0, count = 0;
        for (int num : arr) {
            prefixXor ^= num;
            // if prefixXor ^ k exists, those subarrays ending here have XOR = k
            count += prefixXorCount.getOrDefault(prefixXor ^ k, 0);
            prefixXorCount.put(prefixXor, prefixXorCount.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 6, 4};
        int k = 6;
        System.out.println("Array  : " + java.util.Arrays.toString(arr));
        System.out.println("k      : " + k);
        System.out.println("Count  : " + countSubarraysWithXOR(arr, k)); // 4
    }
}

/*

Output:

Array  : [4, 2, 2, 6, 4]
k      : 6
Count  : 4

*/






