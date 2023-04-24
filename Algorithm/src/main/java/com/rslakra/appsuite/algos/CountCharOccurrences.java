package com.rslakra.appsuite.algos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 3/18/20 10:09 AM
 */
public class CountCharOccurrences {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(CountCharOccurrences.class);

    public void printCharOccurrencesCount(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        LOGGER.debug("map:{}", map);
    }

    public static void main(String[] args) {
        CountCharOccurrences count = new CountCharOccurrences();
        count.printCharOccurrencesCount("rohtash singh");
    }
}
