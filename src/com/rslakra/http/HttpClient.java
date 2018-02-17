package com.rslakra.http;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 10:00:53 AM
 */
public final class HttpClient {
	
	private boolean asynchronous;
	private HttpRequest httpRequest;
	
	/**
	 * 
	 * @param asynchronous
	 */
	public HttpClient(final boolean asynchronous) {
		this.asynchronous = asynchronous;
	}
	
	/**
	 * 
	 */
	public HttpClient() {
		this(false);
	}
	
	/**
	 * Returns the asynchronous.
	 * 
	 * @return
	 */
	public boolean isAsynchronous() {
		return asynchronous;
	}
	
	/**
	 * The asynchronous to be set.
	 * 
	 * @param asynchronous
	 */
	public void setAsynchronous(boolean asynchronous) {
		this.asynchronous = asynchronous;
	}
	
	/**
	 * Returns the httpRequest.
	 * 
	 * @return
	 */
	public HttpRequest getHttpRequest() {
		return httpRequest;
	}
	
	/**
	 * The httpRequest to be set.
	 * 
	 * @param httpRequest
	 */
	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	/**
	 * 
	 * @param httpMethod
	 * @param urlString
	 */
	public void execute(String httpMethod, String urlString) {
//		if(getHttpRequest() != null) {
//			getHttpRequest().execute();
//		}
	}
	
	/**
	 * 
	 * @param urlString
	 */
	public void execute(String urlString) {
		execute(null, urlString);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
}
