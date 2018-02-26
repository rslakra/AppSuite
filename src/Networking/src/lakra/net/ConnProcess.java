/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lakra.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConnProcess extends Thread {
	Socket client;
	BufferedWriter out;
	BufferedReader in;

	public ConnProcess(Socket socket) {
		client = socket;
		try {
			out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		this.start();
	}
	
	public void run() {
		String line;
		try {
			while((line = in.readLine()) != null) {
				out.write(line);
			}				
			out.close();
			in.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
