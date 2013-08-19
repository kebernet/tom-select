package com.totsp.tom.util;

import com.google.common.base.Function;

import javax.annotation.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: Robert
 * Date: 8/19/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
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
