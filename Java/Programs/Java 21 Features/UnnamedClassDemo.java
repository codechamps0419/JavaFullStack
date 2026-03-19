/*
@author CodeChamps0419

Demonstrates Java 21 Unnamed Classes and Instance Main Methods (JEP 445 - Preview Feature):
- Evolution Note: Introduced as a developer-friendly preview feature in Java 21 (JEP 445) 
  to streamline small programs, scripts, and learning by removing explicit class and static boilerplate.
1. Implicitly declared unnamed top-level class.
2. Instance main method (`void main()`) without static modifier or String[] args array.
3. Direct top-level helper methods and fields accessible within the unnamed class scope.

Note: use --enable-preview to enable preview APIs and run this program (If not then you will get errors)
*/

// No package statement or class declaration required!

String greetingMessage = "Welcome to Java 21 Unnamed Classes!";

void printBanner() {
    System.out.println("   " + greetingMessage);
}

// Instance main method (non-static, no args required)
void main() {
    printBanner();
    
    // Testing flexible signature options
    System.out.println("   -> Instance main executed successfully without 'public static void main(String[] args)'!");
    
    // Demonstrating standard logic execution
    int coreCount = Runtime.getRuntime().availableProcessors();
    System.out.println("   -> Available CPU Cores detected: " + coreCount);
}

/*
How to run?
> java UnnamedClassDemo.java
	or
> javac UnnamedClassDemo.java
> java UnnamedClassDemo

Output:

   Welcome to Java 21 Unnamed Classes!
   -> Instance main executed successfully without 'public static void main(String[] args)'!
   -> Available CPU Cores detected: 14

*/