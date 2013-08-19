package com.totsp.tom.util;

import com.google.common.collect.Iterables;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 */
public class NumberStatistics<T extends Number & Comparable<T>> extends Statistics<T> {

    private final MathContext context;

    public NumberStatistics(MathContext context) {
        this.context = context;
    }

    public NumberStatistics(){
        this.context = new MathContext(6, RoundingMode.HALF_UP);
    }

    public BigDecimal mean(){
        int count = 0;
        BigDecimal value = new BigDecimal(0, context);
        for(T t : inner){
            value = value.add(new BigDecimal(t.toString(), context));
            count++;
        }
        return value.divide(new BigDecimal(count),context);
    }

    public BigDecimal medianAveraged(){
        int index = (inner.size() - 1) /2;
        if(inner.size() % 2 == 1){
            T low = Iterables.get(inner, index, null);
            return new BigDecimal(low.toString(), context);
        } else {
            int i = 0;
            T low = null;
            T high = null;
            for(T t: inner){
                if(index == i){
                    low = t;
                } else if(index + 1 == i){
                    high = t;
                    break;
                }
                i++;
            }
            return new BigDecimal(high.toString(), context).add(new BigDecimal(low.toString(), context)).divide(new BigDecimal(2, context), context);
        }

    }

}
