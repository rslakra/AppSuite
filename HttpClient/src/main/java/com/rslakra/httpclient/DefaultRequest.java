/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.httpclient;

import com.rslakra.core.CoreUtils;
import com.rslakra.core.IOUtils;
import com.rslakra.core.SecurityUtils;
import com.rslakra.httpclient.HTTPUtils.Pairs;

import javax.net.ServerSocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * The <code>DefaultRequest</code> is the base class for all HTTP(s) request
 * handling.
 *
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @created 2017-09-28 12:06:28 PM
 * @since 1.0.0
 */
public abstract class DefaultRequest /*implements HttpRequest*/ {

    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * ISO-8859-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /* USE_FULLY_QUALIFIED_HOSTNAME */
    private static final boolean USE_FULLY_QUALIFIED_HOSTNAME = false;

    /* KERNEL_VERSION */
    private static final String KERNEL_VERSION = System.getProperty("os.version");

    /**
     * mimeTypes
     */
    private static Map<String, String> mimeTypes;

    /* urlRedirects */
    private static final Map<String, String> urlRedirects = new HashMap<String, String>(3);

    /* headersIgnored */
    private static String[] headersIgnored;

    /* excludedHeaders */
    private static List<String> excludedHeaders;

    /* excludedParameters */
    private static List<String> excludedParameters;

    /* excludedMethods */
    private static List<String> excludedMethods;

    // Singleton object
    public DefaultRequest() {
        /* Enables cookies at application level. */
        initCookieManager();
    }

