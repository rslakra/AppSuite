package com.rslakra.core.http;

import com.rslakra.core.ArrayIterator;

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
    FORM_URL_ENCODED(
        new String[]{"application/x-www-form-urlencoded, application/x-www-form-urlencoded;charset=UTF-8"}),
    X_JAVA_SERIALIZED_OBJECT(new String[]{"application/x-java-serialized-object"}),
    MULTIPART_FORM_DATA(new String[]{"multipart/form-data"}),
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

