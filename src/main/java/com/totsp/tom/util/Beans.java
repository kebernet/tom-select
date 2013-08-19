package com.totsp.tom.util;

import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.exceptions.EvaluationException;

/**
 *
 */
public class Beans {

    public static <P> P read(Introspector i, String expression, Object introspectable){
        try {
            return (P) i.getDescriptor(introspectable)
                    .getProperty(expression)
                    .getAccessorMethod()
                    .invoke(introspectable, null);
        } catch (Exception e) {
            throw new EvaluationException("Unabled to read ["+expression+"] on the "+introspectable.getClass()+"  == "+introspectable, e);
        }
    }
}
