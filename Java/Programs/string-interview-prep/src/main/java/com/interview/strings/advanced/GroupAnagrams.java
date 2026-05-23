/* Day 3 -  03/02/2026 */

package com.interview.strings.advanced;

import java.util.*;

/**
 * @author CodeChamps0419
 *
 * Group Anagrams from a List of Strings
 *
 * LOGIC (Sorted Key — HashMap):
 * - Sort each word's characters to get a canonical key.
 * - All anagrams produce the same sorted key.
 *
 * ALTERNATIVE (Frequency Array Key):
 * - Use int[26] frequency array as key — O(k) vs O(k log k) for sort.
 *
 * TIME:  O(n * k log k)
 * SPACE: O(n * k)
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            map.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(map.values());
    }

    // O(n*k) alternative using frequency array key
    public static List<List<String>> groupAnagramsOptimal(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) freq[c - 'a']++;
            String key = Arrays.toString(freq);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(input));
        // [[eat, tea, ate], [tan, nat], [bat]]
        System.out.println(groupAnagramsOptimal(input));
    }
}


