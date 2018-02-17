package com.rslakra.java;

/**
 * 
 * @author Rohtash Singh Lakra
 */
public class StrBuffer {
	
	public static void main(String[] args) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("Rohtash");
		// sBuffer.append("Singh");
		// sBuffer.append("Lakra");
		sBuffer.reverse();
		
		System.out.println("Length:" + sBuffer.length() + ", Capacity:" + sBuffer.capacity());
		System.out.println(sBuffer.toString());
		
	}
	
}
