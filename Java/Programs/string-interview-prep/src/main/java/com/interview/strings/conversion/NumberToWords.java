/* Day 17 -  17/02/2026 */

package com.interview.strings.conversion;

/**
 * @author CodeChamps0419
 *
 * Convert a Number to Words
 *
 * LOGIC:
 * - Break number into groups of 3 digits (ones, thousands, millions, billions).
 * - For each group, convert the 3-digit chunk using helper.
 * - Append the scale word (Thousand, Million, Billion) for non-zero groups.
 *
 * TIME:  O(1) — number has at most 10 digits (bounded input)
 * SPACE: O(1)
 */
public class NumberToWords {

    private static final String[] ONES  = {"", "One", "Two", "Three", "Four", "Five",
                                            "Six", "Seven", "Eight", "Nine", "Ten",
                                            "Eleven", "Twelve", "Thirteen", "Fourteen",
                                            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS  = {"", "", "Twenty", "Thirty", "Forty", "Fifty",
                                            "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] SCALE = {"", "Thousand", "Million", "Billion"};

    public static String numberToWords(int num) {
        if (num == 0) return "Zero";
        if (num < 0)  return "Negative " + numberToWords(-num);

        StringBuilder result = new StringBuilder();
        int scaleIndex = 0;
        while (num > 0) {
            int chunk = num % 1000;
            if (chunk != 0) {
                String chunkWords = threeDigits(chunk);
                String scale = SCALE[scaleIndex];
                result.insert(0, chunkWords + (scale.isEmpty() ? "" : " " + scale) + (result.isEmpty() ? "" : " "));
            }
            num /= 1000;
            scaleIndex++;
        }
        return result.toString().trim();
    }

    private static String threeDigits(int n) {
        if (n == 0) return "";
        if (n < 20) return ONES[n];
        if (n < 100) return TENS[n / 10] + (n % 10 != 0 ? " " + ONES[n % 10] : "");
        return ONES[n / 100] + " Hundred" + (n % 100 != 0 ? " " + threeDigits(n % 100) : "");
    }

    public static void main(String[] args) {
        System.out.println(numberToWords(0));        // Zero
        System.out.println(numberToWords(13));       // Thirteen
        System.out.println(numberToWords(1234));     // One Thousand Two Hundred Thirty Four
        System.out.println(numberToWords(1000000));  // One Million
        System.out.println(numberToWords(1000010));  // One Million Ten
    }
}


