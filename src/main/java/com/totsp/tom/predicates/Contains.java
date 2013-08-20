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

import java.io.Serializable;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 8/19/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Contains<T, P extends Serializable> extends AbstractOpPredicate<T, P> {

    private final P value;
    private final String propertyExpression;

    public Contains(P value, String propertyExpression) {
        super(propertyExpression);
        this.value = value;
        this.propertyExpression = propertyExpression;
    }

    @Override
    protected boolean applyInternal(P read) {
         Collection<P> collection = (Collection<P>) read;
        return collection == null ? false : collection.contains(value);
    }

}
