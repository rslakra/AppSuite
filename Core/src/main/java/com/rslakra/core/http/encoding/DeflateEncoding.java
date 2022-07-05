package com.rslakra.core.http.encoding;

import com.rslakra.core.rest.ContentEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.DeflateDecompressingEntity;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:14 PM
 */
public class DeflateEncoding extends ContentEncoding {

    public DeflateEncoding() {
        super(Type.DEFLATE);
    }

    /**
     * @param rawEntity
     * @return
     */
    public HttpEntity wrapResponseEntity(HttpEntity rawEntity) {
        return new DeflateDecompressingEntity(rawEntity);
    }
}

