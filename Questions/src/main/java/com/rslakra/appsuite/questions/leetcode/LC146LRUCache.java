package com.rslakra.appsuite.questions.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 9/17/23 12:03 PM
 */
public class LC146LRUCache {

    private int capacity;
    private Map<Integer, Integer> map = new HashMap<>();
    private LinkedList<Integer> linkedList = new LinkedList();

    public LC146LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = map.get(key);
        if (map.containsKey(key)) {
            linkedList.remove((Object) key);
            linkedList.addFirst(key);
        } else {
            value = -1;
        }

        return value;
    }

    /**
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (map.size() >= capacity) {
            Integer lastKey = linkedList.removeLast();
            if (lastKey != null) {
                map.remove(lastKey);
            }
        }

        linkedList.addFirst(key);
        map.put(key, value);
    }
}
