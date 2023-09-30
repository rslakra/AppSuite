package com.rslakra.appsuite.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rslakra.appsuite.core.entity.Color;
import com.rslakra.appsuite.core.entity.Instance;
import com.rslakra.appsuite.core.enums.EntityStatus;
import com.rslakra.appsuite.core.enums.RoleType;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author Rohtash Lakra
 * @created 5/27/20 2:40 PM
 */
public class BeanUtilsTest {

    // LOGGER
    private final Logger LOGGER = LoggerFactory.getLogger(BeanUtilsTest.class);

    @Test
    public void testIsNull() {
        assertTrue(BeanUtils.isNull(null));
        assertFalse(BeanUtils.isNull(new Object()));
    }


    @Test
    public void testIsNotNull() {
        assertFalse(BeanUtils.isNotNull(null));
        assertTrue(BeanUtils.isNotNull(new Object()));
    }


    @Test
    public void testHasBlanks() {
        assertFalse(BeanUtils.hasBlanks(null));
        assertFalse(BeanUtils.hasBlanks(""));
        assertTrue(BeanUtils.hasBlanks(" "));
        assertTrue(BeanUtils.hasBlanks(" rsl"));
    }

    @Test
    public void testIsBlank() {
        assertTrue(BeanUtils.isBlank(null));
        assertTrue(BeanUtils.isBlank(""));
        assertTrue(BeanUtils.isBlank(" "));
        assertFalse(BeanUtils.isBlank(" rsl"));
    }

    @Test
    public void testIsNotBlank() {
        assertFalse(BeanUtils.isNotBlank(null));
        assertFalse(BeanUtils.isNotBlank(""));
        assertFalse(BeanUtils.isNotBlank(" "));
        assertTrue(BeanUtils.isNotBlank(" rsl"));
    }

    @Test
    public void testIsArray() {
        assertFalse(BeanUtils.isArray(null));
        assertTrue(BeanUtils.isArray(new String[1]));
        assertTrue(BeanUtils.isArray(new Integer[]{1, 2}));
        assertTrue(BeanUtils.isArray(new Class[1]));
        assertTrue(BeanUtils.isArray(Class[].class));
        assertFalse(BeanUtils.isArray(String.class));
    }

    @Test
    public void testIsTypeOf() {
        assertFalse(BeanUtils.isTypeOf(null, Object.class));
        assertFalse(BeanUtils.isTypeOf(null, Class.class));
        assertFalse(BeanUtils.isTypeOf(null, String.class));
        assertFalse(BeanUtils.isTypeOf(null, Integer.class));
        assertFalse(BeanUtils.isTypeOf(null, List.class));
        assertFalse(BeanUtils.isTypeOf(null, Map.class));

        assertTrue(BeanUtils.isTypeOf(new Object(), Object.class));
        assertTrue(BeanUtils.isTypeOf(new Object().getClass(), Class.class));
        assertTrue(BeanUtils.isTypeOf(new String(), String.class));
        assertTrue(BeanUtils.isTypeOf(Integer.valueOf("10"), Integer.class));
        assertTrue(BeanUtils.isTypeOf(new ArrayList<>(), List.class));
        assertTrue(BeanUtils.isTypeOf(new HashMap<>(), Map.class));
    }

