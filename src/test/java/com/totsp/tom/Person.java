/**
 *    Copyright 2013 Robert Cooper
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
