/* Day 8 -  08/02/2026 */

package com.interview.strings.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CodeChamps0419
 *
 * Text Justification (LeetCode #68)
 *
 * PROBLEM: Given words and a maxWidth, format text so each line has exactly
 * maxWidth characters and is fully justified (left-right).
 *
 * APPROACH: Greedy Line Packing
 * - Greedily pack as many words as possible per line.
 * - Distribute spaces evenly between words; extra spaces go to leftmost gaps.
 * - Last line is left-justified (single space between words, pad right with spaces).
 *
 * RULES:
 * - If only one word on a line → left-justify (pad right).
 * - Last line → left-justify.
 * - Otherwise → distribute spaces evenly, extra spaces fill left gaps first.
 *
 * TIME  : O(n * maxWidth) — n = number of words
 * SPACE : O(maxWidth) — per line buffer
 */
public class TextJustification {

    /*
     * APPROACH: Greedy Line Packing
     * Complexity ~ TIME : O(n * maxWidth), SPACE : O(maxWidth)
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            // Step 1: determine words that fit on this line
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= maxWidth) {
                lineLen += 1 + words[j].length();
                j++;
            }

            // Step 2: build the line
            int numWords = j - i;
            int numGaps  = numWords - 1;
            StringBuilder sb = new StringBuilder();

            // Last line or single word → left justify
            if (j == words.length || numWords == 1) {
                for (int k = i; k < j; k++) {
                    if (k > i) sb.append(' ');
                    sb.append(words[k]);
                }
                // pad right with spaces
                while (sb.length() < maxWidth) sb.append(' ');
            } else {
                // Distribute spaces evenly
                int totalSpaces = maxWidth - (lineLen - numGaps); // total chars minus gaps already counted
                int spacePerGap = totalSpaces / numGaps;
                int extraSpaces = totalSpaces % numGaps;

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        int spaces = spacePerGap + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spaces; s++) sb.append(' ');
                    }
                }
            }
            result.add(sb.toString());
            i = j;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println("--- Example 1 ---");
        fullJustify(words1, 16).forEach(System.out::println);

        String[] words2 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        System.out.println("--- Example 2 ---");
        fullJustify(words2, 16).forEach(System.out::println);

        String[] words3 = {"Science", "is", "what", "we", "understand", "well", "enough",
                           "to", "explain", "to", "a", "computer."};
        System.out.println("--- Example 3 ---");
        fullJustify(words3, 20).forEach(System.out::println);
    }
}

/*
Output:

--- Example 1 ---
This    is    an
example  of text
justification.  
--- Example 2 ---
What   must   be
acknowledgment  
shall be        
--- Example 3 ---
Science  is  what we
understand      well
enough to explain to
a computer.         

*/


