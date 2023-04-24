package com.rslakra.appsuite.jdk8.lambda;

import java.util.stream.IntStream;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Jul 19, 2021 20:34:18
 */
public class ArrayLambda {

    /**
     * Returns the index of an element in an array if exists otherwis -1.
     *
     * @param nums
     * @param target
     * @return
     */
    public int findIndex(int[] nums, int target) {
        return (nums != null ? IntStream.range(0, nums.length).filter(i -> target == nums[i]).findFirst().orElse(-1) : -1);
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        ArrayLambda arrayLambda = new ArrayLambda();
        int[] nums = {9, 3, 4, 2, 1, 6, 8, 0, 7, 5};
        for (int index = 0; index <= 10; index++) {
            System.out.println("Index of [" + index + "] in array:" + arrayLambda.findIndex(nums, index));
        }
    }
}
