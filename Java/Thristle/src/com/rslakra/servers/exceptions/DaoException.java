package com.rslakra.servers.exceptions;

/**
 * This exception is used to mark any DAO exception.
 * 
 * @author Rohtash Singh
 * 
 * @since 1.0.0
 */
public class DaoException extends BusinessException {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	public static final String DAO_ERROR_MESSAGE = "DAO Exception-";
	
	/**
	 * 
	 * @param errorMessage
	 */
	public DaoException(String errorMessage) {
		super(DAO_ERROR_MESSAGE + errorMessage);
	}
	
	/**
	 * 
	 * @param errorMessage
	 * @param exception
	 */
	public DaoException(String errorMessage, Exception exception) {
		super(DAO_ERROR_MESSAGE + errorMessage, exception);
	}
}