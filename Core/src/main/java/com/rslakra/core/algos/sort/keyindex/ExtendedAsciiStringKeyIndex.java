package com.rslakra.core.algos.sort.keyindex;

/**
 * @author Rohtash Lakra
 * @created 1/6/21 1:52 PM
 */
public class ExtendedAsciiStringKeyIndex extends StringKeyIndex<Character> {

    public ExtendedAsciiStringKeyIndex() {
        int index = 0;
        // lower-case
        for (int i = 0; i < 256; i++) {
            put(Character.valueOf((char) i), index++);
        }
    }

}
