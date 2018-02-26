package lakra;

import java.net.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: rohtash.singh
 * Date: Jun 16, 2005
 * Time: 2:52:23 PM
 * To change this template use Options | File Templates.
 */

class SenderThread extends Thread {
	private InetAddress server;
	private int port;
	private DatagramSocket socket;
	private boolean stopped = false;

	public SenderThread(InetAddress server, int port) throws SocketException {
		this.server = server;
		this.port = port;
		this.socket = new DatagramSocket();
	}

	public void halt() {
		this.stopped = true;
	}

	public DatagramSocket getSocket() {
		return this.socket;
	}

	public void run() {
		try {
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				if (stopped)
					return;
				String theLine = userInput.readLine();
				if (theLine.equals("."))
					break;
				byte[] data = theLine.getBytes();
				DatagramPacket output = new DatagramPacket(data, data.length, server, port);
				socket.send(output);
				Thread.yield();
			}//end while
		}  // end try
		catch (IOException e) {
			System.err.println(e);
		}
	}  // end run
} //end Sender Thread
