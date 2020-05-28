package com.rslakra.core;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rohtash Lakra
 * @created 5/27/20 2:35 PM
 */

public enum BeanInfo {
    INSTANCE;

    private final String CLASS = "class";
    private final String GET = "get";
    private final String SET = "set";
    private final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap(8);
    private final Set<String> IMMUTABLE_ATTRIBUTES = new HashSet(Arrays.asList("id"));
    private final Map<Class<?>, Map<String, PropertyDescriptor>>
        CLASS_PROPERTY_DESCRIPTOR =
        new ConcurrentHashMap();

    private BeanInfo() {
        primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
        primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
        primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
        primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
        primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
        primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
        primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
        primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
    }

    /**
     * @param name
     * @return
     */
    public final boolean isModifiable(final String name) {
        return !IMMUTABLE_ATTRIBUTES.contains(name);
    }

    /**
     * Returns the <code>PropertyDescriptor[]</code> for the given class.
     *
     * @param classType
     * @return
     * @throws IntrospectionException
     */
    public final PropertyDescriptor[] getBeanInfo(final Class<?> classType) throws IntrospectionException {
        return Introspector.getBeanInfo(classType).getPropertyDescriptors();
    }

    /**
     * @param classType
     * @param propertyDescriptor
     * @throws IntrospectionException
     */
    private final void findWriteMethod(final Class<?> classType, final PropertyDescriptor propertyDescriptor)
        throws IntrospectionException {
        if (!isClassPropertyDescriptor(propertyDescriptor) && propertyDescriptor.getReadMethod() != null) {
            final String setterMethod = getSetterMethod(propertyDescriptor);
            final Class<?> propType = getReturnType(propertyDescriptor);
            for (Method method : classType.getMethods()) {
                if (setterMethod.equals(method.getName()) && method.getParameterTypes().length == 1 && method
                    .getParameterTypes()[0].isAssignableFrom(propType)) {
                    propertyDescriptor.setWriteMethod(method);
                    return;
                }
            }
        }
    }


    /**
     * @param classType
     * @return
     */
    public final Map<String, PropertyDescriptor> findByClass(final Class<?> classType) {
        try {
            Map<String, PropertyDescriptor> typePropertyDescriptor = CLASS_PROPERTY_DESCRIPTOR.get(classType);
            if (typePropertyDescriptor == null) {
                typePropertyDescriptor = new LinkedHashMap();
                final PropertyDescriptor[] propertyDescriptors = getBeanInfo(classType);
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getWriteMethod() == null) {
                        findWriteMethod(classType, propertyDescriptor);
                    }

                    typePropertyDescriptor.put(propertyDescriptor.getName(), propertyDescriptor);
                }

                CLASS_PROPERTY_DESCRIPTOR.putIfAbsent(classType, typePropertyDescriptor);
                typePropertyDescriptor = CLASS_PROPERTY_DESCRIPTOR.get(classType);
            }

            return typePropertyDescriptor;
        } catch (IntrospectionException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Returns true if the <code>PropertyDescriptor</code> name is class otherwise false.
     *
     * @param propertyDescriptor
     * @return
     */
    public final boolean isClassPropertyDescriptor(final PropertyDescriptor propertyDescriptor) {
        return (propertyDescriptor != null && CLASS.equals(propertyDescriptor.getName()));
    }

    /**
     * @param propertyDescriptor
     * @param methodType
     * @return
     */
    public final String toMutatorMethod(final PropertyDescriptor propertyDescriptor, final String methodType) {
        return String.format("%s%s%s", methodType, propertyDescriptor.getName().substring(0, 1).toUpperCase(),
                             propertyDescriptor.getName().substring(1));
    }

    /**
     * @param propertyDescriptor
     * @return
     */
    public final String getSetterMethod(final PropertyDescriptor propertyDescriptor) {
        return toMutatorMethod(propertyDescriptor, SET);
    }

    /**
     * @param propertyDescriptor
     * @return
     */
    public final String getGetterMethod(final PropertyDescriptor propertyDescriptor) {
        return toMutatorMethod(propertyDescriptor, GET);
    }

    /**
     * Returns the type of the property.
     *
     * @param propertyDescriptor
     * @return
     */
    public final Class<?> getReturnType(final PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getReadMethod().getReturnType();
    }

    /**
     * Ensures that the method is public and accessible.
     *
     * @param method
     */
    public final void ensurePublic(final Method method) {
        if (method != null && !Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            method.setAccessible(true);
        }
    }


    /**
     * @param classType
     * @param name
     * @return
     */
    public final PropertyDescriptor findByName(final Class<?> classType, final String name) {
        return findByClass(classType).get(name);
    }

    /**
     * Returns the properties for the provided class.
     *
     * @param classType
     * @return
     */
    public final Collection<PropertyDescriptor> getProperties(final Class<?> classType) {
        return findByClass(classType).values();
    }

    /**
     * @param propertyDescriptor
     * @param bean
     * @return
     */
    public final Object get(final PropertyDescriptor propertyDescriptor, final Object bean) {
        try {
            return propertyDescriptor.getReadMethod().invoke(bean);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex.getTargetException());
        }
    }

    /**
     * @param propertyDescriptor
     * @param bean
     * @param value
     */
    public final void set(PropertyDescriptor propertyDescriptor, Object bean, Object value) {
        try {
            propertyDescriptor.getWriteMethod().invoke(bean, value);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex.getTargetException());
        }
    }


    /**
     * @param classType
     * @return
     */
    public boolean isSimpleProperty(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return isSimpleValueType(classType) || classType.isArray() && isSimpleValueType(classType.getComponentType());
    }

    /**
     * @param classType
     * @return
     */
    public boolean isSimpleValueType(final Class<?> classType) {
        return isPrimitiveOrWrapper(classType) || classType.isEnum() || CharSequence.class
            .isAssignableFrom(classType) || Number.class.isAssignableFrom(classType) || Date.class
                   .isAssignableFrom(classType) || classType.equals(URI.class) || classType.equals(URL.class)
               || classType.equals(Locale.class) || classType.equals(Class.class);
    }

    /**
     * @param classType
     * @return
     */
    public boolean isPrimitiveWrapper(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return primitiveWrapperTypeMap.containsKey(classType);
    }

    /**
     * @param classType
     * @return
     */
    public boolean isPrimitiveOrWrapper(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return classType.isPrimitive() || isPrimitiveWrapper(classType);
    }
}