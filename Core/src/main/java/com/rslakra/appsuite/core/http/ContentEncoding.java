package com.rslakra.appsuite.core.http;

import com.rslakra.appsuite.core.BeanUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 6:06 PM
 */
public abstract class ContentEncoding {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentEncoding.class);
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
         * @param httpResponse
         * @param httpContext
         * @throws HttpException
         * @throws IOException
         */
        public void process(HttpResponse httpResponse, HttpContext httpContext) {
            if (this.hasEncoding(httpResponse, ContentEncoding.this.getContentEncoding())) {
                httpResponse.setEntity(ContentEncoding.this.wrapResponseEntity(httpResponse.getEntity()));
            }
        }

        /**
         * @param httpResponse
         * @param contentEncoding
         * @return
         */
        protected boolean hasEncoding(HttpResponse httpResponse, String contentEncoding) {
            LOGGER.debug("hasEncoding({}, {})", httpResponse, contentEncoding);
            HttpEntity httpEntity = httpResponse.getEntity();
            LOGGER.debug("httpEntity:{}", httpEntity);
            if (BeanUtils.isNotNull(httpEntity) && BeanUtils.isNotEmpty(contentEncoding)) {
                Header contentEncodingHeader = httpEntity.getContentEncoding();
                LOGGER.debug("contentEncodingHeader:{}", contentEncodingHeader);
                if (BeanUtils.isNotNull(contentEncodingHeader)) {
                    final HeaderElement[] contentEncodingCodecs = contentEncodingHeader.getElements();
                    if (BeanUtils.isNotEmpty(contentEncodingCodecs)) {
                        for (HeaderElement contentEncodingCodec : contentEncodingCodecs) {
                            if (contentEncoding.equalsIgnoreCase(contentEncodingCodec.getName())) {
                                return true;
                            }
                        }
                    }
                }
            }

            return false;
        }
    }

    /**
     *
     */
    protected class RequestInterceptor implements HttpRequestInterceptor {

        /**
         * @param httpRequest
         * @param httpContext
         * @throws HttpException
         * @throws IOException
         */
        @Override
        public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
            HTTPUtils.setAcceptEncodingHeader(httpRequest, getContentEncoding());
        }
    }

    /**
     * EncodingType
     */
    public enum EncodingType {
        GZIP,
        COMPRESS,
        DEFLATE;

        /**
         * @param encodingType
         * @return
         */
        public static EncodingType forName(final String encodingType) {
            return (BeanUtils.isNull(encodingType) ? null : EncodingType.valueOf(encodingType.toUpperCase()));
        }
    }
}
