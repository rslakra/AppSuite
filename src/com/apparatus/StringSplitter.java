package com.apparatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apparatus.utils.StringHelper;

/**
 * This class splits the string according to the given delimiter. By default, it
 * does not omit empty string excluding the last one. This also has less
 * overhead than split, which uses regular expressions and allocates an array to
 * hold the results.
 * 
 * <p>
 * The most efficient way to use this class is:
 * 
 * <pre>
 * StringSplitter splitter = new StringSplitter(',');
 * String[] tokens = stringSplitter.split();
 * 
 * OR
 * String[] tokens = stringSplitter.split("one,two,");
 * 
 * OR
 * String[] tokens = stringSplitter.split("one,,,two,", true);
 * 
 * OR
 * StringSplitter splitter = new StringSplitter(string, ',');
 * String[] tokens = stringSplitter.split();
 * 
 * OR
 * StringSplitter splitter = new StringSplitter(string, ',', true);
 * String[] tokens = stringSplitter.split();
 * </pre>
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 7, 2015 3:15:20 PM
 */
public class StringSplitter implements Iterator<String> {
	
	// EMPTY_STRING
	private static final String[] EMPTY_STRING = new String[0];
	
	private String string;
	private char delimiter;
	private boolean excludeEmptyStrings;
	private int position;
	private int length;
	
	/**
	 * The delimiter used to split the string.
	 * 
	 * @param string
	 * @param delimiter
	 * @param excludeEmptyStrings
	 */
	public StringSplitter(String string, char delimiter, boolean excludeEmptyStrings) {
		this.delimiter = delimiter;
		this.setString(string, excludeEmptyStrings);
	}
	
	/**
	 * 
	 * @param string
	 * @param delimiter
	 */
	public StringSplitter(String string, char delimiter) {
		this(string, delimiter, false);
	}
	
	/**
	 * 
	 * @param delimiter
	 * @param excludeEmptyStrings
	 */
	public StringSplitter(char delimiter, boolean excludeEmptyStrings) {
		this(null, delimiter, excludeEmptyStrings);
	}
	
	/**
	 * The delimiter used to split the string.
	 * 
	 * @param delimiter
	 */
	public StringSplitter(char delimiter) {
		this(delimiter, false);
	}
	
	/**
	 * 
	 * @param string
	 * @param excludeEmptyStrings
	 */
	private void setString(String string, boolean excludeEmptyStrings) {
		this.string = string;
		this.excludeEmptyStrings = excludeEmptyStrings;
		this.position = 0;
		this.length = StringHelper.isNullOrEmpty(this.string) ? 0 : this.string.length();
	}
	
	/**
	 * Returns the string.
	 * 
	 * @return
	 */
	public String getString() {
		return this.string;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isExcludeEmptyStrings() {
		return this.excludeEmptyStrings;
	}
	
	/**
	 * Returns the delimiter.
	 * 
	 * @return
	 */
	public char getDelimiter() {
		return this.delimiter;
	}
	
	/**
	 * Returns the position.
	 * 
	 * @return
	 */
	public int getPosition() {
		return this.position;
	}
	
	/**
	 * Returns the length.
	 * 
	 * @return
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> splitAsList() {
		List<String> stringTokens = new ArrayList<String>();
		// if(!StringHelper.isNullOrEmpty(getString())) {
		while(hasNext()) {
			String nextToken = next();
			if(isExcludeEmptyStrings()) {
				if(!StringHelper.isNullOrEmpty(nextToken)) {
					stringTokens.add(nextToken);
				}
			} else {
				stringTokens.add(nextToken);
			}
		}
		// }
		
		return stringTokens;
	}
	
	/**
	 * 
	 * @param excludeEmptyStrings
	 * @return
	 */
	public List<String> splitAsList(boolean excludeEmptyStrings) {
		this.excludeEmptyStrings = excludeEmptyStrings;
		return splitAsList();
	}
	
	/**
	 * Sets the string to split
	 * 
	 * @param string the string to split
	 */
	public String[] split() {
		return (StringHelper.isNullOrEmpty(getString()) ? null : splitAsList().toArray(EMPTY_STRING));
		// if(!StringHelper.isNullOrEmpty(getString())) {
		// List<String> stringTokens = splitAsList();
		// tokens = stringTokens.toArray(EMPTY_STRING);
		// }
		//
		// return tokens;
	}
	
	/**
	 * 
	 * @param excludeEmptyStrings
	 * @return
	 */
	public String[] split(boolean excludeEmptyStrings) {
		this.excludeEmptyStrings = excludeEmptyStrings;
		return split();
	}
	
	/**
	 * Sets the string to split
	 * 
	 * @param string
	 * @param excludeEmptyStrings
	 * @return
	 */
	public String[] split(String string, boolean excludeEmptyStrings) {
		this.setString(string, excludeEmptyStrings);
		return split();
	}
	
	/**
	 * Sets the string to split
	 * 
	 * @param string the string to split
	 */
	public String[] split(String string) {
		return split(string, false);
	}
	
	/**
	 * 
	 * @return
	 */
	public Iterator<String> iterator() {
		return this;
	}
	
	/**
	 * Returns true if the string has next token otherwise false.
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return getPosition() < getLength();
	}
	
	/**
	 * Returns the next token of the string.
	 * 
	 * @see java.util.Iterator#next()
	 */
	public String next() {
		int end = getString().indexOf(getDelimiter(), getPosition());
		if(end == -1) {
			end = getLength();
		}
		
		String nextString = getString().substring(getPosition(), end);
		this.position = end + 1; // Skip the delimiter.
		return nextString;
	}
	
	/**
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}
