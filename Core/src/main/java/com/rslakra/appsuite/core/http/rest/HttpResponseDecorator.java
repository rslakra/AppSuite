package com.rslakra.appsuite.core.http.rest;

import com.rslakra.appsuite.core.http.HTTPUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;

import java.util.Iterator;
import java.util.Locale;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 10:37 AM
 */
public class HttpResponseDecorator implements HttpResponse {

    private HttpResponseDecorator.HeadersDecorator headers;
    private HttpResponse response;
    private HttpContextDecorator context;
    private Object responseData;

    /**
     * @param response
     * @param parsedResponse
     */
    public HttpResponseDecorator(final HttpResponse response, final Object parsedResponse) {
        this(response, (HttpContextDecorator) null, parsedResponse);
    }

    /**
     * @param response
     * @param context
     * @param parsedResponse
     */
    public HttpResponseDecorator(HttpResponse response, HttpContextDecorator context, Object parsedResponse) {
        this.headers = null;
        this.response = response;
        this.context = context;
        this.responseData = parsedResponse;
    }

    /**
     * @return
     */
    public HttpResponseDecorator.HeadersDecorator getHeaders() {
        if (this.headers == null) {
            this.headers = new HttpResponseDecorator.HeadersDecorator();
        }

        return this.headers;
    }

    /**
     * @return
     */
    public boolean isSuccess() {
        return RestStatus.find(this.getStatus()) == RestStatus.SUCCESS;
    }

    /**
     * @return
     */
    public int getStatus() {
        return this.response.getStatusLine().getStatusCode();
    }

    /**
     * @return
     */
    public String getContentType() {
        return HTTPUtils.getContentType(this.response);
    }

    /**
     * @return
     */
    public Object getData() {
        return this.responseData;
    }

    /**
     * @param responseData
     */
    void setData(Object responseData) {
        this.responseData = responseData;
    }

    /**
     * @return
     */
    public HttpContextDecorator getContext() {
        return this.context;
    }

    /**
     * @return
     */
    public HttpEntity getEntity() {
        return this.response.getEntity();
    }

    /**
     * @return
     */
    public Locale getLocale() {
        return this.response.getLocale();
    }

    /**
     * @return
     */
    public StatusLine getStatusLine() {
        return this.response.getStatusLine();
    }

    /**
     * @param entity
     */
    public void setEntity(HttpEntity entity) {
        this.response.setEntity(entity);
    }

    /**
     * @param locale
     */
    public void setLocale(Locale locale) {
        this.response.setLocale(locale);
    }

    /**
     * @param reasonPhrase
     * @throws IllegalStateException
     */
    public void setReasonPhrase(String reasonPhrase) throws IllegalStateException {
        this.response.setReasonPhrase(reasonPhrase);
    }

    /**
     * @param statusCode
     * @throws IllegalStateException
     */
    public void setStatusCode(int statusCode) throws IllegalStateException {
        this.response.setStatusCode(statusCode);
    }

    /**
     * @param statusLine
     */
    public void setStatusLine(StatusLine statusLine) {
        this.response.setStatusLine(statusLine);
    }

    /**
     * @param protocolVersion
     * @param arg1
     */
    public void setStatusLine(ProtocolVersion protocolVersion, int arg1) {
        this.response.setStatusLine(protocolVersion, arg1);
    }

    /**
     * @param protocolVersion
     * @param arg1
     * @param arg2
     */
    public void setStatusLine(ProtocolVersion protocolVersion, int arg1, String arg2) {
        this.response.setStatusLine(protocolVersion, arg1, arg2);
    }

    /**
     * @param header
     */
    public void addHeader(Header header) {
        this.response.addHeader(header);
    }

    /**
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        this.response.addHeader(name, value);
    }

    /**
     * @param name
     * @return
     */
    public boolean containsHeader(String name) {
        return this.response.containsHeader(name);
    }

    /**
     * @return
     */
    public Header[] getAllHeaders() {
        return this.response.getAllHeaders();
    }

    /**
     * @param name
     * @return
     */
    public Header getFirstHeader(String name) {
        return this.response.getFirstHeader(name);
    }

    /**
     * @param name
     * @return
     */
    public Header[] getHeaders(String name) {
        return this.response.getHeaders(name);
    }

    /**
     * @param name
     * @return
     */
    public Header getLastHeader(String name) {
        return this.response.getLastHeader(name);
    }

    /**
     * @return
     */
    public HttpParams getParams() {
        return this.response.getParams();
    }

    public ProtocolVersion getProtocolVersion() {
        return this.response.getProtocolVersion();
    }

    public HeaderIterator headerIterator() {
        return this.response.headerIterator();
    }

    /**
     * @param name
     * @return
     */
    public HeaderIterator headerIterator(String name) {
        return this.response.headerIterator(name);
    }

    /**
     * @param header
     */
    public void removeHeader(Header header) {
        this.response.removeHeader(header);
    }

    /**
     * @param name
     */
    public void removeHeaders(String name) {
        this.response.removeHeaders(name);
    }

    public void setHeader(Header arg0) {
        this.response.setHeader(arg0);
    }

    public void setHeader(String arg0, String arg1) {
        this.response.setHeader(arg0, arg1);
    }

    public void setHeaders(Header[] arg0) {
        this.response.setHeaders(arg0);
    }

    public void setParams(HttpParams arg0) {
        this.response.setParams(arg0);
    }

    /**
     *
     */
    public final class HeadersDecorator implements Iterable<Header> {

        public HeadersDecorator() {
        }

        /**
         * @param name
         * @return
         */
        public Header getHeader(String name) {
            return HttpResponseDecorator.this.response.getFirstHeader(name);
        }

        /**
         * @param name
         * @return
         */
        protected String propertyMissing(final String name) {
            Header header = this.getHeader(name);
            return header != null ? header.getValue() : null;
        }

        /**
         * @return
         */
        public Iterator iterator() {
            return HttpResponseDecorator.this.response.headerIterator();
        }
    }
}
