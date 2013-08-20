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

import com.google.common.collect.Iterables;
import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import com.totsp.tom.predicates.TestBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.google.common.collect.Iterables.concat;
import static com.totsp.tom.Tom.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TomTest {

    @Test
    public void testSimple(){
        ArrayList<TestBean> beans = new ArrayList<TestBean>(100);
        for(int i=0; i < 100; i++){
            TestBean b = new TestBean();
            b.setIntProperty(i);
            beans.add(b);
        }
        Tom.initialize(new JVMIntrospector());

        Iterable<TestBean> it = tom().select()
                                     .from(beans)
                                     .where(anyOf(
                                             ltEq("intProperty", 10),
                                             gtEq("intProperty", 90))
                                     ).asIterable();
        for(TestBean b: it){
            assertTrue(b.getIntProperty() <= 10 || b.getIntProperty() >= 90);
        }

        it = tom().select()
                .from(beans)
                .where( ltEq("intProperty", 10)).asIterable();
        for(TestBean b: it){
            assertTrue(b.getIntProperty() <= 10 );
        }

    }

    @Test
    public void anyInACollectionTest(){
        Tom.initialize(new JVMIntrospector());
        List<Person> people = Arrays.asList(
                new Person("Sherlock", "Holmes", new Address("221B Baker St", "London", "GA", "30308")),
                new Person("Homer", "Simpson", new Address("742 Evergreen Tr", "Springfield", "KY", "42082"), new Address("555 Main St.", "Nowhere", "Georgia", "30308")),
                new Person("John", "Doe", new Address("1234 Broadway", "New York", "NY", "10001")),
                new Person("Jane", "Doe", new Address("1235 Broadway", "New York", "NY", "10002"))
        );

        ArrayList<Person> selected = new ArrayList<Person>();
        tom().select().from(people).where(eq("addresses.zip", "30308")).into(selected);

        assertEquals(2, selected.size());
        assertEquals("Holmes", selected.get(0).getLastName());
        assertEquals("Simpson", selected.get(1).getLastName());

        selected = new ArrayList<Person>();
        tom().select().from(people).where(anyOf(eq("addresses.zip", "10001"), eq("addresses.zip", "10002"))).into(selected);

        assertEquals(2, selected.size());
        assertEquals("Doe", selected.get(0).getLastName());
        assertEquals("Doe", selected.get(1).getLastName());
    }

    @Test
    public void weirdCaseTest(){
        Tom.initialize(new JVMIntrospector());
        List<Person> people = Arrays.asList(
                new Person("Sherlock", "Holmes", new Address("221B Baker St", "London", "GA", "30308")),
                new Person("Homer", "Simpson", new Address("742 Evergreen Tr", "Springfield", "KY", "42082"), new Address("555 Main St.", "Nowhere", "Georgia", "30308")),
                new Person("John", "Doe", new Address("1234 Broadway", "New York", "NY", "10001")),
                new Person("Jane", "Doe", new Address("1235 Broadway", "New York", "NY", "10002"))
        );

        ArrayList<HashMap<String,String>> selected = new ArrayList<HashMap<String,String>>();
        tom().select("street", "zip").from(
                concat(tom().select("addresses").from(people).every().asIterable()))
        .where(eq("zip", "30308")).into(selected);
        System.out.println(selected);
    }

}
