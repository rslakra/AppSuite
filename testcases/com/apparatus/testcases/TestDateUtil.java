package com.apparatus.testcases;

import com.apparatus.HashTable;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public class TestDateUtil {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashTable hashTable = new HashTable();
		hashTable.put(null, 10);
		hashTable.put("one", 12);
		hashTable.put("two", 11);
		System.out.println(hashTable);
	}
}