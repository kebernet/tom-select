package com.totsp.tom.predicates;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import com.totsp.tom.Tom;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class LessThanTest {

    @Test
    public void testCompare(){
        Tom.initialize(new JVMIntrospector());

        TestBean bean1 = new TestBean();

        LessThan lt = new LessThan(1, "intProperty");
        assertTrue(lt.apply(bean1));

        bean1.setIntProperty(0);
        assertTrue(lt.apply(bean1));

        bean1.setIntProperty(1);
        assertFalse(lt.apply(bean1));

        bean1.setIntProperty(2);
        assertFalse(lt.apply(bean1));

    }




}
