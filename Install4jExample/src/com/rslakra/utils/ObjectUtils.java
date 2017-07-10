package com.rslakra.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Rohtash Singh (rsingh@boardvantage.com)
 * @version 1.0.0
 * @since Apr 29, 2015 3:12:38 PM
 */
public class ObjectUtils {
	
	/**
	 * Returns true if the object is null otherwise false.
	 * 
	 * @param object
	 * @return
	 */
	public static <T> boolean isNull(T object) {
		return (object == null);
	}
	
	/**
	 * Returns true if the object is not null otherwise false.
	 * 
	 * @param object
	 * @return
	 */
	public static <T> boolean isNotNull(T object) {
		return (!isNull(object));
	}
	
	/**
	 * Returns true if an array is null otherwise false.
	 * 
	 * @param objects
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(T[] objects) {
		return (isNull(objects) || objects.length == 0);
	}
	
	/**
	 * Returns true if the StringBuilder is null or empty otherwise false.
	 * 
	 * @param stringBuilder
	 * @return
	 */
	public static boolean isNullOrEmpty(StringBuilder stringBuilder) {
		return (isNull(stringBuilder) || stringBuilder.length() == 0);
	}
	
	/**
	 * Returns true if the collection is either null or empty otherwise false.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		return (isNull(collection) || collection.isEmpty());
	}
	
	/**
	 * Returns true if the map is either null or empty otherwise false.
	 * 
	 * @param map
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(Map<?, ?> map) {
		return (isNull(map) || map.isEmpty());
	}
	
	/**
	 * Returns the first key from the map, if its not null otherwise null.
	 * 
	 * @param map
	 * @return
	 */
	public static Object getFirstKey(Map<?, ?> map) {
		return map.keySet().toArray()[0];
	}
	
	/**
	 * Returns the value from of the selected index, if the map is null
	 * otherwise null.
	 * 
	 * @param map
	 * @param keyIndex
	 * @return
	 */
	public static Object getKeyValue(Map<?, ?> map, int keyIndex) {
		return map.keySet().toArray()[keyIndex];
	}
	
	/**
	 * Returns true if the bytes array is either null or empty otherwise
	 * false.
	 * 
	 * @param bytes
	 * @return
	 */
	public static boolean isNullOrEmpty(byte... array) {
		return (isNull(array) || array.length == 0);
	}
	
	/**
	 * Returns the new instance of the specified class type.
	 * 
	 * @param type
	 * @param className
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<T> type, String className) throws Exception {
		Class<?> klass = Class.forName(className);
		return (T) (klass == null ? null : klass.newInstance());
	}
	
	/**
	 * Converts the list into the set.
	 * 
	 * @param args
	 * @return
	 */
	public static <T> Set<T> asSet(List<T> args) {
		Set<T> set = new HashSet<T>();
		if(!isNullOrEmpty(args)) {
			set.addAll(args);
		}
		
		return set;
	}
	
	/**
	 * Returns true of the collection contains the specified value otherwise
	 * false.
	 * 
	 * @param collection
	 * @param value
	 * @return
	 */
	public static <T> boolean contains(Collection<T> collection, T value) {
		return ((!isNullOrEmpty(collection)) && collection.contains(value));
	}
	
	/**
	 * Converts the string to set after splitting the string with the specified
	 * delimiter.
	 * 
	 * @param value
	 * @param delimiter
	 * @return
	 */
	public static Set<String> asSet(String value, String delimiter) {
		Set<String> set = new HashSet<String>();
		if(value != null && delimiter != null) {
			String[] values = value.split(delimiter);
			if(values != null) {
				for(int i = 0; i < values.length; i++) {
					set.add(values[i].trim());
				}
			}
		}
		
		return set;
	}
	
	/**
	 * 
	 * @param arguments
	 * @return
	 */
	public static <T> Set<T> asSet(T... arguments) {
		Set<T> set = new HashSet<T>();
		if(arguments != null && arguments.length > 0) {
			for(T obj : arguments) {
				set.add(obj);
			}
		}
		
		return set;
	}
	
