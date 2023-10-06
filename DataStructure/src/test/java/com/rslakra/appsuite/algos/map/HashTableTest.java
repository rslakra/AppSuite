package com.rslakra.appsuite.algos.map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra
 * @created 5/4/22 5:38 PM
 */
public class HashTableTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(HashTableTest.class);

    // toolSet
    private HashTable<String, String> toolSet = new HashTable<>();

    /**
     * Returns the new <code>HashTable</code>.
     *
     * @return
     */
    private HashTable newHashTable() {
        return newHashTable(false);
    }

    /**
     * Returns the new <code>HashTable</code>.
     *
     * @param filled
     * @return
     */
    private HashTable newHashTable(final boolean filled) {
        final HashTable<String, String> toolSet = new HashTable<>();
        assertNotNull(toolSet);
        assertEquals(0, toolSet.size());
        assertTrue(toolSet.isEmpty());
        LOGGER.debug("{}", toolSet);
        if (filled) {
            buildToolSet(toolSet);
        }

        return toolSet;
    }

    /**
     * This loads the toolbox with all the needed tools
     */
    private void buildToolSet(HashTable<String, String> toolSet) {
        toolSet.put("8mm", "8mm Socket");
        toolSet.put("10mm", "10mm Socket");
        toolSet.put("12mm", "12mm Socket");
        toolSet.put("14mm", "14mm Socket");
        toolSet.put("17mm", "17mm Socket");
        toolSet.put("1/4sae", "1/4sae Socket");
        toolSet.put("3/8sae", "3/8sae Socket");
        toolSet.put("1/2sae", "1/2sae Socket");
        toolSet.put("5/8sae", "5/8sae Socket");
        toolSet.put("3/4sae", "3/4sae Socket");
        toolSet.put("1/4d", "1/4 socket wrench");
        toolSet.put("2de", "2 inch Drive extension");
        assertNotNull(toolSet);
        assertEquals(12, toolSet.size());
        assertFalse(toolSet.isEmpty());
        LOGGER.debug("Toolset size: " + toolSet.size());
        LOGGER.debug("Toolset: " + toolSet);
    }

    public void emptyToolset() {
        toolSet.remove("8mm");
        toolSet.remove("10mm");
        toolSet.remove("12mm");
        toolSet.remove("14mm");
        toolSet.remove("17mm");
        toolSet.remove("1/4sae");
        toolSet.remove("3/8sae");
        toolSet.remove("1/2sae");
        toolSet.remove("5/8sae");
        toolSet.remove("3/4sae");
        toolSet.remove("1/4d");
        toolSet.remove("2de");
        LOGGER.debug("Toolset size: " + toolSet.size());
        LOGGER.debug("Toolset: " + toolSet);
    }

    private void removeSkidPlate() {
        //get the 10mm socket and wrench
        String socket = toolSet.get("10mm");
        String wrench = toolSet.get("1/4d");
        LOGGER.debug("Removed 4 skid plate nuts with the " + socket + " and " + wrench);
        LOGGER.debug("Toolset: " + toolSet);
    }

    private void replaceSkidPlate() {
        //get the 10mm socket and wrench
        String socket = toolSet.get("10mm");
        String wrench = toolSet.get("1/4d");
        LOGGER.debug("Replaced 4 skid plate nuts with the " + socket + " and " + wrench);
        LOGGER.debug("Toolset: " + toolSet);
    }

    private void drainAndReplaceOil() {
        //this motorcycle's drain plug nut is 17mm.  That's a strange size, check to see if the toolSet has it
        LOGGER.debug(
            "Toolset has 17mm socket: " + toolSet.containsValue("17mm Socket") + " - " + toolSet.containsKey("17mm"));

        String socket = toolSet.get("17mm");
        String wrench = toolSet.get("1/4d");
        LOGGER.debug("Removed oil drain plug with " + socket + " and " + wrench);
        LOGGER.debug("Drained 1.7 quarts of oil");
        LOGGER.debug("Replaced oil drain plug with " + socket + " and " + wrench);
        LOGGER.debug("Added 1.7 quarts of oil");
        LOGGER.debug("Toolset: " + toolSet);
    }

    @Test
    public void testHashTable() {
        HashTable<String, String> hashTable = newHashTable();
        assertNotNull(hashTable);
        assertEquals(0, hashTable.size());
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testSizeAndIsEmpty() {
        HashTable<String, String> toolSet = newHashTable(true);
        assertNotNull(toolSet);
        assertEquals(12, toolSet.size());
        assertFalse(toolSet.isEmpty());
        LOGGER.debug("{}", toolSet);
    }

    @Test
    public void testPut() {
        HashTable<String, String> toolSet = newHashTable(true);
        toolSet.put("null", null);
        assertEquals(13, toolSet.size());
        LOGGER.debug("{}", toolSet);
    }


    @Test
    public void testGet() {
        HashTable<String, String> toolSet = newHashTable(true);
        //get the 10mm socket and wrench
        String socket = toolSet.get("10mm");
        String wrench = toolSet.get("1/4d");
        assertEquals("10mm Socket", socket);
        assertEquals("1/4 socket wrench", wrench);
        String unKnownSocket = toolSet.get("101mm");
        assertNotEquals("unKnownSocket", unKnownSocket);
        LOGGER.debug("Removed 4 skid plate nuts with the " + socket + " and " + wrench);
        LOGGER.debug("Toolset: " + toolSet);
    }

    @Test
    public void testContainsKey() {
        HashTable<String, String> toolSet = newHashTable(true);
        //get the 10mm socket and wrench
        assertTrue(toolSet.containsKey("10mm"));
        assertTrue(toolSet.containsKey("1/4d"));
        assertFalse(toolSet.containsKey("1/34d"));
    }

    @Test
    public void testContainsValue() {
        HashTable<String, String> toolSet = newHashTable(true);
        //get the 10mm socket and wrench
        assertFalse(toolSet.containsValue("10mm"));
        assertFalse(toolSet.containsValue("1/4d"));
        assertFalse(toolSet.containsValue("1/34d"));
    }

    @Test
    public void testRemove() {
        HashTable<String, String> toolSet = newHashTable(true);
        toolSet.remove("1/34d");
// assertFalse();
    }

    @Test
    public void testPutAll() {
        HashTable<String, String> toolSet = newHashTable(true);
    }

    @Test
    public void testClear() {
        HashTable<String, String> toolSet = newHashTable(true);
        toolSet.clear();
        assertEquals(0, toolSet.size());
        assertTrue(toolSet.isEmpty());
    }


    @Test
    public void testKeySet() {
        HashTable<String, String> toolSet = newHashTable(true);
        assertTrue(toolSet.size() > 0);
        assertNotNull(toolSet.keySet());
    }

    @Test
    public void testValues() {
        HashTable<String, String> toolSet = newHashTable(true);
    }

    @Test
    public void testEntrySet() {
        HashTable<String, String> toolSet = newHashTable(true);
    }

}
