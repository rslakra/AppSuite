package com.rslakra.java;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 6/30/22 11:33 AM
 */
public class ArraysTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(ArraysTest.class);

    @Test
    public void testEquals() {
        String[] names = StringUtils.split(null, ",");
        LOGGER.debug("names:{}", Arrays.toString(names));
        String[] inputs = null;
        boolean result = inputs != null && Arrays.asList(inputs).contains(null);
        LOGGER.debug("result:{}", result);
        Assert.assertFalse(result);

        result = Arrays.asList(1, 2, 3).contains(2);
        LOGGER.debug("result:{}", result);
        Assert.assertTrue(result);

        result = Arrays.asList(1, 2, 3).contains(5);
        LOGGER.debug("result:{}", result);
        Assert.assertFalse(result);
    }

}
