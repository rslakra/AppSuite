package com.apparatus.junit.utils;

import java.util.HashMap;
import java.util.Map;

import com.apparatus.utils.ObjectUtils;

public class ObjectUtilTest {
	
	public static void testSorting() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("serviceName", "ObjectUtil");
		parameters.put("phoneNumber", "201-238-6938");
		parameters.put("methodName", "testSorting");
		
		System.out.println("Unsorted Map");
		ObjectUtils.print(parameters);
		
		System.out.println("Sorted Map");
//		ObjectUtils.print(ObjectUtils.sortByNaturalOrder(parameters));
	}
	
	public static void main(String[] args) {
		testSorting();
	}
	
}
