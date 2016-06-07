/******************************************************************************
 * Copyright (C) Devamatre/Devmatre Inc. 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license
 * agreements. The reproduction, transmission or use of this code or the
 * snippet is not permitted without prior express written consent of Devamatre.
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the
 * offenders will be liable for any damages. All rights, including but not
 * limited to rights created by patent grant or registration of a utility model
 * or design, are reserved. Technical specifications and features are binding
 * only insofar as they are specifically and expressly agreed upon in a written
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 * http://www.devamatre.com/licenses/license.txt.
 * 
 * Devamatre reserves the right to modify the technical specifications and or
 * features without any prior notice.
 *****************************************************************************/
package com.apparatus.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.StringCharacterIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.apparatus.Constants;
import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;

/**
 * StringUtils.java
 * 
 * The <code>StringUtils</code> TODO Define Purpose here
 * 
 * @author Rohtash Singh (rohtash.singh@gmail.com)
 * @created 01/08/2010 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public final class StringHelper implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = -8755337045582158234L;
	
	/* constants */
	public static final char SPACE = '\u0020';
	public static final char SPACE_SEPARATOR = '\u00A0';
	public static final char LINE_SEPARATOR = '\u2007';
	public static final char PARAGRAPH_SEPARATOR = '\u202F';
	public static final String STR_SPACE = "" + SPACE;
	public static final String STR_LINE = "" + LINE_SEPARATOR;
	public static final String STR_PARAGRAPH = "" + PARAGRAPH_SEPARATOR;
	
	/* Horizontal Tabulation ('\t') */
	public static final char HTAB = '\u0009';
	/* Vertical Tabulation */
	public static final char VTAB = '\u000B';
	public static final char FORM_FEED = '\u000C';
	public static final char FILE_SEPARATOR = '\u001C';
	public static final char GROUP_SEPARATOR = '\u001D';
	public static final char RECORD_SEPARATOR = '\u001E';
	public static final char UNIT_SEPARATOR = '\u001F';
	public static final char LINE_FEED = '\n';
	public static final char CARRIAGE_RETURN = '\r';
	public static final String DELIMITER = "" + HTAB;
	public static final String VER_DELIMITER = "" + VTAB;
	public static final String EMPTY_STR = "";
	
	/* valid email expression. */
	private static String VALID_EMAIL_EXPRESSION = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	
	/** logger */
	private static Logger log = LogManager.getLogger(StringHelper.class);
	
	/**
	 * Removes leading whitespace of the specified string using expressions.
	 * 
	 * @param str
	 * @return
	 */
	public static String lTrim(String str) {
		return str.replaceAll("^\\s+", "");
	}
	
	/**
	 * Remove trailing whitespace of the specified string using expressions.
	 * 
	 * @param str
	 * @return
	 */
	public static String rTrim(String str) {
		return str.replaceAll("\\s+$", "");
	}
	
	/**
	 * Replaces multiple whitespace between words with single whitespace using
	 * expressions.
	 * 
	 * @param str
	 * @return
	 */
	public static String iTrim(String str) {
		return str.replaceAll("\\b\\s{2,}\\b", " ");
	}
	
	/**
	 * Removes all redundant (leading, trailing and centric) whitespace of the
	 * specified string using expressions.
	 * 
	 * 
	 * @param str
	 * @return String
	 */
	public static String trim(String str) {
		return iTrim(lTrim(rTrim(str)));
	}
	
	/**
	 * Removes all redundant (leading, trailing and centric) delimiter
	 * characters from the specified string.
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String trim(String str, String delimiter) {
		if(log.isDebugEnabled()) {
			log.debug("+trim(" + str + ", " + delimiter + ")");
		}
		if(str != null && delimiter != null) {
			StringBuilder strBuilder = new StringBuilder(str);
			int index = strBuilder.indexOf(delimiter);
			while(index != -1 && index < strBuilder.length()) {
				if(delimiter.length() == 1) {
					strBuilder.deleteCharAt(index);
				} else {
					strBuilder.delete(index, index + delimiter.length());
				}
				index = strBuilder.indexOf(delimiter);
			}
			str = strBuilder.toString();
		}
		
		if(log.isDebugEnabled()) {
			log.debug("-trim(), str: " + str);
		}
		return str;
	}
	
	/**
	 * Removes the duplicates of the specified string and keeps the position of
	 * the characters.
	 * 
	 * @param string
	 * @return
	 */
	public static String removeDuplidates(String string) {
		StringBuilder uniqueString = new StringBuilder();
		boolean[] checked = new boolean[256];
		for(int i = 0; i < string.length(); i++) {
			char nextChar = string.charAt(i);
			if(!checked[nextChar]) {
				checked[nextChar] = true;
				uniqueString.append(nextChar);
			}
		}
		
		return uniqueString.toString();
	}
	
	/**
	 * Returns true if the string contains a space, otherwise false.
	 * 
	 * @param str
	 *            - to be checked for space.
	 * @return true, if string contains space, otherwise false.
	 */
	public static boolean hasSpace(String str) {
		if(isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}
		
		boolean hasSpace = false;
		for(int index = 0; index < str.length(); index++) {
			if(Character.isSpaceChar(str.charAt(index))) {
				hasSpace = true;
				break;
			}
		}
		return hasSpace;
	}
	
	/**
	 * Check whether the specified string only contains digits including dot(.).
	 * If the specified string is null or empty, it will return false.
	 * 
	 * @param str
	 *            - to be checked.
	 * @return true if the string contains all the digits including dot,
	 *         otherwise false.
	 */
	public static boolean isNumeric(String str) {
		if(isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}
		
		boolean numeric = true;
		for(int index = 0; numeric && index < str.length(); index++) {
			if(!Character.isDigit(str.charAt(index)) && str.charAt(index) != '.') {
				numeric = false;
			}
		}
		return numeric;
	}
	
	/**
	 * Replaces the find string to the with string. It is a multi-purpose method
	 * which can replace a single or multiple characters.
	 * 
	 * @param str
	 * @param find
	 * @param with
	 * @return
	 */
	public static String replace(String str, String find, String with) {
		if(str != null) {
			StringBuilder strBuilder = new StringBuilder(str);
			int index = strBuilder.indexOf(find);
			while(index != -1 && index < strBuilder.length()) {
				strBuilder.replace(index, index + find.length(), with);
				index = strBuilder.indexOf(find);
			}
			str = strBuilder.toString();
		}
		return str;
	}
	
	/**
	 * Returns true if a valid email address is passed. The email address
	 * criteria is: This means an email can start with any combination of
	 * letters and numbers that is followed by any number of periods and letters
	 * and numbers. It must have a @ character followed by a valid host name.
	 * 
	 * Expression Pattern Used:
	 * "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-
	 * ]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b"
	 * 
	 * @param eMailAddress
	 * @return
	 */
	public static boolean isValidEmail(String eMailAddress) {
		Pattern pattern = Pattern.compile(VALID_EMAIL_EXPRESSION);
		Matcher matcher = pattern.matcher(VALID_EMAIL_EXPRESSION);
		return matcher.find();
	}
	
	/**
	 * It returns true if the string matches exactly either "true"/"True" or
	 * "yes"/"Yes".
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTrueOrYes(String str) {
		return str.matches("[tT]rue|[yY]es");
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return (str == null);
	}
	
	/**
	 * Checks whether the given string is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return (isNull(str) || str.isEmpty());
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String str) {
		return (!isNullOrEmpty(str));
	}
	
	/**
	 * Returns the padded string. It fills your string with up to repeat
	 * characters with a whitespace and then all whitespace are replaced with
	 * the provided padding string.
	 * 
	 * @param str
	 * @param repeat
	 * @param padStr
	 * @return
	 */
	public static String padRight(String str, int repeat, String padStr) {
		return String.format("%1$-" + repeat + "s", str).replaceAll(" ", padStr);
	}
	
	/**
	 * Returns the padded string. It fills your string with up to repeat
	 * characters with a whitespace and then all whitespace are replaced with
	 * the provided padding string.
	 * 
	 * @param str
	 * @param repeat
	 * @param padStr
	 * @return
	 */
	public static String padLeft(String str, int repeat, String padStr) {
		return String.format("%1$" + repeat + "s", str).replaceAll(" ", padStr);
	}
	
	/**
	 * Returns the padded string. It fills your string with up to repeat
	 * characters with a whitespace and then all whitespace are replaced with
	 * the provided padding string.
	 * 
	 * @param str
	 * @param repeat
	 * @param padChar
	 * @return
	 */
	public static String padCenter(String str, int repeat, String padChar) {
		if(isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}
		
		if(repeat > 0) {
			int pads = repeat - str.length();
			if(pads > 0) {
				str = padLeft(str, str.length() + pads / 2, padChar);
				str = padRight(str, repeat, padChar);
			}
		}
		return str;
	}
	
	/**
	 * Returns the string of padded characters.
	 * 
	 * @param repeat
	 * @param padChar
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public static String repeat(int repeat, char padChar) throws IndexOutOfBoundsException {
		if(repeat < 0) {
			throw new IndexOutOfBoundsException("Invalid Index!, repeat: " + repeat);
		}
		final char[] cBuffer = new char[repeat];
		for(int i = 0; i < cBuffer.length; i++) {
			cBuffer[i] = padChar;
		}
		return new String(cBuffer);
	}
	
	/**
	 * Returns the string truncated from the start index to end index.
	 * 
	 * @param str
	 * @param start
	 *            The beginning index, inclusive.
	 * @param end
	 *            The ending index, exclusive.
	 * @return
	 */
	public static String truncate(String str, int start, int end) {
		if(isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}
		
		if(start < 0 || start > end || end > str.length()) {
			throw new StringIndexOutOfBoundsException("Invalid Index!, start: " + start + ", endIndex:" + end);
		}
		
		StringBuilder sBuilder = new StringBuilder(str);
		sBuilder.delete(start, end);
		return sBuilder.toString();
	}
	
	/**
	 * Returns the string in sentence case.
	 * 
	 * @param str
	 * @return
	 */
	public static String toSentenceCase(String str) {
		if(isNullOrEmpty(str)) {
			throw new NullPointerException("Invalid Parameter!, str: " + str);
		}
		
		return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toTitleCase(String str) {
		String result = str;
		if(!isNullOrEmpty(str)) {
			result = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toToggleCase(String str) {
		if(log.isDebugEnabled()) {
			log.debug("+toToggleCase(" + str + ")");
		}
		if(isNullOrEmpty(str)) {
			throw new NullPointerException("Invalid Parameter!, str: " + str);
		}
		
		/* remove all unwanted spaces if any. */
		StringBuilder strBuilder = new StringBuilder();
		char ch = 0;
		for(int index = 0; index < str.length(); index++) {
			ch = str.charAt(index);
			if(Character.isUpperCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if(Character.isTitleCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if(Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}
			strBuilder.append(ch);
		}
		
		if(log.isDebugEnabled()) {
			log.debug("-toToggleCase(), result: " + strBuilder.toString());
		}
		return strBuilder.toString();
	}
	
	/**
	 * Returns the string created by joining the specified elements, separated
	 * by using the specified delimiter. if the delimiter is null or empty
	 * default delimiter space is used. if the passed elements array is null,
	 * return null;
	 * 
	 * @param elements
	 * @return
	 */
	public static String join(String[] elements, String delimiter) {
		StringBuilder sBuilder = null;
		if(elements != null) {
			if(isNullOrEmpty(delimiter)) {
				delimiter = STR_SPACE;
			}
			sBuilder = new StringBuilder();
			for(int i = 0; i < elements.length; i++) {
				sBuilder.append(elements[i]);
				if(i < (elements.length - 1)) {
					sBuilder.append(delimiter);
				}
			}
		}
		
		return (sBuilder == null ? null : sBuilder.toString());
	}
	
	/**
	 * Returns the string created by joining the specified elements using the
	 * default space delimiter. if the passed elements array is null, return
	 * null;
	 * 
	 * @param elements
	 * @return
	 */
	public static String join(String[] elements) {
		return join(elements, null);
	}
	
	/**
	 * Splits this string that matches of the given delimiter. if delimiter is
	 * null or empty, space is used as default delimiter
	 * 
	 * @param source
	 * @param delimiter
	 * @return
	 * 
	 * @see java.lang.String#split(String)
	 */
	public static String[] split(String source, String delimiter) {
		String[] elements = null;
		if(!isNullOrEmpty(source)) {
			if(isNullOrEmpty(delimiter)) {
				delimiter = STR_SPACE;
			}
			
			if(".$|()[{^?*+\\".contains(delimiter)) {
				delimiter = "\\" + delimiter;
			}
			elements = source.split(delimiter);
		}
		
		return elements;
	}
	
	/**
	 * Converts a string into an array of strings.
	 * 
	 * @param str
	 * @return returns an array of String
	 */
	public static String[] split(String str, char delimiter) {
		String[] words = null;
		if(!isNullOrEmpty(str)) {
			List<String> segments = new LinkedList<String>();
			char[] chars = str.toCharArray();
			String newStr = null;
			int startIdx = 0;
			for(int i = 0; i < chars.length; i++) {
				if(chars[i] == delimiter) {
					/* ignore extra spaces/delimiter characters */
					if(i == startIdx) {
						startIdx++;
						continue;
					}
					newStr = new String(chars, startIdx, (i - startIdx));
					segments.add(newStr);
					startIdx = i + 1;
				}
			}
			newStr = new String(chars, startIdx, (chars.length - startIdx));
			segments.add(newStr);
			words = toStr(segments);
		}
		return words;
	}
	
	/**
	 * Returns the elements of string separated by default delimiter (space).
	 * 
	 * @param str
	 * @return
	 */
	public static String[] split(String str) {
		return split(str, " ");
	}
	
	/**
	 * Prints elements of string at output stream.
	 * 
	 * @param elements
	 */
	public static void printLine(String... elements) {
		if(elements != null && elements.length > 0) {
			for(String str : elements) {
				System.out.println(str);
			}
		}
	}
	
	/**
	 * Returns a <code>String</code> object that is filled with the number of
	 * specified character. if <code>times <= 0</code>, an empty
	 * <code>String</code> object is returned.
	 * 
	 * @param times
	 * @return
	 */
	public static String fill(char cChar, int times) {
		StringBuilder indentType = new StringBuilder();
		if(times > 0) {
			for(int i = 0; i < times; i++) {
				indentType.append(cChar);
			}
		}
		
		return indentType.toString();
	}
	
	/**
	 * Returns a <code>String</code> object that is filled with the number of
	 * spaces. if <code>times <= 0</code>, an empty <code>String</code> object
	 * is returned.
	 * 
	 * @param times
	 * @return
	 */
	public static String fill(int times) {
		return fill(SPACE_SEPARATOR, times);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static long countWords(String str) {
		if(log.isDebugEnabled()) {
			log.debug("+countWords(" + str + ")");
		}
		
		if(isNullOrEmpty(str)) {
			throw new NullPointerException("Invalid Parameter!, str: " + str);
		}
		
		long words = 0;
		int index = 0;
		boolean whiteSpace = true;
		while(index < str.length()) {
			char cChar = str.charAt(index++);
			// log.debug("cChar:" + cChar);
			boolean isWhitespace = Character.isWhitespace(cChar);
			if(whiteSpace && !isWhitespace) {
				words++;
			}
			whiteSpace = isWhitespace;
		}
		
		if(log.isDebugEnabled()) {
			log.debug("-countWords(), words: " + words);
		}
		return words;
	}
	
	/* optimize the below given method. */
	
	/**
	 * Returns the unique/duplicate set of string elements.
	 * 
	 * @param elements
	 * @param ignoreCase
	 * @param unique
	 * @return
	 */
	public static Set<String> filter(String[] elements, boolean ignoreCase, boolean unique) {
		Set<String> uniques = null;
		Set<String> duplicates = null;
		if(elements != null) {
			uniques = new LinkedHashSet<String>();
			duplicates = new LinkedHashSet<String>();
			for(int i = 0; i < elements.length; i++) {
				String str = elements[i];
				/* remove if the word ends with non-letter character. */
				char cChar = str.charAt(str.length() - 1);
				if(!Character.isLetter(cChar)) {
					str = str.substring(0, elements[i].length() - 1);
				}
				
				if(ignoreCase) {
					str = str.toLowerCase();
				}
				
				if(!uniques.add(str)) {
					duplicates.add(str);
				}
				// if (log.isDebugEnabled()) {
				// log.debug("uniques: " + uniques);
				// log.debug("duplicates: " + duplicates);
				// }
			}
		}
		return (unique ? uniques : duplicates);
	}
	
	/**
	 * Converts an array of objects into an array of strings.
	 * 
	 * @param elements
	 * @return
	 */
	public static String[] toStr(Object... elements) {
		String[] strElements = null;
		if(elements != null) {
			strElements = new String[elements.length];
			for(int i = 0; i < elements.length; i++) {
				if(elements[i] instanceof String) {
					strElements[i] = elements[i].toString();
				}
			}
		}
		return strElements;
	}
	
	/**
	 * Converts the collection into an array of strings.
	 * 
	 * @param elements
	 * @return
	 */
	public static String[] toStr(Collection<String> elements) {
		String[] strElements = null;
		if(elements != null) {
			strElements = new String[elements.size()];
			Object[] oElements = elements.toArray();
			for(int i = 0; i < oElements.length; i++) {
				strElements[i] = oElements[i].toString();
			}
		}
		return strElements;
	}
	
	/**
	 * Returns the string of unique words. if the ignoreCase is true, the word
	 * is considered unique, if any of the letter of the word in different case.
	 * 
	 * @param elements
	 * @param ignoreCase
	 * @return
	 */
	public static String findUnique(String[] elements, boolean ignoreCase) {
		Set<String> uniques = filter(elements, ignoreCase, true);
		return join(toStr(uniques));
	}
	
	/**
	 * Returns the string of unique words without ignoring by case.
	 * 
	 * Find Unique Words in a String
	 * 
	 * @param elements
	 * @return
	 */
	public static String findUnique(String[] elements) {
		return findUnique(elements, false);
	}
	
	/**
	 * Returns the string of duplicate words. if the ignoreCase is true, the
	 * word is considered unique, if any of the letter of the word in different
	 * case.
	 * 
	 * @param elements
	 * @param ignoreCase
	 * @return
	 */
	public static String findDuplicate(String[] elements, boolean ignoreCase) {
		Set<String> duplicates = filter(elements, ignoreCase, false);
		return join(toStr(duplicates));
	}
	
	/**
	 * Returns the string of duplicate words.
	 * 
	 * @param elements
	 * @return
	 */
	public static String findDuplicate(String[] elements) {
		return findDuplicate(elements, false);
	}
	
	/**
	 * Converts the specified string into boolean value. if the specified string
	 * is null or empty, it returns false.
	 * 
	 * @param value
	 * @param bDefault
	 * @return
	 */
	public static boolean toBoolean(String value) {
		boolean result = false;
		if(!isNullOrEmpty(value)) {
			result = Boolean.valueOf(value.trim()).booleanValue();
		}
		return result;
	}
	
	/**
	 * Converts the specified string into integer value. if the specified string
	 * is null or empty, it returns -1.
	 * 
	 * @param value
	 * @return
	 */
	public static int toInteger(String value) {
		int result = -1;
		if(!isNullOrEmpty(value)) {
			try {
				result = Integer.parseInt(value);
			} catch(NumberFormatException nfe) {
				log.error("Error parsing value: " + value, nfe);
			}
		}
		return result;
	}
	
	/**
	 * Converts the specified string into long value. if the specified string is
	 * null or empty, it returns -1.
	 * 
	 * @param value
	 * @return
	 */
	public static long toLong(String value) {
		long result = -1L;
		if(!isNullOrEmpty(value)) {
			try {
				result = Long.parseLong(value);
			} catch(NumberFormatException nfe) {
				log.error("Error parsing value: " + value, nfe);
			}
		}
		return result;
	}
	
	/**
	 * Tests the number of words in the string provided by user.
	 * 
	 * @param active
	 */
	public static void printWords(int number, boolean active) {
		if(active) {
			int num = 0;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter a Number : ");
			try {
				num = Integer.parseInt(in.readLine());
			} catch(IOException ioe) {
				System.out.println("Exception : " + ioe.toString());
			}
			if(num != -1) {
				number = num;
			}
		}
		System.out.println("Num : " + number);
		System.out.println("Size : " + numbersToWords(number));
	}
	
	/**
	 * Converts a number less than 100 to it's equivalent word(s)
	 * 
	 * @param num
	 * @return
	 */
	public static String onesConverter(int num) {
		String[] Units = {
				" ",
				"One",
				"Two",
				"Three",
				"Four",
				"Five",
				"Six",
				"Seven",
				"Eight",
				"Nine" };
		String[] Tens = {
				" ",
				"Eleven",
				"Twelve",
				"Thirteen",
				"Fourteen",
				"Fifteen",
				"Sixteen",
				"Seventeen",
				"Eighteen",
				"Nineteen" };
		String[] Hundreds = {
				" ",
				"Ten",
				"Twenty",
				"Thirty",
				"Fourty",
				"Fifty",
				"Sixty",
				"Seventy",
				"Eighty",
				"Ninety" };
		System.out.println("onesConverter Num : " + num);
		String str = " ";
		if(num < 10) {
			str = Units[num];
		} else if(num < 20) {
			str = Tens[num % 10];
		} else if(num < 100) {
			System.out.println("Hundreds[num] : " + Hundreds[num / 10] + " " + Units[num % 10]);
			str = Hundreds[num / 10] + " " + Units[num % 10];
		}
		System.out.println("onesConverter str : " + str);
		return str;
	}
	
	/**
	 * Converts a number less than 1000 to it's equivalent word(s)
	 * 
	 * @param num
	 * @return
	 */
	public static String twosConverter(int num) {
		System.out.println("twosConverter Num : " + num);
		String str = " ";
		System.out.println("(num / 100) : " + (num / 100));
		if((num / 100) > 0) {
			str += onesConverter(num / 100) + " Hundred ";
		}
		System.out.println("(num % 100) : " + (num % 100));
		str += onesConverter(num % 100);
		System.out.println("twosConverter str : " + str);
		return str;
	}
	
	/**
	 * Converts a number less than 10000 to it's equivalent word(s)
	 * 
	 * @param num
	 * @return
	 */
	public static String threesConverter(int num) {
		System.out.println("threesConverter Num : " + num);
		String str = " ";
		System.out.println("(num / 1000) : " + (num / 1000));
		if((num / 1000) > 0) {
			str += twosConverter(num / 1000) + " " + Prefixes[num / 1000];
			System.out.println("threesConverter str : " + str);
		}
		System.out.println("(num % 1000) : " + (num % 1000));
		str += twosConverter(num % 1000);
		System.out.println("threesConverter str : " + str);
		return str;
	}
	
	public static String numbersToWords(int num) {
		System.out.println("numerToWord Num : " + num);
		String str = "";
		int ctr = 0;
		if(num > 0) {
			System.out.println("numerToWord (num % 1000) : " + (num % 1000));
			while(ctr < Prefixes.length && num > 0) {
				str += Prefixes[ctr] + threesConverter(num % 1000) + Prefixes[ctr];
				System.out.println("numerToWord str : " + str);
				System.out.println("numerToWord Before Dividing Num : " + num);
				num = num / 1000;
				System.out.println("numerToWord After Dividing Num : " + num);
				ctr++;
			}
		}
		return str;
	}
	
	static String[] Prefixes = {
			" ",
			"Thousand",
			"Lakhs",
			"Million" };
	
	/**
	 * Returns the reversed string using recursion.
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		return (isNullOrEmpty(str) ? "" : reverse(str.substring(1)) + str.substring(0, 1));
	}
	
	/** HEX_DIGIT_CHARS */
	private static final String HEX_DIGIT_CHARS = "0123456789abcdef";
	/** NEWLINE */
	public static final String NEWLINE = "\n";
	
	/**
	 * Returns the value extracted from the argument separated by delimiter.
	 * 
	 * @param argument
	 * @param delimiter
	 * @return
	 */
	public static String getArgValue(String argument, String delimiter) {
		return (isNullOrEmpty(argument) ? null : argument.split(delimiter)[1]);
	}
	
	/**
	 * Convert a byte array to a hex string of the format "1f 30 b7".
	 */
	public static String byteArrayToHex(byte[] a) {
		int hn, ln, cx;
		StringBuffer buf = new StringBuffer(a.length * 2);
		for(cx = 0; cx < a.length; cx++) {
			hn = ((int) (a[cx]) & 0x00ff) / 16;
			ln = ((int) (a[cx]) & 0x000f);
			buf.append(HEX_DIGIT_CHARS.charAt(hn));
			buf.append(HEX_DIGIT_CHARS.charAt(ln));
		}
		return buf.toString();
	}
	
	/**
	 * Convert a hex string into an array of bytes. The hex string can be all
	 * digits, or 1-octet groups separated by blanks, or any mix thereof.
	 * 
	 * @param str
	 *            String to be converted
	 */
	public static byte[] hexToByteArray(String str) {
		StringBuffer acc = new StringBuffer(str.length() + 1);
		int cx, rp, ff, val;
		char[] s = new char[str.length()];
		str.toLowerCase().getChars(0, str.length(), s, 0);
		for(cx = str.length() - 1, ff = 0; cx >= 0; cx--) {
			if(HEX_DIGIT_CHARS.indexOf(s[cx]) >= 0) {
				acc.append(s[cx]);
				ff++;
			} else {
				if((ff % 2) > 0) {
					acc.append('0');
				}
				ff = 0;
			}
		}
		if((ff % 2) > 0) {
			acc.append('0');
		}
		
		byte[] ret = new byte[acc.length() / 2];
		for(cx = 0, rp = ret.length - 1; cx < acc.length(); cx++, rp--) {
			val = HEX_DIGIT_CHARS.indexOf(acc.charAt(cx));
			cx++;
			val += 16 * HEX_DIGIT_CHARS.indexOf(acc.charAt(cx));
			ret[rp] = (byte) val;
		}
		
		return ret;
	}
	
	/**
	 * Remove the FONT FACE attribute from an html text so that client can
	 * render the current selected font face for this string.
	 * 
	 * @param str
	 *            String to be converted
	 */
	
	public static String removeAttribFontFace(String str, String fontName) {
		if(!isNullOrEmpty(str)) {
			if(fontName != null && fontName.equalsIgnoreCase("comic")) {
				fontName = "Comic Sans MS";
			}
			
			if(fontName != null && fontName != "") {
				str = replace(str, "mainFont", fontName);
			}
			str = replace(str, "face=_", "face=");
		}
		return str;
	}
	
	/**
	 * Encode string into javascript safe. This is to avoid issue such as single
	 * quote, where it would cause errors on the client side.
	 * 
	 * @param textString
	 * @return
	 */
	public static String javaScriptEncode(String textString) {
		final StringBuffer result = new StringBuffer();
		final StringCharacterIterator iterator = new StringCharacterIterator(textString);
		char character = iterator.current();
		while(character != StringCharacterIterator.DONE) {
			if(character == '<') {
				result.append("&lt;");
			} else if(character == '>') {
				result.append("&gt;");
			} else if(character == '\"') {
				result.append("\\\"");
			} else if(character == '\'') {
				result.append("\\\'");
			} else if(character == '\\') {
				result.append("&#092;");
			} else if(character == '&') {
				result.append("&amp;");
			} else {
				/* the char is not a special one add it to the result as is. */
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}
	
	public static String htmlEncode(String aTagFragment) {
		if(isNullOrEmpty(aTagFragment)) {
			return aTagFragment;
		}
		
		final StringBuffer result = new StringBuffer();
		
		final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
		char character = iterator.current();
		while(character != StringCharacterIterator.DONE) {
			if(character == '<') {
				result.append("&lt;");
			} else if(character == '>') {
				result.append("&gt;");
			} else if(character == '\"') {
				result.append("&quot;");
			} else if(character == '\'') {
				result.append("&#039;");
			} else if(character == '\\') {
				result.append("&#092;");
			} else if(character == '&') {
				result.append("&amp;");
			} else {
				/* the char is not a special one add it to the result as is. */
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}// end htmlEncode
	
	public static String htmlDecode(String str) {
		if(!isNullOrEmpty(str)) {
			str = str.replaceAll("&amp;", "&");
			str = str.replaceAll("&quot;", "\"");
			str = str.replaceAll("&#039;", "\'");
			str = str.replaceAll("&#092;", "\\\\");
			str = str.replaceAll("&lt;", "<");
			str = str.replaceAll("&gt;", ">");
			str = str.replaceAll("&nbsp;", " ");
			str = str.replaceAll("&apos;", "'");
		}
		return str;
	}
	
	public static int getIntValue(String value) throws NumberFormatException {
		return Integer.parseInt(value);
	}
	
	/**
	 * returns stack as string
	 * 
	 * @param th
	 * @return
	 * @throws Exception
	 */
	public static String getErrorAsString(Throwable th) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(out);
		th.printStackTrace(writer);
		writer.flush();
		writer.close();
		out.close();
		return new String(out.toByteArray());
		
	}
	
	/**
	 * Returns true if either the string is null or empty otherwise false.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmptyOrNull(String str) {
		return (null == str || str.trim().length() == 0 || "null".equalsIgnoreCase(str));
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String... str) {
		return (null == str || str.length == 0);
	}
	
	/**
	 * Returns true if either the string is null or empty or contains null or
	 * undefined as value otherwise false.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmptyOrUndefined(String str) {
		return (isNullOrEmpty(str) || "undefined".equalsIgnoreCase(str));
	}
	
	/**
	 * 
	 * @param patternStr
	 * @param inputStr
	 * @param replacementStr
	 * @return
	 */
	public static String replaceAll(String patternStr, String inputStr, String replacementStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.replaceAll(replacementStr);
	}
	
	/**
	 * filter out the special characters and just keep the alpha-numeric
	 * characters
	 * 
	 * @param inputStr
	 * @return
	 */
	public static String filterSpecialCharacters(String inputStr) {
		return replaceAll("([^a-zA-Z0-9\\s]+)", inputStr, "");
	}
	
	public static String replaceSpecialCharacters(String inputStr) {
		return replaceAll("([^a-zA-Z0-9\\.\\s]+)", inputStr, "_");
	}
	
	public static String getAlphaNumericStringWithouSpace(String inputStr) {
		return replaceAll("([^a-zA-Z0-9])", inputStr, "");
	}
	
	/**
	 * Remove the duplicate white spaces. All whitespace characters are replaced
	 * with a single space. Line terminators, tabs are treated like whitespace.
	 * 
	 * @param inputStr
	 * @return
	 */
	public static String removeDuplicateWhitespace(String inputStr) {
		return replaceAll("\\s+", inputStr, " ");
	}
	
	/**
	 * Capitalizes all the delimiter separated words in a String. All characters
	 * will be converted to lower case first prior to the conversion.
	 * 
	 * @see org.apache.commons.lang.WordUtils
	 * @param str
	 * @param delimiters
	 * @return
	 */
	public static String capitalize(String str, char[] delimiters) {
		if(str == null || str.length() == 0) {
			return str;
		}
		
		str = str.toLowerCase();
		int strLen = str.length();
		StringBuffer buffer = new StringBuffer(strLen);
		
		int delimitersLen = 0;
		if(delimiters != null) {
			delimitersLen = delimiters.length;
		}
		
		boolean capitalizeNext = true;
		for(int i = 0; i < strLen; i++) {
			char ch = str.charAt(i);
			
			boolean isDelimiter = false;
			if(delimiters == null) {
				isDelimiter = Character.isWhitespace(ch);
			} else {
				for(int j = 0; j < delimitersLen; j++) {
					if(ch == delimiters[j]) {
						isDelimiter = true;
						break;
					}
				}
			}
			
			if(isDelimiter) {
				buffer.append(ch);
				capitalizeNext = true;
			} else if(capitalizeNext) {
				buffer.append(Character.toTitleCase(ch));
				capitalizeNext = false;
			} else {
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}
	
	/**
	 * Needed by legacy Hana code (Vault-related), this was brought from Hana's
	 * BVStringUtils class.
	 * 
	 * @param str
	 * @return
	 */
	public static String XSLString(String value) throws Exception {
		if(value == null) {
			value = "";
		}
		StringBuffer buffer = new StringBuffer();
		try {
			value = new String(value.getBytes("UTF-8"), "UTF-8");
			for(int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				int type = Character.getType(c);
				if(type == 15) {
					buffer.append(" ");
				} else {
					switch(c) {
						case '&':
							buffer.append("&#38;");
							break;
						case '!':
							buffer.append("&#33;");
							break;
						case '"':
							buffer.append("&#34;");
							break;
						case '%':
							buffer.append("&#37;");
							break;
						case '\'':
							buffer.append("&#39;");
							break;
						case '(':
							buffer.append("&#40;");
							break;
						case ')':
							buffer.append("&#41;");
							break;
						case '*':
							buffer.append("&#42;");
							break;
						case '+':
							buffer.append("&#43;");
							break;
						case '/':
							buffer.append("&#47;");
							break;
						case '<':
							buffer.append("&#60;");
							break;
						case '>':
							buffer.append("&#62;");
							break;
						case '?':
							buffer.append("&#63;");
							break;
						case '@':
							buffer.append("&#64;");
							break;
						case '[':
							buffer.append("&#91;");
							break;
						case ']':
							buffer.append("&#93;");
							break;
						case '^':
							buffer.append("&#94;");
							break;
						case '|':
							buffer.append("&#124;");
							break;
						case '~':
							buffer.append("&#126;");
							break;
						default:
							buffer.append(c);
					}
				}
			}
			value = buffer.toString();
			buffer = null;
		} finally {
			buffer = null;
		}
		return value;
	}
	
	public static String cleanHtml(String str) {
		if(str != null && str.length() > 0) {
			str = str.replaceAll("&nbsp;", " ");
			str = str.replaceAll("&amp;", "&");
		}
		
		return str;
	}
	
	public static String deleteHTMLTags(String htmlStr) {
		if(htmlStr != null && htmlStr.length() > 0) {
			return htmlStr.replaceAll("\\<.*?\\>", "");
		}
		return htmlStr;
	}
	
	public static String replaceCommaWithSpace(String str) {
		if(str != null && str.length() > 0) {
			str = str.replaceAll(",", " ");
		}
		return str;
	}
	
	public static String replaceExtension(String filename) {
		if(filename != null && filename.indexOf(".") != -1) {
			String ext = filename.substring(filename.lastIndexOf("."));
			if(!ext.equalsIgnoreCase(".pdf")) {
				filename = filename.substring(0, filename.lastIndexOf("."));
				filename += ".pdf";
			}
		}
		
		return filename;
	}
	
	public static String replaceExtension(String fileName, String newExtension) {
		if(!isNullOrEmpty(fileName) && fileName.lastIndexOf(".") != -1 && !isNullOrEmpty(newExtension)) {
			int index = fileName.lastIndexOf(".");
			if(index != -1 && !newExtension.equalsIgnoreCase(fileName.substring(index + 1))) {
				fileName = fileName.substring(0, index + 1) + newExtension;
			}
		}
		
		return fileName;
	}
	
	/**
	 * Returns the decoded value using UTF-8.
	 * 
	 * @param encodedURL
	 * @return
	 */
	public static String decode(String encodedURL) {
		try {
			return URLDecoder.decode(encodedURL, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void embedFLV(PrintWriter writer, String url, String versionUuid, String height) {
		writer.append("<html>");
		writer.append("<head></head>");
		writer.append("<body>");
		writer.append("<div id='container'></div>");
		writer.append("<embed src='/portlet/player.swf' width='100%' height='" + height + "' allowscriptaccess='samedomain' allowfullscreen='true' flashvars='file=" + url + "/services/contentviewer/%3FversionUuid=" + versionUuid + "%26name=123.flv'></embed>");
		writer.append("</body>");
		writer.append("</html>");
	}
	
	/**
	 * Generates Password randomly
	 * 
	 * @return
	 */
	public static String autoGeneratePassword() {
		String DIGITS = "0123456789";
		char[] availableChars = DIGITS.toCharArray();
		Random generator = new Random();
		int availableCharsLeft = availableChars.length;
		
		StringBuffer temp = new StringBuffer(6);
		for(int i = 0; i < 6; i++) {
			int pos = (int) (availableCharsLeft * generator.nextDouble());
			temp.append(availableChars[pos]);
			availableChars[pos] = availableChars[availableCharsLeft - 1];
			--availableCharsLeft;
		}
		
		return String.valueOf(temp);
	}
	
	public static String Replace(String content, String oldWord, String newWord) {
		String tempString = content;
		int position = tempString.toLowerCase().indexOf(oldWord.toLowerCase());
		while(position > -1) {
			tempString = tempString.substring(0, position) + newWord + tempString.substring(position + oldWord.length());
			position = tempString.toLowerCase().indexOf(oldWord.toLowerCase(), position + newWord.length());
		}
		return tempString;
	}
	
	public static String replaceNotSupportCharWithDash(String strToBeReplaced) {
		if(isNullOrEmpty(strToBeReplaced)) {
			return strToBeReplaced;
		}
		
		String specialChar = "\\/:*?<>|" + '"' + "\n\r\t";
		for(int j = 0; j < specialChar.length(); j++) {
			strToBeReplaced = strToBeReplaced.replace(specialChar.charAt(j), '_');
		}
		return strToBeReplaced;
	}
	
	public static String escapeSQLCharacters(String input) {
		return (isNullOrEmpty(input) ? null : input.replaceAll("'", "''"));
	}
	
	public static String replaceHtmlDecodeWithSpace(String str) {
		if(str != null && !str.equalsIgnoreCase("")) {
			str = Replace(str, "&amp;", " ");
			str = Replace(str, "&quot;", " ");
			str = Replace(str, "&#39;", " ");
			str = Replace(str, "&lt;", " ");
			str = Replace(str, "&gt;", " ");
			str = Replace(str, "&nbsp;", " ");
		}
		
		return str;
	}
	
	/**
	 * Replace special characters with XML escapes:
	 * 
	 * <pre>
	 * &amp; <small>(ampersand)</small> is replaced by &amp;amp;
	 * &lt; <small>(less than)</small> is replaced by &amp;lt;
	 * &gt; <small>(greater than)</small> is replaced by &amp;gt;
	 * &quot; <small>(double quote)</small> is replaced by &amp;quot;
	 * &apos; <small>(apostrophe)</small> is replaced by  &amp;apos;
	 * = <small>(equal to)</small> is replaced by  \u003d;
	 * </pre>
	 * 
	 * @param string
	 * @return
	 */
	public static String escape(String string) {
		StringBuilder escapeBuilder = new StringBuilder(string.length());
		for(int i = 0, length = string.length(); i < length; i++) {
			char character = string.charAt(i);
			switch(character) {
				case '&':
					escapeBuilder.append("&amp;");
					break;
				case '<':
					escapeBuilder.append("&lt;");
					break;
				case '>':
					escapeBuilder.append("&gt;");
					break;
				case '"':
					escapeBuilder.append("&quot;");
					break;
				case '\'':
					escapeBuilder.append("&apos;");
					break;
				case '=':
					escapeBuilder.append("\u003d");
					break;
				default:
					escapeBuilder.append(character);
			}
		}
		
		return escapeBuilder.toString();
	}
	
	/**
	 * Replace special characters with XML escapes:
	 * 
	 * <pre>
	 * &amp; <small>(ampersand)</small> is replaced by &amp;amp;
	 * &lt; <small>(less than)</small> is replaced by &amp;lt;
	 * &gt; <small>(greater than)</small> is replaced by &amp;gt;
	 * &quot; <small>(double quote)</small> is replaced by &amp;quot;
	 * &apos; <small>(apostrophe)</small> is replaced by  &amp;apos;
	 * = <small>(equal to)</small> is replaced by  \u003d;
	 * </pre>
	 * 
	 * @param string
	 * @return
	 */
	public static String unescape(String string) {
		StringBuilder stringBuilder = new StringBuilder(string.length());
		String entity = null;
		for(int index = 0, length = string.length(); index < length; index++) {
			char character = string.charAt(index);
			switch(character) {
				case '&':
					entity = string.substring(index);
					if(entity.startsWith("&amp;")) {
						stringBuilder.append("&");
						index += "&amp;".length() - 1;
					} else if(entity.startsWith("&lt;")) {
						stringBuilder.append("<");
						index += "&lt;".length() - 1;
					} else if(entity.startsWith("&gt;")) {
						stringBuilder.append(">");
						index += "&gt;".length() - 1;
					} else if(entity.startsWith("&quot;")) {
						stringBuilder.append("\"");
						index += "&quot;".length() - 1;
					} else if(entity.startsWith("&apos;")) {
						stringBuilder.append("\'");
						index += "&apos;".length() - 1;
					} else {
						stringBuilder.append(character);
					}
					break;
				case '\\':
					entity = string.substring(index);
					if(entity.startsWith("\u003d")) {
						stringBuilder.append("=");
						index += "\u003d".length() - 1;
					} else {
						stringBuilder.append(character);
					}
					break;
				default:
					stringBuilder.append(character);
					break;
			}
		}
		
		return stringBuilder.toString();
	}
	
	public static String generateActivationCode() {
		// int code = new Random().nextInt(899999) + 100000;
		int code = new Random().nextInt(8999) + 1000;
		return code + "";
	}
	
	public static String getUTF8String(String str) {
		String utf8Str;
		try {
			utf8Str = new String(str.getBytes(), "utf-8");
		} catch(UnsupportedEncodingException e) {
			utf8Str = str;
			e.printStackTrace();
		}
		
		return utf8Str;
	}
	
	public static String toString(String... args) {
		StringBuilder sBuilder = new StringBuilder();
		if(args != null && args.length > 0) {
			for(int i = 0; i < args.length; i++) {
				sBuilder.append(args[i]);
				if(i < (args.length - 1)) {
					sBuilder.append(",");
				}
			}
		}
		
		return sBuilder.toString();
	}
	
	public static String replaceHTMLFontSize(String htmlText, int size) {
		return (isNullOrEmpty(htmlText) ? htmlText : replaceAll("(?i)(font[^/>]*size=(?:\"|\'))(\\d{1,3})", htmlText, ("$1" + size)));
	}
	
	public static String getOnlyNumerics(String str) {
		if(isNullOrEmpty(str)) {
			return str;
		}
		
		StringBuffer strBuff = new StringBuffer();
		char c;
		for(int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if(Character.isDigit(c)) {
				strBuff.append(c);
			}
		}
		return strBuff.toString();
	}
	
	public static String indentIt(String input, int spaces) {
		StringBuffer sbResult = new StringBuffer();
		String[] lines = input.split(NEWLINE);
		for(String line : lines) {
			for(int i = 0; i < spaces; i++) {
				sbResult.append(" ");
			}
			sbResult.append(line).append(NEWLINE);
		}
		
		return sbResult.toString();
	}
	
	public static String indentItOneLine(String input, int spaces) {
		StringBuilder sbResult = new StringBuilder();
		for(int i = 0; i < spaces; i++) {
			sbResult.append(" ");
		}
		sbResult.append(input);
		
		return sbResult.toString();
	}
	
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	public static boolean isValidUuid(String uuid) {
		return (!isNullOrEmpty(uuid) && uuid.contains(":") && uuid.contains("-"));
	}
	
	/**
	 * 
	 * @param args
	 * @return
	 */
	public static String concat(String... args) {
		if(args != null) {
			StringBuilder sBuilder = new StringBuilder();
			for(int i = 0; i < args.length; i++) {
				sBuilder.append(args[i]);
			}
			
			return sBuilder.toString();
		}
		
		return null;
	}
	
	/**
	 * Returns true if the specified string matches with any of the specified
	 * arguments otherwise false.
	 * 
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean equals(String string, String... args) {
		if(!isNullOrEmpty(string) && args != null) {
			for(int i = 0; i < args.length; i++) {
				if(string.equals(args[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if the specified string matches ignoring case with any of
	 * the specified arguments otherwise false.
	 * 
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean equalsIgnoreCase(String string, String... args) {
		if(!isNullOrEmpty(string) && args != null) {
			for(int i = 0; i < args.length; i++) {
				if(string.equalsIgnoreCase(args[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static String removeExtension(String fileName) {
		if(!isNullOrEmpty(fileName)) {
			int index = fileName.lastIndexOf(".");
			if(index != -1) {
				return fileName.substring(0, index);
			}
		}
		
		return fileName;
	}
	
	public static String toString(Throwable th) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PrintWriter writer = new PrintWriter(out);
			th.printStackTrace(writer);
			writer.flush();
			writer.close();
			out.close();
		} catch(Exception ex) {
			System.err.println(ex);
		}
		
		return new String(out.toByteArray());
	}
	
	public static String trimSpaces(String text) {
		return (isNullOrEmpty(text) ? text : text.trim());
	}
	
	/**
	 * 
	 * @param classes
	 * @param delimiter
	 * @return
	 */
	public static Class<?>[] getClassArray(String classes, String delimiter) {
		try {
			if(classes != null && classes.trim().length() > 0) {
				String[] strClassArray = classes.split(delimiter);
				if(strClassArray != null) {
					Class<?>[] classArray = new Class[strClassArray.length];
					for(int i = 0; i < strClassArray.length; i++) {
						classArray[i] = Class.forName(strClassArray[i]);
					}
					return classArray;
				}
			}
		} catch(Exception ex) {
			System.err.println(ex);
		}
		return null;
	}
	
	public static String safeSubstring(String string, int beginIndex, int endIndex) {
		if(string == null) {
			return "null";
		}
		
		if(string.length() < endIndex) {
			return string;
		} else {
			return string.substring(beginIndex, endIndex);
		}
	}
	
	/**
	 * Returns the first 20 characters of the string, if its length is > 20.
	 * 
	 * @param string
	 * @return
	 */
	public static String safeSubstring(String str) {
		return safeSubstring(str, 0, 20);
	}
	
	/**
	 * Returns true if the value starts with any of the specified prefixes
	 * otherwise false.
	 * 
	 * @param value
	 * @param prefixes
	 * @return
	 */
	public static boolean startsWith(String value, String... prefixes) {
		boolean result = false;
		if(!isNullOrEmpty(value) && prefixes != null) {
			for(String prefix : prefixes) {
				if(value.startsWith(prefix)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Returns true if the value contains '\n' (new line) character otherwise
	 * false.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean containsNewLine(String value) {
		return (!isNullOrEmpty(value) && value.contains("\n".intern()));
	}
	
	/**
	 * Returns the index of the string within the elements.
	 * 
	 * @param elements
	 * @param str
	 * @return
	 */
	public static int getIndexOf(String[] elements, String str) {
		int index = -1;
		if(!isNullOrEmpty(elements) && !isNullOrEmpty(str)) {
			for(int i = 0; i < elements.length; i++) {
				if(elements[i].equals(str)) {
					index = i;
					break;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Returns the latest version from the version list. For example: if your
	 * versions list contains the following values:
	 * <code>[1.0, 2.0, 3.0, 1.0.0, 1.1.1, 1.0.0.1, 2.0.1, 1.0.1]</code>, then
	 * it will return the <code>3.0</code> as latest version. If the version
	 * list is null or empty, it returns empty string;
	 * 
	 * @param versions
	 * @return
	 */
	public static String getLatestVersion(List<String> versions) {
		String latestVersion = "";
		if(versions != null && !versions.isEmpty()) {
			Collections.sort(versions, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			latestVersion = (versions.get(versions.size() - 1));
		}
		
		return latestVersion;
	}
	
	/**
	 * Returns true if the specified string matches with any of the specified
	 * arguments otherwise false.
	 * 
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean areEquals(String string, String... args) {
		if(!isNullOrEmpty(string) && args != null) {
			for(int i = 0; i < args.length; i++) {
				if(string.equals(args[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if the newVersion is greater than oldVersion otherwise
	 * false.
	 * 
	 * @param oldVersion
	 * @param newVersion
	 * @return
	 */
	public static boolean isNewVersion(String oldVersion, String newVersion) {
		return (!isNullOrEmpty(newVersion) && newVersion.compareTo(oldVersion) > 0);
	}
	
	/**
	 * Converts the bytes into the StringBuilder. If bytes array is null or
	 * empty, empty StringBuilder is returned.
	 * 
	 * @param bytes
	 * @return
	 */
	public static StringBuilder bytesAsStringBuilder(byte... bytes) {
		StringBuilder strBuilder = new StringBuilder();
		if(!ObjectHelper.isNullOrEmpty(bytes)) {
			try {
				strBuilder = new StringBuilder(new String(bytes, "UTF-8"));
			} catch(UnsupportedEncodingException ex) {
				System.err.println(ex);
			}
		}
		
		return strBuilder;
	}
	
	/**
	 * Returns the full host name extracted from the specified URL string.
	 * 
	 * @param urlString
	 * @return
	 */
	public static String extractHostNameFromURLString(String urlString) {
		System.out.println("+extractHostNameFromURLString(" + urlString + ")");
		String hostName = null;
		if(!isNullOrEmpty(urlString)) {
			int startIndex = urlString.indexOf("://");
			if(startIndex != -1) {
				startIndex += "://".length();
				int endIndex = urlString.lastIndexOf(":");
				System.out.println("startIndex:" + startIndex + ", endIndex:" + endIndex);
				hostName = (endIndex <= startIndex) ? urlString.substring(startIndex) : urlString.substring(startIndex, endIndex);
			}
		}
		
		System.out.println("-extractHostNameFromURLString(), hostName:" + hostName);
		return hostName;
	}
	
	/**
	 * Validates and returns the valid CHARSET for the given charsetName.
	 * 
	 * @param charsetName
	 * @return
	 */
	public static String emptyString(String string) {
		return (string = Constants.EMPTY_STRING);
	}
	
	/**
	 * Returns the filename from the specified fullPath, if its not null or
	 * empty otherwise null.
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getFileName(String fullPath, boolean withExtension) {
		String fileName = null;
		if(!isNullOrEmpty(fullPath)) {
			int pathSeparatorIndex = fullPath.lastIndexOf(File.separator);
			if(pathSeparatorIndex < fullPath.length() - 1) {
				fileName = fullPath.substring(pathSeparatorIndex + 1);
				if(!withExtension) {
					int dotIndex = fileName.lastIndexOf(".");
					fileName = ((dotIndex > -1 && dotIndex < fileName.length() - 1) ? fileName.substring(0, dotIndex) : fileName);
				}
			}
		}
		
		return fileName;
	}
	
}