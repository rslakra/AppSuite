package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rohtash Lakra
 * @created 4/15/20 6:42 PM
 */
public final class PayloadBuilder<K, V> extends ConcurrentHashMap<K, V> {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(PayloadBuilder.class);

    /**
     *
     */
    public PayloadBuilder() {
        super();
    }

    /**
     * @param payloadBuilder
     */
    public PayloadBuilder(final PayloadBuilder payloadBuilder) {
        super(payloadBuilder);
        LOGGER.debug("PayloadBuilder({})", payloadBuilder);
    }

    /**
     * @param key
     * @return
     */
    public V get(final String key) {
        LOGGER.debug("get({})", key);
        return super.get(key);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public V getOrDefault(final String key, final V defaultValue) {
        return super.getOrDefault(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public V add(final K key, final V value) {
        return super.put(key, value);
    }

    /**
     * @param key
     * @param value
     */
    public PayloadBuilder of(final K key, final V value) {
        super.put(key, value);
        return this;
    }

    /**
     * @param key
     * @param value
     */
    public V setIfAbsent(final K key, final V value) {
        return super.putIfAbsent(key, value);
    }


    /**
     * @param key
     * @return
     */
    public boolean contains(final String key) {
        return super.containsKey(key);
    }

    /**
     * Returns the new <code>PayloadBuilder</code> object.
     *
     * @return
     */
    public static <K, V> PayloadBuilder<K, V> newBuilder() {
        return new PayloadBuilder<K, V>();
    }
}
