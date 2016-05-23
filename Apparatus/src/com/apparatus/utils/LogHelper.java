package com.apparatus.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created Apr 14, 2016 11:43:13 AM
 * 
 */
public class LogHelper {
	
	/**
	 * 
	 * @param httpSession
	 */
	public static void printSessionAttributes(HttpSession httpSession) {
		if(httpSession != null) {
			System.out.println("\nPrinting Session Attributes\n");
			System.out.println("SessionID:" + httpSession.getId());
			@SuppressWarnings("unchecked")
			Enumeration<String> attributeNames = httpSession.getAttributeNames();
			while(attributeNames.hasMoreElements()) {
				String name = (String) attributeNames.nextElement();
				Object value = httpSession.getAttribute(name);
				System.out.println(name + ":" + value);
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 */
	public static void printRequestHeaders(HttpServletRequest request) {
		if(request != null) {
			System.out.println("\nPrinting Request Headers\n");
			@SuppressWarnings("unchecked")
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String name = (String) headerNames.nextElement();
				Object value = request.getHeader(name);
				System.out.println(name + ":" + value);
			}
		}
	}
	
	public static void printRequestParameters(HttpServletRequest request) {
		if(request != null) {
			System.out.println("\nPrinting Request Parameters\n");
			@SuppressWarnings("unchecked")
			Enumeration<String> parameterNames = request.getParameterNames();
			while(parameterNames.hasMoreElements()) {
				String name = (String) parameterNames.nextElement();
				Object value = request.getParameter(name);
				System.out.println(name + ":" + value);
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 */
	public static void printRequestAttributes(HttpServletRequest request) {
		if(request != null) {
			System.out.println("\nPrinting Request Attributes\n");
			@SuppressWarnings("unchecked")
			Enumeration<String> attributeNames = request.getAttributeNames();
			while(attributeNames.hasMoreElements()) {
				String name = (String) attributeNames.nextElement();
				Object value = request.getAttribute(name);
				System.out.println(name + ":" + value);
			}
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param includeSession
	 */
	public static void printRequest(HttpServletRequest request, boolean includeSession) {
		if(request != null) {
			System.out.println("\n\n\n");
			System.out.println("Printing Request\n");
			System.out.println("RequestURI:" + request.getRequestURI());
			printRequestHeaders(request);
			printRequestAttributes(request);
			printRequestParameters(request);
			if(includeSession) {
				printSessionAttributes(request.getSession(true));
			}
			System.out.println("\n\n\n");
		}
	}
}
