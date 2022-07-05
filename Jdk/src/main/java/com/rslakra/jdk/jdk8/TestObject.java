package com.rslakra.jdk.jdk8;

/**
 * @author Rohtash Lakra
 * @created 7/15/20 10:55 AM
 */
public class TestObject {

    private int id;
    private String name;

    TestObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("TestObject<id:%d, name:%s>", getId(), getName());
    }
}
