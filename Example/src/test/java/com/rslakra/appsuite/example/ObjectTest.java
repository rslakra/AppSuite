package com.rslakra.appsuite.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 10/2/19 10:15 AM
 */
public class ObjectTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("+main({})", args);
        String string = "Rohtash>Singh>Lakra<";
        String validString = string.replace(">", " ");
        LOGGER.debug("replace: " + validString);

        validString = string.replaceAll("[><]", " ");
        LOGGER.debug("replaceAll: " + validString);

        ObjectTest str = new ObjectTest();
        string = "Hello";
        byte[] result = BeanUtils.toBytes(string);
        LOGGER.debug("string: " + string + ", result: " + result);

        //
        string = "Hello!";
        result = BeanUtils.toBytes(string);
        LOGGER.debug("string: " + string + ", result: " + result);

        //
        string = "HelloWorld";
        result = BeanUtils.toBytes(string);
        LOGGER.debug("string: " + string + ", result: " + result);

        final byte[] keyAndIvGenerationSalt = new byte[]{76, 97, 0, 107, 114, -127, 97, -27};
        LOGGER.debug("keyAndIvGenerationSalt:" + Arrays.toString(keyAndIvGenerationSalt));
        LOGGER.debug("String:" + new String(keyAndIvGenerationSalt));

        LOGGER.debug("-main()");
    }

}
