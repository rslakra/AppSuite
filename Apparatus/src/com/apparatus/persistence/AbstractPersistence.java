package com.apparatus.persistence;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * all delegation to {@link Persistence } for manager for file persistence of
 * java object<br>
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 4:21:08 PM
 */
public abstract class AbstractPersistence implements Persistence {
	
	/** for thread synchronization */
	protected transient Object lock = new Object();
	
	private boolean sessionStatus;
	
	/**
	 * 
	 * @param sessionStatus
	 */
	protected void setSessionStatus(final boolean sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.Persistence#getLock()
	 */
	@Override
	public Object getLock() {
		return lock;
	}
	
	@Override
	public int getZipCompressionLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setZipCompressionLevel(int zipCompressionLevel) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isJavaGCSubstitutable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setJavaGCSubstitutable(boolean javaGCSubstitutable) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setGarbageCollectorListener(GarbageCollectorListener garbageCollectorListener) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void loadClasses(boolean allowLazyLoading, Class<?>... classes) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void loadClasses(Class<?>... classes) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void loadClass(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void loadLazily(Class<?>... classes) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void loadLazily(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveWithSerialization(Class<?>... classes) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveWithSerialization(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveWithExternalization(Class<?>... classes) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveWithExternalization(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void markDirty(Class<?>... classes) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void markDirty(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getRecordCount() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getRecordSize() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getFreeSize() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getTotalSize() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getWeakReferenceCount() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getDirtyRecordCount() throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String getHeapFileName() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setAutoSaveEventListener(AutoSaveEventListener autoSaveEventListener) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public PersistenceSession createPersistenceSession() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clear() throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object deepClone(Object sourceObject, boolean forceLoad) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void exportDataToXML(String directory, boolean encrypted) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void importXMLData(String directory, boolean encrypted, boolean validate) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setClassLoader(ClassLoader classLoader) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ClassLoader getClassLoader() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public URL getResource(String resourceName) throws MalformedURLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object newInstance(Object object) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object newInstance(Class<?> klass, Class<?>[] dataTypes, Object[] arguments) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object newInstance(Class<?> klass) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setPersistentFields(Class<?> klass, String[] fieldNames) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setTransientFields(Class<?> klass, String[] fieldNames) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public long getIdentifier(Class<?> clazz) throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isProxy(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isCacheStorageEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setCacheStorageEnabled(boolean cacheStorageEnabled) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void cacheStorage(String key, Object object) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getCachedObjectKey(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isAutoSaveEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setAutoSaveEnabled(boolean enabled) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.Persistence#autoSaveSetup(int)
	 */
	@Override
	public void autoSaveSetup(int maxInMemoryThreshold) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.Persistence#close()
	 */
	@Override
	public void close() throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.Persistence#assertOpened()
	 */
	@Override
	public void assertOpened() throws PersistenceException {
		if(!isOpened()) {
			throw new PersistenceException("Error - persistence session is closed!");
		}
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.Persistence#isClosed()
	 */
	@Override
	public boolean isClosed() {
		return (!isOpened());
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.Persistence#isOpened()
	 */
	@Override
	public boolean isOpened() {
		return sessionStatus;
	}
	
}
