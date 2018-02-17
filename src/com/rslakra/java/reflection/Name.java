/**
 * 
 */
package com.rslakra.java.reflection;

/**
 * @author Rohtash Singh Lakra
 */
public class Name {
	private String firstName;
	private String middleName;
	private String lastName;
	
	/**
	 * 
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public Name(final String firstName, final String middleName, final String lastName) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Name(final String firstName, final String lastName) {
		this(firstName, null, lastName);
	}
	
	/**
	 * Returns the firstName.
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * The firstName to be set.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Returns the middleName.
	 *
	 * @return middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	/**
	 * The middleName to be set.
	 * 
	 * @param middleName
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * Returns the lastName.
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * The lastName to be set.
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		if(getFirstName() != null) {
			sBuilder.append(getFirstName());
		}
		
		if(getMiddleName() != null) {
			sBuilder.append(" ").append(getMiddleName());
		}
		
		if(getLastName() != null) {
			sBuilder.append(" ").append(getLastName());
		}
		
		return sBuilder.toString();
	}
}
