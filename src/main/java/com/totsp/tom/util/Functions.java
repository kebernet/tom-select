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
