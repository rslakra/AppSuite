package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 6/9/21 12:54 PM
 */
public class Slf4jLoggerTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(Slf4jLoggerTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.trace("Trace log message");
        LOGGER.debug("Debug log message");
        LOGGER.info("Info log message");
        LOGGER.warn("Warn log message");
        LOGGER.error("Error log message");
    }
}
