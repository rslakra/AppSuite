package com.rslakra.appsuite.example.enums;

/**
 * @author Rohtash Lakra
 * @created 3/30/20 3:48 PM
 */
public enum Language {
    EN("English", 1070L),
    HI("Hindi", 1090L),
    ZH_CN("Simplified Chinese", 1188L),
    ZH_TW("Traditional Chinese", 1189L),
    ANY("Any"),
    NONE("None"),
    OTHER("Other");

    private final String name;
    private final Long tagId;

    /**
     * @param name
     * @param tagId
     */
    private Language(final String name, final Long tagId) {
        this.name = name;
        this.tagId = tagId;
    }

    /**
     * @param name
     */
    private Language(final String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public Long getTagId() {
        return tagId;
    }
}
