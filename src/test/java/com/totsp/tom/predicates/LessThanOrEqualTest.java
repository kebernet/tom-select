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
package com.totsp.tom.predicates;

import com.totsp.gwittir.rebind.introspection.JVMIntrospector;
import com.totsp.tom.Tom;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class LessThanOrEqualTest {
    @Test
    public void testCompare() throws Exception {
        Tom.initialize(new JVMIntrospector());

        TestBean bean1 = new TestBean();

        LessThanOrEqual lteq = new LessThanOrEqual(1, "intProperty");
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(0);
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(1);
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(2);
        assertFalse(lteq.apply(bean1));

        lteq = new LessThanOrEqual(null, "intProperty");
        assertFalse(lteq.apply(bean1));

        bean1.setIntProperty(null);
        assertTrue(lteq.apply(bean1));

        lteq = new LessThanOrEqual(1, "intProperty");
        assertTrue(lteq.apply(bean1));

        bean1.setIntProperty(1);
        assertTrue(lteq.apply(bean1));
    }
}
