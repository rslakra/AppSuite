package com.rslakra.appsuite.jdk.annotations.serialize;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 10/3/19 9:00 AM
 */
public final class JsonSerializer {

    /**
     * @param object
     */
    private void checkIfSerializable(final Object object) {
        if (Objects.isNull(object)) {
            throw new JsonSerializationException("The object to be serialized is null!");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new JsonSerializationException("The class " + clazz.getSimpleName()
                                                 + " is not annotated with @JsonSerializable");
        }
    }

    /**
     * @param object
     * @throws Exception
     */
    private void initializeObject(final Object object) throws Exception {
        Class<?> objClass = object.getClass();
        for (Method method : objClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(JsonInit.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    /**
     * Returns the key for the field, if provided explicitly or user it's name as key.
     *
     * @param field
     * @return
     */
    public static String getFieldKey(final Field field) {
        String fieldKey = field.getAnnotation(JsonElement.class).key();
        return (fieldKey.isEmpty() ? field.getName() : fieldKey);
    }

    /**
     * Returns the <code>JSON</code> string of the <code>Map<String, String></code> object.
     *
     * @param jsonElements
     * @return
     */
    private String toJSONString(final Map<String, String> jsonElements) {
        final StringBuilder jsonBuilder = new StringBuilder("{");
        if (jsonElements != null) {
            jsonBuilder.append(jsonElements.entrySet().stream()
                                   .map(entry -> String.format("\"%s\":\"%s\"", entry.getKey(), entry.getValue()))
                                   .collect(Collectors.joining(",")));
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    /**
     * Converts the <code>object</code> into the <code>JSON</code> string.
     *
     * @param object
     * @return
     * @throws Exception
     */
    private final String toJSONString(final Object object) throws Exception {
        Class<?> objClass = object.getClass();
        Map<String, String> jsonElements = new HashMap<>();
        for (Field field : objClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElements.put(getFieldKey(field), (String) field.get(object));
            }
        }

        return toJSONString(jsonElements);
    }

    /**
     * Serialize the <code>object</code> into <code>JSON</code> string.
     *
     * @param object
     * @return
     * @throws JsonSerializationException
     */
    public String serialize(final Object object) throws JsonSerializationException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return toJSONString(object);
        } catch (Exception ex) {
            throw new JsonSerializationException(ex.getMessage());
        }
    }
}
