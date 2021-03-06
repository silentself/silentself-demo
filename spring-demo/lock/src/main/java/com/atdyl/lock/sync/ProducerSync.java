package com.atdyl.lock.sync;


/**
 * @author Dong YL
 * @version V1.0 2020/1/17 19:50
 */
public class ProducerSync implements Runnable {


    private final Object lock;

    ProducerSync(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        /*  while (true) */
        synchronized (lock) {
            while (GoodsSync.num >= 1) {
                try {
                    System.err.println("等一会再生产");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("开始执行生产");
            GoodsSync.num++;
            System.out.println(Thread.currentThread().getName() + ": " + GoodsSync.num);
            lock.notifyAll();
        }

    }
}
