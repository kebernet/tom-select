package com.totsp.tom.predicates;

import java.io.Serializable;

/**
 *
 */
public class LessThanOrEqual<T, P extends Comparable & Serializable> extends Compared<T, P>{

    public LessThanOrEqual(P value, String propertyExpression) {
        super(value, propertyExpression);
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
