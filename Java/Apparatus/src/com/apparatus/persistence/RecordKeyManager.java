package com.apparatus.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:37:00 PM
 */
public interface RecordKeyManager {
	
	/**
	 * 
	 * @param leftRecordKey
	 * @param rightRecordKey
	 * @return
	 * @throws HeapException
	 */
	int compareRecordKeys(RecordKey leftRecordKey, RecordKey rightRecordKey) throws HeapException;
	
	/**
	 * 
	 * @param keyData
	 * @return
	 * @throws HeapException
	 */
	Comparable<?> unmarshall(byte[] keyData) throws HeapException;
	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws HeapException
	 */
	RecordIdentifier createKey(Object key) throws HeapException;
}
