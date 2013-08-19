package com.totsp.tom.predicates;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 8/19/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Contains<T, P extends Serializable> extends AbstractOpPredicate<T, P> {

    private final P value;
    private final String propertyExpression;

    public Contains(P value, String propertyExpression) {
        super(propertyExpression);
        this.value = value;
        this.propertyExpression = propertyExpression;
    }

    @Override
    protected boolean applyInternal(P read) {
         Collection<P> collection = (Collection<P>) read;
        return collection == null ? false : collection.contains(value);
    }

}
