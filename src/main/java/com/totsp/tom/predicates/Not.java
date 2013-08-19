package com.totsp.tom.predicates;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;

/**
 *
 */
public class Not<T> implements Predicate<T> {

    private final Predicate<T> inner;

    public Not(Predicate<T> inner) {
        this.inner = inner;
    }

    @Override
    public boolean apply(@Nullable T t) {
       return !inner.apply(t);
    }

}
