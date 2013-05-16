package com.devamatre.core;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Creates output directory structure for multi-jvm, multi-url unit, regression
 * and compliance tests.
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 01/12/2012 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class VersionMaker {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		if (args == null) {
			args = new String[] { "unit", "c:\\downloads\\temp",
					"c:\\downloads\\temp" };
		}
		if (args.length < 3) {
			usage();
			System.exit(1);
		}

		String jvmVersion = replaceWhitespaces(System
				.getProperty("java.version"));
		String jvmVendor = replaceWhitespaces(System.getProperty("java.vendor"));
		String osName = replaceWhitespaces(System.getProperty("os.name"));
		String osArch = replaceWhitespaces(System.getProperty("os.arch"));
		String osVersion = replaceWhitespaces(System.getProperty("os.version"));

		String jdbcUrl = System.getProperty("com.mysql.jdbc.testsuite.url");

		String mysqlVersion = "not-available";

		String jvmSubdirName = jvmVendor + "-" + jvmVersion;
		String osSubdirName = osName + "-" + osArch + "-" + osVersion;

		File baseDir = new File(args[1]);
		File mysqlVersionDir = new File(baseDir, mysqlVersion);
		File osVersionDir = new File(mysqlVersionDir, osSubdirName);
		File jvmVersionDir = new File(osVersionDir, jvmSubdirName);

		jvmVersionDir.mkdirs();

		FileOutputStream pathOut = null;

		try {
			String propsOutputPath = args[2];
			pathOut = new FileOutputStream(propsOutputPath);
			String baseDirStr = baseDir.getAbsolutePath();
			String jvmVersionDirStr = jvmVersionDir.getAbsolutePath();
			if (jvmVersionDirStr.startsWith(baseDirStr)) {
				jvmVersionDirStr = jvmVersionDirStr.substring(baseDirStr
						.length() + 1);
			}
			pathOut.write(jvmVersionDirStr.getBytes());
		} finally {
			if (pathOut != null) {
				pathOut.flush();
				pathOut.close();
			}
		}
	}

	public static String replaceWhitespaces(String input) {
		if (input == null) {
			return input;
		}

		int strLen = input.length();
		StringBuffer output = new StringBuffer(strLen);

		for (int i = 0; i < strLen; i++) {
			char c = input.charAt(i);
			if (!Character.isDigit(c) && !Character.isLetter(c)) {
				if (Character.isWhitespace(c)) {
					output.append("_");
				} else {
					output.append(".");
				}
			} else {
				output.append(c);
			}
		}

		return output.toString();
	}

	private static void usage() {
		System.err
				.println("Creates a fs hierarchy representing MySQL version, OS version and JVM version.");
		System.err
				.println("Stores the full path as 'outputDirectory' property in file 'directoryPropPath'");
		System.err.println();
		System.err
				.println("Usage: java VersionMaker unit|compliance baseDirectory directoryPropPath");
	}
}