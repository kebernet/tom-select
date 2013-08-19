package com.totsp.tom.predicates;

import com.totsp.gwittir.introspection.Introspector;

/**
 *
 */
public class LessThan<T, P extends Comparable> extends Compared<T, P>{

    public LessThan(Introspector introspector, P value, String propertyExpression) {
        super(introspector, value, propertyExpression);
    }

    @Override
    protected boolean compare(P value, P read) {
        if(value == read || value == null && read != null){
            return false;
        }
        return value != null && read == null || read.compareTo(value) < 0;
    }
}
