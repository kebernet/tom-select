package com.totsp.tom;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import com.totsp.tom.predicates.TestBean;
import org.junit.Test;

import java.util.ArrayList;

import static com.totsp.tom.Tom.*;
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
                                                lt("intProperty", 10),
                                                gt("intProperty", 90))
                                     ).all();
        for(TestBean b: it){
            assertTrue(b.getIntProperty() < 10 || b.getIntProperty() > 90);
        }

    }
}
