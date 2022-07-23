package com.rslakra.core.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created Jul 22, 2022 21:20:40
 */
public class TextUtilsTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(TextUtilsTest.class);

    @Test
    public void testTrims() {
        String value = "Rohtash    Singh    Lakra  ";
        String result = TextUtils.trims(value);
        LOGGER.debug("result:{}", result);
        assertNotNull(result);
        assertEquals(result, "Rohtash Singh Lakra");
    }
}
