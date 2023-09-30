package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/19/23 4:47 PM
 */
public class LC438FindAllAnagramsTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> findAnagramsData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: s = "cbaebabacd", p = "abc"
//        Output: [0,6]
//        Explanation:
//        The substring with start index = 0 is "cba", which is an anagram of "abc".
//        The substring with start index = 6 is "bac", which is an anagram of "abc".
        inputs.add(new Object[]{"cbaebabacd", "abc", Arrays.asList(0, 6)});

//        Example 2:
//        Input: s = "abab", p = "ab"
//        Output: [0,1,2]
//        Explanation:
//        The substring with start index = 0 is "ab", which is an anagram of "ab".
//        The substring with start index = 1 is "ba", which is an anagram of "ab".
//        The substring with start index = 2 is "ab", which is an anagram of "ab".
        inputs.add(new Object[]{"abab", "ab", Arrays.asList(0, 1, 2)});

//        Example 3:
//        Input: s = "cbaebbacd", p = "abc"
//        Output: [0,6]
//        Explanation:
//        The substring with start index = 0 is "cba", which is an anagram of "abc".
//        The substring with start index = 5 is "bac", which is an anagram of "abc".
        inputs.add(new Object[]{"cbaebbacd", "abc", Arrays.asList(0, 5)});

        return inputs.iterator();
    }

    @Test(dataProvider = "findAnagramsData")
    public void testFindAnagrams(String input, String sub, List<Integer> result) {
        LC438FindAllAnagrams instance = new LC438FindAllAnagrams();
        assertEquals(result, instance.findAnagrams(input, sub));
    }
}
