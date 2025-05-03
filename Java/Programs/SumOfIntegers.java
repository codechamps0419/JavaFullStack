import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
//import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class SumOfIntegers {
    public static void main(String[] args) {
      
      List<Integer> numbers = Arrays.asList(4, 1, 9, 7);
      int[] numbersArr = {4, 1, 9, 7};
      
      //Using mapToInt and sum
      System.out.println("Sum of given numbers - "+ 
      numbers.stream().mapToInt(Integer::intValue).sum()); 
      //mapToInt(num -> num) - Even if intValue() not passed, it will work due to Unboxing
      
      //Using reduce
      System.out.println("Sum of given numbers - "+ 
      Stream.of(4,1,9,7).reduce(0, Integer::sum)); 
      //IntStream.of(4,1,9,7).reduce(0, (a, b) -> a+b)
      
      //Using summingInt and collect
      System.out.println("Sum of given numbers - "+ 
      numbers.stream().collect(Collectors.summingInt(num -> num.intValue())));
                     //collect(Collectors.summingInt(Integer::intValue))
                     
      System.out.println("Sum of given numbers in array - "+
      Arrays.stream(numbersArr).sum());
      
  }
}


/*
Output:

Sum of given numbers - 21
Sum of given numbers - 21
Sum of given numbers - 21
Sum of given numbers in array - 21

*/
