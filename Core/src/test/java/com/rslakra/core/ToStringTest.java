package com.rslakra.core;

import com.rslakra.core.utils.ToString;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 12/17/21 12:16 PM
 */
public class ToStringTest {

    // LOGGER
    private final Logger LOGGER = LoggerFactory.getLogger(ToStringTest.class);

    @Test
    public void testToString() {
        String str = new ToStringTest().toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("ToStringTest"));
    }

    @Test
    public void testOfClass() {
        String str = ToString.of(ToStringTest.class).add("name").toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("ToStringTest"));
        Assert.assertTrue(str.contains("name"));
    }

    @Test
    public void testAdd() {
        // test null value
        String str = ToString.of().add(null).toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("<null>"));

        //test  value
        str = ToString.of().add("name").toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("name"));
    }

    @Test
    public void testAddKeyValue() {
        // test null key
        String str = ToString.of().add(null, "Rohtash Lakra").toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("<Rohtash Lakra>"));

        // test null value
        str = ToString.of().add("name", null).toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("<name>"));

        //test key/value
        str = ToString.of()
            .add("firstName", "Rohtash")
            .add("lastName", "Lakra")
            .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("firstName="));
        Assert.assertTrue(str.contains("lastName="));
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(ToStringTest.class).toString();
    }
}
