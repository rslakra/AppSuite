package com.rslakra.bos;

/**
 * Gender.java
 * 
 * The <code>Gender</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 15, 2010 12:52:56 PM
 */
public enum Gender {

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