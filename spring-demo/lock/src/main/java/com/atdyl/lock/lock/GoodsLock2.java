package com.atdyl.lock.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本方式是对第一种方式的优化，参考queue的方式
 *
 * @author Dong YL
 * @version V1.0 2020/1/17 20:08
 */
public class GoodsLock2 {

    Lock consumer = new ReentrantLock();
    Lock produce = new ReentrantLock();

    Condition consumerCondition = consumer.newCondition();
    Condition produceCondition = produce.newCondition();


    private int total = 0;


    public void sub() {
        try {
            consumer.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            while (total <= 0) {
                consumerCondition.await();
            }

            total--;
            System.err.println(Thread.currentThread().getName() + ":" + total);
            consumerCondition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.unlock();
        }

        if (total <= 0) {
            try {
                produce.lockInterruptibly();
                produceCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                produce.unlock();
            }
        }

    }

    public void plus() {
        try {
            produce.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            while (total >= 1) {
                produceCondition.await();
            }
            total++;
            System.err.println(Thread.currentThread().getName() + ":" + total);
            produceCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            produce.unlock();
        }

        if (total > 0) {
            try {
                consumer.lockInterruptibly();
                consumerCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                consumer.unlock();
            }

        }
    }

}
