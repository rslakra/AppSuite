package com.rslakra.java.lang;

import com.rslakra.appsuite.core.HashUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 5/4/22 4:28 PM
 */
public class HashTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(HashTest.class);

    /**
     * <pre>
     * item:foo, hashCode:1957502751
     * item:ofo, hashCode:1884122755
     * item:oof, hashCode:1134612201
     * </pre>
     */
    @Test
    public void testHashCode() {
        List<String> listStrings = Arrays.asList("foo", "ofo", "oof");
        listStrings.forEach(item -> {
            long hashCode = Objects.hashCode(item);
            LOGGER.debug("item:" + item + ", hashCode:" + hashCode);
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
