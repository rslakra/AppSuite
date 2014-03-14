/******************************************************************************
 * Copyright (C) Devamatre Technologies 2009
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code or the 
 * snippet is not permitted without prior express written consent of Devamatre. 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the license is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied and the 
 * offenders will be liable for any damages. All rights, including  but not
 * limited to rights created by patent grant or registration of a utility model 
 * or design, are reserved. Technical specifications and features are binding 
 * only insofar as they are specifically and expressly agreed upon in a written 
 * contract.
 * 
 * You may obtain a copy of the License for more details at:
 *      http://www.devamatre.com/licenses/license.txt.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.devamatre.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

/**
 * StringUtils.java
 * 
 * The <code>StringUtils</code> TODO Define Purpose here
 * 
 * @author Rohtash Singh (rohtash.singh@devamatre.com)
 * @created 01/08/2010 (dd/mm/yyyy)
 * @version 1.0.0
 * @since 1.0.0
 */
public final class StringUtil implements Serializable {

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
	private static Logger log = LogManager.getLogger(StringUtil.class);

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
		if (log.isDebugEnabled()) {
			log.debug("+trim(" + str + ", " + delimiter + ")");
		}
		if (str != null && delimiter != null) {
			StringBuilder strBuilder = new StringBuilder(str);
			int index = strBuilder.indexOf(delimiter);
			while (index != -1 && index < strBuilder.length()) {
				if (delimiter.length() == 1) {
					strBuilder.deleteCharAt(index);
				} else {
					strBuilder.delete(index, index + delimiter.length());
				}
				index = strBuilder.indexOf(delimiter);
			}
			str = strBuilder.toString();
		}

