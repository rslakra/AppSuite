package com.rslakra.java.guava;

import static junit.framework.TestCase.assertEquals;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Rohtash Lakra
 * @created 2/11/20 3:06 PM
 */
public class TestLocalPayload {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(TestLocalPayload.class);

    @Test
    public void testLocalCache() {
        LoadingCache<String, String>
            cache =
            CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    return key.toUpperCase();
                }
            });

//        cache.put("One", "1");

        assertEquals(0, cache.size());
        assertEquals("HELLO", cache.getUnchecked("hello"));
        assertEquals(1, cache.size());
    }

    @Test
    public void listAllFields() {
        List<Field> fieldList = new ArrayList<>();
        Class tmpClass = getClass();
        while (tmpClass != null && !tmpClass.getClass().equals(Object.class)) {
            fieldList.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
            tmpClass = tmpClass.getSuperclass();
        }

        fieldList.forEach(field ->  LOGGER.debug(field.getName() + " -> " + field.getClass().getName()));
    }
}
