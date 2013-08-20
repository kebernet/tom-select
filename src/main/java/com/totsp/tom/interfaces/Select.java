package com.totsp.tom.interfaces;

import com.google.common.base.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: keber_000
 * Date: 8/16/13
 * Time: 12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Select<T> {

    From<T> select();
    From<T> select(@Nonnull String propertyExpression);
    From<T> select(@Nonnull String propertyExpression, @Nullable String... propertyExpressions);
    From<T> select(@Nonnull Function<T, ?> selector);
}
