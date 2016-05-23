package com.apparatus.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 4:54:21 PM
 */
public interface AutoSaveEvent {
	
	/**
	 * 
	 * @return
	 */
	int weakReferenceObjectsCount();
	
	/**
	 * 
	 * @return
	 */
	int savedObjectsCount();
	
	/**
	 * 
	 * @return
	 */
	long getDuration();
	
}
