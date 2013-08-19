package com.totsp.tom;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.totsp.gwittir.introspection.Introspector;
import com.totsp.tom.functions.PropertiesSelectorFunction;
import com.totsp.tom.functions.PropertySelectionFunction;
import com.totsp.tom.interfaces.Finally;
import com.totsp.tom.interfaces.From;
import com.totsp.tom.interfaces.Select;
import com.totsp.tom.interfaces.Where;
import com.totsp.tom.predicates.All;
import com.totsp.tom.predicates.Any;
import com.totsp.tom.predicates.Equals;
import com.totsp.tom.predicates.GreaterThan;
import com.totsp.tom.predicates.GreaterThanOrEqual;
import com.totsp.tom.predicates.LessThan;
import com.totsp.tom.predicates.LessThanOrEqual;
import com.totsp.tom.predicates.Not;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class Tom<T> implements Select<T>, From<T>, Where<T>, Finally<T> {

    private static Introspector INTROSPECTOR;
    private static final Function<?, ?> IDENTITY = new Function<Object, Object>() {
        @Nullable
        @Override
        public Object apply(@Nullable Object t) {
            return t;
        }
    };



    private Iterable<T> source;
    private Function<T, ?> selector;

    private Tom(){
        super();
    }

    public Where<T> from(Iterable<? extends Object> collection){
        this.source = (Iterable<T>) collection;
        return this;
    }

    public Finally<T> where(Predicate<T> predicate){
        this.source = Iterables.filter(this.source, predicate);
        return this;
    }

    public static void initialize(Introspector introspector){
        INTROSPECTOR = introspector;
    }

    public static <T> Tom<T> Tom(){
        return new Tom<T>();
    }

    public static <T, P> Predicate<T> eq(String propertyExpression, P value){
        return new Equals<T, P>(INTROSPECTOR, value, propertyExpression);
    }

    public static <T, P extends Comparable> Predicate<T> lt(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return new LessThan<T, P>(INTROSPECTOR, value, propertyExpression);
    }

    public static <T, P extends Comparable> Predicate<T> ltEq(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return new LessThanOrEqual<T, P>(INTROSPECTOR, value, propertyExpression);
    }

    public static <T, P extends Comparable> Predicate<T> gt(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return new GreaterThan<T, P>(INTROSPECTOR, value, propertyExpression);
    }

    public static <T, P extends Comparable> Predicate<T> gtEq(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return new GreaterThanOrEqual<T,P>(INTROSPECTOR, value, propertyExpression);
    }

    public static <T> Predicate<T> not(@Nonnull Predicate<T> predicate) {
        checkNotNull(predicate, "You must supply a predicate function");
        return new Not<T>(predicate);
    }

    @Override
    public From select() {
        this.selector = (Function<T, ?>) IDENTITY;
        return this;
    }

    @Override
    public From select(String propertyExpression) {
        checkNotNull(propertyExpression, "You must provide at least one property expression.");
        this.selector = new PropertySelectionFunction<T>(INTROSPECTOR, propertyExpression);
        return this;
    }

    @Override
    public From select(String propertyExpression, String... propertyExpressions) {
        checkNotNull(propertyExpression, "You must provide at least one property expression.");
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add(propertyExpression);
        if(propertyExpressions != null){
            Collections.addAll(linkedList, propertyExpressions);
        }
        this.selector = new PropertiesSelectorFunction<T>(INTROSPECTOR, linkedList);
        return this;
    }

    public static <T> Select<T> tom(){
        return new Tom<T>();
    }

    public static <T> Predicate<T> any(Predicate<T>... predicates){
        return new Any(predicates);
    }

    public static <T> Predicate<T> all(Predicate<T>... predicates){
        return new All<T>(predicates);
    }

    @Override
    public <R> Iterable<R> all() {
        return Iterables.transform(source, (Function<? super T,? extends R>) this.selector);
    }

    @Override
    public <R> void into(Collection<R> collection) {
        for(R r : (Iterable<R>) this.all()){
            collection.add(r);
        }
    }
}
