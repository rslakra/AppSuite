package com.rslakra.core.json;

import com.google.gson.FieldNamingPolicy;
import com.rslakra.core.ClassTest;
import com.rslakra.core.algos.map.Dictionary;
import com.rslakra.core.entity.TestEntity;
import com.rslakra.core.entity.User;
import com.rslakra.core.enums.DistanceUnitType;
import com.rslakra.core.enums.WeekDays;
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
        ClassTest classTest = new ClassTest(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json = null;
        try {
            json = JSONUtils.toJson(classTest);
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }
        LOGGER.info("json: {}", json);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.contains(DistanceUnitType.METER.name().toLowerCase()));
    }

    @Test
    public void testFromJson() {
        ClassTest classTest = new ClassTest(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json;
        try {
            json = JSONUtils.toJson(classTest);
            LOGGER.info("json: {}", json);
            classTest = JSONUtils.fromJson(json, ClassTest.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }
        LOGGER.info("testClass: {}", classTest);
        LOGGER.info("testClass: {}, {}", classTest.getDistanceUnitType(), classTest.getWeekDays());
        Assert.assertNotNull(classTest);
        Assert.assertEquals(DistanceUnitType.METER, classTest.getDistanceUnitType());
        Assert.assertEquals(WeekDays.SUNDAY, classTest.getWeekDays());
    }

    @Test
    public void testToJSONString() {
        ClassTest classTest = new ClassTest(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json = JSONUtils.toJSONString(classTest);
        LOGGER.info("json: {}", json);
        Assert.assertNotNull(json);
        Assert.assertTrue(json.contains(WeekDays.SUNDAY.name()));
    }

    @Test
    public void testFromJSONString() {
        ClassTest classTest = new ClassTest(DistanceUnitType.METER, WeekDays.SUNDAY);
        String json = JSONUtils.toJSONString(classTest);
        LOGGER.info("json: {}", json);
        classTest = JSONUtils.fromJSONString(json, ClassTest.class);
        LOGGER.info("testClass: {}", classTest);
        LOGGER.info("testClass: {}, {}", classTest.getDistanceUnitType(), classTest.getWeekDays());
        Assert.assertNotNull(classTest);
        Assert.assertEquals(DistanceUnitType.METER, classTest.getDistanceUnitType());
        Assert.assertEquals(WeekDays.SUNDAY, classTest.getWeekDays());
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
        LOGGER.info("pairs: {}", pairs);
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
