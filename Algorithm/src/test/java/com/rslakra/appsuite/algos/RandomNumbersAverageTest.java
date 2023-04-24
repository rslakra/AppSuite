package com.rslakra.appsuite.algos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 12/23/19 4:10 PM
 */
public class RandomNumbersAverageTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(RandomNumbersAverageTest.class);

    @Test
    public void testAverage() {
        RandomNumbersAverage randomNumbersAverage = new RandomNumbersAverage();
        LOGGER.debug("randomNumbersAverage:{}", randomNumbersAverage);
        Assert.assertNotNull(randomNumbersAverage);
        Assert.assertEquals(3, randomNumbersAverage.average(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testRandomNumbersAverage() {
        RandomNumbersAverage randomNumbersAverage = new RandomNumbersAverage();
        LOGGER.debug("randomNumbersAverage:{}", randomNumbersAverage);
        Assert.assertNotNull(randomNumbersAverage);
    }
}
