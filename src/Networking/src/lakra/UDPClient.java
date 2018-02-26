package lakra;

import java.net.*;
import java.io.*;

/**
* Created by IntelliJ IDEA.
* User: rohtash.singh
* Date: Jun 16, 2005
* Time: 11:33:47 AM
* To change this template use Options | File Templates.
*/
public class UDPClient {
	public final static int DEFAULT_PORT = 1601;

	public static void main(String[] args) {
		String hostname = "localhost";
		int port = DEFAULT_PORT;
		try {
			InetAddress iNetAddress = InetAddress.getByName(hostname);
			SenderThread sender = new SenderThread(iNetAddress, port);
			sender.start();
//			ReceiverThread receiver = new ReceiverThread(sender.getSocket());
//			receiver.start();
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (SocketException se) {
			System.err.println(se);
		}
	}  // end main
}

//// Sender Thread
//class SenderThread extends Thread {
//	private InetAddress server;
//	private int port;
//	private DatagramSocket socket;
//	private boolean stopped = false;
//
//	public SenderThread(InetAddress server, int port) throws SocketException {
//		this.server = server;
//		this.port = port;
//		this.socket = new DatagramSocket();
//	}
//
//	public void halt() {
//		this.stopped = true;
//	}
//
//	public DatagramSocket getSocket() {
//		return this.socket;
//	}
//
//	public void run() {
//		try {
//			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//			while (true) {
//				if (stopped)
//					return;
//				String theLine = userInput.readLine();
//				if (theLine.equals("."))
//					break;
//				byte[] data = theLine.getBytes();
//				DatagramPacket output = new DatagramPacket(data, data.length, server, port);
//				socket.send(output);
//				Thread.yield();
//			}//end while
//		}  // end try
//		catch (IOException e) {
//			System.err.println(e);
//		}
//	}  // end run
//} //end Sender Thread

//

class ReceiverThread extends Thread {
	DatagramSocket socket;
	private boolean stopped = false;

	public ReceiverThread(DatagramSocket ds) throws SocketException {
		this.socket = ds;
	}

	public void halt() {
		this.stopped = true;
	}

	public void run() {
		byte[] buffer = new byte[65507];
		while (true) {
			if (stopped)
				return;
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(dp);
				String s = new String(dp.getData(), 0, dp.getLength());
				System.out.println(s);
				Thread.yield();
			}
			catch (IOException e) {
				System.err.println(e);
			}
		} //end while
	} //end run
} // end Receiver Thread


