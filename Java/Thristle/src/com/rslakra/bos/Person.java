package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>Person</code> class is the base class for employee and user
 * classes.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 15, 2010 2:57:10 PM
 */
public class Person extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** user */
	private User user;
	
	/** permanentAddress */
	private Address permanentAddress;
	
	/** mailingAddress */
	private Address mailingAddress;
	
	/**
	 * Returns the user.
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * The user to be set.
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		if(this.user != user) {
			User oldValue = this.user;
			this.user = user;
			firePropertyChange("user", oldValue, user);
		}
	}
	
	/**
	 * Returns the permanentAddress.
	 * 
	 * @return
	 */
	public Address getPermanentAddress() {
		return permanentAddress;
	}
	
	/**
	 * The permanentAddress to be set.
	 * 
	 * @param permanentAddress
	 */
	public void setPermanentAddress(Address permanentAddress) {
		if(this.permanentAddress != permanentAddress) {
			Address oldValue = this.permanentAddress;
			this.permanentAddress = permanentAddress;
			firePropertyChange("permanentAddress", oldValue, permanentAddress);
		}
	}
	
	/**
	 * Returns the mailingAddress.
	 * 
	 * @return
	 */
	public Address getMailingAddress() {
		return mailingAddress;
	}
	
	/**
	 * The mailingAddress to be set.
	 * 
	 * @param mailingAddress
	 */
	public void setMailingAddress(Address mailingAddress) {
		if(this.mailingAddress != mailingAddress) {
			Address oldValue = this.mailingAddress;
			this.mailingAddress = mailingAddress;
			firePropertyChange("mailingAddress", oldValue, mailingAddress);
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
		sBuilder.append(", User:").append(getUser());
		sBuilder.append(", PermanentAddress:").append(getPermanentAddress());
		sBuilder.append(", MailingAddress:").append(getMailingAddress());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}