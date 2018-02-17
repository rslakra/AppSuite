package com.rslakra.java;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 09/22/2017 03:25:16 PM
 */
public class TestString {
	
	public static void main(String[] args) {
		String s1 = "Rohtash";
		String s2 = "Rohtash";
		String s3 = new String(" Rohtash");
		String s4 = new String(" Rohtash");
		String s5 = "Lakra";
		String nulls1 = null;
		String nulls2 = null;
		
		System.out.println("Comparing strings with equals:");
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s3));
		System.out.println(s1.equals(s5));
		
		System.out.println("Comparing strings with ==:");
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s3 == s4);
		
		System.out.println("Comparing strings with compareTo():");
		System.out.println(s1.compareTo(s3));
		System.out.println(s1.compareTo(s5));
		System.out.println(System.out.format("%d-%d=%d", (int)'R', (int)'L', ('R' - 'L')));
	}
	
}
