package com.rslakra.httpclient.rest;

import org.apache.http.*;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:06 PM
 */
public abstract class ContentEncoding {

    private final EncodingType contentEncodingType;

    /**
     * @param contentEncodingType
     */
    public ContentEncoding(final EncodingType contentEncodingType) {
        this.contentEncodingType = contentEncodingType;
    }

    /**
     * @return
     */
    public String getContentEncoding() {
        return contentEncodingType.toString();
    }

    /**
     * @param httpEntity
     * @return
     */
    protected abstract HttpEntity wrapResponseEntity(HttpEntity httpEntity);

    /**
     * @return
     */
    public HttpRequestInterceptor getRequestInterceptor() {
        return new ContentEncoding.RequestInterceptor();
    }

    /**
     * @return
     */
    public HttpResponseInterceptor getResponseInterceptor() {
        return new ContentEncoding.ResponseInterceptor();
    }

    /**
     *
     */
    protected class ResponseInterceptor implements HttpResponseInterceptor {

        protected ResponseInterceptor() {
        }

        /**
         * @param response
         * @param context
         * @throws HttpException
         * @throws IOException
         */
        public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
            if (this.hasEncoding(response, ContentEncoding.this.getContentEncoding())) {
                response.setEntity(ContentEncoding.this.wrapResponseEntity(response.getEntity()));
            }
        }

        protected boolean hasEncoding(HttpResponse response, String encoding) {
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return false;
            } else {
                Header ceHeader = entity.getContentEncoding();
                if (ceHeader == null) {
                    return false;
                } else {
                    HeaderElement[] codecs = ceHeader.getElements();

                    for (int i = 0; i < codecs.length; ++i) {
                        if (encoding.equalsIgnoreCase(codecs[i].getName())) {
                            return true;
                        }
                    }

                    return false;
                }
            }
        }
    }

    /**
     *
     */
    protected class RequestInterceptor implements HttpRequestInterceptor {

        protected RequestInterceptor() {
        }

        /**
         * @param httpRequest
         * @param httpContext
         * @throws HttpException
         * @throws IOException
         */
        @Override
        public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
            //TODO FIXME!
//            HTTPUtils.setAcceptEncodingHeader(httpRequest, getContentEncoding());
        }
    }

    /**
     * EncodingType
     */
    public enum EncodingType {
        GZIP,
        COMPRESS,
        DEFLATE;
    }
}
