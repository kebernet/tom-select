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

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 *
 */
public class Any<T> implements Predicate<T>, Serializable {

    private final Predicate<T>[] predicates;

    public Any(Predicate<T>... predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean apply(@Nullable T t) {
        for(Predicate<T> p : predicates){
            if(p.apply(t)){
                return  true;
            }
        }
        return false;
    }
}
