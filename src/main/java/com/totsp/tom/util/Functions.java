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
package com.totsp.tom.util;

import com.google.common.base.Function;

import javax.annotation.Nullable;

/**
 * Utility Functions.
 */
public class Functions {

    public static final Function<?, ?> IDENTITY = new Function<Object, Object>() {
        @Nullable
        @Override
        public Object apply(@Nullable Object t) {
            return t;
        }
    };
}
