package com.rslakra.java;

import com.google.common.primitives.Longs;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rohtash Lakra
 * @created 9/29/20 11:53 AM
 */
public class SortsTest {

    @Test
    public void testEquals() {
        Long first = Longs.tryParse("first-1969702");
        Assert.assertNull(first);
        Long second = Long.valueOf("1938167");
        Assert.assertNotNull(second);
        Assert.assertFalse(second.equals(first));
    }

}
