package com.rslakra.java.jdk8;

import com.rslakra.core.entity.User;
import com.rslakra.jdk.jdk8.JDK8Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 4/17/20 5:31 PM
 */
public class JDK8StreamTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(JDK8StreamTest.class);

    @Test
    public void testGetCustomDataValue() {
        final List<Map<String, String>> customDataList = new ArrayList<>();
        //1
        Map<String, String> entry = new HashMap<>();
        entry.putIfAbsent(JDK8Stream.Property.KEY.toString(), "1");
        entry.putIfAbsent(JDK8Stream.Property.VALUE.toString(), "One");
        customDataList.add(entry);

        //2
        entry = new HashMap<>();
        entry.putIfAbsent(JDK8Stream.Property.KEY.toString(), "2");
        entry.putIfAbsent(JDK8Stream.Property.VALUE.toString(), "Two");
        customDataList.add(entry);

        //3
        entry = new HashMap<>();
        entry.putIfAbsent(JDK8Stream.Property.KEY.toString(), "3");
        entry.putIfAbsent(JDK8Stream.Property.VALUE.toString(), "Three");
        customDataList.add(entry);

        //4
        entry = new HashMap<>();
        entry.putIfAbsent(JDK8Stream.Property.KEY.toString(), "4");
        entry.putIfAbsent(JDK8Stream.Property.VALUE.toString(), "Four");
        customDataList.add(entry);

        //5
        entry = new HashMap<>();
        entry.putIfAbsent(JDK8Stream.Property.KEY.toString(), "5");
        entry.putIfAbsent(JDK8Stream.Property.VALUE.toString(), "Five");
        customDataList.add(entry);

        //wrong value
        entry = new HashMap<>();
        entry.putIfAbsent(JDK8Stream.Property.KEY.toString(), JDK8Stream.Property.VALUE.toString());
        customDataList.add(entry);

        LOGGER.debug("{}",customDataList);
        JDK8Stream jdk8Stream = new JDK8Stream();
        String customDataValue = jdk8Stream.getCustomDataValue(customDataList, "3");
        Assert.assertNotNull(customDataValue);
        Assert.assertEquals(customDataValue, "Three");

        customDataValue = jdk8Stream.getCustomDataValue(customDataList, "key");
        Assert.assertNull(customDataValue);

        customDataValue = jdk8Stream.getCustomDataValue(customDataList, "0");
        Assert.assertNull(customDataValue);
    }

    @Test
    public void testEmails() {
        List<User> emailUsers = Arrays.asList(new User());
        List<String>
            emails =
            emailUsers.stream().filter(user -> StringUtils.isNotBlank(user.getEmail())).map(user -> user.getEmail())
                .collect(Collectors.toList());
    }

}