    @Test
    public void testIsAssignableFrom() {
        assertFalse(BeanUtils.isAssignableFrom(null, Object.class));
        assertFalse(BeanUtils.isAssignableFrom(null, Class.class));
        assertFalse(BeanUtils.isAssignableFrom(null, String.class));
        assertFalse(BeanUtils.isAssignableFrom(null, Integer.class));
        assertFalse(BeanUtils.isAssignableFrom(null, List.class));
        assertFalse(BeanUtils.isAssignableFrom(null, Map.class));
        assertFalse(BeanUtils.isAssignableFrom(new Object(), Object.class));
        assertTrue(BeanUtils.isAssignableFrom(new Object().getClass(), Object.class));
        assertTrue(BeanUtils.isAssignableFrom(Object.class, Object.class));
        assertTrue(BeanUtils.isAssignableFrom(Class.class, Class.class));
        assertTrue(BeanUtils.isAssignableFrom(String.class, CharSequence.class));
        assertTrue(BeanUtils.isAssignableFrom(Integer.class, Number.class));
        assertTrue(BeanUtils.isAssignableFrom(ArrayList.class, List.class));
        assertTrue(BeanUtils.isAssignableFrom(Vector.class, List.class));
        assertTrue(BeanUtils.isAssignableFrom(AbstractList.class, List.class));
        assertTrue(BeanUtils.isAssignableFrom(HashMap.class, Map.class));
        assertTrue(BeanUtils.isAssignableFrom(ConcurrentHashMap.class, Map.class));
        assertTrue(BeanUtils.isAssignableFrom(Dictionary.class, Dictionary.class));
        assertTrue(BeanUtils.isAssignableFrom(Hashtable.class, Dictionary.class));
    }

    @Test
    public void testIsTypeOfMap() {
        assertFalse(BeanUtils.isTypeOfMap(null));
        assertFalse(BeanUtils.isTypeOfMap(Dictionary.class));
        assertTrue(BeanUtils.isTypeOfMap(AbstractMap.class));
        assertTrue(BeanUtils.isTypeOfMap(Map.class));
        assertFalse(BeanUtils.isTypeOfMap(new Object()));
        assertTrue(BeanUtils.isTypeOfMap(new HashMap<>()));
        assertTrue(BeanUtils.isTypeOfMap(new Hashtable<>()));
    }

    @Test
    public void testIsTypeOfList() {
        assertFalse(BeanUtils.isTypeOfList(null));
        assertTrue(BeanUtils.isTypeOfList(AbstractList.class));
        assertTrue(BeanUtils.isTypeOfList(List.class));
        assertFalse(BeanUtils.isTypeOfList(new Object()));
        assertTrue(BeanUtils.isTypeOfList(new ArrayList<>()));
        assertTrue(BeanUtils.isTypeOfList(new Vector<>()));
    }

    @Test
    public void testIsTypeOfSet() {
        assertFalse(BeanUtils.isTypeOfSet(null));
        assertTrue(BeanUtils.isTypeOfSet(AbstractSet.class));
        assertTrue(BeanUtils.isTypeOfSet(Set.class));
        assertFalse(BeanUtils.isTypeOfSet(new Object()));
        assertTrue(BeanUtils.isTypeOfSet(new HashSet<>()));
    }

    @Test
    public void testIsTypeOfCollection() {
        assertFalse(BeanUtils.isTypeOfCollection(null));
        assertFalse(BeanUtils.isTypeOfCollection(new Object()));

        // list
        assertTrue(BeanUtils.isTypeOfCollection(AbstractList.class));
        assertTrue(BeanUtils.isTypeOfCollection(List.class));
        assertTrue(BeanUtils.isTypeOfCollection(new ArrayList<>()));
        assertTrue(BeanUtils.isTypeOfCollection(new Vector<>()));
        // set
        assertTrue(BeanUtils.isTypeOfCollection(AbstractSet.class));
        assertTrue(BeanUtils.isTypeOfCollection(Set.class));
        assertTrue(BeanUtils.isTypeOfCollection(new HashSet<>()));
        //collection
        assertTrue(BeanUtils.isTypeOfCollection(AbstractCollection.class));
        assertTrue(BeanUtils.isTypeOfCollection(Collection.class));
    }

    @Test
    public void testIsTypeOfCharSequence() {
        assertFalse(BeanUtils.isTypeOfCharSequence(null));
        assertFalse(BeanUtils.isTypeOfCharSequence(new Object()));
        assertTrue(BeanUtils.isTypeOfCharSequence(""));
        assertTrue(BeanUtils.isTypeOfCharSequence(new String()));
        assertTrue(BeanUtils.isTypeOfCharSequence(CharSequence.class));
        assertTrue(BeanUtils.isTypeOfCharSequence(String.class));
        assertTrue(BeanUtils.isTypeOfCharSequence(new StringBuilder()));
        assertTrue(BeanUtils.isTypeOfCharSequence(new StringBuffer()));
    }

