package com.atdyl.lock.sync;


/**
 * @author Dong YL
 * @version V1.0 2020/1/17 19:52
 */
public class ConsumerSync implements Runnable {

    private final Object lock;

    ConsumerSync(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        /*while (true)*/
        synchronized (lock) {
            while (GoodsSync.num <= 0) {
                try {
                    System.err.println("等一会再消耗");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("开始消耗");
            GoodsSync.num--;
            System.out.println(Thread.currentThread().getName() + ": " + GoodsSync.num);
            lock.notifyAll();
        }
    }
}
