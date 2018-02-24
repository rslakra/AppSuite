/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.java.net.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Helper class for HTTP handling.
 * 
 * @author Rohtash Singh
 * @version 1.0.0
 * @since May 6, 2015 2:37:37 PM
 */
public final class HttpUtils {
	
	/** SLASH */
	public static final String SLASH = "/".intern();
	
	/** NEWLINE */
	public static final String NEWLINE = "\n".intern();
	
	/** UTF-8 */
	public static final String UTF_8 = "UTF-8".intern();
	
	/** ISO-8859-1 */
	public static final String ISO_8859_1 = "ISO-8859-1".intern();
	
	/** SEPERATOR_HASH */
	public static final String SEPERATOR_HASH = "#".intern();
	
	/** COLON_DOUBLE_SLASH */
	public static final String COLON_DOUBLE_SLASH = "://".intern();
	
	/** HEX_DIGIT_CHARS */
	public static final String HEX_DIGITS = "0123456789abcdef".intern();
	
	/** EMPTY_STRING */
	public static final String EMPTY_STRING = "".intern();
	
	// Singleton object
	private HttpUtils() {
		throw new UnsupportedOperationException("Object creation is not allowed!");
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
	 * @param objects
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(T[] objects) {
		return (isNull(objects) || objects.length == 0);
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
	 * Returns true if either the string is null or empty otherwise false.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string) {
		return (isNull(string) || string.isEmpty() || string.trim().length() == 0);
	}
	
	/**
	 * Returns true if the given string is neither null nor empty otherwise
	 * false.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String string) {
		return (!isNullOrEmpty(string));
	}
	
	/**
	 * Returns true if the StringBuilder is null or empty otherwise false.
	 * 
	 * @param stringBuilder
	 * @return
	 */
	public static boolean isNullOrEmpty(CharSequence sharSequence) {
		return (isNull(sharSequence) || sharSequence.length() == 0);
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
	 * Returns the value of the given key, if exists otherwise null.
	 * 
	 * @param mapObjects
	 * @param key
	 * @return
	 */
	public static Object getKeyValue(Map<String, Object> mapObjects, String key) {
		return ((mapObjects != null && key != null) ? mapObjects.get(key) : null);
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
	 * Returns true of the <code>keyValues</code> map contains the specified
	 * <code>key</code> otherwise false.
	 * 
	 * @param keyValues
	 * @param key
	 * @return
	 */
	public static <K, V> boolean keyExists(Map<K, V> keyValues, K key) {
		return (!isNullOrEmpty(keyValues) && keyValues.keySet().contains(key));
	}
	
	/**
	 * Converts the string to set after splitting the string with the specified
	 * delimiter.
	 * 
	 * @param value
	 * @param delimiter
	 * @return
	 */
	public static Set<String> asSet(String valueString, String delimiter) {
		Set<String> setStrings = new HashSet<String>();
		if(isNotNullOrEmpty(valueString) && isNotNullOrEmpty(delimiter)) {
			String[] tokens = valueString.split(delimiter);
			if(!isNullOrEmpty(tokens)) {
				for(int i = 0; i < tokens.length; i++) {
					setStrings.add(tokens[i].trim());
				}
			}
		}
		
		return setStrings;
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
	 * Returns an array containing all elements contained in this {@code List}.
	 * If the specified array is large enough to hold the elements, the
	 * specified array is used, otherwise an array of the same type is created.
	 * If the specified array is used and is larger than this {@code List}, the
	 * array element following the collection elements is set to null.
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
	 * @param type
	 * @param types
	 * @return
	 */
	public static <T> boolean equals(T type, T... types) {
		boolean result = false;
		if(isNotNull(type) && !isNullOrEmpty(types)) {
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
	 * Returns true if the <code>string</code> matches any of the specified
	 * <code>args</code> otherwise false.
	 * 
	 * @param ignoreCase
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean equalsAnyone(boolean ignoreCase, String string, String... args) {
		if(isNotNullOrEmpty(string) && args != null) {
			for(int i = 0; i < args.length; i++) {
				if(ignoreCase) {
					if(string.equalsIgnoreCase(args[i])) {
						return true;
					}
				} else {
					if(string.equals(args[i])) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if the <code>string</code> matches any of the specified
	 * <code>args</code> otherwise false.
	 * 
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean equalsAnyone(String string, String... args) {
		return equalsAnyone(false, string, args);
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
			@Override
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
	public static String toString(Object object) {
		return (object != null ? object.toString() : EMPTY_STRING);
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
		if(isNotNull(object) && !isNullOrEmpty(classTypes)) {
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
	
	/**
	 * Converts the normal map to sorted map.
	 * 
	 * @param map
	 * @return
	 */
	public static SortedMap<String, Object> toSortedMap(Map<String, ? extends Object> map) {
		return new TreeMap<String, Object>(map);
	}
	
	/**
	 * Added to handle the key/value pair.
	 *
	 * @author Rohtash Singh Lakra
	 * @date 04/19/2017 11:12:00 AM
	 * 
	 * @param <K>
	 * @param <V>
	 */
	public static class KeyValuePair<K, V> implements Serializable {
		
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;
		
		private final K key;
		private final V value;
		
		/**
		 * Extracts the key and value from the specified
		 * <code>keyValueString</code> based on the equals to (=) sign, if its
		 * not
		 * null or empty otherwise null.
		 * 
		 * @param keyValueString
		 * @return
		 */
		public static KeyValuePair<String, String> newKeyValuePair(String keyValueString) {
			KeyValuePair<String, String> keyValuePair = null;
			if(isNotNullOrEmpty(keyValueString)) {
				int equalIndex = keyValueString.indexOf("=");
				if(equalIndex != -1) {
					String key = keyValueString.substring(0, equalIndex).trim();
					int lastIndex = keyValueString.indexOf(";");
					String value = null;
					if(lastIndex != -1) {
						value = keyValueString.substring(equalIndex + 1, lastIndex).trim();
					} else {
						value = keyValueString.substring(equalIndex + 1).trim();
					}
					keyValuePair = new KeyValuePair<String, String>(key, value);
				}
			}
			
			return keyValuePair;
		}
		
		/**
		 * 
		 * @param key
		 * @param value
		 * @param hash
		 * @param nextEntry
		 */
		public KeyValuePair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		/**
		 * Returns the key.
		 * 
		 * @return
		 */
		public final K getKey() {
			return key;
		}
		
		/**
		 * Return the value.
		 * 
		 * @return
		 */
		public final V getValue() {
			return value;
		}
		
		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public final boolean equals(Object object) {
			if(!(object instanceof KeyValuePair)) {
				return false;
			}
			KeyValuePair<?, ?> keyValuePair = (KeyValuePair<?, ?>) object;
			return (getKey().equals(keyValuePair.getKey()) && getValue().equals(keyValuePair.getValue()));
		}
		
		/**
		 * Returns the hash code of this object.
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public final int hashCode() {
			return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
		}
		
		/**
		 * Returns the string representation of this object.
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public final String toString() {
			return (getKey() + "=" + getValue());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static <T> List<T> emptyList() {
		return new ArrayList<T>();
	}
	
	/**
	 * Returns an empty ArrayList if the specified list is null. Otherwise,
	 * returns the list itself.
	 */
	public static <T> List<T> makeEmptyIfNull(List<T> list) {
		return (list == null ? new ArrayList<T>() : list);
	}
	
	/**
	 * Returns true if the specified object is an instance of any of the
	 * specified classes.
	 */
	public static boolean instanceOfAny(Object object, Class<?>... classes) {
		boolean result = false;
		if(object != null) {
			for(Class<?> klass : classes) {
				if(klass != null && klass.isInstance(object)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the GSON object.
	 * 
	 * @param prettyPrint
	 * @return
	 */
	private static Gson createGsonObject(boolean prettyPrint) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.enableComplexMapKeySerialization();
		if(prettyPrint) {
			gsonBuilder.setPrettyPrinting();
		}
		
		Gson gson = gsonBuilder.create();
		return gson;
	}
	
	/**
	 * Returns the JSON string for the given JSON object.
	 * 
	 * @param object
	 * @param prettyPrint
	 * @return
	 */
	public static String toJson(Object object, boolean prettyPrint) {
		Gson gson = createGsonObject(prettyPrint);
		return gson.toJson(object);
	}
	
	/**
	 * Returns the JSON string of the given list.
	 * 
	 * @param list
	 * @return
	 */
	public static String toJSONString(List<String> list) {
		String jsonString = "";
		if(!isNullOrEmpty(list)) {
			jsonString = new Gson().toJson(list);
		}
		
		return jsonString;
	}
	
	/**
	 * Returns the JSON string for the given JSON object.
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return toJson(object, false);
	}
	
	/**
	 * Generates the JSON string from the given map.
	 * 
	 * @param mapKeyValues
	 * @return
	 */
	public static String toJson(Map<String, String> mapKeyValues) {
		return createGsonObject(false).toJson(mapKeyValues);
	}
	
	/**
	 * Returns the object of the class type T for the given JSON string.
	 * 
	 * @param jsonString
	 * @param classType
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> classType) {
		Gson gson = createGsonObject(false);
		T object = gson.fromJson(jsonString, classType);
		return object;
	}
	
	/**
	 * Returns the Map object from the given data bytes.
	 * 
	 * @param dataBytes
	 * @return
	 */
	public static Map<String, Object> jsonBytesAsMap(byte[] dataBytes) {
		TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
		String jsonString = toUTF8String(dataBytes);
		return (new Gson().fromJson(jsonString, typeToken.getType()));
	}
	
	/**
	 * Returns the Map object from the given string.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, List<String>> jsonHeadersAsMap(String jsonString) {
		TypeToken<Map<String, List<String>>> typeToken = new TypeToken<Map<String, List<String>>>() {};
		return (new Gson().fromJson(jsonString, typeToken.getType()));
	}
	
	/**
	 * Returns the defaultCharset, if the given charsetName is either null or
	 * empty otherwise the same.
	 * 
	 * @param charsetName
	 * @return
	 */
	public static String defaultCharset(String charsetName) {
		return (isNullOrEmpty(charsetName) ? Charset.defaultCharset().displayName() : charsetName);
	}
	
	/**
	 * Converts the specified <code>bytes</code> to the specified
	 * <code>charsetName</code> String.
	 * 
	 * @param bytes
	 * @param charsetName
	 * @return
	 */
	public static String toString(byte[] bytes, String charsetName) {
		String bytesAsString = null;
		if(!isNullOrEmpty(bytes)) {
			try {
				if(isNullOrEmpty(charsetName)) {
					bytesAsString = new String(bytes);
				} else {
					bytesAsString = new String(bytes, charsetName);
				}
			} catch(Exception ex) {
				bytesAsString = (isNull(bytes) ? null : bytes.toString());
			}
		}
		
		return bytesAsString;
	}
	
	/**
	 * /**
	 * Returns the UTF-8 String representation of the given <code>bytes</code>.
	 * 
	 * @param bytes
	 * @param replaceNonDigitCharacters
	 * @return
	 */
	public static String toUTF8String(byte[] bytes, boolean replaceNonDigitCharacters) {
		String utf8String = toString(bytes, UTF_8);
		if(replaceNonDigitCharacters && isNotNullOrEmpty(utf8String)) {
			utf8String = utf8String.replaceAll("\\D+", "");
		}
		
		return utf8String;
	}
	
	/**
	 * Returns the UTF-8 String representation of the given <code>bytes</code>.
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toUTF8String(byte[] bytes) {
		return toUTF8String(bytes, false);
	}
	
	/**
	 * Returns the UTF-8 String representation of the given <code>string</code>.
	 * 
	 * @param string
	 * @return
	 */
	public static String toUTF8String(String string) {
		return toUTF8String(string.getBytes());
	}
	
	/**
	 * Returns the ISO-8859-1 String representation of the given
	 * <code>bytes</code>.
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toISOString(byte[] bytes) {
		return toString(bytes, ISO_8859_1);
	}
	
	/**
	 * Converts the specified <code>string</code> into bytes using the specified
	 * <code>charsetName</code>.
	 * 
	 * @param string
	 * @param charsetName
	 * @return
	 */
	public static byte[] toBytes(String string, String charsetName) {
		byte[] stringAsBytes = null;
		if(isNotNull(string)) {
			try {
				stringAsBytes = isNullOrEmpty(charsetName) ? string.getBytes() : string.getBytes(charsetName);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return stringAsBytes;
	}
	
	/**
	 * Converts the specified <code>string</code> into bytes.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] toBytes(String string) {
		return toBytes(string, null);
	}
	
	/**
	 * Returns the UTF-8 bytes for the given string.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] toUTF8Bytes(String string) {
		return toBytes(string, UTF_8);
	}
	
	/**
	 * Returns the ISO-8859-1 bytes for the given string.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] toISOBytes(String string) {
		return toBytes(string, ISO_8859_1);
	}
	
	/**
	 * Returns the string of the given strings array.
	 * 
	 * @param strings
	 * @return
	 */
	public static String toString(String... strings) {
		StringBuilder sBuilder = new StringBuilder();
		if(isNullOrEmpty(strings)) {
			sBuilder.append("[]");
		} else {
			sBuilder.append("[");
			for(int i = 0; i < strings.length; i++) {
				sBuilder.append(strings[i]);
				if(i < (strings.length - 1)) {
					sBuilder.append(",");
				}
			}
			
			sBuilder.append("]");
		}
		
		return sBuilder.toString();
	}
	
	/**
	 * Convert a byte array to a HEXA String of the format "1f 30 b7".
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toHexString(byte[] bytes) {
		String hexString = null;
		if(!isNullOrEmpty(bytes)) {
			StringBuilder hexBuilder = new StringBuilder(bytes.length * 2);
			for(int index = 0; index < bytes.length; index++) {
				int hn = ((int) (bytes[index]) & 0x00ff) / 16;
				int ln = ((int) (bytes[index]) & 0x000f);
				
				hexBuilder.append(HEX_DIGITS.charAt(hn));
				hexBuilder.append(HEX_DIGITS.charAt(ln));
			}
			
			hexString = hexBuilder.toString();
			// available for GC.
			hexBuilder = null;
		}
		
		return hexString;
	}
	
	/**
	 * Convert the hex string into an array of bytes.
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] toHexBytes(String hexString) {
		byte[] hexBytes = null;
		if(!isNullOrEmpty(hexString)) {
			int length = hexString.length() / 2;
			hexBytes = new byte[length];
			for(int i = 0; i < length; i++) {
				hexBytes[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
			}
		}
		
		return hexBytes;
	}
	
	/**
	 * Returns the extension from the specified fullPath, if its not null or
	 * empty otherwise null.
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getExtension(String fullPath) {
		String extension = null;
		if(!isNullOrEmpty(fullPath)) {
			int dotIndex = fullPath.lastIndexOf(".");
			extension = ((dotIndex > -1 && dotIndex < fullPath.length() - 1) ? fullPath.substring(dotIndex + 1) : "");
		}
		
		return extension;
	}
	
	/**
	 * Returns the filename from the specified fullPath, if its not null or
	 * empty otherwise null.
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getFileName(String fullPath, boolean withExtension) {
		String fileName = null;
		if(!isNullOrEmpty(fullPath)) {
			int pathSeparatorIndex = fullPath.lastIndexOf(File.separator);
			if(pathSeparatorIndex < fullPath.length() - 1) {
				fileName = fullPath.substring(pathSeparatorIndex + 1);
				if(!withExtension) {
					int dotIndex = fileName.lastIndexOf(".");
					fileName = ((dotIndex > -1 && dotIndex < fileName.length() - 1) ? fileName.substring(0, dotIndex) : fileName);
				}
			}
		}
		
		return fileName;
	}
	
	/**
	 * Returns the host name extracted from the specified urlString.
	 * 
	 * @param urlString
	 * @return
	 */
	public static String getHostNameFromUrl(String urlString) {
		String hostName = urlString;
		if(!isNullOrEmpty(urlString)) {
			int startIndex = urlString.indexOf("://");
			if(startIndex >= 0) {
				startIndex += "://".length();
				int endIndex = urlString.lastIndexOf(":");
				if(endIndex > -1 && endIndex < startIndex) {
					int slashIndex = urlString.lastIndexOf(SLASH);
					if(slashIndex != -1) {
						hostName = urlString.substring(startIndex, slashIndex);
					} else {
						hostName = urlString.substring(startIndex);
					}
				} else {
					hostName = urlString.substring(startIndex, endIndex);
				}
			}
		}
		
		return hostName;
	}
	
	/**
	 * Returns true if the string contains only digits. The $ avoids a partial
	 * match, i.e. 1b.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isDigits(String string) {
		return (isNotNullOrEmpty(string) && string.matches("^[0-9]*$"));
	}
	
	/**
	 * Returns true if the value starts with any of the specified prefixes
	 * otherwise false.
	 * 
	 * @param value
	 * @param prefixes
	 * @return
	 */
	public static boolean startsWith(String value, String... prefixes) {
		boolean result = false;
		if(!isNullOrEmpty(value) && prefixes != null) {
			for(String prefix : prefixes) {
				if(value.startsWith(prefix)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the stack trace as string.
	 * 
	 * @param error
	 * @return
	 */
	public static String toString(Throwable error) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			PrintWriter writer = new PrintWriter(outputStream);
			error.printStackTrace(writer);
			writer.flush();
			writer.close();
			outputStream.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return new String(outputStream.toByteArray());
	}
	
	/**
	 * Returns true if the <code>string</code> contains any of the specified
	 * <code>args</code> otherwise false.
	 * 
	 * @param ignoreCase
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean containsAnyone(boolean ignoreCase, String string, String... args) {
		if(isNotNullOrEmpty(string) && args != null) {
			for(int i = 0; i < args.length; i++) {
				if(ignoreCase) {
					Locale defaultLocale = Locale.getDefault();
					if(string.toLowerCase(defaultLocale).contains(args[i].toLowerCase(defaultLocale))) {
						return true;
					}
				} else {
					if(string.contains(args[i])) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if the <code>string</code> contains any of the specified
	 * <code>args</code> otherwise false.
	 * 
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean containsAnyone(String string, String... args) {
		return containsAnyone(false, string, args);
	}
	
}
