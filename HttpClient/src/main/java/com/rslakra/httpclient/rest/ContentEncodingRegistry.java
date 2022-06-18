package com.rslakra.httpclient.rest;

import com.rslakra.httpclient.encoding.DeflateEncoding;
import com.rslakra.httpclient.encoding.GZIPEncoding;
import com.rslakra.httpclient.rest.ContentEncoding.EncodingType;
import org.apache.http.impl.client.AbstractHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:12 PM
 */
public class ContentEncodingRegistry {

    private Map<String, ContentEncoding> availableEncoders = this.getDefaultEncoders();

    public ContentEncodingRegistry() {
    }

    /**
     * @return
     */
    protected Map<String, ContentEncoding> getDefaultEncoders() {
        Map<String, ContentEncoding> contentEncodingMap = new HashMap();
        contentEncodingMap.put(EncodingType.GZIP.name(), new GZIPEncoding());
        contentEncodingMap.put(EncodingType.DEFLATE.name(), new DeflateEncoding());
        return contentEncodingMap;
    }

    /**
     * TODO: FIX ME!
     *
     * @param client
     * @param contentEncodings
     */
    void setInterceptors(AbstractHttpClient client, Object... contentEncodings) {
        client.removeRequestInterceptorByClass(ContentEncoding.RequestInterceptor.class);
        client.removeResponseInterceptorByClass(ContentEncoding.ResponseInterceptor.class);
        for (Object object : contentEncodings) {
            final ContentEncoding contentEncoding = this.availableEncoders.get(object.toString());
            if (contentEncoding != null) {
                client.addRequestInterceptor(contentEncoding.getRequestInterceptor());
                client.addResponseInterceptor(contentEncoding.getResponseInterceptor());
            }
        }
    }
}

