package com.rslakra.servers.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	
	/**
	 * Converts the specified <code>object</code> into JSON String.
	 * 
	 * @param object
	 * @param prettyPrint
	 * @return
	 */
	public static String jsonString(Object object, boolean prettyPrint) {
		String jsonString = null;
		Gson gson = (prettyPrint ? new GsonBuilder().setPrettyPrinting().create() : new Gson());
		jsonString = gson.toJson(object);
		
		return jsonString;
	}
	
	/**
	 * Converts the specified <code>object</code> into JSON String.
	 * 
	 * @param object
	 * @return
	 */
	public static String jsonString(Object object) {
		return jsonString(object, false);
	}
	
	/**
	 * Returns the object of the <code>Class<T></code> type populated with the
	 * specified <code>jsonString</code>.
	 * 
	 * @param jsonString
	 * @param klass
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> klass) {
		return new Gson().fromJson(jsonString, klass);
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param propertName
	 * @return
	 */
	public static JsonElement propertyValue(JsonObject jsonObject, String propertName) {
		return (jsonObject != null ? jsonObject.get(propertName) : null);
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param propertName
	 * @return
	 */
	public static String propertyValueAsString(JsonObject jsonObject, String propertName) {
		JsonElement jsonElement = propertyValue(jsonObject, propertName);
		return (jsonElement != null ? jsonElement.getAsString() : null);
	}
	
	/**
	 * 
	 * @param jsonObject
	 * @param propertName
	 * @return
	 */
	public static Integer propertyValueAsInteger(JsonObject jsonObject, String propertName) {
		JsonElement jsonElement = propertyValue(jsonObject, propertName);
		return (jsonElement != null ? jsonElement.getAsInt() : null);
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static JsonElement jsonElement(String jsonString) {
		return new JsonParser().parse(jsonString);
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static JsonArray jsonArrayFromJson(String jsonString) {
		JsonArray jsonArray = null;
		JsonElement jsonElement = jsonElement(jsonString);
		if(jsonElement != null && jsonElement.isJsonArray()) {
			jsonArray = jsonElement.getAsJsonArray();
		}
		
		return jsonArray;
	}
	
	/**
	 * Returns true if the specified jsonString is valid otherwise false.
	 * 
	 * @param jsonString
	 * @return
	 */
	public static boolean isValidJsonString(String jsonString) {
		try {
			new JsonParser().parse(jsonString);
		} catch(JsonSyntaxException ex) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returns the object of the <code>Class<T></code> type populated with the
	 * specified <code>jsonString</code>.
	 * 
	 * @param jsonString
	 * @param klass
	 * @return
	 */
	public static <T> T fromJson(String jsonString) {
		TypeToken<T> typeToken = new TypeToken<T>() {};
		return new Gson().fromJson(jsonString, typeToken.getType());
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static List<String> listOfStrings(String jsonString) {
		// TypeToken<List<String>> typeToken = new TypeToken<List<String>>() {};
		// List<String> listOfStrings = new Gson().fromJson(jsonString,
		// typeToken.getType());
		// return listOfStrings;
		return fromJson(jsonString);
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return null if jsonString is null or empty
	 */
	public static List<String[]> listOfStringArray(String jsonString) {
		// TypeToken<List<String[]>> typeToken = new TypeToken<List<String[]>>()
		// {};
		// List<String[]> listOfStringArray = new Gson().fromJson(jsonString,
		// typeToken.getType());
		// return listOfStringArray;
		return fromJson(jsonString);
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return null if jsonString is null or empty
	 */
	public static List<Object[]> listOfObjectArray(String jsonString) {
		// TypeToken<List<Object[]>> typeToken = new TypeToken<List<Object[]>>()
		// {};
		// List<Object[]> listOfObjectArray = new Gson().fromJson(jsonString,
		// typeToken.getType());
		// return listOfObjectArray;
		return fromJson(jsonString);
	}
	
	/**
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> jsonStringAsMap(String jsonString) {
		// TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String,
		// Object>>() {};
		// Map<String, Object> jsonStringAsMap = new Gson().fromJson(jsonString,
		// typeToken.getType());
		// return jsonStringAsMap;
		return fromJson(jsonString);
	}
	
	/**
	 * This method returns array of object of passed type from the JSON array
	 * String
	 * 
	 * @param jsonArrayString String in the format [{},{}]
	 * @param clazz object type
	 * @return
	 */
	public static <T> List<T> listFromJsonArrayString(String jsonArrayString, Class<T> clazz) {
		Gson gson = new Gson();
		JsonArray jsonArray = new JsonParser().parse(jsonArrayString).getAsJsonArray();
		List<T> list = new ArrayList<T>();
		for(int i = 0; i < jsonArray.size(); i++) {
			T object = gson.fromJson(jsonArray.get(i), clazz);
			list.add(object);
		}
		
		return list;
	}
	
	// TODO: we should just replace toJson and fromJson with the one below once
	// we see no problem with this.
	private static Gson createGson() {
		return new GsonBuilder().disableHtmlEscaping().registerTypeHierarchyAdapter(Date.class, new DateTimeSerializer()).registerTypeHierarchyAdapter(Date.class, new DateTimeDeserializer()).create();
	}
	
	public static <T> T fromJsonWithDate(String json, Class<T> clazz) {
		return createGson().fromJson(json, clazz);
	}
	
	public String toJsonWithDate(Object object) {
		return createGson().toJson(object);
	}
	
	private static class DateTimeSerializer implements JsonSerializer<Date> {
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(src.getTime());
		}
	}
	
	private static class DateTimeDeserializer implements JsonDeserializer<Date> {
		
		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			return new Date(Long.valueOf((json).getAsString()));
		}
	}
}
