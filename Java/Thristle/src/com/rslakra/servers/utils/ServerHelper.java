package com.rslakra.servers.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devmatre.core.utils.StringUtil;
import com.rslakra.servers.Constants.Keys;
import com.rslakra.servers.Constants.Values;

public class ServerHelper {
	
	/**
	 * Returns the value for the specified <code>paramString</code> of the
	 * specified <code>servletRequest</code>.
	 * 
	 * @param servletRequest
	 * @param paramString
	 * @return
	 * @throws IOException
	 */
	public static String getParameter(HttpServletRequest servletRequest, String paramString) throws IOException {
		return (servletRequest != null ? servletRequest.getParameter(paramString) : null);
	}
	
	/**
	 * 
	 * @param servletResponse
	 * @param paramStatus
	 * @throws IOException
	 */
	public static void setDefaultHeaders(HttpServletResponse servletResponse, int paramStatus) throws IOException {
		// HTTP 1.1.
		servletResponse.setHeader(Keys.CACHE_CONTROL, Values.CACHE_CONTROL);
		// HTTP 1.0.
		servletResponse.setHeader(Keys.PRAGMA, Values.NO_CACHE);
		// Proxies.
		servletResponse.setDateHeader(Keys.EXPIRES, 0);
		// Status
		servletResponse.setStatus(paramStatus);
	}
	
	/**
	 * Sends the specified <code>dataBytes</code> to the
	 * <code>servletResponse</code>.
	 * 
	 * @param servletResponse
	 * @param dataBytes
	 * @param contentType
	 * @throws IOException
	 */
	public static void sendResponse(HttpServletResponse servletResponse, byte[] dataBytes, String contentType) throws IOException {
		setDefaultHeaders(servletResponse, HttpServletResponse.SC_OK);
		if(!StringUtil.isNullOrEmpty(contentType)) {
			servletResponse.setContentType(contentType);
		}
		
		ServletOutputStream servletOutput = servletResponse.getOutputStream();
		servletResponse.setContentLength(dataBytes.length);
		servletOutput.write(dataBytes);
		servletOutput.flush();
		servletOutput.close();
	}
}
