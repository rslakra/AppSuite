package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/16/23 4:28 PM
 */
public class LC189RotateArrayTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> rotateData() {
        List<Object[]> inputs = new ArrayList<>();

//        Example 1:
//        Input: nums = [1,2,3,4,5,6,7], k = 3
//        Output: [5,6,7,1,2,3,4]
//        Explanation:
//        rotate 1 steps to the right: [7,1,2,3,4,5,6]
//        rotate 2 steps to the right: [6,7,1,2,3,4,5]
//        rotate 3 steps to the right: [5,6,7,1,2,3,4]
        inputs.add(new Object[]{new int[]{1, 2, 3, 4, 5, 6, 7}, 3, new int[]{5, 6, 7, 1, 2, 3, 4}});

//        Example 2:
//        Input: nums = [-1,-100,3,99], k = 2
//        Output: [3,99,-1,-100]
//        Explanation:
//        rotate 1 steps to the right: [99,-1,-100,3]
//        rotate 2 steps to the right: [3,99,-1,-100]
        inputs.add(new Object[]{new int[]{-1, -100, 3, 99}, 2, new int[]{3, 99, -1, -100}});

//        Example 3:
//        Input: nums = [], k = 1
//        Output: []
//        Explanation:
        inputs.add(new Object[]{new int[]{}, 1, new int[]{}});

        return inputs.iterator();
    }

    @Test(dataProvider = "rotateData")
    public void testRotate(int[] input, int k, int[] result) {
        LC189RotateArray instance = new LC189RotateArray();
        assertEquals(result, instance.rotate(input, k));
        assertEquals(result, instance.rotateOptimized(input, k));
    }

}
