package com.apparatus.persistence;

/**
 * Class of a serialized object cannot be found.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 2:58:50 PM
 */
public class PersistenceClassNotFoundException extends PersistenceException {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public PersistenceClassNotFoundException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public PersistenceClassNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public PersistenceClassNotFoundException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public PersistenceClassNotFoundException(final Throwable cause) {
		super(cause);
	}
}
