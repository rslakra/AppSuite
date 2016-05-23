package com.apparatus;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import com.apparatus.utils.ObjectHelper;
import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;

/**
 * 
 * 
 * A HashTable that is just like <code>java.util.Hashtable</code> but is much
 * faster than that.
 * 
 * @see java.util.Hashtable
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Apr 15, 2016 2:35:02 PM
 * @version 1.0.0
 * @since 1.0.0
 */
public class HashTable extends Dictionary<Object, Object> implements Cloneable, Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** logger - intentionally debugEnabled is set to false for this logger. */
	private static Logger logger = LogManager.getLogger(HashTable.class, false);
	
	/** default initial capacity. */
	public static final int INITIAL_CAPACITY = 161;
	
	/**
	 * The maximum capacity, used if a higher value is implicitly specified by
	 * either of the constructors with arguments. MUST be a power of two <=
	 * 1<<30.
	 */
	public static final int MAXIMUM_CAPACITY = 1 << 30;
	
	/** default load factor. */
	public static final float LOAD_FACTOR = 0.75F;
	
	/** The hash table data. */
	private HashTableEntry[] hashTableEntries;
	
	/** The total number of entries in the hash table. */
	private int count;
	
	/** Rehashes the table when count exceeds this threshold. */
	private int threshold;
	
	/** The load factor for the HashTable. */
	private float loadFactor;
	
	/***
	 * Constructs a new, empty HashTable with the specified initial capacity and
	 * the specified load factor.
	 * 
	 * @param initialCapacity
	 *            the initial number of buckets
	 * @param loadFactor
	 *            a number between 0.0 and 1.0, it defines the threshold for
	 *            rehashing the HashTable into a bigger one.
	 * @exception IllegalArgumentException
	 *                If the initial capacity is less than or equal to 0.
	 * @exception IllegalArgumentException
	 *                If the load factor is less than or equal to 0.0.
	 */
	public HashTable(int initialCapacity, float loadFactor) {
		logger.debug("+HashTable(" + initialCapacity + ", " + loadFactor + ")");
		if(initialCapacity <= 0) {
			throw new IllegalArgumentException("Invalid initial capacity!, initialCapacity:" + initialCapacity);
		}
		
		if(loadFactor <= 0.0 || loadFactor > 1.0) {
			throw new IllegalArgumentException("Invalid load factor!, loadFactor:" + loadFactor);
		}
		
		this.loadFactor = loadFactor;
		hashTableEntries = new HashTableEntry[initialCapacity];
		threshold = (int) (initialCapacity * loadFactor);
		logger.debug("-HashTable()");
	}
	
	/**
	 * 
	 * @param initialCapacity
	 *            - The initial number of buckets
	 */
	public HashTable(int initialCapacity) {
		this(initialCapacity, LOAD_FACTOR);
	}
	
	/**
	 * Constructs a new and empty HashTable with a default capacity and load
	 * factor.
	 * 
	 * Note that the HashTable will automatically grow when it gets full.
	 */
	public HashTable() {
		this(INITIAL_CAPACITY, LOAD_FACTOR);
	}
	
	/**
	 * Returns the number of elements contained in the HashTable.
	 * 
	 * @see java.util.Dictionary#size()
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Returns true if the HashTable contains no elements.
	 * 
	 * @see java.util.Dictionary#isEmpty()
	 */
	public boolean isEmpty() {
		return (size() == 0 && (hashTableEntries == null || hashTableEntries.length == 0));
	}
	
	/**
	 * Returns an enumeration of the HashTable's keys.
	 * 
	 * @see java.util.Dictionary#keys()
	 */
	public synchronized Enumeration<Object> keys() {
		return new HashTableEnumerator<Object>(hashTableEntries, true);
	}
	
	/**
	 * Returns an enumeration of the elements. Use the Enumeration methods on
	 * the returned object to fetch the elements sequentially.
	 * 
	 * @see java.util.Dictionary#elements()
	 */
	public synchronized Enumeration<Object> elements() {
		return new HashTableEnumerator<Object>(hashTableEntries, false);
	}
	
	/**
	 * Returns true if the specified object is an element of the HashTable. This
	 * operation is more expensive than the containsKey() method.
	 * 
	 * @param value
	 *            the value that we are looking for
	 * @return
	 * @exception NullPointerException
	 *                If the value being searched for is equal to null.
	 */
	public synchronized boolean contains(Object value) {
		logger.debug("+contains(" + value + ")");
		
		if(value == null) {
			throw new NullPointerException();
		}
		
		HashTableEntry[] tableEntries = this.hashTableEntries;
		int index = hashTableEntries.length;
		while(index-- > 0) {
			for(HashTableEntry nextEntry = tableEntries[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
				if(nextEntry.getValue().equals(value)) {
					logger.debug("-contains(), true");
					return true;
				}
			}
		}
		
		logger.debug("-contains(), false");
		return false;
	}
	
	/**
	 * Returns true if the collection contains an element for the key.
	 * 
	 * @param key
	 *            the key that we are looking for
	 * @return
	 */
	public synchronized boolean containsKey(Object key) {
		logger.debug("+containsKey(" + key + ")");
		
		HashTableEntry tableEntry[] = hashTableEntries;
		int hash = ObjectHelper.hashCode(key);
		int index = (hash & 0x7FFFFFFF) % tableEntry.length;
		for(HashTableEntry nextEntry = tableEntry[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
			if(nextEntry.getHash() == hash && nextEntry.getKey() == key) {
				logger.debug("-containsKey(), true");
				return true;
			}
		}
		
		logger.debug("-containsKey(), false");
		return false;
	}
	
	/**
	 * Gets the object associated with the specified key in the HashTable.
	 * 
	 * @see java.util.Dictionary#get(java.lang.Object)
	 */
	public synchronized Object get(Object key) {
		logger.debug("+get(" + key + ")");
		Object value = null;
		
		if(ObjectHelper.isNotNull(key)) {
			HashTableEntry tableEntry[] = hashTableEntries;
			int hash = ObjectHelper.hashCode(key);
			int index = (hash & 0x7FFFFFFF) % tableEntry.length;
			for(HashTableEntry nextEntry = tableEntry[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
				if(nextEntry.getHash() == hash && nextEntry.getKey() == key) {
					logger.debug("+contains(" + value + ")");
					value = nextEntry.getValue();
					break;
				}
			}
		}
		
		logger.debug("get(), value:" + value);
		return value;
	}
	
	/**
	 * Rehashes the content of the table into a bigger table. This method is
	 * called automatically when the hashtable's size exceeds the threshold.
	 */
	protected void reHash() {
		HashTableEntry oldTable[] = hashTableEntries;
		int oldCapacity = hashTableEntries.length;
		
		int newCapacity = oldCapacity * 2 + 1;
		HashTableEntry newTable[] = new HashTableEntry[newCapacity];
		threshold = (int) (newCapacity * loadFactor);
		hashTableEntries = newTable;
		
		int index = oldCapacity;
		while(index-- > 0) {
			HashTableEntry oldEntry = oldTable[index];
			while(oldEntry != null) {
				HashTableEntry nextEntry = oldEntry;
				oldEntry = oldEntry.getNextEntry();
				
				// regenerate next hash
				int newIndex = (nextEntry.getHash() & 0x7FFFFFFF) % newCapacity;
				nextEntry.setNextEntry(newTable[newIndex]);
				newTable[index] = nextEntry;
			}
		}
	}
	
	/**
	 * 
	 * Puts the specified element into the HashTable, using the specified key.
	 * The element may be retrieved by doing a get() with the same key. The key
	 * and the element cannot be null.
	 * 
	 * @param key
	 *            the specified key in the HashTable
	 * @param value
	 *            the specified element
	 * @exception NullPointerException
	 *                If the value of the element is equal to null.
	 * 
	 * @see java.util.Dictionary#put(java.lang.Object, java.lang.Object)
	 */
	public synchronized Object put(Object key, Object value) {
		// Make sure the value is not null.
		if(ObjectHelper.isNull(key) || ObjectHelper.isNull(value)) {
			throw new NullPointerException();
		}
		
		// Makes sure the key is not already in the HashTable.
		HashTableEntry newTableEntry[] = hashTableEntries;
		int hash = ObjectHelper.hashCode(key);
		int index = (hash & 0x7FFFFFFF) % newTableEntry.length;
		for(HashTableEntry nextEntry = newTableEntry[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
			if(nextEntry.getHash() == hash && nextEntry.getKey() == key) {
				Object oldValue = nextEntry.getValue();
				nextEntry.setValue(value);
				return oldValue;
			}
		}
		
		// Rehash the table, if required.
		if(count >= threshold) {
			reHash();
			return put(key, value);
		}
		
		// Creates the new entry.
		newTableEntry[index] = new HashTableEntry(key, value, newTableEntry[index]);
		++count;
		return null;
	}
	
	/**
	 * Removes the element corresponding to the key. Does nothing if the key is
	 * not present.
	 * 
	 * @param key
	 * @see java.util.Dictionary#remove(java.lang.Object)
	 */
	public synchronized Object remove(Object key) {
		HashTableEntry[] tableEntry = hashTableEntries;
		int hash = ObjectHelper.hashCode(key);
		int index = (hash & 0x7FFFFFFF) % tableEntry.length;
		HashTableEntry prevEntry = null;
		HashTableEntry nextEntry = tableEntry[index];
		while(nextEntry != null) {
			if(nextEntry.getHash() == hash && nextEntry.getKey() == key) {
				if(prevEntry != null) {
					prevEntry.setNextEntry(nextEntry.getNextEntry());
				} else {
					tableEntry[index] = nextEntry.getNextEntry();
				}
				--count;
				return nextEntry.getValue();
			}
			
			prevEntry = nextEntry;
			nextEntry = nextEntry.getNextEntry();
		}
		
		return null;
	}
	
	/**
	 * Clears the hash table so that it has no more elements in it.
	 */
	public synchronized void clear() {
		HashTableEntry[] tableEntries = this.hashTableEntries;
		for(int index = tableEntries.length; --index >= 0;) {
			tableEntries[index] = null;
		}
		count = 0;
	}
	
	/**
	 * Clone the specified table entry.
	 * 
	 * @param tableEntry
	 * @return
	 * @throws CloneNotSupportedException
	 */
	private HashTableEntry cloneHashTableEntry(HashTableEntry tableEntry) throws CloneNotSupportedException {
		return (tableEntry == null ? null : (HashTableEntry) tableEntry.clone());
	}
	
	/**
	 * Creates a clone of the HashTable. A shallow copy is made, the keys and
	 * elements themselves are NOT cloned. This is a relatively expensive
	 * operation.
	 * 
	 * @see java.lang.Object#clone()
	 */
	public synchronized Object clone() {
		try {
			HashTable hashTable = (HashTable) super.clone();
			hashTable.hashTableEntries = new HashTableEntry[hashTableEntries.length];
			int index = hashTableEntries.length;
			while(index-- > 0) {
				hashTable.hashTableEntries[index] = cloneHashTableEntry(hashTableEntries[index]);
			}
			
			return hashTable;
		} catch(CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
	
	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public synchronized String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		Enumeration<Object> keys = keys();
		Enumeration<Object> elements = elements();
		stringBuilder.append("{");
		
		for(int i = 0; i < size(); ++i) {
			stringBuilder.append(keys.nextElement()).append("=").append(elements.nextElement().toString());
			if(i < (size() - 1)) {
				stringBuilder.append(", ");
			}
		}
		stringBuilder.append("}");
		
		return stringBuilder.toString();
	}
}

/**
 * IntHashTableEntry.java
 * 
 * The <code>IntHashTableEntry</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @date Aug 10, 2009 11:19:33 PM
 */
final class HashTableEntry {
	
	private int hash;
	private Object key;
	private Object value;
	private HashTableEntry nextEntry;
	
	/**
	 *
	 */
	public HashTableEntry() {
		this(null, null, null);
	}
	
	/**
	 * 
	 * @param hash
	 * @param key
	 * @param value
	 * @param nextEntry
	 */
	public HashTableEntry(Object key, Object value, HashTableEntry nextEntry) {
		this.key = key;
		this.hash = ObjectHelper.hashCode(key);
		this.value = value;
		this.nextEntry = (nextEntry == null ? null : (HashTableEntry) nextEntry.clone());
	}
	
	/**
	 * Returns the hash.
	 * 
	 * @return
	 */
	public int getHash() {
		return hash;
	}
	
	/**
	 * The hash to be set.
	 * 
	 * @param hash
	 */
	public void setHash(int hash) {
		this.hash = hash;
	}
	
	/**
	 * Returns the key.
	 * 
	 * @return
	 */
	public Object getKey() {
		return key;
	}
	
	/**
	 * The key to be set.
	 * 
	 * @param key
	 */
	public void setKey(Object key) {
		this.key = key;
	}
	
	/**
	 * Returns the value.
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * The value to be set.
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	/**
	 * Returns the nextEntry.
	 * 
	 * @return
	 */
	public HashTableEntry getNextEntry() {
		return nextEntry;
	}
	
	/**
	 * The nextEntry to be set.
	 * 
	 * @param nextEntry
	 */
	public void setNextEntry(HashTableEntry nextEntry) {
		this.nextEntry = nextEntry;
	}
	
	/**
	 * Returns true if the object is same otherwise false.
	 * 
	 * @param object
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public final boolean equals(Object object) {
		if(object instanceof HashTableEntry) {
			HashTableEntry otherEntry = (HashTableEntry) object;
			if(getKey() == otherEntry.getKey() || (getKey() != null && getKey().equals(otherEntry.getKey()))) {
				if(getValue() == otherEntry.getValue() || (getValue() != null && getValue().equals(otherEntry.getValue()))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public final int hashCode() {
		return (ObjectHelper.hashCode(getKey()) ^ ObjectHelper.hashCode(getValue()));
	}
	
	/**
	 * Returns the clone of this object.
	 * 
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() {
		return new HashTableEntry(key, value, nextEntry);
	}
}

/**
 * The <code>HashTableEnumerator</code>
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @date Aug 10, 2009 11:20:34 PM
 */
final class HashTableEnumerator<E> implements Enumeration<E> {
	
	private int index;
	private HashTableEntry[] hashTableEntries;
	private boolean enumKeys;
	private HashTableEntry nextEntry;
	
	/**
	 * 
	 * @param hashTableEntries
	 * @param enumKeys
	 */
	HashTableEnumerator(HashTableEntry[] hashTableEntries, boolean enumKeys) {
		this.hashTableEntries = hashTableEntries;
		this.index = hashTableEntries.length;
		this.enumKeys = enumKeys;
	}
	
	/**
	 * 
	 * @return
	 */
	protected boolean isEnumKeys() {
		return enumKeys;
	}
	
	/**
	 * Checks whether more elements exists in table or not. Returns true if
	 * element exists otherwise false.
	 * 
	 * @see java.util.Enumeration#hasMoreElements()
	 */
	public boolean hasMoreElements() {
		boolean result = false;
		if(ObjectHelper.isNull(nextEntry)) {
			while(index-- > 0) {
				if((nextEntry = hashTableEntries[index]) != null) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @see java.util.Enumeration#nextElement()
	 */
	@SuppressWarnings("unchecked")
	public E nextElement() {
		if(nextEntry == null) {
			while((index-- > 0) && ((nextEntry = hashTableEntries[index]) == null))
				;
		}
		
		if(nextEntry != null) {
			HashTableEntry tableEntry = nextEntry;
			nextEntry = tableEntry.getNextEntry();
			return (E) (isEnumKeys() ? tableEntry.getKey() : tableEntry.getValue());
		}
		
		throw new NoSuchElementException("HashTableEnumerator");
	}
}