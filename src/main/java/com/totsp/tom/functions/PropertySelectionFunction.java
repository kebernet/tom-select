package com.totsp.tom.functions;

import com.google.common.base.Function;
import com.totsp.tom.Tom;
import com.totsp.tom.util.Beans;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 *
 */
public class PropertySelectionFunction<T> implements Function<T, Object>, Serializable {
    private final String propertyExpression;

    public PropertySelectionFunction(String propertyExpression) {
        this.propertyExpression = propertyExpression;
    }

    @Nullable
    @Override
    public Object apply(@Nullable T t) {
        if(t == null){
            return null;
        } else {
            return Beans.read(Tom.getIntrospector(), propertyExpression, t);
        }
    }
}
