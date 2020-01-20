package com.atdyl.lock.lock;

/**
 * ①condition vs object
 * ConditionObject相比较与Object天生可以实现定向通知
 * ②
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 10:03
 */
public class MainClassLock {

    public static void main(String[] args)  {
        GoodsLock2 goodsLock = new GoodsLock2();
        for (int i = 0; i < 1000; i++) {
            new Thread(goodsLock::sub, "consumer:").start();
            new Thread(goodsLock::plus, "produce:").start();
        }
    }

}
