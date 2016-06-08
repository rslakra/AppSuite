package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>State</code> class defines the state properties.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 7:35:11 PM
 */
public class State extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	// /** id */
	// private String id;
	
	/* the name of the state */
	private String name;
	
	/* the code of the state */
	private String code;
	
	/* the country of the state */
	private Country country;
	
	public State() {
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
	// if (this.id != id) {
	// String oldId = this.id;
	// this.id = id;
	// firePropertyChange("id", oldId, id);
	// }
	// }
	
	/**
	 * @return the name of the state.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the state name to set.
	 */
	public void setName(String name) {
		if(this.name != name) {
			String oldValue = this.name;
			this.name = name;
			firePropertyChange("name", oldValue, name);
		}
	}
	
	/**
	 * @return the code of the state.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code
	 *            the state code to set.
	 */
	public void setCode(String code) {
		if(this.code != code) {
			String oldValue = this.code;
			this.code = code;
			firePropertyChange("code", oldValue, code);
		}
	}
	
	/**
	 * @return the state
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * @param state
	 *            the state to set
	 */
	public void setCountry(Country country) {
		if(this.country != country) {
			Country oldValue = this.country;
			this.country = country;
			firePropertyChange("country", oldValue, country);
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
		sBuilder.append("State<Id=").append(getId());
		sBuilder.append(", Name:").append(getName());
		sBuilder.append(", Code:").append(getCode());
		sBuilder.append(", ").append(getCountry());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}