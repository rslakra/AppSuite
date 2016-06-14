package com.rslakra.json;

import com.devmatre.core.utils.CoreUtil;

public class JSONFailure extends JSONBase {
	
	/** message */
	private String message;
	/** exceptionName */
	private String errorName;
	
	public JSONFailure() {
	}
	
	/**
	 * 
	 * @param message
	 */
	public JSONFailure(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @param ex
	 */
	public JSONFailure(Exception ex) {
		String message = ex.getMessage();
		Throwable cause = ex.getCause();
		if(cause != null) {
			message = cause.getMessage();
		}
		
		if(CoreUtil.isNull(message)) {
			message = ex.toString();
		}
		
		if(message.indexOf("<br>") != -1) {
			message = message.replaceAll("<br>", "\n");
		} else if(message.indexOf("<br/>") != -1) {
			message = message.replaceAll("<br/>", "\n");
		} else if(message.indexOf("<BR>") != -1) {
			message = message.replaceAll("<BR>", "\n");
		} else if(message.indexOf("<BR/>") != -1) {
			message = message.replaceAll("<BR/>", "\n");
		}
		
		this.message = message;
		this.errorName = ex.getClass().getSimpleName();
	}
	
	/**
	 * Returns the message.
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Returns the errorName.
	 * 
	 * @return
	 */
	public String getErrorName() {
		return errorName;
	}
	
	/**
	 * 
	 * @see com.rslakra.json.BaseJSONClass#getBOName()
	 */
	@Override
	public String getBOName() {
		return "BOFailure";
	}
	
}