    @Test
    public void testIsTypeOfString() {
        assertFalse(BeanUtils.isTypeOfString(null));
        assertFalse(BeanUtils.isTypeOfString(new Object()));
        assertTrue(BeanUtils.isTypeOfString(""));
        assertTrue(BeanUtils.isTypeOfString(new String()));
        assertTrue(BeanUtils.isTypeOfString(String.class));
        assertFalse(BeanUtils.isTypeOfString(new StringBuilder()));
        assertFalse(BeanUtils.isTypeOfString(new StringBuffer()));
    }

    @Test
    public void testGetLength() {
        // Object
        assertEquals(0, BeanUtils.getLength(null));
        assertEquals(0, BeanUtils.getLength(new Object()));
        assertEquals(0, BeanUtils.getLength(new BeanUtilsTest()));

        // String
        assertEquals(0, BeanUtils.getLength(""));
        assertEquals(1, BeanUtils.getLength(" "));
        assertEquals(3, BeanUtils.getLength("rsl"));

        // Array
        assertEquals(0, BeanUtils.getLength(new Class[0]));
        assertEquals(0, BeanUtils.getLength(new Object[0]));
        assertEquals(0, BeanUtils.getLength(new String[0]));
        assertEquals(0, BeanUtils.getLength(new Map[0]));
        assertEquals(0, BeanUtils.getLength(new List[0]));
        assertEquals(0, BeanUtils.getLength(new Set[0]));
        assertEquals(0, BeanUtils.getLength(new Collection[0]));

        assertEquals(1, BeanUtils.getLength(new Class[1]));
        assertEquals(2, BeanUtils.getLength(new Object[2]));
        assertEquals(1, BeanUtils.getLength(new String[1]));
        assertEquals(2, BeanUtils.getLength(new String[]{"Rohtash", "Lakra"}));
        assertEquals(2, BeanUtils.getLength(new Integer[]{1, 2}));
        assertEquals(2, BeanUtils.getLength(Arrays.asList("Rohtash", "Singh")));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(BeanUtils.isEmpty(null));
        assertTrue(BeanUtils.isEmpty(""));
        assertFalse(BeanUtils.isEmpty(" "));
        assertFalse(BeanUtils.isEmpty(" rsl"));
        assertFalse(BeanUtils.isEmpty(new Object()));
        assertTrue(BeanUtils.isEmpty(new Class[0]));
        assertTrue(BeanUtils.isEmpty(new Object[0]));
        assertTrue(BeanUtils.isEmpty(new String[0]));
        assertTrue(BeanUtils.isEmpty(new Map[0]));
        assertTrue(BeanUtils.isEmpty(new List[0]));
        assertTrue(BeanUtils.isEmpty(new Set[0]));
    }

    @Test
    public void testIsNotEmpty() {
        assertFalse(BeanUtils.isNotEmpty(null));
        assertFalse(BeanUtils.isNotEmpty(""));
        assertTrue(BeanUtils.isNotEmpty(" "));
        assertTrue(BeanUtils.isNotEmpty(" rsl"));
        assertTrue(BeanUtils.isNotEmpty(new String("Lakra")));
        assertTrue(BeanUtils.isNotEmpty(new Object()));
        assertTrue(BeanUtils.isNotEmpty(new Object(), new String("RS")));
        assertTrue(BeanUtils.isNotEmpty(new Class[]{BeanUtils.class}));
        assertTrue(BeanUtils.isNotEmpty(new Class[1], new Class[1]));
        assertFalse(BeanUtils.isNotEmpty(new Object[1]));
        assertFalse(BeanUtils.isNotEmpty(new String[1]));
        assertFalse(BeanUtils.isNotEmpty(new Map[1]));
        assertFalse(BeanUtils.isNotEmpty(new HashMap<>()));
        assertFalse(BeanUtils.isNotEmpty(new Hashtable<>()));
        assertFalse(BeanUtils.isNotEmpty(new List[1]));
        assertFalse(BeanUtils.isNotEmpty(new Set[1]));
    }

