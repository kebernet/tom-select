package com.totsp.tom.predicates;

import com.google.common.base.Predicate;
import com.totsp.tom.Tom;
import com.totsp.tom.util.Beans;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 *
 */
public abstract class AbstractOpPredicate<T, P extends Serializable> implements Predicate<T>, Serializable {

    protected final String propertyExpression;

    public AbstractOpPredicate(String propertyExpression) {
        this.propertyExpression = propertyExpression;
    }

    @Override
    public boolean apply(@Nullable T t) {
        if(t == null){
            return false;
        } else {
            P read = Beans.read(Tom.getIntrospector(), this.propertyExpression, t);
            if(read != null && read instanceof Iterable){
                Iterable<P> iterable = (Iterable<P>) read;
                for(P check : iterable){
                    if(applyInternal(check)){
                        return true;
                    }
                }
                return false;
            } else {
                return applyInternal(read);
            }
        }
    }

    protected abstract boolean applyInternal(P read);
}
