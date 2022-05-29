package com.rslakra.core.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;

/**
 * @Author Rohtash Lakra
 * @Since 3/19/20 1:55 PM
 */
public class SetsTest {

    @Test
    public void testCreateSet() {
        //unique values
        Set<String> types = Sets.asSet("VISA", "MSTR", "AMEX", "DISC");
        Assert.assertEquals(4, types.size());
        types.forEach(item -> System.out.println(item));
        System.out.println();
        printSpliterator(types);

        //duplicate values
        types = Sets.asSet("VISA", "MSTR", "MSTR", "AMEX", "DISC");
        Assert.assertEquals(4, types.size());
        types.forEach(item -> System.out.println(item));
        System.out.println();
        printSpliterator(types);
    }

    /**
     * @param types
     */
    private void printSpliterator(final Set<String> types) {
        Objects.requireNonNull(types);
        Spliterator<String> spliterator = types.spliterator();
        System.out.println(
            "characteristics: " + spliterator.characteristics() + ", estimateSize: " + spliterator.estimateSize());


    }
}
