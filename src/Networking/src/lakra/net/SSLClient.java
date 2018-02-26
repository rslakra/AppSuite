/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lakra.net;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * SslSocketClient.java
 * Copyright (c) 2005 by Dr. Herong Yang
 */
import java.io.*;
import javax.net.ssl.*;

public class SSLClient {
    
    public static void main(String[] args) {
        PrintStream out = System.out;
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
    	try {
         SSLSocket client =(SSLSocket) f.createSocket("10.0.61.151", 1601);
         printSocketInfo(client);
         client.startHandshake();
         BufferedWriter w = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
         BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
         String msg = "Welcome to Client:\n";
         while ((msg = r.readLine())!= null) {
            out.println(msg);
            msg = in.readLine();
            w.write(msg,0,msg.length());
            w.newLine();
            w.flush();
         }
         w.close();
         r.close();
         client.close();
      } catch (IOException e) {
         System.err.println(e.toString());
      }
   }
    
   private static void printSocketInfo(SSLSocket s) {
      System.out.println("Socket class: "+ s.getClass());
      System.out.println("Remote address = "+ s.getInetAddress());
      System.out.println("Remote port = "+ s.getPort());
      System.out.println("Local socket address = "+ s.getLocalSocketAddress());
      System.out.println("Local address = "+ s.getLocalAddress());
      System.out.println("Local port = "+ s.getLocalPort());
      System.out.println("Need client authentication = "+ s.getNeedClientAuth());
      SSLSession ss = s.getSession();
      System.out.println("Cipher suite = "+ ss.getCipherSuite());
      System.out.println("Protocol = "+ ss.getProtocol());
   }
}
