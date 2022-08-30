package com.rslakra.core.http;

import com.rslakra.core.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 1/24/22 4:39 PM
 */
@Getter
@Setter
public final class Headers extends LinkedHashMap<String, List<String>> {

    public static final String ACCEPT_LANGUAGE_VALUE = "en-US,en;q=0.5";
    public static final String KEEP_ALIVE = "keep-alive";
    public static final String WILDCARD = "*/*";

    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";

    public static final String EXPIRES = "Expires";
    public static final String PRAGMA = "Pragma";
    public static final String PRAGMA_PUBLIC = "public";
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String USER_AGENT = "User-Agent";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String SET_COOKIE = "Set-Cookie";
    public static final String COOKIE = "Cookie";
    public static final String LOCATION = "Location";
    public static final String SERVER = "Server";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";

    public static final String REQUEST_TRACER = "Request-Tracer";
    public static final String DEVICE_ID = "Device-Id";
    public static final String CLIENT_ID = "Client-Id";

    /**
     * @param key
     * @param value
     */
    public void addHeader(final String key, final String value) {
        this.addHeader(key, Arrays.asList(value));
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
     * @param headerEntry
     */
    private void addHeader(final Map.Entry<String, List<String>> headerEntry) {
        addHeader(headerEntry.getKey(), headerEntry.getValue());
    }

    /**
     * Adds all headers.
     *
     * @param headers
     */
    public void addHeaders(final Headers headers) {
        if (BeanUtils.isNotNull(headers)) {
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
    public List<String> getHeaders(final String key) {
        return super.get(key);
    }

}
