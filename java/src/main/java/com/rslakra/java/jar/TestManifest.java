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
package com.rslakra.java.jar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class TestManifest {
	/** STR_MIDS_VERSION */
	private static final String STR_MIDS_VERSION = "MiDS";

	/** localPrefix */
	private static final String localPrefix = "/usr/local/";

	/** properties */
	// public static MDSConfig config;
	/** myrioiAdapter */
	// public static MyrioIConnector myrioIConnector;
	/** resourceFilePath */
	private String resourceFilePath = System.getProperty("mclient.root") + localPrefix;

	/** resourceFileName */
	private String resourceFileName = null;

	/** servers */
	// private Servers servers;
	/** nameDir */
	private String nameDir = "c:\\naresh\\META-INF";

	/** nameFile */
	private String nameFile = "MANIFEST.MF";

	/** line_1 */
	private String line_1 = "Manifest-Version: 1.0";

	/** line_2 */
	private String line_2 = "Software-Version: MiDS";

	/** line_3 */
	private String line_3 = "Locales: en,es,fr,ja,nl";
	static String manifestFilePath = "c:\\naresh\\META-INF\\MANIFEST.MF";
	static Manifest manifest = null;

	/** line_4 */
	private String line_4 = "Created-By: 1.6.0_03 (Sun Microsystems Inc.)";

	/** line_5 */
	private String line_5 = "Client-Variant: ";

	/** line_6 */
	private String line_6 = "MAC-Address: ";

	/** line_7 */
	private String line_7 = "UI-Version: Standalone-01_std";

	/** meta_error */
	private String meta_error = "ERROR creating the directory META-INF.";

	/** meta_success */
	private String meta_success = "Directory META-INF created with success.";

	/** meta_not_exist */
	private String meta_not_exist = "MANIFEST.MF did not exist and was created.";

	/** meta_exists */
	private String meta_exists = "MANIFEST.MF already exists.";

	public static void main(String[] args) {
		new TestManifest().updateUIVersion();
	}

	public void writeFile(File fileMF, String updatedVersion) {

		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(fileMF));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			out.write(line_1);
			out.newLine();
			out.write(line_2);
			out.newLine();
			out.write(line_3);
			out.newLine();
			out.write(line_4);
			out.newLine();
			out.write(line_5);
			out.newLine();
			out.write(line_6);
			out.newLine();
			if (updatedVersion == null) {
				out.write(line_7);
			} else {
				out.write("UI-Version: " + updatedVersion);
			}
			out.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // end method writeFile

	public void updateUIVersion() {
		String version = "";
		Attributes att = null;
		Manifest manifest = null;
		File path = new File(nameDir);
		File fileMF = new File(path + "\\" + nameFile);
		;
		File file = new File(manifestFilePath);
		if (!file.exists()) {

			if (!path.exists()) {
				boolean success = (path).mkdir();

				if (!success) {
					System.out.println(meta_error);
				} else {
					System.out.println(meta_success);

					try {
						success = fileMF.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}

					if (success) {
						System.out.println(meta_not_exist);
						writeFile(fileMF, null);

					} else {
						System.out.println(meta_exists);
					}
				}
			} // end if

		} // end if

		if (file.exists()) {
			// Load of the MANIFEST.MF
			boolean result = false;
			try {
				manifest = new Manifest(file.toURL().openStream());
				System.out.println("manifest : " + manifest);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if (file.delete() && !result) {
					writeFile(fileMF, null);
				}
			}

			att = manifest.getMainAttributes();
			if (att != null) {
				version = att.getValue("UI-Version");
			}
			// Get the property UI-Version
			// version = properties.getProperty("UI-Version");

			// Splits the version around matches of the "_"
			String[] arrayVersion = version.split("_");

			int arrayLength = arrayVersion.length;

			if (arrayLength >= 2) {

				// String to compare with STR_MIDS_VERSION
				String strMids = arrayVersion[arrayLength - 2];

				// String with the version number
				String strNVersion = arrayVersion[arrayLength - 1];

				// String with the updated version
				String updatedVersion = "";

				// If the current version contains STR_MIDS_VERSION then
				// increment the version number
				if (strMids.equals(STR_MIDS_VERSION)) {
					int nVersion = Integer.parseInt(strNVersion);

					// Increment the version number
					arrayVersion[arrayLength - 1] = String.valueOf(++nVersion);

					// Put the array of strings into the updated version string
					for (int i = 0; i < arrayLength; i++) {
						updatedVersion += arrayVersion[i];

						if (i < (arrayLength - 1)) {
							updatedVersion += "_";
						}
					}
				} else {
					updatedVersion = version + "_" + STR_MIDS_VERSION + "_1";
				}
				file.delete();
				writeFile(fileMF, updatedVersion);
			}
			// } // end if
		} // end if
	} // end method updateUIVersion
}
