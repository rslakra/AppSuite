package com.rslakra.appsuite.core.enums;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 1/7/22 11:21 AM
 */
public class TestingCycleTest {


    // LOGGER
    private final Logger LOGGER = LoggerFactory.getLogger(TestingCycleTest.class);

    @Test
    public void testTestingCycle() {
        TestingCycle testingCycle = TestingCycle.ofString(null);
        LOGGER.debug("testingCycle:{}", testingCycle);
        Assert.assertNull(testingCycle);

        testingCycle = TestingCycle.ofString(TestingCycle.BLACKBOX.name());
        LOGGER.debug("testingCycle:{}", testingCycle);
        Assert.assertNotNull(testingCycle);
        Assert.assertEquals(TestingCycle.BLACKBOX, testingCycle);

        testingCycle = TestingCycle.ofString("BLACKBOX");
        LOGGER.debug("testingCycle:{}", testingCycle);
        Assert.assertNotNull(testingCycle);
        Assert.assertEquals(TestingCycle.BLACKBOX, testingCycle);

        testingCycle = TestingCycle.ofString("BlackBox");
        LOGGER.debug("testingCycle:{}", testingCycle);
        Assert.assertNotNull(testingCycle);
        Assert.assertEquals(TestingCycle.BLACKBOX, testingCycle);

        testingCycle = TestingCycle.ofString("Blackbox");
        LOGGER.debug("testingCycle:{}", testingCycle);
        Assert.assertNotNull(testingCycle);
        Assert.assertEquals(TestingCycle.BLACKBOX, testingCycle);

        testingCycle = TestingCycle.ofString("white");
        LOGGER.debug("testingCycle:{}", testingCycle);
        Assert.assertNull(testingCycle);
    }

}
