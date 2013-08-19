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
import com.totsp.tom.predicates.Contains;
import com.totsp.tom.predicates.Equals;
import com.totsp.tom.predicates.GreaterThan;
import com.totsp.tom.predicates.GreaterThanOrEqual;
import com.totsp.tom.predicates.LessThan;
import com.totsp.tom.predicates.LessThanOrEqual;
import com.totsp.tom.predicates.Not;
import com.totsp.tom.util.Functions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class Tom<T> implements Select<T>, From<T>, Where<T>, Finally<T> {

    private static Introspector INTROSPECTOR;

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

    @Override
    public From select() {
        this.selector = (Function<T, ?>) Functions.IDENTITY;
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

    public static void initialize(Introspector introspector){
        INTROSPECTOR = introspector;
    }

    public static <T, P extends Serializable, R extends Predicate<T> & Serializable> R eq(String propertyExpression, P value){
        return (R) new Equals<T, P>(value, propertyExpression);
    }

    public static <T, P extends Comparable & Serializable, R extends Predicate<T> & Serializable> R lt(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return (R) new LessThan<T, P>(value, propertyExpression);
    }

    public static <T, P extends Comparable & Serializable, R extends Predicate<T> & Serializable> R ltEq(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return (R) new LessThanOrEqual<T, P>(value, propertyExpression);
    }

    public static <T, P extends Comparable & Serializable, R extends Predicate<T> & Serializable> R gt(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return (R) new GreaterThan<T, P>(value, propertyExpression);
    }

    public static <T, P extends Comparable & Serializable, R extends Predicate<T> & Serializable> R gtEq(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return (R) new GreaterThanOrEqual<T,P>(value, propertyExpression);
    }

    public static <T, P extends Predicate<T> & Serializable, R extends Predicate<T> & Serializable> R not(@Nonnull P predicate) {
        checkNotNull(predicate, "You must supply a predicate function");
        return (R) new Not<T>(predicate);
    }

    public static <T, P extends Serializable, R extends Predicate<T> & Serializable> R contains(@Nonnull String propertyExpression, @Nullable P value){
        checkNotNull(propertyExpression, "You must supply a property expression.");
        return (R) new Contains<T, P>(value, propertyExpression);
    }

    public static <T> Select<T> tom(){
        return new Tom<T>();
    }

    public static <T> Predicate<T> any(Predicate<T>... predicates){
        return new Any<T>(predicates);
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

    public static Introspector getIntrospector() {
        return INTROSPECTOR;
    }
}
