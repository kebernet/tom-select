package com.totsp.tom.predicates;

import com.google.common.base.Predicate;
import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.util.Beans;

import javax.annotation.Nullable;

/**
 *
 */
public abstract class Compared<T, P extends Comparable> implements Predicate<T> {

    private final Introspector introspector;
    private final P value;
    private final String propertyExpression;

    public Compared(Introspector introspector, P value, String propertyExpression) {
        this.introspector = introspector;
        this.value = value;
        this.propertyExpression = propertyExpression;
    }


    @Override
    public boolean apply(@Nullable T t) {
        if(t != null){
            P read = Beans.read(this.introspector, propertyExpression, t);
            return compare(value, read);
        }
        return false;
    }

    protected abstract boolean compare(@Nullable P value, @Nullable P read);
}
