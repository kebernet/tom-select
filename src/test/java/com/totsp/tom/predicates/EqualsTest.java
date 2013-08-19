package com.totsp.tom.predicates;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import com.totsp.tom.Tom;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class EqualsTest {
    @Test
    public void testApply() throws Exception {

        Tom.initialize(new JVMIntrospector());
        TestBean testBean1 = new TestBean();
        Equals eq = new Equals(null, "intProperty");

        assertTrue(eq.apply(testBean1));

        eq = new Equals(1, "intProperty");
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
