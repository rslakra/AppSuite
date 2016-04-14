package com.apparatus.db.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:11:03 PM
 */
public final class PersistencePropertiesReader {
	
	// singleton instance
	private final static PersistencePropertiesReader INSTANCE = new PersistencePropertiesReader();
	
	/** data model identifier construction parameter */
	private static final String DATA_MODEL_IDENTIFIER = "dataModelIdentifier";
	
	/** conversion definition file construction parameter */
	private static final String DATA_MODEL_CONVERSION_DEF_FILE = "dataModelConvertionDefFile";
	
	private static final String FILE_ACCESS_MODE = "fileAccessMode";
	
	private static final String NUMBER_OF_FILE_BUFFER = "numberOfFileBuffer";
	
	private static final String FILE_BUFFER_SIZE = "fileBufferSize";
	
	/** storage directory path construction parameter */
	private static final String PATH = "path";
	
	/** crash safe mode on/off construction parameter */
	private static final String CRASH_SAFE_MODE = "crashSafeMode";
	
	/** proxy mode on/off construction parameter */
	private static final String PROXY_MODE = "proxyMode";
	
	/** use cache on/off construction parameter */
	private static final String FILE_CACHE = "fileCache";
	
	/** cache page size construction parameter */
	private static final String FILE_CACHE_PAGE_SIZE = "fileCache.pageSize";
	
	/** cache max number of page construction parameter */
	private static final String FILE_CACHE_MAX_PAGE = "fileCache.maxPage";
	
	/** garbage management on/off construction parameter */
	private static final String GARBAGE_MANAGEMENT = "garbageManagement";
	
	/** zip compression level setup */
	private static final String ZIP_COMPRESSION_LEVEL = "zipCompressionLevel";
	
	/** substitution of java util collection */
	private static final String SUBSTITUTION_OF_JAVA_UTIL_COLLECTION =
	/**/"substitutionOfJavaUtilCollection";
	
	/** activate or not background garbage sweep */
	private static final String BACKGROUND_GARBAGE_SWEEP = "backgroundGarbageSweep";
	
	/** declare stored enum */
	@Deprecated
	private static final String STORED_ENUM = "storedEnum.";
	
	/** declare stored enum */
	private static final String STORED_MUTABLE_ENUM = "storedMutableEnum.";
	
	/** declare stored enum */
	private static final String STORED_IMMUTABLE_ENUM = "storedImmutableEnum.";
	
	/** install custom object i/o */
	private static final String OBJECT_I_O = "objectIO.";
	
	/** set force enhance of class */
	private static final String FORCE_ENHANCE = "forceEnhance.";
	
	/** set storing mode of class */
	private static final String STORE_MODE = "storeMode.";
	
	/** set a class to be not storable */
	private static final String NOT_STORABLE = "notStorable.";
	
	/** set a class to be deprecated in store */
	private static final String DEPRECATED_IN_STORE = "deprecatedInStore.";
	
	private static final String STORE_ONLY_MARKED_STORABLE = "storeOnlyMarkedStorable";
	
	private static final String RECORD_SAVE_ACTIONS = "recordSaveActions";
	
	/** set substitution */
	private static final String SUBSTITUTE = "substitute.";
	
	private static final String MAINTENED_IN_MEMORY = "maintainedInMemory";
	
	private static final String MAINTENED_IN_MEMORY_QUOTA = "maintainedInMemoryQuota";
	
	private static final String AUTO_SAVE = "autoSave";
	
	private static final String MAX_IN_MEMORY_THRESHOLD = "maxInMemoryThreshold";
	
	private static final String DATA_FILE_NAME = "dataFileName";
	
	private static final String BACKUP_DATA_FILE_NAME = "backupDataFileName";
	
	private static final String STATE_OK_FLAG_FILE_NAME = "stateOkFlagFileName";
	
	private static final String STATE_BACKUP_OK_FLAG_FILE_NAME = "stateBackupOkFlagFileName";
	
	private static final String GLOBAL_STATE_FLAG_FILE_NAME = "globalStateFlagFileName";
	
	private static final String MAX_FILE_OPERATION_RETRY = "maxFileOperationRetry";
	
	private static final String FILE_OPERATION_RETRY_MS_DELAY = "fileOperationRetryMsDelay";
	
	private static final String NO_MORE_DATA_ACTION = "noMoreDataAction";
	
	private static final String DISABLED = "disabled";
	
	private static final String ENABLED = "enabled";
	
