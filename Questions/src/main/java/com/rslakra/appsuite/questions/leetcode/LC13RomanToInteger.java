package com.rslakra.appsuite.questions.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * <pre>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * </pre>
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply
 * X + II. The number 27 is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 * Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same
 * principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * <pre>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * </pre>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <pre>
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * </pre>
 * <p>
 * Example 3:
 * <pre>
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 9/17/23 10:53 AM
 */
public class LC13RomanToInteger {


    /**
     * @param s
     * @return
     */
    public int romanToIntApproach1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int prev = 1000;
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            current = map.get(s.charAt(i));
            if (current > prev) {
                current -= (2 * prev);
            } else if (current < prev) {
                prev = current;
            }

            result += current;
        }

        return result;
    }

    /**
     * @param s
     * @return
     */
    public int romanToIntApproach2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            // if the value at index i < value at index + 1, then subtract the value of index from index + 1 otherwise add.
            if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                result -= map.get(s.charAt(i));
            } else {
                result += map.get(s.charAt(i));
            }
        }

        return result;
    }

    public int romanToIntApproach3(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        if (s != null && s.length() > 0) {
            int current = 0;
            int i = s.length() - 1;
            while (i > 0) {
                current = map.get(s.charAt(i));
                int prev = map.get(s.charAt(i - 1));
                if (prev < current) {
                    current = current - prev;
                    i--;
                }

                result += current;
                i--;
            }

            if (i == 0) {
                current = map.get(s.charAt(i));
                result += current;
            }
        }

        return result;
    }
}
