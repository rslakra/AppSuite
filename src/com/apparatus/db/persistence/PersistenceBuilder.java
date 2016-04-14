package com.apparatus.db.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 12:43:24 PM
 */
public class PersistenceBuilder {
	
	// persistenceProperties
	private final PersistenceProperties persistenceProperties = new PersistenceProperties();
	
	/**
	 * 
	 * @param autoSaveEnabled
	 */
	public void setAutoSaveEnabled(final boolean autoSaveEnabled) {
		persistenceProperties.setValue(PersistenceProperties.AUTO_SAVE_ENABLED, autoSaveEnabled);
	}
	
	/**
	 * The dataManager to be set.
	 * 
	 * @param dataManager
	 */
	public void setDataManager(final DataManager dataManager) {
		if(dataManager != null) {
			persistenceProperties.setValue(dataManager.getType(), dataManager);
		}
	}
	
	/**
	 * 
	 * @return file persistence manager
	 * @throws FilePersistenceNotSerializableException
	 *             Some object to be serialized does not follow joafip
	 *             persistence rules or does not implement the
	 *             java.io.Serializable interface.
	 * @throws FilePersistenceInvalidClassException
	 *             something is wrong with a class used by serialization
	 * @throws FilePersistenceDataCorruptedException
	 *             data is inconsistent.
	 * @throws FilePersistenceClassNotFoundException
	 *             Class of a serialized object cannot be found.
	 */
	public Persistence build() throws PersistenceException, PersistenceClassNotFoundException, InvalidPersistenceClassException, CorruptedDataException, PersistenceSerializationException {
		return new FilePersistence(persistenceProperties);
	}
}
