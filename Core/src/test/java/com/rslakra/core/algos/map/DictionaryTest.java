package com.rslakra.core.algos.map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @created 5/20/22 12:42 PM
 */
public class DictionaryTest {

    private static Logger LOGGER = LoggerFactory.getLogger(Dictionary.class);

    @Test
    public void testDictionary() {
        Dictionary<String, String> nameDictionary = Dictionary.newDictionary()
            .of("firstName", "Rohtash Singh")
            .of("lastName", "Lakra");
        LOGGER.debug("nameDictionary:{}", nameDictionary);
        assertTrue(nameDictionary.size() > 0);
        assertNotNull(nameDictionary.keySet());

        Dictionary<String, Dictionary> dictionary = Dictionary.newDictionary().of("name", nameDictionary);
        LOGGER.debug(dictionary.toString());
        LOGGER.debug("dictionary:{}", dictionary);
        assertTrue(dictionary.size() > 0);
        assertNotNull(dictionary.keySet());
    }

}
