package com.rslakra.appsuite.jdk.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @author Rohtash Lakra
 * @created 2/9/21 11:51 AM rlakra 2/9/21 11:51 AM
 */
public class Str {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(Str.class);

    //    private static final Pattern REGEX_VAT_ID = Pattern.compile("^[a-zA-Z0-9]+$");
//    private static final Pattern REGEX_VAT_ID = Pattern.compile("^([A-Z]{2,3}[A-Z0-9]+)$");
    private static final Pattern REGEX_VAT_ID = Pattern.compile("[A-Z]{0,3}[A-Z0-9\\.\\-\\/ ]");

    public static boolean isValidVATNumber(String vatNumber) {
        return REGEX_VAT_ID.matcher(vatNumber).matches();
    }

    public static void main(String[] args) {
        String[] vatNumbers = {
                "FR08788882504",
                "RO15207820",
                "CHE473010108MWST",
                "ro15207820",
                "822478341",
                "8568.28.890.B.01",
                "CHE-473.010.108 MWST",
                "18.175.431/0001-54"
        };

        for (String vatNumber : vatNumbers) {
            LOGGER.debug(vatNumber + " is valid: " + Str.isValidVATNumber(vatNumber));
        }

        int ctr = 0;
        LOGGER.debug("Before loop");
        do {
            LOGGER.debug("ctr: " + ctr);
            if (ctr == 1) {
                LOGGER.debug("Break! ctr:" + ctr);
                break;
            }

            ctr += 1;

        } while (ctr < 3);
        LOGGER.debug("After loop");
    }

}
