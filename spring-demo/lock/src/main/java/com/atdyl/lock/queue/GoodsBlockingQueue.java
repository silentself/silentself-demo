package com.atdyl.lock.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LinkedBlockingQueue 的队列是 lazy-init 的，但 ArrayBlockingQueue 在创建时就已经 init
 *
 * @author Dong YL
 * @version V1.0 2020/1/19 10:32
 */
public class GoodsBlockingQueue {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private BlockingQueue<Integer> queue;

    public GoodsBlockingQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void sub() {
        try {
            Integer take = queue.take();
            System.err.println(Thread.currentThread().getName() + ":" + take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void plus() {
        try {
            atomicInteger.incrementAndGet();
            System.err.println(Thread.currentThread().getName() + ":" + atomicInteger.get());
            queue.put(atomicInteger.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
