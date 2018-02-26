/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lakra.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ProcessConnection extends Thread {
   Socket client;
   BufferedReader bReader;
   DataOutputStream os;
   
   	public ProcessConnection(Socket s) { // constructor
      client = s;
      try {
      		bReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      		os = new DataOutputStream(client.getOutputStream());
      	} catch (IOException e) {
      		System.out.println("Exception: " + e.getMessage());
      	}
      	this.start(); // Thread starts here...this start() will call run()
   	}
 
   	public void run() {
   		try {
         // get a request and parse it.
         String request = bReader.readLine();
         System.out.println( "Request: "+request );
         StringTokenizer st = new StringTokenizer( request );
            if ( (st.countTokens() >= 2) && 
              st.nextToken().equals("GET") ) {
               if ( (request = st.nextToken()).startsWith("/") )
                  request = request.substring( 1 );
               if ( request.equals("") )
                  request = request + "index.html";
               System.out.println("request : " + request);
               File f = new File(request);
               shipDocument(os, f);
            } else {
               os.writeBytes( "400 Bad Request" );
            } 
            client.close();
      } catch (Exception e) {
         System.out.println("Exception: " + e.getMessage());
      } 
   }

  /**
   * Read the requested file and ships it 
   * to the browser if found.
   */
   public static void shipDocument(DataOutputStream out, 
     File f) throws Exception {
       try {
          DataInputStream in = new 
            DataInputStream(new FileInputStream(f));
          int len = (int) f.length();
          byte[] buf = new byte[len];
          in.readFully(buf);
          in.close();
          out.writeBytes("HTTP/1.0 200 OK\r\n");
          out.writeBytes("Content-Length: " + 
            f.length() +"\r\n");
          out.writeBytes("Content-Type: text/html\r\n\r\n");
          out.write(buf);
          out.flush();
       } catch (Exception e) {
          out.writeBytes("<html><head><title>error</title></head><body>\r\n\r\n");
          out.writeBytes("HTTP/1.0 400 " + e.getMessage() + "\r\n");
          out.writeBytes("Content-Type: text/html\r\n\r\n");
          out.writeBytes("</body></html>");
          out.flush();
       } finally {
          out.close();
       }
   }
}
