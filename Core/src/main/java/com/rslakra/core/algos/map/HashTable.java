package com.rslakra.core.algos.map;

import com.rslakra.core.utils.HashUtils;
import com.rslakra.core.utils.ToString;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/4/22 5:24 PM
 */
public class HashTable<K, V> implements Map<K, V> {

    /* The percentage of capacity representing the maximum number of entries before the table will grow. E.g., 0.80 */
    private static final float FILL_FACTOR = 0.80f;
    /* The multiple to increase the capacity of the hash table, when the fill factor has been exceeded. E.g., 1.50 */
    private static final float GROWTH_FACTOR = 1.5f;
    private static final int DEFAULT_SIZE = 16;

    private HashEntry[] hashEntries;
    // maximum elements of the hashMap.
    private int capacity;
    // current elements of the hashMap.
    private int size;

    /**
     *
     */
    public HashTable() {
        this(DEFAULT_SIZE);
    }

    /**
     * @param capacity
     */
    public HashTable(final int capacity) {
        this.capacity = capacity;
        hashEntries = new HashEntry[this.capacity];
        size = 0;
    }

    /**
     * Validates the hashmap has enough capacity.
     */
    private void checkCapacity() {
    }

    /**
     * @param key
     * @return
     */
    private int getHashIndex(final Object key) {
        return HashUtils.getHashIndex(key, capacity);
    }


    /**
     * Returns the number of key-value mappings in this map.  If the map contains more than {@code Integer.MAX_VALUE}
     * elements, returns {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * Returns {@code true} if this map contains a mapping for the specified key.  More formally, returns {@code true}
     * if and only if this map contains a mapping for a key {@code k} such that {@code Objects.equals(key, k)}.  (There
     * can be at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified key
     * @throws ClassCastException   if the key is of an inappropriate type for this map (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map does not permit null keys (<a
     *                              href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsKey(Object key) {
        int hashIndex = getHashIndex(key);
        HashEntry hashEntry = hashEntries[hashIndex];
        while (hashEntry != null && !hashEntry.getKey().equals(key)) {
            hashEntry = hashEntry.getNextEntry();
        }

        return (hashEntry != null && hashEntry.getKey().equals(key));
    }

    /**
     * Returns {@code true} if this map maps one or more keys to the specified value.  More formally, returns {@code
     * true} if and only if this map contains at least one mapping to a value {@code v} such that {@code
     * Objects.equals(value, v)}.  This operation will probably require time linear in the map size for most
     * implementations of the {@code Map} interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the specified value
     * @throws ClassCastException   if the value is of an inappropriate type for this map (<a
     *                              href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this map does not permit null values (<a
     *                              href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the
     * key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code Objects.equals(key, k)}, then this method returns {@code v};
     * otherwise it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.  The {@link #containsKey containsKey} operation
     * may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for this map (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map does not permit null keys (<a
     *                              href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V get(final Object key) {
        int hashIndex = getHashIndex(key);
        HashEntry hashEntry = hashEntries[hashIndex];
        while (hashEntry != null && !hashEntry.getKey().equals(key)) {
            hashEntry = hashEntry.getNextEntry();
        }

        return (hashEntry != null ? (V) hashEntry.getValue() : null);
    }

    /**
     * Associates the specified value with the specified key in this map (optional operation).  If the map previously
     * contained a mapping for the key, the old value is replaced by the specified value.  (A map {@code m} is said to
     * contain a mapping for a key {@code k} if and only if {@link #containsKey(Object) m.containsKey(k)} would return
     * {@code true}.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or {@code null} if there was no mapping for {@code key}.
     * (A {@code null} return can also indicate that the map previously associated {@code null} with {@code key}, if the
     * implementation supports {@code null} values.)
     * @throws UnsupportedOperationException if the {@code put} operation is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value prevents it from being stored in
     *                                       this map
     * @throws NullPointerException          if the specified key or value is null and this map does not permit null
     *                                       keys or values
     * @throws IllegalArgumentException      if some property of the specified key or value prevents it from being
     *                                       stored in this map
     */
    @Override
    public V put(K key, V value) {
        int hashIndex = getHashIndex(key);
        HashEntry oldValue = hashEntries[hashIndex];
        while (oldValue != null && oldValue.hasNextEntry()) {
            oldValue = oldValue.getNextEntry();
        }

        if (oldValue == null) {
            hashEntries[hashIndex] = new HashEntry(key, value);
        } else {
            oldValue.nextEntry = new HashEntry(key, value);
        }
        size++;

        return (oldValue != null ? (V) oldValue.getValue() : null);
    }

