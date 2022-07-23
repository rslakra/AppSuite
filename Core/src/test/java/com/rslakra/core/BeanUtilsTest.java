package com.rslakra.core;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import com.rslakra.core.dto.StateDTO;
import com.rslakra.core.dto.UserDTO;
import com.rslakra.core.entity.MockEntity;
import com.rslakra.core.entity.State;
import com.rslakra.core.entity.User;
import com.rslakra.core.enums.Language;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Assert.assertTrue(BeanUtils.isNull(null));
        Assert.assertFalse(BeanUtils.isNull(new Object()));
    }


    @Test
    public void testIsNotNull() {
        Assert.assertFalse(BeanUtils.isNotNull(null));
        Assert.assertTrue(BeanUtils.isNotNull(new Object()));
    }


    @Test
    public void testHasBlanks() {
        Assert.assertFalse(BeanUtils.hasBlanks(null));
        Assert.assertFalse(BeanUtils.hasBlanks(""));
        Assert.assertTrue(BeanUtils.hasBlanks(" "));
        Assert.assertTrue(BeanUtils.hasBlanks(" rsl"));
    }

    @Test
    public void testIsBlank() {
        Assert.assertTrue(BeanUtils.isBlank(null));
        Assert.assertTrue(BeanUtils.isBlank(""));
        Assert.assertTrue(BeanUtils.isBlank(" "));
        Assert.assertFalse(BeanUtils.isBlank(" rsl"));
    }

    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(BeanUtils.isNotBlank(null));
        Assert.assertFalse(BeanUtils.isNotBlank(""));
        Assert.assertFalse(BeanUtils.isNotBlank(" "));
        Assert.assertTrue(BeanUtils.isNotBlank(" rsl"));
    }

    @Test
    public void testIsArray() {
        Assert.assertFalse(BeanUtils.isArray(null));
        Assert.assertTrue(BeanUtils.isArray(new String[1]));
        Assert.assertTrue(BeanUtils.isArray(new Integer[]{1, 2}));
        Assert.assertTrue(BeanUtils.isArray(new Class[1]));
        Assert.assertTrue(BeanUtils.isArray(Class[].class));
        Assert.assertFalse(BeanUtils.isArray(String.class));
    }

    @Test
    public void testIsTypeOf() {
        Assert.assertFalse(BeanUtils.isTypeOf(null, Object.class));
        Assert.assertFalse(BeanUtils.isTypeOf(null, Class.class));
        Assert.assertFalse(BeanUtils.isTypeOf(null, String.class));
        Assert.assertFalse(BeanUtils.isTypeOf(null, Integer.class));
        Assert.assertFalse(BeanUtils.isTypeOf(null, List.class));
        Assert.assertFalse(BeanUtils.isTypeOf(null, Map.class));

        Assert.assertTrue(BeanUtils.isTypeOf(new Object(), Object.class));
        Assert.assertTrue(BeanUtils.isTypeOf(new Object().getClass(), Class.class));
        Assert.assertTrue(BeanUtils.isTypeOf(new String(), String.class));
        Assert.assertTrue(BeanUtils.isTypeOf(Integer.valueOf("10"), Integer.class));
        Assert.assertTrue(BeanUtils.isTypeOf(new ArrayList<>(), List.class));
        Assert.assertTrue(BeanUtils.isTypeOf(new HashMap<>(), Map.class));
    }

    @Test
    public void testIsAssignableFrom() {
        Assert.assertFalse(BeanUtils.isAssignableFrom(null, Object.class));
        Assert.assertFalse(BeanUtils.isAssignableFrom(null, Class.class));
        Assert.assertFalse(BeanUtils.isAssignableFrom(null, String.class));
        Assert.assertFalse(BeanUtils.isAssignableFrom(null, Integer.class));
        Assert.assertFalse(BeanUtils.isAssignableFrom(null, List.class));
        Assert.assertFalse(BeanUtils.isAssignableFrom(null, Map.class));
        Assert.assertFalse(BeanUtils.isAssignableFrom(new Object(), Object.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(new Object().getClass(), Object.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(Object.class, Object.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(Class.class, Class.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(String.class, CharSequence.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(Integer.class, Number.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(ArrayList.class, List.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(Vector.class, List.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(AbstractList.class, List.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(HashMap.class, Map.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(ConcurrentHashMap.class, Map.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(Dictionary.class, Dictionary.class));
        Assert.assertTrue(BeanUtils.isAssignableFrom(Hashtable.class, Dictionary.class));
    }

    @Test
    public void testIsTypeOfMap() {
        Assert.assertFalse(BeanUtils.isTypeOfMap(null));
        Assert.assertFalse(BeanUtils.isTypeOfMap(Dictionary.class));
        Assert.assertTrue(BeanUtils.isTypeOfMap(AbstractMap.class));
        Assert.assertTrue(BeanUtils.isTypeOfMap(Map.class));
        Assert.assertFalse(BeanUtils.isTypeOfMap(new Object()));
        Assert.assertTrue(BeanUtils.isTypeOfMap(new HashMap<>()));
        Assert.assertTrue(BeanUtils.isTypeOfMap(new Hashtable<>()));
    }

    @Test
    public void testIsTypeOfList() {
        Assert.assertFalse(BeanUtils.isTypeOfList(null));
        Assert.assertTrue(BeanUtils.isTypeOfList(AbstractList.class));
        Assert.assertTrue(BeanUtils.isTypeOfList(List.class));
        Assert.assertFalse(BeanUtils.isTypeOfList(new Object()));
        Assert.assertTrue(BeanUtils.isTypeOfList(new ArrayList<>()));
        Assert.assertTrue(BeanUtils.isTypeOfList(new Vector<>()));
    }

    @Test
    public void testIsTypeOfSet() {
        Assert.assertFalse(BeanUtils.isTypeOfSet(null));
        Assert.assertTrue(BeanUtils.isTypeOfSet(AbstractSet.class));
        Assert.assertTrue(BeanUtils.isTypeOfSet(Set.class));
        Assert.assertFalse(BeanUtils.isTypeOfSet(new Object()));
        Assert.assertTrue(BeanUtils.isTypeOfSet(new HashSet<>()));
    }

    @Test
    public void testIsTypeOfCollection() {
        Assert.assertFalse(BeanUtils.isTypeOfCollection(null));
        Assert.assertFalse(BeanUtils.isTypeOfCollection(new Object()));

        // list
        Assert.assertTrue(BeanUtils.isTypeOfCollection(AbstractList.class));
        Assert.assertTrue(BeanUtils.isTypeOfCollection(List.class));
        Assert.assertTrue(BeanUtils.isTypeOfCollection(new ArrayList<>()));
        Assert.assertTrue(BeanUtils.isTypeOfCollection(new Vector<>()));
        // set
        Assert.assertTrue(BeanUtils.isTypeOfCollection(AbstractSet.class));
        Assert.assertTrue(BeanUtils.isTypeOfCollection(Set.class));
        Assert.assertTrue(BeanUtils.isTypeOfCollection(new HashSet<>()));
        //collection
        Assert.assertTrue(BeanUtils.isTypeOfCollection(AbstractCollection.class));
        Assert.assertTrue(BeanUtils.isTypeOfCollection(Collection.class));
    }

    @Test
    public void testIsTypeOfCharSequence() {
        Assert.assertFalse(BeanUtils.isTypeOfCharSequence(null));
        Assert.assertFalse(BeanUtils.isTypeOfCharSequence(new Object()));
        Assert.assertTrue(BeanUtils.isTypeOfCharSequence(""));
        Assert.assertTrue(BeanUtils.isTypeOfCharSequence(new String()));
        Assert.assertTrue(BeanUtils.isTypeOfCharSequence(String.class));
        Assert.assertTrue(BeanUtils.isTypeOfCharSequence(new StringBuilder()));
        Assert.assertTrue(BeanUtils.isTypeOfCharSequence(new StringBuffer()));
    }

    @Test
    public void testGetLength() {
        // Object
        Assert.assertEquals(0, BeanUtils.getLength(null));
        Assert.assertEquals(0, BeanUtils.getLength(new Object()));
        Assert.assertEquals(0, BeanUtils.getLength(new BeanUtilsTest()));

        // String
        Assert.assertEquals(0, BeanUtils.getLength(""));
        Assert.assertEquals(1, BeanUtils.getLength(" "));
        Assert.assertEquals(3, BeanUtils.getLength("rsl"));

        // Array
        Assert.assertEquals(0, BeanUtils.getLength(new Class[0]));
        Assert.assertEquals(0, BeanUtils.getLength(new Object[0]));
        Assert.assertEquals(0, BeanUtils.getLength(new String[0]));
        Assert.assertEquals(0, BeanUtils.getLength(new Map[0]));
        Assert.assertEquals(0, BeanUtils.getLength(new List[0]));
        Assert.assertEquals(0, BeanUtils.getLength(new Set[0]));
        Assert.assertEquals(0, BeanUtils.getLength(new Collection[0]));

        Assert.assertEquals(1, BeanUtils.getLength(new Class[1]));
        Assert.assertEquals(2, BeanUtils.getLength(new Object[2]));
        Assert.assertEquals(1, BeanUtils.getLength(new String[1]));
        Assert.assertEquals(2, BeanUtils.getLength(new String[]{"Rohtash", "Lakra"}));
        Assert.assertEquals(2, BeanUtils.getLength(new Integer[]{1, 2}));
        Assert.assertEquals(2, BeanUtils.getLength(Arrays.asList("Rohtash", "Singh")));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(BeanUtils.isEmpty(null));
        Assert.assertTrue(BeanUtils.isEmpty(""));
        Assert.assertFalse(BeanUtils.isEmpty(" "));
        Assert.assertFalse(BeanUtils.isEmpty(" rsl"));
        Assert.assertFalse(BeanUtils.isEmpty(new Object()));
        Assert.assertTrue(BeanUtils.isEmpty(new Class[0]));
        Assert.assertTrue(BeanUtils.isEmpty(new Object[0]));
        Assert.assertTrue(BeanUtils.isEmpty(new String[0]));
        Assert.assertTrue(BeanUtils.isEmpty(new Map[0]));
        Assert.assertTrue(BeanUtils.isEmpty(new List[0]));
        Assert.assertTrue(BeanUtils.isEmpty(new Set[0]));
    }

    @Test
    public void testIsNotEmpty() {
        Assert.assertFalse(BeanUtils.isNotEmpty(null));
        Assert.assertFalse(BeanUtils.isNotEmpty(""));
        Assert.assertTrue(BeanUtils.isNotEmpty(" "));
        Assert.assertTrue(BeanUtils.isNotEmpty(" rsl"));
        Assert.assertTrue(BeanUtils.isNotEmpty(new String("Lakra")));
        Assert.assertTrue(BeanUtils.isNotEmpty(new Object()));
        Assert.assertTrue(BeanUtils.isNotEmpty(new Object(), new String("RS")));
        Assert.assertTrue(BeanUtils.isNotEmpty(new Class[]{BeanUtils.class}));
        Assert.assertTrue(BeanUtils.isNotEmpty(new Class[1], new Class[1]));
        Assert.assertFalse(BeanUtils.isNotEmpty(new Object[1]));
        Assert.assertFalse(BeanUtils.isNotEmpty(new String[1]));
        Assert.assertFalse(BeanUtils.isNotEmpty(new Map[1]));
        Assert.assertFalse(BeanUtils.isNotEmpty(new HashMap<>()));
        Assert.assertFalse(BeanUtils.isNotEmpty(new Hashtable<>()));
        Assert.assertFalse(BeanUtils.isNotEmpty(new List[1]));
        Assert.assertFalse(BeanUtils.isNotEmpty(new Set[1]));
    }

    @Test
    public void testNotNull() {
        try {
            BeanUtils.notNull(null, "Object should not be null!");
            Assert.fail("GOTCHA!!! HOW DID YOU REACH HERE!");
        } catch (IllegalStateException ex) {
            Assert.assertTrue(true);
        }

        BeanUtils.notNull(new Object(), "Object should not be null!");
    }

    @Test
    public void testToTitleCase() {
        Assert.assertEquals(BeanUtils.EMPTY_STR, BeanUtils.toTitleCase(null));
        Assert.assertEquals("Rohtash", BeanUtils.toTitleCase("rohtash"));
        Assert.assertEquals("Rohtash Lakra", BeanUtils.toTitleCase("rohtash Lakra"));
    }

    @Test
    public void testPathSegments() {
        Assert.assertEquals(BeanUtils.EMPTY_STR, BeanUtils.pathSegments(BeanUtils.EMPTY_STR));
        Assert.assertEquals("rohtash", BeanUtils.pathSegments("rohtash"));
        Assert.assertEquals("rohtash/lakra", BeanUtils.pathSegments("rohtash", "lakra"));
    }

    @Test
    public void testNextUuid() {
        Assert.assertNotNull(BeanUtils.nextUuid());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(BeanUtils.equals(null, null));
        Object leftObject = new Object();
        Object rightObject = new Object();
        Object lastObject = rightObject;
        Assert.assertFalse(BeanUtils.equals(leftObject, null));
        Assert.assertFalse(BeanUtils.equals(null, rightObject));
        Assert.assertFalse(BeanUtils.equals(leftObject, rightObject));
        Assert.assertTrue(BeanUtils.equals(lastObject, rightObject));
        Assert.assertFalse(BeanUtils.equals(lastObject, leftObject));
        String leftString = new String();
        String rightString = new String();
        String lastString = rightString;
        Assert.assertFalse(BeanUtils.equals(leftString, null));
        Assert.assertFalse(BeanUtils.equals(null, rightString));
        Assert.assertTrue(BeanUtils.equals(leftString, rightString));
        Assert.assertTrue(BeanUtils.equals(lastString, rightString));

        Assert.assertTrue(BeanUtils.equals("", ""));
        Assert.assertTrue(BeanUtils.equals(Integer.valueOf(10), Integer.valueOf(10)));
        Assert.assertTrue(BeanUtils.equals("rohtash", String.valueOf("rohtash")));
    }

    @Test
    public void testNotEquals() {
        Assert.assertFalse(BeanUtils.notEquals(null, null));
        Object leftObject = new Object();
        Object rightObject = new Object();
        Object lastObject = rightObject;
        Assert.assertTrue(BeanUtils.notEquals(leftObject, null));
        Assert.assertTrue(BeanUtils.notEquals(null, rightObject));
        Assert.assertTrue(BeanUtils.notEquals(leftObject, rightObject));
        Assert.assertFalse(BeanUtils.notEquals(lastObject, rightObject));
        Assert.assertTrue(BeanUtils.notEquals(lastObject, leftObject));
        String leftString = new String();
        String rightString = new String();
        String lastString = rightString;
        Assert.assertTrue(BeanUtils.notEquals(leftString, null));
        Assert.assertTrue(BeanUtils.notEquals(null, rightString));
        Assert.assertFalse(BeanUtils.notEquals(leftString, rightString));
        Assert.assertFalse(BeanUtils.notEquals(lastString, rightString));

        Assert.assertFalse(BeanUtils.notEquals("", ""));
        Assert.assertFalse(BeanUtils.notEquals(Integer.valueOf(10), Integer.valueOf(10)));
        Assert.assertFalse(BeanUtils.notEquals("rohtash", String.valueOf("rohtash")));
    }

    @Test
    public void testToBytes() {
        assertNotNull(BeanUtils.toBytes("null"));
    }

    @Test
    public void testToClassPathString() {
        assertNotNull(BeanUtils.toClassPathString(BeanUtilsTest.class));
    }

    @Test
    public void testPartitionListBySize() {
        assertNotNull(BeanUtils.partitionListBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 3));
        assertTrue(BeanUtils.partitionListBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 0).isEmpty());
        assertEquals(3, BeanUtils.partitionListBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3).size());
        assertEquals(4, BeanUtils.partitionListBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3).size());
    }

    @Test
    public void testPartitionBySize() {
        assertNotNull(BeanUtils.partitionBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 3));
        assertTrue(BeanUtils.partitionBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 0).isEmpty());
        assertEquals(3, BeanUtils.partitionBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3).size());
        assertEquals(4, BeanUtils.partitionBySize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3).size());

        // null values
        List<Integer> values = null;
        int size = 4;
        List<List<Integer>> intPartitions = BeanUtils.partitionBySize(values, size);
        assertNotNull(intPartitions);
        assertEquals(intPartitions.size(), 0);

        values = Arrays.asList(1, 2, 3, 4);
        intPartitions = BeanUtils.partitionBySize(values, size);
        assertNotNull(intPartitions);
        assertEquals(intPartitions.size(), 1);

        values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        intPartitions = BeanUtils.partitionBySize(values, size);
        assertNotNull(intPartitions);
        assertEquals(intPartitions.size(), (values.size() / size) + 1);
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
        Assert.assertNotNull(mockEntity);
        Method[] methods = mockEntity.getClass().getMethods();
        Assert.assertNotNull(methods);
        Method methodGetName = mockEntity.getClass().getMethod("getUserName");
        Assert.assertTrue(BeanUtils.isGetter(methodGetName));
        Method methodIsActive = mockEntity.getClass().getMethod("isActive");
        Assert.assertTrue(BeanUtils.isGetter(methodIsActive));
    }

    @Test
    public void testIsSetter() throws NoSuchMethodException {
        MockEntity mockEntity = new MockEntity();
        Assert.assertNotNull(mockEntity);
        Method[] methods = mockEntity.getClass().getMethods();
        Assert.assertNotNull(methods);
        Method methodSetName = mockEntity.getClass().getMethod("setUserName", String.class);
        Assert.assertTrue(BeanUtils.isSetter(methodSetName));
        Method methodSetActive = mockEntity.getClass().getMethod("setActive", boolean.class);
        Assert.assertTrue(BeanUtils.isSetter(methodSetActive));
    }

    @Test
    public void testSeparateCamelCase() {
        String str = BeanUtils.separateCamelCase(null, ",");
        LOGGER.info("str: {}", str);
        Assert.assertNull(str);

        str = BeanUtils.separateCamelCase("FirstName", null);
        LOGGER.info("str: {}", str);
        Assert.assertNotNull(str);
        Assert.assertEquals("FirstName", str);

        str = BeanUtils.separateCamelCase("FirstName", "-");
        LOGGER.info("str: {}", str);
        Assert.assertNotNull(str);
        Assert.assertEquals("First-Name", str);
    }

    @Test
    public void testUpperCaseFirstLetter() {
        String str = BeanUtils.upperCaseFirstLetter(null);
        LOGGER.info("str: {}", str);
        Assert.assertNull(str);

        str = BeanUtils.upperCaseFirstLetter("First Name");
        LOGGER.info("str: {}", str);
        Assert.assertNotNull(str);
        Assert.assertEquals("First Name", str);

        str = BeanUtils.upperCaseFirstLetter("firstName");
        LOGGER.info("str: {}", str);
        Assert.assertNotNull(str);
        Assert.assertEquals("FirstName", str);

        str = BeanUtils.upperCaseFirstLetter("$firstName");
        LOGGER.info("str: {}", str);
        Assert.assertNotNull(str);
        Assert.assertEquals("$FirstName", str);
    }

    @Test
    public void testCopyProperties() {
        StateDTO stateDTO = StateDTO.ofDefault();
        State state = new State();
        LOGGER.debug("stateDTO:{}", stateDTO);
        BeanUtils.copyProperties(stateDTO, state);
        Assert.assertNotNull(state);
        LOGGER.debug("state:{}", state);

        StateDTO tempState = new StateDTO();
        BeanUtils.copyProperties(state, tempState);
        Assert.assertNotNull(tempState);
        LOGGER.debug("tempState:{}", tempState);
    }


    @Test
    public void testDeepCopyProperties() {
        UserDTO userDTO = UserDTO.ofDefault();
        User user = new User();
        LOGGER.debug("userDTO:{}", userDTO);
        BeanUtils.deepCopyProperties(userDTO, user);
        Assert.assertNotNull(user);
        LOGGER.debug("user:{}", user);

        UserDTO tempUser = new UserDTO();
        BeanUtils.deepCopyProperties(user, tempUser);
        Assert.assertNotNull(user);
        LOGGER.debug("tempUser:{}", tempUser);
    }

}
