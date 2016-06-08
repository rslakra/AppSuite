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
	
	/** unitDesignator */
	private UnitDesignator unitDesignator;
	/** street */
	private String street;
	/** province */
	private String province;
	/** city */
	private City city;
	/** state */
	private State state;
	/** zipCode */
	private String zipCode;
	/** country */
	private Country country;
	
	/** phoneNuber */
	private String phoneNumber;
	
	/** mobileNuber */
	private String mobileNumber;
	
	/** email */
	private String email;
	
	/** webSite */
	private String webSite;
	
	public Address() {
	}
	
	/**
	 * Returns the unitDesignator.
	 *
	 * @return
	 */
	public UnitDesignator getUnitDesignator() {
		return unitDesignator;
	}
	
	/**
	 * The unitDesignator to be set.
	 * 
	 * @param unitDesignator
	 */
	public void setUnitDesignator(UnitDesignator unitDesignator) {
		if(this.unitDesignator != unitDesignator) {
			UnitDesignator oldValue = this.unitDesignator;
			this.unitDesignator = unitDesignator;
			firePropertyChange("unitDesignator", oldValue, unitDesignator);
		}
	}
	
	/**
	 * Returns the street.
	 *
	 * @return
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * The street to be set.
	 * 
	 * @param street
	 */
	public void setStreet(String street) {
		if(this.street != street) {
			String oldValue = this.street;
			this.street = street;
			firePropertyChange("street", oldValue, street);
		}
	}
	
	/**
	 * Returns the province.
	 *
	 * @return
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * The province to be set.
	 * 
	 * @param province
	 */
	public void setProvince(String province) {
		if(this.province != province) {
			String oldValue = this.province;
			this.province = province;
			firePropertyChange("province", oldValue, province);
		}
	}
	
	/**
	 * Returns the city.
	 *
	 * @return
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * The city to be set.
	 * 
	 * @param city
	 */
	public void setCity(City city) {
		if(this.city != city) {
			City oldValue = this.city;
			this.city = city;
			firePropertyChange("city", oldValue, city);
		}
	}
	
	/**
	 * Returns the state.
	 *
	 * @return
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * The state to be set.
	 * 
	 * @param state
	 */
	public void setState(State state) {
		if(this.state != state) {
			State oldValue = this.state;
			this.state = state;
			firePropertyChange("state", oldValue, state);
		}
	}
	
	/**
	 * Returns the zipCode.
	 *
	 * @return
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * The zipCode to be set.
	 * 
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		if(this.zipCode != zipCode) {
			String oldValue = this.zipCode;
			this.zipCode = zipCode;
			firePropertyChange("zipCode", oldValue, zipCode);
		}
	}
	
	/**
	 * Returns the country.
	 *
	 * @return
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * The country to be set.
	 * 
	 * @param country
	 */
	public void setCountry(Country country) {
		if(this.country != country) {
			Country oldValue = this.country;
			this.country = country;
			firePropertyChange("country", oldValue, country);
		}
	}
	
	/**
	 * Returns the phoneNumber.
	 *
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * The phoneNumber to be set.
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		if(this.phoneNumber != phoneNumber) {
			String oldValue = this.phoneNumber;
			this.phoneNumber = phoneNumber;
			firePropertyChange("phoneNumber", oldValue, phoneNumber);
		}
	}
	
	/**
	 * Returns the mobileNumber.
	 *
	 * @return
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	/**
	 * The mobileNumber to be set.
	 * 
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		if(this.mobileNumber != mobileNumber) {
			String oldValue = this.mobileNumber;
			this.mobileNumber = mobileNumber;
			firePropertyChange("mobileNumber", oldValue, mobileNumber);
		}
	}
	
	/**
	 * Returns the email.
	 *
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * The email to be set.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		if(this.email != email) {
			String oldValue = this.email;
			this.email = email;
			firePropertyChange("email", oldValue, email);
		}
	}
	
	/**
	 * Returns the webSite.
	 *
	 * @return
	 */
	public String getWebSite() {
		return webSite;
	}
	
	/**
	 * The webSite to be set.
	 * 
	 * @param webSite
	 */
	public void setWebSite(String webSite) {
		if(this.webSite != webSite) {
			String oldValue = this.webSite;
			this.webSite = webSite;
			firePropertyChange("webSite", oldValue, webSite);
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
		sBuilder.append("Address<id=").append(getId());
		sBuilder.append(", UnitDesignator:").append(getUnitDesignator());
		sBuilder.append(", Street:").append(getStreet());
		sBuilder.append(", Province:").append(getProvince());
		sBuilder.append(", City:").append(getCity());
		sBuilder.append(", State:").append(getState());
		sBuilder.append(", ZipCode:").append(getZipCode());
		sBuilder.append(", Country:").append(getCountry());
		sBuilder.append(", PhoneNumber:").append(getPhoneNumber());
		sBuilder.append(", MobileNumber:").append(getMobileNumber());
		sBuilder.append(", Email:").append(getEmail());
		sBuilder.append(", WebSite:").append(getWebSite());
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