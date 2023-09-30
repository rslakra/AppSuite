package com.rslakra.appsuite.questions.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Rohtash Lakra
 * @created 9/25/23 12:12 PM
 */
public class LC721AccountsMerge {

    private static final Logger LOGGER = LoggerFactory.getLogger(LC721AccountsMerge.class);

    /**
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        LOGGER.debug("+accountsMerge({})", accounts);
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Map<String, TreeSet<String>> unionFind = new HashMap<>();

        // Step 1: Initialization
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                parent.put(account.get(i), account.get(i));
                owner.put(account.get(i), account.get(0));
            }
        }
        LOGGER.debug("owner:{}", owner);
        LOGGER.debug("parent:{}", parent);
        LOGGER.debug("unionFind:{}", unionFind);

        // Step 2: Merge each lines
        for (List<String> account : accounts) {
            String root = findParent(account.get(1), parent);
            for (int i = 2; i < account.size(); i++) {
                parent.put(findParent(account.get(i), parent), root);
            }
        }

        LOGGER.debug("owner:{}", owner);
        LOGGER.debug("parent:{}", parent);
        LOGGER.debug("unionFind:{}", unionFind);

        // Step 3: Add to unionFind. (Actually merge different lines)
        for (List<String> account : accounts) {
            String root = findParent(account.get(1), parent);
            if (!unionFind.containsKey(root)) {
                unionFind.put(root, new TreeSet<String>());
            }
            for (int i = 1; i < account.size(); i++) {
                unionFind.get(root).add(account.get(i));
            }
        }

        LOGGER.debug("owner:{}", owner);
        LOGGER.debug("parent:{}", parent);
        LOGGER.debug("unionFind:{}", unionFind);

        // Step 4: Create result List.
        List<List<String>> result = new ArrayList<>();
        for (String key : unionFind.keySet()) {
            TreeSet<String> unionNode = unionFind.get(key);
            List<String> temp = new ArrayList<>();
            temp.add(owner.get(unionNode.first()));
            temp.addAll(unionNode);
            result.add(temp);
        }

        LOGGER.debug("-accountsMerge(), result:{}", result);
        return result;
    }

    /**
     * @param key
     * @param accounts
     * @return
     */
    private String findParent(String key, Map<String, String> accounts) {
        LOGGER.debug("+find()", key, accounts);
        if (!accounts.get(key).equals(key)) {
            LOGGER.debug("Updating key:{}, accounts:{}", key, accounts);
            accounts.put(key, findParent(accounts.get(key), accounts));
            LOGGER.debug("Updated key:{}, accounts:{}", key, accounts);
        }

        String result = accounts.get(key);
        LOGGER.debug("-find(), result:{}", result);
        return result;
    }
}
