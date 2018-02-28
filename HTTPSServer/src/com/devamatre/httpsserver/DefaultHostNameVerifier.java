/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009-2018.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.httpsserver;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * This <code>DefaultHostNameVerifier</code> disables the host name verification
 * for the demo purposes. If you wish to use this code in production, make sure
 * you verifiy the host as per your certificate.
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @created 2015-05-28 11:23:48 AM
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultHostNameVerifier implements HostnameVerifier {

	/** hostNames */
	private String[] hostNames;

	/**
	 * 
	 * @param hostNames
	 */
	public DefaultHostNameVerifier(final String[] hostNames) {
		this.hostNames = hostNames;
	}

	/**
	 * Default Constructor.
	 */
	public DefaultHostNameVerifier() {
		this(null);
	}

	/**
	 * @param hostname
	 * @param session
	 * @return
	 * @see javax.net.ssl.HostnameVerifier#verify(java.lang.String,
	 *      javax.net.ssl.SSLSession)
	 */
	@Override
	public boolean verify(String hostname, SSLSession session) {
		if (hostNames != null) {

		}

		return true;
	}

}
