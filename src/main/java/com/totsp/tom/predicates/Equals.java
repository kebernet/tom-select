package com.totsp.tom.predicates;

import com.google.common.base.Predicate;
import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.util.Beans;

import javax.annotation.Nullable;

/**
 *
 */
public class Equals<T, P> implements Predicate<T> {

    private final Introspector introspector;
    private final P value;
    private final String propertyExpression;

    public Equals(Introspector introspector, P value, String propertyExpression) {
        this.introspector = introspector;
        this.value = value;
        this.propertyExpression = propertyExpression;
    }


    @Override
    public boolean apply(@Nullable T t) {
        if(t != null){
            P read = Beans.read(this.introspector, propertyExpression, t);
            if(read == value || (value != null && value.equals(read))){
                return true;
            }
        }
        return false;
    }
}
