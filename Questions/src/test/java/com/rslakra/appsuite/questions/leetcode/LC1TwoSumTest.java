package com.rslakra.appsuite.questions.leetcode;

import static org.testng.Assert.assertEquals;

import com.rslakra.appsuite.questions.leetcode.LC1TwoSum;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 5/1/21 8:37 AM
 */
public class LC1TwoSumTest {

    @DataProvider
    public Iterator<Object[]> twoSumData() {
        List<Object[]> inputs = new ArrayList<>();
        inputs.add(new Object[]{new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}});
        inputs.add(new Object[]{new int[]{3, 2, 4}, 6, new int[]{1, 2}});
        inputs.add(new Object[]{new int[]{3, 3}, 6, new int[]{0, 1}});
        inputs.add(new Object[]{new int[]{1}, 3, new int[]{-1, -1}});
        inputs.add(new Object[]{new int[]{}, 3, new int[]{-1, -1}});
        return inputs.iterator();
    }

    @Test(dataProvider = "twoSumData")
    public void testTwoSum(int[] nums, int target, int[] expectedResults) {
        int[] results = LC1TwoSum.twoSum(nums, target);
        assertEquals(expectedResults, results);
    }

}
