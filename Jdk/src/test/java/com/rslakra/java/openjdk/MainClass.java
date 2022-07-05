package com.rslakra.java.openjdk;

import com.rslakra.java.lang.AddressTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 5/29/20 12:16 PM
 */
public class MainClass {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) throws InterruptedException {
        int i = 1;
        do {
            LOGGER.debug("Before Sleeping : " + i);
            Thread.sleep(60 * 1000); //minutes to sleep
            LOGGER.debug("After Sleeping : " + i);
            i++;
        } while (i <= 10);
    }

}

