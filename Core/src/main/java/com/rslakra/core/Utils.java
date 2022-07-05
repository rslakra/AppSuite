package com.rslakra.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 10/2/19 10:15 AM
 */
public enum Utils {
    INSTANCE;

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    /**
     * @param objects
     * @return
     */
    public static int hashCode(final Object... objects) {
        return (Objects.hash(objects) & 0x7fffffff);
    }

    /**
     * @param charSequence
     */
    public byte[] toBytes(final CharSequence charSequence) {
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
     * @param klass
     * @return
     */
    public static String toClassPathString(final Class<?> klass) {
        assert klass != null;
        return klass.getPackage().getName().replace(".", "/");
    }

    /**
     * @param klass
     * @param pathString
     * @return
     */
    public static String toClassPathString(final Class<?> klass, final String pathString) {
        String classPath = Utils.toClassPathString(klass);
        if (BeanUtils.isNotEmpty(pathString)) {
            classPath += (pathString.startsWith(File.separator) ? "" : File.separator) + pathString;
        }

        return classPath;
    }

    /**
     * @param inputList
     * @param size
     * @param <T>
     * @return
     */
    public final <T> Collection<List<T>> partitionListBySize(final List<T> inputList, final int size) {
        if (inputList == null || inputList.isEmpty() || size <= 0) {
            return Collections.emptyList();
        } else if (inputList.size() <= size) {
            return Collections.singletonList(inputList);
        } else {
            final AtomicInteger counter = new AtomicInteger(0);
            return inputList.stream().collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size)).values();
        }
    }

//    /**
//     * @param inputSet
//     * @param size
//     * @param <T>
//     * @return
//     */
//    public final <T> Collection<Set<T>> partitionSetBySize(final Set<T> inputSet, final int size) {
//        if (inputSet == null || inputSet.isEmpty() || size <= 0) {
//            return Collections.emptyList();
//        } else if (inputSet.size() <= size) {
//            return Collections.singletonList(inputSet);
//        } else {
//            final AtomicInteger counter = new AtomicInteger(0);
//            return inputSet.stream()
//                .collect(Collectors.toSet()).values();
//        }
//    }


    /**
     * @param values
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> partitionBySize(final Collection<T> values, final int size) {
        if (BeanUtils.isEmpty(values) || size <= 0) {
            return Collections.emptyList();
        } else {
            final AtomicInteger counter = new AtomicInteger(0);
            return new ArrayList<>(
                values.stream().collect(Collectors.groupingBy(item -> counter.getAndIncrement() / size)).values());
//        } else {
//            final List<List<T>> partitionList = new ArrayList<>(values.size() / size + 1);
//            List<T> current = new LinkedList<>();
//            partitionList.add(current);
//
//            for (Iterator<T> itr = values.iterator(); itr.hasNext(); current.add(itr.next())) {
//                if (current.size() == size) {
//                    current = new LinkedList();
//                    partitionList.add(current);
//                }
//            }
//
//            return partitionList;
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
    public String readContents(final InputStream inputStream)
        throws IOException {
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
    public String readFile(final String fileName) {
        try {
            return readContents(getClass().getClassLoader().getResourceAsStream(fileName));
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
    public boolean isZero(final BigDecimal value) {
        return (value != null && value.signum() == 0);
    }

    /**
     * Returns true if the value is not null and greater than zero otherwise false.
     *
     * @param value
     * @return
     */
    public boolean isPositive(final BigDecimal value) {
        return (value != null && value.signum() == 1);
    }


    /**
     * Returns true if the value is not null and less than zero otherwise false.
     *
     * @param value
     * @return
     */
    public boolean isNegative(final BigDecimal value) {
        return (value != null && value.signum() == -1);
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
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> StringJoiner newStringJoiner(final Class<T> classType) {
        return new StringJoiner(", ", classType.getSimpleName() + "[", "]");
    }

}
