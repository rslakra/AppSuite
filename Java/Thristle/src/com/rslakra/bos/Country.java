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
	
	// /** id */
	// private String id;
	
	/* the name of the country */
	private String name;
	
	/* the code of the country */
	private String code;
	
	/* the continent of the country */
	private Continent continent;
	
	public Country() {
	}
	
	// /**
	// * Returns the id.
	// *
	// * @return
	// */
	// public String getId() {
	// return id;
	// }
	//
	// /**
	// * The id to be set.
	// *
	// * @param id
	// */
	// public void setId(String id) {
	// if(this.id != id) {
	// String oldId = this.id;
	// this.id = id;
	// firePropertyChange("id", oldId, id);
	// }
	// }
	
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
	 * The string representation consists of the properties in the order
	 * <country name, country code, continent>, enclosed in angular brackets
	 * (<tt>"<>"</tt>). Adjacent properties are separated by the characters
	 * <tt>", "</tt> (comma and space).
	 * 
	 * @return
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