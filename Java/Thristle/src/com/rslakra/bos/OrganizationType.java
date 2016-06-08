package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>OrganizationType</code> class defines the Organization Types.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 7:35:11 PM
 */
public class OrganizationType extends BusinessObject implements Serializable {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** organizationType */
	public String organizationType;
	
	public OrganizationType() {
	}
	
	/**
	 * @return the organizationType
	 */
	public String getOrganizationType() {
		return organizationType;
	}
	
	/**
	 * @param organizationType
	 *            the organizationType to set
	 */
	public void setOrganizationType(String organizationType) {
		if(this.organizationType != organizationType) {
			String oldValue = this.organizationType;
			this.organizationType = organizationType;
			firePropertyChange("organizationType", oldValue, organizationType);
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
		sBuilder.append("OrganizationType<Id=").append(getId());
		sBuilder.append(", OrganizationType:").append(getOrganizationType());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}
