package com.rslakra.core;

import com.rslakra.core.dto.StateDTO;
import com.rslakra.core.dto.UserDTO;
import com.rslakra.core.entity.MockEntity;
import com.rslakra.core.entity.State;
import com.rslakra.core.entity.User;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

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
    public void testIsEmpty() {
        Assert.assertTrue(BeanUtils.isEmpty(null));
        Assert.assertTrue(BeanUtils.isEmpty(""));
        Assert.assertFalse(BeanUtils.isEmpty(new Object()));
        Assert.assertFalse(BeanUtils.isEmpty(" "));
        Assert.assertFalse(BeanUtils.isEmpty(" rsl"));
    }

    @Test
    public void testIsNotEmpty() {
        Assert.assertFalse(BeanUtils.isNotEmpty(null));
        Assert.assertFalse(BeanUtils.isNotEmpty(""));
        Assert.assertTrue(BeanUtils.isNotEmpty(" "));
        Assert.assertTrue(BeanUtils.isNotEmpty(" rsl"));
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
    public void testIsArray() {
        Assert.assertFalse(BeanUtils.isArray(null));
        Assert.assertTrue(BeanUtils.isArray(new String[1]));
        Assert.assertTrue(BeanUtils.isArray(new Integer[]{1, 2}));
        Assert.assertTrue(BeanUtils.isArray(new Class[1]));
        Assert.assertTrue(BeanUtils.isArray(Class[].class));
        Assert.assertFalse(BeanUtils.isArray(String.class));
    }

    @Test
    public void testGetLength() {
        Assert.assertEquals(0, BeanUtils.getLength(null));
        Assert.assertEquals(1, BeanUtils.getLength(new String[1]));
        Assert.assertEquals(2, BeanUtils.getLength(new Integer[]{1, 2}));
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
        BeanUtils.INSTANCE.deepCopyProperties(userDTO, user);
        Assert.assertNotNull(user);
        LOGGER.debug("user:{}", user);

        UserDTO tempUser = new UserDTO();
        BeanUtils.INSTANCE.deepCopyProperties(user, tempUser);
        Assert.assertNotNull(user);
        LOGGER.debug("tempUser:{}", tempUser);
    }

}
