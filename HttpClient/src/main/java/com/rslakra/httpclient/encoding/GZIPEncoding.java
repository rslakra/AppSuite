package com.rslakra.httpclient.encoding;

import com.rslakra.httpclient.rest.ContentEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:14 PM
 */
public class GZIPEncoding extends ContentEncoding {

    public GZIPEncoding() {
        super(Type.GZIP);
    }

    /**
     * @param rawEntity
     * @return
     */
    public HttpEntity wrapResponseEntity(HttpEntity rawEntity) {
        return new GzipDecompressingEntity(rawEntity);
    }
}
