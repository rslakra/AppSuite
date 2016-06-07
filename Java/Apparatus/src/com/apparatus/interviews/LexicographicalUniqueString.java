package com.apparatus.interviews;

public class LexicographicalUniqueString {
	
	/**
	 * 
	 * @param nextChar
	 * @param index
	 * @param string
	 * @return
	 */
	public static String removeDuplidates(char nextChar, int index, String string) {
		string = string.replace(nextChar, ' ');
		char[] newStr = string.toCharArray();
		newStr[index] = nextChar;
		string = new String(newStr);
		
		return string;
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String removeDuplidates(String string) {
		StringBuilder uniqueString = new StringBuilder();
		if(string != null && string.length() > 0) {
			char lexiChar = string.charAt(0);
			int index = 0;
			for(int i = 0; i < string.length(); i++) {
				if(string.charAt(i) < lexiChar) {
					lexiChar = string.charAt(i);
					index = i;
				}
			}
			
			System.out.println("lexiChar:" + lexiChar + ", index:" + index);
			char nextChar = lexiChar;
			for(int i = index; i < string.length(); i++) {
				nextChar = string.charAt(i);
				if(nextChar == ' ') {
					continue;
				}
				string = removeDuplidates(nextChar, i, string);
				uniqueString.append(string.charAt(i));
			}
			
			// add rest of the characters
			for(int i = 0; i < index; i++) {
				nextChar = string.charAt(i);
				if(nextChar == ' ') {
					continue;
				}
				string = removeDuplidates(nextChar, i, string);
				uniqueString.append(string.charAt(i));
			}
		}
		
		return uniqueString.toString();
	}
	
	public static void main(String[] args) {
		String string = "bcabc";
		System.out.println("string:" + string + ", Lexicographical:" + removeDuplidates(string));
		string = "cbacdcbc";
		System.out.println("string:" + string + ", Lexicographical:" + removeDuplidates(string));
		string = "cbazcgkadcbc";
		System.out.println("string:" + string + ", Lexicographical:" + removeDuplidates(string));
		string = "acbzleozacdcbc";
		System.out.println("string:" + string + ", Lexicographical:" + removeDuplidates(string));
		string = "cboqpzaqcdcbc";
		System.out.println("string:" + string + ", Lexicographical:" + removeDuplidates(string));
		string = "swimming";
		System.out.println("string:" + string + ", Lexicographical:" + removeDuplidates(string));
	}
	
}
