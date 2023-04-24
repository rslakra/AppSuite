package com.rslakra.appsuite.jdk.enums;

/**
 * @author Rohtash Lakra
 * @created 4/23/20 7:04 PM
 */
public enum Color {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow");

    private String value;

    Color(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
