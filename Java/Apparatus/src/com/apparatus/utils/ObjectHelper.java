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
	 * 
	 * @param object
	 * @return
	 */
	public static int hashCode(Object object) {
		return (isNull(object) ? 0 : object.hashCode());
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
	
	/**
	 * Bubble sort, sometimes referred to as sinking sort, is a simple sorting
	 * algorithm that repeatedly steps through the list to be sorted, compares
	 * each pair of adjacent items and swaps them if they are in the wrong
	 * order. The pass through the list is repeated until no swaps are needed,
	 * which indicates that the list is sorted.
	 * 
	 * Bubble sort has worst-case and average complexity both О(n2), where n is
	 * the number of items being sorted. There exist many sorting algorithms
	 * with substantially better worst-case or average complexity of O(n log n).
	 * Even other О(n2) sorting algorithms, such as insertion sort, tend to have
	 * better performance than bubble sort. Therefore, bubble sort is not a
	 * practical sorting algorithm when n is large.
	 * 
	 * @param sortArray
	 */
	public static void bubbleSort(int[] sortArray) {
		if(!isNullOrEmpty(sortArray)) {
			for(int i = 0; i < sortArray.length - 1; i++) {
				for(int j = 0; j < sortArray.length - 1 - i; j++) {
					if(sortArray[j] > sortArray[j + 1]) {
						int temp = sortArray[j];
						sortArray[j] = sortArray[j + 1];
						sortArray[j + 1] = temp;
					}
				}
			}
		}
	}
	
	/**
	 * Insertion sort is a simple sorting algorithm that builds the final sorted
	 * array (or list) one item at a time. It is much less efficient on large
	 * lists than more advanced algorithms such as quicksort, heapsort, or merge
	 * sort.
	 * 
	 * However, insertion sort provides several advantages:
	 * - Efficient for (quite) small data sets, much like other quadratic
	 * sorting algorithms.
	 * - More efficient in practice than most other simple quadratic (i.e.,
	 * O(n2)) algorithms such as selection sort or bubble sort
	 * 
	 * Insertion sort iterates, consuming one input element each repetition, and
	 * growing a sorted output list. Each iteration, insertion sort removes one
	 * element from the input data, finds the location it belongs within the
	 * sorted list, and inserts it there. It repeats until no input elements
	 * remain.
	 * 
	 * @param sortArray
	 */
	public static void insertionSort(int[] sortArray) {
		if(!isNullOrEmpty(sortArray)) {
			int j, temp;
			for(int i = 1; i < sortArray.length; i++) {
				temp = sortArray[i];
				for(j = i; j > 0 && temp < sortArray[j - 1]; j--) {
					sortArray[j] = sortArray[j - 1];
				}
				sortArray[j] = temp;
			}
		}
	}
	
	/**
	 * 
	 * @param sortArray
	 * @param lowIndex
	 * @param micIndex
	 * @param highIndex
	 * @param tempArray
	 */
	private static void mergeSort(int[] sortArray, int lowIndex, int midIndex, int highIndex, int[] tempArray) {
		int i = lowIndex;
		int j = midIndex + 1;
		int k = lowIndex;
		
		// populate tempArray
		System.arraycopy(sortArray, 0, tempArray, 0, sortArray.length);
		
		// merge sets
		while(i <= midIndex && j <= highIndex) {
			if(tempArray[i] < tempArray[j]) {
				sortArray[k] = tempArray[i];
				k++;
				i++;
			} else {
				sortArray[k] = tempArray[j];
				k++;
				j++;
			}
		}
		
		// merge left set
		while(i <= midIndex) {
			sortArray[k] = tempArray[i];
			k++;
			i++;
		}
		
		// merge right set.
		while(j <= highIndex) {
			sortArray[k] = tempArray[j];
			k++;
			j++;
		}
	}
	
	/**
	 * Splits the list and merge.
	 * 
	 * @param sortArray
	 * @param lowIndex
	 * @param highIndex
	 * @param tempArray
	 */
	private static void mergeSort(int[] sortArray, int lowIndex, int highIndex, int[] tempArray) {
		if(highIndex > lowIndex) {
			int midIndex = (highIndex + lowIndex) / 2;
			mergeSort(sortArray, lowIndex, midIndex, tempArray);
			mergeSort(sortArray, midIndex + 1, highIndex, tempArray);
			mergeSort(sortArray, lowIndex, midIndex, highIndex, tempArray);
		}
	}
	
	/**
	 * Merge sort (also commonly spelled mergesort) is an efficient,
	 * general-purpose, comparison-based sorting algorithm. Most implementations
	 * produce a stable sort, which means that the implementation preserves the
	 * input order of equal elements in the sorted output. Mergesort is a divide
	 * and conquer algorithm
	 * 
	 * Conceptually, a merge sort works as follows:
	 * 
	 * 1. Divide the unsorted list into n sublists, each containing 1 element (a
	 * list of 1 element is considered sorted).
	 * 2. Repeatedly merge sublists to produce new sorted sublists until there
	 * is only 1 sublist remaining. This will be the sorted list.
	 * 
	 * In top down merge sort algorithm that recursively splits the list (called
	 * runs in this example) into sublists until sublist size is 1, then merges
	 * those sublists to produce a sorted list. The copy back step could be
	 * avoided if the recursion alternated between two functions so that the
	 * direction of the merge corresponds with the level of recursion.
	 * 
	 * In sorting n objects, merge sort has an average and worst-case
	 * performance of O(n log n). If the running time of merge sort for a list
	 * of length n is T(n), then the recurrence T(n) = 2T(n/2) + n follows from
	 * the definition of the algorithm (apply the algorithm to two lists of half
	 * the size of the original list, and add the n steps taken to merge the
	 * resulting two lists). The closed form follows from the master theorem.
	 * 
	 * In the worst case, the number of comparisons merge sort makes is equal to
	 * or slightly smaller than (n ⌈lg n⌉ - 2⌈lg n⌉ + 1), which is between (n lg
	 * n - n + 1) and (n lg n + n + O(lg n)).
	 * 
	 * @param sortArray
	 */
	public static void mergeSort(int[] sortArray) {
		if(!isNullOrEmpty(sortArray)) {
			int[] tempArray = new int[sortArray.length];
			mergeSort(sortArray, 0, sortArray.length - 1, tempArray);
		}
	}
	
}
