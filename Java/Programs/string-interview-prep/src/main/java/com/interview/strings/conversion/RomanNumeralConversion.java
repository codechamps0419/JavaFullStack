/* Day 18 -  18/02/2026 */

package com.interview.strings.conversion;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author CodeChamps0419
 *
 * Roman Numeral ↔ Integer Conversion
 *
 * ROMAN TO INT: If current value < next value → subtract. Otherwise → add.
 * INT TO ROMAN: Greedily subtract largest possible value, appending its symbol.
 *
 * TIME:  O(n) for both
 * SPACE: O(1) — fixed symbol table
 */
public class RomanNumeralConversion {

    private static final Map<Integer, String> INT_TO_ROMAN = new LinkedHashMap<>();
    static {
        INT_TO_ROMAN.put(1000, "M"); INT_TO_ROMAN.put(900, "CM");
        INT_TO_ROMAN.put(500, "D");  INT_TO_ROMAN.put(400, "CD");
        INT_TO_ROMAN.put(100, "C");  INT_TO_ROMAN.put(90, "XC");
        INT_TO_ROMAN.put(50, "L");   INT_TO_ROMAN.put(40, "XL");
        INT_TO_ROMAN.put(10, "X");   INT_TO_ROMAN.put(9, "IX");
        INT_TO_ROMAN.put(5, "V");    INT_TO_ROMAN.put(4, "IV");
        INT_TO_ROMAN.put(1, "I");
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50,
            'C', 100, 'D', 500, 'M', 1000
        );
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = map.get(s.charAt(i));
            int next = (i + 1 < s.length()) ? map.get(s.charAt(i + 1)) : 0;
            result += (curr < next) ? -curr : curr;
        }
        return result;
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : INT_TO_ROMAN.entrySet()) {
            while (num >= entry.getKey()) {
                sb.append(entry.getValue());
                num -= entry.getKey();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));    // 3
        System.out.println(romanToInt("IV"));     // 4
        System.out.println(romanToInt("MCMXCIV")); // 1994
        System.out.println(intToRoman(3));        // III
        System.out.println(intToRoman(1994));     // MCMXCIV
        System.out.println(intToRoman(58));       // LVIII
    }
}


