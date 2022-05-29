package com.rslakra.jdk.domain;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/26/20 9:24 PM
 * Version: 1.0.0
 */
public class User {
    private Long id;
    private String userName;
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", address=" + address +
                '}';
    }
}
