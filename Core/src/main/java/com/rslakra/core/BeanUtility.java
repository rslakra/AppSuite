package com.rslakra.core;

import com.devamatre.logger.LogManager;
import com.devamatre.logger.Logger;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Rohtash Lakra
 * @created 5/27/20 2:13 PM
 */
public enum BeanUtility {

    INSTANCE;

    private final Logger logger = LogManager.getLogger(BeanUtility.class);
    private final ConcurrentMap<Class<?>, ClassProperties> CACHE;

    private BeanUtility() {
        CACHE = new ConcurrentHashMap(256);
    }

    /**
     * @param classType
     * @return
     */
    private ClassProperties getPropertyDescriptors(final Class<?> classType) {
        ClassProperties classProperties = CACHE.get(classType);
        if (classProperties == null) {
            CACHE.putIfAbsent(classType, new ClassProperties(classType));
            classProperties = (ClassProperties) CACHE.get(classType);
        }

        return classProperties;
    }

    /**
     * @param source
     * @param target
     * @param ignoredProperties
     * @throws IllegalStateException
     */
    public void copyProperties(final Object source, final Object target, final String... ignoredProperties)
            throws IllegalStateException {
        Objects.requireNonNull(source, "Source must not be null!");
        Objects.requireNonNull(target, "Target must not be null!");
        Object name = null;
        try {
            final ClassProperties sourceProperties = getPropertyDescriptors(source.getClass());
            final ClassProperties targetProperties = getPropertyDescriptors(target.getClass());
            final Set<String> ignoreLookup = Sets.asSet(ignoredProperties);
            final CachedPropertyDescriptor[] writeProperties = targetProperties.writeProperties;
            for (int i = 0; i < writeProperties.length; ++i) {
                CachedPropertyDescriptor targetProperty = writeProperties[i];
                name = targetProperty.name;
                if (!ignoreLookup.contains(targetProperty.name)) {
                    CachedPropertyDescriptor sourceProperty = sourceProperties.readProperties.get(targetProperty.name);
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
            logger.error("exception copy property " + target.getClass().getName() + ":" + name);
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @param source
     * @param target
     * @param ignoredProperties
     * @throws IllegalStateException
     */
    public void deepCopyProperties(final Object source, final Object target, final String... ignoredProperties)
            throws IllegalStateException {
        Objects.requireNonNull(source, "Source must not be null!");
        Objects.requireNonNull(target, "Target must not be null!");
        Object name = null;
        try {
            final ClassProperties sourceProperties = getPropertyDescriptors(source.getClass());
            final ClassProperties targetProperties = getPropertyDescriptors(target.getClass());
            final Set<String> ignoreLookup = Sets.asSet(ignoredProperties);
            final CachedPropertyDescriptor[] writeProperties = targetProperties.writeProperties;
            for (int i = 0; i < writeProperties.length; ++i) {
                CachedPropertyDescriptor targetProperty = writeProperties[i];
                name = targetProperty.name;
                if (!ignoreLookup.contains(targetProperty.name)) {
                    CachedPropertyDescriptor sourceProperty = sourceProperties.readProperties.get(targetProperty.name);
                    if (sourceProperty != null) {
                        if (BeanInfo.INSTANCE.isSimpleProperty(sourceProperty.classType)) {
                            final Object value = sourceProperty.readMethod.invoke(source);
                            try {
                                targetProperty.writeMethod.invoke(target, value);
                            } catch (IllegalArgumentException ex) {
                                //ignore me
                            }
                        } else {
                            try {
                                final Object sourceObject = sourceProperty.readMethod.invoke(source);
                                final Object targetObject = targetProperty.classType.newInstance();
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
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            logger.error("exception copy property " + target.getClass().getName() + ":" + name);
            throw new IllegalStateException(ex);
        }
    }


    /**
     * CachedPropertyDescriptor
     */
    private final class CachedPropertyDescriptor {

        public final String name;
        public final Class<?> classType;
        public final Method readMethod;
        public final Method writeMethod;

        /**
         * @param propertyDescriptor
         */
        public CachedPropertyDescriptor(final PropertyDescriptor propertyDescriptor) {
            this.name = propertyDescriptor.getName();
            classType = propertyDescriptor.getReadMethod().getReturnType();
            this.readMethod = propertyDescriptor.getReadMethod();
            this.writeMethod = propertyDescriptor.getWriteMethod();
        }
    }

    /**
     * Contains the class properties.
     */
    private final class ClassProperties {

        public final Map<String, CachedPropertyDescriptor> readProperties;
        public final CachedPropertyDescriptor[] writeProperties;

        /**
         * @param classType
         */
        public ClassProperties(final Class<?> classType) {
            final Collection<PropertyDescriptor> allProperties = BeanInfo.INSTANCE.getProperties(classType);
            this.readProperties = new HashMap(allProperties.size(), 1.0F);
            final List<CachedPropertyDescriptor> writeList = new ArrayList(allProperties.size());
            allProperties.forEach(propertyDescriptor -> {
                BeanInfo.INSTANCE.ensurePublic(propertyDescriptor.getReadMethod());
                BeanInfo.INSTANCE.ensurePublic(propertyDescriptor.getWriteMethod());
                if (propertyDescriptor.getReadMethod() != null) {
                    this.readProperties
                            .put(propertyDescriptor.getName(), new CachedPropertyDescriptor(propertyDescriptor));
                }

                if (propertyDescriptor.getWriteMethod() != null) {
                    writeList.add(new CachedPropertyDescriptor(propertyDescriptor));
                }
            });

            this.writeProperties = writeList.toArray(new CachedPropertyDescriptor[writeList.size()]);
        }
    }
}
