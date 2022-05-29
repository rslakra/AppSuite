package com.rslakra.testcases.httpclient.http;

import java.util.Iterator;

/**
 * @author Rohtash Lakra
 * @created 4/13/20 5:59 PM
 */
public enum ContentType {
    ANY(new String[]{"*/*"}),
    TEXT(new String[]{"text/plain"}),
    JSON(new String[]{"application/json", "application/javascript", "text/javascript"}),
    XML(new String[]{"application/xml", "text/xml", "application/xhtml+xml", "application/atom+xml"}),
    HTML(new String[]{"text/html"}),
    URL_ENCODED(new String[]{"application/x-www-form-urlencoded"}),
    BINARY(new String[]{"application/octet-stream"});

    private final String[] contentTypes;

    /**
     * @return
     */
    public String[] getContentTypes() {
        return this.contentTypes;
    }

    /**
     * @return
     */
    public String toString() {
        return this.contentTypes[0];
    }

    /**
     * @return
     */
    public String getAcceptHeader() {
        final Iterator<String> itr = new ArrayIterator(this.contentTypes);
        final StringBuilder sBuilder = new StringBuilder();
        while (itr.hasNext()) {
            sBuilder.append(itr.next());
            if (itr.hasNext()) {
                sBuilder.append(", ");
            }
        }

        return sBuilder.toString();
    }

    /**
     * @param contentTypes
     */
    private ContentType(final String... contentTypes) {
        this.contentTypes = contentTypes;
    }
}

