package com.rslakra.core.text;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created Jul 22, 2022 20:47:14
 */
public class ByteIterableTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(ByteIterableTest.class);

    @Test
    public void testByteIterable() {
        String value = "Lakra";
        LOGGER.debug(value.toString());
        Iterator<Byte> itr = new ByteIterable(value).iterator();
        Assert.assertNotNull(itr);
        TextUtils.logIterator(itr);
    }

}
