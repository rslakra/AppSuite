package com.rslakra.bos;

import java.io.Serializable;

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
	
}
