package com.totsp.tom.functions;

import com.google.common.base.Function;
import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.Tom;
import com.totsp.tom.util.Beans;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class PropertiesSelectorFunction<T> implements Function<T, Map<String, Object>>, Serializable {

    private final List<String> propertyExpressions;

    public PropertiesSelectorFunction(List<String> propertyExpressions) {
        this.propertyExpressions = propertyExpressions;
    }


    @Nullable
    @Override
    public Map<String, Object> apply(@Nullable T t) {
        if(t == null){
            return null;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        for(String expression : this.propertyExpressions){
            result.put(expression, Beans.read(Tom.getIntrospector(), expression, t));
        }
        return result;
    }
}
