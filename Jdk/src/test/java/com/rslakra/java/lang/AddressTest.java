package com.rslakra.java.lang;

import com.rslakra.appsuite.jdk.lang.Address;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 1/26/21 12:33 PM
 */
public class AddressTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressTest.class);

    @Test
    public void testIsSameAddress() {
        /**
         * 701 1st Ave
         * Sunnyvale, CA 94089
         */
        Address office = new Address();
        office.setId(Long.valueOf(2014242));
        office.setAddressLine1("701 1st Ave");
        office.setCity("Sunnyvale");
        office.setState("CA");
        office.setCountry("United States");
        office.setPostalCode("94089");

        /**
         * 1199 Coleman Ave
         * San Jose, CA 95110
         */
        Address newOffice = new Address();
        newOffice.setId(Long.valueOf(2014242));
        newOffice.setAddressLine1("1199 Coleman Ave");
        newOffice.setCity("San Jose");
        newOffice.setState("CA");
        newOffice.setCountry("United States");
        newOffice.setPostalCode("95110");

        if (office.getId().equals(newOffice.getId())) {
            LOGGER.debug("Equals");
        } else {
            LOGGER.debug("Not Equals");
        }

        if (office.getId().compareTo(newOffice.getId()) == 0) {
            LOGGER.debug("Equals");
        } else {
            LOGGER.debug("Not Equals");
        }

//        Assert.assertEquals(office, newOffice);
    }
}
