package com.apparatus.db.persistence;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.Deflater;

/**
 * manager for file persistence of java object<br>
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 4:45:03 PM
 */
public class FilePersistence extends AbstractPersistence {
	
	private static final String ROOT_OBJECT_MAP_NULLER_TRACE = "rootObjectMap nuller trace";
	private static final String UNDEFINED_ROOT_OBJECT_MAP = "undefined root object map";
	private static final String FILE_PERSISTENCE_CREATION_FAILED = "file persistence creation failed";
	private static final String CAN_NOT_RE_OPEN_CLOSE_PENDING = "can not re-open, close pending";
	private static final String CAN_NOT_CLOSE_AGAIN_CLOSE_PENDING = "can not close again, close pending";
	private static final String FAILED_CANCEL_OBJECTS_MODIFICATION = "failed cancel objects modification";
	private static final String FAILED_SAVE_OBJECTS = "failed save objects";
	private static final String NOT_OPENNED = "session is not openned";
	private static final String ALREADY_OPENNED = "session already openned";
	private static final String FAILED_SET_ROOT_OBJECT = "failed set root object";
	
	/** instance counter */
	private static int idCount = 0;
	
	private static Map<Integer, FilePersistence> openedFilePersitenceMap = new TreeMap<Integer, FilePersistence>();
	
	/** path of working directory */
	private File path;
	
	/**
	 * map of object to persist, this is the root object to persist for
	 * {@link IStore}. the instance exist only for use session.
	 */
	private transient Map<String, Object> rootObjectMap;
	
	private Exception rootObjectMapNuller = new Exception(ROOT_OBJECT_MAP_NULLER_TRACE);
	
	// /** all opened data access session */
	// private final Set<IDASFilePersistenceCallBack> openedDataAccessSessionSet
	// =
	// /**/new HashSet<IDASFilePersistenceCallBack>();
	//
	// private final Map<IDASFilePersistenceCallBack, Exception>
	// lastDataAccessSessionOpennerMap =
	// /**/new HashMap<IDASFilePersistenceCallBack, Exception>();
	//
	// /** all close pending data access session */
	// private final Set<IDASFilePersistenceCallBack>
	// closePendingDataAccessSessionSet =
	// /**/new HashSet<IDASFilePersistenceCallBack>();
	//
	// private final Map<IDASFilePersistenceCallBack, Exception>
	// lastDataAccessSessionCloserMap =
	// /**/new HashMap<IDASFilePersistenceCallBack, Exception>();
	//
	// /** the opened alone data access session, null if none */
	// private IExclusiveDataAccessSession exclusiveDataAccessSession;
	
	/** Compression level for no compression. */
	public static final int NO_COMPRESSION = Deflater.NO_COMPRESSION;
	
	/** Compression level for fastest compression. */
	public static final int BEST_SPEED = Deflater.BEST_SPEED;
	
	/** Compression level for best compression. */
	public static final int BEST_COMPRESSION = Deflater.BEST_COMPRESSION;
	
	/**
	 * Default compression level.
	 */
	public static final int DEFAULT_COMPRESSION = -1;
	
	/** file persistence properties setup */
	private PersistenceProperties persistenceProperties;
	
	/** true if opened, it is the case at construction */
	private boolean opened = true;
	
	private boolean previousSessionWasOpened;
	
	/** to trace this close invoker */
	private Exception closer;
	
	private boolean autoSaveEnable = true;
	
	// /**
	// *
	// * @return version information according to svn revision and location
	// */
	// public static String getVersion() {
	// return null;
	// }
	
	/**
	 * 
	 * @param persistenceProperties
	 * @throws PersistenceException
	 */
	public FilePersistence(final PersistenceProperties persistenceProperties) throws PersistenceException {
		super();
		synchronized(FilePersistence.class) {
			this.persistenceProperties = persistenceProperties;
			final String pathName = persistenceProperties.getDataFilePath();
			final DataManager dataManager = persistenceProperties.getDataManager();
			if(pathName == null) {
				if(dataManager == null) {
					final String message = "data manager or path must be set";
					// logger.error(FILE_PERSISTENCE_CREATION_FAILED + "\n"
					// + message);
					throw new PersistenceException(message);
				}
				lock((File) null);
			} else {
				if(dataManager != null) {
					final String message = "both data manager and file path set";
					// logger.error(FILE_PERSISTENCE_CREATION_FAILED + "\n"
					// + message);
					throw new PersistenceException(message);
				}
				path = new File(pathName);
				lock(path);
			}
			// setStore(new
			// Store(filePersistenceProperties.getStoreProperties()));
			
			// initialize
			initialise(persistenceProperties.isAllowedRemoveFile());
		}
	}
	
