package com.rslakra.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 6/28/21 2:57 PM
 */
public class Dictionary<K, V> extends ConcurrentHashMap<K, V> implements Serializable {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Dictionary.class);

    public Dictionary() {
        super();
        LOGGER.debug("Dictionary()");
    }

    /**
     * @param linkedHashMap
     */
    protected Dictionary(final LinkedHashMap linkedHashMap) {
        super(linkedHashMap);
        LOGGER.debug("Dictionary({})", linkedHashMap);
    }

    /**
     * @param dictionary
     */
    public Dictionary(final Dictionary dictionary) {
        super(dictionary);
        LOGGER.debug("Dictionary({})", dictionary);
    }

    /**
     * @return
     */
    public static <K, V> Dictionary newDictionary() {
        return new Dictionary<K, V>();
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public Dictionary of(K key, V value) {
        super.put(key, value);
        return this;
    }

    /**
     * @param linkedHashMap
     * @return
     */
    public Dictionary of(final LinkedHashMap linkedHashMap) {
        super.putAll(linkedHashMap);
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

}
