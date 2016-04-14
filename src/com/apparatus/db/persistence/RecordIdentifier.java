package com.apparatus.db.persistence;

import java.io.Serializable;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:39:55 PM
 */
public class RecordIdentifier implements Comparable<RecordIdentifier>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final RecordIdentifier ZERO = new RecordIdentifier();
	public static final RecordIdentifier LAST = new RecordIdentifier(Long.MAX_VALUE);
	
	public final long value;
	private final RecordKey recordKey;
	private int index;
	
	/**
	 * create the first data record identifier: #0
	 * 
	 */
	public RecordIdentifier() {
		this(0L);
	}
	
	/**
	 * create a data record identifier with initial small value ( the firsts
	 * data recording )
	 * 
	 * @param value
	 */
	public RecordIdentifier(final int value) {
		this(new Long(value).longValue());
	}
	
	/**
	 * create setting value
	 * 
	 * @param long1
	 * @param long2
	 */
	public RecordIdentifier(final long value) {
		super();
		this.value = value;
		this.recordKey = null;
	}
	
	/**
	 * create the next value of a data record identifier
	 * 
	 * @param dataRecordIdentifier
	 *            reference data identifier
	 */
	public RecordIdentifier(final RecordIdentifier recordIdentifier) {
		super();
		this.value = recordIdentifier.value + 1;
		this.recordKey = null;
	}
	
	/**
	 * 
	 * @param recordKey
	 */
	public RecordIdentifier(final RecordKey recordKey) {
		super();
		this.value = -1;
		this.recordKey = recordKey;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDataSize() {
		return (value == -1 ? recordKey.getDataSize() : 0);
	}
	
	/**
	 * 
	 * @return
	 */
	public byte[] getData() {
		return value == -1 ? recordKey.getData() : null;
	}
	
	/**
	 * 
	 * @return
	 * @throws HeapException
	 */
	public Object getKey() throws HeapException {
		return (value == -1 ? recordKey.getKey() : null);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * 
	 * @param index
	 */
	public void setIndex(final int index) {
		this.index = index;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) (value ^ (value >>> 32)) + (value == -1 ? recordKey.getHashcode() : 0);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		return (this == object ? true : value == ((RecordIdentifier) object).value && (value != -1 || recordKey.compareTo(((RecordIdentifier) object).recordKey) == 0));
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value == -1 ? recordKey.toString() : String.valueOf(value);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RecordIdentifier recordIdentifier) {
		return (value < recordIdentifier.value ? -1 : (value > recordIdentifier.value ? 1 : ((value == -1 && recordKey != null) ? recordKey.compareTo(recordIdentifier.recordKey) : 0)));
	}
	
}
