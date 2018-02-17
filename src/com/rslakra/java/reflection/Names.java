/**
 * 
 */
package com.rslakra.java.reflection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Singh Lakra (Rohtash.Lakra@nasdaq.com)
 * @date 02/16/2018 10:53:24 AM
 */
public class Names {
	
	// names
	private List<Name> names;
	
	public Names() {
		names = new ArrayList<>();
	}
	
	/**
	 * Returns the names.
	 *
	 * @return names
	 */
	public List<Name> getNames() {
		return names;
	}
	
	/**
	 * The names to be set.
	 * 
	 * @param names
	 */
	public void setNames(List<Name> names) {
		this.names = names;
	}
	
	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return names.toString();
	}
	
}
