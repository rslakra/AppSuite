package com.rslakra.bos;

/**
 * The <code>Gender</code> defines the genders.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 15, 2010 12:52:56 PM
 */
public enum Gender {
	
	/* Male */
	NONE("None"),
	
	/* Male */
	MALE("Male"),
	
	/* Female */
	FEMALE("Female");
	
	/**
	 * 
	 * @param value
	 */
	Gender(String value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/* value */
	private String value;
}