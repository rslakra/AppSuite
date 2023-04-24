package com.rslakra.appsuite.jdk.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 5/4/21 1:02 PM
 */
public class DNSCache {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(DNSCache.class);

    public static void main(String[] args) throws Exception {
        // put some values in the internal DNS cache

        // good DNS name
        InetAddress.getByName("stackoverflow.com");
        InetAddress.getByName("www.google.com");
        InetAddress.getByName("www.yahoo.com");
        try {
            // bad DNS name
            InetAddress.getByName("bad.yahoo.com");
        } catch (UnknownHostException ex) {
            // do nothing
        }

        //bnn.cfz.cl
        InetAddress.getByName("bnn.cfz.cl");

        // dump the good DNS entries
        String addressCache = "addressCache";
        LOGGER.debug("---------" + addressCache + "---------");
        printDNSCache(addressCache);

        // dump the bad DNS entries
        String negativeCache = "negativeCache";
        LOGGER.debug("---------" + negativeCache + "---------");
        printDNSCache(negativeCache);
    }

    /**
     * Returns the given <code>Field</code> of the <codee>objectClass</codee>.
     *
     * @param objectClass
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getDeclaredField(final Class<?> objectClass, final String fieldName)
        throws NoSuchFieldException {
        Field declaredField = objectClass.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        return declaredField;
    }

    /**
     * By introspection, dump the InetAddress internal DNS cache
     *
     * @param cacheName can be addressCache or negativeCache
     * @throws Exception
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void printDNSCache(String cacheName) throws Exception {
        Field declaredField = getDeclaredField(InetAddress.class, cacheName);
        Object addressCache = declaredField.get(null);
        Class cacheClass = addressCache.getClass();
        Field cacheClassDeclaredField = getDeclaredField(cacheClass, "cache");

        Map<String, Object> cache = (Map<String, Object>) cacheClassDeclaredField.get(addressCache);
        for (Map.Entry<String, Object> cacheEntry : cache.entrySet()) {
            Object cacheEntryValue = cacheEntry.getValue();
            Class cacheEntryClass = cacheEntryValue.getClass();
            Field fieldExpiration = getDeclaredField(cacheEntryClass, "expiration");
            long expires = (Long) fieldExpiration.get(cacheEntryValue);
            // JDK 1.7, older version maybe "address"
            Field fieldAddress = getDeclaredField(cacheEntryClass, "addresses");
            InetAddress[] addresses = (InetAddress[]) fieldAddress.get(cacheEntryValue);
            List<String> listOfAddresses = new ArrayList<>(addresses.length);
            for (InetAddress address : addresses) {
                listOfAddresses.add(address.getHostAddress());
            }

            LOGGER.debug(cacheEntry.getKey() + " " + new Date(expires) + " " + listOfAddresses);
        }
    }

}
