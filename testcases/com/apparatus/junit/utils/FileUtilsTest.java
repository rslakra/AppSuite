package com.apparatus.junit.utils;

import java.io.IOException;

import com.apparatus.utils.FileHelper;
import com.apparatus.utils.ObjectUtils;

public class FileUtilsTest {
	
	public static void testReadFileBytes() {
		String filePath = "/Users/singhr/Downloads/ToBeDeleted/CompareTest/MeetX-11.0.20150528.101817/resources/assets/images/BVLiveryDocumentIconJPG_new.5f9341d7abfc7ea358be96f972667532.png";
		String outputFilePath = FileHelper.pathString(ObjectUtils.getTestCasesTempDir(), "BVLiveryDocumentIconJPG_new.5f9341d7abfc7ea358be96f972667532.png");
		byte[] fileBytes = null;
		try {
			fileBytes = FileHelper.readFileBytes(filePath);
			System.out.println("fileBytes:" + fileBytes);
			FileHelper.writeFile(outputFilePath, fileBytes);
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String testCasesTempDir = ObjectUtils.getTestCasesTempDir();
		FileHelper.makeDirectory(testCasesTempDir);
		testReadFileBytes();
		
	}
}
