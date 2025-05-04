import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.function.Function;

public class MaximumOccurringCharacter {
  
  public static void main(String[] args) {
      
    String input = "Welcome, CodeChamps!";
      
    //Without Java 8 features - TC : O(n), SP: O(1)
    findMaxiumOccuringCharacter(input);
      
    //Java 8 features - TC : O(n), SC : O(K) - k is the number of unique characters in the string
    System.out.println("Highest frequency character in a given string : " + 
    getMaxFrequencyChar(input));
      
    findMaxFrequencyCharWithCount(input);
    
  }
  
  public static void findMaxiumOccuringCharacter(String input)
  {
    HashMap<Character,Integer> max = new HashMap<>();
    max.put(input.charAt(0), input.length() - input.replace(String.valueOf(input.charAt(0)), "").length());
      
    int count = 0;
    for(char c : input.toCharArray())
    {
      count = input.length() - input.replace(String.valueOf(c), "").length();
      
      if(count > max.get(max.keySet().iterator().next()))
      {
        max.clear();
        max.put(c, count);
      }
      
    }
    System.out.println("Maximum occurring character in a given string : "+ max.keySet().iterator().next()); 
  
  }
  
  public static char getMaxFrequencyChar(String input)
  {
    return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)  //
                .orElse('\0');
  }
  
  public static void findMaxFrequencyCharWithCount(String input)
  {
    Map.Entry<Character,Long> mostFrequentEntry = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //LinkedHashMap to maintain order of insertion
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
                
    if (mostFrequentEntry != null) {
      System.out.print("Highest frequency character : " + mostFrequentEntry.getKey());
      System.out.print(" and Count : " + mostFrequentEntry.getValue());
    } 
    else {
        System.out.println("String is empty.");
    }
  }
  
}


/*
Output:

Maximum occurring character in a given string : e
Highest frequency character in a given string : e
Highest frequency character : e and Count : 3


Input - AS&HP
Output - 
Maximum occurring character in a given string : A
Highest frequency character in a given string : P
Highest frequency character : A and Count : 1

*/
