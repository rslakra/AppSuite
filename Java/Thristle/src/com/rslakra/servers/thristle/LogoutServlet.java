package com.rslakra.servers.thristle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;
import com.rslakra.servers.Constants.Keys;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends BaseServlet {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(LogoutServlet.class);
	
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
		
		Cookie[] cookies = servletRequest.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(Keys.JSESSIONID)) {
					System.out.println(Keys.JSESSIONID + "=" + cookie.getValue());
					break;
				}
			}
		}
		
		// invalidate the session if exists
		HttpSession session = servletRequest.getSession(false);
		if(session != null) {
			System.out.println("userName=" + session.getAttribute(Keys.USER_NAME));
			session.invalidate();
		}
		servletResponse.sendRedirect("Login.html");
	}
	
}
