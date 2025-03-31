/* Problem Statement -
Given an array, the task is to rearrange it in alternate positive and negative manner without changing the relative order of positive and negative numbers. 
In case of extra positive/negative numbers, they appear at the end of the array.
Note: The rearranged array should start with a positive number and 0 (zero) should be considered as a positive number.
*/

import java.util.stream.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
      
        int []arr1= {1, 2, 3, -4, -1, 4, 0, -19, 19, 7, 0};
        
        //Basic - Two Pointers Approach
        arrangePosNegNoInAlternateManner1(arr1);
        //With Java 8
        int []arr2 = {1, 2, 3, -4, -1, 4, 0, -19, 19, 7, 0};
        arrangePosNegNoInAlternateManner2(arr2);
    
    }
    
     static void arrangePosNegNoInAlternateManner1(int []arr) {
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        // Separate positive and negative numbers
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0)
                pos.add(arr[i]);
            else
                neg.add(arr[i]);
        }

        int posIdx = 0, negIdx = 0;
        int i = 0;

        // Place positive and negative numbers alternately
        // in the original array
        while (posIdx < pos.size() && negIdx < neg.size()) {
            if (i % 2 == 0)
                arr[i++] = pos.get(posIdx++);
            else 
                arr[i++] = neg.get(negIdx++);
        }

        // Append remaining positive numbers (if any)
        while (posIdx < pos.size())
            arr[i++] = pos.get(posIdx++);

        // Append remaining negative numbers (if any)
        while (negIdx < neg.size())
            arr[i++] = neg.get(negIdx++);
            
        System.out.println(Arrays.toString(arr));
    }

    
    
    public static void arrangePosNegNoInAlternateManner2(int[] arr) {
        List<Integer> positives = Arrays.stream(arr)
                .filter(num -> num >= 0)
                .boxed()
                .collect(Collectors.toList());
        
        List<Integer> negatives = Arrays.stream(arr)
                .filter(num -> num < 0)
                .boxed()
                .collect(Collectors.toList());

        int[] result = new int[arr.length];
        int posIndex = 0, negIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0 && posIndex < positives.size()) {
                result[i] = positives.get(posIndex++);
            } else if (negIndex < negatives.size()) {
                result[i] = negatives.get(negIndex++);
            } else if (posIndex < positives.size()) {
                result[i] = positives.get(posIndex++);
            }
        }
        
        System.out.println(Arrays.toString(result));
    }
}

/*
Output:

[1, -4, 2, -1, 3, -19, 4, 0, 19, 7, 0]
[1, -4, 2, -1, 3, -19, 4, 0, 19, 7, 0]

=== Code Execution Successful ===
*/
