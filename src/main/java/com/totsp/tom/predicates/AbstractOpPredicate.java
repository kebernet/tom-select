/**
 *    Copyright 2013 Robert Cooper
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
