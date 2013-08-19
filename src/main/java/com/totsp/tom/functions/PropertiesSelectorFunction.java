package com.totsp.tom.functions;

import com.google.common.base.Function;
import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.util.Beans;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class PropertiesSelectorFunction<T> implements Function<T, Map<String, Object>> {

    private final Introspector introspector;
    private final List<String> propertyExpressions;

    public PropertiesSelectorFunction(Introspector introspector, List<String> propertyExpressions) {
        this.introspector = introspector;
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
            result.put(expression, Beans.read(introspector, expression, t));
        }
        return result;
    }
}