    @Test
    public void testAssertNonNull() {
        try {
            BeanUtils.assertNonNull(null, "Object should not be null!");
            assertTrue(false);
        } catch (NullPointerException ex) {
            assertTrue(true);
        }

        BeanUtils.assertNonNull(new Object());
        assertTrue(true);
    }

    @Test
    public void testToTitleCase() {
        assertEquals(BeanUtils.EMPTY_STR, BeanUtils.toTitleCase(null));
        assertEquals("Rohtash", BeanUtils.toTitleCase("rohtash"));
        assertEquals("Rohtash Lakra", BeanUtils.toTitleCase("rohtash Lakra"));
    }

    @Test
    public void testPathSegments() {
        assertEquals(BeanUtils.EMPTY_STR, BeanUtils.pathSegments(BeanUtils.EMPTY_STR));
        assertEquals("rohtash", BeanUtils.pathSegments("rohtash"));
        assertEquals("rohtash/lakra", BeanUtils.pathSegments("rohtash", "lakra"));
    }

    @Test
    public void testNextUuid() {
        String nextUuid = BeanUtils.nextUuid();
        LOGGER.debug("nextUuid:{}", nextUuid);
        assertNotNull(nextUuid);
    }

    @Test
    public void testEquals() {
        assertTrue(BeanUtils.equals(null, null));
        Object leftObject = new Object();
        Object rightObject = new Object();
        Object lastObject = rightObject;
        assertFalse(BeanUtils.equals(leftObject, null));
        assertFalse(BeanUtils.equals(null, rightObject));
        assertFalse(BeanUtils.equals(leftObject, rightObject));
        assertTrue(BeanUtils.equals(lastObject, rightObject));
        assertFalse(BeanUtils.equals(lastObject, leftObject));
        String leftString = new String();
        String rightString = new String();
        String lastString = rightString;
        assertFalse(BeanUtils.equals(leftString, null));
        assertFalse(BeanUtils.equals(null, rightString));
        assertTrue(BeanUtils.equals(leftString, rightString));
        assertTrue(BeanUtils.equals(lastString, rightString));

        assertTrue(BeanUtils.equals("", ""));
        assertTrue(BeanUtils.equals(Integer.valueOf(10), Integer.valueOf(10)));
        assertTrue(BeanUtils.equals("rohtash", String.valueOf("rohtash")));
    }

    @Test
    public void testNotEquals() {
        assertFalse(BeanUtils.notEquals(null, null));
        Object leftObject = new Object();
        Object rightObject = new Object();
        Object lastObject = rightObject;
        assertTrue(BeanUtils.notEquals(leftObject, null));
        assertTrue(BeanUtils.notEquals(null, rightObject));
        assertTrue(BeanUtils.notEquals(leftObject, rightObject));
        assertFalse(BeanUtils.notEquals(lastObject, rightObject));
        assertTrue(BeanUtils.notEquals(lastObject, leftObject));
        String leftString = new String();
        String rightString = new String();
        String lastString = rightString;
        assertTrue(BeanUtils.notEquals(leftString, null));
        assertTrue(BeanUtils.notEquals(null, rightString));
        assertFalse(BeanUtils.notEquals(leftString, rightString));
        assertFalse(BeanUtils.notEquals(lastString, rightString));

        assertFalse(BeanUtils.notEquals("", ""));
        assertFalse(BeanUtils.notEquals(Integer.valueOf(10), Integer.valueOf(10)));
        assertFalse(BeanUtils.notEquals("rohtash", String.valueOf("rohtash")));
    }

    @Test
    public void testToBytes() {
        assertNotNull(BeanUtils.toBytes("null"));
    }

    /**
     * @return
     */
    private static Stream<Arguments> classPathWithClassNameData() {
        return Stream.of(
            Arguments.of(null, false, null),
            Arguments.of(null, true, null),
            Arguments.of(BeanUtilsTest.class, false, BeanUtils.getClassPath(BeanUtilsTest.class)),
            Arguments.of(BeanUtilsTest.class, true, BeanUtils.getClassPath(BeanUtilsTest.class))
        );
    }

