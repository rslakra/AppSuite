package org.lakra.crocus.examples.servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;


/**
 * ContactServlet contains a simple main method that creates a connection to the
 * servlet, Posts a request, and prints out the response.
 *
 * @author      rohtash.singh
 * @version 	Mar 28, 2006
 */

public class ContactServlet {

    public static void main(String[] args) {

        try {
            new HelloWorldHttpServet().doPost(null, null);
        } catch (ServletException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        String uRL =
            "http://localhost:8080/org.lakra.crocus.examples.servlets.HelloWorldHttpServet";

        try {
            URL url = new URL(uRL);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            //output
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
                        connection.getOutputStream()));
            output.write("username=Rohtash Singh\n");
            output.flush();
            output.close();

            //input
            BufferedReader input = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
            String response = null;

            while ((response = input.readLine()) != null) {
                System.out.println(response);
            }

            input.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
