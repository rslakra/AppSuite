package com.rslakra.core;

import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @created 8/25/21 5:47 PM
 */
public class UnsafeUtilsTest {

    @Test
    public void testIsUnsafeSupported() {
        Assert.assertFalse(UnsafeUtils.isUnsafeSupported());
    }
}
