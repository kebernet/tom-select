package com.totsp.tom.predicates;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class LessThanTest {

    @Test
    public void testCompare(){

        TestBean bean1 = new TestBean();

        LessThan lt = new LessThan(new JVMIntrospector(), 1, "intProperty");
        assertTrue(lt.apply(bean1));

        bean1.setIntProperty(0);
        assertTrue(lt.apply(bean1));

        bean1.setIntProperty(1);
        assertFalse(lt.apply(bean1));

        bean1.setIntProperty(2);
        assertFalse(lt.apply(bean1));

    }




}
