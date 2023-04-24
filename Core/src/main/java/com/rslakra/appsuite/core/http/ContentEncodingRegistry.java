package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.appsuite.core.http.encoding.DeflateEncoding;
import com.rslakra.appsuite.core.http.encoding.GZIPEncoding;
import org.apache.http.impl.client.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:12 PM
 */
public final class ContentEncodingRegistry {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentEncodingRegistry.class);
    // availableEncoders
    private final Map<ContentEncoding.EncodingType, ContentEncoding> availableEncoders = new HashMap<>();

    public ContentEncodingRegistry() {
        // register default encoders
        if (BeanUtils.isEmpty(availableEncoders)) {
            availableEncoders.put(ContentEncoding.EncodingType.GZIP, new GZIPEncoding());
            availableEncoders.put(ContentEncoding.EncodingType.DEFLATE, new DeflateEncoding());
            LOGGER.debug("availableEncoders:{}", availableEncoders);
        }
    }

    /**
     * TODO: FIX ME!
     *
     * @param httpClient
     * @param contentEncodingTypes
     */
    public void setInterceptors(AbstractHttpClient httpClient, ContentEncoding.EncodingType... contentEncodingTypes) {
        LOGGER.debug("setInterceptors({}, {})", httpClient, contentEncodingTypes);
        httpClient.removeRequestInterceptorByClass(ContentEncoding.RequestInterceptor.class);
        httpClient.removeResponseInterceptorByClass(ContentEncoding.ResponseInterceptor.class);
        for (ContentEncoding.EncodingType contentEncodingType : contentEncodingTypes) {
            final ContentEncoding contentEncoding = availableEncoders.get(contentEncodingType);
            LOGGER.debug("contentEncoding:{}", contentEncoding);
            if (BeanUtils.isNotNull(contentEncoding)) {
                httpClient.addRequestInterceptor(contentEncoding.getRequestInterceptor());
                httpClient.addResponseInterceptor(contentEncoding.getResponseInterceptor());
            }
        }
    }
}

