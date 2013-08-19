package com.totsp.tom.predicates;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class LessThanOrEqualTest {
    @Test
    public void testCompare() throws Exception {

        TestBean bean1 = new TestBean();

        LessThanOrEqual lteq = new LessThanOrEqual(new JVMIntrospector(), 1, "intProperty");
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(0);
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(1);
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(2);
        assertFalse(lteq.apply(bean1));

        lteq = new LessThanOrEqual(new JVMIntrospector(), null, "intProperty");
        assertFalse(lteq.apply(bean1));

        bean1.setIntProperty(null);
        assertTrue(lteq.apply(bean1));

        lteq = new LessThanOrEqual(new JVMIntrospector(), 1, "intProperty");
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(1);
        assertTrue(lteq.apply(bean1));
    }
}
