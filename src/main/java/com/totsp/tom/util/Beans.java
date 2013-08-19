package com.totsp.tom.util;

import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.exceptions.EvaluationException;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.LinkedList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class Beans {

    public static <P> P read(@Nonnull Introspector i, @Nonnull String expression, Object introspectable){
        checkNotNull(i, "No introspector provided. Have you called Tom.initialize?");
        checkNotNull(expression, "No property expression specified.");
        if(introspectable == null){
            return null;
        }
        Object read = readInternal(i, expression, introspectable);
        return (P) read;

    }

    private static Object readInternal(Introspector i, String expression, Object introspectable){
        LinkedList result = new LinkedList();
        try {
            int dotIndex = expression.indexOf('.');
            String childExpressions = null;
            if(dotIndex > 0){
                childExpressions = expression.substring(dotIndex + 1);
                expression = expression.substring(0, dotIndex);
            }

            Object read =  i.getDescriptor(introspectable)
                    .getProperty(expression)
                    .getAccessorMethod()
                    .invoke(introspectable, null);
            if("_size".equals(childExpressions) && read instanceof Collection){
                return ((Collection) read).size();
            } else if(read instanceof Iterable && childExpressions != null){
                Iterable it = (Iterable) read;
                for(Object o : it){
                    Object child = readInternal(i, childExpressions, o);
                    if(child instanceof LinkedList){
                        result.addAll((LinkedList) child);
                    } else {
                        result.add(child);
                    }
                }
                return result;
            } else {
                return read;
            }
        } catch (Exception e) {
            throw new EvaluationException("Unabled to read ["+expression+"] on the "+introspectable.getClass()+"  == "+introspectable, e);
        }
    }
}
