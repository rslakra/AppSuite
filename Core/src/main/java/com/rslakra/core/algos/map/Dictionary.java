package com.rslakra.core.algos.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 5/20/22 12:35 PM
 */
public class Dictionary<K, V> extends HashTable<K, V> {

    private static Logger LOGGER = LoggerFactory.getLogger(Dictionary.class);

    /**
     *
     */
    public Dictionary() {
        super();
    }

    /**
     * @param capacity
     */
    public Dictionary(final int capacity) {
        super(capacity);
    }

    /**
     * @param hashTable
     */
    public Dictionary(final Map<K, V> hashTable) {
        super(hashTable);
        LOGGER.debug("hashTable:{}", hashTable);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public Dictionary of(final K key, final V value) {
        super.put(key, value);
        return this;
    }

    /**
     * @param hashTable
     * @return
     */
    public Dictionary of(final HashTable<K, V> hashTable) {
        super.putAll(hashTable);
        return this;
    }

    /**
     * @param key
     * @return
     */
    public Dictionary getDictionary(final K key) {
        return new Dictionary((LinkedHashMap) get(key));
    }

    /**
     * @param dictionary
     * @return
     */
    public Dictionary of(final Dictionary dictionary) {
        super.putAll(dictionary);
        return this;
    }

    /**
     * @return
     */
    public static <K, V> Dictionary newDictionary() {
        return new Dictionary<K, V>();
    }
}
