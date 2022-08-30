package com.rslakra.core;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.rslakra.core.dto.StateDTO;
import com.rslakra.core.dto.UserDTO;
import com.rslakra.core.entity.MockEntity;
import com.rslakra.core.entity.State;
import com.rslakra.core.entity.User;
import com.rslakra.core.enums.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

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
        assertTrue(BeanUtils.isTypeOfCharSequence(String.class));
        assertTrue(BeanUtils.isTypeOfCharSequence(new StringBuilder()));
        assertTrue(BeanUtils.isTypeOfCharSequence(new StringBuffer()));
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
    public void testNotNull() {
        try {
            BeanUtils.nullCheck(null, "Object should not be null!");
            fail("GOTCHA!!! HOW DID YOU REACH HERE!");
        } catch (IllegalStateException ex) {
            assertTrue(true);
        }

        BeanUtils.nullCheck(new Object(), "Object should not be null!");
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
    @DataProvider
    public Iterator<Object[]> classPathWithClassNameData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{null, false, null});
        data.add(new Object[]{null, true, null});
        data.add(new Object[]{BeanUtilsTest.class, false, "com/rslakra/core"});
        data.add(new Object[]{BeanUtilsTest.class, true, "com/rslakra/core/BeanUtilsTest"});

        return data.iterator();
    }

    /**
     * @param classType
     * @param withClassName
     * @param expectedPath
     * @param <T>
     */
    @Test(dataProvider = "classPathWithClassNameData")
    public <T> void testGetClassPathWithClassName(Class<T> classType, boolean withClassName, String expectedPath) {
        String classPath = BeanUtils.getClassPath(classType, withClassName);
        if (BeanUtils.isNull(expectedPath)) {
            assertNull(classPath);
        } else {
            assertNotNull(classPath);
            assertTrue(classPath.endsWith(expectedPath));
        }
    }

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> classPathWithClassNameAndPathsData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{null, false, null, null});
        data.add(new Object[]{null, false, new String[]{}, null});
        data.add(new Object[]{null, false, new String[]{"data"}, null});

        data.add(new Object[]{null, true, null, null});
        data.add(new Object[]{null, true, new String[]{}, null});
        data.add(new Object[]{null, true, new String[]{"data"}, null});

        data.add(new Object[]{BeanUtilsTest.class, false, null, "com/rslakra/core"});
        data.add(new Object[]{BeanUtilsTest.class, false, new String[]{}, "com/rslakra/core"});
        data.add(new Object[]{BeanUtilsTest.class, false, new String[]{"data"}, "com/rslakra/core/data"});

        data.add(new Object[]{BeanUtilsTest.class, true, null, "com/rslakra/core/BeanUtilsTest"});
        data.add(new Object[]{BeanUtilsTest.class, true, new String[]{}, "com/rslakra/core/BeanUtilsTest"});
        data.add(new Object[]{BeanUtilsTest.class, true, new String[]{"data"}, "com/rslakra/core/BeanUtilsTest/data"});

        return data.iterator();
    }

    /**
     * @param classType
     * @param withClassName
     * @param pathComponents
     * @param expectedPath
     * @param <T>
     */
    @Test(dataProvider = "classPathWithClassNameAndPathsData")
    public <T> void testGetClassPathWithClassNameAndPaths(Class<T> classType, boolean withClassName,
                                                          String[] pathComponents, String expectedPath) {
        String classPath = BeanUtils.getClassPath(classType, withClassName, pathComponents);
        if (BeanUtils.isNull(expectedPath)) {
            assertNull(classPath);
        } else {
            assertNotNull(classPath);
            assertTrue(classPath.endsWith(expectedPath));
        }
    }

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> classPathWithPathComponentsData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{null, null, null});
        data.add(new Object[]{null, new String[]{}, null});
        data.add(new Object[]{null, new String[]{"data"}, null});

        data.add(new Object[]{BeanUtilsTest.class, null, "com/rslakra/core"});
        data.add(new Object[]{BeanUtilsTest.class, new String[]{}, "com/rslakra/core"});
        data.add(new Object[]{BeanUtilsTest.class, new String[]{"data"}, "com/rslakra/core/data"});
        data.add(new Object[]{BeanUtilsTest.class, new String[]{"first", "last"}, "com/rslakra/core/first/last"});

        return data.iterator();
    }

    /**
     * @param classType
     * @param pathComponents
     * @param expectedPath
     * @param <T>
     */
    @Test(dataProvider = "classPathWithPathComponentsData")
    public <T> void testGetClassPathWithPathComponents(Class<T> classType, String[] pathComponents,
                                                       String expectedPath) {
        String classPath = BeanUtils.getClassPath(classType, pathComponents);
        if (BeanUtils.isNull(expectedPath)) {
            assertNull(classPath);
        } else {
            assertNotNull(classPath);
            assertTrue(classPath.endsWith(expectedPath));
        }
    }

    @DataProvider
    public Iterator<Object[]> partitionListBySizeData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{null, 4, 0});
        data.add(new Object[]{Arrays.asList(), 4, 0});
        data.add(new Object[]{Arrays.asList(1, 2, 3), 3, 1});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 0});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 1, 9});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 2, 5});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 3, 3});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 4, 3});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 5, 2});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 6, 2});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 9, 1});
        data.add(new Object[]{Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 10, 1});

        return data.iterator();
    }

    @Test(dataProvider = "partitionListBySizeData")
    public void testPartitionListBySizeData(List<Integer> listIntegers, int partitionSize, int expectedPartitionSize) {
        // null values
        List<List<Integer>> partitionList = BeanUtils.partitionBySize(listIntegers, partitionSize);
        LOGGER.debug("partitionList size:{}, elements:{}", partitionList.size(), partitionList);
        assertNotNull(partitionList);
        assertEquals(partitionList.size(), expectedPartitionSize);
        if (expectedPartitionSize == 0) {
            assertTrue(partitionList.isEmpty());
        }
    }

    @DataProvider
    public Iterator<Object[]> partitionSetBySizeData() {
        List<Object[]> data = new ArrayList<>();
        //same key/value
        data.add(new Object[]{null, 4, 0});
        data.add(new Object[]{Sets.asSet(), 4, 0});
        data.add(new Object[]{Sets.asSet(1, 2, 3), 3, 1});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 0});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 1, 9});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 2, 5});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 3, 3});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 4, 3});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 5, 2});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 6, 2});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 9, 1});
        data.add(new Object[]{Sets.asSet(1, 2, 3, 4, 5, 6, 7, 8, 9), 10, 1});

        return data.iterator();
    }

    @Test(dataProvider = "partitionSetBySizeData")
    public void testPartitionSetBySize(Set<Integer> setInts, int partitionSize, int expectedPartitionSize) {
        // null values
        Set<Set<Integer>> partitionSet = BeanUtils.partitionBySize(setInts, partitionSize);
        LOGGER.debug("partitionSet size:{}, elements:{}", partitionSet.size(), partitionSet);
        assertNotNull(partitionSet);
        assertEquals(partitionSet.size(), expectedPartitionSize);
        if (expectedPartitionSize == 0) {
            assertTrue(partitionSet.isEmpty());
        }
    }

    @Test
    public void testFindEnumByClass() {
        assertNull(BeanUtils.findEnumByClass(Language.class, "hindi"));
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
        MockEntity mockEntity = new MockEntity();
        assertNotNull(mockEntity);
        Method[] methods = mockEntity.getClass().getMethods();
        assertNotNull(methods);
        Method methodGetName = mockEntity.getClass().getMethod("getUserName");
        assertTrue(BeanUtils.isGetter(methodGetName));
        Method methodIsActive = mockEntity.getClass().getMethod("isActive");
        assertTrue(BeanUtils.isGetter(methodIsActive));
    }

    @Test
    public void testIsSetter() throws NoSuchMethodException {
        MockEntity mockEntity = new MockEntity();
        assertNotNull(mockEntity);
        Method[] methods = mockEntity.getClass().getMethods();
        assertNotNull(methods);
        Method methodSetName = mockEntity.getClass().getMethod("setUserName", String.class);
        assertTrue(BeanUtils.isSetter(methodSetName));
        Method methodSetActive = mockEntity.getClass().getMethod("setActive", boolean.class);
        assertTrue(BeanUtils.isSetter(methodSetActive));
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

    @Test
    public void testCopyProperties() {
        StateDTO stateDTO = StateDTO.ofDefault();
        State state = new State();
        LOGGER.debug("stateDTO:{}", stateDTO);
        BeanUtils.copyProperties(stateDTO, state);
        assertNotNull(state);
        LOGGER.debug("state:{}", state);

        StateDTO tempState = new StateDTO();
        BeanUtils.copyProperties(state, tempState);
        assertNotNull(tempState);
        LOGGER.debug("tempState:{}", tempState);
    }


    @Test
    public void testDeepCopyProperties() {
        UserDTO userDTO = UserDTO.ofDefault();
        User user = new User();
        LOGGER.debug("userDTO:{}", userDTO);
        BeanUtils.deepCopyProperties(userDTO, user);
        assertNotNull(user);
        LOGGER.debug("user:{}", user);

        UserDTO tempUser = new UserDTO();
        BeanUtils.deepCopyProperties(user, tempUser);
        assertNotNull(user);
        LOGGER.debug("tempUser:{}", tempUser);
    }

}
