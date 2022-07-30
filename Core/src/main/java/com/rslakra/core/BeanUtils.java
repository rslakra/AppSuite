/******************************************************************************
 * Copyright (C) Devamatre 2009 - 2022. All rights reserved.
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 5/27/20 2:13 PM
 */
public enum BeanUtils {
    INSTANCE;

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);
    public static final String EMPTY_STR = "";
    public static final String CLASS = "class";
    public static final String IS = "is";
    public static final String GET = "get";
    public static final String SET = "set";
    public static final String ID = "id";
    // IMMUTABLE_PROPERTIES
    public static final Set<String>
            IMMUTABLE_PROPERTIES =
            Sets.asSet("createdOn", "createdAt", "createdBy", "createdBy", "updatedOn", "updatedAt", "updatedBy");
    // PRIMITIVE_WRAPPER_TYPES
    private final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_TYPES = new HashMap(8);
    // IMMUTABLE_ATTRIBUTES
    private final Set<String> IMMUTABLE_ATTRIBUTES = new HashSet(Arrays.asList(ID));
    // CLASS_PROPERTY_DESCRIPTORS
    private final Map<Class<?>, Map<String, PropertyDescriptor>> CLASS_PROPERTY_DESCRIPTORS = new ConcurrentHashMap();
    // CLASS_PROPERTIES
    private final ConcurrentMap<Class<?>, ClassProperties> CLASS_PROPERTIES = new ConcurrentHashMap(256);

    private BeanUtils() {
        PRIMITIVE_WRAPPER_TYPES.put(Boolean.class, Boolean.TYPE);
        PRIMITIVE_WRAPPER_TYPES.put(Byte.class, Byte.TYPE);
        PRIMITIVE_WRAPPER_TYPES.put(Character.class, Character.TYPE);
        PRIMITIVE_WRAPPER_TYPES.put(Double.class, Double.TYPE);
        PRIMITIVE_WRAPPER_TYPES.put(Float.class, Float.TYPE);
        PRIMITIVE_WRAPPER_TYPES.put(Integer.class, Integer.TYPE);
        PRIMITIVE_WRAPPER_TYPES.put(Long.class, Long.TYPE);
        PRIMITIVE_WRAPPER_TYPES.put(Short.class, Short.TYPE);
    }

    /**
     * Returns true if the provided reference is null otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isNull(final Object object) {
        return Objects.isNull(object);
    }

    /**
     * Returns true if the provided reference is not null otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }

    /**
     * Returns true if the provided references are not null otherwise false.
     *
     * @param object
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> boolean isNotNull(final Object object, final Class<T> classType) {
        return (!isNull(object) && !isNull(classType));
    }

    /**
     * Returns true if the readable sequence of char values has white-space otherwise false.
     *
     * @param input
     * @return
     */
    public static boolean hasBlanks(final CharSequence input) {
        if (isNotNull(input)) {
            for (int index = 0; index < input.length(); index++) {
                if (Character.isWhitespace(input.charAt(index))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if the readable sequence of char values  are not a white-space otherwise false.
     *
     * @param input
     * @return
     */
    public static boolean isBlank(final CharSequence input) {
        if (isNotNull(input)) {
            for (int index = 0; index < input.length(); index++) {
                if (!Character.isWhitespace(input.charAt(index))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @param input
     * @return
     */
    public static boolean isNotBlank(final CharSequence input) {
        return !isBlank(input);
    }

    /**
     * Returns true if the object is an array otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isArray(final Object object) {
        if (isNotNull(object)) {
            if (object instanceof Object[]) {
                return true;
            } else if (object.getClass().isArray()) {
                return true;
            } else if (isTypeOf(object, Class.class)) {
                LOGGER.debug("object:{}", object);
                final Class classType = (Class) object;
                return (classType.isArray() || Array.class.isAssignableFrom(classType));
            }
        }

        return false;
    }

    /**
     * Returns true if the provided Object is assignment-compatible with the object represented by the
     * <code>Class<T></code> otherwise false.
     *
     * @param object
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> boolean isTypeOf(final Object object, final Class<T> classType) {
        return (isNotNull(classType) && classType.isInstance(object));
    }

    /**
     * Returns true if the class or interface represented by the <code>Class<T></code> classObject is either the same
     * as, or is a superclass or superinterface of, the class or interface represented by the specified Class parameter.
     * It returns true if so; otherwise it returns false. If this Class classObject represents a primitive type, this
     * method returns true if the specified Class parameter is exactly this Class classObject; otherwise it returns
     * false.
     *
     * @param classObject
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> boolean isAssignableFrom(final Object classObject, final Class<T> classType) {
        return (isTypeOf(classObject, Class.class) && classType.isAssignableFrom((Class) classObject));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>Map</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isTypeOfMap(final Object object) {
        return (isTypeOf(object, Map.class) || isAssignableFrom(object, Map.class));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>List</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isTypeOfList(final Object object) {
        return (isTypeOf(object, List.class) || isAssignableFrom(object, List.class));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>Set</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isTypeOfSet(final Object object) {
        return (isTypeOf(object, Set.class) || isAssignableFrom(object, Set.class));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>Collection</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isTypeOfCollection(final Object object) {
        return (isTypeOf(object, Collection.class) || isAssignableFrom(object, Collection.class));
    }

    /**
     * @param object
     * @return
     */
    public static boolean isTypeOfIterable(final Object object) {
        return (isTypeOf(object, Iterable.class) || isAssignableFrom(object, Iterable.class));
    }

    /**
     * @param object
     * @return
     */
    public static boolean isTypeOfIterator(final Object object) {
        return (isTypeOf(object, Iterator.class) || isAssignableFrom(object, Iterator.class));
    }

    /**
     * @param object
     * @return
     */
    public static boolean isTypeOfEnumeration(final Object object) {
        return (isTypeOf(object, Enumeration.class) || isAssignableFrom(object, Enumeration.class));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>CharSequence</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isTypeOfCharSequence(final Object object) {
        return (isTypeOf(object, CharSequence.class) || isAssignableFrom(object, CharSequence.class));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>BigDecimal</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isTypeOfBigDecimal(final Object object) {
        return (isTypeOf(object, BigDecimal.class) || isAssignableFrom(object, BigDecimal.class));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>Date</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isTypeOfDate(final Object object) {
        return (isTypeOf(object, Date.class) || isAssignableFrom(object, Date.class));
    }

    /**
     * @param object
     * @return
     */
    public static boolean isTypeOfThrowable(final Object object) {
        return (isTypeOf(object, Throwable.class) || isAssignableFrom(object, Throwable.class));
    }

    /**
     * @param iterator
     * @return
     */
    public static int sizeIterator(final Iterator<?> iterator) {
        int size = 0;
        if (iterator != null) {
            while (iterator.hasNext()) {
                iterator.next();
                size++;
            }
        }

        return size;
    }

    /**
     * @param iterable
     * @return
     */
    public static int sizeIterable(final Iterable<?> iterable) {
        return (isNotNull(iterable) ? sizeIterator(iterable.iterator()) : 0);
    }

    /**
     * @param enumeration
     * @return
     */
    public static int sizeEnumeration(final Enumeration<?> enumeration) {
        int size = 0;
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                enumeration.nextElement();
                size++;
            }
        }

        return size;
    }

    /**
     * Returns the length of the object.
     *
     * @param object
     * @return
     */
    public static int getLength(final Object object) {
        if (isNotNull(object)) {
            if (isArray(object)) {
                return ((Object[]) object).length;
            } else if (isTypeOfMap(object)) {
                return ((Map) object).size();
            } else if (isTypeOfCollection(object)) {
                return ((Collection) object).size();
            } else if (isTypeOfIterator(object)) {
                return sizeIterator((Iterator<?>) object);
            } else if (isTypeOfIterable(object)) {
                return sizeIterable((Iterable) object);
            } else if (isTypeOfEnumeration(object)) {
                return sizeEnumeration((Enumeration<?>) object);
            } else if (isTypeOfCharSequence(object)) {
                return ((CharSequence) object).length();
            }
        }

        return 0;
    }

    /**
     * Returns true if the <code>object</code> is null or empty otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(final Object object) {
        LOGGER.trace("+isEmpty({})", object);
        boolean result = false;
        if (isNull(object)) {
            result = true;
        } else if (isArray(object) && getLength(object) == 0) {
            result = true;
        } else if (isTypeOfMap(object) && getLength(object) == 0) {
            result = true;
        } else if (isTypeOfCollection(object) && getLength(object) == 0) {
            result = true;
        } else if (isTypeOfIterator(object) && getLength(object) == 0) {
            result = true;
        } else if (isTypeOfIterable(object) && getLength(object) == 0) {
            result = true;
        } else if (isTypeOfEnumeration(object) && getLength(object) == 0) {
            result = true;
        } else if (isTypeOfCharSequence(object) && getLength(object) == 0) {
            result = true;
        }

        LOGGER.trace("-isEmpty(), result:{}", result);
        return result;
    }

    /**
     * Returns true if the <code>object</code> is null or empty otherwise false.
     *
     * @param objects
     * @return
     */
    public static boolean isNotEmpty(final Object... objects) {
        if (isNotNull(objects) && getLength(objects) > 0) {
            for (int index = 0; index < objects.length; index++) {
                if (isEmpty(objects[index])) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /**
     * @param object
     * @param message
     */
    public static void notNull(final Object object, final String message) {
        if (isNull(object)) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Capitalize the string.
     *
     * @param self
     * @return
     */
    public static String toTitleCase(final CharSequence self) {
        return isEmpty(self) ? EMPTY_STR
                : EMPTY_STR + Character.toUpperCase(self.charAt(0)) + self.subSequence(1, self.length());
    }


    /**
     * @param pathSegments
     * @return
     */
    public static String pathSegments(final String... pathSegments) {
        Objects.nonNull(pathSegments);
        return String.join(File.separator, pathSegments);
    }

    /**
     * Returns the unique random <code>UUID</code> string.
     *
     * @return
     */
    public static String nextUuid() {
        return UUID.randomUUID().toString();
    }


    /**
     * Returns true if the <code>source</code> and <code>target</code> objects are same otherwise false.
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equals(final Object source, final Object target) {
        if (source == target) {
            return true;
        } else if (isNotNull(source)) {
            if (isNotNull(target)) {
                if (isTypeOfBigDecimal(source.getClass())) {
                    return ((BigDecimal) source).compareTo((BigDecimal) target) == 0;
                } else if (isTypeOfDate(source.getClass())) {
                    return ((Date) source).getTime() == ((Date) target).getTime();
                }

                return source.equals(target);
            }
        }

        return false;
    }


    /**
     * @param source
     * @param target
     * @return
     */
    public static boolean notEquals(final Object source, final Object target) {
        return !equals(source, target);
    }

    /**
     * @param charSequence
     */
    public static byte[] toBytes(final CharSequence charSequence) {
        byte[] dataBytes = null;
        if (BeanUtils.isNotEmpty(charSequence)) {
            dataBytes = new byte[charSequence.length()];
            for (int i = 0; i < charSequence.length(); i++) {
                char cChar = charSequence.charAt(i);
                if (cChar > 0xff) {
                    throw new IllegalArgumentException(
                            "Invalid Character: " + (cChar) + " at index:" + (i + 1) + " in string: " + charSequence);
                }
                dataBytes[i] = (byte) cChar;
            }
        }

        return dataBytes;
    }

    /**
     * @param classType
     * @return
     */
    public static String getClassPath(final Class<?> classType) {
        assert classType != null;
        return classType.getPackage().getName().replace(".", "/");
    }

    /**
     * @param klass
     * @param pathString
     * @return
     */
    public static String getClassPath(final Class<?> klass, final String pathString) {
        String classPath = getClassPath(klass);
        if (BeanUtils.isNotEmpty(pathString)) {
            classPath += (pathString.startsWith(File.separator) ? "" : File.separator) + pathString;
        }

        return classPath;
    }

    /**
     * @param inputValues
     * @param partitionSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> partitionBySize(final List<T> inputValues, final int partitionSize) {
        if (isEmpty(inputValues) || partitionSize <= 0) {
            return Collections.emptyList();
        } else if (inputValues.size() <= partitionSize) {
            return Collections.singletonList(inputValues);
        } else {
            final AtomicInteger counter = new AtomicInteger(0);
            return new ArrayList<>(
                    inputValues.stream().collect(Collectors.groupingBy(item -> counter.getAndIncrement() / partitionSize))
                            .values());
        }
    }

    /**
     * @param inputValues
     * @param partitionSize
     * @param <T>
     * @return
     */
    public static <T> Set<Set<T>> partitionBySize(final Set<T> inputValues, final int partitionSize) {
        if (isEmpty(inputValues) || partitionSize <= 0) {
            return Collections.emptySet();
        } else if (inputValues.size() <= partitionSize) {
            return Collections.singleton(inputValues);
        } else {
            final AtomicInteger counter = new AtomicInteger(0);
            final Collection<Set<T>> partitions = inputValues.stream()
                    .collect(Collectors.groupingBy(item -> counter.getAndIncrement() / partitionSize, Collectors.toSet()))
                    .values();
            return new HashSet<>(partitions);
        }
    }

    /**
     * @param typeClass
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T findEnumByClass(final Class<T> typeClass, final String name) {
        return Arrays.stream(typeClass.getEnumConstants())
                .filter(e -> ((Enum) e).name().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    /**
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readContents(final InputStream inputStream) throws IOException {
        final StringBuilder sBuilder = new StringBuilder();
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line);
            }
        }

        return sBuilder.toString();
    }

    /**
     * @param fileName
     * @return
     */
    public static String readFile(final String fileName) {
        try {
            return readContents(BeanUtils.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Returns true if the value is not null and equal to zero otherwise false.
     *
     * @param value
     * @return
     */
    public static boolean isZero(final BigDecimal value) {
        return (isNotNull(value) && value.signum() == 0);
    }

    /**
     * Returns true if the value is not null and greater than zero otherwise false.
     *
     * @param value
     * @return
     */
    public static boolean isPositive(final BigDecimal value) {
        return (isNotNull(value) && value.signum() == 1);
    }


    /**
     * Returns true if the value is not null and less than zero otherwise false.
     *
     * @param value
     * @return
     */
    public boolean isNegative(final BigDecimal value) {
        return (isNotNull(value) && value.signum() == -1);
    }

    /**
     *
     */
    public static void logCallerClassNameAndMethodName() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String callerClass = "unknown";
        String callerMethod = "unknown";

        for (int index = 0; index < 6; index++) {
            StackTraceElement element = stack[index];
            LOGGER.debug(String.format("index=%d, lineNumber=%d, className=%s, methodName=%s", index,
                    element.getLineNumber(), element.getClassName(),
                    element.getMethodName()));
        }

        if (stack != null && stack.length > 2) {
            callerClass = stack[2].getClassName();
            callerMethod = stack[2].getMethodName();
        }
        LOGGER.info("logCallerClassNameAndMethodName", "caller class=%s, method=%s", callerClass, callerMethod);

    }

    /**
     * @param source
     * @param responseType
     * @param <T>
     * @return
     */
    private <T> T arrayFromObject(final Object source, Class<T> responseType) {
        LOGGER.debug("+arrayFromObject({}, {})", source, responseType);
        final int length = getLength(source);
        LOGGER.debug("length:{}", length);
        Object[] toObject = null;
        if (responseType.isAssignableFrom(String[].class)) {
            toObject = new String[length];
        } else if (responseType.isAssignableFrom(Character[].class)) {
            toObject = new Character[length];
        } else if (responseType.isAssignableFrom(Byte[].class)) {
            toObject = new Byte[length];
        } else if (responseType.isAssignableFrom(Short[].class)) {
            toObject = new Short[length];
        } else if (responseType.isAssignableFrom(Integer[].class)) {
            toObject = new Integer[length];
        } else if (responseType.isAssignableFrom(Long[].class)) {
            toObject = new Long[length];
        } else if (responseType.isAssignableFrom(Float[].class)) {
            toObject = new Float[length];
        } else if (responseType.isAssignableFrom(Double[].class)) {
            toObject = new Double[length];
        } else if (responseType.isAssignableFrom(BigDecimal[].class)) {
            toObject = new BigDecimal[length];
        } else {
            toObject = new Object[length];
        }

        System.arraycopy(source, 0, toObject, 0, length);
        LOGGER.debug("-arrayFromObject(), toObject:{}", (T) toObject);
        return (T) toObject;
    }

    /**
     * Converts the collection <code>response</code> to an array.
     *
     * @param response
     * @param responseType
     * @param <T>
     * @return
     */
    private <T> T arrayFromCollection(final Collection<T> response, Class<T> responseType) {
        LOGGER.debug("+arrayFromCollection({}, {})", response, responseType);
        if (BeanUtils.isArray(responseType)) {
            if (responseType.isAssignableFrom(String[].class)) {
                return (T) response.toArray(new String[response.size()]);
            } else if (responseType.isAssignableFrom(Character[].class)) {
                return (T) response.toArray(new Character[response.size()]);
            } else if (responseType.isAssignableFrom(Byte[].class)) {
                return (T) response.toArray(new Byte[response.size()]);
            } else if (responseType.isAssignableFrom(Short[].class)) {
                return (T) response.toArray(new Short[response.size()]);
            } else if (responseType.isAssignableFrom(Integer[].class)) {
                return (T) response.toArray(new Integer[response.size()]);
            } else if (responseType.isAssignableFrom(Float[].class)) {
                return (T) response.toArray(new Float[response.size()]);
            } else if (responseType.isAssignableFrom(Double[].class)) {
                return (T) response.toArray(new Double[response.size()]);
            } else {
                return (T) response.toArray(new Object[response.size()]);
            }
        } else if (isTypeOfSet(responseType)) {
            if (isArray(response)) {
                return (T) Arrays.asList(response).stream().collect(Collectors.toSet());
            } else if (isTypeOfCollection(response)) {
                return (T) response.stream().collect(Collectors.toSet());
            }
        } else if (isTypeOfList(responseType)) {
            if (isArray(response)) {
                return (T) Arrays.asList(response);
            } else if (isTypeOfCollection(response)) {
                return (T) response.stream().collect(Collectors.toList());
            }
        }

        LOGGER.debug("-arrayFromCollection()", response, responseType);
        return (T) response;
    }

    /**
     * Converts the collection <code>response</code> to an array.
     *
     * @param response
     * @param responseType
     * @param <T>
     * @return
     */
    private <T> T arrayFromMap(final Map response, Class<T> responseType, final boolean keysArray) {
        if (keysArray) {
            return (T) arrayFromCollection((Collection) response.keySet(), responseType);
        } else {
            return (T) arrayFromCollection((Collection) response.values(), responseType);
        }
    }

    /**
     * Converts the <code>response</code> to an array.
     *
     * @param response
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T toType(final Object response, Class<T> responseType) {
        if (isArray(responseType)) {
            if (isArray(response)) {
                return INSTANCE.arrayFromObject(response, responseType);
            } else if (isTypeOfMap(response)) {
                return INSTANCE.arrayFromMap((Map) response, responseType, true);
            } else if (isTypeOfCollection(response)) {
                return INSTANCE.arrayFromCollection((Collection<T>) response, responseType);
            }
        } else if (isTypeOfMap(responseType)) {
        } else if (isTypeOfCollection(responseType)) {
            if (isTypeOfMap(response)) {
                return INSTANCE.arrayFromMap((Map) response, responseType, true);
            } else if (isTypeOfCollection(response)) {
                return INSTANCE.arrayFromCollection((Collection<T>) response, responseType);
            }
        }

        return (T) response;
    }

    /**
     * Capitalize the string.
     *
     * @param self
     * @return
     */
    public static String capitalize(final CharSequence self) {
        return BeanUtils.isEmpty(self) ? BeanUtils.EMPTY_STR
                : BeanUtils.EMPTY_STR + Character.toUpperCase(self.charAt(0))
                + self.subSequence(1, self.length());
    }

    /**
     * @param name
     * @return
     */
    private boolean isModifiable(final String name) {
        return !IMMUTABLE_ATTRIBUTES.contains(name);
    }

    /**
     * Returns the <code>PropertyDescriptor[]</code> for the given class.
     *
     * @param classType
     * @return
     * @throws IntrospectionException
     */
    public static PropertyDescriptor[] getBeanInfo(final Class<?> classType) throws IntrospectionException {
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
            Map<String, PropertyDescriptor> typePropertyDescriptor = CLASS_PROPERTY_DESCRIPTORS.get(classType);
            if (typePropertyDescriptor == null) {
                typePropertyDescriptor = new LinkedHashMap();
                final PropertyDescriptor[] propertyDescriptors = getBeanInfo(classType);
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (isNull(propertyDescriptor.getWriteMethod())) {
                        findWriteMethod(classType, propertyDescriptor);
                    }

                    typePropertyDescriptor.put(propertyDescriptor.getName(), propertyDescriptor);
                }

                CLASS_PROPERTY_DESCRIPTORS.putIfAbsent(classType, typePropertyDescriptor);
                typePropertyDescriptor = CLASS_PROPERTY_DESCRIPTORS.get(classType);
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
     * @param propName
     * @return
     */
    public final String toMutatorMethod(final String prefix, final String propName) {
        return String.format("%s%s%s", prefix, propName.substring(0, 1).toUpperCase(), propName.substring(1));
    }

    /**
     * @param propertyDescriptor
     * @param methodType
     * @return
     */
    public final String toMutatorMethod(final PropertyDescriptor propertyDescriptor, final String methodType) {
        return toMutatorMethod(methodType, propertyDescriptor.getName());
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
        if (propertyDescriptor.getPropertyType().isAssignableFrom(Boolean.class)) {
            return toMutatorMethod(propertyDescriptor, IS);
        }

        return toMutatorMethod(propertyDescriptor, GET);
    }

    /**
     * @param method
     * @return
     */
    private boolean hasGetPrefix(final Method method) {
        return (isNotNull(method) && method.getName().startsWith(GET) && method.getName().length() > GET.length());
    }

    /**
     * @param method
     * @return
     */
    private boolean hasIsPrefix(final Method method) {
        return (isNotNull(method) && method.getName().startsWith(IS) && method.getName().length() > IS.length());
    }

    /**
     * @param method
     * @return
     */
    private boolean hasSetPrefix(final Method method) {
        return (isNotNull(method) && method.getName().startsWith(SET) && method.getName().length() > SET.length());
    }

    /**
     * Returns true if the <code>method</code> is getter method otherwise false.
     *
     * @param method
     * @return
     */
    public static boolean isGetter(final Method method) {
        return (isNotNull(method) && method.getParameterTypes().length == 0 && (INSTANCE.hasIsPrefix(method) || INSTANCE.hasGetPrefix(method)));
    }

    /**
     * Returns true if the <code>method</code> is setter method otherwise false.
     *
     * @param method
     * @return
     */
    public static boolean isSetter(final Method method) {
        return (isNotNull(method) && method.getParameterTypes().length == 1 && INSTANCE.hasSetPrefix(method));
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
        if (isNotNull(method) && !Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            method.setAccessible(true);
        }
    }

    /**
     * @param classType
     * @param propertyName
     * @return
     */
    public final PropertyDescriptor findByPropertyName(final Class<?> classType, final String propertyName) {
        return findByClass(classType).get(propertyName);
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
    public boolean isPrimitiveWrapper(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return PRIMITIVE_WRAPPER_TYPES.containsKey(classType);
    }

    /**
     * @param classType
     * @return
     */
    public boolean isPrimitiveOrWrapper(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return classType.isPrimitive() || isPrimitiveWrapper(classType);
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
    public boolean isSimpleProperty(final Class<?> classType) {
        Objects.requireNonNull(classType, "Class must not be null!");
        return isSimpleValueType(classType) || classType.isArray() && isSimpleValueType(
                classType.getComponentType());
    }

    /**
     * @param classType
     * @return
     */
    private ClassProperties getPropertyDescriptors(final Class<?> classType) {
        ClassProperties classProperties = this.CLASS_PROPERTIES.get(classType);
        if (classProperties == null) {
            this.CLASS_PROPERTIES.putIfAbsent(classType, new ClassProperties(classType));
            classProperties = this.CLASS_PROPERTIES.get(classType);
        }

        return classProperties;
    }

    /**
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T newInstance(final Class<T> classType) throws ReflectiveOperationException {
        return (isNull(classType) ? null : classType.getDeclaredConstructor().newInstance());
    }

    /**
     * The <code>BeanPropertyDescriptor</code> contains the bean <code>name</code>, <code>classType</code>,
     * <code>readMethod</code>, and <code>writeMethod</code>.
     */
    private final class BeanPropertyDescriptor {

        public final String name;
        public final Class<?> classType;
        public final Method readMethod;
        public final Method writeMethod;

        /**
         * @param propertyDescriptor
         */
        public BeanPropertyDescriptor(final PropertyDescriptor propertyDescriptor) {
            this.name = propertyDescriptor.getName();
            classType = propertyDescriptor.getReadMethod().getReturnType();
            this.readMethod = propertyDescriptor.getReadMethod();
            this.writeMethod = propertyDescriptor.getWriteMethod();
        }

        /**
         * @return
         * @throws IllegalAccessException
         * @throws InstantiationException
         */
        public Object newInstance() throws ReflectiveOperationException {
            if (classType.equals(Set.class)) {
                return new HashSet<>();
            } else if (classType.equals(Map.class)) {
                return new HashMap<>();
            } else if (classType.equals(List.class)) {
                return new ArrayList<>();
            } else {
                return BeanUtils.newInstance(classType);
            }
        }
    }

    /**
     * Contains the class properties.
     */
    private final class ClassProperties {

        // readProperties
        public final Map<String, BeanPropertyDescriptor> readProperties;
        // writeProperties
        public final BeanPropertyDescriptor[] writeProperties;

        /**
         * @param classType
         */
        public ClassProperties(final Class<?> classType) {
            final Collection<PropertyDescriptor> allProperties = getProperties(classType);
            this.readProperties = new HashMap(allProperties.size(), 1.0F);
            final List<BeanPropertyDescriptor> writeList = new ArrayList(allProperties.size());
            allProperties.forEach(propertyDescriptor -> {
                ensurePublic(propertyDescriptor.getReadMethod());
                ensurePublic(propertyDescriptor.getWriteMethod());
                if (propertyDescriptor.getReadMethod() != null) {
                    this.readProperties
                            .put(propertyDescriptor.getName(), new BeanPropertyDescriptor(propertyDescriptor));
                }

                if (propertyDescriptor.getWriteMethod() != null) {
                    writeList.add(new BeanPropertyDescriptor(propertyDescriptor));
                }
            });

            this.writeProperties = writeList.toArray(new BeanPropertyDescriptor[writeList.size()]);
        }
    }

    /**
     * @param source
     * @param target
     * @param ignoredProperties
     * @throws IllegalStateException
     */
    public static void copyProperties(final Object source, final Object target, final String... ignoredProperties)
            throws IllegalStateException {
        Objects.requireNonNull(source, "Source must not be null!");
        Objects.requireNonNull(target, "Target must not be null!");
        Object name = null;
        try {
            final ClassProperties sourceProperties = INSTANCE.getPropertyDescriptors(source.getClass());
            final ClassProperties targetProperties = INSTANCE.getPropertyDescriptors(target.getClass());
            final Set<String> ignoreLookup = Sets.asSet(ignoredProperties);
            final BeanPropertyDescriptor[] writeProperties = targetProperties.writeProperties;
            for (int i = 0; i < writeProperties.length; ++i) {
                BeanPropertyDescriptor targetProperty = writeProperties[i];
                name = targetProperty.name;
                if (!ignoreLookup.contains(targetProperty.name)) {
                    BeanPropertyDescriptor sourceProperty = sourceProperties.readProperties.get(targetProperty.name);
                    if (sourceProperty != null) {
                        final Object value = sourceProperty.readMethod.invoke(source);
                        try {
                            targetProperty.writeMethod.invoke(target, value);
                        } catch (IllegalArgumentException ex) {
                            //ignore me
                        }
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            LOGGER.error("copy property %s:%s", new Object[]{target.getClass().getName(), name});
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param source
     * @param target
     * @param ignoredProperties
     * @throws IllegalStateException
     */
    public static void deepCopyProperties(final Object source, final Object target, final String... ignoredProperties)
            throws IllegalStateException {
        Objects.requireNonNull(source, "Source must not be null!");
        Objects.requireNonNull(target, "Target must not be null!");
        Object name = null;
        try {
            final ClassProperties sourceProperties = INSTANCE.getPropertyDescriptors(source.getClass());
            final ClassProperties targetProperties = INSTANCE.getPropertyDescriptors(target.getClass());
            final Set<String> ignoreLookup = Sets.asSet(ignoredProperties);
            final BeanPropertyDescriptor[] writeProperties = targetProperties.writeProperties;
            for (int i = 0; i < writeProperties.length; ++i) {
                BeanPropertyDescriptor targetProperty = writeProperties[i];
                name = targetProperty.name;
                if (!ignoreLookup.contains(targetProperty.name)) {
                    BeanPropertyDescriptor sourceProperty = sourceProperties.readProperties.get(targetProperty.name);
                    if (sourceProperty != null) {
                        if (INSTANCE.isSimpleProperty(sourceProperty.classType)) {
                            final Object value = sourceProperty.readMethod.invoke(source);
                            try {
                                targetProperty.writeMethod.invoke(target, value);
                            } catch (IllegalArgumentException ex) {
                                //ignore me
                            }
                        } else {
                            try {
                                final Object sourceObject = sourceProperty.readMethod.invoke(source);
                                final Object targetObject = BeanUtils.newInstance(targetProperty.classType);
                                deepCopyProperties(sourceObject, targetObject);
                                try {
                                    targetProperty.writeMethod.invoke(target, targetObject);
                                } catch (IllegalArgumentException ex) {
                                    //ignore me
                                }
                            } catch (InstantiationException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (ReflectiveOperationException ex) {
            LOGGER.error("deepCopyProperties - property %s:%s", new Object[]{target.getClass().getName(), name});
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param source
     * @param target
     * @param ignoredProperties
     * @return
     * @throws Exception
     */
    public List<PropertyDescriptor> mergeObject(final Object source, final Object target,
                                                final Set<String> ignoredProperties) throws Exception {
        final List<PropertyDescriptor> updatedProperties = new ArrayList<>();
        // Iterate over all the attributes
        for (PropertyDescriptor propertyDescriptor : BeanUtils.getBeanInfo(target.getClass())) {
            // Only copy writable attributes
            if (propertyDescriptor.getWriteMethod() != null) {
                final String propName = propertyDescriptor.getName();
                if (ignoredProperties.contains(propName)) {
                    continue;
                }
                Object sourceValue = propertyDescriptor.getReadMethod().invoke(source);
                Object destValue = propertyDescriptor.getReadMethod().invoke(target);
                if (isNotNull(sourceValue) && notEquals(sourceValue, destValue)) {
                    updatedProperties.add(propertyDescriptor);
                    propertyDescriptor.getWriteMethod().invoke(target, sourceValue);
                }
            }
        }

        return updatedProperties;
    }


    public static String separateCamelCase(final CharSequence charSequence, final String separator) {
        if (isNull(charSequence)) {
            return null;
        } else if (isNull(separator)) {
            return charSequence.toString();
        } else {
            final StringBuilder translation = new StringBuilder();
            int i = 0;
            for (int length = charSequence.length(); i < length; ++i) {
                char character = charSequence.charAt(i);
                if (Character.isUpperCase(character) && translation.length() != 0) {
                    translation.append(separator);
                }

                translation.append(character);
            }

            return translation.toString();
        }
    }

    /**
     * @param text
     * @return
     */
    public static String toSentenceCase(final CharSequence text) {
        if (isNull(text)) {
            return null;
        }

        int firstLetterIndex = 0;
        for (int limit = text.length() - 1;
             !Character.isLetter(text.charAt(firstLetterIndex)) && firstLetterIndex < limit;
             ++firstLetterIndex) {
            // do nothing
        }

        char firstLetter = text.charAt(firstLetterIndex);
        if (Character.isUpperCase(firstLetter)) {
            return text.toString();
        } else {
            final String fullString = text.toString();
            char upperCaseLetter = Character.toUpperCase(firstLetter);
            return (firstLetterIndex == 0 ? upperCaseLetter + fullString.substring(1)
                    : fullString.substring(0, firstLetterIndex) + upperCaseLetter
                    + fullString.substring(firstLetterIndex + 1));
        }
    }

    /**
     * Returns empty if null otherwise string.
     *
     * @param object
     * @return
     */
    public static String toString(final Throwable throwable) {
        if (isNull(throwable)) {
            return EMPTY_STR;
        }

        final StringWriter strWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(strWriter));
        return strWriter.toString();
    }

    /**
     * Returns empty if null otherwise string.
     *
     * @param object
     * @return
     */
    public static String toString(final Object object) {
        if (isTypeOfBigDecimal(object)) {
            return ((BigDecimal) object).toPlainString();
        } else if (isTypeOfThrowable(object)) {
            return toString((Throwable) object);
        }

        return Objects.toString(object);
    }

}
