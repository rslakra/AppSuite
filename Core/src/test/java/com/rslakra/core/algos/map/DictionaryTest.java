package com.rslakra.core.algos.map;

import com.rslakra.core.algos.map.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/20/22 12:42 PM
 */
public class DictionaryTest {

    private static Logger LOGGER = LoggerFactory.getLogger(Dictionary.class);

    @Test
    public void testDictionary() {
        Dictionary<String, String> nameDictionary = Dictionary.newDictionary()
            .of("firstName", "Rohtash Singh")
            .of("lastName", "Lakra");
        Dictionary<String, Dictionary> dictionary = Dictionary.newDictionary().of("name", nameDictionary);
        LOGGER.debug(dictionary.toString());
    }

}
