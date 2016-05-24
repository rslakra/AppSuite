package com.rslakra.servers.thristle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 */
	public BaseServlet() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// HTTP 1.1.
		servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		servletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		servletResponse.setDateHeader("Expires", 0); // Proxies.
		// Set the response message's MIME type
		servletResponse.setContentType("text/html;charset=UTF-8");
		
		// // Allocate a output writer to write the response message into the
		// // network socket
		// PrintWriter out = servletResponse.getWriter();
		//
		// // Write the response message, in an HTML page
		// try {
		// out.println("<!DOCTYPE html>");
		// out.println("<html><head>");
		// out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		// out.println("<title>Hello, World</title></head>");
		// out.println("<body>");
		// out.println("<h1>Hello, world!</h1>"); // says Hello
		// // Echo client's request information
		// out.println("<p>Request URI: " + servletRequest.getRequestURI() +
		// "</p>");
		// out.println("<p>Protocol: " + servletRequest.getProtocol() + "</p>");
		// out.println("<p>PathInfo: " + servletRequest.getPathInfo() + "</p>");
		// out.println("<p>Remote Address: " + servletRequest.getRemoteAddr() +
		// "</p>");
		// // Generate a random number upon each request
		// out.println("<p>A Random Number: <strong>" + Math.random() +
		// "</strong></p>");
		// out.println("</body>");
		// out.println("</html>");
		// } finally {
		// out.close(); // Always close the output writer
		// }
	}
	
}
