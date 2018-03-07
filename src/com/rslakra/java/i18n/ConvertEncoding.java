package com.rslakra.java.i18n;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/** A program to convert from one character encoding to another */
public class ConvertEncoding {
	public static void main(String[] args) {
		String from = null, to = null;
		String infile = null, outfile = null;
		for (int i = 0; i < args.length; i++) { // Parse command-line arguments.
			if (i == args.length - 1)
				usage(); // All args require another.
			if (args[i].equals("-from"))
				from = args[++i];
			else if (args[i].equals("-to"))
				to = args[++i];
			else if (args[i].equals("-in"))
				infile = args[++i];
			else if (args[i].equals("-out"))
				outfile = args[++i];
			else
				usage();
		}
		
		try {
			convert(infile, outfile, from, to);
		} // Attempt conversion.
		catch (Exception e) { // Handle exceptions.
			LocalizedError.display(e); // Defined at the end of this chapter.
			System.exit(1);
		}
	}
	
	public static void usage() {
		System.err.println("Usage: java ConvertEncoding <options>\n" + "Options:\n\t-from <encoding>\n\t" + "-to <encoding>\n\t" + "-in <file>\n\t-out <file>");
		System.exit(1);
	}
	
	public static void convert(String infile, String outfile, String from, String to) throws IOException, UnsupportedEncodingException {
		// Set up byte streams.
		InputStream in;
		if (infile != null)
			in = new FileInputStream(infile);
		else
			in = System.in;
		OutputStream out;
		if (outfile != null)
			out = new FileOutputStream(outfile);
		else
			out = System.out;
		
		// Use default encoding if no encoding is specified.
		if (from == null)
			from = System.getProperty("file.encoding");
		if (to == null)
			to = System.getProperty("file.encoding");
		
		// Set up character streams.
		Reader r = new BufferedReader(new InputStreamReader(in, from));
		Writer w = new BufferedWriter(new OutputStreamWriter(out, to));
		
		// Copy characters from input to output. The InputStreamReader
		// converts from the input encoding to Unicode, and the
		// OutputStreamWriter converts from Unicode to the output encoding.
		// Characters that cannot be represented in the output encoding are
		// output as '?'
		char[] buffer = new char[4096];
		int len;
		while ((len = r.read(buffer)) != -1) // Read a block of input.
			w.write(buffer, 0, len); // And write it out.
		r.close(); // Close the input.
		w.close(); // Flush and close output.
	}
}
