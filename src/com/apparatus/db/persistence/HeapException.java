package com.apparatus.db.persistence;

/**
 * heap exception contains information about the file state<br>
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:38:17 PM
 */
public class HeapException extends Exception {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public HeapException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public HeapException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public HeapException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public HeapException(final Throwable cause) {
		super(cause);
	}
}
