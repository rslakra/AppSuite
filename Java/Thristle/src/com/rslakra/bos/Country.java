package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>Country</code> class defines the country details.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 7:31:01 PM
 */
public class Country extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/* the name of the country */
	private String name;
	
	/* the code of the country */
	private String code;
	
	/* the continent of the country */
	private Continent continent;
	
	public Country() {
	}
	
	/**
	 * @return the name of the country.
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
	 * @return the code of the country.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * The code to be set.
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		if(this.code != code) {
			String oldValue = this.code;
			this.code = code;
			firePropertyChange("code", oldValue, code);
		}
	}
	
	/**
	 * @return the continent of the country.
	 */
	public Continent getContinent() {
		return continent;
	}
	
	/**
	 * The continent to be set.
	 * 
	 * @param continent
	 */
	public void setContinent(Continent continent) {
		if(this.continent != continent) {
			Continent oldValue = this.continent;
			this.continent = continent;
			firePropertyChange("continent", oldValue, continent);
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
		sBuilder.append("Country<id=").append(getId());
		sBuilder.append(", Name:").append(getName());
		sBuilder.append(", Code:").append(getCode());
		sBuilder.append(", ").append(getContinent());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}