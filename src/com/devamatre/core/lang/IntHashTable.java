package com.devamatre.core.lang;

// IntHashtable - a Hashtable that uses ints as the keys
// This is 90% based on JavaSoft's java.util.Hashtable.
// Visit the ACME Labs Java page for up-to-date versions of this and other
// fine Java utilities: http://www.acme.com/java/

import java.util.*;

/// A Hashtable that uses ints as the keys.
// <P>
// Use just like java.util.Hashtable, except that the keys must be ints.
// This is much faster than creating a new Integer for each access.
// <P>
// <A HREF="/resources/classes/Acme/IntHashtable.java">Fetch the software.</A><BR>
// <A HREF="/resources/classes/Acme.tar.Z">Fetch the entire Acme package.</A>
// <P>
// @see java.util.Hashtable

public class IntHashTable extends Dictionary implements Cloneable {

	/** The hash table data. */
	private IntHashTableEntry table[];

	// / The total number of entries in the hash table.
	private int count;

	// / Rehashes the table when count exceeds this threshold.
	private int threshold;

	// / The load factor for the hashtable.
	private float loadFactor;

	// / Constructs a new, empty hashtable. A default capacity and load factor
	// is used. Note that the hashtable will automatically grow when it gets
	// full.
	public IntHashTable() {
		this(101, 0.75f);
	}

	// / Constructs a new, empty hashtable with the specified initial
	// capacity.
	// @param initialCapacity the initial number of buckets
	public IntHashTable(int initialCapacity) {
		this(initialCapacity, 0.75f);
	}

	// / Constructs a new, empty hashtable with the specified initial
	// capacity and the specified load factor.
	// @param initialCapacity the initial number of buckets
	// @param loadFactor a number between 0.0 and 1.0, it defines
	// the threshold for rehashing the hashtable into
	// a bigger one.
	// @exception IllegalArgumentException If the initial capacity
	// is less than or equal to zero.
	// @exception IllegalArgumentException If the load factor is
	// less than or equal to zero.
	public IntHashTable(int initialCapacity, float loadFactor) {
		if (initialCapacity <= 0 || loadFactor <= 0.0) {
			throw new IllegalArgumentException();
		}
		this.loadFactor = loadFactor;
		table = new IntHashTableEntry[initialCapacity];
		threshold = (int) (initialCapacity * loadFactor);
	}

	// / Returns the number of elements contained in the hashtable.
	public int size() {
		return count;
	}

	// / Returns true if the hashtable contains no elements.
	public boolean isEmpty() {
		return count == 0;
	}

	// / Returns an enumeration of the hashtable's keys.
	// @see IntHashtable#elements
	public synchronized Enumeration<?> keys() {
		return new IntHashTableEnumerator<Integer>(table, true);
	}

	// / Returns an enumeration of the elements. Use the Enumeration methods
	// on the returned object to fetch the elements sequentially.
	// @see IntHashtable#keys
	public synchronized Enumeration<?> elements() {
		return new IntHashTableEnumerator<Integer>(table, false);
	}

	// / Returns true if the specified object is an element of the hashtable.
	// This operation is more expensive than the containsKey() method.
	// @param value the value that we are looking for
	// @exception NullPointerException If the value being searched
	// for is equal to null.
	// @see IntHashtable#containsKey
	public synchronized boolean contains(Object value) {
		if (value == null)
			throw new NullPointerException();
		IntHashTableEntry tab[] = table;
		for (int i = tab.length; i-- > 0;) {
			for (IntHashTableEntry e = tab[i]; e != null; e = e.getNext()) {
				if (e.getValue().equals(value))
					return true;
			}
		}
		return false;
	}

	// / Returns true if the collection contains an element for the key.
	// @param key the key that we are looking for
	// @see IntHashtable#contains
	public synchronized boolean containsKey(int key) {
		IntHashTableEntry tab[] = table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (IntHashTableEntry e = tab[index]; e != null; e = e.getNext()) {
			if (e.getHash() == hash && e.getKey() == key)
				return true;
		}
		return false;
	}

