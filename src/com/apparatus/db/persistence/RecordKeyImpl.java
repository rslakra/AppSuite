package com.apparatus.db.persistence;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:34:40 PM
 */
public class RecordKeyImpl implements RecordKey {
	
	private RecordKeyManager recordKeyManager;
	private byte[] data;
	
	private transient Object key;
	
	/**
	 * 
	 * @param recordKeyManager
	 * @param data
	 */
	public RecordKeyImpl(final RecordKeyManager recordKeyManager, final byte[] data) {
		super();
		this.recordKeyManager = recordKeyManager;
		this.data = data;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.RecordKey#getKey()
	 */
	@Override
	public Object getKey() throws HeapException {
		if(key == null) {
			key = recordKeyManager.unmarshall(data);
		}
		
		return key;
	}
	
	/**
	 * 
	 * @param key
	 */
	public void setKey(final Object key) {
		this.key = key;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("DataRecordKey [key=");
		builder.append(key);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.RecordKey#getHashcode()
	 */
	@Override
	public int getHashcode() {
		return 0;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.RecordKey#compareTo(com.rslakra.db.persistence.RecordKey)
	 */
	@Override
	public int compareTo(RecordKey recordKey) {
		try {
			return recordKeyManager.compareRecordKeys(this, recordKey);
		} catch(HeapException ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.RecordKey#getData()
	 */
	@Override
	public byte[] getData() {
		return data;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.RecordKey#getDataSize()
	 */
	@Override
	public int getDataSize() {
		return (data == null ? 0 : data.length);
	}
}
