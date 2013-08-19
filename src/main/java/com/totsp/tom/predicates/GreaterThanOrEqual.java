package com.totsp.tom.predicates;

import com.totsp.gwittir.introspection.Introspector;

/**
 *
 */
public class GreaterThanOrEqual<T, P extends Comparable> extends Compared<T, P>{

    public GreaterThanOrEqual(Introspector introspector, P value, String propertyExpression) {
        super(introspector, value, propertyExpression);
    }

    @Override
    protected boolean compare(P value, P read) {
       return value == read ||
               (value == null && read != null && (value != null && read == null || read.compareTo(value) >= 0));
    }
}
