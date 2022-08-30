package com.rslakra.core;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 4/7/21 5:27 PM
 */
public class PairTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(PairTest.class);

    @DataProvider
    public Iterator<Object[]> pairData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{Pair.NULL, true});
        data.add(new Object[]{Pair.of("name", "name"), true});
        data.add(new Object[]{Pair.of(1, 1), true});
        data.add(new Object[]{Pair.of(false, false), true});
        data.add(new Object[]{Pair.of(true, true), true});
        data.add(new Object[]{Pair.of(Pair.EMPTY_ARRAY, Pair.EMPTY_ARRAY), true});
        Object[] floats = new Object[]{10.3f, 53.7f};
        data.add(new Object[]{Pair.of(floats, floats), true});

        //different key/value.
        data.add(new Object[]{Pair.of("firstName", "Rohtash"), false});
        data.add(new Object[]{Pair.of("index", 10), false});
        data.add(new Object[]{Pair.of("exists", false), false});

        return data.iterator();
    }

    @Test(dataProvider = "pairData")
    public void testPair(Pair pair, Boolean isSame) {
        LOGGER.debug("testPair({}, {})", pair, isSame);
        if (isSame) {
            assertEquals(pair.getKey(), pair.getValue());
            assertEquals(pair.getLeft(), pair.getRight());
        } else {
            assertNotEquals(pair.getKey(), pair.getValue());
            assertNotEquals(pair.getLeft(), pair.getRight());
        }
    }

    @Test
    public void testCompareTo() {
        Pair leftPair = Pair.of("firstName", "lastName");
        LOGGER.debug("leftPair:{}", leftPair);
        assertEquals(0, leftPair.compareTo(leftPair));

        Pair nextPair = Pair.of("firstName", "name");
        LOGGER.debug("leftPair:{}, nextPair:{}", leftPair, nextPair);
        assertTrue(leftPair.compareTo(nextPair) < 0);

        Pair rightPair = Pair.of("eachName", "lastName");
        LOGGER.debug("leftPair:{}, rightPair:{}", leftPair, rightPair);
        assertTrue(leftPair.compareTo(rightPair) > 0);
    }
}
