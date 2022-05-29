package com.rslakra.core;

import com.google.gson.FieldNamingPolicy;
import com.rslakra.core.entity.TestClass;
import com.rslakra.core.entity.TestEntity;
import com.rslakra.core.entity.User;
import com.rslakra.core.enums.DistanceUnitType;
import com.rslakra.core.enums.WeekDays;
import com.rslakra.core.json.JSONUtils;
import com.rslakra.core.utils.Dictionary;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Rohtash Lakra
 * @created 5/7/20 3:10 PM
 */
public class JSONUtilsTest {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(JSONUtilsTest.class);

    @Test
    public void testToJson() {
        TestClass testClass = new TestClass(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json = null;
        try {
            json = JSONUtils.toJson(testClass);
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }
        LOGGER.info("json: {}", json);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.contains(DistanceUnitType.METER.name().toLowerCase()));
    }

    @Test
    public void testFromJson() {
        TestClass testClass = new TestClass(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json;
        try {
            json = JSONUtils.toJson(testClass);
            LOGGER.info("json: {}", json);
            testClass = JSONUtils.fromJson(json, TestClass.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }
        LOGGER.info("testClass: {}", testClass);
        LOGGER.info("testClass: {}, {}", testClass.getDistanceUnitType(), testClass.getWeekDays());
        Assert.assertNotNull(testClass);
        Assert.assertEquals(DistanceUnitType.METER, testClass.getDistanceUnitType());
        Assert.assertEquals(WeekDays.SUNDAY, testClass.getWeekDays());
    }

    @Test
    public void testToJSONString() {
        TestClass testClass = new TestClass(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json = JSONUtils.toJSONString(testClass);
        LOGGER.info("json: {}", json);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.contains(WeekDays.SUNDAY.name()));
    }

    @Test
    public void testFromJSONString() {
        TestClass testClass = new TestClass(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json = JSONUtils.toJSONString(testClass);
        LOGGER.info("json: {}", json);
        testClass = JSONUtils.fromJSONString(json, TestClass.class);
        LOGGER.info("testClass: {}", testClass);
        LOGGER.info("testClass: {}, {}", testClass.getDistanceUnitType(), testClass.getWeekDays());
        Assert.assertNotNull(testClass);
        Assert.assertEquals(DistanceUnitType.METER, testClass.getDistanceUnitType());
        Assert.assertEquals(WeekDays.SUNDAY, testClass.getWeekDays());
    }

    @Test
    public void testJson() {
        Dictionary<String, Dictionary> dictionary = Dictionary.newDictionary().of("name", Dictionary.newDictionary()
            .of("firstName", "Rohtash Singh")
            .of("lastName", "Lakra"));
        String json;
        Dictionary tempDictionary = null;
        try {
            json = JSONUtils.toJson(dictionary);
            LOGGER.info("json: {}", json);
            tempDictionary = JSONUtils.fromJson(json, Dictionary.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }
        LOGGER.info("tempDictionary: {}", tempDictionary);
        Assert.assertNotNull(tempDictionary);
        Dictionary<String, String> pairs = tempDictionary.getDictionary("name");
        Assert.assertEquals(2, pairs.size());
        Assert.assertEquals("Rohtash Singh", pairs.get("firstName"));
        Assert.assertEquals("Lakra", pairs.get("lastName"));
    }


    @Test
    public void testSetFieldNamingPolicy() {
        User user = User.of(1001L, "rlakra", "Rohtash", "Lakra");
        TestEntity testEntity = new TestEntity();
        testEntity.setUser(user);
        JSONUtils.INSTANCE.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        // FieldNamingPolicy.IDENTITY
        String jsonTestEntity = JSONUtils.toJSONString(testEntity);
        LOGGER.info("jsonTestEntity: {}", jsonTestEntity);
        Assert.assertNotNull(jsonTestEntity);
        Assert.assertTrue(jsonTestEntity.contains("userName"));
        Assert.assertTrue(jsonTestEntity.contains("firstName"));
        Assert.assertTrue(jsonTestEntity.contains("middleName"));
        Assert.assertTrue(jsonTestEntity.contains("lastName"));
        Assert.assertTrue(jsonTestEntity.contains("Rohtash"));

        // FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        JSONUtils.INSTANCE.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        jsonTestEntity = JSONUtils.toJSONString(testEntity);
        LOGGER.info("jsonTestEntity: {}", jsonTestEntity);
        Assert.assertNotNull(jsonTestEntity);
        Assert.assertTrue(jsonTestEntity.contains("user_name"));
        Assert.assertTrue(jsonTestEntity.contains("first_name"));
        Assert.assertTrue(jsonTestEntity.contains("middle_name"));
        Assert.assertTrue(jsonTestEntity.contains("last_name"));
    }

    @Test
    public void testSerializeNulls() {
        User user = User.of(1001L, "rlakra", "Rohtash", "Lakra");
        TestEntity testEntity = new TestEntity();
        testEntity.setUser(user);
        JSONUtils.INSTANCE.serializeNulls();
        // FieldNamingPolicy.IDENTITY
        String jsonTestEntity = JSONUtils.toJSONString(testEntity);
        LOGGER.info("jsonTestEntity: {}", jsonTestEntity);
        Assert.assertNotNull(jsonTestEntity);
        Assert.assertTrue(jsonTestEntity.contains("state"));
        Assert.assertTrue(jsonTestEntity.contains("color"));
    }

}
