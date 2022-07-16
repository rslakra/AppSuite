package com.rslakra.core;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/25/21 7:21 PM
 */
public class MathUtilsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(MathUtilsTest.class);

    /**
     * Test Right Shift.
     */
    @Test
    public void testRightShift() {
        int number = 12;
        int shift = 2;
        int rightShift = number >> shift;
        LOGGER.debug("number:{}, shift:{}, rightShift:{}", number, shift, rightShift);
        Assert.assertEquals(rightShift, MathUtils.rightShift(number, shift));
    }

    /**
     * Test Left Shift.
     */
    @Test
    public void testLeftShift() {
        int number = 12;
        int shift = 2;
        int leftShift = number << shift;
        LOGGER.debug("number:{}, shift:{}, leftShift:{}", number, shift, leftShift);
        Assert.assertEquals(leftShift, MathUtils.leftShift(number, shift));
    }

    @Test
    public void testCountDigitsNaiveApproach1() {
        int digits = MathUtils.countDigitsNaiveApproach1(1601);
        LOGGER.debug("digits: {}", digits);
        Assert.assertEquals(4, digits);
    }

    @Test
    public void testCountDigitsNaiveApproach2() {
        int digits = MathUtils.countDigitsNaiveApproach2(1601);
        LOGGER.debug("digits: {}", digits);
        Assert.assertEquals(4, digits);
    }

    @Test
    public void testCountDigitsNaive() {
        int digits = MathUtils.countDigitsNaive(1601);
        LOGGER.debug("digits: {}", digits);
        Assert.assertEquals(4, digits);
    }

    @Test
    public void testCountDecimalDigits() {
        LOGGER.debug("+testCountDecimalDigits()");
        //find digits.
        for (int i = 0; i <= 10; i++) {
            long number = (long) Math.pow(10, i);
            int digits = MathUtils.countDecimalDigits(number);
            LOGGER.debug("{} has {} digits", number, digits);
            digits = MathUtils.countDigitsNaiveApproach1(number);
            LOGGER.debug("{} has {} digits", number, digits);
            digits = MathUtils.countDigitsNaiveApproach2(number);
            LOGGER.debug("{} has {} digits", number, digits);
            digits = MathUtils.countDigitsNaive(number);
            LOGGER.debug("{} has {} digits", number, digits);
            LOGGER.debug("\n");
        }
        LOGGER.debug("-testCountDecimalDigits()");
    }

    private void printPartitions(int size) {
        for (int i = 1; i <= 10; i++) {
            LOGGER.debug("i:{}, partition: {}", i, Integer.toString((i % size)));
        }
    }

    @Test
    public void testPartition() {
        printPartitions(10);
    }

    /**
     * @param zero
     * @param negative
     * @param positive
     */
    private void assertNumbers(final BigDecimal zero, final BigDecimal negative, final BigDecimal positive) {
        // ==
        LOGGER.debug("{} == 0 : {}", zero, MathUtils.equalToZero(zero));
        Assert.assertTrue(MathUtils.equalToZero(zero));
        LOGGER.debug("{} == 0 : {}", negative, MathUtils.equalToZero(negative));
        Assert.assertFalse(MathUtils.equalToZero(negative));
        LOGGER.debug("{} == 0 : {}", positive, MathUtils.equalToZero(positive));
        Assert.assertFalse(MathUtils.equalToZero(positive));
        LOGGER.info("\n");

        // !=
        LOGGER.debug("{} != 0 : {}", zero, MathUtils.notEqualToZero(zero));
        Assert.assertFalse(MathUtils.notEqualToZero(zero));
        LOGGER.debug("{} != 0 : {}", negative, MathUtils.notEqualToZero(negative));
        Assert.assertTrue(MathUtils.notEqualToZero(negative));
        LOGGER.debug("{} != 0 : {}", positive, MathUtils.notEqualToZero(positive));
        Assert.assertTrue(MathUtils.notEqualToZero(positive));
        LOGGER.info("\n");

        // >=
        LOGGER.debug("{} >= {} : {}", zero, negative, MathUtils.greaterThanEqualToZero(zero, negative));
        Assert.assertTrue(MathUtils.greaterThanEqualToZero(zero, negative));
        LOGGER.debug("{} >= {} : {}", zero, positive, MathUtils.greaterThanEqualToZero(zero, positive));
        Assert.assertFalse(MathUtils.greaterThanEqualToZero(zero, positive));
        LOGGER.debug("{} >= {} : {}", negative, positive, MathUtils.greaterThanEqualToZero(negative, positive));
        Assert.assertFalse(MathUtils.greaterThanEqualToZero(negative, positive));
        LOGGER.info("\n");

        // >=
        LOGGER.debug("{} >= 0 : {}", zero, MathUtils.greaterThanEqualToZero(zero));
        Assert.assertTrue(MathUtils.greaterThanEqualToZero(zero));
        LOGGER.debug("{} >= 0 : {}", negative, MathUtils.greaterThanEqualToZero(negative));
        Assert.assertFalse(MathUtils.greaterThanEqualToZero(negative));
        LOGGER.debug("{} >= 0 : {}", positive, MathUtils.greaterThanEqualToZero(positive));
        Assert.assertTrue(MathUtils.greaterThanEqualToZero(positive));
        LOGGER.info("\n");

        // >
        LOGGER.debug("{} > 0 : {}", zero, MathUtils.greaterThanZero(zero));
        Assert.assertFalse(MathUtils.greaterThanZero(zero));
        LOGGER.debug("{} > 0 : {}", negative, MathUtils.greaterThanZero(negative));
        Assert.assertFalse(MathUtils.greaterThanZero(negative));
        LOGGER.debug("{} > 0 : {}", positive, MathUtils.greaterThanZero(positive));
        Assert.assertTrue(MathUtils.greaterThanZero(positive));
        LOGGER.info("\n");

        // <=
        LOGGER.debug("{} <= {} : {}", zero, negative, MathUtils.lessThanEqualTo(zero, negative));
        Assert.assertFalse(MathUtils.lessThanEqualTo(zero, negative));
        LOGGER.debug("{} <= {} : {}", zero, positive, MathUtils.lessThanEqualTo(zero, positive));
        Assert.assertTrue(MathUtils.lessThanEqualTo(zero, positive));
        LOGGER.debug("{} <= {} : {}", negative, positive, MathUtils.lessThanEqualTo(negative, positive));
        Assert.assertTrue(MathUtils.lessThanEqualTo(negative, positive));
        LOGGER.info("\n");

        // <=
        LOGGER.debug("{} <= 0 : {}", zero, MathUtils.lessThanEqualToZero(zero));
        Assert.assertTrue(MathUtils.lessThanEqualToZero(zero));
        LOGGER.debug("{} <= 0 : {}", negative, MathUtils.lessThanEqualToZero(negative));
        Assert.assertTrue(MathUtils.lessThanEqualToZero(negative));
        LOGGER.debug("{} <= 0 : {}", positive, MathUtils.lessThanEqualToZero(positive));
        Assert.assertFalse(MathUtils.lessThanEqualToZero(positive));
        LOGGER.info("\n");

        // <
        LOGGER.debug("{} < 0 : {}", zero, MathUtils.lessThanZero(zero));
        Assert.assertFalse(MathUtils.lessThanZero(zero));
        LOGGER.debug("{} < 0 : {}", negative, MathUtils.lessThanZero(negative));
        Assert.assertTrue(MathUtils.lessThanZero(negative));
        LOGGER.debug("{} < 0 : {}", positive, MathUtils.lessThanZero(positive));
        Assert.assertFalse(MathUtils.lessThanZero(positive));
    }


    @Test
    public void testNumbers() {
        LOGGER.debug("\n");
        // zero
        assertNumbers(BigDecimal.ZERO, BigDecimal.valueOf(-10), BigDecimal.TEN);
        //decimal numbers
        assertNumbers(BigDecimal.valueOf(0.0), BigDecimal.valueOf(-0.04), BigDecimal.valueOf(0.04));
    }

    @Test
    public void testFirstNDigitNumber() {
        int digits = 1;
        long result = MathUtils.firstNDigitNumber(digits);
        LOGGER.debug("First {} digit(s) number: ", digits, result);
        digits = 2;
        result = MathUtils.firstNDigitNumber(digits);
        LOGGER.debug("First {} digit(s) number: ", digits, result);
        digits = 3;
        result = MathUtils.firstNDigitNumber(digits);
        LOGGER.debug("First {} digit(s) number: ", digits, result);
    }

    @Test
    public void testListNaturalNumbers() {
        int count = 5;
        List<Integer> results = MathUtils.listNaturalNumbers(count);
        LOGGER.debug("count:{}, listNaturalNumbers:{}", count, results);
        results = MathUtils.listNaturalNumbersWithZero(count);
        LOGGER.debug("count:{}, listNaturalNumbersWithZero:{}", count, results);
        results = MathUtils.listEvenNumbers(count);
        LOGGER.debug("count:{}, listEvenNumbers:{}", count, results);
    }

    @Test
    public void testIsVoidCharge() {
        BigDecimal amount = BigDecimal.ZERO;
        LOGGER.debug("{} == 0 : {}", amount, MathUtils.isVoidCharge(amount));
        Assert.assertFalse(MathUtils.isVoidCharge(amount));
        amount = BigDecimal.TEN;
        LOGGER.debug("{} == 0 : {}", amount, MathUtils.isVoidCharge(amount));
        Assert.assertFalse(MathUtils.isVoidCharge(amount));
        amount = BigDecimal.TEN.negate();
        LOGGER.debug("{} == 0 : {}", amount, MathUtils.isVoidCharge(amount));
        Assert.assertTrue(MathUtils.isVoidCharge(amount));
        LOGGER.info("\n");
    }

}
