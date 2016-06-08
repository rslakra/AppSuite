package com.rslakra.servers.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

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
public class DaoImpl<T extends BusinessObject> extends SimpleJdbcDaoSupport implements Dao<T> {
	
}
