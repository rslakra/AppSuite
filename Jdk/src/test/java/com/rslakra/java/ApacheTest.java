package com.rslakra.java;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 6/30/22 6:02 PM
 */
public class ApacheTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(ApacheTest.class);

    @Test
    public void testStringUtils_isBlank() {
        String input = null;
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isBlank(input));

        input = "";
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isBlank(input));

        input = "    token";
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isBlank(input));

        input = "lakra";
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isBlank(input));
    }

    @Test
    public void testStringUtils_isNotBlank() {
        String input = null;
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isNotBlank(input));

        input = "";
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isNotBlank(input));

        input = "Bearer token";
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isNotBlank(input));

        input = "lakra";
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isNotBlank(input));
    }


    @Test
    public void testStringUtils_isEmpty() {
        String input = null;
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isEmpty(input));

        input = "";
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isEmpty(input));

        input = "Bearer token";
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isEmpty(input));

        input = "lakra";
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isEmpty(input));
    }

    @Test
    public void testStringUtils_isNotEmpty() {
        String input = null;
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isNotEmpty(input));

        input = "";
        LOGGER.debug("input:{}", input);
        Assert.assertFalse(StringUtils.isNotEmpty(input));

        input = "Bearer token";
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isNotEmpty(input));

        input = "lakra";
        LOGGER.debug("input:{}", input);
        Assert.assertTrue(StringUtils.isNotEmpty(input));
    }


}
