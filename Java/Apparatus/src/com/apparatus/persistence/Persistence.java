package com.apparatus.persistence;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 2:07:30 PM
 */
public interface Persistence {
	
	/**
	 * 
	 * @return
	 */
	Object getLock();
	
	/**
	 * 
	 * @return
	 */
	int getZipCompressionLevel();
	
	/**
	 * 
	 * @param zipCompressionLevel
	 * @throws PersistenceException
	 */
	void setZipCompressionLevel(int zipCompressionLevel) throws PersistenceException;
	
	/**
	 * 
	 * @return
	 */
	boolean isJavaGCSubstitutable();
	
	/**
	 * 
	 * @param javaGCSubstitutable
	 * @throws PersistenceException
	 */
	void setJavaGCSubstitutable(boolean javaGCSubstitutable) throws PersistenceException;
	
	/**
	 * 
	 * @param garbageCollectorListener
	 * @throws PersistenceException
	 */
	void setGarbageCollectorListener(GarbageCollectorListener garbageCollectorListener) throws PersistenceException;
	
	/**
	 * 
	 * @param allowLazyLoading
	 * @param classes
	 * @throws PersistenceException
	 */
	void loadClasses(boolean allowLazyLoading, Class<?>... classes) throws PersistenceException;
	
	/**
	 * 
	 * @param classes
	 * @throws PersistenceException
	 */
	void loadClasses(Class<?>... classes) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @throws PersistenceException
	 */
	void loadClass(Class<?> klass) throws PersistenceException;
	
	/**
	 * 
	 * @param classes
	 * @throws PersistenceException
	 */
	void loadLazily(Class<?>... classes) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @throws PersistenceException
	 */
	void loadLazily(Class<?> klass) throws PersistenceException;
	
	/**
	 * 
	 * @param classes
	 * @throws PersistenceException
	 */
	void saveWithSerialization(Class<?>... classes) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @throws PersistenceException
	 */
	void saveWithSerialization(Class<?> klass) throws PersistenceException;
	
	/**
	 * 
	 * @param classes
	 * @throws PersistenceException
	 */
	void saveWithExternalization(Class<?>... classes) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @throws PersistenceException
	 */
	void saveWithExternalization(Class<?> klass) throws PersistenceException;
	
	/**
	 * 
	 * @param classes
	 * @throws PersistenceException
	 */
	void markDirty(Class<?>... classes) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @throws PersistenceException
	 */
	void markDirty(Class<?> klass) throws PersistenceException;
	
	/**
	 * get number of data record in heap file<br>
	 * 
	 * @return number of data record in heap file
	 * @throws FilePersistenceException
	 */
	int getRecordCount() throws PersistenceException;
	
	/**
	 * get number of bytes used to store data in data file<br>
	 * 
	 * @return number of bytes used to store data in data file
	 * @throws FilePersistenceException
	 */
	long getRecordSize() throws PersistenceException;
	
	/**
	 * get number of free bytes in data file<br>
	 * 
	 * @return number of free bytes in data file
	 * @throws FilePersistenceException
	 */
	long getFreeSize() throws PersistenceException;
	
	/**
	 * get total size of data file<br>
	 * 
	 * @return total size of data file
	 * @throws FilePersistenceException
	 */
	long getTotalSize() throws PersistenceException;
	
	/**
	 * 
	 * @return number of weak reference on object with knew persistent state
	 * @throws FilePersistenceException
	 */
	int getWeakReferenceCount() throws PersistenceException;
	
	/**
	 * 
	 * @return number of modified object saved
	 * @throws FilePersistenceException
	 */
	int getDirtyRecordCount() throws PersistenceException;
	
	/**
	 * 
	 * @return data storage file name
	 * @throws FilePersistenceException
	 */
	String getHeapFileName() throws PersistenceException;
	
	/**
	 * 
	 * @param autoSaveEventListener
	 */
	void setAutoSaveEventListener(AutoSaveEventListener autoSaveEventListener);
	
	/**
	 * 
	 * @return a new created data access session
	 * @throws FilePersistenceException
	 */
	PersistenceSession createPersistenceSession() throws PersistenceException;
	
