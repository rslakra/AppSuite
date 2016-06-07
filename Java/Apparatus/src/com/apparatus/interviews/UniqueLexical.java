package com.apparatus.interviews;

public class UniqueLexical {
	public static String reverse(String str) {
		return ((str == null || str.isEmpty()) ? "" : reverse(str.substring(1)) + str.substring(0, 1));
	}
	
	public static String removeDuplidates(String string) {
		if(string != null && string.length() > 0) {
			string = reverse(string);
			StringBuilder uniqueString = new StringBuilder();
			for(int i = 0; i < string.length(); i++) {
				uniqueString.delete(0, string.length());
				boolean[] checked = new boolean[256];
				for(int j = 0; j < string.length(); j++) {
					if(!checked[string.charAt(j)]) {
						checked[string.charAt(j)] = true;
						uniqueString.append(string.charAt(j));
					}
				}
				
				string = uniqueString.toString();
			}
			
			string = reverse(string);
		}
		
		return string;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
}
