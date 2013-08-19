package com.totsp.tom;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import com.totsp.tom.predicates.TestBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                                     .where(any(
                                                ltEq("intProperty", 10),
                                                gtEq("intProperty", 90))
                                     ).all();
        for(TestBean b: it){
            assertTrue(b.getIntProperty() <= 10 || b.getIntProperty() >= 90);
        }

        it = tom().select()
                .from(beans)
                .where( ltEq("intProperty", 10)).all();
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
        tom().select().from(people).where(any(eq("addresses.zip", "10001"), eq("addresses.zip", "10002"))).into(selected);

        assertEquals(2, selected.size());
        assertEquals("Doe", selected.get(0).getLastName());
        assertEquals("Doe", selected.get(1).getLastName());
    }


}
