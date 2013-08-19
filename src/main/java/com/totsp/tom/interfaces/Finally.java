package com.totsp.tom.interfaces;

import java.util.Collection;

/**
 *
 */
public interface Finally<T> {

    /**
     * Returns the result of the select as an interable
     * @param <R> The final type of the iterable.
     * @return An interable of the results;
     */
    <R> Iterable<R> asIterable();

    /**
     * Drains the results into a collection.
     * @param collection Target collection
     * @param <C> collection type
     * @return the collection passed in.
     */
    public <C extends Collection<?>> C into(C collection);
}
