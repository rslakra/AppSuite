package com.rslakra.servers.exceptions;

/**
 * This exception is used to mark business rule violations.
 * 
 * @author Rohtash Singh
 * 
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** rootCause */
	private Throwable rootCause;
	
	/**
	 * 
	 */
	public BusinessException() {
		// empty
	}
	
	/**
	 * 
	 * @param error
	 */
	public BusinessException(String error) {
		super(error);
	}
	
	/**
	 * 
	 * @param error
	 * @param rootCause
	 */
	public BusinessException(String error, Throwable rootCause) {
		super(error);
		this.rootCause = rootCause;
	}
	
	/**
	 * Returns the rootCause.
	 * 
	 * @return
	 */
	public Throwable getRootCause() {
		return this.rootCause;
	}
}