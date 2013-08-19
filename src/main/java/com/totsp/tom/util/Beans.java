package com.totsp.tom.util;

import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.exceptions.EvaluationException;

import javax.annotation.Nonnull;
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
        LinkedList read = readInternal(i, expression, introspectable);
        if(read.isEmpty()){
            return null;
        } else if(read.getFirst() == read.getLast()){
            return (P) read.getFirst();
        } else {
            return (P) read;
        }
    }

    private static LinkedList readInternal(Introspector i, String expression, Object introspectable){
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
            if(read instanceof Iterable && childExpressions != null){
                Iterable it = (Iterable) read;
                for(Object o : it){
                    result.addAll(readInternal(i, childExpressions, o));
                }
            } else {
                result.add(read);
            }
        } catch (Exception e) {
            throw new EvaluationException("Unabled to read ["+expression+"] on the "+introspectable.getClass()+"  == "+introspectable, e);
        }
        return result;
    }
}
