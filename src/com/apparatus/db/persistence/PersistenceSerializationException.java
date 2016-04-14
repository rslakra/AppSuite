package com.apparatus.db.persistence;

/**
 * Some object to be serialized does not follow joafip persistence rules or does
 * not implement the java.io.Serializable interface.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:18:39 PM
 */
public class PersistenceSerializationException extends PersistenceException {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public PersistenceSerializationException() {
		super();
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public PersistenceSerializationException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public PersistenceSerializationException(final String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public PersistenceSerializationException(final Throwable cause) {
		super(cause);
	}
}