	// / Gets the object associated with the specified key in the
	// hashtable.
	// @param key the specified key
	// @returns the element for the key or null if the key
	// is not defined in the hash table.
	// @see IntHashtable#put
	public synchronized Object get(int key) {
		IntHashTableEntry tab[] = table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (IntHashTableEntry e = tab[index]; e != null; e = e.getNext()) {
			if (e.getHash() == hash && e.getKey() == key)
				return e.getValue();
		}
		return null;
	}

	// / A get method that takes an Object, for compatibility with
	// java.util.Dictionary. The Object must be an Integer.
	public Object get(Object okey) {
		if (!(okey instanceof Integer))
			throw new InternalError("key is not an Integer");
		Integer ikey = (Integer) okey;
		int key = ikey.intValue();
		return get(key);
	}

	// / Rehashes the content of the table into a bigger table.
	// This method is called automatically when the hashtable's
	// size exceeds the threshold.
	protected void rehash() {
		int oldCapacity = table.length;
		IntHashTableEntry oldTable[] = table;

		int newCapacity = oldCapacity * 2 + 1;
		IntHashTableEntry newTable[] = new IntHashTableEntry[newCapacity];

		threshold = (int) (newCapacity * loadFactor);
		table = newTable;

		for (int i = oldCapacity; i-- > 0;) {
			for (IntHashTableEntry old = oldTable[i]; old != null;) {
				IntHashTableEntry e = old;
				old = old.getNext();

				int index = (e.getHash() & 0x7FFFFFFF) % newCapacity;
				e.setNext(newTable[index]);
				newTable[index] = e;
			}
		}
	}

	// / Puts the specified element into the hashtable, using the specified
	// key. The element may be retrieved by doing a get() with the same key.
	// The key and the element cannot be null.
	// @param key the specified key in the hashtable
	// @param value the specified element
	// @exception NullPointerException If the value of the element
	// is equal to null.
	// @see IntHashtable#get
	// @return the old value of the key, or null if it did not have one.
	public synchronized Object put(int key, Object value) {
		// Make sure the value is not null.
		if (value == null)
			throw new NullPointerException();

		// Makes sure the key is not already in the hashtable.
		IntHashTableEntry hashTable[] = table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % hashTable.length;
		for (IntHashTableEntry e = hashTable[index]; e != null; e = e.getNext()) {
			if (e.getHash() == hash && e.getKey() == key) {
				Object old = e.getValue();
				e.setValue(value);
				return old;
			}
		}

		if (count >= threshold) {
			// Rehash the table if the threshold is exceeded.
			rehash();
			return put(key, value);
		}

		// Creates the new entry.
		IntHashTableEntry intHashTableEntry = new IntHashTableEntry(hash, key,
				value, hashTable[index]);
		hashTable[index] = intHashTableEntry;
		++count;
		return null;
	}

	// / A put method that takes an Object, for compatibility with
	// java.util.Dictionary. The Object must be an Integer.
	public Object put(Object okey, Object value) {
		if (!(okey instanceof Integer))
			throw new InternalError("key is not an Integer");
		Integer ikey = (Integer) okey;
		int key = ikey.intValue();
		return put(key, value);
	}

	// / Removes the element corresponding to the key. Does nothing if the
	// key is not present.
	// @param key the key that needs to be removed
	// @return the value of key, or null if the key was not found.
	public synchronized Object remove(int key) {
		IntHashTableEntry hashTable[] = table;
		int hash = key;
		int index = (hash & 0x7FFFFFFF) % hashTable.length;
		for (IntHashTableEntry entry = hashTable[index], prev = null; entry != null; prev = entry, entry = entry
				.getNext()) {
			if (entry.getHash() == hash && entry.getKey() == key) {
				if (prev != null)
					prev.setNext(entry.getNext());
				else
					hashTable[index] = entry.getNext();
				--count;
				return entry.getValue();
			}
		}
		return null;
	}

