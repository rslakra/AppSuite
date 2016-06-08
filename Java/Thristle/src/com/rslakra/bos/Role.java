package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>Role</code> class defines the Roles.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 7:35:11 PM
 */
public class Role extends BusinessObject implements Serializable {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** name */
	public String name;
	/** description */
	public String description;
	
	/**
	 * Return the name of role.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * The name to be set.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		if(this.name != name) {
			String oldValue = this.name;
			this.name = name;
			firePropertyChange("name", oldValue, name);
		}
	}
	
	/**
	 * Return the description of role.
	 * 
	 * @return String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * The description to be set.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		if(this.description != description) {
			String oldValue = this.description;
			this.description = description;
			firePropertyChange("description", oldValue, description);
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
		sBuilder.append("Role<id=").append(getId());
		sBuilder.append(", Name:").append(getName());
		sBuilder.append(", Description:").append(getDescription());
		sBuilder.append(">");
		
		return sBuilder.toString();
	}
	
}
