package com.apparatus.db.persistence;

/**
 * data is inconsistent.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:19:55 PM
 */
public class CorruptedDataException extends PersistenceException {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CorruptedDataException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public CorruptedDataException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public CorruptedDataException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public CorruptedDataException(final Throwable cause) {
		super(cause);
	}
}
