package com.apparatus.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:09:42 PM
 */
public class PersistenceRuntimeException extends RuntimeException {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public PersistenceRuntimeException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public PersistenceRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public PersistenceRuntimeException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public PersistenceRuntimeException(final Throwable cause) {
		super(cause);
	}
}
