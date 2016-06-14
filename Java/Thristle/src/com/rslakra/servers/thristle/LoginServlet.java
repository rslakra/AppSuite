package com.rslakra.servers.thristle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;
import com.rslakra.json.JSONFailure;
import com.rslakra.json.JSONSuccess;
import com.rslakra.servers.Constants.Keys;
import com.rslakra.servers.Constants.Values;
import com.rslakra.servers.utils.JsonUtil;
import com.rslakra.servers.utils.ServerHelper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends BaseServlet {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(LoginServlet.class);
	
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
		
		String serviceName = null;
		String methodName = null;
		String[] params = null;
		String contentType = null;
		try {
			serviceName = ServerHelper.getParameter(servletRequest, Keys.SERVICE_NAME);
			methodName = ServerHelper.getParameter(servletRequest, Keys.METHOD_NAME);
			// params = getParametersForMethod(serviceName, methodName,
			// servletRequest);
			// responseContentType = getResponseType(serviceName, methodName);
		} catch(Exception ex) {
			throw new ServletException(ex);
		}
		
		String userName = servletRequest.getParameter(Keys.USER_NAME);
		String password = servletRequest.getParameter(Keys.PASSWORD);
		System.out.println("userName:" + userName + ", password:" + password);
		if("rsingh".equals(userName) && "Password".equals(password)) {
			HttpSession session = servletRequest.getSession();
			session.setAttribute(Keys.USER_NAME, "Rohtash");
			// setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30 * 60);
			// Cookie cookie = new Cookie(Keys.USER_NAME, userName);
			// cookie.setMaxAge(30 * 60);
			// servletResponse.addCookie(cookie);
			// servletResponse.sendRedirect("/thristle/LoginSuccess.jsp");
			
			JSONSuccess jsonSuccess = new JSONSuccess("OK");
			String jsonString = JsonUtil.jsonString(jsonSuccess);
			ServerHelper.sendResponse(servletResponse, jsonString.getBytes(), Values.APPLICATION_JSON_UTF8);
			
		} else {
			// RequestDispatcher requestDispatcher =
			// getRequestDispatcher("/thristle/Login.html");
			// PrintWriter out = servletResponse.getWriter();
			// out.println("<font color=red>Either user name or password is
			// wrong.</font>");
			// requestDispatcher.include(servletRequest, servletResponse);
			JSONFailure jsonFailure = new JSONFailure("Invalid Username or Password!");
			String jsonString = JsonUtil.jsonString(jsonFailure);
			ServerHelper.sendResponse(servletResponse, jsonString.getBytes(), Values.APPLICATION_JSON_UTF8);
		}
		
		logger.debug("-processRequest()");
	}
	
}
