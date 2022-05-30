package com.rslakra.core;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 6/16/21 4:56 PM
 */
public class PayloadBuilderTest {

    @Test
    public void testAdd() {
        PayloadBuilder<String, String> payloadBuilder = PayloadBuilder
            .newBuilder()
            .of("name", "rohtash");
        Assert.assertEquals("rohtash", payloadBuilder.get("name"));
    }

}
