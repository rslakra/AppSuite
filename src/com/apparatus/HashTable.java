package com.apparatus;

// IntHashtable - a Hashtable that uses ints as the keys
// This is 90% based on JavaSoft's java.util.Hashtable.
// Visit the ACME Labs Java page for up-to-date versions of this and other
// fine Java utilities: http://www.acme.com/java/

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.NoSuchElementException;

// / A Hashtable that uses ints as the keys.
// <P>
// Use just like java.util.Hashtable, except that the keys must be ints.
// This is much faster than creating a new Integer for each access.
// <P>
// <A HREF="/resources/classes/Acme/IntHashtable.java">Fetch the
// software.</A><BR>
// <A HREF="/resources/classes/Acme.tar.Z">Fetch the entire Acme package.</A>
// <P>
// @see java.util.Hashtable

public class HashTable extends Dictionary<Object, Object> implements Cloneable {
	
	/** default initial capacity. */
	private static final int INITIAL_CAPACITY = 161;
	
	/** default load factor. */
	private static final float LOAD_FACTOR = 0.75F;
	
	/** The hash table data. */
	private HashTableEntry table[];
	
	/** The total number of entries in the hash table. */
	private int count;
	
	/** Rehashes the table when count exceeds this threshold. */
	private int threshold;
	
	/** The load factor for the HashTable. */
	private float loadFactor;
	
