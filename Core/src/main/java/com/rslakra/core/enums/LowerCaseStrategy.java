package com.rslakra.core.enums;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 5:31 PM
 */
public class LowerCaseStrategy extends PropertyNamingStrategy {

    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        return field.getName();
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convert(method, defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convert(method, defaultName);
    }

    /**
     * get the class from getter/setter methods
     *
     * @param method
     * @param defaultName - jackson generated name
     * @return the correct property name
     */
    private String convert(AnnotatedMethod method, String defaultName) {
        Class<?> clazz = method.getDeclaringClass();
        List<Field> fieldList = getAllFields(clazz);
        for (Field field : fieldList) {
            if (field.getName().equalsIgnoreCase(defaultName)) {
                return field.getName();
            }
        }
        return defaultName;
    }

    /**
     * get all fields from class
     *
     * @param currentClass - should not be null
     * @return fields from the currentClass and its superclass
     */
    private List<Field> getAllFields(Class<?> currentClass) {
        List<Field> fieldList = new ArrayList<>();
        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();
            Collections.addAll(fieldList, fields);
            if (currentClass.getSuperclass() == null) {
                break;
            }
            currentClass = currentClass.getSuperclass();
        }

        return fieldList;
    }

}
