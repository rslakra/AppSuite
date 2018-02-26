package lakra.net;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.*;

import javax.net.ssl.*;

public class Client implements SSLConnection {

	SSLServerSocket sslServer = null;
	public Client() {
		try {
			SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			SSLSocket client = (SSLSocket)factory.createSocket(SERVER_HOST, HTTPS_PORT);
			//Reader/Writer
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));			
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println("Client : " + line);
				writer.write(line);
			}
			writer.close();
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getCertificate() {
		return null;	
}
	
	public static void main(String[] args) {
		Client client = new Client();
	}
}


