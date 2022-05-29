package com.rslakra.core.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Rohtash Lakra
 * @created 5/19/20 5:08 PM
 */
public enum JSONUtils {
    INSTANCE;

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GsonBuilder gsonBuilder = new GsonBuilder();

    private JSONUtils() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        gsonBuilder.setPrettyPrinting();
    }

    /**
     * @return
     */
    private ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * @return
     */
    private GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }

    /**
     * @param jsonString
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T fromJson(final String jsonString, Class<T> responseType) throws IOException {
        LOGGER.debug("fromJson({}, {})", jsonString, responseType);
        return INSTANCE.getObjectMapper().readValue(jsonString, responseType);
    }

    /**
     * @param inputStream
     * @param responseType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T fromJson(final InputStream inputStream, Class<T> responseType) throws IOException {
        return INSTANCE.getObjectMapper().readValue(inputStream, responseType);
    }


    /**
     * @param object
     * @return
     * @throws IOException
     */
    public static String toJson(final Object object) throws IOException {
        return INSTANCE.getObjectMapper().writeValueAsString(object);
    }

    /**
     * <code>FieldNamingPolicy.IDENTITY</code> will ensure that the field name is unchanged. This is default
     * behavior.
     *
     * <code>FieldNamingPolicy.LOWER_CASE_WITH_DASHES</code> will modify the Java Field name from its camel cased form
     * to a lower case field name where each word is separated by a dash (-).
     *
     * <code>FieldNamingPolicy.LOWER_CASE_WITH_DOTS</code> will modify the Java Field name from its camel cased form to
     * a lower case field name where each word is separated by a dot (.).
     *
     * <code>FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES</code> will modify the Java Field name from its camel cased
     * form to a lower case field name where each word is separated by an underscore (_).
     *
     * <code>FieldNamingPolicy.UPPER_CAMEL_CASE</code> will ensure that the first “letter” of the Java field name is
     * capitalized when serialized to its JSON form.
     *
     * <code>FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES</code> will ensure that the first “letter” of the Java
     * field name is capitalized when serialized to its JSON form and the words will be separated by a space.
     *
     * @param fieldNamingPolicy
     */
    public final void setFieldNamingPolicy(final FieldNamingPolicy fieldNamingPolicy) {
        if (fieldNamingPolicy != null) {
            getGsonBuilder().setFieldNamingPolicy(fieldNamingPolicy);
        }
    }

    /**
     * <code>GsonBuilder.serializeNulls()</code> Serialization of null Values
     * By default, Gson ignores null properties during serialization. But, sometimes we want to serialize fields with
     * null value so that it must appear in JSON. Use serializeNulls() method for this purpose.
     */
    public final void serializeNulls() {
        getGsonBuilder().serializeNulls();
    }

    /**
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String toJSONString(final T object) {
        return INSTANCE.getGsonBuilder().create().toJson(object);
    }

    /**
     * @param jsonString
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T fromJSONString(final String jsonString, final Class<T> responseType) {
        return INSTANCE.getGsonBuilder().create().fromJson(jsonString, responseType);
    }

    //    public static JSONObject getJSONObjectValue(JSONArray header, JSONArray record,
//                                                String key) throws JSONException {
//        for (int i = 0; i < header.length(); i++) {
//            if (header.getString(i) != null && header.getString(i).equals(key)) {
//                return record.getJSONObject(i);
//            }
//        }
//
//        return new JSONObject();
//    }
}
