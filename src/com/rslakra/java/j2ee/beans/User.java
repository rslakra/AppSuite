/***************************************************************************
 * Created By : rohtash.singh
 * Created on : May 31, 2006
 **************************************************************************/
package org.crocus.corm.j2ee.beans;

/**
 * TODO Auto-generated comments.
 * 
 * @author rohtash.singh
 * @version May 31, 2006
 * 
 */
public class User {
	private String name;
	private String password;
	
	public User() {
		
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "User - Name:" + getName() + ", Password:" + getPassword();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setName("Rohtash Singh");
		user.setPassword("Test");
		System.out.println(user);
	}
}
