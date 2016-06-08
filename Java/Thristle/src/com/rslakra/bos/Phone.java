package com.rslakra.bos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>Phone</code>
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 8:04:33 PM
 */
public class Phone extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/* The country code */
	private String countryCode;
	
	/** three-digit Numbering Plan Area (NPA) code (area code). */
	private String areaCode;
	
	/** three-digit central office (CO) code, the second part. */
	private String centralOfficeCode;
	
	/** four digits number the specific line assigned to each telephone. */
	private String lineNumber;
	
	/** the phone number */
	private String number;
	
	/** the list of extensions of the phone */
	private List<String> extensions;
	
	public Phone() {
	}
	
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	
	/**
	 * The countryCode to be set.
	 * 
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		if(this.countryCode != countryCode) {
			String oldValue = this.countryCode;
			this.countryCode = countryCode;
			firePropertyChange("countryCode", oldValue, countryCode);
		}
	}
	
	/**
	 * Returns the areadCode.
	 * 
	 * @return
	 */
	public String getAreaCode() {
		return areaCode;
	}
	
	/**
	 * The areaCode to be set.
	 * 
	 * @param areaCode
	 */
	public void setAreaCode(String areaCode) {
		if(this.areaCode != areaCode) {
			String oldValue = this.areaCode;
			this.areaCode = areaCode;
			firePropertyChange("areaCode", oldValue, areaCode);
		}
	}
	
	/**
	 * Returns the centralOfficeCode.
	 * 
	 * @return
	 */
	public String getCentralOfficeCode() {
		return centralOfficeCode;
	}
	
	/**
	 * The centralOfficeCode to be set.
	 * 
	 * @param centralOfficeCode
	 */
	public void setCentralOfficeCode(String centralOfficeCode) {
		if(this.centralOfficeCode != centralOfficeCode) {
			String oldValue = this.centralOfficeCode;
			this.centralOfficeCode = centralOfficeCode;
			firePropertyChange("centralOfficeCode", oldValue, centralOfficeCode);
		}
	}
	
	/**
	 * Returns the lineNumber.
	 * 
	 * @return
	 */
	public String getLineNumber() {
		return lineNumber;
	}
	
	/**
	 * The lineNumber to be set.
	 * 
	 * @param lineNumber
	 */
	public void setLineNumber(String lineNumber) {
		if(this.lineNumber != lineNumber) {
			String oldValue = this.lineNumber;
			this.lineNumber = lineNumber;
			firePropertyChange("lineNumber", oldValue, lineNumber);
		}
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * The number to be set.
	 * 
	 * @param number
	 */
	public void setNumber(String number) {
		if(this.number != number) {
			String oldValue = number;
			this.number = number;
			firePropertyChange("number", oldValue, number);
		}
	}
	
	/**
	 * @return the extensions
	 */
	public List<String> getExtensions() {
		return extensions;
	}
	
	/**
	 * The extensions to be set.
	 * 
	 * @param extensions
	 */
	public void setExtensions(List<String> extensions) {
		if(this.extensions != extensions) {
			List<String> oldValue = this.extensions;
			this.extensions = extensions;
			firePropertyChange("extensions", oldValue, extensions);
		}
	}
	
	// /**
	// * Adds the extension at the specified index.
	// *
	// * @param index
	// * @param value
	// */
	// public void addExtension(int index, int extension) {
	// if (extensions == null) {
	// extensions = new ArrayList<Integer>();
	// }
	//
	// // add extension at the specified index.
	// extensions.add(index, extension);
	// }
	
	/**
	 * Adds extensions at the end of the extensions list.
	 *
	 * @param extension
	 */
	public void addExtension(String extension) {
		if(extensions == null) {
			extensions = new ArrayList<String>();
		}
		
		// add extension at the end
		extensions.add(extension);
	}
	
	// /**
	// * Removes extensions from the specified index.
	// *
	// * @param index
	// */
	// public void removeExtension(int index) {
	// if (extensions == null) {
	// throw new NullPointerException("'extensions' is NULL!");
	// }
	//
	// if (index < 0 || index > (extensions.size() - 1)) {
	// throw new ArrayIndexOutOfBoundsException("index '" + index + "' out of
	// bound. extensions size: " + extensions.size());
	// }
	//
	// // remove extension form the specified index.
	// extensions.remove(index);
	// }
	
	/**
	 * The extensions of the phone to be removed.
	 *
	 * @param extensions
	 */
	public void removeExtension(String extension) {
		extensions.remove(extension);
	}
	
	/**
	 * The extensions of the phone to be removed.
	 *
	 * @param extensions
	 */
	public void removeExtensions(List<String> extensions) {
		if(extensions == null) {
			throw new NullPointerException("'extensions' are NULL!");
		}
		
		// remove all extensions
		this.extensions.removeAll(extensions);
	}
	
	/**
	 * Returns a string representation of this object.
	 * 
	 * @return
	 * @see com.rslakra.bos.BusinessObject#toString()
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Phone<Id=").append(getId());
		sBuilder.append(", Country Code:").append(getCountryCode());
		sBuilder.append(", Area/STD code:").append(getAreaCode());
		sBuilder.append(", Number:").append(getNumber());
		sBuilder.append(", Extensions:").append(getExtensions());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
}