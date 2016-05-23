package com.apparatus.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;

import com.apparatus.Constants;
import com.apparatus.config.Config;

/**
 * 
 * @author Rohtash Singh Lakra
 * @created Apr 15, 2016 11:09:52 AM
 */
public class UrlHelper {
	
	public static final String KEY_SET_COOKIE = "Set-Cookie";
	public static final String KEY_COOKIE = "Cookie";
	public static final String SCHEMA_HTTP = "http";
	public static final String SCHEMA_HTTPS = "https";
	public static final String SERVICES = "/services";
	public static final String LOGIN_IPADPUBLIC = "/login/ipadpublic";
	public static final String LOGIN_RESOURCES = "/login/resources";
	public static final String SERVICES_IPAD = "/services/ipad";
	public static final String SERVICES_IPADPUBLIC = "/services/ipadpublic";
	public static final String SERVICES_DOWNLOAD = "/services/download";
	public static final String SERVICES_UPDATE = "/desktop/zipservice/";
	public static final String SERVICES_IPAD_IMAGE_LOADER = "/services/ipadimageloader";
	
	public static final int CONNECT_TIMEOUT = 20000;
	public static final int READ_TIMEOUT = 60000;
	public static final String CLIENT_REQUEST_HASH_CODE = "client-request-hashcode";
	public static final String HASH_CODE_FILE_LAST_MODIFIED = "Hash-Code-File-Last-Modified";
	public static final String HASH_CODE_FILE_SIZE = "Hash-Code-File-Size";
	public static final String USE_EXISTING_FILE = "Use-Existing-File";
	
	/** URL_PATTERN */
	public static final String URL_REGEX = "^(http|https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	/** CHARLES_PROXY - Enables CHARLES as PROXY. */
	public static HttpHost CHARLES_PROXY = new HttpHost(Constants.LOCAL_HOST, 8888);
	public static final String[] HEADERS_IGNORED = { "Host", "Accept", "Origin", "X-Requested-With", "User-Agent", "Content-Length", "Referer", "Accept-Encoding", "Accept-Language", "Cookie"};
	public static final String CONTENY_TYPE_JSON = "application/json";
	
	/**
	 * 
	 * @param suffixS
	 * @return
	 */
	public static String getServerUrl(String suffix) {
		StringBuilder urlServer = new StringBuilder();
		urlServer.append(Config.getServerScheme()).append("://");
		urlServer.append(Config.getServerHost());
		if(!StringHelper.isNullOrEmpty(Config.getServerPort())) {
			urlServer.append(":").append(Config.getServerPort());
		}
		
		if(!StringHelper.isNullOrEmpty(suffix)) {
			if(suffix.startsWith("/")) {
				urlServer.append(suffix);
			} else {
				urlServer.append("/").append(suffix);
			}
		}
		
		return urlServer.toString();
	}
	
	public static boolean isNetworkAvailable(String urlSuffix) {
		boolean networkAvaialable = false;
		HttpURLConnection urlConnection = null;
		try {
			String urlString = getServerUrl(urlSuffix);
			System.out.println("urlString:" + urlString);
			urlConnection = getHttpConnection(urlString, null);
			if(urlConnection != null) {
				urlConnection.setConnectTimeout(Config.getIntValue(Config.SERVER_CONNECTION_TIMEOUT, 10000));
				urlConnection.setReadTimeout(Config.getIntValue(Config.SERVER_READ_TIMEOUT, 10000));
				networkAvaialable = (urlConnection.getContent() != null);
			}
		} catch(Exception ex) {
			networkAvaialable = false;
		} finally {
			close(urlConnection);
		}
		
		System.out.println("isNetworkAvailable(), networkAvaialable:" + networkAvaialable);
		return networkAvaialable;
	}
	
	/**
	 * Encodes the specified URL.
	 * 
	 * @param url
	 * @return
	 */
	public static String encodeUrl(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch(UnsupportedEncodingException ex) {
			System.err.println(ex);
		}
		
		return url;
	}
	
	/**
	 * Decodes the specified URL.
	 * 
	 * @param url
	 * @return
	 */
	public static String deodeUrl(String url) {
		try {
			return URLDecoder.decode(url, "UTF-8");
		} catch(UnsupportedEncodingException ex) {
			System.err.println(ex);
		}
		
		return url;
	}
	
