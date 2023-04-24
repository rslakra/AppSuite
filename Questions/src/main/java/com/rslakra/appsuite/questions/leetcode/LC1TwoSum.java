package com.rslakra.appsuite.questions.leetcode;

import java.util.HashMap;

/**
 * @author Rohtash Lakra
 * @created 5/1/21 8:14 AM
 */
public class LC1TwoSum {

    /**
     * https://leetcode.com/problems/two-sum/
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] indices = new int[]{-1, -1};
        if (nums != null && nums.length > 1) {
            HashMap<Integer, Integer> diffMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int diff = target - nums[i];
                if (diffMap.containsKey(diff)) {
                    indices[0] = diffMap.get(diff);
                    indices[1] = i;
                    break;
                } else {
                    diffMap.put(nums[i], i);
                }
            }
        }

        return indices;
    }

}
