package com.rslakra.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
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
    public static final String REQUEST_TRACER = "requestTracer";
    public static final String CLASS = "class";
    public static final String IS = "is";
    public static final String GET = "get";
    public static final String SET = "set";
    public static final String ID = "id";
    // IMMUTABLE_PROPERTIES
    public static final Set<String> IMMUTABLE_PROPERTIES = Sets.asSet("createdOn", "createdAt", "createdBy", "createdBy", "updatedOn", "updatedAt", "updatedBy");
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
     * @param object
     * @return
     */
    public static boolean isNull(final Object object) {
        return Objects.isNull(object);
    }

    /**
     * @param object
     * @return
     */
    public static boolean isNotNull(final Object object) {
        return !isNull(object);
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
            } else if (object instanceof Class) {
                LOGGER.debug("object:{}", object);
                final Class classType = (Class) object;
                return (classType.isArray() || Array.class.isAssignableFrom(classType));
            }
        }

        return false;
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>Map</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isMap(final Object object) {
        return (isNotNull(object) && (object instanceof Map || (object instanceof Class && Map.class.isAssignableFrom(
                (Class) object))));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>List</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isList(final Object object) {
        return (isNotNull(object) && (object instanceof List || List.class.isAssignableFrom((Class) object)));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>Set</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isSet(final Object object) {
        return (isNotNull(object) && (object instanceof Set || Set.class.isAssignableFrom((Class) object)));
    }

    /**
     * Returns true if the <code>object</code> is a type of <code>Collection</code> otherwise false.
     *
     * @param object
     * @return
     */
    public static boolean isCollection(final Object object) {
        return (isNotNull(object) && (object instanceof Collection || Collection.class.isAssignableFrom(
                (Class) object)));
    }

    /**
     * Returns the length of the object.
     *
     * @param object
     * @return
     */
    public static int getLength(final Object object) {
        if (isNotNull(object)) {
//            final Class<?> classType = object.getClass();
            if (isArray(object)) {
                return ((Object[]) object).length;
            } else if (isMap(object)) {
                return ((Map) object).size();
            } else if (isCollection(object)) {
                return ((Collection) object).size();
//            } else {
//                throw new IllegalArgumentException("Unhandled class:" + object.getClass());
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
    public static boolean isNullOrEmpty(final Object object) {
        LOGGER.trace("+isNullOrEmpty({})", object);
        boolean result = false;
        if (isNull(object)) {
            result = true;
        } else if (object instanceof CharSequence && ((CharSequence) object).length() == 0) {
            result = true;
        } else if (isArray(object) && getLength(object) == 0) {
            result = true;
        } else if (object instanceof Map && ((Map) object).size() == 0) {
            result = true;
        } else if (object instanceof Collection && ((Collection) object).size() == 0) {
            result = true;
        }

        LOGGER.trace("-isNullOrEmpty(), result:{}", result);
        return result;
    }

    /**
     * @param object
     * @return
     */
    public static boolean isNotNullOrEmpty(final Object object) {
        return (!isNullOrEmpty(object));
    }

    /**
     * @param classType
     * @return
     */
    public static String getClassPath(Class<?> classType) {
        String classPath = null;
        if (isNotNull(classType)) {
            classPath = classType.getPackage().getName().replace(".", File.separator);
        }

        return classPath;
    }


    /**
     * Returns true if the method is getter otherwise false.
     *
     * @param method
     * @return
     */
    public static boolean isGetter(Method method) {
        if (method != null) {
            if (!(method.getName().startsWith("get") || method.getName().startsWith("is"))) {
                return false;
            } else if (method.getParameterTypes().length != 0) {
                return false;
            } else if (void.class.equals(method.getReturnType())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the method is setter otherwise false.
     *
     * @param method
     * @return
     */
    public static boolean isSetter(Method method) {
        if (method != null) {
            if (!method.getName().startsWith("set")) {
                return false;
            } else if (method.getParameterTypes().length != 1) {
                return false;
            } else if (!void.class.equals(method.getReturnType())) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param charSequence
     * @param separator
     * @return
     */
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
     * @param charSequence
     * @return
     */
    public static String upperCaseFirstLetter(final CharSequence charSequence) {
        if (isNull(charSequence)) {
            return null;
        } else {

        }
        int firstLetterIndex = 0;

        for (int limit = charSequence.length() - 1;
             !Character.isLetter(charSequence.charAt(firstLetterIndex)) && firstLetterIndex < limit;
             ++firstLetterIndex) {
            // do nothing
        }

        char firstLetter = charSequence.charAt(firstLetterIndex);
        if (Character.isUpperCase(firstLetter)) {
            return charSequence.toString();
        } else {
            final String fullString = charSequence.toString();
            char upperCasedFirstLetter = Character.toUpperCase(firstLetter);
            return (firstLetterIndex == 0 ? upperCasedFirstLetter + fullString.substring(1)
                    : fullString.substring(0, firstLetterIndex) + upperCasedFirstLetter
                    + fullString.substring(firstLetterIndex + 1));
        }
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
            final Class<?> sourceClass = source.getClass();
            if (isNotNull(target)) {
                if (BigDecimal.class.isAssignableFrom(sourceClass)) {
                    return ((BigDecimal) source).compareTo((BigDecimal) target) == 0;
                } else if (Date.class.isAssignableFrom(sourceClass)) {
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
            Map<String, PropertyDescriptor> typePropertyDescriptor = CLASS_PROPERTY_DESCRIPTORS.get(classType);
            if (typePropertyDescriptor == null) {
                typePropertyDescriptor = new LinkedHashMap();
                final PropertyDescriptor[] propertyDescriptors = getBeanInfo(classType);
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getWriteMethod() == null) {
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
        return isSimpleValueType(classType) || classType.isArray() && isSimpleValueType(classType.getComponentType());
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
        for (PropertyDescriptor propertyDescriptor : BeanUtils.INSTANCE.getBeanInfo(target.getClass())) {
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
        } else if (isSet(responseType)) {
            if (isArray(response)) {
                return (T) Arrays.asList(response).stream().collect(Collectors.toSet());
            } else if (isCollection(response)) {
                return (T) response.stream().collect(Collectors.toSet());
            }
        } else if (isList(responseType)) {
            if (isArray(response)) {
                return (T) Arrays.asList(response);
            } else if (isCollection(response)) {
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
            } else if (isMap(response)) {
                return INSTANCE.arrayFromMap((Map) response, responseType, true);
            } else if (isCollection(response)) {
                return INSTANCE.arrayFromCollection((Collection<T>) response, responseType);
            }
        } else if (isMap(responseType)) {
        } else if (isCollection(responseType)) {
            if (isMap(response)) {
                return INSTANCE.arrayFromMap((Map) response, responseType, true);
            } else if (isCollection(response)) {
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
        return BeanUtils.isNullOrEmpty(self) ? BeanUtils.EMPTY_STR
                : BeanUtils.EMPTY_STR + Character.toUpperCase(self.charAt(0))
                + self.subSequence(1, self.length());
    }

}
