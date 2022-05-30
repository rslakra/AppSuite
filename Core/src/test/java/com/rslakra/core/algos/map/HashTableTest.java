package com.rslakra.core.algos.map;

import com.rslakra.core.algos.map.HashTable;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/4/22 5:38 PM
 */
public class HashTableTest {

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
        Assert.assertNotNull(toolSet);
        Assert.assertEquals(0, toolSet.size());
        Assert.assertTrue(toolSet.isEmpty());
        System.out.println(toolSet);
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
        Assert.assertNotNull(toolSet);
        Assert.assertEquals(12, toolSet.size());
        Assert.assertFalse(toolSet.isEmpty());
        System.out.println("Toolset size: " + toolSet.size());
        System.out.println("Toolset: " + toolSet);
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
        System.out.println("Toolset size: " + toolSet.size());
        System.out.println("Toolset: " + toolSet);
    }

    private void removeSkidPlate() {
        //get the 10mm socket and wrench
        String socket = toolSet.get("10mm");
        String wrench = toolSet.get("1/4d");
        System.out.println("Removed 4 skid plate nuts with the " + socket + " and " + wrench);
        System.out.println("Toolset: " + toolSet);
    }

    private void replaceSkidPlate() {
        //get the 10mm socket and wrench
        String socket = toolSet.get("10mm");
        String wrench = toolSet.get("1/4d");
        System.out.println("Replaced 4 skid plate nuts with the " + socket + " and " + wrench);
        System.out.println("Toolset: " + toolSet);
    }

    private void drainAndReplaceOil() {
        //this motorcycle's drain plug nut is 17mm.  That's a strange size, check to see if the toolSet has it
        System.out.println(
            "Toolset has 17mm socket: " + toolSet.containsValue("17mm Socket") + " - " + toolSet.containsKey("17mm"));

        String socket = toolSet.get("17mm");
        String wrench = toolSet.get("1/4d");
        System.out.println("Removed oil drain plug with " + socket + " and " + wrench);
        System.out.println("Drained 1.7 quarts of oil");
        System.out.println("Replaced oil drain plug with " + socket + " and " + wrench);
        System.out.println("Added 1.7 quarts of oil");
        System.out.println("Toolset: " + toolSet);
    }

    @Test
    public void testHashTable() {
        HashTable<String, String> hashTable = newHashTable();
        Assert.assertNotNull(hashTable);
        Assert.assertEquals(0, hashTable.size());
        Assert.assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testSizeAndIsEmpty() {
        HashTable<String, String> toolSet = newHashTable(true);
        Assert.assertNotNull(toolSet);
        Assert.assertEquals(12, toolSet.size());
        Assert.assertFalse(toolSet.isEmpty());
        System.out.println(toolSet);
    }

    @Test
    public void testPut() {
        HashTable<String, String> toolSet = newHashTable(true);
        toolSet.put("null", null);
        Assert.assertEquals(13, toolSet.size());
        System.out.println(toolSet);
    }


    @Test
    public void testGet() {
        HashTable<String, String> toolSet = newHashTable(true);
        //get the 10mm socket and wrench
        String socket = toolSet.get("10mm");
        String wrench = toolSet.get("1/4d");
        Assert.assertEquals("10mm Socket", socket);
        Assert.assertEquals("1/4 socket wrench", wrench);
        String unKnownSocket = toolSet.get("101mm");
        Assert.assertNotEquals("unKnownSocket", unKnownSocket);
        System.out.println("Removed 4 skid plate nuts with the " + socket + " and " + wrench);
        System.out.println("Toolset: " + toolSet);
    }

    @Test
    public void testContainsKey() {
        HashTable<String, String> toolSet = newHashTable(true);
        //get the 10mm socket and wrench
        Assert.assertTrue(toolSet.containsKey("10mm"));
        Assert.assertTrue(toolSet.containsKey("1/4d"));
        Assert.assertFalse(toolSet.containsKey("1/34d"));
    }

    @Test
    public void testContainsValue() {
        HashTable<String, String> toolSet = newHashTable(true);
        //get the 10mm socket and wrench
        Assert.assertFalse(toolSet.containsValue("10mm"));
        Assert.assertFalse(toolSet.containsValue("1/4d"));
        Assert.assertFalse(toolSet.containsValue("1/34d"));
    }

    @Test
    public void testRemove() {
        HashTable<String, String> toolSet = newHashTable(true);
        toolSet.remove("1/34d");
//        Assert.assertFalse();
    }

    @Test
    public void testPutAll() {
        HashTable<String, String> toolSet = newHashTable(true);
    }

    @Test
    public void testClear() {
        HashTable<String, String> toolSet = newHashTable(true);
        toolSet.clear();
        Assert.assertEquals(0, toolSet.size());
        Assert.assertTrue(toolSet.isEmpty());
    }


    @Test
    public void testKeySet() {
        HashTable<String, String> toolSet = newHashTable(true);
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
