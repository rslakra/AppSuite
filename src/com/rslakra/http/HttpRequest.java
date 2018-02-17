package com.rslakra.http;

import java.util.Map;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 10:00:42 AM
 */
public interface HttpRequest {
	
	/**
	 * Returns the headers.
	 * 
	 * @return
	 */
	public Map<String, String> getHeaders();
	
	/**
	 * The headers to be set.
	 * 
	 * @param headers
	 */
	public void setHeaders(Map<String, String> headers);
	
	/**
	 * Returns the parameters.
	 * 
	 * @return
	 */
	public Map<String, Object> getParameters();
	
	/**
	 * The parameters to be set.
	 * 
	 * @param parameters
	 */
	public void setParameters(Map<String, Object> parameters);
	
	/**
	 * Executes the request and calls the <code>responseHandler</code>, it not
	 * null.
	 * 
	 * @param responseHandler
	 */
	public void execute(ResponseHandler responseHandler);
	
	/**
	 * Executes the request.
	 */
	public void execute();
	
}
