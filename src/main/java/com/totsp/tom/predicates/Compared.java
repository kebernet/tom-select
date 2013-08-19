package com.totsp.tom.predicates;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 *
 */
public abstract class Compared<T, P extends Comparable & Serializable> extends AbstractOpPredicate<T, P> {

    private final P value;
    public Compared(P value, String propertyExpression) {
        super(propertyExpression);
        this.value = value;
    }


    @Override
    protected boolean applyInternal(P read) {
        return compare(value, read);
    }

    protected abstract boolean compare(@Nullable P value, @Nullable P read);
}
