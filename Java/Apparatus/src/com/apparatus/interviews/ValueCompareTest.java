package com.apparatus.interviews;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ValueCompareTest {
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ValueComparator valueComparator = new ValueComparator(map);
		TreeMap<String, Integer> mapSorted = new TreeMap<String, Integer>(valueComparator);
		
		map.put("A", 99);
		map.put("B", 67);
		map.put("C", 68);
		map.put("D", 63);
		
		System.out.println("unsorted map: " + map);
		mapSorted.putAll(map);
		System.out.println("mapSorted: " + mapSorted);
	}
}

class ValueComparator implements Comparator<String> {
	Map<String, Integer> mapToSort;
	
	public ValueComparator(Map<String, Integer> mapToSort) {
		this.mapToSort = mapToSort;
	}
	
	/*
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String entry, String newEntry) {
		return (mapToSort.get(entry) - mapToSort.get(newEntry));
	}
}