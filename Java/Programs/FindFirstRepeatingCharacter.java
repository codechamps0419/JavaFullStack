//Write a program to find the first repeating character in a given string

import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashSet;

public class FindFirstRepeatingCharacter {
  
    public static void main(String[] args) {
      
      String msg = "Hey, CodeChamps!";
      
      System.out.println("First repeating character: "+ findFirstRepeatingChar(msg)); //using java 8 features
      System.out.println("First repeating character: "+ findFirstRepeatingCharIgnoreCase(msg)); ///using java 8 features
      
      System.out.println("First occurring character: "+ findFirstOccurringCharacter(msg)); //without using HashMap
      System.out.println("First occurring character: "+ findFirstOccurringCharacterUsingHM(msg)); //using HashMap
      System.out.println("First occurring character: "+ findFirstOccurringCharacterUsingHS(msg)); //using HashMap
      
  }
  
  //TC : O(n), SC : O(K) - k is the number of unique characters in the string
  public static Character findFirstRepeatingChar(String input) {
    
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
        
  }
  
  //TC : O(n), SC : O(K) - k is the number of unique characters in the string
  public static Character findFirstRepeatingCharIgnoreCase(String input) {
    
        return input.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
        
  }
  
  //TC : O(n), SC : O(1)
  public static char findFirstOccurringCharacter(String input) {
        //Array to store character counts
        int[] charCount = new int[256]; 
        //Count occurrences of each character
        for (char c : input.toCharArray()) {
            charCount[c]++;
        }
 
        //Find the first non-repeating character
        for (char c : input.toCharArray()) {
            if (charCount[c] > 1) {
                return c; // Return the first non-repeating character
            }
        }
        //Return null character if none found
        return '\0'; 
    }
    
  //TC : O(n), SC : O(K) - k is the number of unique characters in the string
  public static char findFirstOccurringCharacterUsingHM(String input) {
        HashMap<Character, Integer> charMap = new HashMap<>();

        for (char c : input.toCharArray()) {     //input.toLowerCase().toCharArray() - Ignore case of repeating char
            if (charMap.containsKey(c)) {
                return c;
            } else {
                charMap.put(c, 1);
            }
        }
        //Return null character if no repeating character is found
        return '\0'; 
    }
    
  public static char findFirstOccurringCharacterUsingHS(String input) {
        Set<Character> seen = new LinkedHashSet<>();
        for (char c : input.toCharArray()) {
            if (seen.contains(c)) {
                return c;
            }
            seen.add(c);
        }
        //Return null character if no repeating character is found
        return '\0';
  }
      
}


/*

First repeating character: e
First repeating character: h
First occurring character: e
First occurring character: e
First occurring character: e

*/
