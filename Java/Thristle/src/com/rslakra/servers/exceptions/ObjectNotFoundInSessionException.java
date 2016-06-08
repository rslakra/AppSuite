package com.rslakra.servers.exceptions;

/**
 * This exception is used to mark object not found in the current session. When
 * try access a object that was not in the session for some unexpected reason.
 * 
 * @author Rohtash Singh
 * 
 * @since 1.0.0
 */
public class ObjectNotFoundInSessionException extends BusinessException {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_MESSAGE = "Object does not exist in the session.";
	
	public ObjectNotFoundInSessionException(Exception excp) {
		super(ERROR_MESSAGE, excp);
	}
}