/*
 * Created on May 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package lakra.net;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/**
 * @author rohtash.singh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
*/
public class HTTPClient {
    public static void main(String[] args) {
        try {
//            // Check the arguments
//            if ((args.length != 1) && (args.length != 2))
//                throw new IllegalArgumentException("Wrong number of args");
//            
        	// Get an output stream to write the URL contents to
            OutputStream to_file; 
//            to_file = new FileOutputStream(args[1]);
            to_file = System.out;
            
            // Now use the URL class to parse the user-specified URL into
            // its various parts.  
            URL url = new URL("http://10.0.61.151:1601");
            String protocol = url.getProtocol();
            if (!protocol.equals("http")) // Check that we support the protocol
               throw new IllegalArgumentException("Must use 'http:' protocol");
            String host = url.getHost();
            int port = url.getPort();
            if (port == -1) port = 80; // if no port, use the default HTTP port
            String filename = url.getFile();

            // Open a network socket connection to the specified host and port
            Socket socket = new Socket(host, port);

            // Get input and output streams for the socket
            InputStream from_server = socket.getInputStream();
            PrintWriter to_server = new PrintWriter(socket.getOutputStream());
            
            // Send the HTTP GET command to the Web server, specifying the file
            // This uses an old and very simple version of the HTTP protocol
            to_server.print("GET " + filename + "\n\n");
            to_server.flush();  // Send it right now!
            
            // Now read the server's response, and write it to the file
            byte[] buffer = new byte[4096];
            int bytes_read;
            while((bytes_read = from_server.read(buffer)) != -1)
                to_file.write(buffer, 0, bytes_read);
            
            // When the server closes the connection, we close our stuff
            socket.close();
            to_file.close();
        }
        catch (Exception e) {    // Report any errors that arise
        	e.printStackTrace();
        }
    }
}
