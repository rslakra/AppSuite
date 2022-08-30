package com.rslakra.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rohtash Lakra
 * @created 3/25/21 6:12 PM
 */
public class RangeTest {

    @Test
    public void testRange() {
        Range range = Range.newBuilder().setFrom(1).setTo(5).build();
        Assert.assertNotNull(range);
        Assert.assertEquals(1, range.getFrom());
        Assert.assertEquals(5, range.getTo());
        Assert.assertEquals(false, range.isReverse());
    }

}
