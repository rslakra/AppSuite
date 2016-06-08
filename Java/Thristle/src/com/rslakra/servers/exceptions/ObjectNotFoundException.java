package com.rslakra.servers.exceptions;

/**
 * This exception is used to mark object not found. When try access a object
 * that was deleted logically. The attribute deleted on target table is not null
 * when it has been deleted.
 * 
 * @author Rohtash Singh
 * 
 * @since 1.0.0
 */
public class ObjectNotFoundException extends BusinessException {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_MESSAGE = "Object does not exist.";
	
	public ObjectNotFoundException() {
		super(ERROR_MESSAGE);
	}
}