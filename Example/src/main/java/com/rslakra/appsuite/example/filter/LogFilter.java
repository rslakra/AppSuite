package com.rslakra.appsuite.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Rohtash Lakra
 * @created 1/27/22 5:26 PM
 */
public class LogFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);
    private static final String X_REQUEST_ID = "X-Request-Id";

    /**
     * Returns the unique request-id.
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getUniqueRequestId() throws UnknownHostException {
        return UUID.nameUUIDFromBytes(
            (InetAddress.getLocalHost().getHostName() + System.currentTimeMillis() + Thread.currentThread().getName()
             + ThreadLocalRandom.current().nextInt()).getBytes(StandardCharsets.UTF_8)).toString();
    }

    /**
     * Sets the <code>requestId</code> to MDC (MDCAdapter) logger and request attribute.
     */
    public void pushRequestIdHeader() {
        try {
            final String requestId = getUniqueRequestId();
            MDC.put(X_REQUEST_ID, requestId);
        } catch (UnknownHostException ex) {
            LOGGER.error("Unable to set X-Request-ID to MDC! ex:{}", ex);
        }
    }

    /**
     * Removes the <code>X_REQUEST_ID</code> from the MDC for the current running thread.
     */
    public static void popRequestIdHeader() {
        MDC.remove(X_REQUEST_ID);
    }

}
