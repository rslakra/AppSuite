package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rohtash Lakra
 * @since 12/13/19 4:25 PM
 */
public class Bits {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bits.class);

    /**
     * Returns the <code>number</code> after right shifting the <code>number</code> to the given <code>rightShift</code>
     * values.
     *
     * @param number
     * @param rightShift
     * @return
     */
    public static final int rightShift(int number, int rightShift) {
        return (number / (int) Math.pow(2, rightShift));
    }

    /**
     * Returns the <code>number</code> after right shifting the <code>number</code> to the given <code>leftShift</code>
     * values.
     *
     * @param number
     * @param leftShift
     * @return
     */
    public static final int leftShift(int number, int leftShift) {
        return (number * (int) Math.pow(2, leftShift));
    }

}
