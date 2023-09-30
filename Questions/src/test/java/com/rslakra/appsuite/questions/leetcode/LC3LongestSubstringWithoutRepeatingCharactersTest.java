package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/4/23 11:26 AM
 */
public class LC3LongestSubstringWithoutRepeatingCharactersTest {

    @DataProvider
    public Iterator<Object[]> longestSubstring() {
        List<Object[]> inputs = new ArrayList<>();
//        Example 1:
//        Input: s = "abcabcbb"
//        Output: 3
//        Explanation: The answer is "abc", with the length of 3.
        inputs.add(new Object[]{"abcabcbb", "abc", 3});
//        Example 2:
//        Input: s = "bbbbb"
//        Output: 1
//        Explanation: The answer is "b", with the length of 1.
        inputs.add(new Object[]{"bbbbb", "b", 1});
//        Example 3:
//        Input: s = "pwwkew"
//        Output: 3
//        Explanation: The answer is "wke", with the length of 3.
        inputs.add(new Object[]{"pwwkew", "wke", 3});
//        Example 4:
//        Input: s = "rohtash"
//        Output: 3
//        Explanation: The answer is "rohtas", with the length of 6.
        inputs.add(new Object[]{"rohtash", "rohtas", 6});
//        Example 5:
//        Input: s = "lakra"
//        Output: 4
//        Explanation: The answer is "lakr", with the length of 4.
        inputs.add(new Object[]{"lakra", "lakr", 4});

//        Example 6:
//        Input: s = "dvdf"
//        Output: 4
//        Explanation: The answer is "vdf", with the length of 3.
        inputs.add(new Object[]{"dvdf", "vdf", 3});
        return inputs.iterator();
    }

    @Test(dataProvider = "longestSubstring")
    public void testLongestSubstringWithoutRepeatingCharactersTest(String input, String subStr, Integer length) {
        LC3LongestSubstringWithoutRepeatingCharacters object = new LC3LongestSubstringWithoutRepeatingCharacters();
        assertEquals(length, object.lengthOfLongestSubstringWithSet(input));
        assertEquals(length, object.lengthOfLongestSubstringWithMap(input));
        assertEquals(length, object.lengthOfLongestSubstringWithMapReplaceIndex(input));
        assertEquals(length, object.lengthOfLongestSubstringWithArray(input));
        assertEquals(length, object.lengthOfLongestSubstringWithSubString(input));
    }
}
