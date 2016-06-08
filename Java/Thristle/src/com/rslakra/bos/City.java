package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>City</code>
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 15, 2010 1:22:35 PM
 */
public class City extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	// /** id */
	// private String id;
	
	/** the name of the city */
	private String name;
	
	/** the code of the city */
	private String code;
	
	/** the zip/pin code of the city */
	private String zip;
	
	/** the state of the city */
	private State state;
	
	public City() {
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
	 * @return name of the city.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the city name to set.
	 */
	public void setName(String name) {
		if(this.name != name) {
			String oldName = this.name;
			this.name = name;
			firePropertyChange("name", oldName, name);
		}
	}
	
	/**
	 * @return the code of the city.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code
	 *            the city code to set.
	 */
	public void setCode(String code) {
		if(this.code != code) {
			String oldCode = this.code;
			this.code = code;
			firePropertyChange("code", oldCode, code);
		}
	}
	
	/**
	 * @return the zip/pin code of the city.
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * the city zip to be set.
	 * 
	 * @param zip
	 */
	public void setZip(String zip) {
		if(this.zip != zip) {
			String oldZip = this.zip;
			this.zip = zip;
			firePropertyChange("zip", oldZip, zip);
		}
	}
	
	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(State state) {
		if(this.state != state) {
			State oldState = this.state;
			this.state = state;
			firePropertyChange("state", oldState, state);
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
		sBuilder.append("City<id=").append(getId());
		sBuilder.append(", Name:").append(getName());
		sBuilder.append(", Code:").append(getCode());
		sBuilder.append(", Zip:").append(getZip());
		sBuilder.append(", ").append(getState());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}