package com.rslakra.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 5/31/22 4:37 PM
 */
public class SetTest {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(SetTest.class);

    public static void main(String[] args) {
        Map<Long, List<Long>> accountIdRoleMap = new HashMap<>();
        accountIdRoleMap.put(1003111574l, Arrays.asList(1003111574l));

        Set<Long> resourceIdsForExtB2BUser = new HashSet<>();
        resourceIdsForExtB2BUser.add(1003111574l);

        Set<Long> accountIdSet = new HashSet(accountIdRoleMap.keySet());
        boolean result = accountIdSet.retainAll(resourceIdsForExtB2BUser);
        LOGGER.debug("result:" + result);
        LOGGER.debug("accountIdRoleMap:" + accountIdRoleMap);
        LOGGER.debug("accountIdSet:" + accountIdSet);
        LOGGER.debug("accountIdSet.isEmpty:" + accountIdSet.isEmpty());

        Set<Long> resourceIds = new HashSet<>();
        result = accountIdSet.retainAll(resourceIds);
        LOGGER.debug("result:" + result);
        LOGGER.debug("accountIdRoleMap:" + accountIdRoleMap);
        LOGGER.debug("accountIdSet:" + accountIdSet);
        LOGGER.debug("accountIdSet.isEmpty:" + accountIdSet.isEmpty());
    }
}
