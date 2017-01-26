package com.apparatus.utils;

/**
 * How to create your own String class.
 * 
 * @author Rohtash Singh Lakra
 * @date 01/26/2017 02:41:09 PM
 *
 */
public final class Str implements CharSequence {
	
	private final char[] value;
	private final int offset;
	private final int count;
	
	public Str(String str) {
		this(0, str.length(), str.toCharArray());
	}
	
	/**
	 * 
	 * @param offset
	 * @param count
	 * @param value
	 */
	public Str(int offset, int count, char[] value) {
		this.value = value;
		this.offset = offset;
		this.count = count;
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public int length() {
		return count;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	@Override
	public char charAt(int index) {
		return value[index + offset];
	}
	
	/**
	 * @see java.lang.CharSequence#subSequence(int, int)
	 */
	@Override
	public CharSequence subSequence(int beginIndex, int endIndex) {
		return new Str(offset + beginIndex, endIndex - beginIndex, value);
	}
	
	/**
	 * Returns the sub-string.
	 * 
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public Str substring(int beginIndex, int endIndex) {
		// check boundary
		return new Str(offset + beginIndex, endIndex - beginIndex, value);
	}
	
	/**
	 * 
	 * @return
	 */
	private char[] subString() {
		char[] subStr = new char[offset + count];
		int ctr = 0;
		for(int i = offset; i < subStr.length; i++) {
			subStr[ctr++] = value[i];
		}
		return subStr;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new String(subString());
	}
	
	/**
	 * Testing.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Str str = new Str("Rohtash Singh");
		System.out.println("str:" + str);
		Str firstName = str.substring(0, 7);
		System.out.println("firstName:" + firstName);
		Str lastName = str.substring(8, str.length());
		System.out.println("lastName:" + lastName);
	}
	
}
