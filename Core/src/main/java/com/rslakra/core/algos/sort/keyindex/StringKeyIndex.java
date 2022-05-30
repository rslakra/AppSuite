package com.rslakra.core.algos.sort.keyindex;

import java.util.HashMap;

/**
 * @author Rohtash Lakra
 * @created 1/6/21 1:53 PM
 */
public abstract class StringKeyIndex<Key> extends HashMap<Key, Integer> {

    public StringKeyIndex() {
    }

    /**
     * Returns the integer value.
     *
     * @param key
     * @return
     */
    @Override
    public Integer get(Object key) {
        return super.containsKey(key) ? super.get(key) : 0;
    }
}