    /**
     * @param classType
     * @param withClassName
     * @param expectedPath
     * @param <T>
     */
//    @ParameterizedTest
//    @MethodSource("classPathWithClassNameData")
//    @Test
//    public <T> void testGetClassPathWithClassName(Class<T> classType, boolean withClassName, String expectedPath) {
//        String classPath = BeanUtils.getClassPath(classType, withClassName);
//        if (BeanUtils.isNull(expectedPath)) {
//            assertNull(classPath);
//        } else {
//            assertNotNull(classPath);
//            assertTrue(classPath.endsWith(expectedPath));
//        }
//    }

    /**
     * @return
     */
    private static Stream<Arguments> classPathWithClassNameAndPathsData() {
        return Stream.of(
            Arguments.of(null, false, null, null),
            Arguments.of(null, false, new String[]{}, null),
            Arguments.of(null, false, new String[]{"data"}, null),

            Arguments.of(null, true, null, null),
            Arguments.of(null, true, new String[]{}, null),
            Arguments.of(null, true, new String[]{"data"}, null),

            Arguments.of(BeanUtilsTest.class, false, null, BeanUtils.getClassPath(BeanUtilsTest.class)),
            Arguments.of(BeanUtilsTest.class, false, new String[]{}, BeanUtils.getClassPath(BeanUtilsTest.class)),
            Arguments.of(BeanUtilsTest.class, false, new String[]{"data"}, BeanUtils.getClassPath(BeanUtilsTest.class)),

            Arguments.of(BeanUtilsTest.class, true, null, BeanUtils.getClassPath(BeanUtilsTest.class)),
            Arguments.of(BeanUtilsTest.class, true, new String[]{}, BeanUtils.getClassPath(BeanUtilsTest.class)),
            Arguments.of(BeanUtilsTest.class, true, new String[]{"data"}, BeanUtils.getClassPath(BeanUtilsTest.class))
        );
    }

    /**
     * @param classType
     * @param withClassName
     * @param pathComponents
     * @param expectedPath
     * @param <T>
     */
//    @ParameterizedTest
//    @MethodSource("classPathWithClassNameAndPathsData")
//    public <T> void testGetClassPathWithClassNameAndPaths(Class<T> classType, boolean withClassName,
//                                                          String[] pathComponents, String expectedPath) {
//        String classPath = BeanUtils.getClassPath(classType, withClassName, pathComponents);
//        if (BeanUtils.isNull(expectedPath)) {
//            assertNull(classPath);
//        } else {
//            assertNotNull(classPath);
//            assertTrue(classPath.endsWith(expectedPath));
//        }
//    }

    /**
     * @return
     */
    private static Stream<Arguments> classPathWithPathComponentsData() {
        return Stream.of(
            Arguments.of(null, (String[]) null, null),
            Arguments.of(null, new String[]{}, null),
            Arguments.of(null, new String[]{"data"}, null),

            Arguments.of(BeanUtilsTest.class, null, BeanUtils.getClassPath(BeanUtilsTest.class)),
            Arguments.of(BeanUtilsTest.class, new String[]{}, BeanUtils.getClassPath(BeanUtilsTest.class)),
            Arguments.of(BeanUtilsTest.class, new String[]{"data"},
                         BeanUtils.getClassPath(BeanUtilsTest.class) + "/data"),
            Arguments.of(BeanUtilsTest.class, new String[]{"first", "last"},
                         BeanUtils.getClassPath(BeanUtilsTest.class) + "/first/last")
        );
    }

