package com.rslakra.core.enums;

import com.rslakra.core.dto.StateDTO;
import com.rslakra.core.dto.UserDTO;
import com.rslakra.core.entity.State;
import com.rslakra.core.entity.User;
import com.rslakra.core.utils.BeanUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

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
    public void testIsNullOrEmpty() {
        Assert.assertTrue(BeanUtils.isNullOrEmpty(null));
        Assert.assertTrue(BeanUtils.isNullOrEmpty(""));
        Assert.assertFalse(BeanUtils.isNullOrEmpty(new Object()));
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
