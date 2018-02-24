/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.java.io;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * This makes an executable jar from the simele jar file.
 * Usage: MakeJarRunnable <jar file> <Main-Class> <output>
 * 
 * Usage Example:
 * java com.openacircle.utils.MakeJarRunnable WebAnalytics.jar com.openacircle.core.WebAnalytics WebAnalytics_r.jar
 * 
 * @author Rohtahs Singh (rohtash.lakra@devamatre.com)
 * @version 2008/06/27
 */
class MakeJarRunnable {
	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Usage: MakeJarRunnable <jar file> <Main-Class> <output>");
			System.exit(0);
		}
		
		try {
			//Access the JAR and its manifest file
			JarInputStream jarIn = new JarInputStream(new FileInputStream(args[0]));
			Manifest manifest = jarIn.getManifest();
			if(manifest == null) {
				//if no manifest exists
				manifest = new Manifest();
			}
	
			Attributes attrs = manifest.getMainAttributes();
			String oldMainClass = attrs.putValue("Main-Class", args[1]);
			//if an old value exists, tell the user and exit
			if(oldMainClass != null) {
				System.out.println("Warning: old Main-Class value is: " + oldMainClass);
				System.exit(1);
			}
	
			//Output the new JAR
			System.out.println("Writing to : " + args[2] + " ...");
			JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(args[2]), manifest);
	
			//write all entries from the input JAR to output JAR via iterating over the entries
			//Create a read buffer to transfer data from the input
			byte[] buffer = new byte[4096];
			//Iterate the entries
			JarEntry entry;
			while ((entry = jarIn.getNextJarEntry()) != null) {
				//Exclude the manifest file from the old JAR
				if ("META-INF/MANIFEST.MF".equals(entry.getName())) continue;
				//Write the entry to the output JAR
				jarOut.putNextEntry(entry);
				int read;
				while ((read = jarIn.read(buffer)) != -1) {
					jarOut.write(buffer, 0, read);
				}
				jarOut.closeEntry();
			}
			//Flush and close all the streams
			jarOut.flush();
			jarOut.close();
			jarIn.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
