package com.rslakra.core.rest;

import com.rslakra.core.rest.ContentEncoding.RequestInterceptor;
import com.rslakra.core.rest.ContentEncoding.ResponseInterceptor;
import com.rslakra.core.rest.ContentEncoding.Type;
import com.rslakra.core.http.encoding.DeflateEncoding;
import com.rslakra.core.http.encoding.GZIPEncoding;
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
        contentEncodingMap.put(Type.GZIP.toString(), new GZIPEncoding());
        contentEncodingMap.put(Type.DEFLATE.toString(), new DeflateEncoding());
        return contentEncodingMap;
    }

    /**
     * TODO: FIX ME!
     *
     * @param client
     * @param contentEncodings
     */
    void setInterceptors(AbstractHttpClient client, Object... contentEncodings) {
        client.removeRequestInterceptorByClass(RequestInterceptor.class);
        client.removeResponseInterceptorByClass(ResponseInterceptor.class);
        for (Object object : contentEncodings) {
            final ContentEncoding contentEncoding = this.availableEncoders.get(object.toString());
            if (contentEncoding != null) {
                client.addRequestInterceptor(contentEncoding.getRequestInterceptor());
                client.addResponseInterceptor(contentEncoding.getResponseInterceptor());
            }
        }
    }
}

