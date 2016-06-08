package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>UnitDesignator</code> class defines the UnitDesignator details.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 7:31:01 PM
 */
public class UnitDesignator extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** description */
	private String description;
	
	/** abbreviation */
	private String abbreviation;
	
	public UnitDesignator() {
	}
	
	/**
	 * Returns the description.
	 * 
	 * @return
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
	 * Returns the abbreviation.
	 * 
	 * @return
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	
	/**
	 * The abbreviation to be set.
	 * 
	 * @param abbreviation
	 */
	public void setAbbreviation(String abbreviation) {
		if(this.abbreviation != abbreviation) {
			String oldValue = this.abbreviation;
			this.abbreviation = abbreviation;
			firePropertyChange("abbreviation", oldValue, abbreviation);
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
		sBuilder.append("UnitDesignator<id=").append(getId());
		sBuilder.append(", Description:").append(getDescription());
		sBuilder.append(", Abbreviation:").append(getAbbreviation());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}