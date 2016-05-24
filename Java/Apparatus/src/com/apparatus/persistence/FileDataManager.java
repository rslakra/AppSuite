package com.apparatus.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 1:59:40 PM
 */
public class FileDataManager extends AbstractDataManager {
	
	/**
	 * Returns the type of the data manager.
	 * 
	 * @see com.rslakra.db.file.DataManager#getType()
	 */
	@Override
	public String getType() {
		return FileDataManager.class.getName();
	}
	
}
