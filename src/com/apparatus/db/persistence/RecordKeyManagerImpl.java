package com.apparatus.db.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;

/**
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Jul 10, 2015 3:44:49 PM
 */
@SuppressWarnings("rawtypes")
public class RecordKeyManagerImpl extends AbstractRecordKeyComparator {
	
	private static final RecordKeyManagerImpl INSTANCE = new RecordKeyManagerImpl();
	
	/**
	 * 
	 * @return
	 */
	public static RecordKeyManagerImpl getInstance() {
		return INSTANCE;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private RecordKeyManagerImpl() {
		super(null);
	}
	
	/**
	 * 
	 * @param comparator
	 */
	@SuppressWarnings("unchecked")
	protected RecordKeyManagerImpl(final Comparator comparator) {
		super(comparator);
	}
	
	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.db.persistence.RecordKeyManager#unmarshall(byte[])
	 */
	@Override
	public Comparable<?> unmarshall(final byte[] keyData) throws HeapException {
		try {
			final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(keyData);
			final ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			final Object result = objectInputStream.readObject();
			objectInputStream.close();
			return (Comparable<?>) result;
		} catch(Exception exception) {
			throw new HeapException("unmarshalling key", exception);
		}
	}
	
	/*
	 * 
	 */
	@Override
	public RecordIdentifier createKey(final Object key) throws HeapException {
		try {
			final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			final ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
			outputStream.writeObject(key);
			outputStream.close();
			final byte[] keyData = byteArrayOutputStream.toByteArray();
			final RecordKeyImpl recordKey = new RecordKeyImpl(this, keyData);
			recordKey.setKey(key);
			return new RecordIdentifier(recordKey);
		} catch(Exception exception) {
			throw new HeapException("creating key", exception);
		}
	}
}
