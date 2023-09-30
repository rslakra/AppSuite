package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/19/23 3:43 PM
 */
public class LC347TopKFrequentElementsTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> topKFrequentData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: nums = [1,1,1,2,2,3], k = 2
//        Output: [1,2]
        inputs.add(new Object[]{new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2}});

//        Example 2:
//        Input: nums = [1], k = 1
//        Output: [1]
        inputs.add(new Object[]{new int[]{1}, 1, new int[]{1}});

//        Example 2:
//        Input: nums = [1, 2], k = 2
//        Output: [1, 2]
        inputs.add(new Object[]{new int[]{1, 2}, 2, new int[]{1, 2}});

        return inputs.iterator();
    }

    @Test(dataProvider = "topKFrequentData")
    public void testTopKFrequent(int[] input, int k, int[] result) {
        LC347TopKFrequentElements instance = new LC347TopKFrequentElements();
        assertArrayEquals(result, instance.topKFrequent(input, k));
    }
}
