package com.rslakra.java.annotations.serialize;


import com.rslakra.jdk.annotations.serialize.JsonElement;
import com.rslakra.jdk.annotations.serialize.JsonInit;
import com.rslakra.jdk.annotations.serialize.JsonSerializable;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 10/3/19 8:38 AM
 */
@JsonSerializable
public class Person {

    @JsonElement
    private String firstName;

    @JsonElement
    private String lastName;

    @JsonElement(key = "gender")
    private String sex;

    private String address;

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String toUpperCaseFirstLetter(String str) {
        return (str.substring(0, 1).toUpperCase() + str.substring(1));
    }

    @JsonInit
    private void initNames() {
        this.firstName = toUpperCaseFirstLetter(getFirstName());
        this.lastName = toUpperCaseFirstLetter(getLastName());
    }
}