	// / A remove method that takes an Object, for compatibility with
	// java.util.Dictionary. The Object must be an Integer.
	public Object remove(Object okey) {
		if (!(okey instanceof Integer))
			throw new InternalError("key is not an Integer");
		Integer ikey = (Integer) okey;
		int key = ikey.intValue();
		return remove(key);
	}

	// / Clears the hash table so that it has no more elements in it.
	public synchronized void clear() {
		IntHashTableEntry tab[] = table;
		for (int index = tab.length; --index >= 0;)
			tab[index] = null;
		count = 0;
	}

	// / Creates a clone of the hashtable. A shallow copy is made,
	// the keys and elements themselves are NOT cloned. This is a
	// relatively expensive operation.
	public synchronized Object clone() {
		try {
			IntHashTable t = (IntHashTable) super.clone();
			t.table = new IntHashTableEntry[table.length];
			for (int i = table.length; i-- > 0;)
				t.table[i] = (table[i] != null) ? (IntHashTableEntry) table[i]
						.clone() : null;
			return t;
		} catch (CloneNotSupportedException e) {
			// This shouldn't happen, since we are Cloneable.
			throw new InternalError();
		}
	}

	// / Converts to a rather lengthy String.
	public synchronized String toString() {
		int max = size() - 1;
		StringBuffer buf = new StringBuffer();
		Enumeration<?> k = keys();
		Enumeration<?> e = elements();
		buf.append("{");

		for (int i = 0; i <= max; ++i) {
			String s1 = k.nextElement().toString();
			String s2 = e.nextElement().toString();
			buf.append(s1 + "=" + s2);
			if (i < max)
				buf.append(", ");
		}
		buf.append("}");
		return buf.toString();
	}
}

/**
 * IntHashTableEntry.java
 * 
 * The <code>IntHashTableEntry</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @date Aug 10, 2009 11:19:33 PM
 */
final class IntHashTableEntry {

	private int hash;
	private int key;
	private Object value;
	private IntHashTableEntry next;

	/**
	 *
	 */
	public IntHashTableEntry() {
		this(0, 0, null, null);
	}

	/**
	 * 
	 * @param hash
	 * @param key
	 * @param value
	 * @param next
	 */
	public IntHashTableEntry(int hash, int key, Object value,
			IntHashTableEntry next) {
		setHash(hash);
		setKey(key);
		setValue(value);
		setNext(next);
	}

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public IntHashTableEntry getNext() {
		return next;
	}

	public void setNext(IntHashTableEntry next) {
		this.next = next;
	}

	/**
	 * @Overridden
	 */
	protected Object clone() {
		IntHashTableEntry entry = new IntHashTableEntry();
		entry.setHash(hash);
		entry.setKey(key);
		entry.setValue(value);
		entry.setNext((next != null) ? (IntHashTableEntry) next.clone() : null);
		return entry;
	}
}

/**
 * IntHashTableEnumerator.java
 * 
 * The <code>IntHashTableEnumerator</code> TODO Define Purpose here
 * 
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @date Aug 10, 2009 11:20:34 PM
 */
class IntHashTableEnumerator<E> implements Enumeration<E> {

	private boolean keys;
	private int index;
	private IntHashTableEntry table[];
	private IntHashTableEntry entry;

	/**
	 * 
	 * @param table
	 * @param keys
	 */
	IntHashTableEnumerator(IntHashTableEntry table[], boolean keys) {
		this.table = table;
		this.keys = keys;
		this.index = table.length;
	}

	/**
	 * Checks whether more elements exists in table or not. Returns true if
	 * element exists otherwise false.
	 */
	public boolean hasMoreElements() {
		if (entry != null) {
			return true;
		}
		while (index-- > 0) {
			if ((entry = table[index]) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	public E nextElement() {
		if (entry == null) {
			while ((index-- > 0) && ((entry = table[index]) == null))
				;
		}
		if (entry != null) {
			IntHashTableEntry e = entry;
			entry = e.getNext();
			return (E) (keys ? new Integer(e.getKey()) : e.getValue());
		}
		throw new NoSuchElementException("IntHashtableEnumerator");
	}
}