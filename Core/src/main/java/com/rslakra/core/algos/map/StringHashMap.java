package com.rslakra.core.algos.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 10:53 AM
 */
public class StringHashMap<V> extends HashMap<String, V> {

    public StringHashMap() {
    }

    /**
     * @param contents
     */
    public StringHashMap(final Map<? extends String, ? extends V> contents) {
        this.putAll(contents);
    }

    /**
     * @param key
     * @return
     */
    public boolean containsKey(final String key) {
        return key == null ? false : super.containsKey(key);
    }

    /**
     * @param key
     * @return
     */
    public V get(final String key) {
        return key == null ? null : super.get(key);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public V put(final String key, V value) {
        return key != null ? super.put(key, value) : value;
    }

    @Override
    public final void putAll(final Map<? extends String, ? extends V> contents) {
        Iterator<? extends String> itr = contents.keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next();
            this.put(key, contents.get(key));
        }
    }

    /**
     * @param key
     * @return
     */
    public V remove(Object key) {
        return key == null ? null : super.remove(key.toString());
    }
}
