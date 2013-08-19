package com.totsp.tom.interfaces;

import com.google.common.base.Predicate;

import javax.annotation.Nonnull;

/**
 * Created with IntelliJ IDEA.
 * User: keber_000
 * Date: 8/16/13
 * Time: 12:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Where<T> {

    Finally<T> where(@Nonnull Predicate<T> predicate);
}
