package org.lakra.crocus.examples.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Q.: Can I use a Java application (client) instead of a JSP (JavaServer Page)
 * to invoke a servlet on an application server?
 *
 * Ans.: Yes, you can invoke a servlet from a Java client by simply using the
 * java.net.URL and java.net.URLConnection classes.
 *
 *
 * @author      rohtash.singh
 * @version 	Mar 28, 2006
 */

public class HelloWorldHttpServet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {


    }

}
