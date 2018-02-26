package lakra;

import java.net.*;
import java.io.*;

public class Client extends Thread  {
	public final static int DEFAULT_PORT = 1601;
	public final static String DEFAULT_HOST_NAME = "localhost";
	private InetAddress server;

	private DatagramSocket socket;
	private String hostName;
	private int port;

	public Client(String hostName, int port) throws SocketException {
		this.hostName = hostName;
		this.port = port;
		this.socket = new DatagramSocket();
		try {
			server = InetAddress.getByName(hostName);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public Client(int port) throws SocketException {
		this(DEFAULT_HOST_NAME, port);
	}

	public Client() throws SocketException {
		this(DEFAULT_PORT);
	} //end Default Constructor


	public void run() {
		try {
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
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


	public static void main(String[] args) {
		try {
			Client client = new Client();
			client.start();
		}catch(SocketException ex) {
			ex.printStackTrace();
		}
//		String hostname = "localhost";
//		int port = DEFAULT_PORT;
//		try {
//			InetAddress iNetAddress = InetAddress.getByName(hostname);
//			SenderThread sender = new SenderThread(iNetAddress, port);
//			sender.start();
//		} catch (UnknownHostException e) {
//			System.err.println(e);
//		} catch (SocketException se) {
//			System.err.println(se);
//		}
	}  // end main
}


//class ReceiverThread extends Thread {
//	DatagramSocket socket;
//	private boolean stopped = false;
//
//	public ReceiverThread(DatagramSocket ds) throws SocketException {
//		this.socket = ds;
//	}
//
//	public void halt() {
//		this.stopped = true;
//	}
//
//	public void run() {
//		byte[] buffer = new byte[65507];
//		while (true) {
//			if (stopped)
//				return;
//			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
//			try {
//				socket.receive(dp);
//				String s = new String(dp.getData(), 0, dp.getLength());
//				System.out.println(s);
//				Thread.yield();
//			}
//			catch (IOException e) {
//				System.err.println(e);
//			}
//		} //end while
//	} //end run
//} // end Receiver Thread


