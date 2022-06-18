package com.rslakra.httpclient.rest;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 4/10/20 3:40 PM
 */
public final class RestClient extends RestBuilder {

    public RestClient() throws URISyntaxException {
        super();
    }

    /**
     * @param defaultURI
     * @throws URISyntaxException
     */
    public RestClient(Object defaultURI) throws URISyntaxException {
        super(defaultURI);
    }

    /**
     * @param defaultURI
     * @param defaultContentType
     * @throws URISyntaxException
     */
    public RestClient(Object defaultURI, Object defaultContentType) throws URISyntaxException {
        super(defaultURI, defaultContentType);
    }

    /**
     * @param args
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public Object get(final Map<String, ?> args) throws ClientProtocolException, IOException, URISyntaxException {
        return this.execute(new RequestDelegate(this, new HttpGet(), null, args, null));
    }

    /**
     * @param args
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object post(final Map<String, ?> args) throws URISyntaxException, ClientProtocolException, IOException {
        return this.execute(new RequestDelegate(this, new HttpPost(), null, args, null));
    }

    /**
     * @param args
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object put(final Map<String, ?> args) throws URISyntaxException, ClientProtocolException, IOException {
        return this.execute(new RequestDelegate(this, new HttpPut(), null, args, null));
    }

    /**
     * @param args
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object patch(final Map<String, ?> args) throws URISyntaxException, ClientProtocolException, IOException {
        return this.execute(new RequestDelegate(this, new HttpPatch(), null, args, null));
    }

    /**
     * @param args
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object head(final Map<String, ?> args) throws URISyntaxException, ClientProtocolException, IOException {
        return this.execute(new RequestDelegate(this, new HttpHead(), null, args, null));
    }

    /**
     * @param args
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object delete(final Map<String, ?> args) throws URISyntaxException, ClientProtocolException, IOException {
        return this.execute(new RequestDelegate(this, new HttpDelete(), null, args, null));
    }

    /**
     * @param args
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public Object options(final Map<String, ?> args) throws ClientProtocolException, IOException, URISyntaxException {
        return this.execute(new RequestDelegate(this, new HttpOptions(), null, args, null));
    }

    /**
     * @param response
     * @param data
     * @return
     * @throws ResponseParseException
     */
    protected HttpResponseDecorator defaultSuccessHandler(HttpResponseDecorator response, Object data)
        throws ResponseParseException {
        response.setData(super.defaultSuccessHandler(response, data));
        return response;
    }

    /**
     * @param response
     * @param data
     * @throws ResponseException
     */
    protected void defaultFailureHandler(HttpResponseDecorator response, Object data) throws ResponseException {
        response.setData(super.defaultSuccessHandler(response, data));
        throw new ResponseException(response);
    }
}
