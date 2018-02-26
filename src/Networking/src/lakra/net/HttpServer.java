/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lakra.net;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

/** 
 * This class implements a multithreaded simple HTTP server that supports 
 * the GET request method. It listens on port 44, waits client requests, 
 * and serves documents.
 */

public class HttpServer {
   // The port number which the server will be listening on
   public static final int HTTP_PORT = 8080;

   public ServerSocket getServer() throws Exception {
      return new ServerSocket(HTTP_PORT);
   }

   // multi-threading -- create a new connection for each request
   public void run() {
      ServerSocket listen;
      try {
         listen = getServer();
         while(true) {
            Socket client = listen.accept();
//            ProcessConnection cc = new ProcessConnection(client);
         }
      } catch(Exception e) {
         System.out.println("Exception:"+e.getMessage());
      }
   }

   // main program
   public static void main(String argv[]) throws 
     Exception {
      HttpServer httpserver = new HttpServer();
      httpserver.run();
   }
}

