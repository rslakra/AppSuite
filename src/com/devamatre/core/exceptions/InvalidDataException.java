/******************************************************************************
 * Copyright (C) Devamatre Technologies 2009
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
package com.devamatre.core.exceptions;

/**
 * An error occurred while storing a record whose underlying data had already
 * been changed
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvalidDataException extends DataAccessException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2782270538328476444L;

	/**
	 * Constructs a new <code>InvalidDataException</code> exception with
	 * <code>null</code> as its detail message. The cause is not initialized,
	 * and may subsequently be initialized by a call to {@link #initCause}.
	 */
	public InvalidDataException() {
		super();
	}

	/**
	 * Constructs a new <code>InvalidDataException</code> exception with the
	 * specified detail message. The cause is not initialized, and may
	 * subsequently be initialized by a call to {@link #initCause}.
	 * 
	 * @param message
	 */
	public InvalidDataException(String message) {
		super(message);
	}

	/**
	 * Constructs a new <code>InvalidDataException</code> exception with the
	 * specified detail message and cause.
	 * 
	 * @param message
	 * @param cause
	 */
	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new <code>InvalidDataException</code> exception with the
	 * specified cause and a detail message of
	 * <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for runtime exceptions that are little more than
	 * wrappers for other throwables.
	 * 
	 * @param cause
	 */
	public InvalidDataException(Throwable cause) {
		super(cause);
	}
}
