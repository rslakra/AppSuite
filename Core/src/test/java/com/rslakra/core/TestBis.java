package com.rslakra.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author Rohtash Lakra
 * @since 12/23/19 3:53 PM
 */
public class TestBis {

    /**
     * Test Right Shift.
     */
    @Test
    public void testRightShift() {
        int number = 12;
        int shift = 2;
        int rightShift = number >> shift;
        System.out.println("number: " + number + ", shift: " + shift + ", rightShift: " + rightShift);
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
        System.out.println("number: " + number + ", shift: " + shift + ", leftShift: " + leftShift);
        Assert.assertEquals(leftShift, Bits.leftShift(number, shift));
    }
}