    /**
     * @param classType
     * @param pathComponents
     * @param expectedPath
     * @param <T>
     */
//    @ParameterizedTest
//    @MethodSource("classPathWithPathComponentsData")
////    @Test
//    public <T> void testGetClassPathWithPathComponents(Class<T> classType, String[] pathComponents,
//                                                       String expectedPath) {
//        String classPath = BeanUtils.getClassPath(classType, pathComponents);
//        if (BeanUtils.isNull(expectedPath)) {
//            assertNull(classPath);
//        } else {
//            assertNotNull(classPath);
//            assertTrue(classPath.endsWith(expectedPath));
//        }
//    }
    private static Stream<Arguments> partitionListBySizeData() {
        return Stream.of(
            Arguments.of(null, 4, 0),
            Arguments.of(Arrays.asList(), 4, 0),
            Arguments.of(Arrays.asList(1, 2, 3), 3, 1),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 0),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 1, 9),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 2, 5),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 3, 3),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 4, 3),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 5, 2),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 6, 2),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 9, 1),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 10, 1)
        );
    }


    @ParameterizedTest
//    @MethodSource("partitionListBySizeData")
////    @Test
//    public void testPartitionListBySizeData(List<Integer> listIntegers, int partitionSize, int expectedPartitionSize) {
//        // null values
//        List<List<Integer>> partitionList = BeanUtils.partitionBySize(listIntegers, partitionSize);
//        LOGGER.debug("partitionList size:{}, elements:{}", partitionList.size(), partitionList);
//        assertNotNull(partitionList);
//        assertEquals(partitionList.size(), expectedPartitionSize);
//        if (expectedPartitionSize == 0) {
//            assertTrue(partitionList.isEmpty());
//        }
//    }

    private static Stream<Arguments> partitionSetBySizeData() {
        return Stream.of(
            Arguments.of(null, 4, 0),
            Arguments.of(Sets.asSet(), 4, 0),
            Arguments.of(Sets.asSet(1, 2, 3), 3, 1),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 0),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 1, 9),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 2, 5),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 3, 3),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 4, 3),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 5, 2),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 6, 2),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 9, 1),
            Arguments.of(Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 10, 1)
        );
    }

