package com.rslakra.http;

import java.io.IOException;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 10:33:42 AM
 */
public class GetRequest extends BaseHttpRequest {
	
	/**
	 * @see com.rslakra.http.HttpRequest#execute(com.rslakra.http.ResponseHandler)
	 */
	@Override
	public void execute(ResponseHandler responseHandler) {
		HttpResponse httpResponse = super.executeGetRequest(null, null, true);
		if(responseHandler != null) {
			try {
				responseHandler.onResponse(httpResponse);
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * @see com.rslakra.http.HttpRequest#execute()
	 */
	@Override
	public void execute() {
		execute(null);
	}
	
}
