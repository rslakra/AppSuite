package com.rslakra.core.algos.map;

import com.rslakra.core.ToString;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/20/22 12:35 PM
 */
public class Dictionary<K, V> extends HashTable<K, V> {

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
    public Dictionary(final HashTable<K, V> hashTable) {
        putAll(hashTable);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Dictionary.class).toString();
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
        return new Dictionary((HashTable) get(key));
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
