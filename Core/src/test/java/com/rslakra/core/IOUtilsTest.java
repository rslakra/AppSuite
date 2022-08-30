package com.rslakra.core;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
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
     * @return
     */
    @DataProvider
    public Iterator<Object[]> classFilePathData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{null, null});
        data.add(new Object[]{BeanUtilsTest.class, "com/rslakra/core"});
        data.add(new Object[]{IOUtils.class, "com/rslakra/core"});

        return data.iterator();
    }

    /**
     * @param classType
     * @param expectedPath
     * @param <T>
     */
    @Test(dataProvider = "classFilePathData")
    public <T> void testGetClassFilePath(Class<T> classType, String expectedPath) {
        String classPath = IOUtils.getClassFilePath(classType);
        LOGGER.debug("classPath:{}", classPath);
        if (BeanUtils.isNull(expectedPath)) {
            assertNull(classPath);
        } else {
            assertNotNull(classPath);
            assertTrue(classPath.endsWith(expectedPath));
        }
    }

    /**
     *
     */
    @Test
    public void testPost() {
        // 5. Build and add JSON data into POST request body
        IOUtils.RequestBody
            requestBody = IOUtils.newBody()
            .addProperty("user_agent", "IOUtils")
            .addProperty("domain", "yahoo.com")
            .addProperty("country_id", "USA");
        LOGGER.debug("requestBody: {}", requestBody);
        StringBuilder response = IOUtils.post("localhost", 8080, "", IOUtils.APPLICATION_JSON, requestBody);
        LOGGER.debug("response: {}", response);
        assertNull(response);
    }

}
