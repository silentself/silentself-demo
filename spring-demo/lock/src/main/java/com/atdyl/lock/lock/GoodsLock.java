package com.atdyl.lock.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dong YL
 * @version V1.0 2020/1/17 20:08
 */
public class GoodsLock {

    Lock lock = new ReentrantLock();

    Condition consumer = lock.newCondition();
    Condition produce = lock.newCondition();


    private volatile int total = 0;


    public void sub() {
        lock.lock();

        try {
            while (total <= 0) {
                consumer.await();
            }

            total--;
            System.err.println(Thread.currentThread().getName() + ":" + total);
            produce.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void plus() {
        lock.lock();
        try {
            while (total >= 1) {
                produce.await();
            }
            total++;
            System.err.println(Thread.currentThread().getName() + ":" + total);
            consumer.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
