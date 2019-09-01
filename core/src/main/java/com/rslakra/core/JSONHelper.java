/******************************************************************************
 * Copyright (C) Devamatre Inc. 2009 - 2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * JSON utility class.
 * 
 * @author Rohtash Singh
 * @version 1.0.0
 * @since May 18, 2015 2:39:11 PM
 */
public final class JSONHelper {
	
	/**
	 * 
	 */
	private JSONHelper() {
		throw new RuntimeException("Object creation is not allowed!");
	}
	
	/**
	 * Returns the GSON object.
	 * 
	 * @param prettyPrint
	 * @return
	 */
	private static Gson newGsonObject(boolean prettyPrint) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.enableComplexMapKeySerialization();
		if (prettyPrint) {
			gsonBuilder.setPrettyPrinting();
		}
		
		Gson gson = gsonBuilder.create();
		return gson;
	}
	
	/**
	 * Returns the JSON string for the given JSON object.
	 * 
	 * @param object
	 * @param prettyPrint
	 * @return
	 */
	public static String toJSONString(Object object, boolean prettyPrint) {
		Gson gson = newGsonObject(prettyPrint);
		return gson.toJson(object);
	}
	
	/**
	 * Returns the JSON string for the given JSON object.
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		return toJSONString(object, false);
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String toJSONString(List<String> list) {
		String json = "";
		if (!CoreHelper.isNullOrEmpty(list)) {
			json = new Gson().toJson(list);
		}
		return json;
	}
	
	/**
	 * Returns the JSON string for the given exception.
	 * 
	 * @param runtimeException
	 * @return
	 */
	public static String toJSONString(Throwable error) {
		return toJSONString(error, false);
	}
	
	/**
	 * Generates the JSON string from the given map.
	 * 
	 * @param mapKeyValues
	 * @return
	 */
	public static String toJSONString(Map<String, String> mapData) {
		return newGsonObject(false).toJson(mapData);
	}
	
	/**
	 * Returns the object of the class type T for the given JSON string.
	 * 
	 * @param jsonString
	 * @param classType
	 * @return
	 */
	public static <T> T fromJSONString(String jsonString, Class<T> classType) {
		Gson gson = newGsonObject(false);
		T object = gson.fromJson(jsonString, classType);
		return object;
	}
	
	/**
	 * Returns the Map object from the given data bytes.
	 * 
	 * @param dataBytes
	 * @return
	 */
	public static Map<String, Object> jsonBytesAsMap(byte[] dataBytes) {
		TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
		};
		String jsonString = IOHelper.toUTF8String(dataBytes);
		return (new Gson().fromJson(jsonString, typeToken.getType()));
	}
	
	/**
	 * Returns the Map object from the given string.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, List<String>> jsonHeadersAsMap(String jsonString) {
		TypeToken<Map<String, List<String>>> typeToken = new TypeToken<Map<String, List<String>>>() {
		};
		return (new Gson().fromJson(jsonString, typeToken.getType()));
	}
	
	/**
	 * Returns the value for the given key from the given jsonString.
	 * 
	 * @param jsonString
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T valueForKey(String jsonString, String key) {
		return (T) fromJSONString(jsonString, Map.class).get(key);
	}
	
	/**
	 * Returns the value for the given key from the given jsonString.
	 * 
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static String valueForKeyAsString(String jsonString, String key) {
		return (String) valueForKey(jsonString, key);
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param propertName
	 * @return
	 */
	public static String valueForKey(JsonObject jsonObject, String propertName) {
		JsonElement jsonElement = jsonObject.get(propertName);
		return (jsonElement == null ? null : jsonElement.getAsString());
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param propertName
	 * @return
	 */
	public static Integer valueForKeyAsInteger(JsonObject jsonObject, String propertName) {
		JsonElement jsonElement = jsonObject.get(propertName);
		return (jsonElement == null ? null : jsonElement.getAsInt());
	}
	
	/**
	 * Returns the list of the specified objects from the given JSON string.
	 * 
	 * @param jsonString
	 * @param classType
	 * @return
	 */
	public static <T> List<T> listOfObjects(String jsonString, Class<T> classType) {
		List<T> objects = new ArrayList<T>();
		JsonArray jsonArray = toJSONArray(jsonString);
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				Gson gson = new Gson();
				T object = gson.fromJson(jsonArray.get(i), classType);
				objects.add(object);
			}
		}
		
		return objects;
	}
	
	/**
	 * Returns the list of the specified objects from the given JSON string.
	 * 
	 * @param jsonString
	 * @param classType
	 * @return
	 */
	public static List<?> listOfObjects(String jsonString, String key, Class<?>... classTypes) {
		List<Object> mixedObjects = new ArrayList<Object>();
		if (jsonString == null || !isValidJSONString(jsonString)) {
			return mixedObjects;
		}
		
		JsonArray jsonArray = toJSONArray(jsonString);
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				String objectJSONString = toJSONString(jsonArray.get(i));
				for (int j = 0; j < classTypes.length; j++) {
					String value = (String) toMap(objectJSONString).get(key);
					if (value.equalsIgnoreCase(classTypes[j].getSimpleName())) {
						Object object = fromJSONString(objectJSONString, classTypes[j]);
						mixedObjects.add(object);
						break;
					}
				}
			}
		}
		
		return mixedObjects;
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static JsonArray toJSONArray(String jsonString) {
		JsonArray array = null;
		JsonElement jsonElement = jsonElement(jsonString);
		if (jsonElement.isJsonArray()) {
			array = jsonElement.getAsJsonArray();
		}
		
		return array;
		
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static JsonElement jsonElement(String jsonString) {
		JsonParser jsonParser = new JsonParser();
		return jsonParser.parse(jsonString);
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static boolean isValidJSONString(String jsonString) {
		if (jsonString == null) {
			return false;
		} else {
			JsonParser parser = new JsonParser();
			try {
				parser.parse(jsonString);
			} catch (JsonSyntaxException ex) {
				return false;
			}
			
			return true;
		}
	}
	
	/**
	 * Returns the list of strings for the given JSON string.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static <T> List<T> listOfType(String jsonString) {
		TypeToken<List<T>> typeToken = new TypeToken<List<T>>() {
		};
		Gson gson = newGsonObject(false);
		List<T> listOfObjects = gson.fromJson(jsonString, typeToken.getType());
		return listOfObjects;
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List<String[]> listOfStringArrays(String jsonString) {
		TypeToken<List<String[]>> tt = new TypeToken<List<String[]>>() {
		};
		Gson gson = newGsonObject(false);
		List<String[]> list = gson.fromJson(jsonString, tt.getType());
		
		return list;
	}
	
	/**
	 * Returns the Map object from the given string.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> toMap(String jsonString) {
		TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
		};
		return (new Gson().fromJson(jsonString, typeToken.getType()));
	}
	
	/**
	 * Returns the Map object from the given data bytes.
	 * 
	 * @param dataBytes
	 * @return
	 */
	public static Map<String, Object> toMap(byte[] dataBytes) {
		TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
		};
		String jsonString = IOHelper.toUTF8String(dataBytes);
		return (new Gson().fromJson(jsonString, typeToken.getType()));
	}
	
	/**
	 * Returns the Map object from the given object.
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> toMap(Object object) {
		return toMap(toJSONString(object));
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return null if jsonString is null or empty
	 */
	public static List<Object[]> listOfObjectArrays(String jsonString) {
		TypeToken<List<Object[]>> tt = new TypeToken<List<Object[]>>() {
		};
		List<Object[]> list = new Gson().fromJson(jsonString, tt.getType());
		return list;
	}
	
	/**
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static List<String> toListOfStrings(JsonArray jsonArray) {
		Gson gson = new Gson();
		TypeToken<List<String>> tt = new TypeToken<List<String>>() {
		};
		List<String> list = gson.fromJson(jsonArray, tt.getType());
		return list;
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static JsonElement getElement(JsonObject jsonObject, String key) {
		if (jsonObject != null && !CoreHelper.isNullOrEmpty(key)) {
			return jsonObject.get(key);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static String getAsString(JsonObject jsonObject, String key) {
		JsonElement jsonElement = getElement(jsonObject, key);
		return (jsonElement != null ? jsonElement.getAsString() : null);
	}
	
	/**
	 * Returns the value for the given key from the given jsonArray.
	 * 
	 * @param jsonArray
	 * @param key
	 * @return
	 */
	public static String valueForKeyAsString(JsonArray jsonArray, String key) {
		String value = null;
		if (jsonArray != null && !CoreHelper.isNullOrEmpty(key)) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject jsonObject = (JsonObject) jsonArray.get(i);
				if (jsonObject != null && getAsString(jsonObject, "name").equals(key)) {
					value = getAsString(jsonObject, "value");
					break;
				}
			}
		}
		
		return value;
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public static boolean getAsBoolean(JsonObject jsonObject, String key) {
		return Boolean.valueOf(getAsString(jsonObject, key));
	}
	
	/**
	 * Returns an image bytes from the given JSON string.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static byte[] toImageBytes(String jsonString) {
		byte[] imageBytes = null;
		if (!CoreHelper.isNullOrEmpty(jsonString)) {
			String javaString = fromJSONString(jsonString, String.class);
			imageBytes = javaString.getBytes();
		}
		
		return imageBytes;
	}
	
	/**
	 * Returns an image files from the given JSON bytes.
	 * 
	 * @param responseBytes
	 * @return
	 */
	public static byte[] toImageBytes(byte[] responseBytes) {
		if (!CoreHelper.isNullOrEmpty(responseBytes)) {
			/*
			 * JSON String is encoded using the Base64 String which needs to
			 * convert back to Java String for images.
			 */
			String jsonString = IOHelper.toUTF8String(responseBytes);
			responseBytes = toImageBytes(jsonString);
		}
		
		return responseBytes;
	}
	
}
