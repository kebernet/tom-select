package com.totsp.tom.predicates;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class EqualsTest {
    @Test
    public void testApply() throws Exception {

        TestBean testBean1 = new TestBean();
        Equals eq = new Equals(new JVMIntrospector(), null, "intProperty");

        assertTrue(eq.apply(testBean1));

        eq = new Equals(new JVMIntrospector(), 1, "intProperty");
        testBean1.setIntProperty(1);
        assertTrue(eq.apply(testBean1));

        testBean1.setIntProperty(0);
        assertFalse(eq.apply(testBean1));

        testBean1.setIntProperty(2);
        assertFalse(eq.apply(testBean1));

        testBean1.setIntProperty(null);
        assertFalse(eq.apply(testBean1));

    }
}
