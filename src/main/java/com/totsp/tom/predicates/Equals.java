package com.totsp.tom.predicates;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 *
 */
public class Equals<T, P extends Serializable> extends AbstractOpPredicate<T, P>{
    private final P value;

    public Equals(P value, String propertyExpression) {
        super(propertyExpression);
        this.value = value;

    }

    @Override
    protected boolean applyInternal(P read) {
        return Objects.equal(read, value);
    }
}
