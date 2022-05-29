package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 10/2/19 10:15 AM
 */
public class TestObjects {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestObjects.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("+main({})", args);
        String string = "Rohtash>Singh>Lakra<";
        String validString = string.replace(">", " ");
        System.out.println("replace: " + validString);

        validString = string.replaceAll("[><]", " ");
        System.out.println("replaceAll: " + validString);

        TestObjects str = new TestObjects();
        string = "Hello";
        byte[] result = Utils.INSTANCE.toBytes(string);
        System.out.println("string: " + string + ", result: " + result);
        System.out.println();

        //
        string = "Hello!";
        result = Utils.INSTANCE.toBytes(string);
        System.out.println("string: " + string + ", result: " + result);
        System.out.println();

        //
        string = "HelloWorld";
        result = Utils.INSTANCE.toBytes(string);
        System.out.println("string: " + string + ", result: " + result);
        System.out.println();

        final byte[] keyAndIvGenerationSalt = new byte[]{76, 97, 0, 107, 114, -127, 97, -27};
        System.out.println("keyAndIvGenerationSalt:" + Arrays.toString(keyAndIvGenerationSalt));
        System.out.println("String:" + new String(keyAndIvGenerationSalt));

        LOGGER.debug("-main()");
    }

}
