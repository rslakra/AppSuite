package com.rslakra.core.algos.lang;

import com.rslakra.core.algos.text.Str;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rohtash Lakra
 * @created 1/20/21 4:09 PM
 */
public class StrTest {

    @Test
    public void testSearchNullOrEmptyValue() {
        Assert.assertEquals(-1, Str.search(null, null));
        Assert.assertEquals(-1, Str.search("", ""));
        Assert.assertEquals(-1, Str.search("lakra", ""));

        Assert.assertEquals(-1, Str.searchOptimized(null, null));
        Assert.assertEquals(-1, Str.searchOptimized("", ""));
        Assert.assertEquals(-1, Str.searchOptimized("lakra", ""));
    }

    @Test
    public void testSearchPattern() {
        String text = "The quick brown fox jumps over the little lazy dog.";
        Assert.assertEquals(-1, Str.search("hello", null));
        Assert.assertEquals(0, Str.search("The", text));
        Assert.assertEquals(4, Str.search("quick", text));
        Assert.assertEquals(16, Str.search("fox", text));
        Assert.assertEquals(26, Str.search("over", text));
        Assert.assertEquals(35, Str.search("little", text));
        Assert.assertEquals(42, Str.search("lazy", text));

        // little optimization with re-indexing
        Assert.assertEquals(-1, Str.searchOptimized("hello", null));
        Assert.assertEquals(0, Str.searchOptimized("The", text));
        Assert.assertEquals(4, Str.searchOptimized("quick", text));
        Assert.assertEquals(16, Str.searchOptimized("fox", text));
        Assert.assertEquals(26, Str.searchOptimized("over", text));
        Assert.assertEquals(35, Str.searchOptimized("little", text));
        Assert.assertEquals(42, Str.searchOptimized("lazy", text));
    }

}
