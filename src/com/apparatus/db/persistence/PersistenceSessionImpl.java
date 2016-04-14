package com.apparatus.db.persistence;

import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 11, 2015 1:05:12 PM
 */
public class PersistenceSessionImpl extends AbstractPersistenceSession implements PersistenceSession {
	
	/**
	 * 
	 * @param filePersistence
	 */
	public PersistenceSessionImpl(final Persistence filePersistence) {
		super(filePersistence);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#saveObjects(java.lang.Class[])
	 */
	@Override
	public void saveObjects(Class<?>... classes) throws PersistenceException {
		super.saveObjects(classes);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#removeAllObjects()
	 */
	@Override
	public void removeAllObjects() throws PersistenceException {
		super.removeAllObjects();
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#keySet()
	 */
	@Override
	public Set<String> keySet() throws PersistenceException {
		return super.keySet();
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#open()
	 */
	@Override
	public void open() throws PersistenceException, PersistenceClassNotFoundException, InvalidPersistenceClassException, CorruptedDataException, PersistenceSerializationException {
		super.open();
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#getObject(java.lang.String)
	 */
	@Override
	public Object getObject(String key) throws PersistenceException {
		return super.getObject(key);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#getObject(long)
	 */
	@Override
	public Object getObject(long recordIdentifier) throws PersistenceException, PersistenceClassNotFoundException, InvalidPersistenceClassException, CorruptedDataException, PersistenceSerializationException {
		return super.getObject(recordIdentifier);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#addObject(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public Object addObject(String key, Object object) throws PersistenceException {
		return super.addObject(key, object);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#removeObject(java.lang.String)
	 */
	@Override
	public Object removeObject(String key) throws PersistenceException {
		return super.removeObject(key);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#getObjects()
	 */
	@Override
	public Collection<Object> getObjects() throws PersistenceException {
		return super.getObjects();
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistenceSession#getIdentifier(java.lang.Class)
	 */
	@Override
	public long getIdentifier(Class<?> klass) throws PersistenceException {
		return super.getIdentifier(klass);
	}
}
