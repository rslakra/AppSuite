package com.rslakra.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * This class is a BufferedReader that filters out all lines that do not contain
 * the specified pattern.
 **/
public class GrepReader extends BufferedReader {
	String pattern; // The string we are going to be matching.
	
	/** Pass the stream to our superclass, and remember the pattern ourself */
	public GrepReader(Reader in, String pattern) {
		super(in);
		this.pattern = pattern;
	}
	
	/**
	 * This is the filter: call our superclass's readLine() to get the actual
	 * lines, but only return lines that contain the pattern. When the
	 * superclass readLine() returns null (EOF), we return null.
	 **/
	public final String readLine() throws IOException {
		String line;
		do {
			line = super.readLine();
		} while ((line != null) && line.indexOf(pattern) == -1);
		return line;
	}
	
	/**
	 * This class demonstrates the use of the GrepReader class. It prints the
	 * lines of a file that contain a specified substring.
	 **/
	public static class Test {
		public static void main(String args[]) {
			try {
				if (args.length != 2)
					throw new IllegalArgumentException("Wrong number of args");
				GrepReader in = new GrepReader(new FileReader(args[1]), args[0]);
				String line;
				while ((line = in.readLine()) != null)
					System.out.println(line);
				in.close();
			} catch (Exception e) {
				System.err.println(e);
				System.out.println("Usage: java GrepReader$Test" + " <pattern> <file>");
			}
		}
	}
}
