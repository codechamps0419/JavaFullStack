/* Day 9 -  09/02/2026 */

package arrays.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: CodeChamps0419
 *
 * Count Frequency of Each Element in an Array
 *
 */
public class FrequencyCount {

    /*
     * APPROACH: HashMap
     * Complexity ~ TIME : O(n), SPACE : O(n)
     */
    public static Map<Integer, Integer> countFrequency(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap;
    }

    /*
     * APPROACH: Stream (Collectors.groupingBy)
     * Complexity ~ TIME : O(n), SPACE : O(n)
     */
    public static Map<Integer, Long> countFrequencyUsingStream(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};

        // Solution 1
        System.out.println("Array      : " + Arrays.toString(arr));
        System.out.println("Freq (S1)  : " + countFrequency(arr));           // {1=1, 2=2, 3=3, 4=1}

        // Solution 2
        System.out.println("Freq (S2)  : " + countFrequencyUsingStream(arr)); // {1=1, 2=2, 3=3, 4=1}
    }
}

/*
Output:

Array      : [1, 2, 2, 3, 3, 3, 4]
Freq (S1)  : {1=1, 2=2, 3=3, 4=1}
Freq (S2)  : {1=1, 2=2, 3=3, 4=1}

*/