//    @ParameterizedTest
//    @MethodSource("partitionSetBySizeData")
////    @Test
//    public void testPartitionSetBySize(Set<Integer> setInts, int partitionSize, int expectedPartitionSize) {
//        // null values
//        Set<Set<Integer>> partitionSet = BeanUtils.partitionBySize(setInts, partitionSize);
//        LOGGER.debug("partitionSet size:{}, elements:{}", partitionSet.size(), partitionSet);
//        assertNotNull(partitionSet);
//        assertEquals(partitionSet.size(), expectedPartitionSize);
//        if (expectedPartitionSize == 0) {
//            assertTrue(partitionSet.isEmpty());
//        }
//    }

    @Test
    public void testFindEnumByClass() {
        assertNotNull(BeanUtils.findEnumByClass(EntityStatus.class, "active"));
        assertNotNull(BeanUtils.findEnumByClass(EntityStatus.class, "inactive"));
        assertNotNull(BeanUtils.findEnumByClass(EntityStatus.class, "deleted"));
        assertNull(BeanUtils.findEnumByClass(EntityStatus.class, "fake"));
        assertNotNull(BeanUtils.findEnumByClass(RoleType.class, "admin"));
        assertNotNull(BeanUtils.findEnumByClass(RoleType.class, "user"));
    }

    @Test
    public void testIsZero() {
        assertEquals(true, BeanUtils.isZero(BigDecimal.ZERO));
        assertEquals(false, BeanUtils.isZero(BigDecimal.TEN));
        assertEquals(false, BeanUtils.isZero(BigDecimal.TEN.negate()));
    }


    @Test
    public void testIsPositive() {
        assertEquals(true, BeanUtils.isPositive(BigDecimal.TEN));
        assertEquals(false, BeanUtils.isPositive(BigDecimal.TEN.negate()));
        assertEquals(false, BeanUtils.isPositive(BigDecimal.ZERO));
    }

    @Test
    public void testIsGetter() throws NoSuchMethodException {
        BeanUtils beanUtils = BeanUtils.INSTANCE;
        assertNotNull(beanUtils);
        Method[] methods = beanUtils.getClass().getMethods();
        assertNotNull(methods);
//        Method methodGetter = beanUtils.getClass().getMethod("isClassPropertyDescriptor");
//        assertNotNull(methodGetter);
//        assertTrue(BeanUtils.isGetter(methodGetter));
//        assertEquals("isClassPropertyDescriptor", methodGetter.getName());
//        Method methodIsActive = beanUtils.getClass().getMethod("isActive");
//        assertTrue(BeanUtils.isGetter(methodIsActive));
    }

    @Test
    public void testIsSetter() throws NoSuchMethodException {
        BeanUtils beanUtils = BeanUtils.INSTANCE;
        assertNotNull(beanUtils);
        Method[] methods = beanUtils.getClass().getMethods();
        assertNotNull(methods);
        Method
            methodSetter =
            beanUtils.getClass().getMethod("setBeanProperty", PropertyDescriptor.class, Object.class, Object.class);
//        assertNotNull(methodSetter);
//        assertTrue(BeanUtils.isSetter(methodSetter));
//        assertEquals("setBeanProperty", methodSetter.getName());
//        Method methodSetActive = beanUtils.getClass().getMethod("setActive", boolean.class);
//        assertTrue(BeanUtils.isSetter(methodSetActive));
    }

    /**
     * @param expected
     * @param target
     */
    private void assertInstance(Instance expected, Instance target) {
        LOGGER.debug("+assertInstance({}, {})", expected, target);
        assertNotNull(expected);
        assertNotNull(target);
        assertEquals(expected.getFamily(), target.getFamily());
        assertEquals(expected.getGeneration(), target.getGeneration());
        assertEquals(expected.getProperties(), target.getProperties());
        assertEquals(expected.getSize(), target.getSize());
        LOGGER.debug("-assertInstance()");
    }

    /**
     * Copy Properties
     */
    @Test
    public void testCopyProperties() {
        Instance sourceInstance = new Instance();
        sourceInstance.setFamily("iPhone");
        sourceInstance.setGeneration("4th");
        sourceInstance.setProperties("name");
        sourceInstance.setSize("10");
        LOGGER.debug("sourceInstance: {}", sourceInstance);

        Instance targetInstance = new Instance();
        BeanUtils.copyProperties(sourceInstance, targetInstance);
        LOGGER.debug("targetInstance: {}", targetInstance);
        assertNotNull(targetInstance);
        assertEquals(sourceInstance.getFamily(), targetInstance.getFamily());
        assertEquals(sourceInstance.getGeneration(), targetInstance.getGeneration());
        assertEquals(sourceInstance.getProperties(), targetInstance.getProperties());
        assertEquals(sourceInstance.getSize(), targetInstance.getSize());
    }

    @Data
    private class TestBean {

        private String name;
        private Instance instance;
        private List<Color> colors;
    }


    /**
     * Copy Properties
     */
    @Test
    public void testDeepCopyProperties() {
        Instance instance = new Instance();
        instance.setFamily("iPhone");
        instance.setGeneration("4th");
        instance.setProperties("name");
        instance.setSize("10");
        LOGGER.debug("instance: {}", instance);

        TestBean sourceInstance = new TestBean();
        sourceInstance.setName("sourceInstance");
        sourceInstance.setInstance(instance);
        Color redColor = new Color(1601L, "Red");
        Color whiteColor = new Color(1602L, "White");
        sourceInstance.setColors(Arrays.asList(redColor, whiteColor));
        LOGGER.debug("sourceInstance: {}", sourceInstance);

        TestBean targetInstance = new TestBean();
        BeanUtils.deepCopyProperties(sourceInstance, targetInstance);
        LOGGER.debug("targetInstance: {}", targetInstance);
        assertNotNull(targetInstance);
        assertEquals(sourceInstance.getName(), targetInstance.getName());
        assertInstance(targetInstance.getInstance(), sourceInstance.getInstance());
        assertNotNull(targetInstance.getColors());
        assertEquals(targetInstance.getColors().size(), targetInstance.getColors().size());
    }

    @Test
    public void testSeparateCamelCase() {
        String str = BeanUtils.separateCamelCase(null, ",");
        LOGGER.info("str: {}", str);
        assertNull(str);

        str = BeanUtils.separateCamelCase("FirstName", null);
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("FirstName", str);

        str = BeanUtils.separateCamelCase("FirstName", "-");
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("First-Name", str);
    }

    @Test
    public void testToSentenceCase() {
        String str = BeanUtils.toSentenceCase(null);
        LOGGER.info("str: {}", str);
        assertNull(str);

        str = BeanUtils.toSentenceCase("First Name");
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("First Name", str);

        str = BeanUtils.toSentenceCase("firstName");
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("FirstName", str);

        str = BeanUtils.toSentenceCase("$firstName");
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("$FirstName", str);

        str = BeanUtils.toSentenceCase("16 $ firstName");
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("16 $ FirstName", str);
    }

    @Test
    public void testToStringWithThrowable() {
        String str = BeanUtils.toString(null);
        LOGGER.info("str: {}", str);
        assertNotNull(str);

        str = BeanUtils.toString(new NullPointerException());
        LOGGER.info("str: {}", str);
        assertNotNull(str);

        str = BeanUtils.toString(new RuntimeException("RuntimeException"));
        LOGGER.info("str: {}", str);
        assertNotNull(str);

        str = BeanUtils.toString(new NumberFormatException("NumberFormatException"));
        LOGGER.info("str: {}", str);
        assertNotNull(str);
    }

    @Test
    public void testToString() {
        Object object = null;
        String str = BeanUtils.toString(object);
        LOGGER.info("str: {}", str);
        assertNotNull(str);

        object = new RuntimeException();
        str = BeanUtils.toString(object);
        LOGGER.info("str: {}", str);
        assertNotNull(str);

        object = "Lakra";
        str = BeanUtils.toString(object);
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("Lakra", str);

        object = new BigDecimal("16.01");
        str = BeanUtils.toString(object);
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("16.01", str);

        object = Double.valueOf("16.01");
        str = BeanUtils.toString(object);
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("16.01", str);

        object = Arrays.asList("Rohtash", "Lakra");
        str = BeanUtils.toString(object);
        LOGGER.info("str: {}", str);
        assertNotNull(str);
        assertEquals("[Rohtash, Lakra]", str);
    }

