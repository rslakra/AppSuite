package com.apparatus.db.persistence;

import java.util.Comparator;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:41:37 PM
 * @param <K>
 */
public abstract class AbstractRecordKeyComparator<K extends Comparable<K>> implements RecordKeyManager {
	
	private final Comparator<? super K> comparator;
	
	/**
	 * 
	 * @param comparator
	 */
	public AbstractRecordKeyComparator(final Comparator<? super K> comparator) {
		super();
		this.comparator = comparator;
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.RecordKeyManager#compareRecordKeys(com.rslakra.db.persistence.RecordKey,
	 *      com.rslakra.db.persistence.RecordKey)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int compareRecordKeys(RecordKey leftRecordKey, RecordKey rightRecordKey) throws HeapException {
		final K leftKey = (K) leftRecordKey.getKey();
		final K rightKey = (K) rightRecordKey.getKey();
		return comparator == null ? leftKey.compareTo(rightKey) : comparator.compare(leftKey, rightKey);
	}
	
}
