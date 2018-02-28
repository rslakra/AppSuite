/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
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
package com.devamatre.httpsserver;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * @author Rohtash Singh Lakra (Rohtash.Lakra@nasdaq.com)
 * @date 02/26/2018 05:17:27 PM
 */
public interface Constants {
	int HTTPS_PORT = 443;

	String CERTIFICATE = "rsl.jks";
	String PASSWORD = "password";

	/**
	 * 
	 * @return
	 */
	static String getHost() {
		return "127.0.0.1";
	}

	/**
	 * 
	 * @return
	 */
	static int getPort() {
		return 7516;
	}

	/**
	 * 
	 * @return
	 */
	static String getCertificate() {
		return CERTIFICATE;
	}

	/**
	 * 
	 * @return
	 */
	static char[] keyStorePassword() {
		return PASSWORD.toCharArray();
	}

	/**
	 * 
	 * @param host
	 * @param port
	 * @param sslAllowed
	 * @return
	 */
	static URL newURL(String host, int port, boolean sslAllowed) {
		try {
			if (sslAllowed) {
				return new URL("https://" + host + ":" + port);
			} else {
				return new URL("http://" + host + ":" + port);
			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns the package path of the given class.
	 * 
	 * @param _class
	 * @param withClassName
	 * @return
	 */
	public static String getPkgPath(Class<?> _class, boolean withClassName) {
		if (_class != null) {
			String pkgPath = _class.getPackage().getName().replace(".", File.separator);
			if (withClassName) {
				pkgPath += File.separator + _class.getSimpleName();
			}

			return pkgPath;
		}

		return null;
	}

	/**
	 * Returns the path of the given class.
	 * 
	 * @param _class
	 * @return
	 */
	public static String filePath(Class<?> _class) {
		if (_class != null) {
			String path = getPkgPath(_class, false);
			if (path != null) {
				URL url = _class.getClassLoader().getResource(path);
				if (url != null) {
					path = url.toExternalForm();
					path = path.replace(" ", "%20");
					URI uri = null;
					try {
						uri = new URI(path);
						if (uri.getPath() == null) {
							path = uri.toString();
							if (path.startsWith("jar:file:")) {
								// Update path and define ZIP file
								path = path.substring(path.indexOf("file:/"));
								path = path.substring(0, path.toLowerCase().indexOf(".jar") + 4);
								// Check is UNC path string
								if (path.startsWith("file://")) {
									path = path.substring(path.indexOf("file:/") + 6);
								}
								path = new URI(path).getPath();
							}
						} else {
							path = uri.getPath();
						}
					} catch (URISyntaxException ex) {
						ex.printStackTrace();
					}
				}
			}

			return path;
		}

		return null;
	}

	/**
	 * Returns the full path for the directory and childName.
	 * 
	 * @param parentFolder
	 * @param childName
	 * @return
	 */
	public static String pathForCertificate(Class<?> _class) {
		String parentFolder = filePath(HTTPSServer.class);
		String childName = getCertificate();
		String pathString = null;
		if (parentFolder == null) {
			throw new IllegalArgumentException("Parent directory should not be null/empty!");
		} else {
			/* Removes unnecessary spaces from parentFolder and fileName. */
			pathString = parentFolder.trim();
			if (childName != null) {
				pathString += (childName.startsWith("/") ? "" : File.separator) + childName.trim();
			}
		}

		return pathString;
	}

	/**
	 * Create and initialize the SSLContext
	 * 
	 * @param _class
	 * @return
	 */
	public static SSLContext createSSLContext(Class<?> _class) {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			String certFilePath = pathForCertificate(_class);
			keyStore.load(new FileInputStream(certFilePath), Constants.keyStorePassword());

			// Create key manager
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
			keyManagerFactory.init(keyStore, Constants.keyStorePassword());
			KeyManager[] km = keyManagerFactory.getKeyManagers();

			// Create trust manager
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
			trustManagerFactory.init(keyStore);
			TrustManager[] tm = trustManagerFactory.getTrustManagers();

			// Initialize SSLContext
			SSLContext sslContext = SSLContext.getInstance("TLSv1");
			sslContext.init(km, tm, null);

			return sslContext;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}
