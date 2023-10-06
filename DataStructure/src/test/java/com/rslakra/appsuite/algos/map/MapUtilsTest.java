package com.rslakra.appsuite.algos.map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 9/19/23 3:21 PM
 */
public class MapUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapUtilsTest.class);

    @Test
    public void testSortByValue() {
        int[] values = {2, 3, 4, 5, 2, 4, 5, 1};
        Map<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            hashMap.put(String.valueOf(values[i]), values[i] * values.length);
        }
        LOGGER.debug("hashMap: {}", hashMap);

        Map<String, Integer> sortedMap = MapUtils.sortByValue(hashMap);
        LOGGER.debug("sortedMap: {}", sortedMap);
    }
}
