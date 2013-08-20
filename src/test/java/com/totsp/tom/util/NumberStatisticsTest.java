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

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class NumberStatisticsTest {

    @Test
    public void testMedians(){
        NumberStatistics<Integer> stats = new NumberStatistics<Integer>();
        for(int i = 0; i < 100; i++){
            stats.add(i);
        }
        assertEquals((Integer) 49, stats.median());
        //System.out.println(stats.median());
        assertEquals(new BigDecimal(49.5), stats.medianAveraged());
        //System.out.println(stats.medianAveraged());
    }

    @Test
    public void testMode(){
        NumberStatistics<Double> stats = new NumberStatistics<Double>();
        stats.add(1.0D);
        stats.add(1.5D);
        stats.add(3.0D);
        stats.add(1.5D);
        stats.add(0D);

        assertEquals(Double.valueOf(1.5), stats.mode());
    }

    @Test
    public void testMean(){
        NumberStatistics<Long> stats = new NumberStatistics<Long>();
        stats.add(2L);
        stats.add(2L);
        stats.add(3L);
        stats.add(3L);
        assertEquals(new BigDecimal(2.5), stats.mean());
    }
}
