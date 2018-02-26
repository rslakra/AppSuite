package lakra;

import java.net.*;
import java.io.*;

public class UDPServer extends Thread {
	//
	public final static int DEFAULT_PORT = 1601;
	public final static int DEFAULT_BUFFER_SIZE = 8096;
	//
	private int bufferSize = DEFAULT_BUFFER_SIZE; // in bytes
	protected DatagramSocket datagramSocket;

	public UDPServer(int port, int bufferSize) throws SocketException {
		this.bufferSize = bufferSize;
		this.datagramSocket = new DatagramSocket(port);
	}

	public UDPServer(int port) throws SocketException {
		this(port, DEFAULT_BUFFER_SIZE);
	}

	public UDPServer() throws SocketException {
		this(DEFAULT_PORT);
	} //end Default Constructor

	public void run() {
		byte[] buffer = new byte[bufferSize];
		while (true) {
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			try {
				datagramSocket.receive(incoming); //Populdate data in buffer
			    datagramSocket.send(incoming);
				//Print Request on Server
				String message = new String(incoming.getData(), 0, incoming.getLength());
				System.out.println(incoming.getSocketAddress() + " : " + message.toString());
				Thread.yield();
			}
			catch (IOException e) {
				System.err.println(e);
			}
		} // end while
	}  // end run


	//Starting Point of Server
	public static void main(String[] args) {
		try {
	        UDPServer server = new UDPServer();
	        server.start();
	    } catch (SocketException e) {
	        System.err.println(e);
		}
	}

}