	//
	// /**
	// * create a persistence setup from properties<br>
	// *
	// * @param propertiesSetupResourceName
	// * properties to use for setup
	// * @param removeFiles
	// * true if remove files before open
	// * @throws PersistenceException
	// * @throws PersistenceSerializationException
	// * Some object to be serialized does not follow joafip
	// * persistence rules or does not implement the
	// * java.io.Serializable interface.
	// * @throws InvalidPersistenceClassException
	// * something is wrong with a class used by serialization
	// * @throws CorruptedDataException
	// * data is inconsistent.
	// * @throws PersistenceClassNotFoundException
	// * Class of a serialized object cannot be found.
	// */
	// public FilePersistence(final String propertiesSetupResourceName, final
	// boolean removeFiles) throws PersistenceException,
	// InvalidPersistenceClassException,
	// PersistenceSerializationException,
	// PersistenceClassNotFoundException,
	// CorruptedDataException {
	// super();
	// synchronized(FilePersistence.class) {
	// try {
	// persistenceProperties = new PersistenceProperties();
	// PersistencePropertiesReader.getInstance().read(propertiesSetupResourceName,
	// persistenceProperties);
	// lock(persistenceProperties.getPathName());
	// setStore(new Store(persistenceProperties.getStoreProperties()));
	// initialise(removeFiles);
	// } catch(final StoreException exception) {
	// unlockIgnoreError();
	// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
	// throw new PersistenceException(exception);
	// }
	// }
	// }
	//
	// private void setSubstitutionFromProperties() throws PersistenceException
	// {
	// final Set<FilePersistencePropertyEntry> set =
	// /**/filePersistenceProperties.getSubstitutionSet();
	// for(final FilePersistencePropertyEntry entry : set) {
	// final String[] strings = entry.getStrings();
	// final String toSubstituteClassName = strings[0];
	// final String substituteClassName = strings[1];
	// final String synchronizerClassName = strings[2];
	// try {
	// final Class<?> toSubstituteClass = Class.forName(toSubstituteClassName);
	// final Class<?> substituteClass = Class.forName(substituteClassName);
	// @SuppressWarnings(UNCHECKED)
	// final Class<? extends ISubsituteSynchronizer> synchronizerClass =
	// (Class<? extends ISubsituteSynchronizer>)
	// Class.forName(synchronizerClassName);
	// final ISubsituteSynchronizer synchronizer =
	// synchronizerClass.newInstance();
	// setWriteSubstitution(toSubstituteClass, substituteClass, synchronizer);
	// } catch(final ClassNotFoundException exception) {
	// throw new PersistenceException(exception);
	// } catch(final ClassCastException exception) {
	// throw new PersistenceException(exception);
	// } catch(final InstantiationException exception) {
	// throw new PersistenceException(exception);
	// } catch(final IllegalAccessException exception) {
	// throw new PersistenceException(exception);
	// }
	// }
	// }
	//
	// private void setForceEnhanceFromProperties() throws PersistenceException
	// {
	// final Set<String> set = persistenceProperties.getForceEnhanceSet();
	// for(final String className : set) {
	// try {
	// final Class<?> toForceEnhance = Class.forName(className);
	// setForceEnhance(toForceEnhance);
	// } catch(final ClassNotFoundException exception) {
	// throw new PersistenceException(exception);
	// }
	// }
	// }
	//
	// @SuppressWarnings(UNCHECKED)
	// private void setObjectIOForClassFromProperties() throws
	// PersistenceException {
	// final Set<FilePersistencePropertyEntry> set =
	// /**/filePersistenceProperties.getObjectIoSet();
	// for(final FilePersistencePropertyEntry entry : set) {
	// final String[] strings = entry.getStrings();
	// try {
	// final Class<? extends IObjectInput> objectInputClass =
	// /**/(Class<? extends IObjectInput>) Class.forName(strings[1]);
	// final Class<? extends IObjectOutput> objectOutputClass =
	// /**/(Class<? extends IObjectOutput>) Class.forName(strings[2]);
	// setObjectIOForClass(strings[0], objectInputClass, objectOutputClass);
	// } catch(final ClassNotFoundException exception) {
	// throw new PersistenceException(exception);
	// } catch(final ClassCastException exception) {
	// throw new PersistenceException(exception);
	// }
	// }
	// }
	//
	// @SuppressWarnings(UNCHECKED)
	// private void setStoredEnumFromProperties() throws PersistenceException {
	// try {
	// Set<String> set = filePersistenceProperties.getMutableEnumSet();
	// for(final String enumClassName : set) {
	// final Class<? extends Enum<?>> enumClass =
	// /**/(Class<? extends Enum<?>>) Class.forName(enumClassName);
	// storedMutableEnum(enumClass);
	// }
	//
	// set = filePersistenceProperties.getImmutableEnumSet();
	// for(final String enumClassName : set) {
	// final Class<? extends Enum<?>> enumClass =
	// /**/(Class<? extends Enum<?>>) Class.forName(enumClassName);
	// storedImmutableEnum(enumClass);
	// }
	//
	// } catch(final ClassNotFoundException exception) {
	// throw new PersistenceException(exception);
	// } catch(final ClassCastException exception) {
	// throw new PersistenceException(exception);
	// }
	// }
	//
	// private void setBackgroundGarbageSweepFromProperties() throws
	// PersistenceException {
	// if(filePersistenceProperties.isGarbageManagement()) {
	// if(filePersistenceProperties.isBackgroundGarbageSweepEnabled()) {
	// final int sleepTime =
	// filePersistenceProperties.getBackgroundGarbageSweepSleepTime();
	// enableBackgroundGarbageSweep(sleepTime);
	// } else {
	// disableBackgroundGarbageSweep();
	// }
	// }
	// }
	//
	// private void setSubstitutionOfJavaUtilCollectionFromProperties() throws
	// PersistenceException {
	// setSubstitutionOfJavaUtilCollection(filePersistenceProperties.isSubsOfJavaUtil());
	// }
	//
	// private void setZipCompressionLevelFromProperties() throws
	// PersistenceException {
	// if(filePersistenceProperties.isZipCompressionLevelSetted()) {
	// setZipCompressionLevel(filePersistenceProperties.getZipCompressionLevel());
	// }
	// }
	
