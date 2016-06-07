package com.rslakra.bos;

import java.io.Serializable;

/**
 * Continent.java
 * 
 * The <code>Country</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 7:45:49 PM
 */
public class Continent extends BusinessObject implements Serializable {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** name */
	private String name;
	
	/** area */
	private String area;
	
	public Continent() {
	}
	
	/**
	 * @return the name of the country.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the country name to set.
	 */
	public void setName(String name) {
		if(this.name != name) {
			String oldValue = this.name;
			this.name = name;
			firePropertyChange("name", oldValue, name);
		}
	}
	
	/**
	 * @return
	 */
	public String getArea() {
		return area;
	}
	
	/**
	 * @param area
	 */
	public void setArea(String area) {
		if(this.area != area) {
			String oldValue = this.area;
			this.area = area;
			firePropertyChange("area", oldValue, area);
		}
	}
	
	/**
	 * Returns a string representation of this object. The string representation
	 * consists of the properties in the order <continent name, continent code>,
	 * enclosed in angular brackets (<tt>"<>"</tt>). Adjacent properties are
	 * separated by the characters <tt>", "</tt> (comma and space).
	 * 
	 * @return a string representation of this object.
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Continent<id=").append(getId());
		sBuilder.append(", Name:").append(getName());
		sBuilder.append(", Area:").append(getArea());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}