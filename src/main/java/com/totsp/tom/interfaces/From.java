package com.totsp.tom.interfaces;

import javax.annotation.Nonnull;

/**
 *
 */
public interface From<T> {

    Where from(@Nonnull Iterable<? extends Object> collection);
}