	/**
	 * Converts the <code>arguments</code> into the
	 * <code>java.util.ArrayList()</code> object.
	 * 
	 * @param arguments
	 * @return
	 */
	public static <T> List<T> asList(Collection<T> arguments) {
		return new ArrayList<T>(arguments);
	}
	
	/**
	 * Returns the <code>java.util.ArrayList()</code> object that can be used to
	 * perform <code>add()</code>, <code>remove()</code> operations on that
	 * array list. if you use the <code>java.util.Arrays#asList(T... a)</code>,
	 * you will not be able to perform those operations as the latter returns a
	 * fixed-size list backed by the specified array. If the <code>args</code>
	 * are null, it returns null.
	 * 
	 * For more details, see: <code>java.util.Arrays#asList(T... a)</code>.
	 * 
	 * @param args
	 * @return
	 * @see java.util.Arrays#asList(T... a)
	 */
	public static <T> List<T> asList(T... args) {
		List<T> list = null;
		if(args != null) {
			list = new ArrayList<T>();
			for(int i = 0; i < args.length; i++) {
				list.add(args[i]);
			}
		}
		
		return list;
	}
	
	/**
	 * * Returns an array containing all elements contained in this {@code List}
	 * . If the
	 * specified array is large enough to hold the elements, the specified array
	 * is used, otherwise an array of the same type is created. If the specified
	 * array is used and is larger than this {@code List}, the array element
	 * following
	 * the collection elements is set to null.
	 * 
	 * @param array
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> list, Class<T> classType) {
		T[] listArray = null;
		if(!isNullOrEmpty(list)) {
			listArray = (T[]) Array.newInstance(classType, list.size());
			listArray = list.toArray(listArray);
		}
		
		return listArray;
	}
	
	/**
	 * Returns the <code>java.util.ArrayList()</code> object which is of the
	 * specified type.
	 * 
	 * @param args
	 * @return
	 * @see java.util.Arrays#asList(T... a)
	 */
	public static List<Integer> asIntList(List<String> args) {
		List<Integer> list = null;
		if(args != null) {
			list = new ArrayList<Integer>();
			for(int i = 0; i < args.size(); i++) {
				list.add(Integer.parseInt(args.get(i)));
			}
		}
		
		return list;
	}
	