	/**
	 * 
	 * @param baseUrl
	 * @param urlSuffix
	 * @return
	 * @throws Exception
	 */
	public static URL getURL(String baseUrl, String urlSuffix) throws Exception {
		URL url = null;
		if(StringHelper.isNullOrEmpty(urlSuffix)) {
			url = new URL(baseUrl);
		} else {
			url = new URL(new URL(baseUrl), urlSuffix);
		}
		
		return url;
	}
	
	/**
	 * Returns the HttpURLConnection object for the specified URL.
	 * 
	 * @param baseUrl
	 * @param urlSuffix
	 * @return
	 */
	public static HttpURLConnection getHttpConnection(String baseUrl, String urlSuffix) throws Exception {
		URL url = getURL(baseUrl, urlSuffix);
		return (url == null ? null : (HttpURLConnection) url.openConnection());
	}
	
	/**
	 * Closes the connection.
	 * 
	 * @param httpURLConnection
	 */
	public static void close(HttpURLConnection httpURLConnection) {
		if(httpURLConnection != null) {
			try {
				httpURLConnection.disconnect();
			} catch(Exception ex) {
				System.err.println(ex);
			}
		}
	}
	
	/**
	 * Closes the socket.
	 * 
	 * @param socket
	 */
	public static void close(Socket socket) {
		if(socket != null) {
			try {
				socket.close();
			} catch(IOException ex) {
				System.err.println(ex);
			}
		}
	}
	
	/**
	 * Closes the socket.
	 * 
	 * @param socket
	 */
	public static void close(ServerSocket socket) {
		if(socket != null) {
			try {
				socket.close();
			} catch(IOException ex) {
				System.err.println(ex);
			}
		}
	}
	
	/**
	 * Returns the value of the specified key from the headers.
	 * 
	 * @param httpResponse
	 * @param key
	 * @return
	 */
	public static String getHeader(HttpResponse httpResponse, String key) {
		String value = null;
		if(httpResponse != null) {
			Header[] headers = (Header[]) httpResponse.getAllHeaders();
			if(headers != null) {
				for(Header header : headers) {
					if(header.getName().equals(key)) {
						value = header.getValue();
						break;
					}
				}
			}
		}
		
		return value;
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
		if(!ObjectHelper.isNullOrEmpty(headers)) {
			List<String> headerValues = headers.get(key);
			if(!ObjectHelper.isNullOrEmpty(headerValues)) {
				value = headerValues.get(0);
			}
		}
		
		return value;
	}
	
	/**
	 * 
	 * @param headers
	 * @param key
	 * @return
	 */
	public static String getValueForKey(Map<String, String> headers, String key) {
		String value = null;
		if(!ObjectHelper.isNullOrEmpty(headers)) {
			value = headers.get(key);
		}
		
		return value;
	}
	
