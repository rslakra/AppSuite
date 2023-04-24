package com.rslakra.appsuite.algos.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 4/21/21 5:11 PM
 */
public class StringCodecTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(StringCodecTest.class);

    @Test
    public void testStringCodec() {
        StringCodec stringCodec = new StringCodec();
        List<String> listStrings = Arrays.asList("Rohtash", "Singh", "Lakra", "7#Rohtash5#Singh5#Lakra");
        LOGGER.debug("{}", listStrings);
        String encoded = stringCodec.encode(listStrings);
        LOGGER.debug(encoded);
        List<String> result = stringCodec.decode(encoded);
        LOGGER.debug("{}", result);
    }
}
