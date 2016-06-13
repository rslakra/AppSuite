package com.rslakra.servers.thristle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rslakra.servers.Constants.Values;
import com.rslakra.servers.utils.ServerHelper;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 */
	public BaseServlet() {
	}
	
	/**
	 * 
	 * @param paramString
	 * @return
	 */
	public RequestDispatcher getRequestDispatcher(String paramString) {
		return getServletContext().getRequestDispatcher(paramString);
	}
	
	/**
	 * 
	 * @param servletResponse
	 */
	public void setDefaultContentType(HttpServletResponse servletResponse) {
		servletResponse.setContentType(Values.TEXT_HTML_UTF8);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest servletRequest,
	 *      HttpServletResponse
	 *      servletResponse)
	 */
	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		processRequest(servletRequest, servletResponse);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest servletRequest,
	 *      HttpServletResponse
	 *      servletResponse)
	 */
	protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		processRequest(servletRequest, servletResponse);
	}
	
	/**
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		ServerHelper.setDefaultHeaders(servletResponse, HttpServletResponse.SC_OK);
		// Set the response message's MIME type
		servletResponse.setContentType(Values.TEXT_HTML_UTF8);
	}
	
}