	/**
	 * clear all persisted data<br>
	 * 
	 * @throws FilePersistenceException
	 * @throws FilePersistenceInvalidClassException
	 * @throws FilePersistenceNotSerializableException
	 * @throws FilePersistenceClassNotFoundException
	 * @throws FilePersistenceDataCorruptedException
	 */
	void clear() throws PersistenceException;
	
	/**
	 * deep copy of object, useful to detach from persistence ( proxy object to
	 * non-proxy, detached state if use java agent )<br>
	 * 
	 * @param sourceObject
	 * @param forceLoad
	 *            true if force proxy loading before copy
	 * @return deep copy of object
	 * @throws FilePersistenceException
	 */
	Object deepClone(Object sourceObject, boolean forceLoad) throws PersistenceException;
	
	/**
	 * Exports the persisted data in an XML file created in the given
	 * <code>directory<code>.
	 * 
	 * @param directory
	 * @param encrypted
	 * @throws PersistenceException
	 */
	void exportDataToXML(String directory, boolean encrypted) throws PersistenceException;
	
	/**
	 * Imports the persisted data from an XML file created in the given
	 * <code>directory<code>.
	 * 
	 * @param directory
	 * @param encrypted
	 * @param validate
	 * @throws PersistenceException
	 */
	void importXMLData(String directory, boolean encrypted, boolean validate) throws PersistenceException;
	
	/**
	 * set the class loader used by proxy creation to define class<br>
	 * the class loader use by proxy creation to define class should be the same
	 * used to load class of object to be persisted<br>
	 * 
	 * @param classLoader
	 * @throws FilePersistenceException
	 */
	void setClassLoader(ClassLoader classLoader) throws PersistenceException;
	
	/**
	 * 
	 * @return the class loader use by proxy creation to define class
	 * @throws FilePersistenceException
	 */
	ClassLoader getClassLoader() throws PersistenceException;
	
	/**
	 * 
	 * @param resourceName
	 * @return
	 * @throws MalformedURLException
	 */
	URL getResource(String resourceName) throws MalformedURLException;
	
	/**
	 * 
	 * @param object
	 * @return
	 * @throws PersistenceException
	 */
	Object newInstance(Object object) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @param dataTypes
	 * @param arguments
	 * @return
	 * @throws PersistenceException
	 */
	Object newInstance(Class<?> klass, Class<?>[] dataTypes, Object[] arguments) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @return
	 * @throws PersistenceException
	 */
	Object newInstance(Class<?> klass) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @param fieldNames
	 * @throws PersistenceException
	 */
	void setPersistentFields(Class<?> klass, String[] fieldNames) throws PersistenceException;
	
	/**
	 * 
	 * @param klass
	 * @param fieldNames
	 * @throws PersistenceException
	 */
	void setTransientFields(Class<?> klass, String[] fieldNames) throws PersistenceException;
	
	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	long getIdentifier(Class<?> clazz) throws PersistenceException;
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	boolean isProxy(Object object);
	
	/**
	 * 
	 * @return
	 */
	boolean isCacheStorageEnabled();
	
	/**
	 * 
	 * @param cacheStorageEnabled
	 */
	void setCacheStorageEnabled(boolean cacheStorageEnabled);
	
	/**
	 * Keeps the data in main/system memory.
	 * 
	 * @param key
	 * @param object
	 * @throws PersistenceException
	 */
	void cacheStorage(final String key, final Object object) throws PersistenceException;
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	String getCachedObjectKey(Object object);
	
	/**
	 * 
	 * @return
	 */
	boolean isAutoSaveEnabled();
	
	/**
	 * 
	 * @param enabled
	 * @throws PersistenceException
	 */
	void setAutoSaveEnabled(boolean enabled) throws PersistenceException;
	
	/**
	 * 
	 * (PROPS) can be set using properties file see
	 * {@link net.sf.joafip.service.FilePersistence#FilePersistence(String, boolean)}
	 * 
	 * @param maxInMemoryThreshold
	 *            maximum loaded object limit, save done when threshold is reach
	 */
	void autoSaveSetup(int maxInMemoryThreshold);
	
	/**
	 * 
	 * @throws PersistenceException
	 */
	void close() throws PersistenceException;
	
	/**
	 * 
	 * @throws PersistenceException
	 */
	public void assertOpened() throws PersistenceException;
	
	/**
	 * 
	 * @return
	 */
	boolean isClosed();
	
	/**
	 * 
	 * @return
	 */
	boolean isOpened();
}
