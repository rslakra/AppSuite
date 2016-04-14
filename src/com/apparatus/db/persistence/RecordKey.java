package com.apparatus.db.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:32:22 PM
 */
public interface RecordKey {
	
	/**
	 * 
	 * @return
	 */
	int getHashcode();
	
	/**
	 * 
	 * @param recordKey
	 * @return
	 */
	int compareTo(RecordKey recordKey);
	
	/**
	 * 
	 * @return
	 */
	int getDataSize();
	
	/**
	 * 
	 * @return
	 */
	byte[] getData();
	
	/**
	 * 
	 * @return
	 */
	Object getKey() throws HeapException;
}
