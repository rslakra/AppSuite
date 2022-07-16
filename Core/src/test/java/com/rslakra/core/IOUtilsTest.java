package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertNull;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/29/21 11:21 AM
 */
public class IOUtilsTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(IOUtilsTest.class);

    /**
     *
     */
    @Test
    public void testApplyFilePermissions() {
        Path tempPathFolder = Paths.get(IOUtils.getBuildDir("target", "temp", "test.tmp"));
        LOGGER.debug("tempPathFolder: {}", tempPathFolder);
        try {
            IOUtils.applyFilePermissions(tempPathFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     */
    @Test
    public void testPost() {
        // 5. Build and add JSON data into POST request body
        IOUtils.RequestBody
                requestBody =
                IOUtils.newBody().addProperty("user_agent", "IOUtils").addProperty("domain", "yahoo.com")
                        .addProperty("country_id", "USA");
        LOGGER.debug("requestBody: {}", requestBody);
        StringBuilder response = IOUtils.post("localhost", 8080, "", IOUtils.APPLICATION_JSON, requestBody);
        LOGGER.debug("response: {}", response);
        assertNull(response);
    }

}
