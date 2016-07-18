package com.apparatus.interviews;

import java.io.BufferedReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ListPalindromes {
	
	static Map<String, Integer> initialMap = new LinkedHashMap<String, Integer>();
	public static boolean ASC = true;
	public static boolean DESC = false;
	
	static Map<String, Integer> map = new HashMap<String, Integer>();
	static MapValueComparator valueComparator = new MapValueComparator(map);
	static Map<String, Integer> mapSorted = new TreeMap<String, Integer>(valueComparator);
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("1:" + args.length);
		if(args.length == 0) {
			return;
		}
		
		ListPalindromes palindrome = new ListPalindromes();
		String fileName = args[0];
		System.out.println("fileName:" + fileName);
		String[] words = null;
		try {
			String filePath = System.getProperty("user.dir") + "/classes/com/apparatus/interviews/" + fileName;
			BufferedReader bufferReader = new BufferedReader(new java.io.FileReader(filePath));
			String line = null;
			while((line = bufferReader.readLine()) != null) {
				line = line.replaceAll("[^\\dA-Za-z ]", " ");
				words = line.split("\\s+");
				palindrome.processWords(words);
			}
			bufferReader.close();
		} catch(Exception ex) {
			System.out.println("Error while reading file line by line");
		}
		
		Map<String, Integer> sortedMapAsc = sortByComparator(initialMap, DESC);
		printMap(sortedMapAsc);
		
		System.out.println("unsorted map:" + map);
		mapSorted.putAll(map);
		System.out.println("mapSorted:" + mapSorted);
	}
	
	public void add(String str) {
		Integer counter = initialMap.get(str);
		if(counter == null) {
			counter = 0;
		}
		
		counter++;
		initialMap.put(str, counter);
		map.put(str, counter);
	}
	
	public void processWords(String words[]) throws Exception {
		if(words == null || words.length < 1) {
			return;
		}
		
		for(int index = 0; index < words.length; index++) {
			String word = words[index].toLowerCase();
			if(initialMap.containsKey(word)) {
				this.add(word);
			} else if(isPalindrome(word)) {
				this.add(word);
			}
		}
	}
	
	public static boolean isPalindrome(String wordToBeChecked) {
		boolean isPalindrome = true;
		if(wordToBeChecked != null && wordToBeChecked.length() > 0) {
			int wordLength = wordToBeChecked.length();
			for(int index = 0; index < wordLength; index++) {
				if(wordToBeChecked.charAt(index) != wordToBeChecked.charAt(wordLength - 1 - index)) {
					isPalindrome = false;
					break;
				}
			}
		}
		
		return isPalindrome;
	}
	
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {
		System.out.println("Testing palindromes");
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
		
		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if(order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());
					
				}
			}
		});
		
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for(Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedMap;
	}
	
	public static void printMap(Map<String, Integer> map) {
		for(Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " > " + entry.getValue());
		}
	}
}


class MapValueComparator implements Comparator<String> {
	Map<String, Integer> mapToSort;
	
	public MapValueComparator(Map<String, Integer> mapToSort) {
		this.mapToSort = mapToSort;
	}
	
	/*
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String entry, String newEntry) {
//		return (mapToSort.get(entry) - mapToSort.get(newEntry));
		if(mapToSort.get(entry) > mapToSort.get(newEntry)) {
			return 1;
		}else if(mapToSort.get(entry) < mapToSort.get(newEntry)) {
			return -1;
		} else {
			return (entry.compareTo(newEntry) > 0 ? 1 : -1);
		}
	}
}
