package com.rslakra.core;

import static org.testng.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 7/21/22 4:38 PM
 */
public class CharSetsTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(CharSetsTest.class);

    @DataProvider
    public Iterator<Object[]> charSetsData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{null, CharSets.UTF_8});
        data.add(new Object[]{CharSets.ASCII.toCharset(), CharSets.ASCII});
        data.add(new Object[]{CharSets.ISO_8859_1.toCharset(), CharSets.ISO_8859_1});
        data.add(new Object[]{CharSets.UTF_8.toCharset(), CharSets.UTF_8});
        data.add(new Object[]{CharSets.UTF_16.toCharset(), CharSets.UTF_16});
        data.add(new Object[]{"null", CharSets.UTF_8});

        return data.iterator();
    }

    /**
     * @param charSetName
     * @param expected
     */
    @Test(dataProvider = "charSetsData")
    public void testCharSets(final String charSetName, final CharSets expected) {
        LOGGER.debug("testCharSets({}, {})", charSetName, expected);
        assertEquals(expected, CharSets.forName(charSetName));
    }
}
