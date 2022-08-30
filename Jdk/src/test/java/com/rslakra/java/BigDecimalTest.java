package com.rslakra.java;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 10/13/21 1:12 PM
 */
public class BigDecimalTest {

    @Test
    public void testEquals() {
        BigDecimal left = BigDecimal.valueOf(100.456);
        BigDecimal right = BigDecimal.valueOf(100.4560);
        Assert.assertEquals(left, right);
        Assert.assertTrue(Objects.equals(left, right));
        Assert.assertTrue(Objects.equals(BigDecimal.valueOf(100.456), BigDecimal.valueOf(100.4560)));

        BigDecimal leftString = new BigDecimal("100.456");
        BigDecimal rightString = new BigDecimal("100.4560");
        Assert.assertNotEquals(leftString, rightString);
        Assert.assertFalse(Objects.equals(leftString, rightString));
    }

    public void testNotEqualToZero() {

    }

}
