package com.rslakra.servers.thristle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rslakra.servers.Constants.Keys;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @see com.rslakra.servers.thristle.BaseServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void processRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
		// get request parameters for userID and password
		String userName = servletRequest.getParameter(Keys.USER_NAME);
		String password = servletRequest.getParameter(Keys.PASSWORD);
		System.out.println("userName:" + userName + ", password:" + password);
		if("rsingh".equals(userName) && "Password".equals(password)) {
			HttpSession session = servletRequest.getSession();
			session.setAttribute(Keys.USER_NAME, "Rohtash");
			// setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30 * 60);
			Cookie cookie = new Cookie(Keys.USER_NAME, userName);
			cookie.setMaxAge(30 * 60);
			servletResponse.addCookie(cookie);
			servletResponse.sendRedirect("LoginSuccess.jsp");
		} else {
			RequestDispatcher requestDispatcher = getRequestDispatcher("/Login.html");
			PrintWriter out = servletResponse.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			requestDispatcher.include(servletRequest, servletResponse);
		}
	}
	
}
