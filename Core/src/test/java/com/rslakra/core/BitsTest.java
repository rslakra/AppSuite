package com.rslakra.core;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rohtash Lakra
 * @since 12/23/19 3:53 PM
 */
public class BitsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(BitsTest.class);

    /**
     * Test Right Shift.
     */
    @Test
    public void testRightShift() {
        int number = 12;
        int shift = 2;
        int rightShift = number >> shift;
         LOGGER.debug("number: " + number + ", shift: " + shift + ", rightShift: " + rightShift);
        Assert.assertEquals(rightShift, Bits.rightShift(number, shift));
    }

    /**
     *
     */
    @Test
    public void testLeftShift() {
        int number = 12;
        int shift = 2;
        int leftShift = number << shift;
         LOGGER.debug("number: " + number + ", shift: " + shift + ", leftShift: " + leftShift);
        Assert.assertEquals(leftShift, Bits.leftShift(number, shift));
    }
}
