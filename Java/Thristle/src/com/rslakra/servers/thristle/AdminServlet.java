package com.rslakra.servers.thristle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;

/**
 * Servlet implementation class HelloWorldServlet
 */
public class AdminServlet extends BaseServlet {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(AdminServlet.class);
	
	/**
	 * Default constructor.
	 */
	public AdminServlet() {
	}
	
	/**
	 * @param servletRequest
	 * @param servletResponse
	 * @throws ServletException
	 * @throws IOException
	 * @see com.rslakra.servers.thristle.BaseServlet#processRequest(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void processRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		logger.debug("+processRequest(" + servletRequest + ", " + servletResponse + ")");
		super.processRequest(servletRequest, servletResponse);
		HelloWorldServlet.setDefaultResponse(servletRequest, servletResponse, "Hello, Admin");
	}
	
}
