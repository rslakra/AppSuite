package com.rslakra.bos;

import java.io.Serializable;

public class Organization extends BusinessObject implements Serializable {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** name */
	public String name;
	/** organizationType */
	private OrganizationType organizationType;
	/** shortName */
	public String shortName;
	/** extraDetails */
	public String extraDetails;
	/** description */
	public String description;
	
	/** address */
	private Address address;
	/** parent */
	private Organization parent;
	/**
	 * employerIdNumber
	 * 
	 * An Employer Identification Number (EIN), also known as a Federal Tax
	 * Identification Number, is used to identify a business entity.
	 */
	private String employerIdNumber;
	/** establishedOn */
	private long establishedOn;
	
	public Organization() {
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if(this.name != name) {
			String oldValue = this.name;
			this.name = name;
			firePropertyChange("name", oldValue, name);
		}
	}
	
	/**
	 * @return the organizationType
	 */
	public OrganizationType getOrganizationType() {
		return organizationType;
	}
	
	/**
	 * @param organizationType the organizationType to set
	 */
	public void setOrganizationType(OrganizationType organizationType) {
		if(this.organizationType != organizationType) {
			OrganizationType oldValue = this.organizationType;
			this.organizationType = organizationType;
			firePropertyChange("organizationType", oldValue, organizationType);
		}
	}
	
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		if(this.shortName != shortName) {
			String oldValue = this.shortName;
			this.shortName = shortName;
			firePropertyChange("shortName", oldValue, shortName);
		}
	}
	
	/**
	 * @return the extraDetails
	 */
	public String getExtraDetails() {
		return extraDetails;
	}
	
	/**
	 * @param extraDetails the extraDetails to set
	 */
	public void setExtraDetails(String extraDetails) {
		if(this.extraDetails != extraDetails) {
			String oldValue = this.extraDetails;
			this.extraDetails = extraDetails;
			firePropertyChange("extraDetails", oldValue, extraDetails);
		}
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if(this.description != description) {
			String oldValue = this.description;
			this.description = description;
			firePropertyChange("description", oldValue, description);
		}
	}
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		if(this.address != address) {
			Address oldValue = this.address;
			this.address = address;
			firePropertyChange("address", oldValue, address);
		}
	}
	
	/**
	 * @return the parent
	 */
	public Organization getParent() {
		return parent;
	}
	
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Organization parent) {
		if(this.parent != parent) {
			Organization oldValue = this.parent;
			this.parent = parent;
			firePropertyChange("parent", oldValue, parent);
		}
	}
	
	/**
	 * Returns the employerIdNumber.
	 * 
	 * @return
	 */
	public String getEmployerIdNumber() {
		return employerIdNumber;
	}
	
	/**
	 * The employerIdNumber to be set.
	 * 
	 * @param employerIdNumber
	 */
	public void setEmployerIdNumber(String employerIdNumber) {
		if(this.employerIdNumber != employerIdNumber) {
			String oldValue = this.employerIdNumber;
			this.employerIdNumber = employerIdNumber;
			firePropertyChange("employerIdNumber", oldValue, employerIdNumber);
		}
	}
	
	/**
	 * Returns the establishedOn.
	 * 
	 * @return
	 */
	public long getEstablishedOn() {
		return establishedOn;
	}
	
	/**
	 * The establishedOn to be set.
	 * 
	 * @param establishedOn
	 */
	public void setEstablishedOn(long establishedOn) {
		if(this.establishedOn != establishedOn) {
			long oldValue = this.establishedOn;
			this.establishedOn = establishedOn;
			firePropertyChange("establishedOn", oldValue, establishedOn);
		}
	}
	
	/**
	 * Returns a string representation of this object.
	 * 
	 * @return
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Organization<id=").append(getId());
		sBuilder.append(", Name:").append(getName());
		sBuilder.append(", ").append(getOrganizationType());
		sBuilder.append(", ShortName:").append(getShortName());
		sBuilder.append(", ExtraDetails:").append(getExtraDetails());
		sBuilder.append(", Description:").append(getDescription());
		sBuilder.append(", ").append(getAddress());
		// stop recursion here.
		if(getParent() != null) {
			sBuilder.append(", ").append(getParent());
		}
		sBuilder.append(">");
		
		return sBuilder.toString();
	}
	
}
