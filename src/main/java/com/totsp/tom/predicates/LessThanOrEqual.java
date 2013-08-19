package com.totsp.tom.predicates;

import com.totsp.gwittir.introspection.Introspector;

/**
 *
 */
public class LessThanOrEqual<T, P extends Comparable> extends Compared<T, P>{

    public LessThanOrEqual(Introspector introspector, P value, String propertyExpression) {
        super(introspector, value, propertyExpression);
    }

    @Override
    protected boolean compare(P value, P read) {
        if(value == read){
            return true;
        } else if(value == null && read != null){
            return false;
        }
        return value != null && read == null || read.compareTo(value) <= 0;
    }
}
