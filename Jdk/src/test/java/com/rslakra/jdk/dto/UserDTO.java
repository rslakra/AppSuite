package com.rslakra.jdk.dto;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/26/20 9:24 PM
 * Version: 1.0.0
 */
public class UserDTO {
    private Long id;
    private String userName;
    private AddressDTO address;

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", address=" + address +
                '}';
    }
}
