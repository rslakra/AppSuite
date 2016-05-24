package com.apparatus.persistence;

import java.util.Collection;
import java.util.Set;

/**
 * The base class for all data access sessions.
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:24:51 PM
 */
public abstract class AbstractPersistenceSession implements PersistenceSession {
	
	protected static final String OPEN_ERROR = "Open Error!";
	protected static final String CLOSE_ERROR = "Close Error!";
	protected static final String PERSISTENT_ERROR = "Persistence Error!";
	
	/** file persistence manager */
	private Persistence persistence;
	
	/** error, if any */
	private Exception error;
	
	/**
	 * 
	 * @param persistence
	 */
	public AbstractPersistenceSession(final Persistence persistence) {
		super();
		this.persistence = persistence;
	}
	
	/**
	 * Returns the persistence.
	 * 
	 * @return
	 */
	public Persistence getPersistence() {
		return persistence;
	}
	
	/**
	 * The persistence to be set.
	 * 
	 * @param persistence
	 */
	public void setPersistence(final Persistence persistence) {
		this.persistence = persistence;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#isOpened()
	 */
	@Override
	public boolean isOpened() throws PersistenceException {
		persistence.assertOpened();
		return persistence.isOpened();
	}
	
	/**
	 * Returns the error.
	 * 
	 * @return
	 */
	public Exception getError() {
		return error;
	}
	
	/**
	 * The error to be set.
	 * 
	 * @param error
	 */
	public void setError(final Exception error) {
		this.error = error;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#isClosed()
	 */
	@Override
	public boolean isClosed() throws PersistenceException {
		persistence.assertOpened();
		return persistence.isClosed();
	}
	
	// @Override
	// public Object getObject(final String key) throws PersistenceException {
	// final Object object;
	// synchronized(persistence.getLock()) {
	// try {
	// assertPersistenceOpenned();
	// assertOpened();
	// // object = persistence.getObject(key);
	// } catch(PersistenceException exception) {
	// // logger.error("get object error", exception);
	// throw exception;
	// }
	// }
	// return object;
	// }
	
	// @Override
	// public Object getObject(final long dataRecordIdentifier) throws
	// PersistenceException, persistenceClassNotFoundException,
	// persistenceInvalidClassException, persistenceDataCorruptedException,
	// persistenceNotSerializableException {
	// final Object object;
	// synchronized(persistence.getLock()) {
	// assertPersistenceOpenned();
	// assertOpened();
	// object = persistence.getObject(dataRecordIdentifier);
	// }
	// return object;
	// }
	
	// @Override
	// @Deprecated
	// public void setEnumState(final Enum<?> enumObject) throws
	// PersistenceException {
	// synchronized(mutex) {
	// try {
	// assertPersistenceOpenned();
	// assertOpened();
	// persistence.setEnumState(enumObject);
	// } catch(PersistenceException exception) {
	// logger.error("set enum state error", exception);
	// throw exception;
	// }
	// }
	// }
	
	// @Override
	// public Object setObject(final String key, final Object object) throws
	// PersistenceException {
	// final Object previousObject;
	// synchronized(persistence.getLock()) {
	// try {
	// assertPersistenceOpenned();
	// assertOpened();
	// // previousObject = persistence.setObject(key, object);
	// } catch(PersistenceException exception) {
	// // logger.error("set object error", exception);
	// throw exception;
	// }
	// }
	// return previousObject;
	// }
	
	// @Override
	// public Object removeObject(final String key) throws PersistenceException
	// {
	// final Object previousObject;
	// synchronized(persistence.getLock()) {
	// try {
	// assertPersistenceOpenned();
	// assertOpened();
	// // previousObject = persistence.removeObject(key);
	// } catch(PersistenceException exception) {
	// // logger.error("remove object error", exception);
	// throw exception;
	// }
	// }
	// return previousObject;
	// }
	
	//
	// @Override
	// public Set<String> objectKeySet() throws PersistenceException {
	// final Set<String> objectKeySet;
	// synchronized(persistence.getLock()) {
	// try {
	// assertPersistenceOpenned();
	// assertOpened();
	// // objectKeySet = persistence.objectKeySet();
	// } catch(PersistenceException exception) {
	// // logger.error("get object keys error", exception);
	// throw exception;
	// }
	// }
	// return objectKeySet;
	// }
	
	// @Override
	// public Collection<Object> getObjects() throws PersistenceException {
	// Collection<Object> collection;
	// synchronized(mutex) {
	// try {
	// assertPersistenceOpenned();
	// assertOpened();
	// collection = persistence.getObjects();
	// } catch(PersistenceException exception) {
	// logger.error("get objects error", exception);
	// throw exception;
	// }
	// }
	// return collection;
	// }
	
	// @Override
	// public int getClassIdentifier(final Class<?> clazz) throws
	// PersistenceException, PersistenceClassNotFoundException {
	// final int classIdentifier;
	// synchronized(persistence.getLock()) {
	// try {
	// assertPersistenceOpenned();
	// classIdentifier = persistence.getClassIdentifier(clazz);
	// } catch(PersistenceException exception) {
	// logger.error("get class identifier error", exception);
	// throw exception;
	// } catch(persistenceClassNotFoundException exception) {
	// logger.error("get class identifier error", exception);
	// throw exception;
	// }
	// }
	// return classIdentifier;
	// }
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#saveObjects(java.lang.Class[])
	 */
	@Override
	public void saveObjects(Class<?>... classes) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#saveObject(java.lang.Class)
	 */
	@Override
	public void saveObject(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#open()
	 */
	@Override
	public void open() throws PersistenceException {
		synchronized(persistence.getLock()) {
			persistence.assertOpened();
		}
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#getObject(java.lang.String)
	 */
	@Override
	public Object getObject(String key) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#getObject(long)
	 */
	@Override
	public Object getObject(long recordIdentifier) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#addObject(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public Object addObject(String key, Object object) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#keySet()
	 */
	@Override
	public Set<String> keySet() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#getObjects()
	 */
	@Override
	public Collection<Object> getObjects() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#getIdentifier(java.lang.Class)
	 */
	@Override
	public long getIdentifier(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#removeObject(java.lang.String)
	 */
	@Override
	public Object removeObject(String key) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.PersistenceSession#removeAllObjects()
	 */
	@Override
	public void removeAllObjects() throws PersistenceException {
		synchronized(persistence.getLock()) {
			try {
				persistence.assertOpened();
				// persistence.removeAllObject();
			} catch(PersistenceException exception) {
				// logger.error("remove all object error", exception);
				throw exception;
			}
		}
	}
}
