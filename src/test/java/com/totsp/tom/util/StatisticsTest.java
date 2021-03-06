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
package com.totsp.tom.util;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import com.totsp.tom.Address;
import com.totsp.tom.Person;
import com.totsp.tom.Tom;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.concat;
import static com.totsp.tom.Tom.tom;
import static org.junit.Assert.assertEquals;

/**
 *
 */
public class StatisticsTest {
    @Test
    public void testHistogram() throws Exception {
        Tom.initialize(new JVMIntrospector());
        List<Person> people = Arrays.asList(
                new Person("Sherlock", "Holmes", new Address("221B Baker St", "London", "GA", "30308")),
                new Person("Homer", "Simpson", new Address("742 Evergreen Tr", "Springfield", "KY", "42082"), new Address("555 Main St.", "Nowhere", "Georgia", "30308")),
                new Person("John", "Doe", new Address("1234 Broadway", "New York", "NY", "10001")),
                new Person("Jane", "Doe", new Address("1235 Broadway", "New York", "NY", "10002"))
        );
        Statistics<String> stats = new Statistics<String>();

        tom().select("zip").from(
               concat(tom().select("addresses").from(people).every().asIterable())
            ).every().into(stats);

        Map<String, Integer> histogram = stats.histogram();

        Map.Entry<String, Integer>[] entries =histogram.entrySet().toArray(new Map.Entry[0]);
        assertEquals("10001", entries[0].getKey());
        assertEquals((Integer) 1, entries[0].getValue());
        assertEquals("10002", entries[1].getKey());
        assertEquals((Integer) 1, entries[1].getValue());
        assertEquals("30308", entries[2].getKey());
        assertEquals((Integer) 2, entries[2].getValue());
        assertEquals("42082", entries[3].getKey());
        assertEquals((Integer) 1, entries[3].getValue());
    }

    @Test
    public void testMode() throws Exception {
        Tom.initialize(new JVMIntrospector());
        List<Person> people = Arrays.asList(
                new Person("Sherlock", "Holmes", new Address("221B Baker St", "London", "GA", "30308")),
                new Person("Homer", "Simpson", new Address("742 Evergreen Tr", "Springfield", "KY", "42082"), new Address("555 Main St.", "Nowhere", "GA", "30308")),
                new Person("John", "Doe", new Address("1234 Broadway", "New York", "NY", "10001")),
                new Person("Jane", "Doe", new Address("1235 Broadway", "New York", "NY", "10002"))
        );
        Statistics<String> stats = new Statistics<String>();
        tom().select("zip").from(
                concat(tom().select("addresses").from(people).every().asIterable())
        ).every().into(stats);

        assertEquals("30308", stats.mode());

        stats = new Statistics<String>();
        tom().select("addresses._size").from(people).every().into(stats);

        assertEquals(1, stats.mode());
        System.out.println(stats.histogram());
    }
}
