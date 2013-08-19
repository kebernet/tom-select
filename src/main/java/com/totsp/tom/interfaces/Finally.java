package com.totsp.tom.interfaces;

import java.util.Collection;

/**
 *
 */
public interface Finally<T> {

    <R> Iterable<R> all();
    <R> void into(Collection<R> collection);
}
