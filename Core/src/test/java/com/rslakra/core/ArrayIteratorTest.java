package com.rslakra.core;

import com.rslakra.core.text.TextUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 7/15/22 10:11 AM
 */
public class ArrayIteratorTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(ArrayIteratorTest.class);

    @Test
    public void testArrayIterator() {
        String[] values = {"Rohtash", "Lakra"};
        Iterator<String> itr = new ArrayIterator(values);
        Assert.assertNotNull(itr);
        TextUtils.logIterator(itr);

        // content-types
        values = new String[]{"application/json", "application/javascript", "text/javascript"};
        itr = new ArrayIterator(values);
        TextUtils.logIterator(itr);

        // content-types
        Integer[] counts = new Integer[]{1, 2, 3, 4, 5};
        itr = new ArrayIterator(counts);
        TextUtils.logIterator(itr);
    }


    @Test
    public void testArrayIteratorWithArguments() {
        LOGGER.debug("\n");
        // content-types
        Integer[] values = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Iterator<Integer> itr = new ArrayIterator(values, 2, 7);
        TextUtils.logIterator(itr);
    }

}
