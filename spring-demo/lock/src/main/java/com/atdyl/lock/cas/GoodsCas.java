package com.atdyl.lock.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dong YL
 * @version V1.0 2020/1/19 10:12
 */
public class GoodsCas {

    private static volatile int total = 0;

    AtomicInteger ai = new AtomicInteger(total);

    public void sub(){
//        while (ai.compareAndSet())
    }
}
