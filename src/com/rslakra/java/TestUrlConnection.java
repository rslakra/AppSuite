package com.rslakra.java;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

import com.rslakra.utils.HTTPUtil;
import com.rslakra.utils.HTTPUtil.HttpResponse;
import com.rslakra.utils.IOUtil;

public class TestUrlConnection {
	
	public static void main(String[] args) {
		// String urlString = "https://qawest.meetx.org/";
		String urlString = "https://qaos.meetx.org/";
		HttpResponse httpResponse = HTTPUtil.executeGetRequest(urlString, null, true);
		System.out.println(httpResponse.getRequestHeaders());
		
		String formActionValue = extractFormActionValue(httpResponse.getDataBytes());
		System.out.println("\nformActionValue:\n" + formActionValue);
		
		String dataString = new String(httpResponse.getDataBytes());
		Pattern pattern = Pattern.compile("\"");
		Matcher matcher = pattern.matcher(dataString);
		if(matcher.matches()) {
			System.out.println("Matched\n");
			System.out.println(matcher.group(1));
		}
		
		System.out.println(StringEscapeUtils.unescapeHtml(urlString));
	}
	
	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String extractFormActionValue(byte[] bytes) {
		String formActionValue = null;
		if(!IOUtil.isNullOrEmpty(bytes)) {
			final String startString = "<form action=\"";
			final String endString = "\" method=\"post\">";
			BufferedReader bReader = null;
			try {
				bReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
				String line = null;
				while((line = bReader.readLine()) != null) {
					if(line.trim().startsWith(startString)) {
						formActionValue = line.substring(line.indexOf(startString) + startString.length(), (line.length() - endString.length()));
						formActionValue = StringEscapeUtils.unescapeHtml(formActionValue);
						break;
					}
				}
			} catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					bReader.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return formActionValue;
	}
	
}
