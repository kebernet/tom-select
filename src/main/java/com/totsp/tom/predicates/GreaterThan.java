package com.totsp.tom.predicates;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 *
 */
public class GreaterThan<T, P extends Comparable & Serializable> extends Compared<T, P>{

    public GreaterThan(P value, String propertyExpression) {
        super(value, propertyExpression);
    }

    @Override
    protected boolean compare(@Nullable P value, @Nullable P read) {
        if(value == read || value != null && read == null){
            return false;
        }
        return !(value == null && read != null) && read.compareTo(value) > 0;
    }
}
