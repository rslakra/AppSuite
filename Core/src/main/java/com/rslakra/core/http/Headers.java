package com.rslakra.core.http;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 1/24/22 4:39 PM
 */
@Getter
@Setter
public final class Headers extends LinkedHashMap<String, List<String>> {

    /**
     * @param key
     * @param value
     */
    public void addHeader(final String key, final String value) {
        if (super.containsKey(key)) {
            super.get(key).add(value);
        } else {
            super.put(key, Arrays.asList(value));
        }
    }

    /**
     * @param key
     * @param values
     */
    public void addHeader(final String key, final List<String> values) {
        if (super.containsKey(key)) {
            super.get(key).addAll(values);
        } else {
            super.put(key, values);
        }
    }

    /**
     * @param entry
     */
    private void addHeader(final Map.Entry<String, List<String>> entry) {
        addHeader(entry.getKey(), entry.getValue());
    }

    /**
     * Adds all headers.
     *
     * @param headers
     */
    public void addHeaders(final Headers headers) {
        if (headers != null) {
            headers.entrySet().stream().forEach(entry -> addHeader(entry));
        }
    }

    /**
     * @return
     */
    public Headers getHeaders() {
        return this;
    }

    /**
     * @return
     */
    public List<String> getHeader(final String key) {
        return super.get(key);
    }

}
