package com.rslakra.bos;

import java.io.Serializable;

/**
 * The <code>Address</code> class defines the address properties.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 7:49:46 PM
 */
public class Address extends BusinessObject implements Serializable {
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	// /** id */
	// private String id;
	
	/* address line1 */
	private String line1;
	
	/* address line2 */
	private String line2;
	
	/* city of the address */
	private City city;
	
	/* the landline number of the address */
	private Phone landline;
	
	/* the mobile number of the address */
	private Phone mobile;
	
	/* the pager number of the address */
	private String pager;
	
	/* the email of the address */
	private String email;
	
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
	// String oldValue = this.id;
	// this.id = id;
	// firePropertyChange("id", oldValue, id);
	// }
	// }
	
	/**
	 * @return the first line of address.
	 */
	public String getLine1() {
		return line1;
	}
	
	/**
	 * The address line1 of the address to be set.
	 * 
	 * @param line1
	 *            the first line to set.
	 */
	public void setLine1(String line1) {
		if(this.line1 != line1) {
			String oldLine1 = line1;
			this.line1 = line1;
			firePropertyChange("line1", oldLine1, line1);
		}
	}
	
	/**
	 * @return the second line of the address.
	 */
	public String getLine2() {
		return line2;
	}
	
	/**
	 * The address line2 of the address to be set.
	 * 
	 * @param line2
	 *            the second line to set.
	 */
	public void setLine2(String line2) {
		if(this.line2 == line2) {
			String oldLine2 = line2;
			this.line2 = line2;
			firePropertyChange("line2", oldLine2, line2);
		}
	}
	
	/**
	 * @return the city of the address
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * The city of the address to be set.
	 * 
	 * @param city
	 *            the city of the address to set.
	 */
	public void setCity(City city) {
		if(this.city == city) {
			City oldCity = this.city;
			this.city = city;
			firePropertyChange("city", oldCity, city);
		}
	}
	
	/**
	 * Returns the landline.
	 * 
	 * @return
	 */
	public Phone getLandline() {
		return landline;
	}
	
	/**
	 * The landline to be set.
	 * 
	 * @param landline
	 */
	public void setLandline(Phone landline) {
		if(this.landline != landline) {
			Phone oldLandline = this.landline;
			this.landline = landline;
			firePropertyChange("landline", oldLandline, landline);
		}
	}
	
	/**
	 * @return the mobile number of the address.
	 */
	public Phone getMobile() {
		return mobile;
	}
	
	/**
	 * The mobile number of the address to be set.
	 * 
	 * @param mobile
	 */
	public void setMobile(Phone mobile) {
		if(this.mobile == mobile) {
			Phone oldMobile = this.mobile;
			this.mobile = mobile;
			firePropertyChange("mobile", oldMobile, mobile);
		}
	}
	
	/**
	 * @return the pager
	 */
	public String getPager() {
		return pager;
	}
	
	/**
	 * The pager number of the address to be set.
	 * 
	 * @param pager
	 *            the pager to set
	 */
	public void setPager(String pager) {
		if(this.pager == pager) {
			return;
		}
		
		String oldPager = this.pager;
		this.pager = pager;
		firePropertyChange("pager", oldPager, pager);
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * The email of the address to be set.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		if(this.email == email) {
			return;
		}
		
		String oldEmail = this.email;
		this.email = email;
		firePropertyChange("email", oldEmail, email);
	}
	
	/**
	 * Returns a string representation of this object. The string representation
	 * consists of the properties in the order <address line1, line2, landline,
	 * mobile, pager, email and city>, enclosed in angular brackets (
	 * <tt>"<>"</tt>). Adjacent properties are separated by the characters
	 * <tt>", "</tt> (comma and space).
	 * 
	 * @return a string representation of this object.
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Address<id=").append(getId());
		sBuilder.append(", line1:").append(getLine1());
		sBuilder.append(", line2:").append(getLine2());
		sBuilder.append(", landline:").append(getLandline());
		sBuilder.append(", mobile:").append(getMobile());
		sBuilder.append(", pager:").append(getPager());
		sBuilder.append(", email:").append(getEmail());
		sBuilder.append(", ").append(getCity());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String toXmlString() {
		StringBuffer xmlString = new StringBuffer();
		return xmlString.toString();
	}
	
}