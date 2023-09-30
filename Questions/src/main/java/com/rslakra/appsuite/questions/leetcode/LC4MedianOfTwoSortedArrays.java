package com.rslakra.appsuite.questions.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 9/4/23 2:12 PM
 */
public class LC4MedianOfTwoSortedArrays {

    private static final Logger LOGGER = LoggerFactory.getLogger(LC4MedianOfTwoSortedArrays.class);

    /**
     * Time Complexity: O(N)
     *
     * @param left
     * @param right
     * @return
     */
    private int[] mergeSortedArrays(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (left != null && right != null && i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        for (; left != null && i < left.length; i++) {
            result[k++] = left[i];
        }

        for (; right != null && j < right.length; j++) {
            result[k++] = right[j];
        }

        return result;
    }

    /**
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int[] merged = new int[nums1.length + nums2.length];
//        System.arraycopy(nums1, 0, merged, 0, nums1.length);
//        System.arraycopy(nums2, 0, merged, nums1.length, nums2.length);
//        Arrays.sort(merged); //O(n log n)
        int[] merged = mergeSortedArrays(nums1, nums2); // O(n)
        LOGGER.debug("merged:{}", Arrays.toString(merged));
        double maiden = 0d;
        int index = merged.length / 2;
        if (merged.length % 2 == 0) { // even index
            maiden = (merged[index - 1] + merged[index]) / 2.0;
        } else { // odd index
            maiden = merged[index];
        }

        LOGGER.debug("Median of arr1:{} and arr2:{} = {}", Arrays.toString(nums1), Arrays.toString(nums2), maiden);
        return maiden;
    }
}
