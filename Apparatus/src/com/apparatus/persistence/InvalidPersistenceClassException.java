package com.apparatus.persistence;

/**
 * throws when something is wrong with a class used by serialization
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:08:08 PM
 */
public class InvalidPersistenceClassException extends PersistenceException {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public InvalidPersistenceClassException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public InvalidPersistenceClassException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public InvalidPersistenceClassException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public InvalidPersistenceClassException(final Throwable cause) {
		super(cause);
	}
}
