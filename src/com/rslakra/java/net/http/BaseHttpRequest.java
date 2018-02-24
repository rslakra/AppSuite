/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
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
package com.rslakra.java.net.http;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ServerSocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rslakra.java.net.http.Constants.HeaderValues;
import com.rslakra.java.net.http.Constants.Headers;
import com.rslakra.java.net.http.Constants.Methods;
import com.rslakra.java.net.http.HttpUtils.KeyValuePair;
import com.rslakra.utils.HTTPHelper;
import com.rslakra.utils.IOHelper;
import com.rslakra.utils.SecurityHelper;

/**
 * The base class for HTTP request handling.
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 10:32:30 AM
 */
public abstract class BaseHttpRequest implements HttpRequest {

	/** UTF-8 */
	public static final String UTF_8 = "UTF-8".intern();

	/** ISO-8859-1 */
	public static final String ISO_8859_1 = "ISO-8859-1".intern();

	/* USE_FULLY_QUALIFIED_HOSTNAME */
	private static final boolean USE_FULLY_QUALIFIED_HOSTNAME = false;

	/* KERNEL_VERSION */
	private static final String KERNEL_VERSION = System.getProperty("os.version");

	/** mimeTypes */
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
	public BaseHttpRequest() {
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
	 * 
	 * NOTE: - Please don't make change in this user agent string. It is used to
	 * send the client requests to server (like iPad and Android).
	 * 
	 * @param mPlatform
	 * @param mAppName
	 * @param mAppBundleIdentifier
	 * @param mServerVersion
	 * @param mAppType
	 * @return
	 */
	public static String getUserAgentString(final String mPlatform, final String mAppName,
			final String mAppBundleIdentifier, final String mServerVersion, final String mAppType) {
		StringBuilder userAgentBuilder = new StringBuilder();

		/* These properties are mandatory for the server requests. */
		if (HttpUtils.isNullOrEmpty(mPlatform) || HttpUtils.isNullOrEmpty(mAppName)
				|| HttpUtils.isNullOrEmpty(mAppBundleIdentifier) || HttpUtils.isNullOrEmpty(mAppType)) {
			throw new IllegalArgumentException("Invalid User-Agent Values!");
		}

		// prepare user-agent value
		userAgentBuilder.append("Platform=").append(mPlatform);
		userAgentBuilder.append(";App=").append(mAppName);
		userAgentBuilder.append(";ABI=").append(mAppBundleIdentifier);
		if (HttpUtils.isNotNullOrEmpty(mServerVersion)) {
			userAgentBuilder.append(";Ver=").append(mServerVersion);
		}
		// userAgentBuilder.append(";Lcl=").append(mLocale.toString());
		// userAgentBuilder.append(";OSVer=").append(OS_VERSION);
		// userAgentBuilder.append(";Dvc=").append(Build.MODEL);
		// userAgentBuilder.append(";Prd=").append(mAppName);
		// userAgentBuilder.append(";Lang=").append(mLocale.getLanguage());
		// userAgentBuilder.append(";AppType=").append(mAppType);
		// userAgentBuilder.append(";KerVer=").append(KERNEL_VERSION);

		return userAgentBuilder.toString();
	}

	/**
	 * 
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
		if (HttpUtils.isNullOrEmpty(urlSuffix)) {
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
	 * 
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
		if (HttpUtils.isNotNullOrEmpty(baseServerUrl)) {
			urlString.append(baseServerUrl);
		}

		// append urlPrefix, if available.
		if (!HttpUtils.isNullOrEmpty(urlSuffix)) {
			if (urlSuffix.startsWith(HttpUtils.SLASH)) {
				if (urlString.toString().endsWith(HttpUtils.SLASH)) {
					urlString.append(urlSuffix.substring(1));
				} else {
					urlString.append(urlSuffix);
				}
			} else {
				if (urlString.toString().endsWith(HttpUtils.SLASH)) {
					urlString.append(urlSuffix);
				} else {
					urlString.append(HttpUtils.SLASH).append(urlSuffix);
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
		if (HttpUtils.isNullOrEmpty(hostName)) {
			if (USE_FULLY_QUALIFIED_HOSTNAME) {
				hostName = HttpUtils.getHostNameFromUrl(urlString);
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
	 * @param resourceExtension
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
	 * @param urlString
	 * @param urlSuffix
	 * @return
	 */
	public static boolean isServerReachable(String baseServerUrl, String urlSuffix) {
		boolean serverReachable = false;
		String urlString = HTTPHelper.getServerUrl(baseServerUrl, urlSuffix);

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
			// LogHelper.i(DEBUG_KEY, "Headers:" +
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
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getRequestHeaders(HttpServletRequest servletRequest) {
		Map<String, String> requestHeaders = new TreeMap<String, String>();
		if (HttpUtils.isNotNull(servletRequest)) {
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
		servletResponse.setDateHeader(Headers.EXPIRES, -1);
		servletResponse.setHeader(Headers.PRAGMA, Headers.PRAGMA_PUBLIC);
		servletResponse.setHeader(Headers.CACHE_CONTROL, HeaderValues.PRAGMA_NO_CACHE);
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
		return (HttpUtils.isNotNull(url)
				? (HttpURLConnection) (HttpUtils.isNotNull(proxy) ? url.openConnection(proxy) : url.openConnection())
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
		return (HttpUtils.isNotNull(url) ? (HttpURLConnection) url.openConnection() : null);
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
		return (HttpUtils.isNotNull(url) && url.getProtocol().equals("https")
				? (HttpsURLConnection) (HttpUtils.isNotNull(proxy) ? url.openConnection(proxy) : url.openConnection())
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
		if (HttpUtils.isNotNull(urlConnection)) {
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
	 * 
	 * For Example: <code>
	 * getUserAgentString(AuthConstants.APP_PLATFORM, AuthConstants.APP_NAME, AuthConstants.APP_BUNDLE_IDENTIFIER, AuthConstants.APP_TYPE)
	 * </code>
	 * 
	 * @param urlConnection
	 * @param userAgent
	 */
	public static void setUserAgent(HttpURLConnection urlConnection, final String userAgent) {
		if (HttpUtils.isNotNull(urlConnection) && HttpUtils.isNotNullOrEmpty(userAgent)) {
			// user-agent (default string generated for Android)
			urlConnection.addRequestProperty(Headers.USER_AGENT, userAgent);
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
		if (HttpUtils.isNotNull(urlConnection)) {
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
		setConnectAndReadTimeouts(urlConnection, Constants.HTTP_CONNECTION_TIMEOUT_SECONDS * 1000,
				Constants.HTTP_READ_TIMEOUT_SECONDS * 1000);
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
		if (HttpUtils.isNotNull(urlConnection)) {
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
		if (HttpUtils.isNotNull(urlConnection)) {
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
		if (HttpUtils.isNotNull(urlConnection)) {
			/*
			 * Sets the flag indicating whether this URLConnection allows input.
			 * It cannot be set after the connection is established.
			 */
			urlConnection.setDoInput(true);

			/* request method (i.e GET/POST/PUT etc) */
			if (HttpUtils.isNotNullOrEmpty(requestMethod)) {
				urlConnection.setRequestMethod(requestMethod);
				urlConnection.setDoOutput(Methods.POST.equalsIgnoreCase(requestMethod));
			} else {
				urlConnection.setRequestMethod(Methods.POST);
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
		if (HttpUtils.isNotNull(urlConnection) && HttpUtils.isNotNullOrEmpty(fieldName)) {
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
		if (HttpUtils.isNotNull(urlConnection) && HttpUtils.isNotNullOrEmpty(fieldName)) {
			urlConnection.setRequestProperty(fieldName, fieldValue);
		}
	}

	/**
	 * Sets the default properties of the given <code>HttpURLConnection</code>
	 * object.
	 * 
	 * @param urlConnection
	 * @param requestMethod
	 * @param doInputOutput
	 * @param useCaches
	 * @throws IOException
	 */
	public static void setConnectionDefaultProperties(final HttpURLConnection urlConnection, final String requestMethod)
			throws IOException {
		if (HttpUtils.isNotNull(urlConnection)) {
			// set connection timeout properties.
			setDefaultUseCaches(urlConnection);
			setDefaultConnectAndReadTimeouts(urlConnection);
			// setUserAgent(urlConnection,
			// getUserAgentString(AuthConstants.APP_PLATFORM,
			// AuthConstants.APP_NAME, AuthConstants.APP_BUNDLE_IDENTIFIER,
			// AuthConstants.APP_TYPE));
			setRequestMethod(urlConnection, requestMethod);

			// add device-id in request.
			addHeader(urlConnection, "deviceId", SecurityHelper.uniqueDeviceIdString());

			// other default properties
			setHeader(urlConnection, Headers.ACCEPT_LANGUAGE, Locale.getDefault().getLanguage());
		}
	}

	/**
	 * Returns the <code>mapCookies</code> as string.
	 * 
	 * @param mapCookies
	 */
	public static String toCookyString(Map<String, String> mapCookies) {
		String cookies = null;
		if (!HttpUtils.isNullOrEmpty(mapCookies)) {
			/** Add Cookies. */
			StringBuilder cookyBuilder = new StringBuilder();
			for (String key : mapCookies.keySet()) {
				String value = mapCookies.get(key);
				if (HttpUtils.isNotNullOrEmpty(value)) {
					if (HttpUtils.equals(Headers.COOKIE, key)) {
						cookyBuilder.append(value).append(";");
					} else {
						// value = SecurityHelper.encodeWithURLEncoder(value,
						// UTF_8);
						cookyBuilder.append(key).append("=").append(value).append(";");
					}
				}
			}

			if (!HttpUtils.isNullOrEmpty(cookyBuilder)) {
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
		if (!HttpUtils.isNullOrEmpty(mapCookies)) {
			/** Merge Cookies. */
			for (String key : mapCookies.keySet()) {
				String value = mapCookies.get(key);
				if (HttpUtils.isNotNullOrEmpty(value)) {
					if (HttpUtils.equals(Headers.COOKIE, key)) {
						Map<String, String> oldCookies = extractCookies(value);
						if (!HttpUtils.isNullOrEmpty(oldCookies)) {
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
		if (HttpUtils.isNotNull(urlConnection) && HttpUtils.isNotNullOrEmpty(cookies)) {
			urlConnection.setRequestProperty(Headers.COOKIE, cookies);
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
		if (!HttpUtils.isNullOrEmpty(requestParameters)) {
			StringBuilder aueryString = new StringBuilder();
			boolean firstParam = true;
			for (String key : requestParameters.keySet()) {
				if (firstParam) {
					firstParam = false;
				} else {
					aueryString.append("&");
				}

				String value = String.valueOf(requestParameters.get(key));
				value = SecurityHelper.encodeWithURLEncoder(value, UTF_8);
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
		return (Methods.GET.equalsIgnoreCase(urlConnection.getRequestMethod()));
	}

	/**
	 * Adds the specified <code>queryString</code> to the specified
	 * <code>urlConnection</code>.
	 * 
	 * @param urlConnection
	 * @param requestParameters
	 */
	public static void setQueryString(HttpURLConnection urlConnection, final String queryString) throws IOException {
		if (HttpUtils.isNotNull(urlConnection) && !isGetRequest(urlConnection)
				&& HttpUtils.isNotNullOrEmpty(queryString)) {
			IOHelper.writeBytes(queryString.getBytes(), urlConnection.getOutputStream(), true);
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
		if (HttpUtils.isNotNullOrEmpty(encodedParameters)) {
			String decodedParameters = SecurityHelper.decodeWithURLDecoder(encodedParameters);
			String[] paramTokens = decodedParameters.split("&");
			if (!HttpUtils.isNullOrEmpty(paramTokens)) {
				for (int i = 0; i < paramTokens.length; i++) {
					String[] tokens = paramTokens[i].split("=");
					if (!HttpUtils.isNullOrEmpty(tokens) && tokens.length > 1) {
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
		if (HttpUtils.isNotNull(urlConnection) && !HttpUtils.isNullOrEmpty(requestHeaders)) {
			for (String headerKey : requestHeaders.keySet()) {
				String headerValue = requestHeaders.get(headerKey);
				if (HttpUtils.isNotNullOrEmpty(headerValue)) {
					// headerValue =
					// SecurityHelper.encodeToBase64String(headerValue);
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
		if (HttpUtils.isNotNull(urlConnection) && !HttpUtils.isNullOrEmpty(requestParameters)) {
			// add CONTENT_TYPE in headers, if available
			String contentType = HTTPHelper.getValueForKeyAsString(requestParameters, Headers.CONTENT_TYPE);
			if (HttpUtils.isNotNullOrEmpty(contentType)) {
				urlConnection.addRequestProperty(Headers.CONTENT_TYPE, contentType);
				requestParameters.remove(Headers.CONTENT_TYPE);
			}

			// // add UNIQUE_RESPONSE_HASH in headers
			// String requestHashCode =
			// getValueForKeyAsString(requestParameters,
			// MeetXConstants.CLIENT_REQUEST_HASH_CODE);
			// if(HttpUtils.isNotNullOrEmpty(requestHashCode)) {
			// String responseHashCode =
			// getValueForKeyAsString(requestParameters,
			// MeetXConstants.UNIQUE_RESPONSE_HASH);
			// if(HttpUtils.isNotNullOrEmpty(responseHashCode)) {
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
	public static HttpResponse executeRequest(final String urlString, final String httpMethod,
			final Map<String, String> requestHeaders, final Map<String, Object> requestParameters,
			final boolean closeStream) {
		long startTime = System.currentTimeMillis();
		HttpResponse httpResponse = new HttpResponse();
		HttpURLConnection urlConnection = null;

		try {
			if (HttpUtils.isNullOrEmpty(urlString)) {
				throw new IllegalArgumentException("Server URL must provide!");
			}

			// open connection and set default header values.
			if (Methods.GET.equalsIgnoreCase(httpMethod)) {
				StringBuilder requestBuilder = new StringBuilder(urlString);
				String queryString = toUrlQueryString(requestParameters);
				if (HttpUtils.isNotNullOrEmpty(queryString)) {
					requestBuilder.append("?").append(queryString);
				}
				urlConnection = HTTPHelper.openHttpURLConnection(requestBuilder.toString());
			} else {
				urlConnection = HTTPHelper.openHttpURLConnection(urlString);
			}

			setConnectionDefaultProperties(urlConnection, httpMethod);

			// add default cookies, if any
			String urlCookies = null;
			if (HttpUtils.isNotNullOrEmpty(urlCookies)) {
				if (requestHeaders.containsKey(Headers.COOKIE)) {
					String requestCookies = requestHeaders.get(Headers.COOKIE);
					if (HttpUtils.isNotNullOrEmpty(requestCookies)) {
						urlCookies += ";" + requestCookies;
					}
				} else {
					requestHeaders.put(Headers.COOKIE, urlCookies);
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
				byte[] dataBytes = IOHelper.readBytes(urlConnection.getInputStream(), closeStream);
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
	public static HttpResponse executeRequest(final String urlString, final String httpMethod,
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
		return executeRequest(urlString, Methods.GET, requestHeaders, requestParameters, closeStream);
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
		return executeRequest(urlString, Methods.POST, requestHeaders, requestParameters, closeStream);
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
		if (!HttpUtils.isNullOrEmpty(headers)) {
			List<String> headerValues = headers.get(key);
			// LogHelper.d(DEBUG_KEY, "headerValues:" + headerValues);
			if (!HttpUtils.isNullOrEmpty(headerValues)) {
				value = headerValues.get(0);
			}
		}

		return value;
	}

	/**
	 * Returns the headers of the specified operationResult.
	 * 
	 * @param operationResult
	 * @return
	 */
	public static Map<String, List<String>> getHeaders(HttpResponse httpResponse) {
		return (HttpUtils.isNull(httpResponse) ? null : httpResponse.getResponseHeaders());
	}

	/**
	 * Returns the response type.
	 * 
	 * @param headers
	 * @return
	 */
	public static String getMimeType(Map<String, List<String>> headers) {
		String mimeType = null;
		if (!HttpUtils.isNullOrEmpty(headers)) {
			mimeType = headers.get(Headers.CONTENT_TYPE).get(0);
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
		if (HttpUtils.isNotNullOrEmpty(stringCookies)) {
			mapCookies = new HashMap<String, String>();
			String[] cookies = stringCookies.split(";");
			for (String cookie : cookies) {
				KeyValuePair<String, String> keyValuePair = KeyValuePair.newKeyValuePair(cookie);
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
		if (!HttpUtils.isNullOrEmpty(responseHeaders)) {
			List<String> allCookies = responseHeaders.get(Headers.SET_COOKIE);
			if (!HttpUtils.isNullOrEmpty(allCookies)) {
				mapCookies = new HashMap<String, String>();
				for (String stringCookie : allCookies) {
					Map<String, String> extractedCookies = HTTPHelper.extractCookies(stringCookie);
					if (!HttpUtils.isNullOrEmpty(extractedCookies)) {
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
	 * @param operationResult
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
	 * @param operationResult
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
		// LogHelper.d(DEBUG_KEY, "+getValueForKey(" + mapKeyValues + ", " + key
		// +
		// ", " + defaultValue + ")");
		Object value = null;
		if (!HttpUtils.isNullOrEmpty(mapKeyValues) && !HttpUtils.isNullOrEmpty(key)) {
			value = mapKeyValues.get(key);
		}

		// return default value if the value is null or empty.
		if (HttpUtils.isNull(value)) {
			value = defaultValue;
		}

		// LogHelper.d(DEBUG_KEY, "-getValueForKey(), value:" + value);
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
	 * @param keyValuesMap
	 * @param key
	 * @return
	 */
	public static boolean getValueForKeyAsBoolean(Map<String, Object> mapKeyValues, String key) {
		return Boolean.parseBoolean(getValueForKeyAsString(mapKeyValues, key));
	}

	/**
	 * Return the value of the key from the specified keyValuesMap as boolean.
	 * 
	 * @param keyValuesMap
	 * @param key
	 * @return
	 */
	public static int getValueForKeyAsInteger(Map<String, Object> mapKeyValues, String key) {
		return Integer.parseInt(getValueForKeyAsString(mapKeyValues, key));
	}

	/**
	 * Return the value of the key from the specified keyValuesMap as boolean.
	 * 
	 * @param keyValuesMap
	 * @param key
	 * @return
	 */
	public static long getValueForKeyAsLong(Map<String, Object> mapKeyValues, String key) {
		return Long.parseLong(getValueForKeyAsString(mapKeyValues, key));
	}

	/**
	 * Return the value of the key from the specified keyValuesMap as boolean.
	 * 
	 * @param keyValuesMap
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
		if (!HttpUtils.isNullOrEmpty(headers)) {
			Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
			for (Map.Entry<String, List<String>> entry : entries) {
				mapHeaders.put(entry.getKey(), entry.getValue().get(0));
			}
		}

		return mapHeaders;
	}

	/**
	 * 
	 * @param contentDisposition
	 * @return
	 */
	public static String getContentDispositionFileNameValue(String contentDisposition) {
		String valueFileName = null;
		if (!HttpUtils.isNullOrEmpty(contentDisposition)) {
			int fileNameIndex = contentDisposition.indexOf(HeaderValues.FILE_NAME_EQUAL);
			if (fileNameIndex > -1 && fileNameIndex < contentDisposition.length() - 1) {
				valueFileName = contentDisposition.substring(fileNameIndex + HeaderValues.FILE_NAME_EQUAL.length());
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
		if (HttpUtils.isNull(excludedHeaders)) {
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
		if (HttpUtils.isNull(headersIgnored)) {
			headersIgnored = HttpUtils.toArray(getExcludedHeaders(), String.class);
		}

		return headersIgnored;
	}

	/**
	 * Returns the list of excluded parameter keys.
	 * 
	 * @return
	 */
	public static List<String> getExcludedParameters() {
		if (HttpUtils.isNull(excludedParameters)) {
			excludedParameters = new ArrayList<String>();
			/* remove the following parameters before generating hashCode. */
			// excludedParameters.add(Headers.CLIENT_REQUEST_HASH_CODE);
			// excludedParameters.add(Headers.HASH_CODE_FILE_LAST_MODIFIED);
			// excludedParameters.add(Headers.HASH_CODE_FILE_SIZE);
			// excludedParameters.add(Headers.USE_EXISTING_FILE);
			// excludedParameters.add(Headers.UNIQUE_RESPONSE_HASH);
			// excludedParameters.add(Headers.CONTENT_DISPOSITION);
			// excludedParameters.add(Headers.RESPONSE_HEADERS);
			// excludedParameters.add(Headers.SET_COOKIE);
			// excludedParameters.add(Headers.ECX_SESSION_ID);
			// excludedParameters.add(Headers.JSESSIONID);
			// excludedParameters.add(Headers.ECX_HT);
			// excludedParameters.add(Headers.ECX_LB);
			// excludedParameters.add(Headers.ECX_PORTAL_ID);
			// excludedParameters.add(Headers.JUP_SYNC);
			// excludedParameters.add(Headers.JUP_CLIENT);
			// excludedParameters.add(Headers.CONTENT_TYPE);
			// excludedParameters.add(Headers.AUTHORIZATION);
			// excludedParameters.add(Headers.CSRF_TOKEN);
			// excludedParameters.add(Headers.REQUEST_CSRF_TOKEN);
		}

		return excludedMethods;
	}

	/**
	 * Returns true if the given paramName is part of an excludedParameters set
	 * otherwise false.
	 * 
	 * @param paramName
	 * @param excludedParameters
	 * @return
	 */
	public static boolean isExcludedParameter(String paramName) {
		boolean excludedParameter = false;
		if (HttpUtils.isNotNullOrEmpty(paramName) && !HttpUtils.isNullOrEmpty(getExcludedParameters())) {
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
		if (!HttpUtils.isNullOrEmpty(sortedParameters) && !HttpUtils.isNullOrEmpty(getExcludedParameters())) {
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
		if (!HttpUtils.isNullOrEmpty(requestParameters)) {
			SortedMap<String, Object> sortedParameters = HttpUtils.toSortedMap(requestParameters);
			/* remove existing custom parameters, if available */
			removeExcludedParameters(sortedParameters);

			/* generating hash code. */
			// UserSession userSession = UserSession.getInstance();
			// if(userSession.isValidSession() &&
			// userSession.isUserBaseUuidAvailable()) {
			// sortedParameters.put(UUIDs.USER_BASE_UUID,
			// userSession.getUserBaseUuid());
			// // add portal base UUID in hash code for these methods.
			// String methodName = getValueForKeyAsString(sortedParameters,
			// Keys.METHOD_NAME);
			// if(Utils.equalsAnyArgs(methodName,
			// ServiceMethods.GET_PORTAL_DATA_JSON,
			// ServiceMethods.GET_COMPANY_TS_LOGO)) {
			// // some methods use portalBaseUuid as securityBaseUuid
			// if(!sortedParameters.containsKey(UUIDs.PORTAL_BASE_UUID)) {
			// // tweak here to get the same hash code.
			// if(sortedParameters.containsKey(UUIDs.SECURITY_BASE_UUID)) {
			// String portalBaseUuid =
			// HTTPUtil.getValueForKeyAsString(sortedParameters,
			// UUIDs.SECURITY_BASE_UUID);
			// sortedParameters.put(UUIDs.PORTAL_BASE_UUID, portalBaseUuid);
			// sortedParameters.remove(UUIDs.SECURITY_BASE_UUID);
			// } else {
			// sortedParameters.put(UUIDs.PORTAL_BASE_UUID,
			// userSession.getPortalBaseUuid());
			// }
			// }
			// }
			// }

			String paramValuesAsString = HTTPHelper.paramValuesAsString(sortedParameters);
			valuesAsHashString = SecurityHelper.paramValueAsHashString(paramValuesAsString);
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
		return paramValuesAsHashString(HttpUtils.toSortedMap(requestParameters));
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
		if (!HttpUtils.isNullOrEmpty(requestMethodName) && !HttpUtils.isNullOrEmpty(excludedMethods)) {
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
		if (HttpUtils.isNull(excludedMethods)) {
			excludedMethods = new ArrayList<String>();
			/* add more methods if required. */
			// excludedMethods.add(ServiceMethods.ARE_YOU_THERE_JSON);
			// excludedMethods.add(ServiceMethods.GENERATE_LOGIN_KEY_PAIR_JSON);
			// excludedMethods.add(ServiceMethods.GET_SESSION_POLLING_MESSAGES_TOUCH_JSON);
			// excludedMethods.add(ServiceMethods.GET_SESSION_POLLING_MESSAGES);
			// excludedMethods.add(ServiceMethods.GET_SESSION_POLLING_MESSAGES_JSON);
			// excludedMethods.add(ServiceMethods.GET_PAGE_AS_PDF);
			// excludedMethods.add(ServiceMethods.INVALIDATE_SESSION);
			// excludedMethods.add(ServiceMethods.INVALIDATE_SESSION_JSON);
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
		if (excludedMethodRequest && !HttpUtils.isNullOrEmpty(requestParameters)) {
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
	 * @param request
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
	 * @param requestParameters
	 * @return
	 */
	public static String paramValuesAsString(String... paramValues) {
		String valuesAsString = null;
		if (!HttpUtils.isNullOrEmpty(paramValues)) {
			StringBuilder hashBuffer = new StringBuilder();
			Arrays.sort(paramValues);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = HttpUtils.isNullOrEmpty(paramValues[i]) ? "" : paramValues[i];
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
	public static String paramValuesAsString(List<String> paramValues) {
		String valuesAsString = null;
		if (!HttpUtils.isNullOrEmpty(paramValues)) {
			StringBuilder hashBuffer = new StringBuilder();
			Collections.sort(paramValues);
			for (String paramValue : paramValues) {
				paramValue = HttpUtils.isNullOrEmpty(paramValue) ? "" : paramValue;
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
		if (!HttpUtils.isNullOrEmpty(requestParameters)) {
			StringBuilder hashBuffer = new StringBuilder();
			/* the iteration should be name in the same order each time. */
			for (String key : requestParameters.keySet()) {
				Object value = requestParameters.get(key);
				String valueAsString = HttpUtils.isNull(value) ? "" : value.toString();
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
		if (HttpUtils.isNotNull(hashBuffer) && !HttpUtils.isNullOrEmpty(values)) {
			for (int i = 0; i < values.length; i++) {
				if (!HttpUtils.isNullOrEmpty(values[i])) {
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
	 * @see com.rslakra.http.HttpRequest#getHeaders()
	 */
	@Override
	public Map<String, String> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.rslakra.http.HttpRequest#setHeaders(java.util.Map)
	 */
	@Override
	public void setHeaders(Map<String, String> headers) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.rslakra.http.HttpRequest#getParameters()
	 */
	@Override
	public Map<String, Object> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.rslakra.http.HttpRequest#setParameters(java.util.Map)
	 */
	@Override
	public void setParameters(Map<String, Object> parameters) {
		// TODO Auto-generated method stub

	}

}
