package com.rslakra.jdk8;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Rohtash Singh Lakra
 * @date 02/02/2018 12:35:15 PM
 */
public class PathsExample {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Path path = Paths.get(PathsExample.class.getPackage().toString(), PathsExample.class.getSimpleName());
		System.out.println(path);
	}
	
}
