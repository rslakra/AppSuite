package com.apparatus.utils;

import java.net.HttpURLConnection;

import com.apparatus.config.Config;

public final class ServerUtil {
	
	/**
	 * 
	 * @param suffixS
	 * @return
	 */
	public static String getServerUrl(String suffix) {
		StringBuilder urlServer = new StringBuilder();
		urlServer.append(Config.getServerScheme()).append("://");
		urlServer.append(Config.getServerHost());
		if(!StringHelper.isNullOrEmpty(Config.getServerPort())) {
			urlServer.append(":").append(Config.getServerPort());
		}
		
		if(!StringHelper.isNullOrEmpty(suffix)) {
			if(suffix.startsWith("/")) {
				urlServer.append(suffix);
			} else {
				urlServer.append("/").append(suffix);
			}
		}
		
		return urlServer.toString();
	}
	
	public static boolean isNetworkAvailable(String urlSuffix) {
		boolean networkAvaialable = false;
		HttpURLConnection urlConnection = null;
		try {
			String urlString = getServerUrl(urlSuffix);
			System.out.println("urlString:" + urlString);
			urlConnection = HttpUtils.getHttpConnection(urlString, null);
			if(urlConnection != null) {
				urlConnection.setConnectTimeout(Config.getIntValue(Config.SERVER_CONNECTION_TIMEOUT, 10000));
				urlConnection.setReadTimeout(Config.getIntValue(Config.SERVER_READ_TIMEOUT, 10000));
				networkAvaialable = (urlConnection.getContent() != null);
			}
		} catch(Exception ex) {
			networkAvaialable = false;
		} finally {
			HttpUtils.close(urlConnection);
		}
		
		System.out.println("isNetworkAvailable(), networkAvaialable:" + networkAvaialable);
		return networkAvaialable;
	}
	
}
