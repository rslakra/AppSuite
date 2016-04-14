package com.apparatus.db.persistence;

/**
 * garbage sweep operation listener
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:00:14 PM
 */
public interface GarbageCollectorListener {
	
	/**
	 * 
	 * @param garbageCollectionEvent
	 */
	void notifyGarbageCollector(GarbageCollectionEvent garbageCollectionEvent);
	
}
