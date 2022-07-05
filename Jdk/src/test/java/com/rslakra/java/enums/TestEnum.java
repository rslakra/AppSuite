package com.rslakra.java.enums;

import com.rslakra.jdk.enums.Color;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rohtash Lakra
 * @created 4/23/20 7:03 PM
 */
public class TestEnum {

    @Test
    public void testEnumToString() {
        Assert.assertEquals(Color.RED.toString(), "Red");
        Assert.assertEquals(Color.GREEN.toString(), "Green");
        Assert.assertEquals(Color.YELLOW.toString(), "Yellow");
        Color color = null;
        Assert.assertTrue(Color.YELLOW != color);
    }

    @Test
    public void testEnumName() {
//        Assert.assertEquals("Green", Color.GREEN.name());
        Assert.assertNotNull(Color.RED.name(), "Red");
        Assert.assertNotNull(Color.GREEN.name(), "Green");
        Assert.assertNotNull(Color.YELLOW.name(), "Yellow");
        Color color = Color.GREEN;
        Assert.assertFalse(Color.GREEN != color);
    }
}
