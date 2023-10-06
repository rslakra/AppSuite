package com.rslakra.appsuite.algos.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 4/29/22 1:34 PM
 */
public class MotorCycleOilChange {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(MotorCycleOilChange.class);

    //we will use a 12 pieces socket set for this job
    private HashMap<String, String> toolSet = new HashMap<>(12);

    /**
     *
     */
    public void changeOil() {
        buildToolSet();

        //remove skid plate to get at the oil pan
        removeSkidPlate();

        //drain the oil by removing the drain plug and refill
        drainAndReplaceOil();

        //replace the skid plate
        replaceSkidPlate();

        emptyToolset();
    }

    /**
     * This loads the toolbox with all the needed tools
     */
    private void buildToolSet() {
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
        LOGGER.debug("Toolset size: " + toolSet.size());
        LOGGER.debug("Toolset: " + toolSet);
    }

    private void emptyToolset() {
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
}
