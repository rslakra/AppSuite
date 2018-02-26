/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lakra.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.parsers.FactoryConfigurationError;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReadHTTPsURL {
	private final static int HTTPS_PORT = 443; //Default port for HTTPS

	public static void main(String[] args) throws Exception {
//		if(args.length != 1) {
//			System.out.println("Usage : java ReadHTTPsURL");
//			System.exit(0);
//		}
		String url = "www.sun.com";
		//The default factory is configured to enable server authentication 
		//only (no client authentication).
		//Get a Socket Factory
		SocketFactory factory = SSLSocketFactory.getDefault();
		//Get Socket from Factory
		Socket socket = factory.createSocket(url, HTTPS_PORT);
		
		//
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer.write("GET /HTTP/3.0 \n\n");
		writer.flush();
		String line;
		StringBuffer buffer = new StringBuffer();
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		writer.close();
		reader.close();
		System.out.println("buffer : " + buffer);

	}//End of Main
}