	/**
	 * Converts the header value <code>List<String></code> to string.
	 * 
	 * @param headers
	 * @return
	 */
	public static Map<String, String> headerValuesAsString(Map<String, List<String>> headers) {
		Map<String, String> mapHeaders = new HashMap<String, String>();
		if(!ObjectHelper.isNullOrEmpty(headers)) {
			Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
			for(Map.Entry<String, List<String>> entry : entries) {
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
		String fileNameValue = null;
		if(!StringHelper.isNullOrEmpty(contentDisposition)) {
			int index = contentDisposition.indexOf(Constants.Http.Headers.KEY_FILE_NAME_EQUAL);
			if(index > -1) {
				fileNameValue = contentDisposition.substring(index + Constants.Http.Headers.KEY_FILE_NAME_EQUAL.length());
			}
		}
		
		return fileNameValue;
	}
	
	/**
	 * Returns the methodName extracted from the request parameters.
	 * 
	 * @param requestParameters
	 * @return
	 */
	public static String getRequestMethodName(List<NameValuePair> requestParameters) {
		String requestMethodName = null;
		if(!ObjectHelper.isNullOrEmpty(requestParameters)) {
			for(NameValuePair nameValuePair : requestParameters) {
				if("methodname".equalsIgnoreCase(nameValuePair.getName())) {
					requestMethodName = nameValuePair.getValue();
					break;
				}
			}
		}
		
		return requestMethodName;
	}
	
	/**
	 * Returns the methodName extracted from the request parameters.
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestMethodName(HttpServletRequest request) {
		String requestMethodName = null;
		if(ObjectHelper.isNotNull(request)) {
			String[] paramValue = (String[]) request.getParameterMap().get("methodname");
			if(!ObjectHelper.isNullOrEmpty(paramValue)) {
				requestMethodName = paramValue[0].toString();
			}
		}
		
		return requestMethodName;
	}
	
	/**
	 * Returns the request parameters as the <code>List<NameValuePair></code>
	 * object after sorts based on the name.
	 * 
	 * @param parameters
	 * @return
	 */
	public static List<NameValuePair> getRequestParameters(Map<String, String> parameters) {
		List<NameValuePair> requestParameters = new LinkedList<NameValuePair>();
		/* extract request parameters, if available. */
		if(!ObjectHelper.isNullOrEmpty(parameters)) {
			boolean pdfOrPortalLogoRequest = false;
			for(String key : parameters.keySet()) {
				String value = parameters.get(key);
				requestParameters.add(new BasicNameValuePair(key, value));
				if(value.equals("getPageAsPdf") || value.equals("portalLogo")) {
					pdfOrPortalLogoRequest = true;
				}
			}
			
			requestParameters = filterRequestParameters(pdfOrPortalLogoRequest, requestParameters);
		}
		
		/* sort parameters to get the same hash code. */
		// Collections.sort(requestParameters,
		// ObjectHelper.NameValuePairNameComparator);
		
		return requestParameters;
	}
	
	private static List<NameValuePair> filterRequestParameters(boolean pdfOrPortalLogoRequest, List<NameValuePair> requestParameters) {
		if(pdfOrPortalLogoRequest && !ObjectHelper.isNullOrEmpty(requestParameters)) {
			List<NameValuePair> filteredRequestParameters = new ArrayList<NameValuePair>();
			for(NameValuePair nameValuePair : requestParameters) {
				if(!("rand".equals(nameValuePair.getName()) || "_".equals(nameValuePair.getName()))) {
					filteredRequestParameters.add(nameValuePair);
				}
			}
			
			requestParameters = filteredRequestParameters;
		}
		
		return requestParameters;
	}
	
	public static void addHeaders(final HttpServletRequest request, HttpRequestBase httpBase, final String url) throws MalformedURLException {
		if(request != null) {
			Enumeration<?> headers = request.getHeaderNames();
			while(headers.hasMoreElements()) {
				String key = (String) headers.nextElement();
				if(StringHelper.equalsIgnoreCase(key, HEADERS_IGNORED)) {
					continue;
				}
				System.out.println("Adding headers, key:" + key + ", value:" + request.getHeader(key));
				httpBase.addHeader(key, request.getHeader(key));
			}
		}
	}
	
	public static void addHeaders(HttpRequestBase httpBase, String key, String value) {
		if(httpBase != null && !StringHelper.isNullOrEmpty(key)) {
			httpBase.addHeader(key, value);
		}
	}
	
	/**
	 * Adds the headers to the specified request.
	 * 
	 * @param httpBase
	 * @param headers
	 * @throws IOException
	 */
	public static void addHeaders(final HttpRequestBase httpBase, Map<String, String> headers) {
		/* Add parameters, if provided. */
		if(!ObjectHelper.isNullOrEmpty(headers)) {
			Set<Map.Entry<String, String>> entries = headers.entrySet();
			for(Map.Entry<String, String> entry : entries) {
				addHeaders(httpBase, entry.getKey(), entry.getValue());
			}
		}
	}
	
	/**
	 * Sets the default connection headers and request properties.
	 * 
	 * @param urlConnection
	 * @param requestMethod
	 * @throws Exception
	 */
	public static void setDefaultConnectionSettings(HttpURLConnection urlConnection, String requestMethod) throws Exception {
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setDefaultUseCaches(false);
		urlConnection.setRequestMethod(requestMethod);
		urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
		urlConnection.setReadTimeout(READ_TIMEOUT);
		
		urlConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		urlConnection.setRequestProperty("Content-Type", "application/x-java-serialized-object");
	}
	
	/**
	 * 
	 * @param urlString
	 * @return
	 */
	public static boolean isValidURL(String urlString) {
		return (!StringHelper.isNullOrEmpty(urlString) && urlString.matches(URL_REGEX));
	}
	
	public static void main(String[] args) {
		String urlString = "https://mbp-rohtash";
		System.out.println("Valid URL:" + UrlHelper.isValidURL(urlString));
		urlString = "www.mbp-rohtash.com";
		System.out.println("Valid URL:" + UrlHelper.isValidURL(urlString));
		urlString = "ftp://mbp-rohtash";
		System.out.println("Valid URL:" + UrlHelper.isValidURL(urlString));
		urlString = "mbp-rohtash.com";
		System.out.println("Valid URL:" + UrlHelper.isValidURL(urlString));
		urlString = "mbp-rohtash";
		System.out.println("Valid URL:" + UrlHelper.isValidURL(urlString));
	}
}
