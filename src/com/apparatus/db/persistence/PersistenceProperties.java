package com.apparatus.db.persistence;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import com.apparatus.utils.StringHelper;

/**
 * file persistence properties<br>
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 12:44:46 PM
 */
public class PersistenceProperties {
	
	public static final String ENABLE_GARBAGE_COLLECTION = "EnableGarbageCollection";
	public static final String GARBAGE_COLLECTION_SLEEP_TIME = "GarbageCollectionSleepTime";
	public static final String ALLOWED_REMOVE_FILE = "AllowedRemoveFile";
	public static final String MAX_BUFFER_SIZE = "MaxBufferSize";
	public static final String MAX_FILE_OPERATION_RETRY = "MaxFileOperatinRetry";
	public static final String ZIP_COMPRESSION_LEVEL = "ZipCompressionLevel";
	public static final String DATA_FILE_PATH = "DataFilePath";
	public static final String AUTO_SAVE_ENABLED = "AutoSaveEnabled";
	
	// persistenceProperties
	private final Properties persistenceProperties = new Properties();
	private final Set<String> mutableObjects = new TreeSet<String>();
	private final Set<String> immutableObjects = new TreeSet<String>();
	
	// private final Map<String, Boolean> recordActions = new TreeMap<String,
	// Boolean>();
	
	/**
	 * 
	 */
	public PersistenceProperties() {
		super();
		setEnableGarbageCollection(false);
		setGarbageCollectionSleepTime(1 * 60 * 60 * 1000);
		setAllowedRemoveFile(false);
	}
	
	/**
	 * Returns the string value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return persistenceProperties.getProperty(key);
	}
	
	/**
	 * The new value to be set for the specified key.
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, Object value) {
		persistenceProperties.put(key, value);
	}
	
	/**
	 * The new value to be set for the specified key.
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value) {
		persistenceProperties.put(key, value);
	}
	
	/**
	 * The new value to be set for the specified key.
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, boolean value) {
		persistenceProperties.put(key, value);
	}
	
	/**
	 * The new value to be set for the specified key.
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, long value) {
		persistenceProperties.put(key, value);
	}
	
	/**
	 * Returns the string value for the specified key. If no value is available,
	 * the default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getValue(String key, String defaultValue) {
		String value = getValue(key);
		if(StringHelper.isNullOrEmpty(value)) {
			value = defaultValue;
		}
		
		return value;
	}
	
	/**
	 * Returns an int value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public int getIntValue(String key) {
		return getIntValue(key, Integer.MIN_VALUE);
	}
	
	/**
	 * Returns an int value for the specified key. If no value is available, the
	 * default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int getIntValue(String key, int defaultValue) {
		String value = getValue(key);
		return (StringHelper.isNullOrEmpty(value) ? defaultValue : Integer.parseInt(value));
	}
	
	/**
	 * Returns the boolean value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBooleanValue(String key) {
		return getBooleanValue(key, false);
	}
	
	/**
	 * Returns the boolean value for the specified key. If no value is
	 * available, the default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getBooleanValue(String key, boolean defaultValue) {
		String value = getValue(key);
		return (StringHelper.isNullOrEmpty(value) ? defaultValue : Boolean.valueOf(value));
	}
	
	/**
	 * Returns the long value for the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public long getLongValue(String key) {
		return getLongValue(key, -1l);
	}
	
	/**
	 * Returns the long value for the specified key. If no value is available,
	 * the default value is returned.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public long getLongValue(String key, long defaultValue) {
		String value = getValue(key);
		return (StringHelper.isNullOrEmpty(value) ? defaultValue : Long.parseLong(value));
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<String> getMutableObjects() {
		return mutableObjects;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<String> getImmutableObjects() {
		return immutableObjects;
	}
	
	/**
	 * @return
	 */
	public boolean isEnableGarbageCollection() {
		return getBooleanValue(ENABLE_GARBAGE_COLLECTION);
	}
	
	/**
	 * 
	 * @param enableGarbageCollection
	 */
	public void setEnableGarbageCollection(final boolean enableGarbageCollection) {
		setValue(ENABLE_GARBAGE_COLLECTION, enableGarbageCollection);
	}
	
	/**
	 * @return
	 */
	public long getGarbageCollectionSleepTime() {
		return getLongValue(GARBAGE_COLLECTION_SLEEP_TIME);
	}
	
	/**
	 * 
	 * @param garbageCollectionSleepTime
	 */
	public void setGarbageCollectionSleepTime(final long garbageCollectionSleepTime) {
		setValue(GARBAGE_COLLECTION_SLEEP_TIME, garbageCollectionSleepTime);
	}
	
	/**
	 * @return
	 */
	public boolean isAllowedRemoveFile() {
		return getBooleanValue(ALLOWED_REMOVE_FILE);
	}
	
	/**
	 * 
	 * @param allowedRemoveFile
	 */
	public void setAllowedRemoveFile(final boolean allowedRemoveFile) {
		setValue(ALLOWED_REMOVE_FILE, allowedRemoveFile);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDataFilePath() {
		return getValue(DATA_FILE_PATH);
	}
	
	/**
	 * 
	 * @param dataFilePath
	 */
	public void setDataFilePath(final String dataFilePath) {
		setValue(DATA_FILE_PATH, dataFilePath);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDataFileName() {
		return null;
	}
	
	/**
	 * 
	 * @param dataFileName
	 */
	public void setDataFileName(final String dataFileName) {
	}
	
	public int getMaxFileOperationRetry() {
		return 0;
	}
	
	/**
	 * 
	 * @param maxFileOperationRetry
	 */
	public void setMaxFileOperationRetry(final int maxFileOperationRetry) {
	}
	
	/**
	 * 
	 * @return
	 */
	public DataManager getDataManager() {
		return null;
	}
}
