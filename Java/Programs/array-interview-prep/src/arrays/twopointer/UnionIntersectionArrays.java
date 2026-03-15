/* Day 40 -  12/03/2026 */

package arrays.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: CodeChamps0419
 *
 * Union and Intersection of Two Sorted Arrays
 *
 */
public class UnionIntersectionArrays {

    /*
     * APPROACH: Two Pointer — Union
     * Complexity ~ TIME : O(m+n), SPACE : O(1) excluding output
     */
    public static List<Integer> union(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            // skip duplicates in arr1
            if (i > 0 && arr1[i] == arr1[i - 1]) { i++; continue; }
            // skip duplicates in arr2
            if (j > 0 && arr2[j] == arr2[j - 1]) { j++; continue; }

            if (arr1[i] < arr2[j])       result.add(arr1[i++]);
            else if (arr1[i] > arr2[j])  result.add(arr2[j++]);
            else { result.add(arr1[i++]); j++; } // equal — add once
        }
        while (i < arr1.length) { if (i == 0 || arr1[i] != arr1[i-1]) result.add(arr1[i]); i++; }
        while (j < arr2.length) { if (j == 0 || arr2[j] != arr2[j-1]) result.add(arr2[j]); j++; }
        return result;
    }

    /*
     * APPROACH: Two Pointer — Intersection
     * Complexity ~ TIME : O(m+n), SPACE : O(1) excluding output
     */
    public static List<Integer> intersection(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j])      i++;
            else if (arr1[i] > arr2[j]) j++;
            else {
                // add only if not duplicate in result
                if (result.isEmpty() || result.getLast() != arr1[i])
                    result.add(arr1[i]);
                i++; j++;
            }
        }
        return result;
    }

    /*
     * APPROACH: Stream — Union and Intersection
     * Complexity ~ TIME : O(m+n), SPACE : O(m+n)
     */
    public static List<Integer> unionUsingStream(int[] arr1, int[] arr2) {
        return java.util.stream.IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .distinct().sorted().boxed().collect(Collectors.toList());
    }

    public static List<Integer> intersectionUsingStream(int[] arr1, int[] arr2) {
        java.util.Set<Integer> set = Arrays.stream(arr1).boxed()
                .collect(Collectors.toCollection(java.util.LinkedHashSet::new));
        return Arrays.stream(arr2).boxed()
                .filter(set::remove)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 4, 5, 6, 7};

        System.out.println("Array1            : " + Arrays.toString(arr1));
        System.out.println("Array2            : " + Arrays.toString(arr2));

        // Solution 1
        System.out.println("Union (S1)        : " + union(arr1, arr2));        // [1,2,3,4,5,6,7]
        System.out.println("Intersection (S1) : " + intersection(arr1, arr2)); // [3,4,5]

        // Solution 2
        System.out.println("Union (S2)        : " + unionUsingStream(arr1, arr2));        // [1,2,3,4,5,6,7]
        System.out.println("Intersection (S2) : " + intersectionUsingStream(arr1, arr2)); // [3,4,5]
    }
}

/*
Output:

Array1            : [1, 2, 3, 4, 5]
Array2            : [3, 4, 5, 6, 7]
Union (S1)        : [1, 2, 3, 4, 5, 6, 7]
Intersection (S1) : [3, 4, 5]
Union (S2)        : [1, 2, 3, 4, 5, 6, 7]
Intersection (S2) : [3, 4, 5]

*/