	/**
	 * add lock on used file
	 * 
	 * @param pathName
	 * @throws PersistenceException
	 */
	private void lock(final String filePath) throws PersistenceException {
		lock(filePath == null ? null : new File(filePath));
	}
	
	/**
	 * add lock on used file
	 * 
	 * @param file
	 *            the used file
	 * @throws PersistenceException
	 *             file already in use
	 */
	private void lock(final File path) throws PersistenceException {
		this.path = path;
		if(path != null) {
			FilePersistenceLock.getInstance().lock(path);
		}
	}
	
	/**
	 * 
	 * @throws PersistenceException
	 */
	private void unlock() throws PersistenceException {
		FilePersistenceLock.getInstance().unlock(path);
	}
	
	/**
	 * initialization for construction<br>
	 * get root object and create it if do not exist ( first time use when data
	 * files not exists or removed )<br>
	 * synchronized to {@link FilePersistence} class<br>
	 * 
	 * @param removeFiles
	 *            true if remove files
	 * @throws PersistenceException
	 * @throws PersistenceSerializationException
	 *             Some object to be serialized does not follow joafip
	 *             persistence rules or does not implement the
	 *             java.io.Serializable interface.
	 * @throws InvalidPersistenceClassException
	 *             something is wrong with a class used by serialization
	 * @throws CorruptedDataException
	 *             data is inconsistent.
	 * @throws PersistenceClassNotFoundException
	 *             Class of a serialized object cannot be found.
	 * 
	 */
	private final void initialise(final boolean removeFiles) throws PersistenceException {
		final int persistenceId = idCount++;
		synchronized(FilePersistence.class) {
			openedFilePersitenceMap.put(persistenceId, this);
		}
		
		// store.setPersistenceId(persistenceId);
		if(path != null) {
			path.mkdirs();
		}
		
		// try {
		// openStore(removeFiles);
		// } catch(final StoreException exception) {
		// storeStop();
		// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
		// throw new PersistenceException(exception);
		// } catch(final StoreClassNotFoundException exception) {
		// storeStop();
		// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
		// throw new PersistenceClassNotFoundException(exception);
		// }
		
		// setZipCompressionLevelFromProperties();
		// setSubstitutionOfJavaUtilCollectionFromProperties();
		// setBackgroundGarbageSweepFromProperties();
		// setStoredEnumFromProperties();
		// setObjectIOForClassFromProperties();
		// setForceEnhanceFromProperties();
		// setStoreModeFromProperties();
		// setNotStorableFromProperties();
		// setDeprecatedInStoreFromProperties();
		// setSubstitutionFromProperties();
		// setStoreOnlyMarkedStorable(persistenceProperties.isStoreOnlyMarkedStorable());
		// setRecordSaveActions(persistenceProperties.isRecordSaveActions());
	}
	
	//
	// private void newAccessSessionAndReadOrCreateRootObject(final boolean
	// exclusiveAccessSession) throws PersistenceException,
	// PersistenceClassNotFoundException,
	// InvalidPersistenceClassException,
	// CorruptedDataException,
	// PersistenceSerializationException {
	// try {
	// storeNewAccessSession(exclusiveAccessSession);
	// if(!readRootObject()) {
	// createRootObject();
	// if(!previousSessionWasOpened) {
	// logger.warn("failed read root object, recreated");
	// }
	// }
	// final Map<EnumKey, Enum<?>> previous = storedEnumMap;
	// storedEnumMap = getStoredEnumMap();
	// if(storedEnumMap == null) {
	// storedEnumMap = previous;
	// setStoredEnumMap(storedEnumMap);
	// } else {
	// storedEnumMapPutAll(previous);
	// }
	// previousSessionWasOpened = true;
	// } catch(final PersistenceException exception) {
	// storeStop();
	// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
	// throw exception;
	// } catch(final PersistenceClassNotFoundException exception) {
	// storeStop();
	// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
	// throw exception;
	// } catch(final InvalidPersistenceClassException exception) {
	// storeStop();
	// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
	// throw exception;
	// } catch(final CorruptedDataException exception) {
	// storeStop();
	// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
	// throw exception;
	// } catch(final PersistenceSerializationException exception) {
	// storeStop();
	// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
	// throw exception;
	// } catch(final Exception exception) {
	// storeStop();
	// logger.error(FILE_PERSISTENCE_CREATION_FAILED, exception);
	// throw new PersistenceException(exception);
	// }
	// }
	//
	// private void storeStop() {
	// closer = new Exception("closer trace");
	// opened = false;
	// try {
	// closeStore();
	// } catch(final StoreException exception) {
	// // ignore error
	// logger.error("while stopping store", exception);
	// }
	// unlockIgnoreError();
	// }
	
	private void unlockIgnoreError() {
		try {
			if(path != null) {
				unlock();
			}
		} catch(final PersistenceException exception) {
			// ignore error
			// logger.warn("while unlocking", exception);
		}
	}
	
	@Override
	public void close() throws PersistenceException {
		// synchronized(mutex) {
		// try {
		// if(!opened) {
		// logger.error("filepersistence already closed");
		// throw new PersistenceException("already closed", closer);
		// }
		// closer = new Exception("closer trace");
		// opened = false;
		// if(path != null) {
		// unlock();
		// }
		// closeStore();
		// openedDataAccessSessionSet.clear();
		// lastDataAccessSessionOpennerMap.clear();
		// closePendingDataAccessSessionSet.clear();
		// lastDataAccessSessionCloserMap.clear();
		// } catch(final StoreException exception) {
		// storeStop();
		// logger.error("close error", exception);
		// throw new PersistenceException(exception);
		// } finally {
		// synchronized(FilePersistence.class) {
		// openedFilePersitenceMap.remove(store.getPersistenceId());
		// }
		// }
		// }
	}
	
