package com.rslakra.servers.exceptions;

/**
 * This exception is used to mark any parameter error. When call a method that
 * need some parameter, and one of those parameters is wrong, throw this
 * exception.
 * 
 * @author Rohtash Singh
 * 
 * @since 1.0.0
 */
public class ParameterException extends BusinessException {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_MESSAGE = "Invalid Parameter:";
	
	/**
	 * Constructor for ParameterException
	 * 
	 * @param methodSignature
	 * @param className
	 */
	public ParameterException(String methodSignature, String className) {
		super(ERROR_MESSAGE + " method name: " + methodSignature + " on " + className + " class.");
	}
}