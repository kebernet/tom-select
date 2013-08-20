/**
 *    Copyright 2013 Robert Cooper
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
