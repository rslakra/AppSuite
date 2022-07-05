package com.rslakra.java;

import com.rslakra.java.annotations.serialize.TestJsonSerializer;
import com.rslakra.jdk.enums.Units;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

/**
 * @Author Rohtash Lakra
 * @Since 2/25/20 5:20 PM
 */
public class EnumSetTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(EnumSetTest.class);

    @Test
    public void testEnumSet() {
        EnumSet even = EnumSet.of(Units.TWO, Units.FOUR, Units.SIX, Units.EIGHT);
        EnumSet evenEnumSet = EnumSet.of(Units.TWO, Units.FOUR, Units.SIX, Units.EIGHT);
        LOGGER.debug("even: " + even + ", hashCode: " + even.hashCode());
        LOGGER.debug("evenEnumSet: " + evenEnumSet + ", hashCode: " + evenEnumSet.hashCode());
        Assert.assertEquals(even, evenEnumSet);
    }
}
