package com.rslakra.appsuite.questions.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer
 * in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1: Input: s = "cbaebabacd", p = "abc" Output: [0,6] Explanation: The substring with start index = 0 is "cba",
 * which is an anagram of "abc". The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <p>
 * Example 2: Input: s = "abab", p = "ab" Output: [0,1,2] Explanation: The substring with start index = 0 is "ab", which
 * is an anagram of "ab". The substring with start index = 1 is "ba", which is an anagram of "ab". The substring with
 * start index = 2 is "ab", which is an anagram of "ab".
 *
 * @author Rohtash Lakra
 * @created 9/19/23 4:46 PM
 */
public class LC438FindAllAnagrams {

    /**
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] hash = new int[26];
        for (int i = 0; i < p.length(); i++) {
            hash[s.charAt(i) - 'a']++;
        }
        System.out.println(Arrays.toString(hash));

        int start = 0;
        int end = 0;
        int count = p.length();
        while (end < s.length()) {
            if (hash[s.charAt(end) - 'a'] > 0) {
                count--;
            }

            hash[s.charAt(end) - 'a']--;
            end++;

            if (count == 0) {
                result.add(start);
            }

            if ((end - start) == p.length()) {
                if (hash[s.charAt(start) - 'a'] >= 0) {
                    count++;
                }

                hash[s.charAt(start) - 'a']++;
                start++;
            }
        }

        return result;
    }
}
