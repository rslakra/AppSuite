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

import java.io.*;
import java.security.*;
import javax.net.ssl.*;

public class SSLServer {
	
   public static void main(String[] args) {
   	char ksPass[] = "rohtashlakra".toCharArray();
   	char ctPass[] = ksPass;

    BufferedWriter out;
    BufferedReader in ;
   	
      try {
         KeyStore ks = KeyStore.getInstance("JKS");
         ks.load(new FileInputStream("lakra/net/serverCert"), ksPass);
         System.out.println(ks.getProvider());
         KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
         kmf.init(ks, ctPass);
         SSLContext sc = SSLContext.getInstance("SSLv3");
         sc.init(kmf.getKeyManagers(), null, null);
         SSLServerSocketFactory ssf = sc.getServerSocketFactory();
         SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(1601);
         printServerSocketInfo(s);
         SSLSocket c = (SSLSocket) s.accept();
         printSocketInfo(c);
         out = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
         in = new BufferedReader(new InputStreamReader(c.getInputStream()));
         String msg = "Welcome to SSL Server:\n";
         out.write(msg,0,msg.length());
         out.flush();
         while((msg = in.readLine())!= null) {
            if (msg.equals(".")) break;
            char[] a = msg.toCharArray();
            int n = a.length;
            for (int i=0; i<n/2; i++) {
               char t = a[i];
               a[i] = a[n-1-i];
               a[n-i-1] = t;
            }
            out.write(a,0,n);
            out.newLine();
            out.flush();
         }
         out.close();
         in.close();
         c.close();
         s.close();
      } catch (Exception e) {
         System.err.println(e.toString());
      }
   }
   private static void printSocketInfo(SSLSocket s) {
    System.out.println("\n========================================\n");
      System.out.println("Socket class: "+s.getClass());
      System.out.println("   Remote address = "
         +s.getInetAddress().toString());
      System.out.println("   Remote port = "+s.getPort());
      System.out.println("   Local socket address = "
         +s.getLocalSocketAddress().toString());
      System.out.println("   Local address = "
         +s.getLocalAddress().toString());
      System.out.println("   Local port = "+s.getLocalPort());
      System.out.println("   Need client authentication = "
         +s.getNeedClientAuth());
      SSLSession ss = s.getSession();
      System.out.println("   Cipher suite = "+ss.getCipherSuite());
      System.out.println("   Protocol = "+ss.getProtocol());
      System.out.println("\n========================================");
   }
   private static void printServerSocketInfo(SSLServerSocket s) {
    System.out.println("\n========================================\n");
      System.out.println("Server socket class: "+s.getClass());
      System.out.println("   Socker address = "
         +s.getInetAddress().toString());
      System.out.println("   Socker port = "
         +s.getLocalPort());
      System.out.println("   Need client authentication = "
         +s.getNeedClientAuth());
      System.out.println("   Want client authentication = "
         +s.getWantClientAuth());
      System.out.println("   Use client mode = "
         +s.getUseClientMode());
      System.out.println("\n========================================");
   } 
}

