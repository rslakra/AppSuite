package com.rslakra.core.http;

import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @version 1.0.0
 * @Created Jun 18, 2022 17:50:52
 */
public class HTTPUtilsTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPUtilsTest.class);

    @Test
    public void testGetRequestTracer() {
        final String urlString = "https://www.xe.com/currencyconverter/convert/?Amount=1&From=USD&To=INR";
        HttpPost httpPost = new HttpPost(urlString);
        httpPost.setHeader(HTTPUtils.REQUEST_TRACER, HTTPUtils.nextRequestTracer());
        LOGGER.debug("{}: {}", HTTPUtils.REQUEST_TRACER, HTTPUtils.getRequestTracer(httpPost));
        LOGGER.debug("getURI:{}", httpPost.getURI());
        LOGGER.debug("RequestLine().getUri:{}", httpPost.getRequestLine().getUri());

        long mills = System.currentTimeMillis();
        int count = 3000;
        LOGGER.debug("diff={}", (mills / count));
    }
}
