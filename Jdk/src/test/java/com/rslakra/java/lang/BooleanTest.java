package com.rslakra.java.lang;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * @author Rohtash Lakra
 * @created 5/1/23 1:11 PM
 */
public class BooleanTest {


    @Test
    public void testNull() {
        Boolean value = null;
        assertFalse(Boolean.parseBoolean(null));
        assertFalse(Boolean.TRUE.equals(value));
    }

}
