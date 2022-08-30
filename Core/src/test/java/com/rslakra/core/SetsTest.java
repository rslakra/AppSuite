package com.rslakra.core;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;

/**
 * @author Rohtash Lakra
 * @created 3/19/20 1:55 PM
 */
public class SetsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(SetsTest.class);

    @Test
    public void testCreateSet() {
        //unique values
        Set<String> types = Sets.asSet("VISA", "MSTR", "AMEX", "DISC");
        Assert.assertEquals(4, types.size());
        types.forEach(item ->  LOGGER.debug(item));
         LOGGER.debug("\n");
        printSpliterator(types);

        //duplicate values
        types = Sets.asSet("VISA", "MSTR", "MSTR", "AMEX", "DISC");
        Assert.assertEquals(4, types.size());
        types.forEach(item ->  LOGGER.debug(item));
         LOGGER.debug("\n");
        printSpliterator(types);
    }

    /**
     * @param types
     */
    private void printSpliterator(final Set<String> types) {
        Objects.requireNonNull(types);
        Spliterator<String> spliterator = types.spliterator();
         LOGGER.debug(
            "characteristics: " + spliterator.characteristics() + ", estimateSize: " + spliterator.estimateSize());


    }
}
