package com.totsp.tom.predicates;

import com.totsp.gwittir.introspection.Introspector;

import javax.annotation.Nullable;

/**
 *
 */
public class GreaterThan<T, P extends Comparable> extends Compared<T, P>{

    public GreaterThan(Introspector introspector, P value, String propertyExpression) {
        super(introspector, value, propertyExpression);
    }

    @Override
    protected boolean compare(@Nullable P value, @Nullable P read) {
        if(value == read || value != null && read == null){
            return false;
        }
        return !(value == null && read != null) && read.compareTo(value) > 0;
    }
}
