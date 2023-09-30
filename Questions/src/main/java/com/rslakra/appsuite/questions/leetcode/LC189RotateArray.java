package com.rslakra.appsuite.questions.leetcode;

/**
 * @author Rohtash Lakra
 * @created 9/16/23 4:28 PM
 */
public class LC189RotateArray {

    /**
     * @param nums
     * @param k
     */
    public int[] rotate(int[] nums, int k) {
        if (nums != null && nums.length > 0 && k > 0) {
            for (int i = 0; i < k; i++) {
                int temp = nums[nums.length - 1];
                for (int right = nums.length - 1; right > 0; right--) {
                    nums[right] = nums[right - 1];
                }
                nums[0] = temp;
            }
        }

        return nums;
    }


    /**
     * @param nums
     * @param start
     * @param end
     */
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            // swap elements
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    public int[] rotateOptimized(int[] nums, int k) {
        // make sure k is within array bounds
        k %= nums.length;
        // reverse all elements
        reverse(nums, 0, nums.length - 1);
        // reverse first k elements
        reverse(nums, 0, k - 1);
        // reverse remaining elements
        reverse(nums, k, nums.length - 1);

        return nums;
    }
}
