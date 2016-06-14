package com.rslakra.json;

public class JSONSuccess extends JSONBase {
	/** success */
	private String success;
	
	public JSONSuccess() {
	}
	
	/**
	 */
	public JSONSuccess(String success) {
		this.success = success;
	}
	
	/**
	 * 
	 * @see com.rslakra.json.JSONBase#getBOName()
	 */
	@Override
	public String getBOName() {
		return "BOSuccess";
	}
	
}