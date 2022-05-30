package com.rslakra.core.stactrace;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 6/9/21 12:01 PM
 */
public class CallerTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(CallerTest.class);

    /**
     * @param classType
     */
    private void validateCallerClass(Class<?> classType) {
        LOGGER.debug("+validateCallerClass({})", classType);
        assertNotNull(classType);
        assertEquals(CallerTest.class, classType);
        assertEquals("com.rslakra.core.stactrace.CallerTest", classType.getName());
        assertEquals("CallerTest", classType.getSimpleName());
        LOGGER.debug("-validateCallerClass()");
    }

    @Test
    public void testCallerBySecurityManager() {
        LOGGER.debug("+testCallerBySecurityManager()");
        Caller caller = CallerFactory.getCaller(CallerFactory.CallerType.CallerBySecurityManager);
        LOGGER.debug("caller:{}", caller);
        assertNotNull(caller);
        validateCallerClass(caller.getCallerClass());
        assertTrue(caller.isCaller(CallerTest.class));
        assertEquals("testCallerBySecurityManager", caller.getLastMethodName());
        LOGGER.debug("-testCallerBySecurityManager()");
    }

    @Test
    public void testCallerByStackTrace() {
        LOGGER.debug("+testCallerByStackTrace()");
        Caller caller = CallerFactory.getCaller(CallerFactory.CallerType.CallerByStackTrace);
        LOGGER.debug("caller:{}", caller);
        assertNotNull(caller);
        validateCallerClass(caller.getCallerClass());
        assertTrue(caller.isCaller(CallerTest.class));
        assertEquals("testCallerByStackTrace", caller.getLastMethodName());
        LOGGER.debug("-testCallerByStackTrace()");
    }
}
