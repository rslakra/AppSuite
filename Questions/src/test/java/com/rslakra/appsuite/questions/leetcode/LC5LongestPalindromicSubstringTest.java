package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/6/23 2:44 PM
 */
public class LC5LongestPalindromicSubstringTest {


    @DataProvider
    public Iterator<Object[]> longestPalindromeData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: s = "babad"
//        Output: "bab"
//        Explanation: "aba" is also a valid answer.
        inputs.add(new Object[]{"babad", Arrays.asList("bab", "aba")});

//        Example 2:
//        Input: s = "cbbd"
//        Output: "bb"
        inputs.add(new Object[]{"cbbd", Arrays.asList("bb")});

//        Example 3:
//        Input: s = "nitin"
//        Output: "nitin"
        inputs.add(new Object[]{"nitin", Arrays.asList("nitin")});

//        Example 4:
//        Input: s = "racecar"
//        Output: "racecar"
        inputs.add(new Object[]{"racecar", Arrays.asList("racecar")});

//        Example 5:
//        Input: s = "nenonen"
//        Output: "nenonen"
        inputs.add(new Object[]{"nenonen", Arrays.asList("nenonen")});

//        Example 6:
//        Input: s = "a"
//        Output: "a"
        inputs.add(new Object[]{"a", Arrays.asList("a")});

//        Example 6:
//        Input: s = "eek"
//        Output: "ee"
        inputs.add(new Object[]{"eek", Arrays.asList("ee")});

//        Example 7:
//        Input: s = "ac"
//        Output: "a"
        inputs.add(new Object[]{"ac", Arrays.asList("a", "c")});

        return inputs.iterator();
    }

    @Test(dataProvider = "longestPalindromeData")
    public void testLongestPalindrome(String input, List<String> results) {
        LC5LongestPalindromicSubstring instance = new LC5LongestPalindromicSubstring();
//        assertEquals(result, instance.longestPalindromeApproach1(input));
        assertTrue(results.contains(instance.longestPalindromeApproach1(input)));
        assertTrue(results.contains(instance.longestPalindromeApproach3(input)));
    }

}
