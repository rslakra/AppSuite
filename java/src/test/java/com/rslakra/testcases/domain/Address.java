package com.rslakra.testcases.domain;

/**
 * Author: Rohtash Singh Lakra
 * Created: 5/26/20 9:25 PM
 * Version: 1.0.0
 */
public class Address {
    private Long id;
    private String line1;
    private State state;
    private String city;
    private String zip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", line1='" + line1 + '\'' +
                ", state=" + state +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
