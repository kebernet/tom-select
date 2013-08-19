package com.totsp.tom.interfaces;

import java.util.Collection;

/**
 *
 */
public interface Finally<T> {

    <R> Iterable<R> asIterable();
    <R> void into(Collection<R> collection);
}
