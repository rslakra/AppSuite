package com.apparatus.db.persistence;

import java.util.Collection;
import java.util.Set;

/**
 * Persistent session allows to save the data object.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:03:30 PM
 */
public interface PersistenceSession {
	
	/**
	 * inform that which class is persisted
	 * 
	 * 
	 * @param classes
	 * @throws PersistenceException
	 */
	void saveObjects(Class<?>... classes) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @throws PersistenceException
	 */
	void saveObject(Class<?> klass) throws PersistenceException;
	
	/**
	 * Opens the persistence session which is not synchronized with other
	 * sessions.
	 * 
	 * @throws PersistenceException
	 */
	void open() throws PersistenceException;
	
	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	boolean isOpened() throws PersistenceException;
	
	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	boolean isClosed() throws PersistenceException;
	
	/**
	 * get a persistent object by its key<br>
	 * 
	 * @param key
	 *            the persistent object key
	 * @return the object or null if none for the key
	 * @throws PersistenceException
	 *             this data access session not opened
	 */
	Object getObject(String key) throws PersistenceException;
	
	/**
	 * 
	 * @param recordIdentifier
	 * @return
	 * @throws PersistenceException
	 */
	Object getObject(long recordIdentifier) throws PersistenceException;
	
	/**
	 * add a persistent object associated to a key<br>
	 * 
	 * @param key
	 *            the object access key
	 * @param object
	 *            the persistent object
	 * @return the replaced object, null if none
	 * @throws PersistenceException
	 *             this data access session not opened
	 */
	Object addObject(String key, Object object) throws PersistenceException;
	
	/**
	 * 
	 * @return key set of persistent objects
	 * @throws PersistenceException
	 *             this data access session not opened
	 */
	Set<String> keySet() throws PersistenceException;
	
	/**
	 * 
	 * @return all persistent objects
	 * @throws PersistenceException
	 */
	Collection<Object> getObjects() throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @return
	 * @throws PersistenceException
	 */
	long getIdentifier(Class<?> klass) throws PersistenceException;
	
	/**
	 * remove a persistent object associated to a key<br>
	 * 
	 * @param key
	 *            the object access key
	 * @return the removed object, null if none
	 * @throws PersistenceException
	 *             this data access session not opened
	 */
	Object removeObject(String key) throws PersistenceException;
	
	/**
	 * remove all persistent object<br>
	 * 
	 * @throws PersistenceException
	 */
	void removeAllObjects() throws PersistenceException;
}