    /**
     * Removes the mapping for a key from this map if it is present (optional operation).   More formally, if this map
     * contains a mapping from key {@code k} to value {@code v} such that {@code Objects.equals(key, k)}, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or {@code null} if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map contained no mapping for the key; it's also
     * possible that the map explicitly mapped the key to {@code null}.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or {@code null} if there was no mapping for {@code key}.
     * @throws UnsupportedOperationException if the {@code remove} operation is not supported by this map
     * @throws ClassCastException            if the key is of an inappropriate type for this map (<a
     *                                       href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key is null and this map does not permit null keys (<a
     *                                       href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V remove(Object key) {
        return null;
    }

    /**
     * Copies all of the mappings from the specified map to this map (optional operation).  The effect of this call is
     * equivalent to that of calling {@link #put(Object, Object) put(k, v)} on this map once for each mapping from key
     * {@code k} to value {@code v} in the specified map.  The behavior of this operation is undefined if the specified
     * map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the {@code putAll} operation is not supported by this map
     * @throws ClassCastException            if the class of a key or value in the specified map prevents it from being
     *                                       stored in this map
     * @throws NullPointerException          if the specified map is null, or if this map does not permit null keys or
     *                                       values, and the specified map contains null keys or values
     * @throws IllegalArgumentException      if some property of a key or value in the specified map prevents it from
     *                                       being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    /**
     * Removes all of the mappings from this map (optional operation). The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation is not supported by this map
     */
    @Override
    public void clear() {
        hashEntries = new HashEntry[capacity];
        size = 0;
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map. The set is backed by the map, so changes to the map
     * are reflected in the set, and vice-versa.  If the map is modified while an iteration over the set is in progress
     * (except through the iterator's own {@code remove} operation), the results of the iteration are undefined.  The
     * set supports element removal, which removes the corresponding mapping from the map, via the {@code
     * Iterator.remove}, {@code Set.remove}, {@code removeAll}, {@code retainAll}, and {@code clear} operations.  It
     * does not support the {@code add} or {@code addAll} operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
//        return Arrays.stream(hashEntries).map(linkedList -> (linkedList.stream().map(((HashEntry)hashEntry -> hashEntry.get).collect(Collectors.toSet())).getKey())
//            .collect(Collectors.toSet());

        return null;
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map. The collection is backed by the map, so
     * changes to the map are reflected in the collection, and vice-versa.  If the map is modified while an iteration
     * over the collection is in progress (except through the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.  The collection supports element removal, which removes the corresponding mapping
     * from the map, via the {@code Iterator.remove}, {@code Collection.remove}, {@code removeAll}, {@code retainAll}
     * and {@code clear} operations.  It does not support the {@code add} or {@code addAll} operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
//        return Arrays.stream(hashEntries).map(hashEntry -> (V) hashEntry.getValue()).collect(Collectors.toList());
        return null;
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map. The set is backed by the map, so changes to the
     * map are reflected in the set, and vice-versa.  If the map is modified while an iteration over the set is in
     * progress (except through the iterator's own {@code remove} operation, or through the {@code setValue} operation
     * on a map entry returned by the iterator) the results of the iteration are undefined.  The set supports element
     * removal, which removes the corresponding mapping from the map, via the {@code Iterator.remove}, {@code
     * Set.remove}, {@code removeAll}, {@code retainAll} and {@code clear} operations.  It does not support the {@code
     * add} or {@code addAll} operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
//        return Arrays.stream(hashEntries).map(hashEntry -> hashEntry).collect(Collectors.toSet());
        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return Arrays.stream(hashEntries).filter(hashEntry -> hashEntry != null).collect(Collectors.toSet()).toString();
    }

    /**
     *
     */
    private class HashEntry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private HashEntry nextEntry;

        /**
         * @param key
         * @param value
         */
        HashEntry(K key, V value) {
            this(key, value, null);
        }

        /**
         * @param key
         * @param value
         * @param nextEntry
         */
        HashEntry(K key, V value, HashEntry nextEntry) {
            this.key = key;
            this.value = value;
            this.nextEntry = nextEntry;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         * @throws IllegalStateException implementations may, but are not required to, throw this exception if the entry
         *                               has been removed from the backing map.
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.  If the mapping has been removed from the backing map (by the
         * iterator's {@code remove} operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         * @throws IllegalStateException implementations may, but are not required to, throw this exception if the entry
         *                               has been removed from the backing map.
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified value (optional operation). (Writes through
         * to the map.)  The behavior of this call is undefined if the mapping has already been removed from the map (by
         * the iterator's {@code remove} operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws UnsupportedOperationException if the {@code put} operation is not supported by the backing map
         * @throws ClassCastException            if the class of the specified value prevents it from being stored in
         *                                       the backing map
         * @throws NullPointerException          if the backing map does not permit null values, and the specified value
         *                                       is null
         * @throws IllegalArgumentException      if some property of this value prevents it from being stored in the
         *                                       backing map
         * @throws IllegalStateException         implementations may, but are not required to, throw this exception if
         *                                       the entry has been removed from the backing map.
         */
        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        public HashEntry getNextEntry() {
            return nextEntry;
        }

        public boolean hasNextEntry() {
            return (getNextEntry() != null);
        }

        /**
         * @param object
         * @return
         */
        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof HashEntry)) {
                return false;
            }

            final HashEntry<?, ?> hashEntry = (HashEntry<?, ?>) object;
            return getKey().equals(hashEntry.getKey());
        }

        /**
         * @return
         */
        @Override
        public int hashCode() {
            return Objects.hash(getKey());
        }

        /**
         * @return
         */
        @Override
        public String toString() {
            return ToString.of(HashEntry.class)
                .add("key=" + getKey())
                .add("value=" + getValue())
                .toString();
        }
    }

}
