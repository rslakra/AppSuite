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
import javax.net.ssl.*;

public class Server implements SSLConnection {
	
	SSLServerSocket sslServer = null;
	public Server() {
		try {
			SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			sslServer = (SSLServerSocket)factory.createServerSocket(HTTPS_PORT);
			SSLSocket client= (SSLSocket)sslServer.accept();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String line;
			while((line = in.readLine()) != null) {
				System.out.println("Server: " + line);
				out.write(line);
			}
			out.close();
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCertificate() {
			return null;	
	}
	
	public static void main(String[] args) {
		Server serv = new Server();
	}
}


