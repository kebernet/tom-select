package com.totsp.tom;

import com.totsp.gwittir.introspection.Introspectable;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Introspectable
public class Person {

    private final String firstName;
    private final String lastName;
    private final List<Address> addresses;

    public Person(String firstName, String lastName, Address... addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addresses = Arrays.asList(addresses);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
