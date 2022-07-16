package com.rslakra.core;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 7/15/22 10:57 AM
 */
public class LineIterableTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(LineIterableTest.class);

    @Test
    public void testArrayIterator() {
        String value = "Lakra";
        LOGGER.debug(value.toString());
        Iterator<Character> itr = new LineIterable(value).iterator();
        Assert.assertNotNull(itr);
        TextUtils.logIterator(itr);
    }

}
