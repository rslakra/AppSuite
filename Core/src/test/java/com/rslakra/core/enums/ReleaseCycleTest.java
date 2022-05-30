package com.rslakra.core.enums;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 1/7/22 11:02 AM
 */
public class ReleaseCycleTest {

    // LOGGER
    private final Logger LOGGER = LoggerFactory.getLogger(ReleaseCycleTest.class);

    @Test
    public void testReleaseCycle() {
        ReleaseCycle releaseCycle = ReleaseCycle.of(null);
        LOGGER.debug("releaseCycle:{}", releaseCycle);
        Assert.assertNull(releaseCycle);

        releaseCycle = ReleaseCycle.of(ReleaseCycle.ALPHA.name());
        LOGGER.debug("releaseCycle:{}", releaseCycle);
        Assert.assertNotNull(releaseCycle);
        Assert.assertEquals(ReleaseCycle.ALPHA, releaseCycle);

        releaseCycle = ReleaseCycle.of("ALPHA");
        LOGGER.debug("releaseCycle:{}", releaseCycle);
        Assert.assertNotNull(releaseCycle);
        Assert.assertEquals(ReleaseCycle.ALPHA, releaseCycle);

        releaseCycle = ReleaseCycle.of("Alpha");
        LOGGER.debug("releaseCycle:{}", releaseCycle);
        Assert.assertNotNull(releaseCycle);
        Assert.assertEquals(ReleaseCycle.ALPHA, releaseCycle);

        releaseCycle = ReleaseCycle.of("alpha");
        LOGGER.debug("releaseCycle:{}", releaseCycle);
        Assert.assertNotNull(releaseCycle);
        Assert.assertEquals(ReleaseCycle.ALPHA, releaseCycle);

        releaseCycle = ReleaseCycle.of("gamma");
        LOGGER.debug("releaseCycle:{}", releaseCycle);
        Assert.assertNull(releaseCycle);
    }
}
