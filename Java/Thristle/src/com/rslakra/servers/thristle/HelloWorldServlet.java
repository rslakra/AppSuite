package com.rslakra.servers.thristle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;

/**
 * Servlet implementation class HelloWorldServlet
 */
public class HelloWorldServlet extends BaseServlet {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(HelloWorldServlet.class);
	
	/**
	 * Default constructor.
	 */
	public HelloWorldServlet() {
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
		setDefaultResponse(servletRequest, servletResponse, "Hello, World");
	}
	
	/**
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @param title
	 */
	public static void setDefaultResponse(HttpServletRequest servletRequest, HttpServletResponse servletResponse, String title) throws IOException {
		// Allocate a output writer to write the response message into the
		// network socket
		PrintWriter out = servletResponse.getWriter();
		
		// Write the response message, in an HTML page
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html><head>");
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>" + title + "</title></head>");
			out.println("<body>");
			out.println("<h1>" + title + "!</h1>");  // says Hello
			// Echo client's request information
			out.println("<p>Request URI: " + servletRequest.getRequestURI() + "</p>");
			out.println("<p>Protocol: " + servletRequest.getProtocol() + "</p>");
			out.println("<p>PathInfo: " + servletRequest.getPathInfo() + "</p>");
			out.println("<p>Remote Address: " + servletRequest.getRemoteAddr() + "</p>");
			// Generate a random number upon each request
			out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();  // Always close the output writer
		}
	}
	
}
