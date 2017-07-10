package com.rslakra.testcases.installer;

import java.util.ArrayList;
import java.util.List;

public class TestStr {

	public static void main(String[] args) {
		
		String parameters = "/Users/singhr/Downloads/nativeVersion (1).bvedit /Users/singhr/Downloads/nativeVersion (2).bvedit";
		parameters = "/Users/singhr/Downloads/nativeVersion.bvedit";
//		parameters = "nativeVersion (1).bvedit nativeVersion (2).bvedit";
//		parameters = "nativeVersion (1).bvedit";
//		parameters = "nativeVersion-1.bvedit nativeVersion-2.bvedit";
//		parameters = "nativeVersion-1.bvedit";
//		parameters = "nativeVersion.bvedit";
		
		String[] fileNames = TestStr.getFileNamesFromParameters(parameters);
		for(String fileName : fileNames) {
			System.out.println(fileName);
		}
	}
	
	/**
	 * Returns an array of strings that are exteracted from the single string
	 * which contains the spaces after file names.
	 * 
	 * @return
	 */
	public static String[] getFileNamesFromParameters(String parameters) {
		//check parameters is not null or empty;
		List<String> fileNames = new ArrayList<String>();
		String ext = ".bvedit";
		while(parameters != null && parameters.length() > 0) {
			int endIndex = parameters.indexOf(ext);
			if(endIndex != -1) {
				endIndex += ext.length();
			}
			
			String fileName = parameters.substring(0, endIndex);
			fileNames.add(fileName);
//			System.out.println("fileName:" + fileName);
			parameters = parameters.substring(endIndex).trim();
		}
		
		return (String[]) fileNames.toArray(new String[0]);
	}
}
