package com.rslakra.jdk8.lambdaexpr;

import java.io.File;
import java.io.FileFilter;

public class JavaFileFilter implements FileFilter {
	
	public static void printFiles(File[] files) {
		for(File file : files) {
			System.out.println(file);
		}
	}
	
	public static void main(String[] args) {
		final String dirPath = System.getProperty("user.dir") + "/src/com/rslakra/jdk18";
		JavaFileFilter fileFilter = new JavaFileFilter();
		File file = new File(dirPath);
		File[] files = file.listFiles(fileFilter);
		printFiles(files);
		System.out.println("\n");
		
		// with lambda expression
		File tempFile = new File(dirPath);
		FileFilter lambdaFilter = (File temp) -> temp.getName().endsWith(".java");
		File[] tempFiles = tempFile.listFiles(lambdaFilter);
		printFiles(tempFiles);
	}
	
	@Override
	public boolean accept(File file) {
		return file.getName().endsWith(".java");
	}
	
}
