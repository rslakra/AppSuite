package com.rslakra.testcases.bos;

import com.rslakra.bos.Phone;
import com.rslakra.testcases.utils.TestCaseUtil;

public class PhoneTestCase {
	
	public static void main(String[] args) {
		Phone phone = new Phone();
		TestCaseUtil.populateDefaults(phone);
		System.out.println(phone);
		
		// add new extensions
		phone.addExtension("456");
		System.out.println("after adding a new extension");
		System.out.println(phone);
		
		// phone.addExtension(2, 193);
		// System.out.println("after adding 1 more extension at index 2");
		// System.out.println(phone);
		//
		// System.out.println("after removing extension from index 4");
		// phone.removeExtension(4);
		
		System.out.println("after removing extension, 123");
		phone.removeExtension("123");
		System.out.println(phone);
	}
	
}
