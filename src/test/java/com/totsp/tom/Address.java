package com.totsp.tom;

import com.totsp.gwittir.introspection.Introspectable;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 8/19/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Introspectable
public class Address {

    private final String street;
    private final String city;
    private final String state;
    private final String zip;

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
