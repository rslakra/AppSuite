package com.rslakra.core.rest;

import com.rslakra.core.rest.ContentEncoding.Type;
import com.rslakra.core.http.ContentType;
import com.rslakra.core.http.HTTPUtils;
import com.rslakra.core.http.Method;
import com.rslakra.core.algos.map.ConcurrentDictionary;
import com.rslakra.core.algos.map.StringHashMap;
import org.apache.commons.collections4.Closure;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 4/10/20 3:40 PM
 */
public class RestBuilder {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(RestBuilder.class);
    public static final String COOKIE_DATE_PATTERNS = "rest.protocol.cookie-datepatterns";
    public static final String HTTP_ROUTE_DEFAULT_PROXY = "rest.route.default-proxy";
    private final List<String>
        cookieDataPatterns =
        Arrays.asList("EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd MMM yyyy HH:mm:ss z");
    private AuthConfig authConfig = new AuthConfig(this);
    private HttpClient httpClient;
    private UriBuilder defaultURI = null;
    private Object contentType;
    private Object requestContentType;
    private boolean autoAcceptHeader;
    private final Map<Object, Closure> responseHandlers;
    private ContentEncodingRegistry contentEncodingHandler;
    private final ConcurrentDictionary<String, String> defaultHeaders;

    /**
     * @throws URISyntaxException
     */
    public RestBuilder() throws URISyntaxException {
        this(null, ContentType.ANY);
        this.setContentEncoding(Type.GZIP, Type.DEFLATE);
    }

    /**
     * @param defaultUri
     * @throws URISyntaxException
     */
    public RestBuilder(final Object defaultUri) throws URISyntaxException {
        this(defaultUri, ContentType.ANY);
    }

    /**
     * @param defaultUri
     * @param defaultContentType
     * @throws URISyntaxException
     */
    public RestBuilder(final Object defaultUri, final Object defaultContentType) throws URISyntaxException {
        this.contentType = (Objects.isNull(defaultContentType) ? ContentType.ANY : defaultContentType);
        this.requestContentType = null;
        setAutoAcceptHeader(true);
        this.responseHandlers = new StringHashMap(this.buildDefaultResponseHandlers());
        this.contentEncodingHandler = new ContentEncodingRegistry();
        this.defaultHeaders = ConcurrentDictionary.newDictionary();
        this.setUri(defaultUri);
    }

    /**
     * @param args
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public Object get(final Map<String, ?> args) throws ClientProtocolException, IOException, URISyntaxException {
        return this.get(args, (Closure) null);
    }

    /**
     * @param args
     * @param responseClosure
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public Object get(final Map<String, ?> args, final Closure responseClosure)
        throws ClientProtocolException, IOException, URISyntaxException {
        RequestDelegate
            delegate =
            new RequestDelegate(this, new HttpGet(), getContentType(), getHeaders(), getResponseHandlers());
        delegate.setPropertiesFromMap(args);
        if (responseClosure != null) {
            delegate.getResponse().put(Status.SUCCESS, responseClosure);
        }

        return this.execute(delegate);
    }

    /**
     * @param args
     * @return
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     */
    public Object post(Map<String, ?> args) throws ClientProtocolException, URISyntaxException, IOException {
        return this.post(args, (Closure) null);
    }

