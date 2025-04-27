//Write a program to find the first non-repeating character in a given string

import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class FindFirstNonRepeatingCharacter {
  
   public static void main(String[] args) {
      String msg = "Hey, CodeChamps!";
      
      System.out.println("First non-repeating character: "+ findFirstNonRepeatingChar(msg)); //using java 8 features
      System.out.println("First non-repeating character: "+ findFirstNonRepeatingCharIgnoreCase(msg)); ///using java 8 features
      
      System.out.println("First non-occurring character: "+ findFirstNonOccurringCharacter(msg)); //without using HashMap
      System.out.println("First non-occurring character: "+ findFirstNonOccurringCharacterUsingHM(msg)); //using HashMap
      
  }
  
  //TC : O(n), SC : O(K) - k is the number of unique characters in the string
  public static Character findFirstNonRepeatingChar(String input) {
    
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
        
  }
  
  //TC : O(n), SC : O(K) - k is the number of unique characters in the string
  public static Character findFirstNonRepeatingCharIgnoreCase(String input) {
    
        return input.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
        
  }
  
  //TC : O(n), SC : O(1)
  public static char findFirstNonOccurringCharacter(String str) {
        //Array to store character counts
        int[] charCount = new int[256]; 
        //Count occurrences of each character
        for (char c : str.toCharArray()) {
            charCount[c]++;
        }
 
        //Find the first non-repeating character
        for (char c : str.toCharArray()) {
            if (charCount[c] == 1) {
                return c; // Return the first non-repeating character
            }
        }
        //Return null character if none found
        return '\0'; 
    }
    
    //TC : O(n), SC : O(n)
    public static Character findFirstNonOccurringCharacterUsingHM(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        
        Map<Character, Integer> charCounts = new HashMap<>();
        
        //Count occurrences of each character
        for (char c : input.toCharArray()) {     //input.toLowerCase().toCharArray() - Ignore case of repeating char
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        //Find the first character with count 1
        for (char c : input.toCharArray()) {
            if (charCounts.getOrDefault(c,0) == 1) {
                return c;
            }
        }

        return null;
    }
      
}


/*

Output:

First non-repeating character: H
First non-repeating character: y
First non-occurring character: H
First non-occurring character: H

*/