		if (log.isDebugEnabled()) {
			log.debug("-trim(), str: " + str);
		}
		return str;
	}

	/**
	 * Returns true if the string contains a space, otherwise false.
	 * 
	 * @param str
	 *            - to be checked for space.
	 * @return true, if string contains space, otherwise false.
	 */
	public static boolean hasSpace(String str) {
		if (isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}

		boolean hasSpace = false;
		for (int index = 0; index < str.length(); index++) {
			if (Character.isSpaceChar(str.charAt(index))) {
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
		if (isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}

		boolean numeric = true;
		for (int index = 0; numeric && index < str.length(); index++) {
			if (!Character.isDigit(str.charAt(index))
					&& str.charAt(index) != '.') {
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
		if (str != null) {
			StringBuilder strBuilder = new StringBuilder(str);
			int index = strBuilder.indexOf(find);
			while (index != -1 && index < strBuilder.length()) {
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
	 * Checks whether the given string is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
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
		return String.format("%1$-" + repeat + "s", str)
				.replaceAll(" ", padStr);
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
		return String.format("%1$" + repeat + "s", str)
				.replaceAll(" ", padStr);
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
		if (isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}

		if (repeat > 0) {
			int pads = repeat - str.length();
			if (pads > 0) {
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
	public static String repeat(int repeat, char padChar)
			throws IndexOutOfBoundsException {
		if (repeat < 0) {
			throw new IndexOutOfBoundsException("Invalid Index!, repeat: "
					+ repeat);
		}
		final char[] cBuffer = new char[repeat];
		for (int i = 0; i < cBuffer.length; i++) {
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
		if (isNullOrEmpty(str)) {
			throw new IllegalArgumentException("Invalid String!, str: " + str);
		}

		if (start < 0 || start > end || end > str.length()) {
			throw new StringIndexOutOfBoundsException("Invalid Index!, start: "
					+ start + ", endIndex:" + end);
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
		if (isNullOrEmpty(str)) {
			throw new NullPointerException("Invalid Parameter!, str: " + str);
		}

		return new StringBuilder().append(Character.toUpperCase(str.charAt(0)))
				.append(str.substring(1)).toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toTitleCase(String str) {
		if (log.isDebugEnabled()) {
			log.debug("+toTitleCase(" + str + ")");
		}
		if (isNullOrEmpty(str)) {
			throw new NullPointerException("Invalid Parameter!, str: " + str);
		}

		StringBuilder strBuilder = new StringBuilder();
		String[] words = str.split(" ");
		for (int index = 0; index < words.length; index++) {
			if (!isNullOrEmpty(words[index])) {
				strBuilder.append(toSentenceCase(words[index]));
				if (index < words.length - 1) {
					strBuilder.append(SPACE);
				}
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("-toTitleCase(), result: " + strBuilder.toString());
		}
		return strBuilder.toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toToggleCase(String str) {
		if (log.isDebugEnabled()) {
			log.debug("+toToggleCase(" + str + ")");
		}
		if (isNullOrEmpty(str)) {
			throw new NullPointerException("Invalid Parameter!, str: " + str);
		}

		/* remove all unwanted spaces if any. */
		StringBuilder strBuilder = new StringBuilder();
		char ch = 0;
		for (int index = 0; index < str.length(); index++) {
			ch = str.charAt(index);
			if (Character.isUpperCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isTitleCase(ch)) {
				ch = Character.toLowerCase(ch);
			} else if (Character.isLowerCase(ch)) {
				ch = Character.toUpperCase(ch);
			}
			strBuilder.append(ch);
		}

		if (log.isDebugEnabled()) {
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
		if (elements != null) {
			if (isNullOrEmpty(delimiter)) {
				delimiter = STR_SPACE;
			}
			sBuilder = new StringBuilder();
			for (int i = 0; i < elements.length; i++) {
				sBuilder.append(elements[i]);
				if (i < (elements.length - 1)) {
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
	 * Returns the elements of string separated by using the specified
	 * delimiter. if delimiter is null or empty, space is used as default
	 * delimiter
	 * 
	 * @param str
	 * @return
	 */
	public static String[] split(String str, String delimiter) {
		String[] elements = null;
		if (!isNullOrEmpty(str)) {
			if (isNullOrEmpty(delimiter)) {
				delimiter = STR_SPACE;
			}
			StringTokenizer sTokenizer = new StringTokenizer(str, delimiter);
			elements = new String[sTokenizer.countTokens()];
			for (int i = 0; sTokenizer.hasMoreTokens(); i++) {
				elements[i] = sTokenizer.nextToken();
			}
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
		if (!isNullOrEmpty(str)) {
			List<String> segments = new LinkedList<String>();
			char[] chars = str.toCharArray();
			String newStr = null;
			int startIdx = 0;
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == delimiter) {
					/* ignore extra spaces/delimiter characters */
					if (i == startIdx) {
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
		if (elements != null && elements.length > 0) {
			for (String str : elements) {
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
		if (times > 0) {
			for (int i = 0; i < times; i++) {
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
		if (log.isDebugEnabled()) {
			log.debug("+countWords(" + str + ")");
		}

		if (isNullOrEmpty(str)) {
			throw new NullPointerException("Invalid Parameter!, str: " + str);
		}

		long words = 0;
		int index = 0;
		boolean whiteSpace = true;
		while (index < str.length()) {
			char cChar = str.charAt(index++);
			// log.debug("cChar:" + cChar);
			boolean isWhitespace = Character.isWhitespace(cChar);
			if (whiteSpace && !isWhitespace) {
				words++;
			}
			whiteSpace = isWhitespace;
		}

		if (log.isDebugEnabled()) {
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
	public static Set<String> filter(String[] elements, boolean ignoreCase,
			boolean unique) {
		Set<String> uniques = null;
		Set<String> duplicates = null;
		if (elements != null) {
			uniques = new LinkedHashSet<String>();
			duplicates = new LinkedHashSet<String>();
			for (int i = 0; i < elements.length; i++) {
				String str = elements[i];
				/* remove if the word ends with non-letter character. */
				char cChar = str.charAt(str.length() - 1);
				if (!Character.isLetter(cChar)) {
					str = str.substring(0, elements[i].length() - 1);
				}
				
				if (ignoreCase) {
					str = str.toLowerCase();
				}
				
				if (!uniques.add(str)) {
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
		if (elements != null) {
			strElements = new String[elements.length];
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] instanceof String) {
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
		if (elements != null) {
			strElements = new String[elements.size()];
			Object[] oElements = elements.toArray();
			for (int i = 0; i < oElements.length; i++) {
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
		if (!isNullOrEmpty(value)) {
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
		if (!isNullOrEmpty(value)) {
			try {
				result = Integer.parseInt(value);
			} catch (NumberFormatException nfe) {
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
		if (!isNullOrEmpty(value)) {
			try {
				result = Long.parseLong(value);
			} catch (NumberFormatException nfe) {
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
		if (active) {
			int num = 0;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter a Number : ");
			try {
				num = Integer.parseInt(in.readLine());
			} catch (IOException ioe) {
				System.out.println("Exception : " + ioe.toString());
			}
			if (num != -1) {
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
		String[] Units = { " ", "One", "Two", "Three", "Four", "Five", "Six",
				"Seven", "Eight", "Nine" };
		String[] Tens = { " ", "Eleven", "Twelve", "Thirteen", "Fourteen",
				"Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		String[] Hundreds = { " ", "Ten", "Twenty", "Thirty", "Fourty",
				"Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
		System.out.println("onesConverter Num : " + num);
		String str = " ";
		if (num < 10) {
			str = Units[num];
		} else if (num < 20) {
			str = Tens[num % 10];
		} else if (num < 100) {
			System.out.println("Hundreds[num] : " + Hundreds[num / 10] + " "
					+ Units[num % 10]);
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
		if ((num / 100) > 0) {
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
		if ((num / 1000) > 0) {
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
		if (num > 0) {
			System.out.println("numerToWord (num % 1000) : " + (num % 1000));
			while (ctr < Prefixes.length && num > 0) {
				str += Prefixes[ctr] + threesConverter(num % 1000)
						+ Prefixes[ctr];
				System.out.println("numerToWord str : " + str);
				System.out.println("numerToWord Before Dividing Num : " + num);
				num = num / 1000;
				System.out.println("numerToWord After Dividing Num : " + num);
				ctr++;
			}
		}
		return str;
	}

	static String[] Prefixes = { " ", "Thousand", "Lakhs", "Million" };

	/**
	 * Returns the reversed string using recursion.
	 * 
	 * @param str
	 * @return
	 */
	public String reverse(String str) {
		if (null == str || str.isEmpty()) {
			return "";
		}
		return reverse(str.substring(1)) + str.substring(0, 1);
	}

}