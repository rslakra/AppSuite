package com.rslakra.testcases.bos;

import com.rslakra.bos.User;
import com.rslakra.testcases.utils.TestCaseUtil;

public class UserTestCase {
	
	public static void main(String[] args) {
		User user = new User();
		TestCaseUtil.populateDefaults(user);
		System.out.println(user);
		
	}
	
}
