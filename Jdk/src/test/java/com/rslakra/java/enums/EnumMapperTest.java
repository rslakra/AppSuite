package com.rslakra.java.enums;

import com.rslakra.core.Sets;
import com.rslakra.jdk.enums.Color;
import com.rslakra.jdk.enums.EnumMapper;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 11/4/21 12:53 PM
 */
public class EnumMapperTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(EnumMapperTest.class);

    public static EnumMapper<Color> colorMapper() {
        return EnumMapper.newBuilder(Color.class)
            .map(Color.RED.name(), Color.RED)
            .map(Color.GREEN.name(), Color.GREEN)
            .map(Color.BLUE.name(), Color.BLUE)
            .build();
    }

    @Test
    public void testEnumMapper() {
        EnumMapper<Color> colorEnumMapper = colorMapper();
        Assert.assertNotNull(colorEnumMapper);
        Assert.assertEquals(3, colorEnumMapper.size());
        Assert.assertEquals(Color.RED, colorEnumMapper.toEnum(Color.RED.name()));
        Assert.assertEquals(Color.GREEN, colorEnumMapper.toEnum(Color.GREEN.name()));
        Assert.assertEquals(Color.BLUE, colorEnumMapper.toEnum(Color.BLUE.name()));
        Assert.assertEquals(Color.RED.name(), colorEnumMapper.from(Color.RED));
        Assert.assertEquals(Color.GREEN.name(), colorEnumMapper.from(Color.GREEN));
        Assert.assertEquals(Color.BLUE.name(), colorEnumMapper.from(Color.BLUE));
    }

    @Test
    public void testNulls() {
        EnumSet<Color> RGB = EnumSet.of(Color.RED, Color.GREEN, Color.BLUE);
        Assert.assertNotNull(RGB);
        Assert.assertTrue(RGB.contains(Color.GREEN));
        Assert.assertFalse(RGB.contains(null));
    }

    @Test
    public void testSting() {
        String url = "${serviceBaseUrl:https://api.devamatre.com/creditcard/authorize";
        if (url.startsWith("${serviceBaseUrl:")) {
            LOGGER.debug(url.substring("${serviceBaseUrl:".length()));
        }

        Sets.asSet(Color.BLUE.name(), Color.RED.name());

    }

}
