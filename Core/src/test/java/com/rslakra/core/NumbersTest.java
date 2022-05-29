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
public class NumbersTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumbersTest.class);

    @Test
    public void testCountDigitsNaiveApproach1() {
        int digits = Numbers.countDigitsNaiveApproach1(1601);
        LOGGER.debug("digits: {}", digits);
        Assert.assertEquals(4, digits);
    }

    @Test
    public void testCountDigitsNaiveApproach2() {
        int digits = Numbers.countDigitsNaiveApproach2(1601);
        LOGGER.debug("digits: {}", digits);
        Assert.assertEquals(4, digits);
    }

    @Test
    public void testCountDigitsNaive() {
        int digits = Numbers.countDigitsNaive(1601);
        LOGGER.debug("digits: {}", digits);
        Assert.assertEquals(4, digits);
    }

    @Test
    public void testCountDecimalDigits() {
        LOGGER.debug("+testCountDecimalDigits()");
        //find digits.
        for (int i = 0; i <= 10; i++) {
            long number = (long) Math.pow(10, i);
            int digits = Numbers.countDecimalDigits(number);
            LOGGER.debug("{} has {} digits", number, digits);
            digits = Numbers.countDigitsNaiveApproach1(number);
            LOGGER.debug("{} has {} digits", number, digits);
            digits = Numbers.countDigitsNaiveApproach2(number);
            LOGGER.debug("{} has {} digits", number, digits);
            digits = Numbers.countDigitsNaive(number);
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
        LOGGER.debug("{} == 0 : {}", zero, Numbers.equalToZero(zero));
        Assert.assertTrue(Numbers.equalToZero(zero));
        LOGGER.debug("{} == 0 : {}", negative, Numbers.equalToZero(negative));
        Assert.assertFalse(Numbers.equalToZero(negative));
        LOGGER.debug("{} == 0 : {}", positive, Numbers.equalToZero(positive));
        Assert.assertFalse(Numbers.equalToZero(positive));
        LOGGER.info("\n");

        // !=
        LOGGER.debug("{} != 0 : {}", zero, Numbers.notEqualToZero(zero));
        Assert.assertFalse(Numbers.notEqualToZero(zero));
        LOGGER.debug("{} != 0 : {}", negative, Numbers.notEqualToZero(negative));
        Assert.assertTrue(Numbers.notEqualToZero(negative));
        LOGGER.debug("{} != 0 : {}", positive, Numbers.notEqualToZero(positive));
        Assert.assertTrue(Numbers.notEqualToZero(positive));
        LOGGER.info("\n");

        // >=
        LOGGER.debug("{} >= {} : {}", zero, negative, Numbers.greaterThanEqualToZero(zero, negative));
        Assert.assertTrue(Numbers.greaterThanEqualToZero(zero, negative));
        LOGGER.debug("{} >= {} : {}", zero, positive, Numbers.greaterThanEqualToZero(zero, positive));
        Assert.assertFalse(Numbers.greaterThanEqualToZero(zero, positive));
        LOGGER.debug("{} >= {} : {}", negative, positive, Numbers.greaterThanEqualToZero(negative, positive));
        Assert.assertFalse(Numbers.greaterThanEqualToZero(negative, positive));
        LOGGER.info("\n");

        // >=
        LOGGER.debug("{} >= 0 : {}", zero, Numbers.greaterThanEqualToZero(zero));
        Assert.assertTrue(Numbers.greaterThanEqualToZero(zero));
        LOGGER.debug("{} >= 0 : {}", negative, Numbers.greaterThanEqualToZero(negative));
        Assert.assertFalse(Numbers.greaterThanEqualToZero(negative));
        LOGGER.debug("{} >= 0 : {}", positive, Numbers.greaterThanEqualToZero(positive));
        Assert.assertTrue(Numbers.greaterThanEqualToZero(positive));
        LOGGER.info("\n");

        // >
        LOGGER.debug("{} > 0 : {}", zero, Numbers.greaterThanZero(zero));
        Assert.assertFalse(Numbers.greaterThanZero(zero));
        LOGGER.debug("{} > 0 : {}", negative, Numbers.greaterThanZero(negative));
        Assert.assertFalse(Numbers.greaterThanZero(negative));
        LOGGER.debug("{} > 0 : {}", positive, Numbers.greaterThanZero(positive));
        Assert.assertTrue(Numbers.greaterThanZero(positive));
        LOGGER.info("\n");

        // <=
        LOGGER.debug("{} <= {} : {}", zero, negative, Numbers.lessThanEqualTo(zero, negative));
        Assert.assertFalse(Numbers.lessThanEqualTo(zero, negative));
        LOGGER.debug("{} <= {} : {}", zero, positive, Numbers.lessThanEqualTo(zero, positive));
        Assert.assertTrue(Numbers.lessThanEqualTo(zero, positive));
        LOGGER.debug("{} <= {} : {}", negative, positive, Numbers.lessThanEqualTo(negative, positive));
        Assert.assertTrue(Numbers.lessThanEqualTo(negative, positive));
        LOGGER.info("\n");

        // <=
        LOGGER.debug("{} <= 0 : {}", zero, Numbers.lessThanEqualToZero(zero));
        Assert.assertTrue(Numbers.lessThanEqualToZero(zero));
        LOGGER.debug("{} <= 0 : {}", negative, Numbers.lessThanEqualToZero(negative));
        Assert.assertTrue(Numbers.lessThanEqualToZero(negative));
        LOGGER.debug("{} <= 0 : {}", positive, Numbers.lessThanEqualToZero(positive));
        Assert.assertFalse(Numbers.lessThanEqualToZero(positive));
        LOGGER.info("\n");

        // <
        LOGGER.debug("{} < 0 : {}", zero, Numbers.lessThanZero(zero));
        Assert.assertFalse(Numbers.lessThanZero(zero));
        LOGGER.debug("{} < 0 : {}", negative, Numbers.lessThanZero(negative));
        Assert.assertTrue(Numbers.lessThanZero(negative));
        LOGGER.debug("{} < 0 : {}", positive, Numbers.lessThanZero(positive));
        Assert.assertFalse(Numbers.lessThanZero(positive));
    }


    @Test
    public void testNumbers() {
        System.out.println();
        // zero
        assertNumbers(BigDecimal.ZERO, BigDecimal.valueOf(-10), BigDecimal.TEN);
        //decimal numbers
        assertNumbers(BigDecimal.valueOf(0.0), BigDecimal.valueOf(-0.04), BigDecimal.valueOf(0.04));
    }

    @Test
    public void testFirstNDigitNumber() {
        int digits = 1;
        long result = Numbers.firstNDigitNumber(digits);
        LOGGER.debug("First {} digit(s) number: ", digits, result);
        digits = 2;
        result = Numbers.firstNDigitNumber(digits);
        LOGGER.debug("First {} digit(s) number: ", digits, result);
        digits = 3;
        result = Numbers.firstNDigitNumber(digits);
        LOGGER.debug("First {} digit(s) number: ", digits, result);
    }

    @Test
    public void testListNaturalNumbers() {
        int count = 5;
        List<Integer> results = Numbers.listNaturalNumbers(count);
        LOGGER.debug("count:{}, listNaturalNumbers:{}", count, results);
        results = Numbers.listNaturalNumbersWithZero(count);
        LOGGER.debug("count:{}, listNaturalNumbersWithZero:{}", count, results);
        results = Numbers.listEvenNumbers(count);
        LOGGER.debug("count:{}, listEvenNumbers:{}", count, results);
    }

    @Test
    public void testIsVoidCharge() {
        BigDecimal amount = BigDecimal.ZERO;
        LOGGER.debug("{} == 0 : {}", amount, Numbers.isVoidCharge(amount));
        Assert.assertFalse(Numbers.isVoidCharge(amount));
        amount = BigDecimal.TEN;
        LOGGER.debug("{} == 0 : {}", amount, Numbers.isVoidCharge(amount));
        Assert.assertFalse(Numbers.isVoidCharge(amount));
        amount = BigDecimal.TEN.negate();
        LOGGER.debug("{} == 0 : {}", amount, Numbers.isVoidCharge(amount));
        Assert.assertTrue(Numbers.isVoidCharge(amount));
        LOGGER.info("\n");
    }

}
