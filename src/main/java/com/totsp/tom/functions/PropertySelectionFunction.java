package com.totsp.tom.functions;

import com.google.common.base.Function;
import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.util.Beans;

import javax.annotation.Nullable;

/**
 *
 */
public class PropertySelectionFunction<T> implements Function<T, Object> {
    private final Introspector introspector;
    private final String propertyExpression;

    public PropertySelectionFunction(Introspector introspector, String propertyExpression) {
        this.introspector = introspector;
        this.propertyExpression = propertyExpression;
    }

    @Nullable
    @Override
    public Object apply(@Nullable T t) {
        if(t == null){
            return null;
        } else {
            return Beans.read(introspector, propertyExpression, t);
        }
    }
}
