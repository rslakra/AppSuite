package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/4/23 2:12 PM
 */
public class LC4MedianOfTwoSortedArraysTest {

    @DataProvider
    public Iterator<Object[]> medianOfTwoSortedArraysData() {
        List<Object[]> inputs = new ArrayList<>();
//        Example 1:
//        Input: nums1 = [1,3], nums2 = [2]
//        Output: 2.00000
//        Explanation: merged array = [1,2,3] and median is 2.
        inputs.add(new Object[]{new int[]{1, 3}, new int[]{2}, 2.0d});

//        Example 2:
//        Input: nums1 = [1,2], nums2 = [3,4]
//        Output: 2.50000
//        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
        inputs.add(new Object[]{new int[]{1, 2}, new int[]{3, 4}, 2.5d});

//        Example 3:
//        Input: nums1 = [1,3,6], nums2 = [2,4,6,8]
//        Output: 2.00000
//        Explanation: merged array = [1,2,3,4,6,6,8] and median is (4 / 2) = 2.
        inputs.add(new Object[]{new int[]{1, 3, 6}, new int[]{2, 4, 6, 8}, 4.0d});

//        Example 4:
//        Input: nums1 = [1,3,6], nums2 = [2,4,8]
//        Output: 3.50000
//        Explanation: merged array = [1,2,3,4,6,8] and median is (3 + 4) / 2 = 3.5.
        inputs.add(new Object[]{new int[]{1, 3, 6}, new int[]{2, 4, 8}, 3.5d});

//        Example 5:
//        Input: nums1 = [], nums2 = [2, 3]
//        Output: 2.50000
//        Explanation: merged array = [2, 3] and median is (2 + 3) / 2 = 2.5.
        inputs.add(new Object[]{new int[]{}, new int[]{2, 3}, 2.5d});

        return inputs.iterator();
    }

    @Test(dataProvider = "medianOfTwoSortedArraysData")
    public void testMedianOfTwoSortedArrays(int[] arr1, int[] arr2, Double result) {
        LC4MedianOfTwoSortedArrays object = new LC4MedianOfTwoSortedArrays();
        assertEquals(result, object.findMedianSortedArrays(arr1, arr2));
    }
}