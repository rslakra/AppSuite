package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 7/1/21 4:28 PM
 */
public class JavaTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaTest.class);

    @Test
    public void testLoops() {
        for (int i = 1; i <= 10; i++) {
            for (int j = i + 1; j <= 5 && j > i; j++) {
                LOGGER.debug("j % i = " + (j % i));
                if (j % i == 0) {
                    LOGGER.debug("breaking for j=" + j + ", i=" + i);
                    break;
                }
            }
            LOGGER.debug("i={}\n", i);
        }
    }

}
