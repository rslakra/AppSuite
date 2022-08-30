package com.rslakra.core;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @version 1.0.0
 * @created 12/13/21 5:41 PM
 */
public class ToStringTest {

    // LOGGER
    private final Logger LOGGER = LoggerFactory.getLogger(ToStringTest.class);

    /**
     * ToString
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(ToStringTest.class).toString();
    }

    @Test
    public void testOfWithDelimiterAndPrefixAndSuffix() {
        String
            str =
            ToString.of(ToStringTest.class, ";", "{", "}")
                .add("firstName", "Rohtash")
                .add("lastName", "Lakra")
                .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("com.rslakra.core.ToStringTest {firstName=Rohtash;lastName=Lakra}", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfWithExcludePackageWithDelimiterAndPrefixAndSuffix() {
        String
            str =
            ToString.of(ToStringTest.class, true, ";", "{", "}")
                .add("firstName", "Rohtash")
                .add("lastName", "Lakra")
                .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("ToStringTest {firstName=Rohtash;lastName=Lakra}", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfWithCustomDelimiterAndPrefixAndSuffix() {
        String
            str =
            ToString.of(ToStringTest.class, ";", "{", "}")
                .add("firstName", "Rohtash")
                .add("lastName", "Lakra")
                .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("com.rslakra.core.ToStringTest {firstName=Rohtash;lastName=Lakra}", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfWithIncludePackage() {
        String
            str =
            ToString.of(ToStringTest.class, false, ";")
                .add("firstName", "Rohtash")
                .add("lastName", "Lakra")
                .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("com.rslakra.core.ToStringTest <firstName=Rohtash;lastName=Lakra>", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfWithExcludePackage() {
        String
            str =
            ToString.of(ToStringTest.class, true, ";")
                .add("firstName", "Rohtash")
                .add("lastName", "Lakra")
                .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("ToStringTest <firstName=Rohtash;lastName=Lakra>", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfWithPrefixAndSuffix() {
        String
            str =
            ToString.of(ToStringTest.class, "[", "]")
                .add("firstName", "Rohtash")
                .add("lastName", "Lakra")
                .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("com.rslakra.core.ToStringTest [firstName=Rohtash, lastName=Lakra]", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfWithDelimiter() {
        String
            str =
            ToString.of(ToStringTest.class, ":")
                .add("firstName", "Rohtash")
                .add("lastName", "Lakra")
                .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("com.rslakra.core.ToStringTest <firstName=Rohtash:lastName=Lakra>", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfClass() {
        String str = ToString.of(ToStringTest.class)
            .add("firstName", "Rohtash")
            .add("lastName", "Lakra")
            .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("com.rslakra.core.ToStringTest <firstName=Rohtash, lastName=Lakra>", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfNoClass() {
        String str = ToString.of()
            .add("firstName", "Rohtash")
            .add("lastName", "Lakra")
            .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("<firstName=Rohtash, lastName=Lakra>", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
    }

    @Test
    public void testOfNoClassWithDelimiterAndPrefixAndSuffix() {
        String str = ToString.of(",", "{", "}")
            .add("firstName", "Rohtash")
            .add("lastName", "Lakra")
            .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("{firstName=Rohtash,lastName=Lakra}", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));

        str = ToString.of("&", ToString.EMPTY_STR, ToString.EMPTY_STR)
            .add("firstName", "Rohtash")
            .add("lastName", "Lakra")
            .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("firstName=Rohtash&lastName=Lakra", str);
        Assert.assertTrue(str.contains("firstName"));
        Assert.assertTrue(str.contains("lastName"));
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
        String str = ToString.of()
            .add(null, "Rohtash Lakra")
            .toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertTrue(str.contains("<Rohtash Lakra>"));

        // test null value
        str = ToString.of()
            .add("name", null)
            .toString();
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

    @Test
    public void testToString() {
        String str = new ToStringTest().toString();
        LOGGER.debug(str);
        Assert.assertNotNull(str);
        Assert.assertEquals("com.rslakra.core.ToStringTest <>", str);
    }
}