	/***
	 * Constructs a new, empty HashTable with the specified initial
	 * capacity and the specified load factor.
	 * 
	 * @param initialCapacity the initial number of buckets
	 * @param loadFactor a number between 0.0 and 1.0, it defines
	 *            the threshold for rehashing the HashTable into
	 *            a bigger one.
	 * @exception IllegalArgumentException If the initial capacity
	 *                is less than or equal to 0.
	 * @exception IllegalArgumentException If the load factor is
	 *                less than or equal to 0.0.
	 */
	public HashTable(int initialCapacity, float loadFactor) {
		if(initialCapacity <= 0) {
			throw new IllegalArgumentException("Invalid initial capacity!, initialCapacity:" + initialCapacity);
		}
		
		if(loadFactor <= 0.0 || loadFactor > 1.0) {
			throw new IllegalArgumentException("Invalid load factor!, loadFactor:" + loadFactor);
		}
		
		this.loadFactor = loadFactor;
		table = new HashTableEntry[initialCapacity];
		threshold = (int) (initialCapacity * loadFactor);
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
	 * 
	 * @param initialCapacity - The initial number of buckets
	 */
	public HashTable(int initialCapacity) {
		this(initialCapacity, LOAD_FACTOR);
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
		return count == 0;
	}
	
	/**
	 * Returns an enumeration of the hashtable's keys.
	 * 
	 * @see java.util.Dictionary#keys()
	 */
	public synchronized Enumeration<Object> keys() {
		return new HashTableEnumerator<Object>(table, true);
	}
	
	/**
	 * Returns an enumeration of the elements. Use the Enumeration methods on
	 * the returned object to fetch the elements sequentially.
	 * 
	 * @see java.util.Dictionary#elements()
	 */
	public synchronized Enumeration<Object> elements() {
		return new HashTableEnumerator<Object>(table, false);
	}
	
	/**
	 * Returns true if the specified object is an element of the hashtable.
	 * This operation is more expensive than the containsKey() method.
	 * 
	 * @param value the value that we are looking for
	 * @return
	 * @exception NullPointerException If the value being searched
	 *                for is equal to null.
	 */
	public synchronized boolean contains(Object value) {
		if(value == null) {
			throw new NullPointerException();
		}
		
		HashTableEntry tableEntry[] = table;
		int index = tableEntry.length;
		while(index-- > 0) {
			for(HashTableEntry nextEntry = tableEntry[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
				if(nextEntry.getValue().equals(value)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private int keyHashCode(Object key) {
		return (key == null ? 1 : key.hashCode());
	}
	
	/**
	 * Returns true if the collection contains an element for the key.
	 * 
	 * @param key the key that we are looking for
	 * @return
	 */
	public synchronized boolean containsKey(Object key) {
		HashTableEntry tableEntry[] = table;
		int hash = keyHashCode(key);
		int index = (hash & 0x7FFFFFFF) % tableEntry.length;
		for(HashTableEntry nextEntry = tableEntry[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
			if(nextEntry.getHash() == hash && nextEntry.getKey() == key) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the object associated with the specified key in the
	 * HashTable.
	 * 
	 * @see java.util.Dictionary#get(java.lang.Object)
	 */
	public Object get(Object key) {
		HashTableEntry tableEntry[] = table;
		int hash = keyHashCode(key);
		int index = (hash & 0x7FFFFFFF) % tableEntry.length;
		for(HashTableEntry nextEntry = tableEntry[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
			if(nextEntry.getHash() == hash && nextEntry.getKey() == key) {
				return nextEntry.getValue();
			}
		}
		
		return null;
	}
	
	/**
	 * Rehashes the content of the table into a bigger table.
	 * This method is called automatically when the hashtable's
	 * size exceeds the threshold.
	 */
	protected void reHash() {
		HashTableEntry oldTable[] = table;
		int oldCapacity = table.length;
		
		int newCapacity = oldCapacity * 2 + 1;
		HashTableEntry newTable[] = new HashTableEntry[newCapacity];
		threshold = (int) (newCapacity * loadFactor);
		table = newTable;
		
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
	 * Puts the specified element into the HashTable, using the specified
	 * key. The element may be retrieved by doing a get() with the same key.
	 * The key and the element cannot be null.
	 * 
	 * @param key the specified key in the HashTable
	 * @param value the specified element
	 * @exception NullPointerException If the value of the element
	 *                is equal to null.
	 * 
	 * @see java.util.Dictionary#put(java.lang.Object, java.lang.Object)
	 */
	public synchronized Object put(Object key, Object newValue) {
		// Make sure the value is not null.
		if(newValue == null) {
			throw new NullPointerException();
		}
		
		// Makes sure the key is not already in the HashTable.
		HashTableEntry newTableEntry[] = table;
		int hash = keyHashCode(key);
		int index = (hash & 0x7FFFFFFF) % newTableEntry.length;
		for(HashTableEntry nextEntry = newTableEntry[index]; nextEntry != null; nextEntry = nextEntry.getNextEntry()) {
			if(nextEntry.getHash() == hash && nextEntry.getKey() == key) {
				Object oldValue = nextEntry.getValue();
				nextEntry.setValue(newValue);
				return oldValue;
			}
		}
		
		// Rehash the table, if required.
		if(count >= threshold) {
			reHash();
			return put(key, newValue);
		}
		
		// Creates the new entry.
		newTableEntry[index] = new HashTableEntry(hash, key, newValue, newTableEntry[index]);
		;
		++count;
		return null;
	}
	
	/**
	 * Removes the element corresponding to the key. Does nothing if the
	 * key is not present.
	 * 
	 * @param key
	 * @see java.util.Dictionary#remove(java.lang.Object)
	 */
	public synchronized Object remove(Object key) {
		HashTableEntry tableEntry[] = table;
		int hash = keyHashCode(key);
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
		HashTableEntry tableEntry[] = table;
		for(int index = tableEntry.length; --index >= 0;) {
			tableEntry[index] = null;
		}
		count = 0;
	}
	
	/**
	 * Creates a clone of the HashTable. A shallow copy is made,
	 * the keys and elements themselves are NOT cloned. This is a
	 * relatively expensive operation.
	 * 
	 * @see java.lang.Object#clone()
	 */
	public synchronized Object clone() {
		try {
			HashTable hashTable = (HashTable) super.clone();
			hashTable.table = new HashTableEntry[table.length];
			int index = table.length;
			while(index-- > 0) {
				hashTable.table[index] = (table[index] == null ? null : (HashTableEntry) table[index].clone());
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
		this(0, null, null, null);
	}
	
	/**
	 * 
	 * @param hash
	 * @param key
	 * @param value
	 * @param nextEntry
	 */
	public HashTableEntry(int hash, Object key, Object value, HashTableEntry nextEntry) {
		this.hash = hash;
		this.key = key;
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
	 * Returns the clone of this object.
	 * 
	 * @see java.lang.Object#clone()
	 */
	protected Object clone() {
		return new HashTableEntry(hash, key, value, nextEntry);
	}
}

/**
 * IntHashTableEnumerator.java
 * 
 * The <code>IntHashTableEnumerator</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @date Aug 10, 2009 11:20:34 PM
 */
final class HashTableEnumerator<E> implements Enumeration<E> {
	
	private boolean keys;
	private int index;
	private HashTableEntry table[];
	private HashTableEntry entry;
	
	/**
	 * 
	 * @param table
	 * @param keys
	 */
	HashTableEnumerator(HashTableEntry table[], boolean keys) {
		this.table = table;
		this.keys = keys;
		this.index = table.length;
	}
	
	/**
	 * Checks whether more elements exists in table or not. Returns true if
	 * element exists otherwise false.
	 */
	public boolean hasMoreElements() {
		if(entry != null) {
			return true;
		}
		
		while(index-- > 0) {
			if((entry = table[index]) != null) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public E nextElement() {
		if(entry == null) {
			while((index-- > 0) && ((entry = table[index]) == null))
				;
		}
		
		if(entry != null) {
			HashTableEntry tableEntry = entry;
			entry = tableEntry.getNextEntry();
			return (E) (keys ? tableEntry.getKey() : tableEntry.getValue());
		}
		
		throw new NoSuchElementException("HashTableEnumerator");
	}
}