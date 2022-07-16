package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @Created Jun 18, 2022 17:24:38
 */
public class HashUtilsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(HashUtilsTest.class);

    @Test
    public void testHashCodePositive() {
        assertEquals(0, HashUtils.hashCodePositive(null));
        assertEquals(3392934, HashUtils.hashCodePositive("null"));
        assertEquals(113258, HashUtils.hashCodePositive("rsl"));
        assertEquals(110499, HashUtils.hashCodePositive("owl"));
        assertEquals(107379, HashUtils.hashCodePositive("low"));

        int hashCode = -2;
        LOGGER.debug("0x7fffffff:" + 0x7fffffff);
        LOGGER.debug("hashCode:" + HashUtils.hashCodePositive(hashCode));
        assertTrue(HashUtils.hashCodePositive(hashCode) > 0);

        String[] strings = {"Aa", "BB"};
        for (String str : strings) {
            LOGGER.debug("str:{}, str.hashCode:{}, hashCodePositive:{}", str, str.hashCode(),
                    HashUtils.hashCodePositive(str));
        }
    }

    /**
     * <pre>
     * item:foo, hashCode:1957502751
     * item:ofo, hashCode:1884122755
     * item:oof, hashCode:1134612201
     * </pre>
     */
    @org.junit.Test
    public void testHashCode() {
        List<String> listStrings = Arrays.asList("foo", "ofo", "oof");
        listStrings.forEach(item -> {
            long hashCode = HashUtils.hashCode(item);
            long hashCodePositive = HashUtils.hashCodePositive(item);
            LOGGER.debug("item:{}, hashCode:{}, hashCodePositive:{}", item, hashCode, hashCodePositive);
        });
    }

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
