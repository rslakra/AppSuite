package com.rslakra.core.algos.sort.keyindex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 1/6/21 1:02 PM
 */
public class KeyIndexSortTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyIndexSortTest.class);

    /**
     *
     */
    public static void testVowelKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String vowels = "ioeuaeuaoeu";
        LOGGER.debug("vowels:{}", vowels);
        vowels = keyIndexSort.sort(vowels);
        LOGGER.debug(vowels);

        // upper-case
        vowels = "IOUEAEIOUAEU";
        LOGGER.debug(vowels);
        vowels = keyIndexSort.sort(vowels);
        LOGGER.debug(vowels);
    }

    /**
     *
     */
    public static void testBinaryKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.BINARY);
        String binary = "1010111100101";
        LOGGER.debug(binary);
        binary = keyIndexSort.sort(binary);
        LOGGER.debug(binary);
    }

    /**
     *
     */
    public static void testAlphabetsKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String string = "Rohtash";
        LOGGER.debug(string);
        string = keyIndexSort.sort(string);
        LOGGER.debug(string);

        // lower-case
        string = "ROHTASH";
        LOGGER.debug(string);
        string = keyIndexSort.sort(string);
        LOGGER.debug(string);
    }

    /**
     *
     */
    public static void testAlphaNumericKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String string = "Rohtash2021";
        LOGGER.debug(string);
        string = keyIndexSort.sort(string);
        LOGGER.debug(string);
    }

    /**
     *
     */
    public static void testAsciiKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String string = "Rohtash2021!9(%)";
        LOGGER.debug(string);
        string = keyIndexSort.sort(string);
        LOGGER.debug(string);
    }

    /**
     * Starting Point.
     *
     * @param args
     */
    public static void main(String[] args) {
        testBinaryKeyIndexSort();
        testVowelKeyIndexSort();
        testAlphabetsKeyIndexSort();
        testAlphaNumericKeyIndexSort();
        testAsciiKeyIndexSort();
    }
}
