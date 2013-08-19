package com.totsp.tom.predicates;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 *
 */
public class Not<T> implements Predicate<T>, Serializable {

    private final Predicate<T> inner;

    public <P extends Predicate<T> & Serializable> Not(P inner) {
        this.inner = inner;
    }

    @Override
    public boolean apply(@Nullable T t) {
       return !inner.apply(t);
    }

}
