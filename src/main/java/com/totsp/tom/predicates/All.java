package com.totsp.tom.predicates;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 *
 */
public class All<T> implements Predicate<T>, Serializable {

    private final Predicate<T>[] predicates;

    public All(Predicate<T>... predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean apply(@Nullable T t) {
       for(Predicate<T> p : predicates){
           if(!p.apply(t)){
               return  false;
           }
       }
       return true;
    }

}
