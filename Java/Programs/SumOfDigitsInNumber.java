import java.util.Arrays;

public class SumOfDigitsInNumber {
  
    //TC: O(log n), SP: O(1)
    public static int sumDigits(int number) {
        int sum = 0;
        
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    
    public static void main(String[] args) {
      
      int number = 4179;
      System.out.println("Sum of digits in given number - " + sumDigits(number));
      
      System.out.println("Sum of digits in given number - " +
      String.valueOf(number).chars().map(Character::getNumericValue).sum());
      
      String numberStr = "4179";
      System.out.println("Sum of digits in given number string - " +
      numberStr.chars().map(Character::getNumericValue).sum());
      
      System.out.println("Sum of digits in given number string - " +
      Arrays.stream(numberStr.split("")).mapToInt(Integer::parseInt).sum());
  }
}


/*
Output:

Sum of digits in given number - 21
Sum of digits in given number - 21
Sum of digits in given number string - 21
Sum of digits in given number string - 21

*/
