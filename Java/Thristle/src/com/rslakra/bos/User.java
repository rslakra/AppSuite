package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>User</code>
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 15, 2010 2:55:42 PM
 */
public class User extends Person implements Serializable {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** userName */
	private String userName;
	/** firstName */
	private String firstName;
	/** middleName */
	private String middleName;
	/** lastName */
	private String lastName;
	/** email */
	private String email;
	/** password */
	private String password;
	/** role */
	private Role role;
	
	public User() {
		
	}
	
	/**
	 * Returns the userName.
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * The userName to be set.
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		if(this.userName != userName) {
			String oldValue = this.userName;
			this.userName = userName;
			firePropertyChange("userName", oldValue, userName);
		}
	}
	
	/**
	 * Returns the firstName.
	 * 
	 * @return
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
		if(this.firstName != firstName) {
			String oldValue = this.firstName;
			this.firstName = firstName;
			firePropertyChange("firstName", oldValue, firstName);
		}
	}
	
	/**
	 * Returns the middleName.
	 * 
	 * @return
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
		if(this.middleName != middleName) {
			String oldValue = this.middleName;
			this.middleName = middleName;
			firePropertyChange("middleName", oldValue, middleName);
		}
	}
	
	/**
	 * Returns the lastName.
	 * 
	 * @return
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
		if(this.lastName != lastName) {
			String oldValue = this.lastName;
			this.lastName = lastName;
			firePropertyChange("lastName", oldValue, lastName);
		}
	}
	
	/**
	 * Returns the email.
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * The email to be set.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		if(this.email != email) {
			String oldValue = this.email;
			this.email = email;
			firePropertyChange("email", oldValue, email);
		}
	}
	
	/**
	 * Returns the password.
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * The password to be set.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		if(this.password != password) {
			String oldValue = this.password;
			this.password = password;
			firePropertyChange("password", oldValue, password);
		}
	}
	
	/**
	 * Returns the role.
	 * 
	 * @return
	 */
	public Role getRole() {
		return role;
	}
	
	/**
	 * The role to be set.
	 * 
	 * @param role
	 */
	public void setRole(Role role) {
		if(this.role != role) {
			Role oldValue = this.role;
			this.role = role;
			firePropertyChange("role", oldValue, role);
		}
	}
	
	/**
	 * Returns a string representation of this object.
	 * 
	 * @return
	 * @see com.rslakra.bos.BusinessObject#toString()
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("User<Id=").append(getId());
		sBuilder.append(", UserName:").append(getUserName());
		sBuilder.append(", FirstName:").append(getFirstName());
		sBuilder.append(", MiddleName:").append(getMiddleName());
		sBuilder.append(", LastName:").append(getLastName());
		sBuilder.append(", Email:").append(getEmail());
		sBuilder.append(", Password:").append(getPassword());
		sBuilder.append(", Role:").append(getRole());
		sBuilder.append(">");
		return sBuilder.toString();
	}
}
