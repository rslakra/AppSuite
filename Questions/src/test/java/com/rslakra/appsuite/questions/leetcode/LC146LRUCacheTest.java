package com.rslakra.appsuite.questions.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Rohtash Lakra
 * @created 9/17/23 12:03 PM
 */
public class LC146LRUCacheTest {

    @Test
    public void testLRUCache() {
        LC146LRUCache lRUCache = new LC146LRUCache(3);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {2=2, 1=1}
        assertEquals(1, lRUCache.get(1)); // cache is {1=1, 2=2}
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {3=3, 1=1, 2=2}
        assertEquals(2, lRUCache.get(2)); // LRU key 2, cache is {2=2, 3=3, 1=1}
        lRUCache.put(4, 4); // LRU key 4, evict 1, cache is {4=4, 2=2, 3=3}
        assertEquals(-1, lRUCache.get(1)); // LRU key 1, cache is {4=4, 2=2, 3=3}
        assertEquals(3, lRUCache.get(3)); // LRU key 3, cache is {3=3, 4=4, 2=2}
        assertEquals(4, lRUCache.get(4)); // LRU key 4, cache is {4=4, 3=3, 2=2}
    }
}
