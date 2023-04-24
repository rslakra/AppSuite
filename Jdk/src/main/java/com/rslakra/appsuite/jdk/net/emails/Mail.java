/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
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
package com.rslakra.appsuite.jdk.net.emails;

/**
 * 
 * @author Rohtash Lakra (rohtash.lakra@devamatre.com)
* @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @created 2008-06-12 02:49:08 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class Mail {
	public Mail() {
	}

	public static void main(String[] args) {
		String a[] = { "mail", "-s", "server", "-t ", "rohtash.singh@gmail.com, ", "-f ",
				"rohtash.lakra@devamatre.com", "-a", "Hello Rohtash", "-m", args[0] };

		try {

			for (int i = 1; i < args.length; i++) {
				a[4] = a[4] + args[i];
			}

			// mail -s server -t
			// rohtash.singh@gmail.com,rohtash.lakra@devamatre.com -f
			// rohtashlakra@yahoo.com -a "Hello Rohtash" -m mail.txt
			Runtime.getRuntime().exec(a);
		} catch (Exception e) {
			System.out.println("Exception " + e);
		}
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[4] + " ");
	}
}
