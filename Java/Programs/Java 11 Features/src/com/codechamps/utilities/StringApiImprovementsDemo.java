/*
@author CodeChamps0419

Demonstrates Java 11 String API Improvements:
1. isBlank() - Checks for empty or whitespace-only strings (Unicode-aware, efficient)
2. strip() - Removes leading and trailing Unicode whitespaces
3. lines() - Splits multi-line text into a Stream of lines handling all line separators
*/

package com.codechamps.utilities;

import java.util.List;
import java.util.stream.Collectors;

public class StringApiImprovementsDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 11 String API Improvements Demonstrations ---\n");

        // 1. ISBLANK() DEMO
        String blankString = "   \t \n ";
        String normalString = "  Java 11  ";

        System.out.println("1. isBlank() Demonstration:");
        System.out.println("   - Is blankString blank? " + blankString.isBlank());
        System.out.println("   - Is normalString blank? " + normalString.isBlank());
        System.out.println("   - Is blankString empty? " + blankString.isEmpty());
        System.out.println();

        // 2. STRIP() vs TRIM() UNICODE DEMO
        System.out.println("2. strip() vs trim() Demonstration:");
        String unicodeSpaces = "\u2000Hello Java 11\u2000";

        System.out.println("   - Original length: " + unicodeSpaces.length());
        System.out.println("   - trim() result length (ASCII only): " + unicodeSpaces.trim().length());
        System.out.println("   - strip() result length (Unicode-aware): " + unicodeSpaces.strip().length());
        System.out.println("   - stripLeading(): '" + unicodeSpaces.stripLeading() + "'");
        System.out.println("   - stripTrailing(): '" + unicodeSpaces.stripTrailing() + "'\n");

        // 3. LINES() DEMO
        System.out.println("3. lines() Stream Processing Demonstration:");
        String multiLineText = "Line 1: Header\r\n\r\nLine 2: Content Payload\nLine 3: Footer";

        List<String> processedLines = multiLineText.lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());

        System.out.println("   - Processed lines count: " + processedLines.size());
        processedLines.forEach(line -> System.out.println("     -> " + line));
    }
}

/*
Output:

--- Java 11 String API Improvements Demonstrations ---

1. isBlank() Demonstration:
   - Is blankString blank? true
   - Is normalString blank? false
   - Is blankString empty? false

2. strip() vs trim() Demonstration:
   - Original length: 17
   - trim() result length (ASCII only): 17
   - strip() result length (Unicode-aware): 13
   - stripLeading(): 'Hello Java 11 '
   - stripTrailing(): ' Hello Java 11'

3. lines() Stream Processing Demonstration:
   - Processed lines count: 3
     -> Line 1: Header
     -> Line 2: Content Payload
     -> Line 3: Footer

*/
