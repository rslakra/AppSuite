package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/16/23 4:03 PM
 */
public class LC169MajorityElementTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> majorityElementData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: nums = [3,2,3]
//        Output: 3
        inputs.add(new Object[]{new int[]{3, 2, 3}, 3});

//        Example 2:
//        Input: nums = [2,2,1,1,1,2,2]
//        Output: 2
        inputs.add(new Object[]{new int[]{2, 2, 1, 1, 1, 2, 2}, 2});

        inputs.add(new Object[]{new int[]{}, 0});
        return inputs.iterator();
    }

    @Test(dataProvider = "majorityElementData")
    public void testMajorityElement(int[] input, int result) {
        LC169MajorityElement instance = new LC169MajorityElement();
        assertEquals(result, instance.majorityElementApproach1(input));
        assertEquals(result, instance.majorityElementApproach2(input));
        assertEquals(result, instance.majorityElementApproach3(input));
    }

}