	/**
	 * 
	 * @return
	 */
	public static PersistencePropertiesReader getInstance() {
		return INSTANCE;
	}
	
	/**
	 * 
	 */
	private PersistencePropertiesReader() {
		super();
	}
	
	/**
	 * load the setup properties from properties resource
	 * 
	 * @param propertiesResourceName
	 * @param persistenceProperties
	 * @throws PersistenceException
	 */
	public void read(final String propertiesResourceName, final PersistenceProperties persistenceProperties) throws PersistenceException {
		try {
			
			final URL url = getClass().getResource(propertiesResourceName);
			final InputStream inputStream = url.openStream();
			final Properties properties = new Properties();
			properties.load(inputStream);
			final Set<Entry<Object, Object>> entrySet = properties.entrySet();
			for(Entry<Object, Object> entry : entrySet) {
				final String key = (String) entry.getKey();
				final String value = (String) entry.getValue();
				if(FILE_ACCESS_MODE.equals(key)) {
					// final EnumFileAccessMode fileAccessMode;
					// if("random".equals(value)) {
					// fileAccessMode = FileReader.RANDOM_FILE_ACCESS;
					// } else if("nio".equals(value)) {
					// fileAccessMode =
					// EnumFileAccessMode.NIO_RANDOM_FILE_ACCESS;
					// } else if("mapped".equals(value)) {
					// fileAccessMode =
					// EnumFileAccessMode.MAPPED_RANDOM_FILE_ACCESS;
					// } else {
					// throw new PersistenceException("bad value \"" + value
					// + "\",random, nio, or mapped expected");
					// }
					// persistenceProperties.setFileAccessMode(null);
					// } else if(NUMBER_OF_FILE_BUFFER.equals(key)) {
					// PersistenceProperties.setMaxNumberOfBuffer(intValue(key,
					// value));
					// } else if(FILE_BUFFER_SIZE.equals(key)) {
					// PersistenceProperties.setMaxBufferSize(intValue(key,
					// value));
					// } else if(PATH.equals(key)) {
					// PersistenceProperties.setPathName(value);
					// } else if(DATA_MODEL_IDENTIFIER.equals(key)) {
					// PersistenceProperties.setDataModelIdentifier(intValue(key,
					// value));
					// } else if(DATA_MODEL_CONVERSION_DEF_FILE.equals(key)) {
					// final URL conversionDefUrl =
					// classLoaderProvider.getResource(value);
					// final InputStream conversionDefInputStream =
					// conversionDefUrl.openStream();
					// final InputStreamAndSource fileInputStreamAndSource =
					// /**/new InputStreamAndSource(conversionDefInputStream,//
					// value);
					// PersistenceProperties.setDataModelConversionDefInputStream(fileInputStreamAndSource);
					// } else if(GARBAGE_MANAGEMENT.equals(key)) {
					// PersistenceProperties.setGarbageManagement(isEnabled(key,
					// value));
					// } else if(PROXY_MODE.equals(key)) {
					// PersistenceProperties.setProxyMode(isEnabled(key,
					// value));
					// } else if(CRASH_SAFE_MODE.equals(key)) {
					// PersistenceProperties.setCrashSafeMode(isEnabled(key,
					// value));
					// } else if(FILE_CACHE.equals(key)) {
					// PersistenceProperties.setUseCacheMode(isEnabled(key,
					// value));
					// } else if(FILE_CACHE_PAGE_SIZE.equals(key)) {
					// PersistenceProperties.setPageSize(intValue(key,
					// value));
					// } else if(FILE_CACHE_MAX_PAGE.equals(key)) {
					// PersistenceProperties.setMaxPage(intValue(key,
					// value));
					// } else if(ZIP_COMPRESSION_LEVEL.equals(key)) {
					// PersistenceProperties.setZipCompressionLevelSetted(true);
					// PersistenceProperties.setZipCompressionLevel(intValue(key,
					// value));
					// } else
					// if(SUBSTITUTION_OF_JAVA_UTIL_COLLECTION.equals(key)) {
					// PersistenceProperties.setSubsOfJavaUtil(isEnabled(key,
					// value));
					// } else if(key.startsWith(SUBSTITUTE)) {
					// addSubtitution(PersistenceProperties,
					// key.substring(SUBSTITUTE.length()), value);
					// } else if(key.startsWith(STORE_MODE)) {
					// addStoreMode(PersistenceProperties,
					// key.substring(STORE_MODE.length()), value);
					// } else if(key.startsWith(NOT_STORABLE)) {
					// addNotStorable(PersistenceProperties,
					// key.substring(NOT_STORABLE.length()), isEnabled(key,
					// value));
					// } else if(key.startsWith(DEPRECATED_IN_STORE)) {
					// addDeprecatedInStore(PersistenceProperties,
					// key.substring(DEPRECATED_IN_STORE.length()),
					// isEnabled(key, value));
					// } else if(key.equals(STORE_ONLY_MARKED_STORABLE)) {
					// PersistenceProperties.setStoreOnlyMarkedStorable(isEnabled(key,
					// value));
					// } else if(key.equals(RECORD_SAVE_ACTIONS)) {
					// PersistenceProperties.setRecordSaveActions(isEnabled(key,
					// value));
					// } else if(key.startsWith(FORCE_ENHANCE)) {
					// addForceEnhance(PersistenceProperties,
					// key.substring(FORCE_ENHANCE.length()), isEnabled(key,
					// value));
					// } else if(key.startsWith(OBJECT_I_O)) {
					// final String[] split = value.split(";");
					// if(split.length != 2) {
					// throw new
					// PersistenceException("must define two class name " +
					// key + "=" + value);
					// }
					// addObjectIo(PersistenceProperties,
					// key.substring(OBJECT_I_O.length()), split[0], split[1]);
					// } else if(key.startsWith(STORED_ENUM)) {
					// addStoredMutableEnum(PersistenceProperties,
					// key.substring(STORED_ENUM.length()), isEnabled(key,
					// value));
					// } else if(key.startsWith(STORED_MUTABLE_ENUM)) {
					// addStoredMutableEnum(PersistenceProperties,
					// key.substring(STORED_MUTABLE_ENUM.length()),
					// isEnabled(key, value));
					// } else if(key.startsWith(STORED_IMMUTABLE_ENUM)) {
					// addStoredImmutableEnum(PersistenceProperties,
					// key.substring(STORED_IMMUTABLE_ENUM.length()),
					// isEnabled(key, value));
					// } else if(BACKGROUND_GARBAGE_SWEEP.equals(key)) {
					//
					// final boolean backgroundGarbageSweepEnabled = true ^
					// DISABLED.equals(value);
					// PersistenceProperties.setBackgroundGarbageSweepEnabled(backgroundGarbageSweepEnabled);
					// if(backgroundGarbageSweepEnabled) {
					// PersistenceProperties.setBackgroundGarbageSweepSleepTime(intValue(key,
					// value));
					// }
					// } else if(key.equals(MAINTENED_IN_MEMORY)) {
					// PersistenceProperties.setMaintenedInMemory(isEnabled(key,
					// value));
					// } else if(key.equals(MAINTENED_IN_MEMORY_QUOTA)) {
					// PersistenceProperties.setMaintenedInMemoryQuota(intValue(key,
					// value));
					// } else if(key.equals(AUTO_SAVE)) {
					// PersistenceProperties.setAutoSaveEnabled(isEnabled(key,
					// value));
					// } else if(key.equals(MAX_IN_MEMORY_THRESHOLD)) {
					// PersistenceProperties.setMaxInMemoryThreshold(intValue(key,
					// value));
					// } else if(key.equals(DATA_FILE_NAME)) {
					// PersistenceProperties.setDataFileName(value);
					// } else if(key.equals(BACKUP_DATA_FILE_NAME)) {
					// PersistenceProperties.setBackupDataFileName(value);
					// } else if(key.equals(STATE_OK_FLAG_FILE_NAME)) {
					// PersistenceProperties.setStateOkFlagFileName(value);
					// } else if(key.equals(STATE_BACKUP_OK_FLAG_FILE_NAME)) {
					// PersistenceProperties.setStateBackupOkFlagFileName(value);
					// } else if(key.equals(GLOBAL_STATE_FLAG_FILE_NAME)) {
					// PersistenceProperties.setGlobalStateFlagFileName(value);
					// } else if(key.equals(MAX_FILE_OPERATION_RETRY)) {
					// PersistenceProperties.setMaxFileOperationRetry(intValue(key,
					// value));
					// } else if(key.equals(FILE_OPERATION_RETRY_MS_DELAY)) {
					// PersistenceProperties.setFileOperationRetryMsDelay(intValue(key,
					// value));
					// } else if(key.equals(NO_MORE_DATA_ACTION)) {
					// final EnumNoMoreDataAction noMoreDataAction;
					// if("delete".equals(value)) {
					// noMoreDataAction = EnumNoMoreDataAction.DELETE_FILE;
					// } else if("resize".equals(value)) {
					// noMoreDataAction = EnumNoMoreDataAction.RESIZE_FILE;
					// } else if("rename".equals(value)) {
					// noMoreDataAction = EnumNoMoreDataAction.RENAME_FILE;
					// } else if("preserve".equals(value)) {
					// noMoreDataAction = EnumNoMoreDataAction.PRESERVE_FILE;
					// } else {
					// throw new
					// PersistenceException("on of 'delete','resize','rename','preserve' value expected for key "
					// + key);
					// }
					// PersistenceProperties.setNoMoreDataAction(noMoreDataAction);
				}
			}
		} catch(MalformedURLException exception) {
			throw new PersistenceException(exception);
		} catch(IOException exception) {
			throw new PersistenceException(exception);
			// } catch(URISyntaxException exception) {
			// throw new PersistenceException(exception);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws PersistenceException
	 */
	private boolean isEnabled(final String key, final String value) throws PersistenceException {
		final String lowerCase = value.toLowerCase();
		final boolean result;
		if(ENABLED.equals(lowerCase)) {
			result = true;
		} else if(DISABLED.equals(lowerCase)) {
			result = false;
		} else {
			throw new PersistenceException("\"" + ENABLED + "\" or \"" + DISABLED + "\" expected for key " + key);
		}
		return result;
	}
	
	private void addSubtitution(final PersistenceProperties PersistenceProperties, final String toSubstituteClassName, final String substitudeAndSynchro) throws PersistenceException {
		final String[] strings = substitudeAndSynchro.split(",");
		if(strings.length != 2) {
			throw new PersistenceException("bad value " + substitudeAndSynchro);
		}
		final String substituteClassName = strings[0];
		final String synchronizerClassName = strings[1];
		// PersistenceProperties.getSubstitutionSet().add(new
		// PersistencePropertyEntry(new String[] { toSubstituteClassName,
		// substituteClassName, synchronizerClassName }));
	}
	
	private void addStoreMode(final PersistenceProperties PersistenceProperties, final String pathClassName, final String value) throws PersistenceException {
		// final EnumStoreMode storeMode = EnumStoreMode.forValue(value);
		// if(storeMode == null) {
		// throw new PersistenceException("bad value " + value + ", expected " +
		// EnumStoreMode.NOT_USE_STANDARD_SERIALIZATION.getValue() + ", " +
		// EnumStoreMode.SERIALIZE_AND_G_ZIPPED_IN_ONE_RECORD.getValue() + ", "
		// + EnumStoreMode.SERIALIZE_AND_ZIPPED_IN_ONE_RECORD.getValue() + ", "
		// + EnumStoreMode.SERIALIZE_IN_ONE_RECORD.getValue() + ", or " +
		// EnumStoreMode.STORE_NOT_LAZY + " expected");
		// }
		// PersistenceProperties.getStoreModeMap().put(pathClassName,
		// storeMode);
	}
	
	private void addNotStorable(final PersistenceProperties PersistenceProperties, final String pathClassName, final boolean value) throws PersistenceException {
		// PersistenceProperties.getNotStorableMap().put(pathClassName, value);
	}
	
	private void addDeprecatedInStore(final PersistenceProperties PersistenceProperties, final String pathClassName, final boolean value) throws PersistenceException {
		// PersistenceProperties.getDeprecatedInStoreMap().put(pathClassName,
		// value);
	}
	
	private void addStoredMutableEnum(final PersistenceProperties PersistenceProperties, final String enumClassName, final boolean enabled) {
		if(enabled) {
			PersistenceProperties.getMutableObjects().add(enumClassName);
		}
	}
	
	private void addStoredImmutableEnum(final PersistenceProperties PersistenceProperties, final String enumClassName, final boolean enabled) {
		if(enabled) {
			PersistenceProperties.getImmutableObjects().add(enumClassName);
		}
	}
	
	private void addObjectIo(final PersistenceProperties PersistenceProperties, final String className, final String objectInputClassName, final String objectOutputClassName) {
		// PersistenceProperties.getObjectIoSet().add(new
		// FilePersistencePropertyEntry(new String[] { className,
		// objectInputClassName, objectOutputClassName }));
	}
	
	private void addForceEnhance(final PersistenceProperties PersistenceProperties, final String className, final boolean enabled) {
		if(enabled) {
			// PersistenceProperties.getForceEnhanceSet().add(className);
		}
	}
	
}
