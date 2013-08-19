package com.totsp.tom.predicates;

import java.io.Serializable;

/**
 *
 */
public class LessThan<T, P extends Comparable & Serializable> extends Compared<T, P>{

    public LessThan(P value, String propertyExpression) {
        super(value, propertyExpression);
    }

    @Override
    protected boolean compare(P value, P read) {
        if(value == read || value == null && read != null){
            return false;
        }
        return value != null && read == null || read.compareTo(value) < 0;
    }
}
