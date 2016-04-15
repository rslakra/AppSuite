package com.apparatus.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Apr 29, 2015 3:12:38 PM
 */
public class ObjectHelper {
	
	/**
	 * Returns the user's home directory.
	 * 
	 * @return
	 */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}
	
	/**
	 * Returns the user's application directory.
	 * 
	 * @return
	 */
	public static String getUserDir() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * Returns the path of the testcases dir.
	 * 
	 * @return
	 */
	public static String getTestCasesDir() {
		return FileHelper.pathString(getUserDir(), "TestCases");
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getTestCasesTempDir() {
		return FileHelper.pathString(getTestCasesDir(), "Temp");
	}
	
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
	 * @param array
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(T... array) {
		return (isNull(array) || array.length == 0);
	}
	
	/**
	 * Returns true if the collection is null or empty otherwise false.
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
	 * Returns true if the bytes array is either null or empty otherwise false.
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
	 * @param map
	 * @return
	 */
	public static <K extends Comparable<K>, V extends Comparable<V>> Map<K, V> sortByKeys(Map<K, V> map) {
		List<K> keys = new LinkedList<K>(map.keySet());
		Collections.sort(keys);
		
		/*
		 * LinkedHashMap will keep the keys in the order they are inserted which
		 * is currently sorted on natural ordering.
		 */
		Map<K, V> sortedMap = new LinkedHashMap<K, V>();
		for(K key : keys) {
			sortedMap.put(key, map.get(key));
		}
		
		return sortedMap;
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
	
	// /**
	// *
	// * @param map
	// * @return
	// */
	// public static <K extends Comparable<K>, V extends Comparable<V>>
	// SortedMap<K, V> sortByNaturalOrder(Map<K, V> map) {
	// return new TreeMap<>(map);
	// }
	
	/**
	 * Prints the given map.
	 * 
	 * @param map
	 */
	public static <K, V> void print(Map<K, V> map) {
		if(!isNullOrEmpty(map)) {
			for(Map.Entry<K, V> entry : map.entrySet()) {
				System.out.println("Key:" + entry.getKey() + ", Value:" + entry.getValue());
			}
		}
	}
	
	/**
	 * Prints the given strings.
	 * 
	 * @param strings
	 */
	public static void print(String... strings) {
		if(!isNullOrEmpty(strings)) {
			System.out.println("Tokens:" + strings.length);
			for(String string : strings) {
				System.out.println(string);
			}
		}
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object) {
		return (object == null ? "" : object.toString());
	}
	
	/**
	 * 
	 * @param object
	 * @param classType
	 * @return
	 */
	public static <T> boolean isInstanceOf(Object object, Class<T> classType) {
		return (classType.isInstance(object));
	}
	
}