//    @Test
//    public void testCopyProperties() {
//        Payload payload = Payload.builder()
//            .of("firstName", "Rohtash")
//            .of("lastName", "Lakra");
//        LOGGER.debug("payload:{}", payload);
//        Payload tempPayload = Payload.builder();
//        BeanUtils.copyProperties(payload, tempPayload);
//        LOGGER.debug("tempPayload:{}", tempPayload);
//        assertNotNull(tempPayload);
//
//        Payload copyPayload = Payload.builder();
//        BeanUtils.copyProperties(tempPayload, copyPayload);
//        LOGGER.debug("copyPayload:{}", copyPayload);
//        assertNotNull(copyPayload);
//        assertEquals(payload, copyPayload);
//    }
//
//
//    @Test
//    public void testDeepCopyProperties() {
//        Payload phones = Payload.builder()
//            .of("home", "4085108759")
//            .of("mobile", "4085108759")
//            .of("office", "4085108759");
//
//        Payload payload = Payload.builder()
//            .of("firstName", "Rohtash")
//            .of("lastName", "Lakra")
//            .of("phones", phones);
//
//        LOGGER.debug("payload:{}", payload);
//        Payload tempPayload = Payload.builder();
//        BeanUtils.deepCopyProperties(payload, tempPayload);
//        LOGGER.debug("tempPayload:{}", tempPayload);
//        assertNotNull(tempPayload);
//
//        Payload copyPayload = Payload.builder();
//        BeanUtils.deepCopyProperties(tempPayload, copyPayload);
//        LOGGER.debug("copyPayload:{}", copyPayload);
//        assertNotNull(copyPayload);
//        assertEquals(payload, copyPayload);
//    }


    @Test
    public void testAddDashes() {
        String value = "Rohtash Lakra";
        String result = BeanUtils.addDashes(value);
        LOGGER.debug("value: {}, result: {}", value, result);
        assertNotNull(result);
        assertEquals(value.toLowerCase(), result);
    }
}
