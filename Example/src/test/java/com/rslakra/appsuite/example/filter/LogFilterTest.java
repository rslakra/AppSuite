package com.rslakra.appsuite.example.filter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 6/27/23 5:19 PM
 */
public class LogFilterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilterTest.class);

    /**
     * @throws UnknownHostException
     */
    @Test
    public void testGetUniqueRequestId() throws UnknownHostException {
        String requestId = LogFilter.getUniqueRequestId();
        LOGGER.debug("requestId:{}", requestId);
        LOGGER.debug("randomUUID:{}", UUID.randomUUID().toString());
        assertNotNull(requestId);
    }

}
