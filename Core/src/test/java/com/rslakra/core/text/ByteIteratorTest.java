package com.rslakra.core.text;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * @author Rohtash Lakra
 * @version 1.0.0
 * @Created Jul 22, 2022 20:49:42
 */
public class ByteIteratorTest {
    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(ByteIteratorTest.class);

    @Test
    public void testByteIterator() {
        String value = "Lakra";
        LOGGER.debug(value.toString());
        Iterator<Byte> itr = new ByteIterator(value);
        Assert.assertNotNull(itr);
        TextUtils.logIterator(itr);
    }
}