	@Override
	public boolean isClosed() {
		return true ^ opened;
	}
	
	@Override
	public boolean isOpened() {
		return opened;
	}
	
	public static void closeAll() {
		synchronized(FilePersistence.class) {
			final List<FilePersistence> list = new LinkedList<FilePersistence>(openedFilePersitenceMap.values());
			for(final FilePersistence filePersistence : list) {
				try {
					filePersistence.close();
				} catch(final Exception exception) {
					// JoafipLogger.getLogger(FilePersistence.class).error("close all error",
					// exception);
					// resume after error
				}
			}
		}
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.AbstractPersistence#getLock()
	 */
	@Override
	public Object getLock() {
		// TODO Auto-generated method stub
		return null;
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
	
	@Override
	public void autoSaveSetup(int maxInMemoryThreshold) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void assertOpened() throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	// public DataAccessSession createDataAccessSession() throws
	// PersistenceException {
	// synchronized(mutex) {
	// try {
	// assertOpenned();
	// final DataAccessSession dataAccessSession = new DataAccessSession(this);
	// store.setSaveInDataAccessSessionEnabled(false);
	// return dataAccessSession;
	// } catch(final PersistenceException exception) {
	// storeStop();
	// logger.error("create data access session error", exception);
	// throw exception;
	// }
	// }
	// }
	// @Override
	// @Deprecated
	// public IAloneDataAccessSession createAloneDataAccessSession() throws
	// PersistenceException {
	// synchronized(mutex) {
	// try {
	// assertOpenned();
	// return new AloneDataAccessSession(this);
	// } catch(final PersistenceException exception) {
	// storeStop();
	// logger.error("create alone data access session error", exception);
	// throw exception;
	// }
	// }
	// }
	// @Override
	// public void clear() throws PersistenceException,
	// InvalidPersistenceClassException,
	// PersistenceSerializationException,
	// PersistenceClassNotFoundException,
	// CorruptedDataException {
	// synchronized(mutex) {
	// if(!openedDataAccessSessionSet.isEmpty() || exclusiveDataAccessSession !=
	// null) {
	// final PersistenceException PersistenceException = new
	// PersistenceException("can not clear because " +
	// (exclusiveDataAccessSession == null ? "exclusive" : ("" +
	// openedDataAccessSessionSet
	// .size())) + " data access session opened");
	// logger.error("clear error", PersistenceException);
	// throw PersistenceException;
	// }
	// storeNewAccessSession(false);
	// clearStoreContent();
	// storeEndAccessSession();
	// }
	// }
	
	// /**
	// *
	// * @param autoSaveEnable
	// */
	// @Override
	// public void setAutoSaveEnable(final boolean autoSaveEnable) {
	// this.autoSaveEnable = autoSaveEnable;
	// }
	//
	// /**
	// *
	// * @return
	// */
	// @Override
	// public boolean isAutoSaveEnable() {
	// return autoSaveEnable;
	// }
	//
	// @Override
	// public boolean autoSave() throws PersistenceException,
	// InvalidPersistenceClassException, PersistenceSerializationException,
	// PersistenceClassNotFoundException, CorruptedDataException,
	// FilePersistenceTooBigForSerializationException {
	// synchronized(mutex) {
	// final boolean done;
	// if(autoSaveEnable) {
	// autoSaveEnable = false;
	// privateSave(true);
	// autoSaveEnable = true;
	// done = true;
	// } else {
	// done = false;
	// }
	// return done;
	// }
	// }
	//
	// public void save() throws PersistenceException,
	// InvalidPersistenceClassException, PersistenceSerializationException,
	// PersistenceClassNotFoundException, CorruptedDataException,
	// FilePersistenceTooBigForSerializationException {
	// synchronized(mutex) {
	// privateSave(false);
	// }
	// }
	//
	// private void privateSave(final boolean autoSave) throws
	// PersistenceException, InvalidPersistenceClassException,
	// PersistenceSerializationException, PersistenceClassNotFoundException,
	// CorruptedDataException, FilePersistenceTooBigForSerializationException {
	// try {
	// assertDataAccessSessionClosed();
	// assertExclusiveDataAccessSessionOpened();
	// save(false, autoSave);
	// } catch(final PersistenceException exception) {
	// storeStop();
	// throw exception;
	// } catch(final PersistenceClassNotFoundException exception) {
	// storeStop();
	// throw exception;
	// } catch(final InvalidPersistenceClassException exception) {
	// storeStop();
	// throw exception;
	// } catch(final CorruptedDataException exception) {
	// storeStop();
	// throw exception;
	// } catch(final PersistenceSerializationException exception) {
	// storeStop();
	// throw exception;
	// } catch(final FilePersistenceTooBigForSerializationException exception) {
	// storeStop();
	// throw exception;
	// }
	// }
	//
	// private void assertDataAccessSessionClosed() throws PersistenceException
	// {
	// if(!openedDataAccessSessionSet.isEmpty() ||
	// !closePendingDataAccessSessionSet.isEmpty()) {
	// throw new PersistenceException("have data access session used");
	// }
	// }
	//
	// private void assertExclusiveDataAccessSessionOpened() throws
	// PersistenceException {
	// if(this.exclusiveDataAccessSession == null) {
	// throw new PersistenceException("no alone data access session opened");
	// }
	// }
	//
	// private void assertExclusiveDataAccessSessionClosed() throws
	// PersistenceException {
	// if(exclusiveDataAccessSession != null) {
	// throw new PersistenceException("alone data access session is opened");
	// }
	// }
	//
	// /**
	// * @param closing
	// * true if save for closing
	// * @param autoSave
	// * true if call for auto save
	// * @throws PersistenceException
	// * failed save in file, no file in stable state, file state
	// * restored to stable, file persistence is not opened
	// * @throws InvalidPersistenceClassException
	// * something is wrong with a class used by serialization
	// * @throws PersistenceSerializationException
	// * Some object to be serialized does not follow joafip
	// * persistence rules or does not implement the
	// * java.io.Serializable interface.
	// * @throws PersistenceClassNotFoundException
	// * Class of a serialized object cannot be found.
	// * @throws CorruptedDataException
	// * data is inconsistent
	// * @throws FilePersistenceTooBigForSerializationException
	// *
	// */
	// @SuppressWarnings("unchecked")
	// @StorableAccess
	// private void save(final boolean closing, final boolean autoSave) throws
	// PersistenceException, InvalidPersistenceClassException,
	// PersistenceSerializationException, PersistenceClassNotFoundException,
	// CorruptedDataException, FilePersistenceTooBigForSerializationException {
	// final boolean runAutosaveEnableBack = runAutosaveEnable;
	// runAutosaveEnable = false;
	// try {
	// // ASSERTX
	// assert assertRootObjectMapDefined();
	// /*
	// * important : closing test before rootObjectMap.isEmpty(). because
	// * save can be called when running in rootObjectMap instance, so no
	// * method must be call here to avoid change object state.
	// */
	// final boolean clear;
	// if(closing) {
	// clear = rootObjectMap.isEmpty();
	// } else {
	// clear = false;
	// }
	// /*
	// * =null is to enable gc to free memory, important to do that before
	// * save to unreference object
	// */
	// rootObjectMap = null;
	// if(clear) {
	// rootObjectMapNuller = new Exception(ROOT_OBJECT_MAP_NULLER_TRACE +
	// ", save clear");
	//
	// storedEnumMap = new PTreeMap<EnumKey, Enum<?>>();
	// createEmptyStore();
	// } else {
	// rootObjectMapNuller = new Exception(ROOT_OBJECT_MAP_NULLER_TRACE +
	// ", save not clear");
	// storeSave(closing, autoSave);
	// if(closing) {
	// storedEnumMap = new PTreeMap<EnumKey, Enum<?>>();
	// } else {
	// rootObjectMap = (Map<String, Object>) storeGetRoot();
	// rootObjectMapNuller = this.rootObjectMap == null ? new
	// Exception(ROOT_OBJECT_MAP_NULLER_TRACE + ", read saved") : null;
	// storedEnumMap = getStoredEnumMap();
	// }
	// }
	// } catch(final StoreException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new PersistenceException(FAILED_SAVE_OBJECTS, exception);
	// } catch(final StoreInvalidClassException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new InvalidPersistenceClassException(FAILED_SAVE_OBJECTS,
	// exception);
	// } catch(final StoreNotSerializableException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new PersistenceSerializationException(FAILED_SAVE_OBJECTS,
	// exception);
	// } catch(final StoreClassNotFoundException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new PersistenceClassNotFoundException(FAILED_SAVE_OBJECTS,
	// exception);
	// } catch(final StoreDataCorruptedException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new CorruptedDataException(FAILED_SAVE_OBJECTS, exception);
	// } catch(final StoreTooBigForSerializationException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new
	// FilePersistenceTooBigForSerializationException(FAILED_SAVE_OBJECTS,
	// exception);
	// } finally {
	// runAutosaveEnable = runAutosaveEnableBack;
	// }
	// }
	//
	// private void storedEnumMapPutAll(final Map<EnumKey, Enum<?>> map) {
	// for(final Map.Entry<EnumKey, Enum<?>> entry : map.entrySet()) {
	// storedEnumMap.put(entry.getKey(), entry.getValue());
	// }
	// }
	//
	// private boolean assertRootObjectMapDefined() throws PersistenceException
	// {
	// if(rootObjectMap == null) {
	// throw new PersistenceException(UNDEFINED_ROOT_OBJECT_MAP,
	// rootObjectMapNuller);
	// }
	// return true;
	// }
	//
	// private void doNotSave() throws PersistenceException {
	// try {
	// storeDoNotSave();
	// // =null is to enable gc to free memory
	// rootObjectMap = null;
	// rootObjectMapNuller = new Exception(ROOT_OBJECT_MAP_NULLER_TRACE);
	// } catch(final StoreException exception) {
	// logger.fatal(FAILED_CANCEL_OBJECTS_MODIFICATION, exception);
	// throw new PersistenceException(FAILED_CANCEL_OBJECTS_MODIFICATION,
	// exception);
	// }
	// }
	//
	// private void clearStoreContent() throws PersistenceException,
	// InvalidPersistenceClassException, PersistenceSerializationException,
	// PersistenceClassNotFoundException, CorruptedDataException {
	// try {
	// storeDoNotSave();
	// rootObjectMap = null;
	// rootObjectMapNuller = new Exception(ROOT_OBJECT_MAP_NULLER_TRACE);
	// createEmptyStore();
	// } catch(final StoreException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new PersistenceException(FAILED_SAVE_OBJECTS, exception);
	// } catch(final StoreClassNotFoundException exception) {
	// logger.fatal(FAILED_SAVE_OBJECTS, exception);
	// throw new PersistenceClassNotFoundException(FAILED_SAVE_OBJECTS,
	// exception);
	// }
	// }
	//
	// private void createEmptyStore() throws StoreException,
	// StoreClassNotFoundException, PersistenceException,
	// InvalidPersistenceClassException, PersistenceSerializationException,
	// PersistenceClassNotFoundException, CorruptedDataException {
	// switch(filePersistenceProperties.getNoMoreDataAction()) {
	// case DELETE_FILE:
	// case RENAME_FILE:
	// closeStore();
	// openStore(true/* remove files */);
	// createRootObject();
	// break;
	// case PRESERVE_FILE:
	// case RESIZE_FILE:
	// clearStore();
	// break;
	// default:
	// throw new PersistenceException("unknows action " +
	// filePersistenceProperties.getNoMoreDataAction());
	// }
	// }
	//
	// /**
	// * get a persistent object by its key<br>
	// *
	// * @param key
	// * the persistent object key
	// * @return the object or null if none for the key
	// * @throws PersistenceException
	// */
	// @SuppressWarnings("PMD")
	// @StorableAccess
	// Object getObject(final String key) throws PersistenceException {
	// synchronized(mutex) {
	// assertRootObjectMapDefined();
	// // ASSERTX
	// assert key != null : "key is null, must have a value";
	// return rootObjectMap.get(key);
	// }
	// }
	//
	// @SuppressWarnings("PMD")
	// @StorableAccess
	// Object getObject(final long dataRecordIdentifier) throws
	// PersistenceException, PersistenceClassNotFoundException,
	// InvalidPersistenceClassException, CorruptedDataException,
	// PersistenceSerializationException {
	// synchronized(mutex) {
	// return createObjectReadingInStoreOrGetExisting(new
	// DataRecordIdentifier(dataRecordIdentifier));
	// }
	// }
	//
	// /**
	// * add a persistent object associated to a key
	// *
	// * @param key
	// * the object access key
	// * @param object
	// * the persistent object
	// * @return the replaced object, null if none
	// * @throws PersistenceException
	// */
	// @SuppressWarnings("PMD")
	// @StorableAccess
	// Object setObject(final String key, final Object object) throws
	// PersistenceException {
	// synchronized(mutex) {
	// assertRootObjectMapDefined();
	// // ASSERTX
	// assert key != null : "key is null, must have a value";
	// // ASSERTX
	// assert object != null : "can not store null objet";
	// return rootObjectMap.put(key, object);
	// }
	// }
	//
	// /**
	// * remove a persistent object associated to a key
	// *
	// * @param key
	// * the object access key
	// * @throws PersistenceException
	// */
	// @SuppressWarnings("PMD")
	// @StorableAccess
	// Object removeObject(final String key) throws PersistenceException {
	// synchronized(mutex) {
	// assertRootObjectMapDefined();
	// return rootObjectMap.remove(key);
	// }
	// }
	//
	// /**
	// * remove all persistent object
	// *
	// * @throws PersistenceException
	// */
	// @SuppressWarnings("PMD")
	// @StorableAccess
	// void removeAllObject() throws PersistenceException {
	// synchronized(mutex) {
	// assertRootObjectMapDefined();
	// rootObjectMap.clear();
	// }
	// }
	//
	// /**
	// *
	// * @return key set of persistent objects
	// * @throws PersistenceException
	// */
	// @SuppressWarnings("PMD")
	// @StorableAccess
	// Set<String> objectKeySet() throws PersistenceException {
	// synchronized(mutex) {
	// assertRootObjectMapDefined();
	// return rootObjectMap.keySet();
	// }
	// }
	//
	// /**
	// *
	// * @return all persistent objects
	// * @throws PersistenceException
	// */
	// @SuppressWarnings("PMD")
	// @StorableAccess
	// Collection<Object> getObjects() throws PersistenceException {
	// synchronized(mutex) {
	// assertRootObjectMapDefined();
	// return rootObjectMap.values();
	// }
	// }
	//
	// /**
	// * for tests only ( used to check all user thread have ended usage )
	// *
	// * @return true if used by at least one data access session
	// * @throws PersistenceException
	// */
	// @Fortest
	// public boolean isUsed() throws PersistenceException {
	// synchronized(mutex) {
	// assertOpenned();
	// return !openedDataAccessSessionSet.isEmpty();
	// }
	// }
	//
	// /**
	// * root object creation and storing
	// *
	// * @throws PersistenceException
	// * @throws InvalidPersistenceClassException
	// * something is wrong with a class used by serialization
	// * @throws PersistenceSerializationException
	// * Some object to be serialized does not follow joafip
	// * persistence rules or does not implement the
	// * java.io.Serializable interface.
	// * @throws PersistenceClassNotFoundException
	// * Class of a serialized object cannot be found.
	// * @throws CorruptedDataException
	// * data is inconsistent
	// *
	// */
	// private void createRootObject() throws PersistenceException,
	// InvalidPersistenceClassException, PersistenceSerializationException,
	// PersistenceClassNotFoundException, CorruptedDataException {
	// Map<String, Object> newRootObjectMap = new PTreeMap<String, Object>();
	// // below fail when nothing in store because new instance have data
	// // record identifier 1
	// // Map<String, Object> newRootObjectMap =(Map<String, Object>)
	// // newInstance(PTreeMap.class);
	// try {
	// storeSetRoot(newRootObjectMap, null/* no stored Enum */);
	// newRootObjectMap = null;
	// storeSave(true, false);
	// // read just saved to have proxy instance
	// if(!readRootObject()) {
	// final String message = FAILED_SET_ROOT_OBJECT +
	// ". Must read just recorded root object";
	// logger.fatal(message);
	// throw new PersistenceException(message);
	// }
	// } catch(final StoreException exception) {
	// logger.fatal(FAILED_SET_ROOT_OBJECT, exception);
	// throw new PersistenceException(FAILED_SET_ROOT_OBJECT, exception);
	// } catch(final StoreInvalidClassException exception) {
	// logger.fatal(FAILED_SET_ROOT_OBJECT, exception);
	// throw new InvalidPersistenceClassException(FAILED_SET_ROOT_OBJECT,
	// exception);
	// } catch(final StoreNotSerializableException exception) {
	// logger.fatal(FAILED_SET_ROOT_OBJECT, exception);
	// throw new PersistenceSerializationException(FAILED_SET_ROOT_OBJECT,
	// exception);
	// } catch(final StoreClassNotFoundException exception) {
	// throw new PersistenceClassNotFoundException(FAILED_SET_ROOT_OBJECT,
	// exception);
	// } catch(final StoreDataCorruptedException exception) {
	// throw new CorruptedDataException(FAILED_SET_ROOT_OBJECT, exception);
	// } catch(final StoreTooBigForSerializationException exception) {
	// throw new CorruptedDataException(FAILED_SET_ROOT_OBJECT, exception);
	// }
	// }
	//
	// /**
	// * read existing root object in file
	// *
	// * @return existing root object read from file or null if none found
	// * @throws PersistenceException
	// * failed read root object
	// * @throws PersistenceClassNotFoundException
	// * Class of a serialized object cannot be found.
	// * @throws InvalidPersistenceClassException
	// * something is wrong with a class used by serialization
	// * @throws CorruptedDataException
	// * data is inconsistent.
	// * @throws PersistenceSerializationException
	// *
	// */
	// @SuppressWarnings(UNCHECKED)
	// private boolean readRootObject() throws PersistenceException,
	// PersistenceClassNotFoundException, InvalidPersistenceClassException,
	// CorruptedDataException, PersistenceSerializationException {
	// final boolean read;
	// try {
	// read = storeReadRoot();
	// rootObjectMap = (Map<String, Object>) storeGetRoot();
	// rootObjectMapNuller = this.rootObjectMap == null ? new
	// Exception(ROOT_OBJECT_MAP_NULLER_TRACE) : null;
	// } catch(final StoreException exception) {
	// throw new PersistenceException(exception);
	// } catch(final StoreClassNotFoundException exception) {
	// throw new PersistenceClassNotFoundException(exception);
	// } catch(final StoreInvalidClassException exception) {
	// throw new InvalidPersistenceClassException(exception);
	// } catch(final StoreDataCorruptedException exception) {
	// throw new CorruptedDataException(exception);
	// } catch(final StoreNotSerializableException exception) {
	// throw new PersistenceSerializationException(exception);
	// }
	// return read;
	// }
	//
	// /**
	// * add session to opened session set
	// *
	// * @param session
	// * the session to add
	// *
	// * @throws PersistenceException
	// * session already registered, session close pending
	// */
	// private void addOpenedSession(final IDASFilePersistenceCallBack session)
	// throws PersistenceException {
	// if(closePendingDataAccessSessionSet.contains(session)) {
	// logger.fatal(CAN_NOT_RE_OPEN_CLOSE_PENDING);
	// throw new PersistenceException(CAN_NOT_RE_OPEN_CLOSE_PENDING,
	// lastDataAccessSessionCloserMap.get(session));
	// }
	// if(!openedDataAccessSessionSet.add(session)) {
	// logger.fatal(ALREADY_OPENNED,
	// lastDataAccessSessionOpennerMap.get(session));
	// throw new PersistenceException(ALREADY_OPENNED,
	// lastDataAccessSessionOpennerMap.get(session));
	// }
	// lastDataAccessSessionOpennerMap.put(session, new
	// Exception("last openner trace"));
	// }
	//
	// /**
	// * remove opened session
	// *
	// * @param session
	// * the opened session to remove
	// * @throws PersistenceException
	// * session close pending,session not registered
	// */
	// private void removeOpenedSession(final IDASFilePersistenceCallBack
	// session) throws PersistenceException {
	// if(closePendingDataAccessSessionSet.contains(session)) {
	// logger.fatal(CAN_NOT_CLOSE_AGAIN_CLOSE_PENDING,
	// lastDataAccessSessionCloserMap.get(session));
	// throw new PersistenceException(CAN_NOT_CLOSE_AGAIN_CLOSE_PENDING,
	// lastDataAccessSessionCloserMap.get(session));
	// }
	// if(!openedDataAccessSessionSet.remove(session)) {
	// logger.fatal(NOT_OPENNED, lastDataAccessSessionCloserMap.get(session));
	// throw new PersistenceException(NOT_OPENNED,
	// lastDataAccessSessionCloserMap.get(session));
	// }
	// lastDataAccessSessionOpennerMap.remove(session);
	// }
	//
	// /**
	// * @throws PersistenceException
	// */
	// @Override
	// protected void assertSessionClosed() throws PersistenceException {
	// if(!openedDataAccessSessionSet.isEmpty() || exclusiveDataAccessSession !=
	// null) {
	// throw new PersistenceException("some data access session opened");
	// }
	// }
	//
	// /**
	// * @throws PersistenceException
	// */
	// @Override
	// protected void assertSessionOpenned() throws PersistenceException {
	// if(openedDataAccessSessionSet.isEmpty() && exclusiveDataAccessSession ==
	// null) {
	// throw new PersistenceException("no data access session opened");
	// }
	// }
	//
	// /**
	// * to have the file state give by exception or its primary cause
	// *
	// * @param exception
	// * exception
	// * @return file state for exception
	// */
	// static public EnumFileState fileState(final Exception exception) {
	// return HeapFileStateHelper.fileState(exception);
	// }
	//
	// static public FileIOException fileIOException(final Exception exception)
	// {
	// FileIOException fileIOException = null;
	// Throwable current = exception;
	// while(fileIOException == null && current != null) {
	// if(current instanceof FileIOException) {
	// fileIOException = (FileIOException) current;
	// }
	// current = current.getCause();
	// }
	// return fileIOException;
	// }
	//
	// /**
	// *
	// * @param exception
	// * the exception in which search for target invocation exception
	// * @return exception thrown at target invocation, null if none
	// */
	// static public Throwable targetCause(final Exception exception) {
	// return ExceptionOfTarget.targetCause(exception);
	// }
	//
	// @SuppressWarnings("PMD")
	// void assertOpenned() throws PersistenceException {
	// if(!opened) {
	// throw new PersistenceException("not openned", closer);
	// }
	// }
	//
	// /**
	// * for test only
	// *
	// * @param idCount
	// * @throws PersistenceException
	// */
	// @Fortest
	// public static void setIdCount(final int idCount) throws
	// PersistenceException {
	// FilePersistence.idCount = idCount;
	// throw new PersistenceException("do not use, for tests only");
	// }
	//
	// @Override
	// public void storedMutableEnum(final Class<? extends Enum<?>> enumClass)
	// throws PersistenceException {
	// synchronized(mutex) {
	// try {
	// final Enum<?>[] enumArray =
	// /**/(Enum[]) HelperReflect.getInstance().invokeStaticMethod(
	// /**/enumClass, "values", new Class<?>[] {}, new Object[] {});
	// if(storedEnumMap == null) {
	// storedEnumMap = new PTreeMap<EnumKey, Enum<?>>();
	// }
	// for(final Enum<?> enumValue : enumArray) {
	// final EnumKey enumkey = new EnumKey(enumValue);
	// storedEnumMap.put(enumkey, enumValue);
	// }
	// } catch(final ReflectException exception) {
	// throw new PersistenceException(exception);
	// }
	// }
	// }
	//
	// @Override
	// public void storedMutableEnum(final Class<? extends Enum<?>>[]
	// enumClasses) throws PersistenceException {
	// for(final Class<? extends Enum<?>> enumClass : enumClasses) {
	// storedMutableEnum(enumClass);
	// }
	// }
	//
	// @Override
	// public void xmlImport(final String directoryName, final boolean
	// validating) throws PersistenceException,
	// PersistenceClassNotFoundException, InvalidPersistenceClassException,
	// CorruptedDataException, PersistenceSerializationException {
	// synchronized(mutex) {
	// try {
	// assertSessionClosed();
	// newAccessSessionAndReadOrCreateRootObject(false);
	// assertNoObjectStored();
	// store.xmlImport(directoryName, validating);
	// } catch(final StoreException exception) {
	// throw new PersistenceException(exception);
	// } catch(final StoreClassNotFoundException exception) {
	// throw new PersistenceClassNotFoundException(exception);
	// } catch(final StoreInvalidClassException exception) {
	// throw new InvalidPersistenceClassException(exception);
	// } catch(final StoreDataCorruptedException exception) {
	// throw new CorruptedDataException(exception);
	// } catch(final StoreNotSerializableException exception) {
	// throw new PersistenceSerializationException(exception);
	// } finally {
	// try {
	// store.endAccessSession();
	// } catch(final StoreException exception2) {
	// logger.error("while ending session after error", exception2);
	// }
	// }
	// }
	// }
	//
	// /**
	// * assert there is no object stored
	// *
	// * @throws PersistenceSerializationException
	// * @throws CorruptedDataException
	// * @throws InvalidPersistenceClassException
	// * @throws PersistenceClassNotFoundException
	// * @throws PersistenceException
	// */
	// private void assertNoObjectStored() throws PersistenceException,
	// PersistenceClassNotFoundException, InvalidPersistenceClassException,
	// CorruptedDataException, PersistenceSerializationException {
	// assertRootObjectMapDefined();
	// // if(readRootObject()) {
	// // final boolean notEmpty = true ^ rootObjectMap.isEmpty();
	// // if(notEmpty) {
	// // throw new PersistenceException("have stored object, not empty");
	// // }
	// // }
	// // try {
	// // // storeDoNotSave();
	// // } catch(final StoreException exception) {
	// // throw new PersistenceException(exception);
	// // }
	// }
	//
	// @Override
	// public Object getLock() {
	// // TODO Auto-generated method stub
	// return null;
	// }
}