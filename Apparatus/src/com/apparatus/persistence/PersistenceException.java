package com.apparatus.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 1:17:09 PM
 */
public class PersistenceException extends Exception {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public PersistenceException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public PersistenceException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public PersistenceException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public PersistenceException(final Throwable cause) {
		super(cause);
	}
}
