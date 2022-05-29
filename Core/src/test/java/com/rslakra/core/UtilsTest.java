package com.rslakra.core;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 3:10 PM
 */
public class UtilsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsTest.class);

    @Test
    public void testIsZero() {
        assertEquals(true, Utils.INSTANCE.isZero(BigDecimal.ZERO));
        assertEquals(false, Utils.INSTANCE.isZero(BigDecimal.TEN));
        assertEquals(false, Utils.INSTANCE.isZero(BigDecimal.TEN.negate()));
    }


    @Test
    public void testIsPositive() {
        assertEquals(true, Utils.INSTANCE.isPositive(BigDecimal.TEN));
        assertEquals(false, Utils.INSTANCE.isPositive(BigDecimal.TEN.negate()));
        assertEquals(false, Utils.INSTANCE.isPositive(BigDecimal.ZERO));
    }

    @Test
    public void testIsNegative() {
        assertEquals(true, Utils.INSTANCE.isNegative(BigDecimal.TEN.negate()));
        assertEquals(false, Utils.INSTANCE.isNegative(BigDecimal.TEN));
        assertEquals(false, Utils.INSTANCE.isNegative(BigDecimal.ZERO));
    }

    @Test
    public void testHashCode() {
        int hashCode = -2;
        System.out.println("0x7fffffff:" + 0x7fffffff);
        System.out.println("hashCode:" + Utils.hashCode(hashCode));
        assertTrue(Utils.hashCode(hashCode) > 0);

        String[] strings = {"Aa", "BB"};
        for (String str : strings) {
            System.out.println(str + "=" + Utils.hashCode(str));
            System.out.println(str + "=" + str.hashCode());
        }
    }


    @Test
    public void testPartitionBySize() {
        // null values
        List<Integer> values = null;
        int size = 4;
        List<List<Integer>> intPartitions = Utils.partitionBySize(values, size);
        assertNotNull(intPartitions);
        assertEquals(intPartitions.size(), 0);

        values = Arrays.asList(1, 2, 3, 4);
        intPartitions = Utils.partitionBySize(values, size);
        assertNotNull(intPartitions);
        assertEquals(intPartitions.size(), 1);

        values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        intPartitions = Utils.partitionBySize(values, size);
        assertNotNull(intPartitions);
        assertEquals(intPartitions.size(), (values.size() / size) + 1);
    }

    @Test
    public void testLogCallerClassNameAndMethodName() {
        Utils.logCallerClassNameAndMethodName();
        assertTrue(true);
    }

}