    /**
     * Initializes the cooky manager.
     */
    public void initCookieManager() {
        if (CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }

    /**
     * Returns the default 'User-Agent' value for these requests. This value is
     * generated in the same way, used by IPad and Android devices. This is the
     * mandatory property and must pass with each request.
     * <p>
     * NOTE: - Please don't make change in this user agent string. It is used to
     * send the client requests to server (like iPad and Android).
     *
     * @param mPlatform
     * @param mAppName
     * @param mAppBundleIdentifier
     * @param mClientVersion
     * @param mAppType
     * @return
     */
    public static String getUserAgentString(final String mPlatform, final String mAppName,
                                            final String mAppBundleIdentifier, final String mClientVersion, final String mAppType) {
        StringBuilder userAgentBuilder = new StringBuilder();

        /* These properties are mandatory for the server requests. */
        if (CoreUtils.isNullOrEmpty(mPlatform) || CoreUtils.isNullOrEmpty(mAppName)
                || CoreUtils.isNullOrEmpty(mAppBundleIdentifier) || CoreUtils.isNullOrEmpty(mAppType)) {
            throw new IllegalArgumentException("Invalid User-Agent Values!");
        }

        // prepare user-agent value
        userAgentBuilder.append("Platform=").append(mPlatform);
        userAgentBuilder.append(";App=").append(mAppName);
        userAgentBuilder.append(";ABI=").append(mAppBundleIdentifier);
        if (CoreUtils.isNotNullOrEmpty(mClientVersion)) {
            userAgentBuilder.append(";CVer=").append(mClientVersion);
        }

        return userAgentBuilder.toString();
    }

    /**
     * @param mPlatform
     * @param mAppName
     * @param mAppBundleIdentifier
     * @param mAppType
     * @return
     */
    public static String getUserAgentString(final String mPlatform, final String mAppName,
                                            final String mAppBundleIdentifier, final String mAppType) {
        return getUserAgentString(mPlatform, mAppName, mAppBundleIdentifier, null, mAppType);
    }

    /**
     * Returns the <code>URL</code> object for the specified
     * <code>urlString</code> .
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    public static URL newURL(String urlString) throws IOException {
        return new URL(urlString);
    }

    /**
     * Returns the <code>URL</code> object for the specified
     * <code>baseUrl</code> and <code>urlSuffix</code>.
     *
     * @param baseUrl
     * @param urlSuffix
     * @return
     * @throws IOException
     */
    public static URL newURL(String baseUrl, String urlSuffix) throws IOException {
        URL url = null;
        if (CoreUtils.isNullOrEmpty(urlSuffix)) {
            url = newURL(baseUrl);
        } else {
            url = new URL(newURL(baseUrl), urlSuffix);
        }

        return url;
    }

    /**
     * Closes the socket.
     *
     * @param socket
     */
    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Closes the socket.
     *
     * @param socket
     */
    public static void close(ServerSocket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param host
     * @param port
     * @return
     */
    public static boolean isPortAvailable(String host, int port) {
        Socket socket = null;
        boolean portAvailable = false;
        try {
            /*
             * If there is no exception, it means something is using the port
             * and has responded.
             */
            socket = new Socket(host, port);
        } catch (Exception ex) {
            portAvailable = true;
            ex.printStackTrace();
        } finally {
            close(socket);
        }

        return portAvailable;
    }

    /**
     * Returns true if the specified port is not used otherwise false.
     *
     * @param port
     * @return
     */
    public static boolean isPortAvailable(int port) {
        ServerSocket socket = null;
        boolean available = false;
        try {
            /*
             * If there is no exception, it means something is using the port
             * and has responded.
             */
            // socket = new ServerSocket(port);
            socket = ServerSocketFactory.getDefault().createServerSocket(port);
            socket.setReuseAddress(true);
        } catch (Exception ex) {
            available = true;
            ex.printStackTrace();
        } finally {
            close(socket);
        }

        return available;
    }

    /**
     * Returns the server URL based on the values provided. If the urlSuffix is
     * null or empty, the base URL of the server is returned.
     *
     * @param baseServerUrl
     * @param urlSuffix
     * @return
     */
    public static String getServerUrl(String baseServerUrl, String urlSuffix) {
        StringBuilder urlString = new StringBuilder();
        if (CoreUtils.isNotNullOrEmpty(baseServerUrl)) {
            urlString.append(baseServerUrl);
        }

        // append urlPrefix, if available.
        if (!CoreUtils.isNullOrEmpty(urlSuffix)) {
            if (urlSuffix.startsWith(IOUtils.SLASH)) {
                if (urlString.toString().endsWith(IOUtils.SLASH)) {
                    urlString.append(urlSuffix.substring(1));
                } else {
                    urlString.append(urlSuffix);
                }
            } else {
                if (urlString.toString().endsWith(IOUtils.SLASH)) {
                    urlString.append(urlSuffix);
                } else {
                    urlString.append(IOUtils.SLASH).append(urlSuffix);
                }
            }
        }

        return urlString.toString();
    }

    /**
     * Returns the host name extracted from the <code>urlString</code>.
     *
     * @return
     */
    public static String getHostName(String urlString) {
        // check in cache first
        String hostName = urlRedirects.get(urlString);
        if (CoreUtils.isNullOrEmpty(hostName)) {
            if (USE_FULLY_QUALIFIED_HOSTNAME) {
                hostName = HTTPUtils.getHostNameFromUrl(urlString);
            } else {
                // hostName =
                // HttpUtils.getHostNameFromUrlWithoutSubdomain(urlString);
            }

            // put in domain cache
            urlRedirects.put(urlString, hostName);
        }

        return hostName;
    }

    /**
     * Returns the MIME TYPE for the specified <code>extensionType</code>.
     *
     * @param extensionType
     * @return
     */
    public static String getMimeType(String extensionType) {
        if (mimeTypes == null) {
            mimeTypes = new HashMap<String, String>();
            mimeTypes.put("css", "text/css");
            mimeTypes.put("eot", "application/vnd.ms-fontobject");
            mimeTypes.put("gif", "image/gif");
            mimeTypes.put("html", "text/html");
            mimeTypes.put("htm", "text/html");
            mimeTypes.put("ico", "image/ico");
            mimeTypes.put("jpeg", "image/jpeg");
            mimeTypes.put("jpg", "image/jpeg");
            mimeTypes.put("js", "application/javascript");
            mimeTypes.put("json", "application/json");
            mimeTypes.put("m4a", "audio/mp4a-latm");
            mimeTypes.put("pdf", "application/pdf");
            mimeTypes.put("png", "image/png");
            mimeTypes.put("svg", "image/svg+xml");
            mimeTypes.put("ttf", "font/opentype");
            mimeTypes.put("woff", "font/woff");
            mimeTypes.put("woff2", "font/woff2");
        }

        return mimeTypes.get(extensionType);
    }

    /**
     * Returns true if the connection to the server is available and returns
     * some results otherwise false.
     *
     * @param baseServerUrl
     * @param urlSuffix
     * @return
     */
    public static boolean isServerReachable(String baseServerUrl, String urlSuffix) {
        boolean serverReachable = false;
        String urlString = HTTPUtils.getServerUrl(baseServerUrl, urlSuffix);

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = openHttpURLConnection(urlString, null);
            setDefaultConnectAndReadTimeouts(urlConnection);
            urlConnection.connect();
            serverReachable = (urlConnection != null
                    && (urlConnection.getResponseCode() == 200 || urlConnection.getContent() != null));
        } catch (Exception ex) {
            serverReachable = false;
        } finally {
            disconnect(urlConnection);
        }

        return serverReachable;
    }

    /**
     * Logs the <code>HttpURLConnection</code> object.
     *
     * @param urlConnection
     * @throws IOException
     */
    public static void logURLConnection(HttpURLConnection urlConnection) throws IOException {
        /* extract request parameters, if available. */
        if (urlConnection != null) {
            // LogUtils.i(DEBUG_KEY, "Header:" +
            // urlConnection.getHeaderFields());
            Map<String, List<String>> requestHeader = urlConnection.getHeaderFields();
            for (String key : requestHeader.keySet()) {
                List<String> listValue = requestHeader.get(key);
            }
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // //////////////////////// HttpServlet Methods ////////////////////////////
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Returns the request headers as the <code>Map<String, Object></code>
     * object after sorts based on the name.
     *
     * @param servletRequest
     * @return
     */
    public static Map<String, String> getRequestHeaders(HttpServletRequest servletRequest) {
        Map<String, String> requestHeaders = new TreeMap<String, String>();
        if (CoreUtils.isNotNull(servletRequest)) {
            try {
                /* extract request headers, if available. */
                @SuppressWarnings("unchecked")
                Enumeration<String> headerNames = servletRequest.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    String headerValue = servletRequest.getHeader(headerName);
                    requestHeaders.put(headerName, headerValue);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return requestHeaders;
    }

    /**
     * Sets the default headers to the specified response.
     *
     * @param servletResponse
     */
    public static void setDefaultHeaders(HttpServletResponse servletResponse) {
        servletResponse.setDateHeader(Header.EXPIRES.getName(), -1);
        servletResponse.setHeader(Header.PRAGMA.getName(), Header.PRAGMA_PUBLIC.getName());
        servletResponse.setHeader(Header.CACHE_CONTROL.getName(), Header.PRAGMA_NO_CACHE.getName());
    }

    /**
     * Prints the servletRequest.
     *
     * @param servletRequest
     * @throws IOException
     */
    public static void logServletRequest(HttpServletRequest servletRequest) throws IOException {
        /* extract request parameters, if available. */
        if (servletRequest != null) {
            for (Object key : servletRequest.getParameterMap().keySet()) {
                String value = servletRequest.getParameter(key.toString());
            }
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    // ///////////// HttpURLConnection/HttpsURLConnection Methods //////////////
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Returns the <code>Proxy</code> object for the specified
     * <code>proxyHost</code> and <code>proxyPort</code>.
     *
     * @param proxyHost
     * @param proxyPort
     * @return
     * @throws IOException
     */
    public static Proxy getProxy(String proxyHost, int proxyPort) throws IOException {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    }

    /**
     * Returns the <code>HttpURLConnection</code> object for the specified
     * <code>url</code>. if the <code>proxy</code> is available the
     * <code>proxy</code> is used for the request.
     *
     * @param url
     * @param proxy
     * @return
     * @throws IOException
     */
    public static HttpURLConnection openHttpURLConnection(URL url, Proxy proxy) throws IOException {
        return (CoreUtils.isNotNull(url)
                ? (HttpURLConnection) (CoreUtils.isNotNull(proxy) ? url.openConnection(proxy) : url.openConnection())
                : null);
    }

    /**
     * Returns the <code>HttpURLConnection</code> object for the specified
     * <code>url</code>.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection openHttpURLConnection(URL url) throws IOException {
        return (CoreUtils.isNotNull(url) ? (HttpURLConnection) url.openConnection() : null);
    }

    /**
     * Returns the <code>HttpURLConnection</code> object for the specified
     * <code>urlString</code>.
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    public static HttpURLConnection openHttpURLConnection(String urlString) throws IOException {
        return openHttpURLConnection(newURL(urlString));
    }

    /**
     * Returns the <code>HttpURLConnection</code> object for the specified
     * <code>baseUrl</code> and <code>urlSuffix</code>.
     *
     * @param baseUrl
     * @param urlSuffix
     * @return
     * @throws IOException
     */
    public static HttpURLConnection openHttpURLConnection(String baseUrl, String urlSuffix) throws IOException {
        return openHttpURLConnection(newURL(baseUrl, urlSuffix));
    }

    /**
     * Returns the <code>HttpsURLConnection</code> object for the specified
     * <code>url</code>. if the <code>proxy</code> is available the
     * <code>proxy</code> is used for the request.
     *
     * @param url
     * @param proxy
     * @return
     * @throws IOException
     */
    public static HttpsURLConnection openHttpsURLConnection(URL url, Proxy proxy) throws IOException {
        return (CoreUtils.isNotNull(url) && url.getProtocol().equals("https")
                ? (HttpsURLConnection) (CoreUtils.isNotNull(proxy) ? url.openConnection(proxy) : url.openConnection())
                : null);
    }

    /**
     * Returns the <code>HttpsURLConnection</code> object for the specified
     * <code>url</code>.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpsURLConnection openHttpsURLConnection(URL url) throws IOException {
        return openHttpsURLConnection(url, null);
    }

    /**
     * Returns the <code>HttpsURLConnection</code> object for the specified
     * <code>urlString</code>.
     *
     * @param urlString
     * @return
     * @throws IOException
     */
    public static HttpsURLConnection openHttpsURLConnection(String urlString) throws IOException {
        return openHttpsURLConnection(newURL(urlString));
    }

    /**
     * Returns the <code>HttpsURLConnection</code> object for the specified
     * <code>baseUrl</code> and <code>urlSuffix</code>.
     *
     * @param baseUrl
     * @param urlSuffix
     * @return
     * @throws IOException
     */
    public static HttpsURLConnection openHttpsURLConnection(String baseUrl, String urlSuffix) throws IOException {
        return openHttpsURLConnection(newURL(baseUrl, urlSuffix));
    }

    /**
     * Closes the <code>HttpURLConnection</code> connection.
     *
     * @param urlConnection
     */
    public static void disconnect(HttpURLConnection urlConnection) {
        if (CoreUtils.isNotNull(urlConnection)) {
            try {
                // Crucial, according to the documentation.
                urlConnection.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * The User-Agent of the connection to be set.
     * <p>
     * For Example: <code>
     * getUserAgentString(AuthConstants.APP_PLATFORM, AuthConstants.APP_NAME, AuthConstants.APP_BUNDLE_IDENTIFIER, AuthConstants.APP_TYPE)
     * </code>
     *
     * @param urlConnection
     * @param userAgent
     */
    public static void setUserAgent(HttpURLConnection urlConnection, final String userAgent) {
        if (CoreUtils.isNotNull(urlConnection) && CoreUtils.isNotNullOrEmpty(userAgent)) {
            // user-agent (default string generated for Android)
            urlConnection.addRequestProperty(Header.USER_AGENT.getName(), userAgent);
        }
    }

    /**
     * Sets the default properties of the specified <code>urlConnection</code>
     * object.
     *
     * @param urlConnection
     * @param timeoutInMillis
     * @param readTimeoutInMillis
     * @throws IOException
     */
    public static void setConnectAndReadTimeouts(HttpURLConnection urlConnection, int timeoutInMillis,
                                                 int readTimeoutInMillis) throws IOException {
        if (CoreUtils.isNotNull(urlConnection)) {
            urlConnection.setConnectTimeout(timeoutInMillis);
            urlConnection.setReadTimeout(readTimeoutInMillis);
        }
    }

    /**
     * Sets the default properties of the specified <code>urlConnection</code>
     * object.
     *
     * @param urlConnection
     * @throws IOException
     */
    public static void setDefaultConnectAndReadTimeouts(HttpURLConnection urlConnection) throws IOException {
        setConnectAndReadTimeouts(urlConnection, Header.connectionTimeoutInMillis(), Header.readTimeoutInMillis());
    }

    /**
     * Sets the <code>doInput</code> and <code>doOutput</code> properties of the
     * given <code>HttpURLConnection</code> object.
     *
     * @param urlConnection
     * @param doInput
     * @param doOutput
     * @throws IOException
     */
    public static void setDoInputAndDoOutput(final HttpURLConnection urlConnection, final boolean doInput,
                                             final boolean doOutput) throws IOException {
        if (CoreUtils.isNotNull(urlConnection)) {
            /*
             * Sets the flag indicating whether this URLConnection allows input.
             * It cannot be set after the connection is established.
             */
            urlConnection.setDoInput(doInput);
            urlConnection.setDoOutput(doOutput);
        }
    }

    /**
     * Sets the <code>useCaches</code> and <code>defaultUseCaches</code>
     * properties of the given <code>HttpURLConnection</code> object.
     *
     * @param urlConnection
     * @param useCaches
     * @param defaultUseCaches
     * @throws IOException
     */
    public static void setUseCaches(final HttpURLConnection urlConnection, final boolean useCaches,
                                    final boolean defaultUseCaches) throws IOException {
        if (CoreUtils.isNotNull(urlConnection)) {
            /*
             * Sets the flag indicating whether this connection allows to use
             * caches or not. This method can only be called prior to the
             * connection establishment.
             */
            urlConnection.setUseCaches(useCaches);
            urlConnection.setDefaultUseCaches(defaultUseCaches);
        }
    }

    /**
     * Sets the <code>useCaches</code> and <code>defaultUseCaches</code>
     * properties to be false of the given <code>HttpURLConnection</code>
     * object.
     *
     * @param urlConnection
     * @throws IOException
     */
    public static void setDefaultUseCaches(final HttpURLConnection urlConnection) throws IOException {
        setUseCaches(urlConnection, false, false);
    }

    /**
     * Sets the <code>requestMethod</code> property of the given
     * <code>HttpURLConnection</code> object.
     *
     * @param urlConnection
     * @param requestMethod
     * @throws IOException
     */
    public static void setRequestMethod(final HttpURLConnection urlConnection, final String requestMethod)
            throws IOException {
        if (CoreUtils.isNotNull(urlConnection)) {
            /*
             * Sets the flag indicating whether this URLConnection allows input.
             * It cannot be set after the connection is established.
             */
            urlConnection.setDoInput(true);

            /* request method (i.e GET/POST/PUT etc) */
            if (CoreUtils.isNotNullOrEmpty(requestMethod)) {
                urlConnection.setRequestMethod(requestMethod);
                urlConnection.setDoOutput(Method.POST == Method.valueOf(requestMethod));
            } else {
                urlConnection.setRequestMethod(Method.POST.getName());
                urlConnection.setDoOutput(true);
            }
        }
    }

    /**
     * Adds the given <code>fieldName</code> property to the request header of
     * the given <code>HttpURLConnection</code> object. Existing properties with
     * the same name will not be overwritten by this method.
     *
     * @param urlConnection
     * @param fieldName
     * @param fieldValue
     * @throws IOException
     */
    public static void addHeader(final HttpURLConnection urlConnection, final String fieldName, final String fieldValue)
            throws IOException {
        if (CoreUtils.isNotNull(urlConnection) && CoreUtils.isNotNullOrEmpty(fieldName)) {
            urlConnection.addRequestProperty(fieldName, fieldValue);
        }
    }

    /**
     * Adds the given <code>fieldName</code> property to the request header of
     * the given <code>HttpURLConnection</code> object. Existing properties with
     * the same name will be overwritten by this method.
     *
     * @param urlConnection
     * @param fieldName
     * @param fieldValue
     * @throws IOException
     */
    public static void setHeader(final HttpURLConnection urlConnection, final String fieldName, final String fieldValue)
            throws IOException {
        if (CoreUtils.isNotNull(urlConnection) && CoreUtils.isNotNullOrEmpty(fieldName)) {
            urlConnection.setRequestProperty(fieldName, fieldValue);
        }
    }

    /**
     * Sets the default properties of the given <code>HttpURLConnection</code>
     * object.
     *
     * @param urlConnection
     * @param requestMethod
     * @throws IOException
     */
    public static void setConnectionDefaultProperties(final HttpURLConnection urlConnection, final String requestMethod)
            throws IOException {
        if (CoreUtils.isNotNull(urlConnection)) {
            // set connection timeout properties.
            setDefaultUseCaches(urlConnection);
            setDefaultConnectAndReadTimeouts(urlConnection);
            // setUserAgent(urlConnection,
            // getUserAgentString(AuthConstants.APP_PLATFORM,
            // AuthConstants.APP_NAME, AuthConstants.APP_BUNDLE_IDENTIFIER,
            // AuthConstants.APP_TYPE));
            setRequestMethod(urlConnection, requestMethod);

            // add device-id in request.
            addHeader(urlConnection, "deviceId", com.rslakra.core.SecurityUtils.uniqueDeviceIdString());

            // other default properties
            setHeader(urlConnection, Header.ACCEPT_LANGUAGE.getName(), Locale.getDefault().getLanguage());
        }
    }

    /**
     * Returns the <code>mapCookies</code> as string.
     *
     * @param mapCookies
     */
    public static String toCookyString(Map<String, String> mapCookies) {
        String cookies = null;
        if (!CoreUtils.isNullOrEmpty(mapCookies)) {
            /** Add Cookies. */
            StringBuilder cookyBuilder = new StringBuilder();
            for (String key : mapCookies.keySet()) {
                String value = mapCookies.get(key);
                if (CoreUtils.isNotNullOrEmpty(value)) {
                    if (HTTPUtils.equals(Header.COOKIE, key)) {
                        cookyBuilder.append(value).append(";");
                    } else {
                        // value = SecurityUtils.encodeWithURLEncoder(value,
                        // UTF_8);
                        cookyBuilder.append(key).append("=").append(value).append(";");
                    }
                }
            }

            if (!CoreUtils.isNullOrEmpty(cookyBuilder)) {
                cookies = cookyBuilder.toString();
                cookyBuilder = null;
            }
        }

        return cookies;
    }

    /**
     * Returns the <code>Cooky</code> string.
     *
     * @param mapCookies
     * @return
     */
    public static Map<String, String> mergeCookies(final Map<String, String> mapCookies) {
        Map<String, String> mergedCookies = new HashMap<String, String>();
        if (!CoreUtils.isNullOrEmpty(mapCookies)) {
            /** Merge Cookies. */
            for (String key : mapCookies.keySet()) {
                String value = mapCookies.get(key);
                if (CoreUtils.isNotNullOrEmpty(value)) {
                    if (HTTPUtils.equals(Header.COOKIE, key)) {
                        Map<String, String> oldCookies = extractCookies(value);
                        if (!CoreUtils.isNullOrEmpty(oldCookies)) {
                            mergedCookies.putAll(oldCookies);
                        }
                    } else {
                        mergedCookies.put(key, value);
                    }
                }
            }
        }

        return mergedCookies;
    }

    /**
     * The <code>cookies</code> to be set to the specified
     * <code>urlConnection</code>.
     *
     * @param urlConnection
     * @param cookies
     */
    public static void setRequestCookies(HttpURLConnection urlConnection, String cookies) {
        if (CoreUtils.isNotNull(urlConnection) && CoreUtils.isNotNullOrEmpty(cookies)) {
            urlConnection.setRequestProperty(Header.COOKIE.getName(), cookies);
        }
    }

    /**
     * The <code>mapCookies</code> to be set to the specified
     * <code>urlConnection</code>.
     *
     * @param urlConnection
     * @param mapCookies
     */
    public static void setRequestCookies(HttpURLConnection urlConnection, Map<String, String> mapCookies) {
        setRequestCookies(urlConnection, toCookyString(mapCookies));
    }

    /**
     * Converts the <code>requestParameters</code> into
     * <code>urlQueryString</code>.
     *
     * @param requestParameters
     * @return
     */
    public static String toUrlQueryString(Map<String, Object> requestParameters) {
        String urlQueryString = null;
        if (!CoreUtils.isNullOrEmpty(requestParameters)) {
            StringBuilder aueryString = new StringBuilder();
            boolean firstParam = true;
            for (String key : requestParameters.keySet()) {
                if (firstParam) {
                    firstParam = false;
                } else {
                    aueryString.append("&");
                }

                String value = String.valueOf(requestParameters.get(key));
                value = com.rslakra.core.SecurityUtils.encodeWithURLEncoder(value, UTF_8);
                aueryString.append(key).append("=").append(value);
            }

            urlQueryString = aueryString.toString();
            /* mark available for garbage-collection. */
            aueryString = null;
        }

        return urlQueryString;
    }

    /**
     * Returns true if the request is GET otherwise false.
     *
     * @param urlConnection
     * @return
     */
    public static boolean isGetRequest(HttpURLConnection urlConnection) {
        return Method.isGetRequest(urlConnection.getRequestMethod());
    }

    /**
     * Adds the specified <code>queryString</code> to the specified
     * <code>urlConnection</code>.
     *
     * @param urlConnection
     * @param queryString
     */
    public static void setQueryString(HttpURLConnection urlConnection, final String queryString) throws IOException {
        if (CoreUtils.isNotNull(urlConnection) && !isGetRequest(urlConnection)
                && CoreUtils.isNotNullOrEmpty(queryString)) {
            IOUtils.writeBytes(queryString.getBytes(), urlConnection.getOutputStream(), true);
        }
    }

    /**
     * Adds the specified <code>requestParameters</code> to the specified
     * <code>urlConnection</code>.
     *
     * @param urlConnection
     * @param requestParameters
     */
    public static void setRequestParameters(HttpURLConnection urlConnection,
                                            final Map<String, Object> requestParameters) throws IOException {
        setQueryString(urlConnection, toUrlQueryString(requestParameters));
    }

    /**
     * Converts the <code>encodedParameters</code> string into
     * <code>Map<String, String></code>.
     *
     * @param encodedParameters
     * @return
     */
    public static Map<String, Object> toRequestParameters(String encodedParameters) {
        Map<String, Object> requestParameters = new LinkedHashMap<String, Object>();
        if (CoreUtils.isNotNullOrEmpty(encodedParameters)) {
            String decodedParameters = com.rslakra.core.SecurityUtils.decodeWithURLDecoder(encodedParameters);
            String[] paramTokens = decodedParameters.split("&");
            if (!CoreUtils.isNullOrEmpty(paramTokens)) {
                for (int i = 0; i < paramTokens.length; i++) {
                    String[] tokens = paramTokens[i].split("=");
                    if (!CoreUtils.isNullOrEmpty(tokens) && tokens.length > 1) {
                        requestParameters.put(tokens[0], tokens[1]);
                    }
                }
            }
        }

        return requestParameters;
    }

    /**
     * The <code>requestHeaders</code> to be set to the specified
     * <code>urlConnection</code>.
     *
     * @param urlConnection
     * @param requestHeaders
     */
    public static void setRequestHeaders(HttpURLConnection urlConnection, Map<String, String> requestHeaders) {
        if (CoreUtils.isNotNull(urlConnection) && !CoreUtils.isNullOrEmpty(requestHeaders)) {
            for (String headerKey : requestHeaders.keySet()) {
                String headerValue = requestHeaders.get(headerKey);
                if (CoreUtils.isNotNullOrEmpty(headerValue)) {
                    // headerValue =
                    // SecurityUtils.encodeToBase64String(headerValue);
                    urlConnection.setRequestProperty(headerKey, headerValue);
                }
            }
        }
    }

    /**
     * Adds the request headers extracted from the request parameters and remove
     * those from parameters.
     *
     * @param urlConnection
     * @param requestParameters
     */
    public static void addRequestHeadersFromParameters(HttpURLConnection urlConnection,
                                                       Map<String, Object> requestParameters) {
        if (CoreUtils.isNotNull(urlConnection) && !CoreUtils.isNullOrEmpty(requestParameters)) {
            // add CONTENT_TYPE in headers, if available
            String contentType = HTTPUtils.getValueForKeyAsString(requestParameters, Header.CONTENT_TYPE.getName());
            if (CoreUtils.isNotNullOrEmpty(contentType)) {
                urlConnection.addRequestProperty(Header.CONTENT_TYPE.getName(), contentType);
                requestParameters.remove(Header.CONTENT_TYPE);
            }

            // // add UNIQUE_RESPONSE_HASH in headers
            // String requestHashCode =
            // getValueForKeyAsString(requestParameters,
            // MeetXConstants.CLIENT_REQUEST_HASH_CODE);
            // if(CoreUtils.isNotNullOrEmpty(requestHashCode)) {
            // String responseHashCode =
            // getValueForKeyAsString(requestParameters,
            // MeetXConstants.UNIQUE_RESPONSE_HASH);
            // if(CoreUtils.isNotNullOrEmpty(responseHashCode)) {
            // urlConnection.addRequestProperty(MeetXConstants.UNIQUE_RESPONSE_HASH,
            // responseHashCode);
            // requestParameters.remove(MeetXConstants.UNIQUE_RESPONSE_HASH);
            // }
            // }
        }
    }

    /**
     * Executes the <code>httpMethod</code> request of the specified
     * <code>urlString</code> with <code>requestHeaders</code> and
     * <code>requestParameters</code> using <code>HttpURLConnection</code>. The
     * <code>OperationResponse</code> object is returned as response which
     * contains the data/error, if any, including response headers information.
     *
     * @param urlString
     * @param httpMethod
     * @param requestHeaders
     * @param requestParameters
     * @param closeStream
     * @return
     */
    public static HttpResponse executeRequest(final String urlString, final Method httpMethod,
                                              final Map<String, String> requestHeaders, final Map<String, Object> requestParameters,
                                              final boolean closeStream) {
        long startTime = System.currentTimeMillis();
        HttpResponse httpResponse = new HttpResponse();
        HttpURLConnection urlConnection = null;

        try {
            if (CoreUtils.isNullOrEmpty(urlString)) {
                throw new IllegalArgumentException("Server URL must provide!");
            }

            // open connection and set default header values.
            if (Method.isGetRequest(httpMethod)) {
                StringBuilder requestBuilder = new StringBuilder(urlString);
                String queryString = toUrlQueryString(requestParameters);
                if (CoreUtils.isNotNullOrEmpty(queryString)) {
                    requestBuilder.append("?").append(queryString);
                }
                urlConnection = HTTPUtils.openHttpURLConnection(requestBuilder.toString());
            } else {
                urlConnection = HTTPUtils.openHttpURLConnection(urlString);
            }

            setConnectionDefaultProperties(urlConnection, httpMethod.getName());

            // add default cookies, if any
            String urlCookies = null;
            if (CoreUtils.isNotNullOrEmpty(urlCookies)) {
                if (requestHeaders.containsKey(Header.COOKIE)) {
                    String requestCookies = requestHeaders.get(Header.COOKIE);
                    if (CoreUtils.isNotNullOrEmpty(requestCookies)) {
                        urlCookies += ";" + requestCookies;
                    }
                } else {
                    requestHeaders.put(Header.COOKIE.getName(), urlCookies);
                }
            }

            // add request header for the request.
            setRequestHeaders(urlConnection, requestHeaders);

            // add content response header for these requests.
            addRequestHeadersFromParameters(urlConnection, requestParameters);

            // add request headers in response.
            httpResponse.setRequestHeaders(urlConnection.getRequestProperties());

            // encode parameters, if any
            setRequestParameters(urlConnection, requestParameters);

            // Connect and get the response.
            urlConnection.connect();
            httpResponse.setResponseCode(urlConnection.getResponseCode());
            httpResponse.setResponseHeaders(urlConnection.getHeaderFields());
            if (httpResponse.getResponseCode() == 200) {
                byte[] dataBytes = IOUtils.readBytes(urlConnection.getInputStream(), closeStream);
                httpResponse.setDataBytes(dataBytes);
                // Final cleanup:
                dataBytes = null;
            }
        } catch (Throwable throwable) {
            httpResponse.setError(throwable);
        } finally {
            disconnect(urlConnection);
            long diff = System.currentTimeMillis() - startTime;
        }

        return httpResponse;
    }

    /**
     * Executes the <code>httpMethod</code> request of the specified
     * <code>urlString</code> with <code>requestParameters</code> using
     * <code>HttpURLConnection</code>. The <code>OperationResponse</code> object
     * is returned as response which contains the data/error, if any, including
     * response headers information.
     *
     * @param urlString
     * @param httpMethod
     * @param requestParameters
     * @param closeStream
     * @return
     */
    public static HttpResponse executeRequest(final String urlString, final Method httpMethod,
                                              final Map<String, Object> requestParameters, final boolean closeStream) {
        return executeRequest(urlString, httpMethod, null, requestParameters, closeStream);
    }

    /**
     * Executes the GET request of the specified <code>urlString</code> with
     * <code>requestHeaders</code> and <code>requestParameters</code> using
     * <code>HttpURLConnection</code>. The <code>OperationResponse</code> object
     * is returned as response which contains the data/error, if any, including
     * response headers information.
     *
     * @param urlString
     * @param requestHeaders
     * @param requestParameters
     * @param closeStream
     * @return
     */
    public static HttpResponse executeGetRequest(final String urlString, final Map<String, String> requestHeaders,
                                                 final Map<String, Object> requestParameters, final boolean closeStream) {
        return executeRequest(urlString, Method.GET, requestHeaders, requestParameters, closeStream);
    }

    /**
     * Executes the GET request of the specified <code>urlString</code> with
     * <code>requestHeaders</code> and <code>requestParameters</code> using
     * <code>HttpURLConnection</code>. The <code>OperationResponse</code> object
     * is returned as response which contains the data/error, if any, including
     * response headers information.
     *
     * @param urlString
     * @param requestParameters
     * @param closeStream
     * @return
     */
    public static HttpResponse executeGetRequest(final String urlString, final Map<String, Object> requestParameters,
                                                 final boolean closeStream) {
        return executeGetRequest(urlString, null, requestParameters, closeStream);
    }

    /**
     * Executes the POST request of the specified <code>urlString</code> with
     * <code>requestHeaders</code> and <code>requestParameters</code> using
     * <code>HttpURLConnection</code>. The <code>OperationResponse</code> object
     * is returned as response which contains the data/error, if any, including
     * response headers information.
     *
     * @param urlString
     * @param requestHeaders
     * @param requestParameters
     * @param closeStream
     * @return
     */
    public static HttpResponse executePostRequest(final String urlString, final Map<String, String> requestHeaders,
                                                  final Map<String, Object> requestParameters, final boolean closeStream) {
        return executeRequest(urlString, Method.POST, requestHeaders, requestParameters, closeStream);
    }

    /**
     * Executes the POST request of the specified <code>urlString</code> with
     * <code>requestHeaders</code> and <code>requestParameters</code> using
     * <code>HttpURLConnection</code>. The <code>OperationResponse</code> object
     * is returned as response which contains the data/error, if any, including
     * response headers information.
     *
     * @param urlString
     * @param requestParameters
     * @param closeStream
     * @return
     */
    public static HttpResponse executePostRequest(final String urlString, final Map<String, Object> requestParameters,
                                                  final boolean closeStream) {
        return executePostRequest(urlString, null, requestParameters, closeStream);
    }

    /**
     * Returns the value of the specified key from the headers. It might be this
     * method throw ClassCastException, if the return value is different than
     * string.
     *
     * @param headers
     * @param key
     * @return
     */
    public static String getHeader(Map<String, List<String>> headers, String key) {
        String value = null;
        if (!CoreUtils.isNullOrEmpty(headers)) {
            List<String> headerValues = headers.get(key);
            // LogUtils.d(DEBUG_KEY, "headerValues:" + headerValues);
            if (!CoreUtils.isNullOrEmpty(headerValues)) {
                value = headerValues.get(0);
            }
        }

        return value;
    }

    /**
     * Returns the headers of the specified operationResult.
     *
     * @param httpResponse
     * @return
     */
    public static Map<String, List<String>> getHeaders(HttpResponse httpResponse) {
        return (CoreUtils.isNull(httpResponse) ? null : httpResponse.getResponseHeaders());
    }

    /**
     * Returns the response type.
     *
     * @param headers
     * @return
     */
    public static String getMimeType(Map<String, List<String>> headers) {
        String mimeType = null;
        if (!CoreUtils.isNullOrEmpty(headers)) {
            mimeType = headers.get(Header.CONTENT_TYPE).get(0);
            if (mimeType.indexOf(";") != -1) {
                mimeType = mimeType.substring(0, mimeType.indexOf(";")).trim();
            }
        }

        return mimeType;
    }

    /**
     * Returns the key/value pair of extracted cookies from the
     * <code>stringCookies</code> headers.
     *
     * @param stringCookies
     * @return
     */
    public static Map<String, String> extractCookies(String stringCookies) {
        Map<String, String> mapCookies = null;
        if (CoreUtils.isNotNullOrEmpty(stringCookies)) {
            mapCookies = new HashMap<String, String>();
            String[] cookies = stringCookies.split(";");
            for (String cookie : cookies) {
                Pairs<String, String> keyValuePair = Pairs.newPair(cookie);
                if (keyValuePair != null) {
                    mapCookies.put(keyValuePair.getKey(), keyValuePair.getValue());
                }
            }
        }

        return mapCookies;
    }

    /**
     * Returns the cookies extracted from the <code>responseHeaders</code>
     * headers.
     *
     * @param responseHeaders
     * @return
     */
    public static Map<String, String> extractCookies(Map<String, List<String>> responseHeaders) {
        Map<String, String> mapCookies = null;
        if (!CoreUtils.isNullOrEmpty(responseHeaders)) {
            List<String> allCookies = responseHeaders.get(Header.SET_COOKIE);
            if (!CoreUtils.isNullOrEmpty(allCookies)) {
                mapCookies = new HashMap<String, String>();
                for (String stringCookie : allCookies) {
                    Map<String, String> extractedCookies = HTTPUtils.extractCookies(stringCookie);
                    if (!CoreUtils.isNullOrEmpty(extractedCookies)) {
                        mapCookies.putAll(extractedCookies);
                    }
                }
            }
        }

        return mapCookies;
    }

    /**
     * Returns the value of the specified key from the headers. It might be this
     * method throw ClassCastException, if the return value is different than
     * string.
     *
     * @param httpResponse
     * @param key
     * @return
     */
    public static String getHeader(HttpResponse httpResponse, String key) {
        // return httpResponse.getResponseHeaders().get(key);
        return null;
    }

    /**
     * Returns the value of the specified key from the headers as boolean.
     *
     * @param httpResponse
     * @param key
     * @return
     */
    public static boolean getHeaderAsBoolean(HttpResponse httpResponse, String key) {
        return Boolean.parseBoolean(getHeader(httpResponse, key));
    }

    /**
     * Return the value of the key from the specified mapKeyValues. If no value
     * is found, the default is returned.
     *
     * @param mapKeyValues
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object getValueForKey(Map<String, Object> mapKeyValues, String key, Object defaultValue) {
        // LogUtils.d(DEBUG_KEY, "+getValueForKey(" + mapKeyValues + ", " + key
        // +
        // ", " + defaultValue + ")");
        Object value = null;
        if (!CoreUtils.isNullOrEmpty(mapKeyValues) && !CoreUtils.isNullOrEmpty(key)) {
            value = mapKeyValues.get(key);
        }

        // return default value if the value is null or empty.
        if (CoreUtils.isNull(value)) {
            value = defaultValue;
        }

        // LogUtils.d(DEBUG_KEY, "-getValueForKey(), value:" + value);
        return value;
    }

    /**
     * Return the value of the key from the specified mapKeyValues.
     *
     * @param mapKeyValues
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getValueForKey(Map<String, Object> mapKeyValues, String key) {
        return (T) getValueForKey(mapKeyValues, key, null);
    }

    /**
     * Return the value of the key from the specified keyValuesMap.
     *
     * @param mapKeyValues
     * @param key
     * @return
     */
    public static String getValueForKeyAsString(Map<String, Object> mapKeyValues, String key) {
        return (String) getValueForKey(mapKeyValues, key, null);
    }

    /**
     * Return the value of the key from the specified keyValuesMap as boolean.
     *
     * @param mapKeyValues
     * @param key
     * @return
     */
    public static boolean getValueForKeyAsBoolean(Map<String, Object> mapKeyValues, String key) {
        return Boolean.parseBoolean(getValueForKeyAsString(mapKeyValues, key));
    }

    /**
     * Return the value of the key from the specified keyValuesMap as boolean.
     *
     * @param mapKeyValues
     * @param key
     * @return
     */
    public static int getValueForKeyAsInteger(Map<String, Object> mapKeyValues, String key) {
        return Integer.parseInt(getValueForKeyAsString(mapKeyValues, key));
    }

    /**
     * Return the value of the key from the specified keyValuesMap as boolean.
     *
     * @param mapKeyValues
     * @param key
     * @return
     */
    public static long getValueForKeyAsLong(Map<String, Object> mapKeyValues, String key) {
        return Long.parseLong(getValueForKeyAsString(mapKeyValues, key));
    }

    /**
     * Return the value of the key from the specified keyValuesMap as boolean.
     *
     * @param mapKeyValues
     * @param key
     * @return
     */
    public static List<?> getValueForKeyAsList(Map<String, Object> mapKeyValues, String key) {
        return ((List<?>) getValueForKey(mapKeyValues, key));
    }

    /**
     * Converts the header value <code>List<String></code> bvHttpRequest string.
     *
     * @param headers
     * @return
     */
    public static Map<String, String> headerValuesAsString(Map<String, List<String>> headers) {
        Map<String, String> mapHeaders = new HashMap<String, String>();
        if (!CoreUtils.isNullOrEmpty(headers)) {
            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            for (Map.Entry<String, List<String>> entry : entries) {
                mapHeaders.put(entry.getKey(), entry.getValue().get(0));
            }
        }

        return mapHeaders;
    }

    /**
     * @param contentDisposition
     * @return
     */
    public static String getContentDispositionFileNameValue(String contentDisposition) {
        String valueFileName = null;
        if (!CoreUtils.isNullOrEmpty(contentDisposition)) {
            int fileNameIndex = contentDisposition.indexOf(Header.FILE_NAME_EQUAL.getName());
            if (fileNameIndex > -1 && fileNameIndex < contentDisposition.length() - 1) {
                valueFileName = contentDisposition.substring(fileNameIndex + Header.FILE_NAME_EQUAL.getName().length());
            }
        }

        return valueFileName;
    }

    /**
     * Returns the list of excluded headers.
     *
     * @return
     */
    public static List<String> getExcludedHeaders() {
        if (CoreUtils.isNull(excludedHeaders)) {
            excludedHeaders = new ArrayList<String>();
            /*
             * excluded the following headers from the request/response headers.
             */
            excludedHeaders.add("Host");
            excludedHeaders.add("Accept");
            excludedHeaders.add("Origin");
            excludedHeaders.add("X-Requested-With");
            excludedHeaders.add("User-Agent");
            excludedHeaders.add("Content-Length");
            excludedHeaders.add("Referer");
            excludedHeaders.add("Accept-Encoding");
            excludedHeaders.add("Accept-Language");
            excludedHeaders.add("Cookie");
        }

        return excludedHeaders;
    }

    /**
     * Returns the list of ignored header.
     *
     * @return
     */
    public static String[] getHeadersIgnored() {
        if (CoreUtils.isNull(headersIgnored)) {
            headersIgnored = HTTPUtils.toArray(getExcludedHeaders(), String.class);
        }

        return headersIgnored;
    }

    /**
     * Returns the list of excluded parameter keys.
     *
     * @return
     */
    public static List<String> getExcludedParameters() {
        if (CoreUtils.isNull(excludedParameters)) {
            excludedParameters = new ArrayList<String>();
            /* Exclude more values here, if required. */
        }

        return excludedMethods;
    }

    /**
     * Returns true if the given paramName is part of an excludedParameters set
     * otherwise false.
     *
     * @param paramName
     * @return
     */
    public static boolean isExcludedParameter(String paramName) {
        boolean excludedParameter = false;
        if (CoreUtils.isNotNullOrEmpty(paramName) && !CoreUtils.isNullOrEmpty(getExcludedParameters())) {
            for (int i = 0; i < excludedParameters.size(); i++) {
                if (excludedParameters.contains(paramName)) {
                    excludedParameter = true;
                    break;
                }
            }
        }

        return excludedParameter;
    }

    /**
     * Removes the parameters which are excluded from the request hash code.
     *
     * @param sortedParameters
     */
    public static void removeExcludedParameters(SortedMap<String, Object> sortedParameters) {
        if (!CoreUtils.isNullOrEmpty(sortedParameters) && !CoreUtils.isNullOrEmpty(getExcludedParameters())) {
            for (int i = 0; i < excludedParameters.size(); i++) {
                sortedParameters.remove(excludedParameters.get(i));
            }
        }
    }

    /**
     * Returns the hash string generated using the specified parameters.
     *
     * @param requestParameters
     * @return
     */
    public static String paramValuesAsHashString(SortedMap<String, Object> requestParameters) {
        String valuesAsHashString = null;
        if (!CoreUtils.isNullOrEmpty(requestParameters)) {
            SortedMap<String, Object> sortedParameters = HTTPUtils.toSortedMap(requestParameters);
            /* remove existing custom parameters, if available */
            removeExcludedParameters(sortedParameters);

            // param values.
            String valuesAsString = HTTPUtils.paramValuesAsString(sortedParameters);
            valuesAsHashString = SecurityUtils.paramValueAsHashString(valuesAsString);
        }

        return valuesAsHashString;
    }

    /**
     * Returns the hash string generated using the specified parameters.
     *
     * @param requestParameters
     * @return
     */
    public static String paramValuesAsHashString(Map<String, ? extends Object> requestParameters) {
        return paramValuesAsHashString(HTTPUtils.toSortedMap(requestParameters));
    }

    /**
     * Returns true if the specified requestMethodName is an excludedMethods set
     * otherwise false.
     *
     * @param requestMethodName
     * @param excludedMethods
     * @return
     */
    public static boolean isExcludedMethodRequest(String requestMethodName, String... excludedMethods) {
        boolean excludedMethodRequest = false;
        if (!CoreUtils.isNullOrEmpty(requestMethodName) && !CoreUtils.isNullOrEmpty(excludedMethods)) {
            for (int i = 0; i < excludedMethods.length; i++) {
                if (excludedMethods[i].equalsIgnoreCase(requestMethodName)) {
                    excludedMethodRequest = true;
                    break;
                }
            }
        }

        return excludedMethodRequest;
    }

    /**
     * Returns the list of black listed methods.
     *
     * @return
     */
    public static List<String> getExcludedMethods() {
        if (CoreUtils.isNull(excludedMethods)) {
            excludedMethods = new ArrayList<String>();
            /* add more methods if required. */
        }

        return excludedMethods;
    }

    /**
     * Filters the request parameters.
     *
     * @param requestParameters
     * @param excludedMethodRequest
     */
    public static void filterRequestParameters(SortedMap<String, Object> requestParameters,
                                               boolean excludedMethodRequest) {
        if (excludedMethodRequest && !CoreUtils.isNullOrEmpty(requestParameters)) {
            SortedMap<String, Object> filteredParameters = new TreeMap<String, Object>(requestParameters);
            for (String key : filteredParameters.keySet()) {
                if (("rand".equals(key) || "_".equals(key))) {
                    requestParameters.remove(key);
                }
            }
        }
    }

    /**
     * Returns the request parameters as the <code>List<NameValuePair></code>
     * object after sorts based on the name.
     *
     * @param servletRequest
     * @return
     * @throws IOException
     */
    public static SortedMap<String, Object> getRequestParameters(HttpServletRequest servletRequest) throws IOException {
        SortedMap<String, Object> requestParameters = new TreeMap<String, Object>();
        if (servletRequest != null) {
            /* extract request parameters, if available. */
            for (Object key : servletRequest.getParameterMap().keySet()) {
                String value = servletRequest.getParameter(key.toString());
                requestParameters.put(key.toString(), value);
            }
        }

        return requestParameters;
    }

    /**
     * Returns the string generated using the specified parameters (only
     * parameter values excluding keys).
     *
     * @param paramValues
     * @return
     */
    public static String paramValuesAsString(String... paramValues) {
        String valuesAsString = null;
        if (!CoreUtils.isNullOrEmpty(paramValues)) {
            StringBuilder hashBuffer = new StringBuilder();
            Arrays.sort(paramValues);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = CoreUtils.isNullOrEmpty(paramValues[i]) ? "" : paramValues[i];
                hashBuffer.append(paramValue);
            }

            valuesAsString = hashBuffer.toString();
            hashBuffer = null;
        }

        return valuesAsString;
    }

    /**
     * Returns the string generated using the specified parameters (only
     * parameter values excluding keys).
     *
     * @param paramValues
     * @return
     */
    public static String paramValuesAsString(List<String> paramValues) {
        String valuesAsString = null;
        if (!CoreUtils.isNullOrEmpty(paramValues)) {
            StringBuilder hashBuffer = new StringBuilder();
            Collections.sort(paramValues);
            for (String paramValue : paramValues) {
                paramValue = CoreUtils.isNullOrEmpty(paramValue) ? "" : paramValue;
                hashBuffer.append(paramValue);
            }

            valuesAsString = hashBuffer.toString();
            hashBuffer = null;
        }

        return valuesAsString;
    }

    /**
     * Returns the string generated using the specified parameters (only
     * parameter values excluding keys).
     *
     * @param requestParameters
     * @return
     */
    public static String paramValuesAsString(SortedMap<String, ? extends Object> requestParameters) {
        String valuesAsString = null;
        if (!CoreUtils.isNullOrEmpty(requestParameters)) {
            StringBuilder hashBuffer = new StringBuilder();
            /* the iteration should be name in the same order each time. */
            for (String key : requestParameters.keySet()) {
                Object value = requestParameters.get(key);
                String valueAsString = CoreUtils.isNull(value) ? "" : value.toString();
                hashBuffer.append(valueAsString);
            }

            valuesAsString = hashBuffer.toString();
            hashBuffer = null;
        }

        return valuesAsString;
    }

    /**
     * Adds the given values into the hashBuffer.
     *
     * @param hashBuffer
     * @param values
     */
    public static void addToHashBuffer(final StringBuilder hashBuffer, String... values) {
        if (CoreUtils.isNotNull(hashBuffer) && !CoreUtils.isNullOrEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                if (!CoreUtils.isNullOrEmpty(values[i])) {
                    hashBuffer.append(values[i]);
                }
            }
        }
    }

    /**
     * Converts the responseHeaders from <code>Map<String, List<String>></code>
     * to <code>Map<String, String></code>.
     *
     * @param responseHeaders
     * @return
     */
    public static Map<String, String> toResponseHeaders(Map<String, List<String>> responseHeaders) {
        Map<String, String> toResponseHeaders = new HashMap<String, String>();
        for (String key : responseHeaders.keySet()) {
            List<String> listValue = responseHeaders.get(key);
            if (listValue.size() > 1 && toResponseHeaders.containsKey(key)) {
                StringBuilder valueBuilder = new StringBuilder();
                for (int i = 0; i < listValue.size(); i++) {
                    valueBuilder.append(listValue.get(i));
                    if (i < listValue.size() - 1) {
                        valueBuilder.append(";");
                    }
                }
                toResponseHeaders.put(key, valueBuilder.toString());
            } else {
                toResponseHeaders.put(key, listValue.get(0));
            }
        }

        return toResponseHeaders;
    }


    /**
     *
     * @param responseHandler
     */
    public abstract void execute(ResponseHandler responseHandler);

    /**
     *
     */
    public void execute() {
        execute(null);
    }

}