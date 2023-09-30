package com.rslakra.appsuite.questions.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * @author Rohtash Lakra
 * @created 9/4/23 11:12 AM
 */
public class LC3LongestSubstringWithoutRepeatingCharacters {

    private static final Logger LOGGER = LoggerFactory.getLogger(LC3LongestSubstringWithoutRepeatingCharacters.class);

    public int lengthOfLongestSubstringWithSet(String str) {
        int length = 0;
        if (str != null) {
            Set<Character> unique = new HashSet<>();
            int left = 0, right = 0;
            while (left <= right && right < str.length()) {
                if (unique.contains(str.charAt(right))) {
                    unique.remove(str.charAt(left));
                    left++;
//                    length = Math.max(length, unique.size());
                } else {
                    unique.add(str.charAt(right));
                    right++;
                }
                length = Math.max(length, unique.size());
            }
        }

        LOGGER.debug("Longest substring length from {} = {}", str, length);
        return length;
    }


    public int lengthOfLongestSubstringWithMap(String str) {
        int length = 0;
        if (str != null) {
            Map<Character, Integer> unique = new HashMap<>();
            int left = 0, right = 0;
            while (left <= right && right < str.length()) {
                if (unique.containsKey(str.charAt(right))) {
                    unique.remove(str.charAt(left));
                    left++;
//                    length = Math.max(length, unique.size());
                } else {
                    unique.put(str.charAt(right), right);
                    right++;
                }
                length = Math.max(length, unique.size());
            }
        }

        LOGGER.debug("Longest substring length from {} = {}", str, length);
        return length;
    }


    public int lengthOfLongestSubstringWithMapReplaceIndex(String str) {
        int length = 0;
        if (str != null) {
            Map<Character, Integer> map = new HashMap<>();
            int left = 0;
            for (int right = 0; right < str.length(); right++) {
                if (!map.containsKey(str.charAt(right)) || map.get(str.charAt(right)) < left) {
                    length = Math.max(length, right - left + 1);
                } else {
                    left = map.get(str.charAt(right)) + 1;
                }
                map.put(str.charAt(right), right);
            }
        }

        LOGGER.debug("Longest substring length from {} = {}", str, length);
        return length;
    }


    public int lengthOfLongestSubstringWithArray(String str) {
        int length = 0;
        if (str != null) {
            int[] arr = new int[128];
            Arrays.fill(arr, -1);
            int left = 0;
            for (int right = 0; right < str.length(); right++) {
                if (arr[str.charAt(right)] >= left) {
                    left = arr[str.charAt(right)] + 1;
                }
                arr[str.charAt(right)] = right;
                length = Math.max(length, right - left + 1);
            }
        }

        LOGGER.debug("Longest substring length from {} = {}", str, length);
        return length;
    }


    public int lengthOfLongestSubstringWithSubString(String str) {
        int length = 0;
        if (str != null && str.length() > 0) {
            length = 1;
            int left = 0;
            int right = left + 1;
            while (left <= right && right < str.length()) {
                String subStr = str.substring(left, right);
                char cChar = str.charAt(right);
                if (subStr.lastIndexOf(cChar) == -1) {
                    int subStrLen = subStr.length();
                    if (left < right && length < subStrLen + 1) {
                        length = subStrLen + 1;
                    }
                    right++;
                } else {
                    left++;
                }
            }
        }

        LOGGER.debug("Longest substring length from {} = {}", str, length);
        return length;
    }
}
