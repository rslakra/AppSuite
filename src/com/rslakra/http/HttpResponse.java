package com.rslakra.http;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/07/2017 10:00:19 AM
 */
public class HttpResponse implements Cloneable {
	
	/** The response headers */
	private Map<String, List<String>> requestHeaders;
	
	/* responseCode */
	private int responseCode;
	
	/** responseHeaders */
	private Map<String, List<String>> responseHeaders;
	private String jsonResponseHeaders;
	
	/* dataBytes */
	private byte[] dataBytes;
	
	/* error */
	private Throwable error;
	
	/**
	 * 
	 */
	public HttpResponse() {
		reset();
	}
	
	/**
	 * Returns the requestHeaders.
	 * 
	 * @return
	 */
	public Map<String, List<String>> getRequestHeaders() {
		return requestHeaders;
	}
	
	/**
	 * The requestHeaders to be set.
	 * 
	 * @param requestHeaders
	 */
	public void setRequestHeaders(Map<String, List<String>> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	
	/**
	 * Returns the responseCode.
	 * 
	 * @return
	 */
	public int getResponseCode() {
		return responseCode;
	}
	
	/**
	 * The responseCode to be set.
	 * 
	 * @param responseCode
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	/**
	 * Returns the responseHeaders.
	 * 
	 * @return the responseHeaders
	 */
	public Map<String, List<String>> getResponseHeaders() {
		return responseHeaders;
	}
	
	/**
	 * The responseHeaders to be set.
	 * 
	 * @param responseHeaders the responseHeaders to set
	 */
	public void setResponseHeaders(final Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
		if(!HttpUtils.isNullOrEmpty(responseHeaders)) {
			jsonResponseHeaders = HttpUtils.toJson(responseHeaders);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getJsonResponseHeaders() {
		return jsonResponseHeaders;
	}
	
	/**
	 * The responseHeaders to be set.
	 * 
	 * @param responseHeaders the responseHeaders to set
	 */
	public void setJsonResponseHeaders(byte[] jsonResponseHeaders) {
		if(!HttpUtils.isNullOrEmpty(jsonResponseHeaders)) {
			String jsonResponseHeader = HttpUtils.toUTF8String(jsonResponseHeaders);
			this.responseHeaders = HttpUtils.jsonHeadersAsMap(jsonResponseHeader);
		}
	}
	
	/**
	 * Returns the dataBytes.
	 * 
	 * @return
	 */
	public byte[] getDataBytes() {
		return dataBytes;
	}
	
	/**
	 * The dataBytes to be set.
	 * 
	 * @param dataBytes
	 */
	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}
	
	/**
	 * @return the error
	 */
	public Throwable getError() {
		return error;
	}
	
	/**
	 * @param error the error to set
	 */
	public void setError(Throwable error) {
		this.error = error;
	}
	
	/**
	 * Returns success if my {@link #status} is any of my SUCCESS
	 * states.
	 */
	public boolean isSuccess() {
		return (responseCode == 200);
	}
	
	/**
	 * Returns true if my {@link #status} is any of my ERROR
	 * states, or if my {@link #error} or {@link #failureBO} fields
	 * have anything in them.
	 */
	public boolean isError() {
		return (this.error != null);
	}
	
	/**
	 * Returns the response type.
	 * 
	 * @return
	 */
	public String getMimeType() {
		String mimeType = null;
		
		if(responseHeaders != null) {
			mimeType = responseHeaders.get("Content-Type").get(0);
			if(mimeType.indexOf(";") != -1) {
				mimeType = mimeType.substring(0, mimeType.indexOf(";")).trim();
			}
		}
		
		return mimeType;
	}
	
	/**
	 * Resets this object.
	 */
	public void reset() {
		requestHeaders = null;
		responseCode = 0;
		responseHeaders = null;
		dataBytes = null;
		error = null;
	}
	
	/**
	 * Creates exact copy of an object.
	 * 
	 * @see java.lang.Object#clone()
	 */
	public HttpResponse clone() {
		HttpResponse cloneResponse = null;
		try {
			cloneResponse = (HttpResponse) super.clone();
			cloneResponse.requestHeaders = this.requestHeaders;
			cloneResponse.responseCode = this.responseCode;
			cloneResponse.responseHeaders = this.responseHeaders;
			cloneResponse.jsonResponseHeaders = this.jsonResponseHeaders;
			cloneResponse.dataBytes = this.dataBytes;
			cloneResponse.error = this.error;
		} catch(CloneNotSupportedException ex) {
			// This should never happen
			throw new InternalError(ex.toString());
		}
		
		return cloneResponse;
	}
	
	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("================ HTTP Response (Start) ================\n");
		sBuilder.append("requestHeaders:").append(getRequestHeaders()).append("\n");
		sBuilder.append("ResponseCode:").append(getResponseCode()).append("\n");
		sBuilder.append("ResponseHeaders:").append(getResponseHeaders()).append("\n");
		sBuilder.append("dataBytes:").append(HttpUtils.toUTF8String(getDataBytes())).append("\n");
		
		if(this.error != null) {
			sBuilder.append("\n\n").append(HttpUtils.toString(getError())).append("\n\n");
		}
		
		sBuilder.append("================ HTTP Response (End) ================\n");
		
		return sBuilder.toString();
	}
	
}
