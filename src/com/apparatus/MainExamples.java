/**
 * 
 */
package com.apparatus;

import com.apparatus.config.Config;
import com.apparatus.utils.FileHelper;
import com.apparatus.utils.ObjectUtils;
import com.apparatus.utils.ServerUtil;
import com.apparatus.utils.StringHelper;

/**
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @version 1.0.0
 * @since Apr 30, 2015 5:25:24 PM
 */
public class MainExamples {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Config.mergeProperties(FileHelper.loadProperties("Config.properties"));
		String ROOT_CONTEXT = Constants.SLASH + "html";
		ServerUtil.isNetworkAvailable(ROOT_CONTEXT);
		
		StringSplitter stringSplitter = new StringSplitter(',');
		
		System.out.println(ObjectUtils.isInstanceOf(null, ObjectUtils.class));
		System.out.println(ObjectUtils.isInstanceOf(stringSplitter, StringSplitter.class));
		
		String urlString = "";
		StringHelper.extractHostNameFromURLString(urlString);
		
		urlString = "https";
		StringHelper.extractHostNameFromURLString(urlString);
		
		urlString = "https://examples.rslakra.com";
		StringHelper.extractHostNameFromURLString(urlString);
		
		urlString = "https://examples.rslakra.com:8080";
		StringHelper.extractHostNameFromURLString(urlString);
		
		System.out.println("urlString:" + urlString);
		System.out.println("emptyString:" + StringHelper.emptyString(urlString));
		
		String fileName = "root/MainExamples.java";
		System.out.println("fileName:" + fileName + ", Name:" + StringHelper.getFileName(fileName, true));
		
		fileName = null;
		System.out.println("fileName:" + fileName + ", Name:" + StringHelper.getFileName(fileName, true));

		fileName = "MainExamples.java";
		System.out.println("fileName:" + fileName + ", Name:" + StringHelper.getFileName(fileName, true));
		String filePath = "/192.168.3.164/Data/030D890860E64561BB59484E58F7E576";
		String name = StringHelper.getFileName(filePath, false);
		System.out.println("filePath:" + filePath + ", Name:" + name);

	}
	
}
