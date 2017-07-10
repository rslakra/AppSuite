package com.rslakra.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.install4j.runtime.installer.platform.win32.Win32Services.ServiceException;

/**
 * @author Rohtash Singh (rsingh@boardvantage.com)
 * @version 1.0.0
 * @since Apr 29, 2015 9:39:13 PM
 */
public final class StringUtils {
	
	/** HEX_DIGIT_CHARS */
	private static final String HEX_DIGIT_CHARS = "0123456789abcdef";
	/** NEWLINE */
	public static final String NEWLINE = "\n".intern();
	/** UTF_8 */
	public static final String UTF_8 = "utf-8".intern();
	/** NEWLINE */
	public static final String ISO_8859_1 = "ISO-8859-1".intern();
	
	/** URL_PATTERN */
	public static final String URL_REGEX = "^(http|https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	/**
	 * 
	 * @param fullString
	 * @param maxLen
	 * @return
	 */
	public static String truncateStringWithDots(String fullString, int maxLen) {
		if(fullString.length() > maxLen) {
			String retStr = fullString.substring(0, maxLen - 3);
			retStr += "...";
			return retStr;
		}
		return fullString;
	}
	
	/**
	 * 
	 * @param arr
	 * @param delimiter
	 * @return
	 */
	public static String arrayToString(String[] arr, String delimiter) {
		String returnStr = "";
		for(String str : arr) {
			returnStr += str + delimiter;
		}
		
		if(!returnStr.isEmpty()) {
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		
		return returnStr;
	}
	
	/**
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static List<String> stringToArray(String str, String delimiter) {
		List<String> returnStr = new ArrayList<String>();
		if(!isNullOrEmpty(str)) {
			String[] pieces = str.split(delimiter);
			for(String piece : pieces) {
				piece = piece.trim();
				if(!piece.isEmpty()) {
					returnStr.add(piece);
				}
			}
		}
		
		return returnStr;
	}
	
	/**
	 * getFirstName
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String getFirstName(String str, String delimiter) {
		String returnStr = "";
		List<String> strList = stringToArray(str, delimiter);
		if(strList.size() > 0) {
			returnStr = strList.get(0);
		}
		return returnStr;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String appendHashToColorValue(String str) {
		String returnStr = "";
		if(!isNullOrEmpty(str)) {
			returnStr = "#" + str;
		}
		
		return returnStr;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isValidColorValue(String str) {
		Boolean isValid = false;
		if(!isNullOrEmpty(str)) {
			String HEX_PATTERN = "[A-Fa-f0-9]{6}";
			isValid = str.matches(HEX_PATTERN);
		}
		return isValid;
	}
	
	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static String extractDomainFromEmailAddress(String emailAddress) {
		int index = emailAddress.indexOf("@") + 1;
		String tmp = emailAddress.substring(index);
		index = tmp.indexOf(".");
		tmp = tmp.substring(0, index);
		return tmp;
	}
	
	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static String extractUsernameFromEmailAddress(String emailAddress) {
		int index = emailAddress.indexOf("@");
		return emailAddress.substring(0, index);
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailValid(String email) {
		String expression = "^([A-Za-z0-9-_\"&]+[\".]?[-_\"&]?)*[A-Za-z0-9]+[-_\"&]*@([^-]?[0-9a-zA-Z]+(-[0-9a-zA-Z]+)*[\".])+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(expression);
		return pattern.matcher(email).matches();
	}
	
	/**
	 * Returns true if the urlString is a valid URL otherwise false.
	 * 
	 * @param urlString
	 * @return
	 */
	public static boolean isURLValid(String urlString) {
		return (!isNullOrEmpty(urlString) && urlString.matches(URL_REGEX));
	}
	
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
	 * Convert a byte array to a HEXA String of the format "1f 30 b7".
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHexString(byte[] bytes) {
		String hexString = null;
		if(!ObjectUtils.isNullOrEmpty(bytes)) {
			StringBuilder hexaBuilder = new StringBuilder(bytes.length * 2);
			for(int index = 0; index < bytes.length; index++) {
				int hn = ((int) (bytes[index]) & 0x00ff) / 16;
				int ln = ((int) (bytes[index]) & 0x000f);
				
				hexaBuilder.append(HEX_DIGIT_CHARS.charAt(hn));
				hexaBuilder.append(HEX_DIGIT_CHARS.charAt(ln));
			}
			
			hexString = hexaBuilder.toString();
			// available for GC.
			hexaBuilder = null;
		}
		
		return hexString;
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
				str = StringUtils.replace(str, "mainFont", fontName);
			}
			str = StringUtils.replace(str, "face=_", "face=");
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
	
	/**
	 * 
	 * @param aTagFragment
	 * @return
	 */
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
	
	/**
	 * 
	 * @param str
	 * @return
	 */
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
	
	/**
	 * Returns the string as integer value.
	 * 
	 * @param value
	 * @return
	 */
	public static int stringAsInteger(String value) {
		return Integer.parseInt(value);
	}
	
	/**
	 * Returns the string as boolean value.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean stringAsBoolean(String value) {
		return Boolean.parseBoolean(value);
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
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(String string) {
		return (null == string || string.trim().length() == 0 || "null".equalsIgnoreCase(string));
	}
	
	/**
	 * Returns true if the given string is neither null nor empty otherwise
	 * false.
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String string) {
		return (!isNullOrEmpty(string));
	}
	
	/**
	 * Returns true if either the strings array is null or empty otherwise
	 * false.
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isNullOrEmpty(String... strings) {
		return (null == strings || strings.length == 0);
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
	 * Returns the bytes for the given string.
	 * 
	 * @param string
	 * @param charsetName
	 * @return
	 */
	public static byte[] getBytes(String string, String charsetName) {
		byte[] bytes = null;
		if(!isNullOrEmpty(string)) {
			try {
				if(isNullOrEmpty(charsetName)) {
					bytes = string.getBytes();
				} else {
					bytes = string.getBytes(charsetName);
				}
			} catch(UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		
		return bytes;
	}
	
	/**
	 * Returns the UTF-8 bytes for the given string.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] getUTF8Bytes(String string) {
		return getBytes(string, UTF_8);
	}
	
	/**
	 * Returns the ISO-8859-1 bytes for the given string.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] getISOBytes(String string) {
		return getBytes(string, ISO_8859_1);
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
	
	/**
	 * 
	 * @param inputStr
	 * @return
	 */
	public static String replaceSpecialCharacters(String inputStr) {
		return replaceAll("([^a-zA-Z0-9\\.\\s]+)", inputStr, "_");
	}
	
	/**
	 * 
	 * @param inputStr
	 * @return
	 */
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
	public static String xslString(String value) throws Exception {
		if(value == null) {
			value = "";
		}
		
		StringBuffer buffer = new StringBuffer();
		try {
			value = toUTF8String(value);
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
		} finally {
			buffer = null;
		}
		
		return value;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String cleanHtml(String str) {
		if(str != null && str.length() > 0) {
			str = str.replaceAll("&nbsp;", " ");
			str = str.replaceAll("&amp;", "&");
		}
		
		return str;
	}
	
	/**
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String deleteHTMLTags(String htmlStr) {
		if(htmlStr != null && htmlStr.length() > 0) {
			return htmlStr.replaceAll("\\<.*?\\>", "");
		}
		return htmlStr;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceCommaWithSpace(String str) {
		if(str != null && str.length() > 0) {
			str = str.replaceAll(",", " ");
		}
		return str;
	}
	
	/**
	 * 
	 * @param filename
	 * @return
	 */
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
	
	/**
	 * 
	 * @param fileName
	 * @param newExtension
	 * @return
	 * @throws ServiceException
	 */
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
	 * 
	 * @param writer
	 * @param url
	 * @param versionUuid
	 * @param height
	 */
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
	
	/**
	 * 
	 * @param content
	 * @param oldWord
	 * @param newWord
	 * @return
	 */
	public static String replace(String content, String oldWord, String newWord) {
		String tempString = content;
		int position = tempString.toLowerCase().indexOf(oldWord.toLowerCase());
		while(position > -1) {
			tempString = tempString.substring(0, position) + newWord + tempString.substring(position + oldWord.length());
			position = tempString.toLowerCase().indexOf(oldWord.toLowerCase(), position + newWord.length());
		}
		return tempString;
	}
	
	/**
	 * 
	 * @param strToBeReplaced
	 * @return
	 */
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
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String escapeSQLCharacters(String input) {
		return (isNullOrEmpty(input) ? null : input.replaceAll("'", "''"));
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHtmlDecodeWithSpace(String str) {
		if(str != null && !str.equalsIgnoreCase("")) {
			str = replace(str, "&amp;", " ");
			str = replace(str, "&quot;", " ");
			str = replace(str, "&#39;", " ");
			str = replace(str, "&lt;", " ");
			str = replace(str, "&gt;", " ");
			str = replace(str, "&nbsp;", " ");
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
	
	/**
	 * 
	 * @return
	 */
	public static String generateActivationCode() {
		int code = new Random().nextInt(8999) + 1000;
		return code + "";
	}
	
	/**
	 * Returns the UTF-8 String representation of the given <code>bytes</code>.
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toUTF8String(byte[] bytes) {
		return bytesAsString(bytes, UTF_8);
	}
	
	/**
	 * Returns the UTF-8 String representation of the given <code>string</code>.
	 * 
	 * @param string
	 * @return
	 */
	public static String toUTF8String(String string) {
		return toUTF8String(string.getBytes());
	}
	
	/**
	 * Returns the ISO-8859-1 String representation of the given
	 * <code>bytes</code>.
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toISOString(byte[] bytes) {
		return bytesAsString(bytes, ISO_8859_1);
	}
	
	/**
	 * Returns the string of the given strings array.
	 * 
	 * @param strings
	 * @return
	 */
	public static String toString(String... strings) {
		StringBuilder sBuilder = new StringBuilder();
		if(isNullOrEmpty(strings)) {
			sBuilder.append("[]");
		} else {
			sBuilder.append("[");
			for(int i = 0; i < strings.length; i++) {
				sBuilder.append(strings[i]);
				if(i < (strings.length - 1)) {
					sBuilder.append(",");
				}
			}
			
			sBuilder.append("]");
		}
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @param htmlText
	 * @param size
	 * @return
	 */
	public static String replaceHTMLFontSize(String htmlText, int size) {
		return (isNullOrEmpty(htmlText) ? htmlText : replaceAll("(?i)(font[^/>]*size=(?:\"|\'))(\\d{1,3})", htmlText, ("$1" + size)));
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
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
	
	/**
	 * 
	 * @param input
	 * @param spaces
	 * @return
	 */
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
	
	/**
	 * 
	 * @param input
	 * @param spaces
	 * @return
	 */
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
	public static <T> String concat(T... args) {
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
	 * Returns true if the first string matches with any of the specified
	 * arguments otherwise false.
	 * 
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean equalsAnyArgs(String string, String... args) {
		if(isNotNullOrEmpty(string) && args != null) {
			for(int i = 0; i < args.length; i++) {
				if(string.equals(args[i])) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if the first string does not match with any of the given
	 * strings otherwise false.
	 * 
	 * @param string
	 * @param args
	 * @return
	 */
	public static boolean notEqualsAnyArgs(String string, String... args) {
		return (!equalsAnyArgs(string, args));
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
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String removeExtension(String fileName) {
		if(!isNullOrEmpty(fileName)) {
			int index = fileName.lastIndexOf(".");
			if(index != -1) {
				return fileName.substring(0, index);
			}
		}
		
		return fileName;
	}
	
	/**
	 * 
	 * @param th
	 * @return
	 */
	public static String toString(Throwable throwable) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			PrintWriter writer = new PrintWriter(outputStream);
			throwable.printStackTrace(writer);
			writer.flush();
			writer.close();
			outputStream.close();
		} catch(Exception ex) {
			System.err.println(ex);
		}
		
		return new String(outputStream.toByteArray());
	}
	
	/**
	 * Returns the trimmed string if it's not null or empty.
	 * 
	 * @param string
	 * @return
	 */
	public static String trimString(String string) {
		return (isNullOrEmpty(string) ? string : string.trim());
	}
	
	/**
	 * 
	 * @param string
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
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
	 * Returns the extension from the specified fullPath, if its not null or
	 * empty otherwise null.
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getExtension(String fullPath) {
		String extension = null;
		if(!StringUtils.isNullOrEmpty(fullPath)) {
			int dotIndex = fullPath.lastIndexOf(".");
			extension = ((dotIndex > -1 && dotIndex < fullPath.length() - 1) ? fullPath.substring(dotIndex + 1) : "");
		}
		
		return extension;
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
		if(!StringUtils.isNullOrEmpty(fullPath)) {
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
	
	/**
	 * Returns true if the value contains '\n' (new line) character otherwise
	 * false.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean containsNewLine(String value) {
		return (!isNullOrEmpty(value) && value.contains(NEWLINE));
	}
	
	/**
	 * Splits this string that matches of the given delimiter.
	 * 
	 * @param source
	 * @param delimiter
	 * @return
	 * 
	 * @see java.lang.String#split(String)
	 */
	public static String[] split(String source, String delimiter) {
		if(!isNullOrEmpty(source) && !isNullOrEmpty(delimiter)) {
			/* use special character's as delimiter, if required. */
			if(".$|()[{^?*+\\".contains(delimiter)) {
				delimiter = "\\" + delimiter;
			}
			
			return source.split(delimiter);
		}
		
		return null;
	}
	
	/**
	 * Joins the strings with the given delimiter.
	 * 
	 * @param elements
	 * @param delimiter
	 * @return
	 */
	public static String join(String[] elements, String delimiter) {
		StringBuilder sBuilder = null;
		if(!isNullOrEmpty(elements) && isNotNullOrEmpty(delimiter)) {
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
	 * Converts the given string into title case.
	 * 
	 * @param string
	 * @return
	 */
	public static String toTitleCase(String string) {
		return (isNullOrEmpty(string) ? string : Character.toUpperCase(string.charAt(0)) + string.substring(1));
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
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			latestVersion = (versions.get(versions.size() - 1));
		}
		
		return latestVersion;
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
	 * Converts the specified <code>bytes</code> to the specified
	 * <code>charsetName</code> String.
	 * 
	 * @param bytes
	 * @param charsetName
	 * @return
	 */
	public static String bytesAsString(byte[] bytes, String charsetName) {
		String bytesAsString = null;
		if(!ObjectUtils.isNullOrEmpty(bytes)) {
			try {
				if(StringUtils.isNullOrEmpty(charsetName)) {
					bytesAsString = new String(bytes);
				} else {
					bytesAsString = new String(bytes, charsetName);
				}
			} catch(Exception ex) {
				System.err.println(ex);
				bytesAsString = (ObjectUtils.isNull(bytes) ? null : bytes.toString());
			}
		}
		
		return bytesAsString;
	}
	
	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesAsString(byte[] bytes) {
		return bytesAsString(bytes, null);
	}
	
	/**
	 * Converts the specified <code>string</code> into bytes using the specified
	 * <code>charsetName</code>.
	 * 
	 * @param string
	 * @param charsetName
	 * @return
	 */
	public static byte[] toBytes(String string, String charsetName) {
		byte[] stringAsBytes = null;
		if(ObjectUtils.isNotNull(string)) {
			try {
				stringAsBytes = StringUtils.isNullOrEmpty(charsetName) ? string.getBytes() : string.getBytes(charsetName);
			} catch(Exception ex) {
				System.err.println(ex);
			}
		}
		
		return stringAsBytes;
	}
	
	/**
	 * Converts the specified <code>string</code> into bytes.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] toBytes(String string) {
		return toBytes(string, null);
	}
	
	/**
	 * Converts the bytes into the StringBuilder. If bytes array is null or
	 * empty, empty StringBuilder is returned.
	 * 
	 * @param bytes
	 * @return
	 */
	public static StringBuilder bytesAsStringBuilder(byte... bytes) {
		return (ObjectUtils.isNull(bytes) ? null : new StringBuilder(bytesAsString(bytes)));
	}
	
	/**
	 * Returns the java script, local storage setItem string for the specified
	 * key and value parameters.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String localStorageSetItemString(String key, String value) {
		StringBuilder strLocalStorageItem = new StringBuilder("localStorage.setItem('");
		if(!isNullOrEmpty(key)) {
			strLocalStorageItem.append(key).append("', '").append(value).append("');");
		}
		
		return strLocalStorageItem.toString();
	}
	
	/**
	 * Returns the java script, local storage setItem string for the specified
	 * key and value parameters.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static void addItemToLocalStorage(String key, String value, StringBuilder localStorage) {
		localStorage.append(localStorageSetItemString(key, value));
	}
	
	/**
	 * Replaces the contents of the currentValue with the newValue, if the
	 * contents and the currentValue are not null or empty.
	 * 
	 * @param contents
	 * @param currentValue
	 * @param newValue
	 * @return
	 */
	public static void replace(StringBuilder contents, String currentValue, String newValue) {
		if(!ObjectUtils.isNullOrEmpty(contents) && !isNullOrEmpty(currentValue)) {
			int startIndex = contents.indexOf(currentValue);
			if(startIndex > -1 && startIndex < contents.length()) {
				int endIndex = startIndex + currentValue.length();
				if(endIndex < contents.length()) {
					contents.replace(startIndex, endIndex, newValue);
				}
			}
		}
	}
	
	/**
	 * Returns the host name extracted from the specified urlString.
	 * 
	 * @param urlString
	 * @return
	 */
	public static String getHostNameFromUrl(String urlString) {
		String hostName = urlString;
		if(!isNullOrEmpty(urlString)) {
			int startIndex = urlString.indexOf("://");
			if(startIndex >= 0) {
				startIndex += "://".length();
				int endIndex = urlString.lastIndexOf(":");
				if(endIndex > -1 && endIndex < startIndex) {
					int slashIndex = urlString.lastIndexOf("/");
					if(slashIndex != -1) {
						hostName = urlString.substring(startIndex, slashIndex);
					} else {
						hostName = urlString.substring(startIndex);
					}
				} else {
					hostName = urlString.substring(startIndex, endIndex);
				}
			}
		}
		
		return hostName;
	}
	
	public static boolean isDigits(String str) {
		if(isNullOrEmpty(str)) {
			return false;
		}
		
		for(int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param urlString (http://emea.boardvantage.com)
	 * @return boardvantage.com
	 */
	public static String getHostNameFromUrlWithoutTheSubdomain(String urlString) {
		String hostname = null;
		URL url;
		try {
			url = new URL(urlString);
			hostname = url.getHost();
			String ip = hostname.replace(".", "");
			
			// check if just numbers
			if(isDigits(ip)) {
				// do nothing
			} else {
				
				int dotComIndex = hostname.lastIndexOf(".");
				
				if(dotComIndex != -1) {
					String hostnameMinusDotCom = hostname.substring(0, dotComIndex);
					
					int secondLastDotIndex = hostnameMinusDotCom.lastIndexOf(".");
					
					if(secondLastDotIndex != -1) {
						String hostnameWithoutSubDomain = hostname.substring(secondLastDotIndex + 1, hostnameMinusDotCom.length()) + hostname.substring(dotComIndex);
						hostname = hostnameWithoutSubDomain;
					}
					
				}
				
			}
			
		} catch(MalformedURLException e) {
			throw new RuntimeException(e);
		}
		
		return hostname;
	}
	
	/**
	 * Returns true if and only if the given sourceString contains any of the
	 * given strings otherwise false.
	 * 
	 * @param value
	 * @param strings
	 * @return
	 */
	public static boolean contains(String sourceString, String... containStrings) {
		boolean stringContains = false;
		if(isNotNullOrEmpty(sourceString) && !isNullOrEmpty(containStrings)) {
			for(int i = 0; i < containStrings.length; i++) {
				if(sourceString.contains(containStrings[i])) {
					stringContains = true;
					break;
				}
			}
		}
		
		return stringContains;
	}
	
	/**
	 * Returns the defaultCharset, if the given charsetName is either null or
	 * empty otherwise the same.
	 * 
	 * @param charsetName
	 * @return
	 */
	public static String defaultCharset(String charsetName) {
		return (isNullOrEmpty(charsetName) ? Charset.defaultCharset().displayName() : charsetName);
	}
	
	/**
	 * Returns an empty string if the give string is null otherwise the same.
	 * 
	 * @param string
	 * @return
	 */
	public static String emptyStringIfNull(String string) {
		return (ObjectUtils.isNull(string) ? "" : string);
	}
	
}