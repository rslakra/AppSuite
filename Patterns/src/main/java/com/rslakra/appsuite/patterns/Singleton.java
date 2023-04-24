package com.rslakra.appsuite.patterns;

/**
 * @author Rohtash Lakra
 * @created 10/18/19 3:59 PM
 */
public enum Singleton {
    INSTANCE;

    private int count;

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}
