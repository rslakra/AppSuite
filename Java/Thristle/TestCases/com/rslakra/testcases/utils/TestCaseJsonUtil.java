package com.rslakra.testcases.utils;

import com.devmatre.logger.LogManager;
import com.rslakra.json.JSONFailure;
import com.rslakra.json.JSONSuccess;
import com.rslakra.servers.utils.JsonUtil;

public class TestCaseJsonUtil {
	
	/**
	 * 
	 */
	public static void testJSONSuccess() {
		JSONSuccess jsonSuccess = new JSONSuccess("OK");
		String jsonString = JsonUtil.jsonString(jsonSuccess);
		System.out.println("jsonString:" + jsonString);
	}
	
	/**
	 * 
	 */
	public static void testJSONFailure(boolean withError) {
		JSONFailure jsonFailure = null;
		if(withError) {
			try {
				throw new IllegalArgumentException("Invalid Parameters!");
			} catch(Exception ex) {
				jsonFailure = new JSONFailure(ex);
			}
		} else {
			jsonFailure = new JSONFailure("Invalid Parameters!");
		}
		String jsonString = JsonUtil.jsonString(jsonFailure);
		System.out.println("jsonString:" + jsonString);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		
		testJSONSuccess();
		testJSONFailure(false);
		testJSONFailure(true);
	}
	
}