	/**
	 * Returns true if the <T> type is equals to any of the specified <T> types
	 * otherwise false.
	 * 
	 * @param relationshipType
	 * @param relationshipTypes
	 * @return
	 */
	public static <T> boolean equals(T type, T... types) {
		boolean result = false;
		if(type != null && types != null) {
			for(int i = 0; i < types.length; i++) {
				if(types[i].equals(type)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public static <T> List<T> getEmptyList() {
		return new ArrayList<T>();
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<? super T>> List<T> sortByKeys(List<T> list) {
		List<T> linkedList = new LinkedList<T>(list);
		Collections.sort(linkedList);
		return linkedList;
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public static <K extends Comparable<K>, V extends Comparable<V>> SortedMap<K, V> sortByNaturalOrder(Map<K, V> map) {
		return new TreeMap<K, V>(map);
	}
	
	/**
	 * Converts the normal map to sorted map.
	 * 
	 * @param map
	 * @return
	 */
	public static SortedMap<String, Object> convertToSortedMap(Map<String, ? extends Object> map) {
		return new TreeMap<String, Object>(map);
	}
	
	/**
	 * Sorts the specified map by keys in natural order.
	 * 
	 * @param map
	 * @return
	 * @throws NullPointerException - if map contains the null as key
	 */
	public static <K extends Comparable<K>, V extends Comparable<V>> Map<K, V> sortByKeys(Map<K, V> map) {
		List<K> keys = new LinkedList<K>(map.keySet());
		Collections.sort(keys);
		
		/*
		 * LinkedHashMap will keep the keys in the order they are inserted
		 * which is currently sorted on natural ordering
		 */
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for(K key : keys) {
			sortedMap.put(key, map.get(key));
		}
		
		return sortedMap;
	}
	
	/**
	 * Sorts the specified map by values in natural order.
	 * 
	 * @param map
	 * @return
	 * @throws NullPointerException - if map contains the null as key
	 */
	public static <K extends Comparable<K>, V extends Comparable<V>> Map<K, V> sortByValues(Map<K, V> map) {
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		
		Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
			/**
			 * @param o1
			 * @param o2
			 * @return
			 */
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		/*
		 * LinkedHashMap will keep the keys in the order they are inserted
		 * which is currently sorted on natural ordering
		 */
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for(Map.Entry<K, V> entry : entries) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedMap;
	}
	
	/**
	 * Converts an object into string if the object is not null and instance of
	 * String otherwise empty string.
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object) {
		return ((object != null && object instanceof String) ? object.toString() : "");
	}
	
	/**
	 * Returns true if the specified object is an instance of any of the
	 * specified classTypes otherwise false.
	 * 
	 * @param object
	 * @param classTypes
	 * @return
	 */
	public static boolean isInstanceOf(Object object, Class<?>... classTypes) {
		boolean instanceOf = false;
		if(ObjectUtils.isNotNull(object) && !ObjectUtils.isNullOrEmpty(classTypes)) {
			for(int i = 0; i < classTypes.length; i++) {
				if(classTypes[i].isInstance(object)) {
					instanceOf = true;
					break;
				}
			}
		}
		
		return instanceOf;
	}
	
	/**
	 * 
	 * @param object
	 * @param classType
	 * @return
	 */
	public static <T> boolean isNotNullAndInstanceOf(Object object, Class<T> classType) {
		return (object != null && classType.isInstance(object));
	}
	
	// /////////////////////////////////////////////////////////////////////////
	// ////////////////// Thread Utility Methods ///////////////////////////////
	// /////////////////////////////////////////////////////////////////////////
	
	/**
	 * Causes the current thread which sent this message to sleep for the given
	 * interval of time (given in milliseconds). The precision is not guaranteed
	 * - the thread may sleep more or less than requested.
	 * 
	 * @param time
	 * @see Thread#sleep()
	 */
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException ex) {
			/* ignore it. */
		}
	}
	
	/**
	 * Returns the current Thread ID.
	 * 
	 * @return
	 */
	public static long currentThreadId() {
		return Thread.currentThread().getId();
	}
	
	/**
	 * Returns the current thread signature (like name, id, priority and thread
	 * group etc.).
	 * 
	 * @return
	 */
	public static String currentThreadSignature() {
		Thread currentThread = Thread.currentThread();
		StringBuilder sBuilder = new StringBuilder("[");
		sBuilder.append(currentThread.getName());
		sBuilder.append(" <id:").append(currentThread.getId());
		sBuilder.append(", priority:").append(currentThread.getPriority());
		sBuilder.append(", threadGroupName:").append(currentThread.getThreadGroup().getName());
		sBuilder.append(">]");
		
		return sBuilder.toString();
	}
	
	/**
	 * Returns the class simple name.
	 * 
	 * @param klass
	 * @return
	 */
	public static String getClassSimpleName(Class<?> klass) {
		return (isNull(klass) ? null : klass.getSimpleName());
	}
	
	/**
	 * Returns the class simple name for this object.
	 * 
	 * @param object
	 * @return
	 */
	public static String getClassSimpleName(Object object) {
		String classSimpleName = null;
		if(isNotNull(object)) {
			Class<?> klass = object.getClass().getEnclosingClass();
			if(isNull(klass)) {
				klass = object.getClass();
			}
			classSimpleName = getClassSimpleName(klass);
		}
		
		return classSimpleName;
	}
	
}
