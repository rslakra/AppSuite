package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created Jun 18, 2022 17:24:38
 */
public class HashUtilsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(HashUtilsTest.class);

    @Test
    public void testSha256Hex() {
        List<String> listStrings = Arrays.asList("foo", "ofo", "oof");
        listStrings.forEach(item -> {
            String sha256Hex = HashUtils.sha256Hex(item);
            LOGGER.debug("item:" + item + ", sha256Hex:" + sha256Hex);
        });
    }

    @Test
    public void testLength() {
        List<String> listStrings = Arrays.asList("Rohtash", "Singh", "Lakra");
        listStrings.forEach(item -> {
            long length = HashUtils.length(item.getBytes());
            LOGGER.debug("item:" + item + ", length:" + length + ", item.length:" + item.length());
        });
    }
}
