package com.apparatus.db.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 4:53:45 PM
 */
public interface AutoSaveEventListener {
	
	/**
	 * 
	 * @return true if save must be done, else will be skipped
	 */
	boolean doSave();
	
	/**
	 * notify save done
	 * 
	 * @param numberOfObjectState
	 * @param numberOfWeakreference
	 * @param msDuration
	 */
	void saveDone(int numberOfObjectState, int numberOfWeakreference, long msDuration);
	
	/**
	 * 
	 * @param autoSaveEvent
	 */
	void preSave(AutoSaveEvent autoSaveEvent);
	
	/**
	 * 
	 * @param autoSaveEvent
	 */
	void save(AutoSaveEvent autoSaveEvent);
	
	/**
	 * 
	 * @param autoSaveEvent
	 */
	void postSave(AutoSaveEvent autoSaveEvent);
	
}
