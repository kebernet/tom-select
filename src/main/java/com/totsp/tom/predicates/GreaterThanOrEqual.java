package com.totsp.tom.predicates;

import java.io.Serializable;

/**
 *
 */
public class GreaterThanOrEqual<T, P extends Comparable & Serializable> extends Compared<T, P>{

    public GreaterThanOrEqual(P value, String propertyExpression) {
        super(value, propertyExpression);
    }

    @Override
    protected boolean compare(P value, P read) {
       return value == read ||
               (value == null && read != null && (value != null && read == null || read.compareTo(value) >= 0));
    }
}
