package com.rslakra.http;

import java.io.IOException;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 09:59:01 AM
 */
public interface ResponseHandler {
	
	/**
	 * 
	 * @param httpResponse
	 * @throws IOException
	 */
	public void onResponse(HttpResponse httpResponse) throws IOException;
	
}