    /**
     * @param args
     * @param responseClosure
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object post(final Map<String, ?> args, final Closure responseClosure)
        throws URISyntaxException, ClientProtocolException, IOException {
        RequestDelegate
            requestDelegate =
            new RequestDelegate(this, new HttpPost(), getContentType(), getHeaders(), getResponseHandlers());
//        requestDelegate.setRequestContentType(ContentType.URLENC.toString());
        requestDelegate.setPropertiesFromMap(args);
        if (responseClosure != null) {
            requestDelegate.getResponse().put(Status.SUCCESS.toString(), responseClosure);
        }

        return this.execute(requestDelegate);
    }

    /**
     * @param method
     * @param configClosure
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object request(final Method method, final Closure configClosure)
        throws ClientProtocolException, IOException {
        return this.doRequest(this.defaultURI.toURI(), method, getContentType(), configClosure);
    }

    /**
     * @param method
     * @param contentType
     * @param configClosure
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public Object request(final Method method, final Object contentType, final Closure configClosure)
        throws ClientProtocolException, IOException {
        return this.doRequest(this.defaultURI.toURI(), method, contentType, configClosure);
    }

    /**
     * @param uri
     * @param method
     * @param contentType
     * @param configClosure
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public Object request(final Object uri, final Method method, final Object contentType, final Closure configClosure)
        throws ClientProtocolException, IOException, URISyntaxException {
        return this.doRequest(UriBuilder.toUri(uri), method, contentType, configClosure);
    }

    /**
     * @param uri
     * @param method
     * @param contentType
     * @param configClosure
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    protected Object doRequest(final URI uri, final Method method, final Object contentType,
                               final Closure configClosure)
        throws ClientProtocolException, IOException {
        HttpRequestBase reqMethod;
        try {
            reqMethod = method.getRequestType().newInstance();
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }

        reqMethod.setURI(uri);
        RequestDelegate
            requestDelegate =
            new RequestDelegate(this, reqMethod, contentType, getHeaders(), getResponseHandlers());
        return this.execute(requestDelegate);
    }

    /**
     * @param requestDelegate
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    protected Object execute(final RestBuilder.RequestDelegate requestDelegate)
        throws ClientProtocolException, IOException {
        requestDelegate.encodeBody();
        final HttpRequestBase reqMethod = requestDelegate.getHttpRequestBase();
        final Object contentType = requestDelegate.getContentType();
        if (isAutoAcceptHeader()) {
            String acceptContentTypes = contentType.toString();
            if (contentType instanceof ContentType) {
                acceptContentTypes = ((ContentType) contentType).getAcceptHeader();
            }

            reqMethod.setHeader("Accept", acceptContentTypes);
        }

        reqMethod.setURI(requestDelegate.getUri().toURI());
        if (reqMethod.getURI() == null) {
            throw new IllegalStateException("Request URI cannot be null");
        } else {
            LOGGER.debug(reqMethod.getMethod() + " " + reqMethod.getURI());
            final Map<?, ?> headers = requestDelegate.getHeaders();
            Iterator<?> itr = headers.keySet().iterator();
            while (itr.hasNext()) {
                final Object key = itr.next();
                /* if key exists and value null, remove key otherwise add value. */
                if (key != null) {
                    final Object value = headers.get(key);
                    if (value == null) {
                        reqMethod.removeHeaders(key.toString());
                    } else {
                        reqMethod.setHeader(key.toString(), value.toString());
                    }
                }
            }

            return this.getHttpClient()
                .execute(reqMethod, new RestResponseHandler(requestDelegate), requestDelegate.getContext());
        }
    }

    /**
     *
     */
    private final class RestResponseHandler implements ResponseHandler<Object> {

        private final RestBuilder.RequestDelegate requestDelegate;
        private HttpResponseDecorator httpResponse;

        /**
         * @param requestDelegate
         */
        RestResponseHandler(final RestBuilder.RequestDelegate requestDelegate) {
            this.requestDelegate = requestDelegate;
        }

        /**
         * @param response
         * @return
         * @throws ClientProtocolException
         * @throws IOException
         */
        public Object handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
            httpResponse = new HttpResponseDecorator(response, requestDelegate.getContext(), (Object) null);
            boolean success = false;
            Object result = null;
            try {
                success = true;
                int status = httpResponse.getStatusLine().getStatusCode();
                Closure responseClosure = requestDelegate.findResponseHandler(status);
                LOGGER.debug("Response code: " + status + "; found handler: " + responseClosure);
                Object[] responseClosureArguments = null;
//                switch (responseClosure.getMaximumParameters()) {
//                    case 1:
//                        responseClosureArguments = new Object[]{httpResponse};
//                        break;
//                    case 2:
//                        HttpEntity httpEntity = httpResponse.getEntity();
//                        try {
//                            if (httpEntity != null && httpEntity.getContentLength() != 0L) {
//                                responseClosureArguments =
//                                    new Object[]{httpResponse,
//                                                 RestBuilder.this.parseResponse(httpResponse,
//                                                                                httpEntity.getContentType())};
//                                break;
//                            }
//
//                            responseClosureArguments = new Object[]{httpResponse, null};
//                            break;
//                        } catch (Exception var15) {
//                            Header h = httpEntity.getContentType();
//                            String respContentType = h != null ? h.getValue() : null;
//                            LOGGER
//                                .warn("Error parsing '" + respContentType + "' response", var15);
//                            throw new ResponseParseException(httpResponse, var15);
//                        }
//                    default:
//                        throw new IllegalArgumentException("Response closure must accept one or two parameters");
//                }
//
//                result = responseClosure.call(responseClosureArguments);
//                LOGGER.trace("response handler result: " + result);
                success = false;
            } finally {
                if (success) {
                    consumeContents(httpResponse);
                }
            }

            consumeContents(httpResponse);
            return result;
        }

        /**
         * @param httpResponse
         * @throws IOException
         */
        private final void consumeContents(final HttpResponseDecorator httpResponse) throws IOException {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                EntityUtils.consume(entity);
            }
        }
    }

    /**
     * @param httpResponse
     * @param contentType
     * @return
     * @throws ResponseException
     */
    protected Object parseResponse(HttpResponse httpResponse, Object contentType) throws ResponseException {
        if (httpResponse.getEntity() == null) {
            LOGGER.debug("Response contains no entity.  Parsed data is null.");
            return null;
        } else {
            String responseContentType = contentType.toString();
            try {
                if (ContentType.ANY.toString().equals(responseContentType)) {
                    responseContentType = HTTPUtils.getContentType(httpResponse);
                }
            } catch (RuntimeException var6) {
                LOGGER.warn("Could not parse content-type: " + var6.getMessage());
                responseContentType = ContentType.BINARY.toString();
            }

            Object parsedData = null;
//            Closure parser = this.parsers.getAt(responseContentType);
//            if (parser == null) {
//                LOGGER.warn("No parser found for content-type: " + responseContentType);
//            } else {
//                LOGGER.debug("Parsing response as: " + responseContentType);
//                parsedData = parser.call(resp);
//                if (parsedData == null) {
//                    LOGGER.warn("Parser returned null!");
//                } else {
//                    LOGGER.debug("Parsed data to instance of: " + parsedData.getClass());
//                }
//            }

            return parsedData;
        }
    }

    /**
     * @return
     */
    protected Map<Object, Closure> buildDefaultResponseHandlers() {
        final Map<Object, Closure> map = new StringHashMap();
//        map.put(Status.SUCCESS, new MethodClosure(this, "defaultSuccessHandler"));
//        map.put(Status.FAILURE, new MethodClosure(this, "defaultFailureHandler"));
        return map;
    }

    /**
     * @param response
     * @param parsedData
     * @return
     * @throws ResponseParseException
     */
    protected Object defaultSuccessHandler(HttpResponseDecorator response, Object parsedData)
        throws ResponseParseException {
//        try {
//            if (parsedData instanceof InputStream) {
//                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//                DefaultMethods.leftShift(buffer, (InputStream) parsedData);
//                parsedData = new ByteArrayInputStream(buffer.toByteArray());
//            } else if (parsedData instanceof Reader) {
//                StringWriter buffer = new StringWriter();
//                DefaultMethods.leftShift(buffer, (Reader) parsedData);
//                parsedData = new StringReader(buffer.toString());
//            } else if (parsedData instanceof Closeable) {
//                LOGGER.warn(
//                    "Parsed data is streaming, but will be accessible after the network connection is closed. Use at your own risk!");
//            }
//
//            return parsedData;
//        } catch (IOException ex) {
//            throw new ResponseParseException(response, ex);
//        }
        return null;
    }

    /**
     * @param response
     * @throws ResponseException
     */
    protected void defaultFailureHandler(HttpResponseDecorator response) throws ResponseException {
        throw new ResponseException(response);
    }

    /**
     * @return
     */
    public Map<?, Closure> getResponseHandlers() {
        return this.responseHandlers;
    }

    /**
     * @param contentType
     */
    public void setContentType(final Object contentType) {
        this.contentType = contentType;
    }

    /**
     * @return
     */
    public Object getContentType() {
        return this.contentType;
    }

    /**
     * @param autoAcceptHeader
     */
    public void setAutoAcceptHeader(final boolean autoAcceptHeader) {
        this.autoAcceptHeader = autoAcceptHeader;
    }

    /**
     * @return
     */
    public boolean isAutoAcceptHeader() {
        return this.autoAcceptHeader;
    }

    /**
     * @param encodings
     */
    public void setContentEncoding(final Object... encodings) {
        final HttpClient client = this.getHttpClient();
        if (client instanceof AbstractHttpClient) {
            this.contentEncodingHandler.setInterceptors((AbstractHttpClient) client, encodings);
        } else {
            throw new IllegalStateException("The HttpClient is not an AbstractHttpClient!");
        }
    }

    /**
     * @param uri
     * @throws URISyntaxException
     */
    public void setUri(final Object uri) throws URISyntaxException {
        this.defaultURI = uri != null ? new UriBuilder(UriBuilder.toUri(uri)) : null;
    }

    /**
     * @return
     */
    public Object getUri() {
        return this.defaultURI;
    }

    /**
     * @param headers
     */
    public void setHeaders(final ConcurrentDictionary<?, ?> headers) {
        getHeaders().clear();
        if (headers != null) {
            final Iterator<?> itr = headers.keySet().iterator();
            while (itr.hasNext()) {
                final Object key = itr.next();
                final Object value = headers.get(key);
                if (value != null) {
                    getHeaders().put(key.toString(), value.toString());
                }
            }

        }
    }

    /**
     * Returns the list of headers.
     *
     * @return
     */
    public ConcurrentDictionary<String, String> getHeaders() {
        return this.defaultHeaders;
    }

    /**
     * @return
     */
    public HttpClient getHttpClient() {
        if (this.httpClient == null) {
            HttpParams defaultParams = new BasicHttpParams();
            defaultParams.setParameter(COOKIE_DATE_PATTERNS, cookieDataPatterns);
            this.httpClient = this.createClient(defaultParams);
        }

        return this.httpClient;
    }

    /**
     * @param httpClient
     */
    public void setHttpClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @param params
     * @return
     */
    protected HttpClient createClient(HttpParams params) {
//        HttpClientBuilder.create()
//        return new HttpClientBuilder(params);
        return null;
    }

    /**
     * @return
     */
    public AuthConfig getAuthConfig() {
        return this.authConfig;
    }

    /**
     * @param authConfig
     */
    public void setAuthConfig(final AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    /**
     * @param cer
     */
    public void setContentEncodingRegistry(final ContentEncodingRegistry cer) {
        this.contentEncodingHandler = cer;
    }

    /**
     * @param host
     * @param port
     * @param scheme
     */
    public void setProxy(final String host, final int port, final String scheme) {
        this.getHttpClient().getParams().setParameter(HTTP_ROUTE_DEFAULT_PROXY, new HttpHost(host, port, scheme));
    }

    /**
     * @throws KeyManagementException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    public void ignoreSSLIssues() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException,
                                         KeyStoreException {
        TrustStrategy trustStrategy = new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        };

//        HttpClientBuilder.create().setSSLSocketFactory().build();
//        SSLConnectionSocketFactory.getSocketFactory().createLayeredSocket()
        SSLSocketFactory
            sslSocketFactory =
            new SSLSocketFactory(trustStrategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        this.getHttpClient().getConnectionManager().getSchemeRegistry()
            .register(new Scheme("https", 443, sslSocketFactory));
    }

    /**
     *
     */
    public void shutdown() {
        this.getHttpClient().getConnectionManager().shutdown();
    }

    /**
     *
     */
    protected class RequestDelegate {

        private final RestBuilder restBuilder;
        private HttpRequestBase httpRequestBase;
        private Object contentType;
        private Object requestContentType;
        private Map<Object, Closure> responseHandlers;
        private UriBuilder uri;
        private Map<Object, Object> headers;
        private HttpContextDecorator context;
        private Object body;

        /**
         * @param restBuilder
         * @param request
         * @param contentType
         * @param requestHeaders
         * @param responseHandlers
         */
        public RequestDelegate(final RestBuilder restBuilder, final HttpRequestBase request, final Object contentType,
                               final Map<?, ?> requestHeaders, final Map<?, Closure> responseHandlers) {
            this.restBuilder = restBuilder;
            this.responseHandlers = new StringHashMap();
            this.headers = new StringHashMap();
            this.context = new HttpContextDecorator();
            if (request == null) {
                throw new IllegalArgumentException("Internal error - HttpRequest instance cannot be null");
            } else {
                this.httpRequestBase = request;
                this.headers.putAll(requestHeaders);
                this.contentType = contentType;
                if (RestBuilder.this.requestContentType != null) {
                    this.requestContentType = RestBuilder.this.requestContentType.toString();
                }

                this.responseHandlers.putAll(responseHandlers);
                URI uri = request.getURI();
                if (uri != null) {
                    this.uri = new UriBuilder(uri);
                }
            }
        }

        /**
         * @param restBuilder
         * @param request
         * @param args
         * @param successHandler
         * @throws URISyntaxException
         */
        public RequestDelegate(final RestBuilder restBuilder, final HttpRequestBase request, final Map<String, ?> args,
                               final Closure successHandler)
            throws URISyntaxException {
            this(restBuilder, request, RestBuilder.this.getContentType(), RestBuilder.this.getHeaders(),
                 RestBuilder.this.getResponseHandlers());
            if (successHandler != null) {
                this.responseHandlers.put(Status.SUCCESS.toString(), successHandler);
            }

            this.setPropertiesFromMap(args);
        }

        /**
         * @return
         */
        public UriBuilder getUri() {
            return this.uri;
        }

        /**
         * @param uri
         * @throws URISyntaxException
         */
        public void setUri(Object uri) throws URISyntaxException {
            if (uri instanceof UriBuilder) {
                this.uri = (UriBuilder) uri;
            }

            this.uri = new UriBuilder(UriBuilder.toUri(uri));
        }

        /**
         * @return
         */
        protected HttpRequestBase getHttpRequestBase() {
            return this.httpRequestBase;
        }

        /**
         * @return
         */
        protected Object getContentType() {
            return this.contentType;
        }

        /**
         * @param contentType
         */
        protected void setContentType(final Object contentType) {
            this.contentType = (contentType == null ? RestBuilder.this.getContentType() : contentType);
        }

        /**
         * @return
         */
        protected Object getRequestContentType() {
            return this.requestContentType != null ? this.requestContentType : this.getContentType();
        }

        /**
         * @param contentType
         */
        protected void setRequestContentType(final Object contentType) {
            this.requestContentType = contentType;
        }

        /**
         * @param args
         * @throws URISyntaxException
         */
        protected void setPropertiesFromMap(final Map<String, ?> args) throws URISyntaxException {
            if (args != null) {
                if (args.containsKey("url")) {
                    throw new IllegalArgumentException("The 'url' parameter is deprecated; use 'uri' instead");
                } else {
                    Object uri = args.remove("uri");
                    if (uri == null) {
                        uri = RestBuilder.this.defaultURI;
                    }

                    if (uri == null) {
                        throw new IllegalStateException("Default URI is null, and no 'uri' parameter was given");
                    } else {
                        this.uri = new UriBuilder(UriBuilder.toUri(uri));
                        Map query = (Map) args.remove("params");
                        if (query != null) {
                            LOGGER.warn("'params' argument is deprecated; use 'query' instead.");
                            this.uri.setQuery(query);
                        }

                        String queryString = (String) args.remove("queryString");
                        if (queryString != null) {
                            this.uri.setRawQuery(queryString);
                        }

                        query = (Map) args.remove("query");
                        if (query != null) {
                            this.uri.addQueryParams(query);
                        }

                        Map headers = (Map) args.remove("headers");
                        if (headers != null) {
                            this.getHeaders().putAll(headers);
                        }

                        Object path = args.remove("path");
                        if (path != null) {
                            this.uri.setPath(path.toString());
                        }

                        Object contentType = args.remove("contentType");
                        if (contentType != null) {
                            this.setContentType(contentType);
                        }

                        contentType = args.remove("requestContentType");
                        if (contentType != null) {
                            this.setRequestContentType(contentType);
                        }

                        Object body = args.remove("body");
                        if (body != null) {
                            this.setBody(body);
                        }

                        if (args.size() > 0) {
                            String invalidArgs = "";
                            String k;
                            for (Iterator i$ = args.keySet().iterator(); i$.hasNext();
                                 invalidArgs = invalidArgs + k + ",") {
                                k = (String) i$.next();
                            }

                            throw new IllegalArgumentException("Unexpected keyword args: " + invalidArgs);
                        }
                    }
                }
            }
        }

        /**
         * @param newHeaders
         */
        public void setHeaders(Map<?, ?> newHeaders) {
            this.headers.putAll(newHeaders);
        }

        /**
         * @return
         */
        public Map<?, ?> getHeaders() {
            return this.headers;
        }

        /**
         * @param contentType
         * @param requestBody
         */
        public void send(final Object contentType, final Object requestBody) {
            this.setRequestContentType(contentType);
            this.setBody(requestBody);
        }

        /**
         * @param body
         */
        public void setBody(final Object body) {
            this.body = body;
        }

        /**
         *
         */
        public void encodeBody() {
            if (this.body != null) {
//                if (!(this.httpRequestBase instanceof HttpEntityEnclosingRequest)) {
//                    throw new IllegalArgumentException(
//                        "Cannot set a request body for a " + this.httpRequestBase.getMethod() + " method");
//                } else {
//                    Closure encoder = RestBuilder.this.encoders.getAt(this.getRequestContentType());
//                    if (encoder == null) {
//                        throw new IllegalArgumentException(
//                            "No encoder found for request content type " + this.getRequestContentType());
//                    } else {
//                        HttpEntity
//                            entity =
//                            (HttpEntity) (encoder.getMaximumParameters() == 2 ? encoder
//                                .call(new Object[]{this.body, this.getRequestContentType()}) : encoder.call(this.body));
//                        ((HttpEntityEnclosingRequest) this.httpRequestBase).setEntity(entity);
//                    }
//                }
            }
        }

        /**
         * @param statusCode
         * @return
         */
        protected Closure findResponseHandler(int statusCode) {
            Closure handler = this.getResponse().get(Integer.toString(statusCode));
            if (handler == null) {
                handler = this.getResponse().get(Status.find(statusCode).toString());
            }

            return handler;
        }

        /**
         * @return
         */
        public Map<Object, Closure> getResponse() {
            return this.responseHandlers;
        }

        /**
         * @return
         */
        public HttpContextDecorator getContext() {
            return this.context;
        }

        /**
         * @param context
         */
        public void setContext(HttpContext context) {
            this.context = new HttpContextDecorator(context);
        }
    }
}
