package com.apparatus.interviews;

import java.io.BufferedReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ListPalindromes {
	
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
		
		System.out.println("unsorted map:" + map);
		mapSorted.putAll(map);
		System.out.println("mapSorted:" + mapSorted);
	}
	
	public void add(String str) {
		Integer counter = map.get(str);
		if(counter == null) {
			counter = 0;
		}
		
		counter++;
		map.put(str, counter);
	}
	
	public void processWords(String words[]) throws Exception {
		if(words == null || words.length < 1) {
			return;
		}
		
		for(int index = 0; index < words.length; index++) {
			String word = words[index].toLowerCase();
			if(map.containsKey(word)) {
				this.add(word);
			} else if(isPalindrome(word)) {
				this.add(word);
			}
		}
	}
	
	public static boolean isPalindrome(String wordToBeChecked) {
		boolean isPalindrome = (wordToBeChecked != null);
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
	
}

/**
 * 
 * @author singhr
 *
 */
class MapValueComparator implements Comparator<String> {
	Map<String, Integer> mapToSort;
	
	public MapValueComparator(Map<String, Integer> mapToSort) {
		this.mapToSort = mapToSort;
	}
	
	/*
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String entry, String newEntry) {
		// return (mapToSort.get(entry) - mapToSort.get(newEntry));
		if(mapToSort.get(entry) > mapToSort.get(newEntry)) {
			return 1;
		} else if(mapToSort.get(entry) < mapToSort.get(newEntry)) {
			return -1;
		} else {
			return (entry.compareTo(newEntry) > 0 ? 1 : -1);
		}
	}
}
