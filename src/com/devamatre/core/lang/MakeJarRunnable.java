package com.devamatre.core.lang;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * This makes an executable jar from the simele jar file. Usage: MakeJarRunnable
 * <jar file> <Main-Class> <output>
 * 
 * Usage Example: java com.openacircle.utils.MakeJarRunnable WebAnalytics.jar
 * com.openacircle.core.WebAnalytics WebAnalytics_r.jar
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 27/06/2008 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
class MakeJarRunnable {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out
					.println("Usage: MakeJarRunnable <jar file> <Main-Class> <output>");
			System.exit(0);
		}

		try {
			// Access the JAR and its manifest file
			JarInputStream jarIn = new JarInputStream(new FileInputStream(
					args[0]));
			Manifest manifest = jarIn.getManifest();
			if (manifest == null) {
				// if no manifest exists
				manifest = new Manifest();
			}

			Attributes attrs = manifest.getMainAttributes();
			String oldMainClass = attrs.putValue("Main-Class", args[1]);
			// if an old value exists, tell the user and exit
			if (oldMainClass != null) {
				System.out.println("Warning: old Main-Class value is: "
						+ oldMainClass);
				System.exit(1);
			}

			// Output the new JAR
			System.out.println("Writing to : " + args[2] + " ...");
			JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(
					args[2]), manifest);

			// write all entries from the input JAR to output JAR via iterating
			// over the entries
			// Create a read buffer to transfer data from the input
			byte[] buffer = new byte[4096];
			// Iterate the entries
			JarEntry entry;
			while ((entry = jarIn.getNextJarEntry()) != null) {
				// Exclude the manifest file from the old JAR
				if ("META-INF/MANIFEST.MF".equals(entry.getName()))
					continue;
				// Write the entry to the output JAR
				jarOut.putNextEntry(entry);
				int read;
				while ((read = jarIn.read(buffer)) != -1) {
					jarOut.write(buffer, 0, read);
				}
				jarOut.closeEntry();
			}
			// Flush and close all the streams
			jarOut.flush();
			jarOut.close();
			jarIn.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}