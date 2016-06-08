package com.rslakra.servers.dao;

import com.rslakra.bos.BusinessObject;

/**
 * The DataAccessObject (DAO) abstracts the underlying data access
 * implementation for the BusinessObject to enable transparent access to the
 * data source.
 * 
 * @author Rohtash Singh
 * 
 * @since 1.0.0
 */
public interface Dao<T extends BusinessObject> {
	
}
