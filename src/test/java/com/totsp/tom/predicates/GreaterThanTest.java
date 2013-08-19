package com.totsp.tom.predicates;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class GreaterThanTest {
    @Test
    public void testCompare() throws Exception {

        TestBean bean1 = new TestBean();

        GreaterThan gt = new GreaterThan(new JVMIntrospector(), 1, "intProperty");
        assertFalse(gt.apply(bean1));

        bean1.setIntProperty(0);
        assertFalse(gt.apply(bean1));

        bean1.setIntProperty(1);
        assertFalse(gt.apply(bean1));

        bean1.setIntProperty(2);
        assertTrue(gt.apply(bean1));

        gt = new GreaterThan(new JVMIntrospector(), null, "intProperty");
        bean1.setIntProperty(null);
        assertFalse(gt.apply(bean1));

        bean1.setIntProperty(0);
        assertFalse(gt.apply(bean1));

        bean1.setIntProperty(1);
        assertFalse(gt.apply(bean1));

        bean1.setIntProperty(2);
        assertFalse(gt.apply(bean1));





    }
}
