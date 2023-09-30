package com.rslakra.appsuite.questions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 8/29/23 11:11 AM
 */
public class MoveAllZeroes2EndOfArray {


    private static final Logger LOGGER = LoggerFactory.getLogger(MoveAllZeroes2EndOfArray.class);

    /**
     * Example 1: Input: nums = [1, 2, 0, 4, 3, 0, 5, 0]
     * <p>
     * Output: nums = [1, 2, 4, 3, 5, 0, 0, 0]
     * <p>
     * Example 2: Input: nums = [1, 2, 0, 0, 0, 3, 6]
     * <p>
     * Output: nums = [1, 2, 3, 6, 0, 0, 0]
     *
     * @param values
     * @return
     */
    public int[] moveAllZeroes2EndOfArray(int[] values) {
        if (values != null) {
            int i = 0;
            for (int num : values) {
                if (num != 0) {
                    LOGGER.info("num:{}", num);
                    values[i] = num;
                    i++;
                }
            }

            while (i < values.length) {
                values[i] = 0;
                i++;
            }
        }

        return values;
    }

    /**
     * Example 1: Input: nums = [1, 2, 0, 4, 3, 0, 5, 0]
     * <p>
     * Output: nums = [1, 2, 4, 3, 5, 0, 0, 0]
     * <p>
     * Example 2: Input: nums = [1, 2, 0, 0, 0, 3, 6]
     * <p>
     * Output: nums = [1, 2, 3, 6, 0, 0, 0]
     *
     * @param values
     * @return
     */
    public int[] moveZeroes2(int[] values) {
        if (values != null) {
            int j = 0;
            for (int i = 0; i < values.length; i++) {
                if (values[i] != 0) {
                    if (i != j) {
                        int temp = values[i];
                        values[i] = values[j];
                        values[j] = temp;
                    }
                    j++;
                }
            }
        }

        return values;
    }


    /**
     * Example 1: Input: nums = [1, 2, 0, 4, 3, 0, 5, 0]
     * <p>
     * Output: nums = [1, 2, 4, 3, 5, 0, 0, 0]
     * <p>
     * Example 2: Input: nums = [1, 2, 0, 0, 0, 3, 6]
     * <p>
     * Output: nums = [1, 2, 3, 6, 0, 0, 0]
     *
     * @param values
     * @return
     */
    public int[] moveZeroes4(int[] values) {
        if (values != null) {
            int j = 0;
            for (int i = 0; i < values.length; i++) {
                if (values[i] == 0) {
                    j++;
                } else if (j > 0) {
                    values[i - j] = values[i];
                    values[i] = 0;
                }
            }
        }

        return values;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        MoveAllZeroes2EndOfArray theObject = new MoveAllZeroes2EndOfArray();
        int[][] inputs = {
            {1, 2, 0, 4, 3, 0, 5, 0},
            {1, 2, 0, 0, 0, 3, 6}
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] input = new int[inputs[i].length];
            System.arraycopy(inputs[i], 0, input, 0, input.length);
            int[] result = theObject.moveAllZeroes2EndOfArray(input);
            LOGGER.info("input:{}, result:{}", inputs[i], result);
        }

        System.out.println();
        for (int i = 0; i < inputs.length; i++) {
            int[] input = new int[inputs[i].length];
            System.arraycopy(inputs[i], 0, input, 0, input.length);
            int[] result = theObject.moveZeroes2(input);
            LOGGER.info("input:{}, result:{}", inputs[i], result);
        }

        System.out.println();
        for (int i = 0; i < inputs.length; i++) {
            int[] input = new int[inputs[i].length];
            System.arraycopy(inputs[i], 0, input, 0, input.length);
            int[] result = theObject.moveZeroes4(input);
            LOGGER.info("input:{}, result:{}", inputs[i], result);
        }
    }
}
