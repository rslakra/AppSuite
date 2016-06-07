package com.apparatus.interviews;

public class LexicalUniqueString {
	
	public static String removeDuplidates(String string) {
		if(string != null && string.length() > 0) {
			char[] str = string.toCharArray();
			for(int j = str.length - 1; j >= 0; j--) {
				for(int i = 0; i < str.length - 1; i++) {
					if(str[j] != ' ') {
						if(str[j] == str[i] && i != j) {
							str[i] = ' ';
						}
					}
				}
			}
			
			StringBuilder uniqueString = new StringBuilder();
			for(int k = 0; k < str.length; k++) {
				if(str[k] != ' ') {
					uniqueString.append(str[k]);
				}
			}
			string = uniqueString.toString();
		}
		
		return string;
	}
	
	public static void main(String[] args) {
		String string = "bcabc";
		System.out.println("string:" + string + ", Lexicographical:" + removeDuplidates(string));
		 string = "cbacdcbc";
		 System.out.println("string:" + string + ", Lexicographical:" +
		 removeDuplidates(string));
		 string = "cbazcgkadcbc";
		 System.out.println("string:" + string + ", Lexicographical:" +
		 removeDuplidates(string));
		 string = "acbzleozacdcbc";
		 System.out.println("string:" + string + ", Lexicographical:" +
		 removeDuplidates(string));
		 string = "cboqpzaqcdcbc";
		 System.out.println("string:" + string + ", Lexicographical:" +
		 removeDuplidates(string));
		 string = "swimming";
		 System.out.println("string:" + string + ", Lexicographical:" +
		 removeDuplidates(string));
		 string = "cbac";
		 System.out.println("string:" + string + ", Lexicographical:" +
		 removeDuplidates(string));
	}
	
}
