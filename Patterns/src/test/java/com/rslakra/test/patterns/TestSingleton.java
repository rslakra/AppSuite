package com.rslakra.test.patterns;

import com.rslakra.appsuite.patterns.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 12/23/19 3:37 PM
 */
public class TestSingleton {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSingleton.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            public void run() {
                Singleton singleton = Singleton.INSTANCE;
                LOGGER.debug("singleton: " + singleton);
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
    }
}
