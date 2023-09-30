package com.rslakra.appsuite.questions.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element
 * always exists in the array.
 * <p>
 * <p>
 * Example 1:
 * <pre>
 * Input: nums = [3,2,3]
 * Output: 3
 * </pre>
 * <p>
 * Example 2:
 * <pre>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 9/16/23 4:03 PM
 */
public class LC169MajorityElement {

    /**
     * Approach 1
     * <pre>
     *  1. Sort an array
     *  2. return the middle element.
     * </pre>
     *
     * <pre>
     *  Time Complexity: O(Log N)
     *  Space Complexity: O(1)
     * </pre>
     *
     * @param nums
     * @return
     */
    public int majorityElementApproach1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    /**
     * Approach 2
     * <pre>
     *  1. Use HashMap to keep the count of elements
     *  2. return the middle element.
     * </pre>
     * <p>
     *
     * <pre>
     *  Time Complexity: O(N)
     *  Space Complexity: O(N)
     * </pre>
     *
     * @param nums
     * @return
     */
    public int majorityElementApproach2(int[] nums) {
        if (nums != null && nums.length > 0) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            int majority = nums.length / 2;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > majority) {
                    return entry.getKey();
                }
            }
        }

        return 0;
    }


    /**
     * Approach 3
     * <pre>
     *  1. Have a count and majority number
     *  2. Iterate each element,if count = 0, then assume num as majority. if num equals majority, increase count otherwise decrease
     *  3. return majority
     * </pre>
     *
     * <pre>
     *  Time Complexity: O(N)
     *  Space Complexity: O(1)
     * </pre>     *
     *
     * @param nums
     * @return
     */
    public int majorityElementApproach3(int[] nums) {
        int count = 0;
        int majority = 0;
        if (nums != null && nums.length > 0) {
            for (int num : nums) {
                if (count == 0) {
                    majority = num;
                }

                if (majority == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        return majority;
    }
}
