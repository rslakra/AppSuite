package com.rslakra.core;

import com.rslakra.core.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Rohtash Lakra
 * @since 12/17/19 5:30 PM
 */
public enum Networks {
    INSTANCE;

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(Networks.class);
    public static final String OCTET_ZERO = "0";
    public static final String OCTET_255 = "255";

    /**
     * Returns the integer equivalent.
     *
     * @param numString
     * @return
     */
    public static int toInteger(final String numString) {
        return Integer.parseInt(numString);
    }

    /**
     * @param ipOctet
     * @param subNetMaskOctet
     * @return
     */
    public static String toBroadcastAddressOctet(String ipOctet, String subNetMaskOctet) {
        LOGGER.debug("+toBroadcastAddressOctet({}, {})", ipOctet, subNetMaskOctet);
        String broadCastAddressOctet = null;
        if (OCTET_ZERO.equals(subNetMaskOctet)) {
            broadCastAddressOctet = OCTET_255;
        } else if (OCTET_255.equals(subNetMaskOctet)) {
            broadCastAddressOctet = ipOctet;
        } else {
            int ipOctetValue = toInteger(ipOctet);
            int subNetMaskOctetValue = toInteger(subNetMaskOctet);
            LOGGER.debug("ipOctetValue:{}, subNetMaskOctetValue:{})", ipOctetValue, subNetMaskOctetValue);
            int magicMultiplier = 256 - subNetMaskOctetValue;
            int result = (magicMultiplier * ((ipOctetValue / magicMultiplier) + 1)) - 1;
            LOGGER.debug("magicMultiplier:{}, result:{})", magicMultiplier, result);
            broadCastAddressOctet = String.valueOf(result);
        }

        LOGGER.debug("-toBroadcastAddressOctet(), broadCastAddressOctet:{}", broadCastAddressOctet);
        return broadCastAddressOctet;
    }

    /**
     * Returns the broadcast address of the given IP address and subnet mask.
     *
     * @param ipAddress
     * @param subNetMask
     * @return
     */
    public static String findBroadcastAddress(String ipAddress, String subNetMask) {
        LOGGER.debug("+findBroadcastAddress({}, {})", ipAddress, subNetMask);
        final StringBuilder broadCastAddress = new StringBuilder();
        if (BeanUtils.isNotNullOrEmpty(ipAddress) && BeanUtils.isNotNullOrEmpty(subNetMask)) {
            String[] ipTokens = ipAddress.split("[.]");
            String[] subNetMaskTokens = subNetMask.split("[.]");
            for (int i = 0; i < ipTokens.length; i++) {
                broadCastAddress.append(toBroadcastAddressOctet(ipTokens[i], subNetMaskTokens[i]));
                if (i < ipTokens.length - 1) {
                    broadCastAddress.append(".");
                }
            }
        }

        LOGGER.debug("-findBroadcastAddress(), broadCastAddress:{}", broadCastAddress);
        return broadCastAddress.toString();
    }

}
