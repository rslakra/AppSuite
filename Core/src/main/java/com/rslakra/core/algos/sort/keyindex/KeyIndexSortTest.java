package com.rslakra.core.algos.sort.keyindex;

/**
 * @author Rohtash Lakra
 * @created 1/6/21 1:02 PM
 */
public class KeyIndexSortTest {

    /**
     *
     */
    public static void testVowelKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String vowels = "ioeuaeuaoeu";
        System.out.println(vowels);
        vowels = keyIndexSort.sort(vowels);
        System.out.println(vowels);

        // upper-case
        vowels = "IOUEAEIOUAEU";
        System.out.println(vowels);
        vowels = keyIndexSort.sort(vowels);
        System.out.println(vowels);
    }

    /**
     *
     */
    public static void testBinaryKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.BINARY);
        String binary = "1010111100101";
        System.out.println(binary);
        binary = keyIndexSort.sort(binary);
        System.out.println(binary);
    }

    /**
     *
     */
    public static void testAlphabetsKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String string = "Rohtash";
        System.out.println(string);
        string = keyIndexSort.sort(string);
        System.out.println(string);
        System.out.println();

        // lower-case
        string = "ROHTASH";
        System.out.println(string);
        string = keyIndexSort.sort(string);
        System.out.println(string);
    }

    /**
     *
     */
    public static void testAlphaNumericKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String string = "Rohtash2021";
        System.out.println(string);
        string = keyIndexSort.sort(string);
        System.out.println(string);
    }

    /**
     *
     */
    public static void testAsciiKeyIndexSort() {
        KeyIndexSort keyIndexSort = new KeyIndexSort(KeyIndexType.ASCII);
        // lower-case
        String string = "Rohtash2021!9(%)";
        System.out.println(string);
        string = keyIndexSort.sort(string);
        System.out.println(string);
    }

    /**
     * Starting Point.
     *
     * @param args
     */
    public static void main(String[] args) {
        testBinaryKeyIndexSort();
        System.out.println();
        testVowelKeyIndexSort();
        System.out.println();
        testAlphabetsKeyIndexSort();
        System.out.println();
        testAlphaNumericKeyIndexSort();
        System.out.println();
        testAsciiKeyIndexSort();
    }
}
