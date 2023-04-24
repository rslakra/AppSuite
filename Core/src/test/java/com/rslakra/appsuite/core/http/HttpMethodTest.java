package com.rslakra.appsuite.core.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 3/30/23 12:18 PM
 */
public class HttpMethodTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMethodTest.class);

    @Test
    public void testOfString() {
        LOGGER.debug("+testOfString()");
        HttpMethod httpMethod = HttpMethod.ofString(null);
        LOGGER.debug("httpMethod: {}", httpMethod);
        assertNull(httpMethod);

        httpMethod = HttpMethod.ofString(HttpMethod.GET.name());
        LOGGER.debug("httpMethod: {}", httpMethod);
        assertNotNull(httpMethod);
        assertEquals(HttpMethod.GET, httpMethod);

        httpMethod = HttpMethod.ofString(HttpMethod.POST.name());
        LOGGER.debug("httpMethod: {}", httpMethod);
        assertNotNull(httpMethod);
        assertEquals(HttpMethod.POST, httpMethod);
        LOGGER.debug("-testOfString()");
    }


    @Test
    public void testIsEquals() {
        LOGGER.debug("+testIsEquals()");
        assertFalse(HttpMethod.isEquals(null, HttpMethod.HEAD));
        assertTrue(HttpMethod.isEquals(HttpMethod.HEAD.name(), HttpMethod.HEAD, HttpMethod.GET));
        assertTrue(HttpMethod.isEquals(HttpMethod.HEAD.name().toLowerCase(), HttpMethod.HEAD, HttpMethod.GET));
        assertTrue(HttpMethod.isEquals(HttpMethod.HEAD.name().toUpperCase(), HttpMethod.HEAD, HttpMethod.GET));
        assertTrue(HttpMethod.isEquals(HttpMethod.HEAD));
        assertFalse(HttpMethod.isEquals(HttpMethod.PATCH.name(), HttpMethod.HEAD, HttpMethod.GET));
        LOGGER.debug("-testIsEquals()");
    }

}
