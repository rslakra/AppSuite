package com.rslakra.core.algos.sort.keyindex;

/**
 * @author Rohtash Lakra
 * @created 1/6/21 1:52 PM
 */
public class AsciiStringKeyIndex extends StringKeyIndex<Character> {

    public AsciiStringKeyIndex() {
        int index = 0;
        // lower-case
        for (int i = 0; i < 128; i++) {
            put(Character.valueOf((char) i), index++);
        }
    }

    /**
     * @param key
     * @return
     */
    @Override
    public Integer get(Object key) {
        return super.get(key);
    }
}
