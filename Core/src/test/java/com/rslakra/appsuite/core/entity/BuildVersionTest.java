package com.rslakra.appsuite.core.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @created 6/16/22 5:49 PM
 */
public class BuildVersionTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(BuildVersionTest.class);

    @Test
    public void testBuildVersion() {
        BuildVersion buildVersion = new BuildVersion();
        LOGGER.debug("buildVersion:{}", buildVersion);
        Assert.assertNotNull(buildVersion);
        buildVersion.loadBuildProperties();
    }
}
