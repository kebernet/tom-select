package com.totsp.tom.util;

import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
public class Statistics<T extends Comparable<T> & Serializable> implements Collection<T> {

    protected TreeMultiset<T> inner = TreeMultiset.create();

    public T mode(){
        Multiset.Entry<T> check = null;
        for( Multiset.Entry<T> entry :  inner.entrySet()){
            if(check == null || entry.getCount() > check.getCount()){
                check = entry;
            }
        }
        return check == null ? null : check.getElement();
    }

    public T median(){
        int index = (inner.size() - 1) /2;

        return Iterables.get(inner, index, null);
    }

    public Map<T, Integer> histogram(){
        LinkedHashMap<T, Integer> result = new LinkedHashMap<T, Integer>();
        for( Multiset.Entry<T> entry :  inner.entrySet()){
            result.put(entry.getElement(), entry.getCount());
        }
        return result;
    }

    @Override
    public int size() {
        return inner.size();
    }

    @Override
    public boolean isEmpty() {
        return inner.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return inner.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return inner.iterator();
    }

    @Override
    public Object[] toArray() {
        return inner.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return inner.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return inner.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return inner.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return inner.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return inner.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return inner.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return inner.retainAll(c);
    }

    @Override
    public void clear() {
        inner.clear();
    }
}
