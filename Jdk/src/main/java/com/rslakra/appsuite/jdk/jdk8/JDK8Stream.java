package com.rslakra.appsuite.jdk.jdk8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 4/17/20 5:30 PM
 */
public class JDK8Stream {

    public enum Property {
        KEY("key"),
        VALUE("value");

        private final String value;

        /**
         * @param value
         */
        private Property(final String value) {
            this.value = value;
        }

        /**
         * @return
         */
        public final String getValue() {
            return value;
        }

        /**
         * @return
         */
        @Override
        public String toString() {
            return getValue();
        }
    }

    /**
     * @param customDataList
     * @param customDataKey
     * @return
     */
    public String getCustomDataValue(final List<Map<String, String>> customDataList, final String customDataKey) {
        return customDataList.stream()
            .filter(map -> map.containsKey(Property.KEY.toString()))
            .filter(map -> map.containsKey(Property.VALUE.toString()))
            .filter(map -> map.get(Property.KEY.toString()).equals(customDataKey))
            .collect(
                Collectors.toMap(map -> map.get(Property.KEY.toString()), map -> map.get(Property.VALUE.toString())))
            .get(customDataKey);
    }

}
