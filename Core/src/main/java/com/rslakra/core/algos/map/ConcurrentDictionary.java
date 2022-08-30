package com.rslakra.core.algos.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rohtash Lakra
 * @created 6/28/21 2:57 PM
 */
public class ConcurrentDictionary<K, V> extends ConcurrentHashMap<K, V> implements Serializable {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentDictionary.class);

    public ConcurrentDictionary() {
        super();
        LOGGER.debug("ConcurrentDictionary()");
    }

    /**
     * @param linkedHashMap
     */
    protected ConcurrentDictionary(final LinkedHashMap linkedHashMap) {
        super(linkedHashMap);
        LOGGER.debug("ConcurrentDictionary({})", linkedHashMap);
    }

    /**
     * @param dictionary
     */
    public ConcurrentDictionary(final ConcurrentDictionary dictionary) {
        super(dictionary);
        LOGGER.debug("ConcurrentDictionary({})", dictionary);
    }

    /**
     * @return
     */
    public static <K, V> ConcurrentDictionary newDictionary() {
        return new ConcurrentDictionary<K, V>();
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public ConcurrentDictionary of(K key, V value) {
        super.put(key, value);
        return this;
    }

    /**
     * @param linkedHashMap
     * @return
     */
    public ConcurrentDictionary of(final LinkedHashMap linkedHashMap) {
        super.putAll(linkedHashMap);
        return this;
    }

    /**
     * @param key
     * @return
     */
    public ConcurrentDictionary getDictionary(final K key) {
        return new ConcurrentDictionary((LinkedHashMap) get(key));
    }

    /**
     * @param dictionary
     * @return
     */
    public ConcurrentDictionary of(final ConcurrentDictionary dictionary) {
        super.putAll(dictionary);
        return this;
    }

}
